package inggitsemut.whattodo.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import javax.xml.transform.Result;

import inggitsemut.whattodo.R;
import inggitsemut.whattodo.api.Service;
import inggitsemut.whattodo.models.Task;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static inggitsemut.whattodo.api.ConfigUtils.BASE_URL;

public class CreateActivity extends AppCompatActivity implements View.OnClickListener{

    EditText taskTitle, taskDetail;
    RadioButton rbVeryImportant, rbImportant, rbLessImportant;
    String type="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        // form create
        taskTitle = findViewById(R.id.taskTitle);
        taskDetail = findViewById(R.id.taskDetail);
        rbVeryImportant = findViewById(R.id.radioVeryImportant);
        rbImportant = findViewById(R.id.radioImportant);
        rbLessImportant = findViewById(R.id.radioLessImportant);

        // button save
        findViewById(R.id.btnSave).setOnClickListener(this);

    }


    private void createTask() {
        String title = taskTitle.getText().toString().trim();
        String detail = taskDetail.getText().toString().trim();

        if (rbVeryImportant.isChecked()){
            type="1";
        }
        else if(rbImportant.isChecked()){
            type="2";
        }
        else
            type="3";


        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Creating a task...");
        progressDialog.show();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Service service = retrofit.create(Service.class);

        Task data = new Task(title, detail, type);

        Call<Task> call = service.createTasks(
                data.getTitle(),
                data.getDetail(),
                data.getType()
        );

        //calling the api
        call.enqueue(new Callback<Task>() {
            @Override
            public void onResponse(Call<Task> call, Response<Task> response) {
                progressDialog.dismiss();

                Toast.makeText(
                        getApplicationContext(),
                        "Add Task Success!",
                        Toast.LENGTH_LONG).show();
                finish(); // kembali ke main activity
            }


            @Override
            public void onFailure(Call<Task> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), "failed", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnSave:
                createTask();
                break;
        }
    }
}

package inggitsemut.whattodo.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import inggitsemut.whattodo.R;
import inggitsemut.whattodo.api.Service;
import inggitsemut.whattodo.models.Task;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static inggitsemut.whattodo.api.ConfigUtils.BASE_URL;

public class EditActivity extends AppCompatActivity implements View.OnClickListener {

    EditText editTaskTitle, editTaskDetail;
    RadioButton rbVeryImportant, rbImportant, rbLessImportant;
    int id;
    String type = "";
    String title, detail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        // form create
        editTaskTitle = findViewById(R.id.taskTitle);
        editTaskDetail = findViewById(R.id.taskDetail);
        rbVeryImportant = findViewById(R.id.radioVeryImportant);
        rbImportant = findViewById(R.id.radioImportant);
        rbLessImportant = findViewById(R.id.radioLessImportant);

        // button save
        findViewById(R.id.btnSave).setOnClickListener(this);

//        Intent intent = getIntent();
//        id = intent.getIntExtra("id",0);
//        title = intent.getStringExtra("title");
//        detail = intent.getStringExtra("detail");
//        type = intent.getStringExtra("type");

//        setDataFromIntentExtra();
    }

//    private void setDataFromIntentExtra(){
//        if(id!=0){
//            editTaskTitle.setText(title);
//            editTaskDetail.setText(detail);
//            if (type.equals("1")){
//                rbVeryImportant.isChecked();
//            }
//            else if (type.equals("2")){
//                rbImportant.isChecked();
//            }
//            else if (type.equals("3")){
//                rbLessImportant.isChecked();
//            }
//
//            editTaskTitle.setFocusableInTouchMode(true);
//            editTaskDetail.setFocusableInTouchMode(true);
//        }
//    }

    private void updateTask(){
        String title = editTaskTitle.getText().toString().trim();
        String detail = editTaskTitle.getText().toString().trim();

        if (rbVeryImportant.isChecked()){
            type="1";
        }
        else if(rbImportant.isChecked()){
            type="2";
        }
        else
            type="3";


        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Updating...");
        progressDialog.show();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Service service = retrofit.create(Service.class);

        Task data = new Task(title, detail, type);

        Call<Task> call = service.updateTask(
                data.getId(),
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
                        "Success update task!",
                        Toast.LENGTH_LONG).show();
                finish(); // kembali ke main activity
            }


            @Override
            public void onFailure(Call<Task> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), "Failed to update task", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnSave:
                updateTask();
                break;
        }
    }
}

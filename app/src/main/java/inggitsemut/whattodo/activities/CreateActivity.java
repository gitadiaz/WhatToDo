package inggitsemut.whattodo.activities;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import inggitsemut.whattodo.R;

public class CreateActivity extends AppCompatActivity {

    EditText taskTitle, taskDetail;
    RadioGroup radioGroup;
    RadioButton radioButton;
    Button btnSave;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        taskTitle = findViewById(R.id.taskTitle);
        taskDetail = findViewById(R.id.taskDetail);
        radioGroup = findViewById(R.id.radioGroup);

        btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //pilih radio button yang ada di radio button group
                int selectedId = radioGroup.getCheckedRadioButtonId();

                // mencari radio button
                radioButton = (RadioButton) findViewById(selectedId);

                // proggress dialog


            }
        });

    }
}

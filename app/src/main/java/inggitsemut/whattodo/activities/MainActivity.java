package inggitsemut.whattodo.activities;

import android.app.ProgressDialog;
import android.app.Service;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import inggitsemut.whattodo.R;
import inggitsemut.whattodo.adapter.TaskAdapter;
import inggitsemut.whattodo.api.ConfigUtils;
import inggitsemut.whattodo.models.Task;
import inggitsemut.whattodo.models.TaskList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    SearchView searchBar;
    TextView btnAdd;

    private RecyclerView recyclerViewTask;
    private ArrayList<Task> tasks;
    private RecyclerView.Adapter taskAdapter;

    Service service;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchBar = findViewById(R.id.searchBar);
        btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentCreate = new Intent(MainActivity.this, CreateActivity.class);
                startActivity(intentCreate);
            }
        });

        recyclerViewTask = findViewById(R.id.rvTask);
        recyclerViewTask.setAdapter( taskAdapter );

        // api
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConfigUtils.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        inggitsemut.whattodo.api.Service service = retrofit.create(inggitsemut.whattodo.api.Service.class);

        Call<TaskList> call = service.getTaskLists();

        call.enqueue(new Callback<TaskList>() {
            @Override
            public void onResponse(Call<TaskList> call, Response<TaskList> response) {
                taskAdapter = new TaskAdapter(response.body().getTaskList(), getApplicationContext());
                recyclerViewTask.setHasFixedSize(true);
                recyclerViewTask.setAdapter(taskAdapter);

            }

            @Override
            public void onFailure(Call<TaskList> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_SHORT).show();
            }
        });

    }


}


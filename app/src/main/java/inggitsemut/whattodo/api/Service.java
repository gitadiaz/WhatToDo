package inggitsemut.whattodo.api;

import inggitsemut.whattodo.models.Task;
import inggitsemut.whattodo.models.TaskList;
import retrofit2.Call;
import retrofit2.http.GET;

public interface Service {

    @GET("tasks")
    Call<TaskList> getTaskLists(
    );

}
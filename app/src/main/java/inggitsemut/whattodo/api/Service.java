package inggitsemut.whattodo.api;

import inggitsemut.whattodo.models.Task;
import inggitsemut.whattodo.models.TaskList;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface Service {
    // get task list
    @GET("tasks")
    Call<TaskList> getTaskLists(
    );

    // create task
    @FormUrlEncoded
    @POST("tasks")
    Call<Task> createTasks(
        @Field("title") String title,
        @Field("detail") String detail,
        @Field("type") String type
    );

    // update task
    @FormUrlEncoded
    @PUT("tasks")
    Call<Task> updateTask(
        @Field("id") int id,
        @Field("title") String title,
        @Field("detail") String detail,
        @Field("type") String type

    );

    // delete task
    @DELETE("tasks")
    Call<Task> deleteTask(
        @Field("id") int id
    );
}
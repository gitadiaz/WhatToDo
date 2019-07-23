package inggitsemut.whattodo.models;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> data;

    public TaskList() {

    }

    public ArrayList<Task> getTaskList() {
        return data;
    }

    public void setTaskList(ArrayList<Task> data) {
        this.data = data;
    }
}

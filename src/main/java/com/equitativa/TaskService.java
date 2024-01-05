package com.equitativa;

// TaskService.java
import com.equitativa.model.Task;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TaskService implements Serializable {
    private List<Task> tasks;

    public TaskService() {
        this.tasks = new ArrayList<>();
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void deleteTask(Task task) {
        tasks.remove(task);
    }
}

package com.equitativa;

// TaskService.java
import com.equitativa.model.Priority;
import com.equitativa.model.Task;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TaskService implements Serializable {
    private List<Task> tasks;

    public TaskService() {
        this.tasks = new ArrayList<>();

        tasks.add(Task.builder().id(UUID.randomUUID()).title("ABC").description("abc").dueDate(LocalDate.now()).priority(Priority.HIGH).build());
        tasks.add(Task.builder().id(UUID.randomUUID()).title("ABC").description("abc").dueDate(LocalDate.now()).priority(Priority.LOW).build());
        tasks.add(Task.builder().id(UUID.randomUUID()).title("ABC").description("abc").dueDate(LocalDate.now()).priority(Priority.HIGH).build());
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

package com.equitativa;

// TaskService.java
import com.equitativa.model.Priority;
import com.equitativa.model.Status;
import com.equitativa.model.Task;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TaskService implements Serializable {
    private static List<Task> tasks;

    public TaskService() {
        this.tasks = new ArrayList<>();

        Task.TaskBuilder taskBuilder = Task.builder()
                .id(UUID.randomUUID())
                .title("ipsum dolor sit amet")
                .description("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.")
                .dueDate(LocalDate.now())
                .priority(Priority.HIGH)
                .status(Status.PENDING);
        tasks.add(taskBuilder.id(UUID.randomUUID()).build());
        tasks.add(taskBuilder.id(UUID.randomUUID()).build());
        tasks.add(taskBuilder.id(UUID.randomUUID()).priority(Priority.MEDIUM).build());
        tasks.add(taskBuilder.id(UUID.randomUUID()).priority(Priority.MEDIUM).build());
        tasks.add(taskBuilder.id(UUID.randomUUID()).priority(Priority.LOW).build());
        tasks.add(taskBuilder.id(UUID.randomUUID()).priority(Priority.LOW).build());
        tasks.add(taskBuilder.id(UUID.randomUUID()).status(Status.COMPLETED).build());
        tasks.add(taskBuilder.id(UUID.randomUUID()).status(Status.COMPLETED).build());
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }


    public void updateTask(Task task) {
        tasks.remove(task);
        tasks.add(task);
    }

    public void deleteTask(Task task) {
        tasks = tasks.stream().filter(t -> t.getId().toString().equals(task.getId().toString())).toList();
    }
}

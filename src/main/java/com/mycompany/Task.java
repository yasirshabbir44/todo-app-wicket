package com.mycompany;

// Task.java
import java.io.Serializable;
import java.time.LocalDate;

public class Task implements Serializable {
    private String description;
    private LocalDate dueDate;
    private Priority priority;
    private boolean completed;

    public Task(String description, LocalDate dueDate, Priority priority) {
        this.description = description;
        this.dueDate = dueDate;
        this.priority = priority;
        this.completed = false;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public Priority getPriority() {
        return priority;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}

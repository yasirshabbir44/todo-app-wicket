package com.equitativa.service;

import com.equitativa.model.Task;
import com.equitativa.model.enumerate.Priority;
import com.equitativa.model.enumerate.Status;
import com.equitativa.repo.TaskRepository;
import com.google.inject.persist.Transactional;
import jakarta.inject.Inject;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TaskService implements Serializable {

    private final TaskRepository taskRepository;

    @Inject
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task getTask(UUID id) {
        return taskRepository.findById(id);
    }

    public List<Task> getAllTasks() {
        return taskRepository.getTaskList();
    }



    public void update(Task task) {
        taskRepository.update(task, task.getId());
    }


    public void delete(Task task) {
        taskRepository.delete(task);
    }


    @Transactional
    public void save(Task task) {
        taskRepository.save(task);
    }

}

package com.equitativa.service;

import com.equitativa.model.Priority;
import com.equitativa.model.Status;
import com.equitativa.model.Task;
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

    public void save(List<Task> task) {
        taskRepository.save(task);
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


    @Transactional
    public void testData() {

        List<Task> tasks = new ArrayList<>();
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

        //  tasks.forEach(taskRepository::save);

        Task task = taskBuilder.id(UUID.randomUUID()).build();
        for (Task t : tasks) {
            taskRepository.save(t);
        }

    }
}

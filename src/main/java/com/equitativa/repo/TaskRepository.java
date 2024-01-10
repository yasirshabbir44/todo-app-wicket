package com.equitativa.repo;

import com.equitativa.model.Person;
import com.equitativa.model.Task;
import com.google.inject.persist.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

public class TaskRepository extends BaseRepository<Task> implements Serializable {

    private static final String SELECT_ALL_TASKS = "SELECT t FROM Task t";


    public Task findById(UUID id) {
        return findById(id, Task.class);
    }

    public List<Task> getTaskList() {
        return getEntityManager()
                .createQuery(SELECT_ALL_TASKS, Task.class)
                .getResultList();

    }


    public void update(Task updatedTask, UUID id){
        Task task = findById(id);
        getEntityManager().getTransaction().begin();
        task.setStatus(updatedTask.getStatus());
        task.setPriority(updatedTask.getPriority());
        getEntityManager().getTransaction().commit();
    }
}

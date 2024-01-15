package com.equitativa.repo;

import com.equitativa.model.Task;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

public class TaskRepository extends BaseRepository<Task> implements Serializable {

    private static final String SELECT_ALL_TASKS = "SELECT t FROM Task t";

    private static final String DELETE_TASK = "DELETE FROM Task t where t.id =:deletedId";


    public Task findById(UUID id) {
        return findById(id, Task.class);
    }

    public List<Task> getTaskList() {
        return getEntityManager()
                .createQuery(SELECT_ALL_TASKS, Task.class)
                .getResultList();

    }


    public void delete(Task task) {

        Task t = findById(task.getId());
        EntityManager em = getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.remove(t);
        transaction.commit();
    }

    public void update(Task task, UUID id) {
        Task t = findById(task.getId());

        EntityManager em = getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();


        t.setProject(task.getProject());
        t.setPerson(task.getPerson());
        t.setPriority(task.getPriority());
        t.setDescription(task.getDescription());
        t.setTitle(task.getTitle());
        t.setStatus(task.getStatus());
        t.setDueDate(task.getDueDate());

        em.merge(t);
        transaction.commit();
    }


}

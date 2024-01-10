package com.equitativa.repo;


import jakarta.inject.Inject;
import jakarta.inject.Provider;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.UUID;

public abstract class BaseRepository<T extends Object> {

    @Inject
    private Provider<EntityManager> entityManagerProvider;

    public void save(T entity) {
        getEntityManager().persist(entity);
    }


    public void save(List<T> entity) {
        getEntityManager().persist(entity);
    }

    public T findById(UUID id, Class<T> type) {
        T entity = getEntityManager().find(type, id);
        if (entity == null) {
            throw new NoSuchElementException("No entity with type [" + type.getName() + "] and ID [" + id + "] exists.");
        }
        return entity;
    }


    public EntityManager getEntityManager() {
        return entityManagerProvider.get();
    }

    public void flush(){
        getEntityManager().flush();
    }
}

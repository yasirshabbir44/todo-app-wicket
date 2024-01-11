package com.equitativa.repo;

import com.equitativa.model.Person;
import com.equitativa.model.Project;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

public class ProjectRepository extends BaseRepository<Project> implements Serializable {

    private static final String SELECT_ALL_PROJECTS = "SELECT p FROM Project p";


    public Project findById(UUID id) {
        return findById(id, Project.class);
    }

    public List<Project> getPersonList() {
        return getEntityManager()
                .createQuery(SELECT_ALL_PROJECTS, Project.class)
                .getResultList();

    }
}

package com.equitativa.service;

import com.equitativa.model.Person;
import com.equitativa.model.Project;
import com.equitativa.repo.PersonRepository;
import com.equitativa.repo.ProjectRepository;
import jakarta.inject.Inject;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

public class ProjectService implements Serializable {

    private final ProjectRepository projectRepository;

    @Inject
    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public Project getUser(UUID id) {
        return projectRepository.findById(id);
    }

    public List<Project> getAllProjects() {
        return projectRepository.getPersonList();
    }
}

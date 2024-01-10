package com.equitativa.repo;

import com.equitativa.model.Person;
import jakarta.inject.Inject;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

public class PersonService implements Serializable {

    private final PersonRepository sqlPersonRepository;

    @Inject
    public PersonService( PersonRepository sqlPersonRepository) {
        this.sqlPersonRepository = sqlPersonRepository;
    }

    public Person getUser(UUID id) {
        return sqlPersonRepository.findById(id);
    }

    public List<Person> getAllUsers() {
        return sqlPersonRepository.getPersonList();
    }
}

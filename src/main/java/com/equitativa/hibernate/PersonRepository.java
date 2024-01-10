package com.equitativa.hibernate;

import com.equitativa.model.Person;
import com.equitativa.repo.BaseRepository;


import java.io.Serializable;
import java.util.List;
import java.util.UUID;

public class PersonRepository extends BaseRepository<Person> implements Serializable {

    private static final String SELECT_ALL_PERSONS = "SELECT p FROM Person p";


    public Person findById(UUID id) {
        return findById(id, Person.class);
    }

    public List<Person> getPersonList() {
        return getEntityManager()
                .createQuery(SELECT_ALL_PERSONS, Person.class)
                .getResultList();

    }
}

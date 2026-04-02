package org.roman.carsharing.service.impl;

import org.roman.carsharing.model.entity.Person;

import java.util.List;

public interface PersonDataBase {

    List<Person> listObject();

    Person getObject(int id);

    void addObject(Person person);

    void updateObject(Person person, int id);

    void deleteObject(int id);
}
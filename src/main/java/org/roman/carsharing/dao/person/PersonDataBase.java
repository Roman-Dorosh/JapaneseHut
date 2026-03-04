package org.roman.carsharing.dao.person;

import org.roman.carsharing.model.Person;

import java.util.List;

public interface PersonDataBase {

    List<Person> listObject();

    Person getObject(int id);

    void addObject(Person person);

    void updateObject(Person person, int id);

    void deleteObject(int id);
}
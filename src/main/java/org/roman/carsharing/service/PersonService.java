package org.roman.carsharing.service;

import org.roman.carsharing.Entity.model.Person;
import org.roman.carsharing.repository.PersonRepository;
import org.roman.carsharing.service.impl.PersonDataBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PersonService implements PersonDataBase {

    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    @Transactional
    public List<Person> listObject() {
        return personRepository.listPerson();
    }

    @Override
    @Transactional
    public Person getObject(int id) {
        return personRepository.getPerson(id);
    }

    @Override
    @Transactional
    public void addObject(Person person) {
        personRepository.addPerson(person);
    }

    @Override
    @Transactional
    public void updateObject(Person person, int id) {
        personRepository.updatePerson(person, id);
    }

    @Override
    @Transactional
    public void deleteObject(int id) {
        personRepository.deletePerson(id);
    }
}
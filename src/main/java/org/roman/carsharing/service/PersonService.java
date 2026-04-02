package org.roman.carsharing.service;

import org.roman.carsharing.model.entity.Person;
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
    @Transactional(readOnly = true)
    public List<Person> listObject() {
        return personRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Person getObject(int id) {
        return personRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void addObject(Person person) {
        personRepository.save(person);
    }

    @Override
    @Transactional
    public void updateObject(Person person, int id) {
        Person updatePerson = personRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Person not found with id: " + id));

        updatePerson.setAge(person.getAge());
        updatePerson.setName(person.getName());
        updatePerson.setSurname(person.getSurname());
    }

    @Override
    @Transactional
    public void deleteObject(int id) {
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Person not found with id: " + id));
        personRepository.delete(person);
    }
}
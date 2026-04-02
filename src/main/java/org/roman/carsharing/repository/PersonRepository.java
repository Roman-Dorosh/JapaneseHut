package org.roman.carsharing.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.roman.carsharing.Entity.model.Person;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PersonRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Person> listPerson() {
        return entityManager.createQuery("from Person", Person.class)
                .getResultList();
    }

    public Person getPerson(int id) {
        return entityManager.find(Person.class, id);
    }

    public void addPerson(Person person) {
        entityManager.persist(person);
    }

    public void updatePerson(Person person, int id) {
        Person updatePerson = entityManager.find(Person.class, id);

        updatePerson.setAge(person.getAge());
        updatePerson.setName(person.getName());
        updatePerson.setSurname(person.getSurname());
    }

    public void deletePerson(int id) {
        Person person = entityManager.find(Person.class, id);
        entityManager.remove(person);
    }
}
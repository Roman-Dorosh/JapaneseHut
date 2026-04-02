package org.roman.carsharing.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.roman.carsharing.Entity.model.Car;

import org.roman.carsharing.Entity.model.Person;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CarRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Car> listCar() {
        return entityManager.createQuery("from Car", Car.class)
                .getResultList();
    }

    public Car getCar(int id) {
        return entityManager.find(Car.class, id);
    }

    public void addCar(Car car) {
        entityManager.persist(car);
    }

    public void updateCar(Car car, int id) {
        Car carUpdate = entityManager.find(Car.class, id);

        carUpdate.setModel(car.getModel());
        carUpdate.setEquipment(car.getEquipment());
        carUpdate.setYear_of_release(car.getYear_of_release());
    }

    public void deleteCar(int id) {
        Car car = entityManager.find(Car.class, id);
        entityManager.remove(car);
    }

    public List<Car> listVacantCar() {
        return entityManager.createQuery("from Car where person is null", Car.class)
                .getResultList();
    }

    public List<Car> listPurchasedCar(int id) {
        Person person = entityManager.find(Person.class, id);
        return person.getCars();
    }

    public void buyCar(int person_id, int car_id) {
        Person person = entityManager.find(Person.class, person_id);
        Car car = entityManager.find(Car.class, car_id);

        person.getCars().add(car);
        car.setPerson(person);
    }

    public void sellCar(int id) {
        Car car = entityManager.find(Car.class, id);
        car.setPerson(null);
    }
}
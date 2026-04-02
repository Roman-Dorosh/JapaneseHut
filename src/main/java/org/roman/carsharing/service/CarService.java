package org.roman.carsharing.service;

import jakarta.persistence.EntityNotFoundException;
import org.roman.carsharing.model.entity.Car;
import org.roman.carsharing.model.entity.Person;
import org.roman.carsharing.repository.CarRepository;
import org.roman.carsharing.repository.PersonRepository;
import org.roman.carsharing.service.impl.CarDataBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CarService implements CarDataBase {

    private final CarRepository carRepository;
    private final PersonRepository personRepository;

    @Autowired
    public CarService(CarRepository carRepository, PersonRepository personRepository) {
        this.carRepository = carRepository;
        this.personRepository = personRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Car> listObject() {
        return carRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Car getObject(int id) {
        return carRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void addObject(Car car) {
        carRepository.save(car);
    }

    @Override
    @Transactional
    public void updateObject(Car car, int id) {
        Car updateCar = carRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Person not found with id: " + id));

        updateCar.setModel(car.getModel());
        updateCar.setEquipment(car.getEquipment());
        updateCar.setYear_of_release(car.getYear_of_release());
    }

    @Override
    @Transactional
    public void deleteObject(int id) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Car not found with id: " + id));
        carRepository.delete(car);
    }

    @Override
    @Transactional
    public List<Car> listVacantObject() {
        return carRepository.findByPersonIsNull();
    }

    @Override
    @Transactional
    public List<Car> listPurchasedObjects(int id) {
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Person not found with id: " + id));
        return person.getCars();
    }

    @Override
    @Transactional
    public void buyObject(int person_id, int car_id) {
        Person person = personRepository.findById(person_id)
                .orElseThrow(() -> new EntityNotFoundException("Person not found with id: " + person_id));

        Car car = carRepository.findById(car_id)
                .orElseThrow(() -> new RuntimeException("Car not found with id: " + car_id));

        person.getCars().add(car);
        car.setPerson(person);
    }

    @Override
    @Transactional
    public void sellObject(int id) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Car not found: " + id));
        Person oldOwner = car.getPerson();
        if (oldOwner != null) {
            oldOwner.getCars().remove(car);
        }
        car.setPerson(null);
    }
}
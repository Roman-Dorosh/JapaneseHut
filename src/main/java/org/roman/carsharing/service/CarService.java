package org.roman.carsharing.service;

import org.roman.carsharing.Entity.model.Car;
import org.roman.carsharing.repository.CarRepository;
import org.roman.carsharing.service.impl.CarDataBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CarService implements CarDataBase {

    private final CarRepository carRepository;

    @Autowired
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    @Transactional
    public List<Car> listObject() {
        return carRepository.listCar();
    }

    @Override
    @Transactional
    public Car getObject(int id) {
        return carRepository.getCar(id);
    }

    @Override
    @Transactional
    public void addObject(Car car) {
        carRepository.addCar(car);
    }

    @Override
    @Transactional
    public void updateObject(Car car, int id) {
        carRepository.updateCar(car, id);
    }

    @Override
    @Transactional
    public void deleteObject(int id) {
        carRepository.deleteCar(id);
    }

    @Override
    @Transactional
    public List<Car> listVacantObject() {
        return carRepository.listVacantCar();
    }

    @Override
    @Transactional
    public List<Car> listPurchasedObjects(int id) {
        return carRepository.listPurchasedCar(id);
    }

    @Override
    @Transactional
    public void buyObject(int person_id, int car_id) {
        carRepository.buyCar(person_id, car_id);
    }

    @Override
    @Transactional
    public void sellObject(int id) {
        carRepository.sellCar(id);
    }
}
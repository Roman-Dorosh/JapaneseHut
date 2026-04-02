package org.roman.carsharing.service.impl;

import org.roman.carsharing.model.entity.Car;

import java.util.List;

public interface CarDataBase {

    List<Car> listObject();

    Car getObject(int id);

    void addObject(Car car);

    void updateObject(Car car, int id);

    void deleteObject(int id);

    List<Car> listVacantObject();

    List<Car> listPurchasedObjects(int id);

    void buyObject(int person_id, int car_id);

    void sellObject(int id);
}
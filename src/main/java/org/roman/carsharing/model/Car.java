package org.roman.carsharing.model;

public class Car {

    private int car_id;
    private int person_id;
    private int year_of_release;

    private String model;
    private String equipment;

    public Car(int car_id, int person_id, String model, int year_of_release, String equipment) {
        this.car_id = car_id;
        this.person_id = person_id;
        this.model = model;
        this.year_of_release = year_of_release;
        this.equipment = equipment;
    }

    public Car(int car_id, int year_of_release, String model, String equipment) {
        this.car_id = car_id;
        this.year_of_release = year_of_release;
        this.model = model;
        this.equipment = equipment;
    }

    public Car(int year_of_release, String model, String equipment) {
        this.year_of_release = year_of_release;
        this.model = model;
        this.equipment = equipment;
    }

    public Car() {
    }

    public int getCar_id() {
        return car_id;
    }

    public void setCar_id(int car_id) {
        this.car_id = car_id;
    }

    public int getYear_of_release() {
        return year_of_release;
    }

    public void setYear_of_release(int year_of_release) {
        this.year_of_release = year_of_release;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getEquipment() {
        return equipment;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }

    public int getPerson_id() {
        return person_id;
    }

    public void setPerson_id(int person_id) {
        this.person_id = person_id;
    }
}
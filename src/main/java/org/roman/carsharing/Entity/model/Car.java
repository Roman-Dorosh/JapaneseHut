package org.roman.carsharing.Entity.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Car")
public class Car {

    @Id
    @Column(name = "car_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int car_id;

    @Column(name = "year_of_release")
    private int year_of_release;

    @Column(name = "model")
    private String model;

    @Column(name = "equipment")
    private String equipment;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;


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

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
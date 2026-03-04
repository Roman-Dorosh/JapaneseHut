package org.roman.carsharing.model;

public class Person {

    private int person_id;
    private int age;

    private String name;
    private String surname;

    public Person(int person_id, int age, String name, String surname) {
        this.person_id = person_id;
        this.age = age;
        this.name = name;
        this.surname = surname;
    }

    public Person(int age, String name, String surname) {
        this.age = age;
        this.name = name;
        this.surname = surname;
    }

    public Person() {
    }

    public int getPerson_id() {
        return person_id;
    }

    public void setPerson_id(int person_id) {
        this.person_id = person_id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
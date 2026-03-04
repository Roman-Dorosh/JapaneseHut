package org.roman.carsharing.dao.person;

import org.roman.carsharing.model.Person;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonJDBC implements PersonDataBase {

    private static final String URL = "jdbc:postgresql://localhost:5432/Carsharing";
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";

    private static final Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Возвращает список людей из таблицы
    @Override
    public List<Person> listObject() {

        List<Person> personList = new ArrayList<>();

        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT * FROM Person");

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Person person = new Person();

                person.setPerson_id(resultSet.getInt("person_id"));
                person.setAge(resultSet.getInt("age"));
                person.setName(resultSet.getString("name"));
                person.setSurname(resultSet.getString("surname"));

                personList.add(person);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return personList;
    }

    // Возвращает определенного человека из таблицы
    @Override
    public Person getObject(int id) {

        Person person = null;

        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT * FROM Person WHERE person_id = ?");

            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();

            person = new Person();

            person.setPerson_id(resultSet.getInt("person_id"));
            person.setAge(resultSet.getInt("age"));
            person.setName(resultSet.getString("name"));
            person.setSurname(resultSet.getString("surname"));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return person;
    }

    // Добавляет нового человека в таблицу
    @Override
    public void addObject(Person person) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Person (age, name, surname) VALUES (?, ?, ?)");

            preparedStatement.setInt(1, person.getAge());
            preparedStatement.setString(2, person.getName());
            preparedStatement.setString(3, person.getSurname());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Обновляет данные о человеке
    @Override
    public void updateObject(Person person, int id) {
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("UPDATE Person SET age = ?, name = ?, surname = ? WHERE person_id = ?");

            preparedStatement.setInt(1, person.getAge());
            preparedStatement.setString(2, person.getName());
            preparedStatement.setString(3, person.getSurname());

            preparedStatement.setInt(4, id);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Удаляет человека из таблицы
    @Override
    public void deleteObject(int id) {
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("DELETE FROM Person WHERE person_id = ?");

            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
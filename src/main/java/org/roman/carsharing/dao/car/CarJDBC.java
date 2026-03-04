package org.roman.carsharing.dao.car;

import org.roman.carsharing.model.Car;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class CarJDBC implements CarDataBase {

    private static final String URL = "jdbc:postgresql://localhost:5432/Carsharing";
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";

    private static Connection connection;

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

    // Возвращает список всех машин из таблицы
    @Override
    public List<Car> listObject() {

        List<Car> carList = new ArrayList<>();

        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT car_id, year_of_release, model, equipment FROM Car");

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Car car = new Car();

                car.setCar_id(resultSet.getInt("car_id"));
                car.setYear_of_release(resultSet.getInt("year_of_release"));
                car.setModel(resultSet.getString("model"));
                car.setEquipment(resultSet.getString("equipment"));

                carList.add(car);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return carList;
    }

    // Возвращает конкретную машину из таблицы
    @Override
    public Car getObject(int id) {

        Car car = null;

        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT car_id, year_of_release, model, equipment FROM car WHERE car_id = ?");

            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();

            car = new Car();

            car.setCar_id(resultSet.getInt("car_id"));
            car.setYear_of_release(resultSet.getInt("year_of_release"));
            car.setModel(resultSet.getString("model"));
            car.setEquipment(resultSet.getString("equipment"));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return car;
    }

    // Добавляет новую машину в таблицу
    @Override
    public void addObject(Car car) {
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("INSERT INTO Car (year_of_release, model , equipment) VALUES (?, ?, ?)");

            preparedStatement.setInt(1, car.getYear_of_release());
            preparedStatement.setString(2, car.getModel());
            preparedStatement.setString(3, car.getEquipment());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Обновляет конкретную машину
    @Override
    public void updateObject(Car car, int id) {
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("UPDATE Car SET year_of_release = ?, model = ?, equipment = ? WHERE car_id = ?");

            preparedStatement.setInt(1, car.getYear_of_release());
            preparedStatement.setString(2, car.getModel());
            preparedStatement.setString(3, car.getEquipment());

            preparedStatement.setInt(4, id);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Удаляет машину
    @Override
    public void deleteObject(int id) {
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("DELETE FROM Car WHERE car_id = ?");

            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    // Показывает только некупленные авто
    @Override
    public List<Car> listVacantObject() {

        List<Car> carList = new ArrayList<>();

        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT * FROM Car WHERE person_id IS NULL");

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Car car = new Car();

                car.setCar_id(resultSet.getInt("car_id"));
                car.setPerson_id(resultSet.getInt("person_id"));
                car.setYear_of_release(resultSet.getInt("year_of_release"));
                car.setModel(resultSet.getString("model"));
                car.setEquipment(resultSet.getString("equipment"));

                carList.add(car);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return carList;
    }


    // Показывавет список купленных машин пользователя
    @Override
    public List<Car> listPurchasedObjects(int id) {

        List<Car> carList = new ArrayList<>();

        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT * FROM Car WHERE person_id = ?");

            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Car car = new Car();

                car.setCar_id(resultSet.getInt("car_id"));
                car.setPerson_id(resultSet.getInt("person_id"));
                car.setYear_of_release(resultSet.getInt("year_of_release"));
                car.setModel(resultSet.getString("model"));
                car.setEquipment(resultSet.getString("equipment"));

                carList.add(car);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return carList;
    }

    // Купить машину
    @Override
    public void buyObject(int person_id, int car_id) {
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("UPDATE Car SET person_id = ? WHERE car_id = ?");

            preparedStatement.setInt(1, person_id);
            preparedStatement.setInt(2, car_id);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Продать машину
    @Override
    public void sellObject(int id) {
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("UPDATE Car SET person_id = null WHERE car_id = ?");

            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
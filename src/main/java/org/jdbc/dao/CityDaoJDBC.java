package org.jdbc.dao;

import org.jdbc.db_connection.DBConnectionException;
import org.jdbc.db_connection.MySQLConnection;
import org.jdbc.model.City;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CityDaoJDBC implements CityDao {

    @Override
    public City findById(int id) {
        String query = "select * from city where id = ?";
        City city = new City();
        try (Connection connection = MySQLConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery();) {
                if (resultSet.next()) {
                    city.setId(resultSet.getInt(1));
                    city.setName(resultSet.getString(2));
                    city.setCountryCode(resultSet.getString(3));
                    city.setDistrict(resultSet.getString(4));
                    city.setPopulation(resultSet.getInt(5));
                }
            }
        } catch (DBConnectionException | SQLException e) {
            System.out.println(e.getMessage());
        }
        return city;
    }

    @Override
    public List<City> findByCode(String code) {
        List<City> cities = new ArrayList<>();
        String query = "select * from city where countryCode = ?";
        try (Connection connection = MySQLConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            preparedStatement.setString(1, code);
            try (ResultSet resultSet = preparedStatement.executeQuery();) {
                while (resultSet.next()) {
                    cities.add(new City(
                            resultSet.getInt(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getInt(5)
                    ));
                }
            }
        } catch (DBConnectionException | SQLException e) {
            System.out.println(e.getMessage());
        }
        return cities;
    }

    @Override
    public List<City> findByName(String name) {
        List<City> cities = new ArrayList<>();
        String query = "select * from city where name = ?";
        try (Connection connection = MySQLConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            preparedStatement.setString(1, name);
            try (ResultSet resultSet = preparedStatement.executeQuery();) {
                while (resultSet.next()) {
                    cities.add(new City(
                            resultSet.getInt(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getInt(5)
                    ));
                }
            }
        } catch (DBConnectionException | SQLException e) {
            System.out.println(e.getMessage());
        }
        return cities;
    }

    @Override
    public List<City> findAll() {
        List<City> cities = new ArrayList<>();
        String query = "select * from city";
        try (Connection connection = MySQLConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery();
        ) {
            while (resultSet.next()) {
                cities.add(new City(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getInt(5)
                ));
            }
        } catch (DBConnectionException | SQLException e) {
            System.out.println(e.getMessage());
        }
        return cities;
    }

    @Override
    public City add(City city) {
        String query = "insert into city(name, CountryCode, district, population) values(?, ?, ?, ?)";
        try (Connection connection = MySQLConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        ) {
            preparedStatement.setString(1, city.getName());
            preparedStatement.setString(2, city.getCountryCode());
            preparedStatement.setString(3, city.getDistrict());
            preparedStatement.setInt(4, city.getPopulation());

            int result = preparedStatement.executeUpdate();
            if(result==1) System.out.println("city is created");
            try(ResultSet resultSet = preparedStatement.getGeneratedKeys();){
                int keyId = 0;
                while (resultSet.next()) {
                    keyId = resultSet.getInt(1);
                }
                city.setId(keyId);
            }
        }
        catch (DBConnectionException | SQLException e) {
            System.out.println(e.getMessage());
        }

        return city;
    }

    @Override
    public City update(City city) {
        String query = "update into city(name, CountryCode, district, population) values(?, ?, ?, ?)";
        try (Connection connection = MySQLConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        ) {
            preparedStatement.setString(1, city.getName());
            preparedStatement.setString(2, city.getCountryCode());
            preparedStatement.setString(3, city.getDistrict());
            preparedStatement.setInt(4, city.getPopulation());

            int result = preparedStatement.executeUpdate();
            System.out.println((result == 1) ? "Updated successfully" : "Not updated!");
            try (ResultSet resultSet = preparedStatement.getGeneratedKeys();) {

                int keyId = 0;
                while (resultSet.next()) {
                    keyId = resultSet.getInt(1);
                }
                city.setId(keyId);
            }
        }
        catch (DBConnectionException | SQLException e) {
            System.out.println(e.getMessage());
        }

        return city;
    }

    @Override
    public int delete(City city) {
        String query = "delete from city where id = ?";
        try (Connection connection = MySQLConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        ) {
            preparedStatement.setInt(1, city.getId());
            int result = preparedStatement.executeUpdate();
            if(result == 1) System.out.println("Deleted successfully") ;
            else System.out.println("could not delete");
        }
        catch (DBConnectionException | SQLException e) {
            System.out.println(e.getMessage());
        }
        return 1;
    }
}

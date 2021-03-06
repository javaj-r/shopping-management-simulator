package com.group4.repositories.impl;

import com.group4.connection.PostgresConnection;
import com.group4.entities.Customer;
import com.group4.repositories.CustomerRepository;
import com.group4.repositories.base.CrudRepositoryImpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author javid
 * Created on 1/31/2022
 */
public class CustomerRepositoryImpl extends CrudRepositoryImpl<Customer, Integer> implements CustomerRepository {

    public CustomerRepositoryImpl(PostgresConnection postgresConnection) {
        super(postgresConnection);
    }

    @Override
    public List<Customer> findAll() {
        Connection connection = postgresConnection.getConnection();
        List<Customer> customers = new ArrayList<>();
        String query = "SELECT id, username, password, firstname, lastname, national_code, email, phone_number"
                + "\n FROM customer;";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Customer customer = new Customer();
                customer.setId(resultSet.getInt("id"));
                customer.setUsername(resultSet.getString("username"));
                customer.setPassword(resultSet.getString("password"));
                customer.setFirstName(resultSet.getString("firstname"));
                customer.setLastName(resultSet.getString("lastname"));
                customer.setEmail(resultSet.getString("email"));
                long temp = resultSet.getLong("national_code");
                customer.setNationalCode(resultSet.wasNull() ? null : temp);
                temp = resultSet.getLong("phone_number");
                customer.setPhoneNumber(resultSet.wasNull() ? null : temp);
                customers.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customers;
    }

    @Override
    public Customer findById(Integer id) {
        Connection connection = postgresConnection.getConnection();
        String query = "SELECT id, username, password, firstname, lastname, national_code, email, phone_number"
                + "\n FROM customer"
                + "\n WHERE id = ?;";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Customer customer = new Customer();
                customer.setId(resultSet.getInt("id"));
                customer.setUsername(resultSet.getString("username"));
                customer.setPassword(resultSet.getString("password"));
                customer.setFirstName(resultSet.getString("firstname"));
                customer.setLastName(resultSet.getString("lastname"));
                customer.setEmail(resultSet.getString("email"));
                long temp = resultSet.getLong("national_code");
                customer.setNationalCode(resultSet.wasNull() ? null : temp);
                temp = resultSet.getLong("phone_number");
                customer.setPhoneNumber(resultSet.wasNull() ? null : temp);
                return customer;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Integer save(Customer entity) {
        Connection connection = postgresConnection.getConnection();
        String query = "INSERT INTO customer (username, password, firstname, lastname, national_code, email, phone_number)"
                + "\n VALUES (?, ?, ?, ?, ?, ?, ?);";
        try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, entity.getUsername());
            statement.setString(2, entity.getPassword());
            statement.setString(3, entity.getFirstName());
            statement.setString(4, entity.getLastName());
            if (entity.getNationalCode() == null) {
                statement.setNull(5, Types.BIGINT);
            } else {
                statement.setLong(5, entity.getNationalCode());
            }
            statement.setString(6, entity.getEmail());
            if (entity.getPhoneNumber() == null) {
                statement.setNull(7, Types.BIGINT);
            } else {
                statement.setLong(7, entity.getPhoneNumber());
            }
            statement.execute();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void update(Customer entity) {
        Connection connection = postgresConnection.getConnection();
        String query = "UPDATE customer SET"
                + "\n     username = ?,"
                + "\n     password = ?,"
                + "\n     firstname = ?,"
                + "\n     lastname = ?,"
                + "\n     national_code = ?,"
                + "\n     email = ?,"
                + "\n     phone_number = ?"
                + "\n WHERE id = ?;";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, entity.getUsername());
            statement.setString(2, entity.getPassword());
            statement.setString(3, entity.getFirstName());
            statement.setString(4, entity.getLastName());
            if (entity.getNationalCode() == null) {
                statement.setNull(5, Types.BIGINT);
            } else {
                statement.setLong(5, entity.getNationalCode());
            }
            statement.setString(6, entity.getEmail());
            if (entity.getPhoneNumber() == null) {
                statement.setNull(7, Types.BIGINT);
            } else {
                statement.setLong(7, entity.getPhoneNumber());
            }
            statement.setInt(8, entity.getId());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(Integer id) {
        Connection connection = postgresConnection.getConnection();
        String query = "DELETE  FROM customer"
                + "\n WHERE id = ?;";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Customer findByUsernameAndPassword(Customer customer) {
        Connection connection = postgresConnection.getConnection();
        String query = "SELECT id, username, password, firstname, lastname, national_code, email, phone_number"
                + "\n FROM customer"
                + "\n WHERE username = ?"
                + "\n     AND password = ?;";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, customer.getUsername());
            statement.setString(2, customer.getPassword());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Customer fetchedCustomer = new Customer();
                fetchedCustomer.setId(resultSet.getInt("id"));
                fetchedCustomer.setUsername(resultSet.getString("username"));
                fetchedCustomer.setPassword(resultSet.getString("password"));
                fetchedCustomer.setFirstName(resultSet.getString("firstname"));
                fetchedCustomer.setLastName(resultSet.getString("lastname"));
                fetchedCustomer.setEmail(resultSet.getString("email"));
                long temp = resultSet.getLong("national_code");
                fetchedCustomer.setNationalCode(resultSet.wasNull() ? null : temp);
                temp = resultSet.getLong("phone_number");
                fetchedCustomer.setPhoneNumber(resultSet.wasNull() ? null : temp);
                return fetchedCustomer;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}

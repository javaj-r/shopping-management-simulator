package com.group4.repositories.impl;

import com.group4.connection.PostgresConnection;
import com.group4.entities.Admin;
import com.group4.repositories.AdminRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author javid
 * Created on 1/31/2022
 */
public class AdminRepositoryImpl implements AdminRepository {

    private final PostgresConnection postgresConnection;

    public AdminRepositoryImpl(PostgresConnection postgresConnection) {
        this.postgresConnection = postgresConnection;
    }

    @Override
    public Admin findByUsernameAndPassword(Admin admin) {
        Connection connection = postgresConnection.getConnection();
        String query = """
                SELECT id, username, password FROM admin
                WHERE username = ?
                AND password = ?;
                """;
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, admin.getUsername());
            statement.setString(2, admin.getPassword());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Admin admin1 = new Admin();
                admin1.setId(resultSet.getInt("id"));
                admin1.setUsername(resultSet.getString("username"));
                admin1.setPassword(resultSet.getString("password"));
                return admin1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Admin> findAll() {
        Connection connection = postgresConnection.getConnection();
        List<Admin> admins = new ArrayList<>();
        String query = """
                SELECT id, username, password FROM admin;
                """;
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Admin admin = new Admin();
                admin.setId(resultSet.getInt("id"));
                admin.setUsername(resultSet.getString("username"));
                admin.setPassword(resultSet.getString("password"));
                admins.add(admin);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return admins;
    }

    @Override
    public Admin findById(Integer id) {
        Connection connection = postgresConnection.getConnection();
        String query = """
                SELECT id, username, password FROM admin
                WHERE id = ?;
                """;
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Admin admin1 = new Admin();
                admin1.setId(resultSet.getInt("id"));
                admin1.setUsername(resultSet.getString("username"));
                admin1.setPassword(resultSet.getString("password"));
                return admin1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Integer save(Admin entity) {
        Connection connection = postgresConnection.getConnection();
        String query = """
                INSERT INTO admin(username, password)
                SELECT ?, ?
                WHERE not exists(
                        SELECT * FROM admin WHERE username = ?
                    );
                """;
        try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, entity.getUsername());
            statement.setString(2, entity.getPassword());
            statement.setString(3, entity.getUsername());
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
    public void update(Admin entity) {
        Connection connection = postgresConnection.getConnection();
        String query = """
                UPDATE admin SET
                    username = ?,
                    password = ?
                WHERE id = ?;
                """;
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, entity.getUsername());
            statement.setString(2, entity.getPassword());
            statement.setInt(3, entity.getId());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(Integer id) {
        Connection connection = postgresConnection.getConnection();
        String query = """
                DELETE FROM admin
                WHERE id = ?;
                """;
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

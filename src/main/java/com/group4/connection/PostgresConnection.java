package com.group4.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author javid
 * Created on 1/30/2022
 */
public class PostgresConnection {

    private Connection connection;

    private PostgresConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            setConnection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static class Singleton {
        private static final PostgresConnection INSTANCE = new PostgresConnection();
    }

    public static PostgresConnection getInstance() {
        return Singleton.INSTANCE;
    }

    public Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                setConnection();
            }
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void setConnection() {
        String url = "jdbc:postgresql://localhost:5432/shopping";
        String user = "postgres";
        String password = "123";
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

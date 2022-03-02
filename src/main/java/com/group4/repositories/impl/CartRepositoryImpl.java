package com.group4.repositories.impl;

import com.group4.connection.PostgresConnection;
import com.group4.entities.Cart;
import com.group4.entities.Customer;
import com.group4.repositories.CartRepository;
import com.group4.repositories.base.CrudRepositoryImpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author javid
 * Created on 1/31/2022
 */
public class CartRepositoryImpl extends CrudRepositoryImpl<Cart, Integer> implements CartRepository {

    public CartRepositoryImpl(PostgresConnection postgresConnection) {
        super(postgresConnection);
    }

    @Override
    public List<Cart> findAll() {
        Connection connection = postgresConnection.getConnection();
        List<Cart> carts = new ArrayList<>();
        String query = "SELECT id, address, phone_number, customer_id, done FROM cart;";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Customer customer = new Customer();
                customer.setId(resultSet.getInt("customer_id"));
                Cart cart = new Cart();
                cart.setCostumer(customer);
                cart.setId(resultSet.getInt("id"));
                cart.setAddress(resultSet.getString("address"));
                cart.setDone(resultSet.getBoolean("done"));
                long phoneNumber = resultSet.getLong("phone_number");
                cart.setPhoneNumber(resultSet.wasNull() ? null : phoneNumber);

                carts.add(cart);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return carts;
    }

    @Override
    public Cart findById(Integer id) {
        Connection connection = postgresConnection.getConnection();
        String query =
                "SELECT id, address, phone_number, customer_id, done FROM cart"
                        + "\n WHERE id = ?;";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Customer customer = new Customer();
                customer.setId(resultSet.getInt("customer_id"));
                Cart cart = new Cart();
                cart.setCostumer(customer);
                cart.setId(resultSet.getInt("id"));
                cart.setAddress(resultSet.getString("address"));
                cart.setDone(resultSet.getBoolean("done"));
                long phoneNumber = resultSet.getLong("phone_number");
                cart.setPhoneNumber(resultSet.wasNull() ? null : phoneNumber);
                return cart;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Integer save(Cart entity) {
        Connection connection = postgresConnection.getConnection();
        String query = "INSERT INTO cart (address, phone_number, customer_id, done)"
                + "\n VALUES (?, ?, ?, ?);";
        try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, entity.getAddress());
            if (entity.getPhoneNumber() == null) {
                statement.setNull(2, Types.BIGINT);
            } else {
                statement.setLong(2, entity.getPhoneNumber());
            }
            statement.setInt(3, entity.getCostumer().getId());
            statement.setBoolean(4, entity.isDone());
            statement.execute();
            ResultSet resultSet = statement.getGeneratedKeys();

            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String query1 = "WITH rows AS ("
                        + "\n     UPDATE product SET"
                        + "\n     stock = stock - 1"
                        + "\n     WHERE id = ?"
                        + "\n     AND stock > 0"
                        + "\n     RETURNING 1"
                        + "\n )"
                        + "\n INSERT INTO cart_product(cart_id, product_id)"
                        + "\n SELECT ?, ? WHERE (SELECT count(*) FROM rows) > 0;";
                 try (PreparedStatement statement1 = connection.prepareStatement(query1)) {
                    entity.getProducts().forEach(product -> {
                        try {
                            statement1.clearParameters();
                            statement1.setInt(1, product.getId());
                            statement1.setInt(2, id);
                            statement1.setInt(3, product.getId());
                            statement1.addBatch();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    });

                    statement1.executeBatch();
                }
                return id;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void update(Cart entity) {
        Connection connection = postgresConnection.getConnection();
        String query = "UPDATE cart SET"
                + "\n address = ?,"
                + "\n phone_number = ?,"
                + "\n customer_id = ?,"
                + "\n done = ?"
                + "\n WHERE id = ?;";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, entity.getAddress());
            if (entity.getPhoneNumber() == null) {
                statement.setNull(2, Types.BIGINT);
            } else {
                statement.setLong(2, entity.getPhoneNumber());
            }
            statement.setInt(3, entity.getCostumer().getId());
            statement.setBoolean(4, entity.isDone());
            statement.setInt(5, entity.getId());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(Integer id) {
        Connection connection = postgresConnection.getConnection();
        String query = "DELETE FROM cart"
                + "\n WHERE id = ?;";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

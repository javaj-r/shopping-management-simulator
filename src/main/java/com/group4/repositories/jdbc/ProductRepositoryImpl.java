package com.group4.repositories.jdbc;

import com.group4.connection.PostgresConnection;
import com.group4.entities.Category;
import com.group4.entities.Product;
import com.group4.repositories.ProductRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author javid
 * Created on 1/31/2022
 */
public class ProductRepositoryImpl implements ProductRepository {

    private final PostgresConnection postgresConnection;

    public ProductRepositoryImpl(PostgresConnection postgresConnection) {
        this.postgresConnection = postgresConnection;
    }

    @Override
    public List<Product> findAll() {
        Connection connection = postgresConnection.getConnection();
        List<Product> products = new ArrayList<>();
        String query = """
                SELECT id, price, category_id, name, stock
                FROM product;
                """;
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Category category = new Category();
                int temp = resultSet.getInt("category_id");
                category.setId(resultSet.wasNull() ? null : temp);
                Product product = new Product();
                product.setId(resultSet.getInt("id"));
                product.setPrice(resultSet.getInt("price"));
                product.setCategory(category);
                product.setName(resultSet.getString("name"));
                product.setStock(resultSet.getInt("stock"));
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }

    @Override
    public Product findById(Integer id) {
        Connection connection = postgresConnection.getConnection();
        String query = """
                SELECT id, price, category_id, name, stock
                FROM product
                WHERE id = ?;
                """;
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Category category = new Category();
                int temp = resultSet.getInt("category_id");
                category.setId(resultSet.wasNull() ? null : temp);
                Product product = new Product();
                product.setId(resultSet.getInt("id"));
                product.setPrice(resultSet.getInt("price"));
                product.setCategory(category);
                product.setName(resultSet.getString("name"));
                product.setStock(resultSet.getInt("stock"));
                return product;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Integer save(Product entity) {
        Connection connection = postgresConnection.getConnection();
        String query = """
                INSERT INTO product (price, category_id, name, stock)
                VALUES (?, ?, ?, ?);
                """;
        try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, entity.getPrice());
            if (entity.getCategory() == null || entity.getCategory().isNew()) {
                statement.setNull(2, Types.INTEGER);
            } else {
                statement.setInt(2, entity.getCategory().getId());
            }
            statement.setString(3, entity.getName());
            statement.setInt(4, entity.getStock());
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
    public void update(Product entity) {

        Connection connection = postgresConnection.getConnection();
        String query = """
                UPDATE product SET
                price = ?,
                category_id = ?,
                name = ?,
                stock = ?
                WHERE id = ?;
                """;
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, entity.getPrice());
            if (entity.getCategory() == null || entity.getCategory().isNew()) {
                statement.setNull(2, Types.INTEGER);
            } else {
                statement.setInt(2, entity.getCategory().getId());
            }
            statement.setString(3, entity.getName());
            statement.setInt(4, entity.getStock());
            statement.setInt(5, entity.getId());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(Integer id) {
        Connection connection = postgresConnection.getConnection();
        String query = """
                DELETE  FROM product
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

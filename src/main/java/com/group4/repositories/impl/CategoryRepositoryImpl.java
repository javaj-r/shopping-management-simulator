package com.group4.repositories.impl;

import com.group4.connection.PostgresConnection;
import com.group4.entities.Category;
import com.group4.repositories.CategoryRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author javid
 * Created on 1/31/2022
 */
public class CategoryRepositoryImpl implements CategoryRepository {

    private final PostgresConnection postgresConnection;

    public CategoryRepositoryImpl(PostgresConnection postgresConnection) {
        this.postgresConnection = postgresConnection;
    }

    @Override
    public List<Category> findAllChildes(Category category) {
        Connection connection = postgresConnection.getConnection();
        List<Category> categories = new ArrayList<>();
        String query = """ 
                SELECT id, name, parent_id
                FROM category
                WHERE parent_id = ?;
                """;
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, category.getId());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Category childCategory = new Category();
                childCategory.setId(resultSet.getInt("id"));
                childCategory.setName(resultSet.getString("name"));
                childCategory.setParentCategory(category);
                categories.add(childCategory);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return categories;
    }

    @Override
    public List<Category> findAll() {
        Connection connection = postgresConnection.getConnection();
        List<Category> categories = new ArrayList<>();
        String query = """ 
                SELECT id, name, parent_id
                FROM category;
                """;
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Category parentCategory = new Category();
                int parentId = resultSet.getInt("parent_id");
                parentCategory.setId(resultSet.wasNull() ? null : parentId);
                Category category = new Category();
                category.setId(resultSet.getInt("id"));
                category.setName(resultSet.getString("name"));
                category.setParentCategory(parentCategory);
                categories.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return categories;
    }

    @Override
    public Category findById(Integer id) {
        Connection connection = postgresConnection.getConnection();
        String query = """ 
                SELECT id, name, parent_id
                FROM category
                WHERE id = ?;
                """;
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Category parentCategory = new Category();
                int parentId = resultSet.getInt("parent_id");
                parentCategory.setId(resultSet.wasNull() ? null : parentId);
                Category category = new Category();
                category.setId(resultSet.getInt("id"));
                category.setName(resultSet.getString("name"));
                category.setParentCategory(parentCategory);
                return category;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Integer save(Category entity) {
        Connection connection = postgresConnection.getConnection();
        String query = """
                INSERT INTO category (name, parent_id)
                VALUES (?, ?);
                """;
        try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, entity.getName());
            if (entity.getParentCategory() == null || entity.getParentCategory().isNew()) {
                statement.setNull(2, Types.INTEGER);
            } else {
                statement.setInt(2, entity.getParentCategory().getId());
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
    public void update(Category entity) {
        Connection connection = postgresConnection.getConnection();
        String query = """
                UPDATE category SET
                name = ?,
                parent_id = ?
                WHERE id = ?;
                """;
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, entity.getName());
            if (entity.getParentCategory() == null || entity.getParentCategory().isNew()) {
                statement.setNull(2, Types.INTEGER);
            } else {
                statement.setInt(2, entity.getParentCategory().getId());
            }
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
                DELETE  FROM category
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

package com.example.manager;

import com.example.db.DBConnectionProvider;
import com.example.model.User;

import java.sql.*;

public class UserManager {
    private static final Connection CONNECTION = DBConnectionProvider.getInstance().getConnection();

    public void addUser(User user) throws SQLException {
        String query = "INSERT INTO user (name,email,password) VALUES (?,?,?)";
        try (PreparedStatement preparedStatement = CONNECTION.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                user.setId(generatedKeys.getInt(1));
            } else {
                throw new SQLException();
            }
        }
    }

    public static User getUserByMail(String email) {
        String query = "SELECT * FROM user WHERE email = ?";
        try (PreparedStatement preparedStatement = CONNECTION.prepareStatement(query)) {
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String password = resultSet.getString("password");
                return User.builder().name(name).id(id).email(email).password(password).build();
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    public static User getUserById(int id) {
        String query = "SELECT * FROM user WHERE id = ?";
        try (PreparedStatement preparedStatement = CONNECTION.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String email = resultSet.getString("email");
                String name = resultSet.getString("name");
                String password = resultSet.getString("password");
                return User.builder().name(name).id(id).email(email).password(password).build();
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }
}

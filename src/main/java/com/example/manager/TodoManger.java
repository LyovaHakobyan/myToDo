package com.example.manager;

import com.example.db.DBConnectionProvider;
import com.example.model.Status;
import com.example.model.Todo;
import com.example.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TodoManger {
    private static final Connection CONNECTION = DBConnectionProvider.getInstance().getConnection();

    public void addTodo(Todo todo) throws SQLException {
        String query = "INSERT INTO todos (title,created_date,finish_date,user_id,status) VALUES (?,?,?,?,?)";
        try (PreparedStatement preparedStatement = CONNECTION.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, todo.getTitle());
            preparedStatement.setDate(2, new Date(todo.getCreatedDate().getTime()));
            if (todo.getFinishDate() != null) {
                preparedStatement.setDate(3, new Date(todo.getFinishDate().getTime()));
            } else {
                preparedStatement.setDate(3, null);
            }
            preparedStatement.setInt(4, todo.getUser().getId());
            preparedStatement.setString(5, todo.getStatus().name());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                todo.setId(generatedKeys.getInt(1));
            } else {
                throw new SQLException();
            }
        }
    }

    public void updateTodo(Todo todo) throws SQLException {
        String query = "UPDATE todos SET title = ?, created_date = ?, finish_date = ?, status = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = CONNECTION.prepareStatement(query)) {
            preparedStatement.setString(1, todo.getTitle());
            preparedStatement.setDate(2, new Date(todo.getCreatedDate().getTime()));
            if (todo.getFinishDate() != null) {
                preparedStatement.setDate(3, new Date(todo.getFinishDate().getTime()));
            } else {
                preparedStatement.setDate(3, null);
            }
            preparedStatement.setString(4, todo.getStatus().name());
            preparedStatement.setInt(5, todo.getId());
            preparedStatement.executeUpdate();
        }
    }

    public List<Todo> getAllUserTodos(int userId) {
        List<Todo> todos = new ArrayList<>();
        User userById = UserManager.getUserById(userId);
        String query = "SELECT * FROM todos WHERE user_id = " + userId;
        try (Statement statement = CONNECTION.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                java.util.Date createdDate = resultSet.getDate("created_date");
                java.util.Date finishDate = resultSet.getDate("finish_date");
                Status status = Status.valueOf(resultSet.getString("status"));
                todos.add(Todo.builder().id(id).title(title).createdDate(createdDate).finishDate(finishDate).user(userById).status(status).build());
            }
            return todos;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Todo getTodoById(int id) {
        String query = "SELECT * FROM todos WHERE id = " + id;
        try (Statement statement = CONNECTION.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                String title = resultSet.getString("title");
                java.util.Date createdDate = resultSet.getDate("created_date");
                java.util.Date finishDate = resultSet.getDate("finish_date");
                Status status = Status.valueOf(resultSet.getString("status"));
                int userId = resultSet.getInt("user_id");
                User userById = UserManager.getUserById(userId);
                return Todo.builder().id(id).title(title).createdDate(createdDate).finishDate(finishDate).user(userById).status(status).build();
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteTodoById(int id) throws SQLException {
        String query = "DELETE FROM todos WHERE id = " + id;
        try (Statement statement = CONNECTION.createStatement()) {
            statement.executeUpdate(query);
        }
    }
}

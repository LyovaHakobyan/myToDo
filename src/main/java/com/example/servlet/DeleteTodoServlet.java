package com.example.servlet;

import com.example.manager.TodoManger;
import com.example.model.Todo;
import com.example.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/deleteTodo")
public class DeleteTodoServlet extends HttpServlet {
    private final TodoManger todoManger = new TodoManger();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id;
        try {
            id = Integer.parseInt(req.getParameter("id"));
        } catch (NumberFormatException e) {
            resp.sendRedirect("/todo");
            return;
        }
        User user = (User) req.getSession().getAttribute("user");
        Todo todoById = TodoManger.getTodoById(id);
        if (todoById != null && todoById.getUser().getId() != user.getId()) {
            resp.sendRedirect("/todo");
        } else {
            try {
                todoManger.deleteTodoById(id);
                resp.sendRedirect("/todo");
            } catch (SQLException e) {
                resp.sendRedirect("/todo");
            }
        }
    }
}

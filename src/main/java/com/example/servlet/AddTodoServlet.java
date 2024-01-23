package com.example.servlet;

import com.example.manager.TodoManger;
import com.example.model.Status;
import com.example.model.Todo;
import com.example.model.User;
import com.example.util.DateUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

@WebServlet(urlPatterns = "/addTodo")
public class AddTodoServlet extends HttpServlet {
    private final TodoManger todoManger = new TodoManger();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        Date createdDate = new Date();
        String finishDate = req.getParameter("finishDate");
        User user = (User) req.getSession().getAttribute("user");
        try {
            todoManger.addTodo(Todo.builder().title(title).createdDate(createdDate).finishDate(DateUtil.stringToDate(finishDate)).user(user).status(Status.NEW).build());
            resp.sendRedirect("/todo");
        } catch (SQLException e) {
            resp.sendRedirect("/todo");
        }
    }
}

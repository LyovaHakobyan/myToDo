package com.example.servlet;

import com.example.manager.TodoManger;
import com.example.model.Status;
import com.example.model.Todo;
import com.example.model.User;
import com.example.util.DateUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/editTodo")
public class EditTodoServlet extends HttpServlet {
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
            req.setAttribute("myTodo", todoById);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/editMyTodo.jsp");
            requestDispatcher.forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        String finishDate = req.getParameter("finishDate");
        String status = req.getParameter("status");
        int id;
        try {
            id = Integer.parseInt(req.getParameter("id"));
        } catch (NumberFormatException e) {
            resp.sendRedirect("/todo");
            return;
        }
        String createdDate = req.getParameter("createdDate");
        User user = (User) req.getSession().getAttribute("user");
        try {
            todoManger.updateTodo(Todo.builder().id(id).title(title).createdDate(DateUtil.stringToDate(createdDate)).finishDate(DateUtil.stringToDate(finishDate)).user(user).status(Status.valueOf(status)).build());
            resp.sendRedirect("/todo");
        } catch (SQLException e) {
            resp.sendRedirect("/todo");
        }
    }
}

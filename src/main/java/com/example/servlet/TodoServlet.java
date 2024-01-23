package com.example.servlet;

import com.example.manager.TodoManger;
import com.example.model.Todo;
import com.example.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/todo")
public class TodoServlet extends HttpServlet {
    private final TodoManger todoManger = new TodoManger();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        List<Todo> allUserTodos = todoManger.getAllUserTodos(user.getId());
        req.setAttribute("allUserTodos", allUserTodos);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/todo.jsp");
        requestDispatcher.forward(req, resp);
    }
}

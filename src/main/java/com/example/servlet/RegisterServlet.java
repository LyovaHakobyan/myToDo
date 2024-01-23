package com.example.servlet;

import com.example.manager.UserManager;
import com.example.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {
    private final UserManager userManager = new UserManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/register.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        User userByMail = UserManager.getUserByMail(email);
        if (userByMail == null) {
            try {
                userManager.addUser(User.builder().name(name).email(email).password(password).build());
                resp.sendRedirect("/login");
            } catch (SQLException e) {
                resp.sendRedirect("/register");
            }
        } else {
            req.getSession().setAttribute("msg", "User By This Email Already Exists !");
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/register.jsp");
            requestDispatcher.forward(req, resp);
        }
    }
}

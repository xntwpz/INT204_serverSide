package dev.bestzige.simplerbacjavaservlet.servlets;

import dev.bestzige.simplerbacjavaservlet.entities.User;
import dev.bestzige.simplerbacjavaservlet.repositories.AuthRepository;
import dev.bestzige.simplerbacjavaservlet.repositories.UserRepository;
import dev.bestzige.simplerbacjavaservlet.utils.StringUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "AuthRegisterServlet", value = "/auth/register")
public class AuthRegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/auth/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String role = StringUtils.isNullOrEmpty(req.getParameter("role")) ? "USER" : req.getParameter("role");
        if (StringUtils.isNullOrEmpty(username) && StringUtils.isNullOrEmpty(password)) {
            req.setAttribute("error", "Username and password cannot be empty");
            doGet(req, resp);
            return;
        }

        if(!role.equals("ADMIN") && !role.equals("USER")) {
            req.setAttribute("error", "Invalid role");
            doGet(req, resp);
            return;
        }

        UserRepository userRepository = new UserRepository();
        User user = userRepository.findByUsername(username);
        if (user != null) {
            req.setAttribute("error", "Username already exists");
            doGet(req, resp);
            return;
        }

        AuthRepository authRepository = new AuthRepository();
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setRole(role);
        boolean success = authRepository.register(newUser, password);
        if (!success) {
            req.setAttribute("error", "Failed to register");
            doGet(req, resp);
            return;
        }

        HttpSession session = req.getSession();
        session.setAttribute("user", newUser);
        resp.sendRedirect(getServletContext().getContextPath() + "/");
    }
}

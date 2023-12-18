package dev.bestzige.simplerbacjavaservlet.servlets;

import dev.bestzige.simplerbacjavaservlet.entities.User;
import dev.bestzige.simplerbacjavaservlet.repositories.AuthRepository;
import dev.bestzige.simplerbacjavaservlet.utils.StringUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.eclipse.tags.shaded.org.apache.xpath.operations.String;

import java.io.IOException;

@WebServlet(name = "AuthLoginServlet", value = "/auth/login")
public class AuthLoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/auth/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if (StringUtils.isNullOrEmpty(username) && StringUtils.isNullOrEmpty(password)) {
            req.setAttribute("error", "Username and password cannot be empty");
            doGet(req, resp);
            return;
        }

        AuthRepository authRepository = new AuthRepository();
        User user = authRepository.login(username, password);

        if (user == null) {
            req.setAttribute("error", "Invalid username or password");
            doGet(req, resp);
            return;
        }

        HttpSession session = req.getSession();
        session.setAttribute("user", user);
        resp.sendRedirect(getServletContext().getContextPath() + "/");
    }
}

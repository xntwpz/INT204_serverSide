package dev.bestzige.simplerbacjavaservlet.servlets;

import com.mysql.cj.util.StringUtils;
import dev.bestzige.simplerbacjavaservlet.entities.User;
import dev.bestzige.simplerbacjavaservlet.repositories.UserRepository;
import dev.bestzige.simplerbacjavaservlet.utils.PasswordUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "UserManagerServlet", value = "/admin/users")
public class UserManagerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int page = StringUtils.isNullOrEmpty(req.getParameter("page")) ? 1 : Integer.parseInt(req.getParameter("page"));
        int limit = StringUtils.isNullOrEmpty(req.getParameter("limit")) ? 10 : Integer.parseInt(req.getParameter("limit"));
        String search = StringUtils.isNullOrEmpty(req.getParameter("search")) ? null : req.getParameter("search");

        int userId = StringUtils.isNullOrEmpty(req.getParameter("id")) ? 0 : Integer.parseInt(req.getParameter("id"));

        UserRepository userRepository = new UserRepository();
        if (userId != 0) {
            User user = userRepository.findById(userId);
            if (user != null) {
                req.setAttribute("user", user);
            }

            req.getRequestDispatcher("/user/user-form.jsp").forward(req, resp);
        }

        List<User> users = userRepository.findAll(page, limit, search);
        int totalUsers = userRepository.countAll(search);
        int totalPages = (int) Math.ceil((double) totalUsers / limit);
        req.setAttribute("page", page);
        req.setAttribute("limit", limit);
        req.setAttribute("search", search);
        req.setAttribute("totalPages", totalPages);
        req.setAttribute("users", users);

        req.getRequestDispatcher("/user/user-list.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = StringUtils.isNullOrEmpty(req.getParameter("action")) ? null : req.getParameter("action");

        if(action.equals("search")) {
            int page = StringUtils.isNullOrEmpty(req.getParameter("page")) ? 1 : Integer.parseInt(req.getParameter("page"));
            int limit = StringUtils.isNullOrEmpty(req.getParameter("limit")) ? 10 : Integer.parseInt(req.getParameter("limit"));
            String search = StringUtils.isNullOrEmpty(req.getParameter("search")) ? "" : req.getParameter("search");

            resp.sendRedirect(getServletContext().getContextPath() + "/admin/users?page=" + page + "&limit=" + limit + "&search=" + search);
            return;
        }

        if (action.equals("delete")) {
            int userId = StringUtils.isNullOrEmpty(req.getParameter("id")) ? 0 : Integer.parseInt(req.getParameter("id"));
            if (userId != 0) {
                UserRepository userRepository = new UserRepository();
                User user = userRepository.findById(userId);
                if (user == null) {
                    doGet(req, resp);
                    return;
                }
                userRepository.remove(user);

                HttpSession session = req.getSession(false);
                User userSession = (User) session.getAttribute("user");
                if(userSession.getId() == user.getId()) {
                    session.invalidate();
                }
            }
        }

        if (action.equals("update")) {
            int userId = StringUtils.isNullOrEmpty(req.getParameter("id")) ? 0 : Integer.parseInt(req.getParameter("id"));
            String username = StringUtils.isNullOrEmpty(req.getParameter("username")) ? "" : req.getParameter("username");
            String role = StringUtils.isNullOrEmpty(req.getParameter("role")) ? "USER" : req.getParameter("role");

            if (userId != 0) {
                UserRepository userRepository = new UserRepository();
                User user = userRepository.findById(userId);
                if (user == null) {
                    doGet(req, resp);
                    return;
                }

                if(!StringUtils.isNullOrEmpty(req.getParameter("password"))) {
                    String hashedPassword = PasswordUtils.hashPassword(req.getParameter("password"));
                    user.setPassword(hashedPassword);
                }

                user.setUsername(username);
                user.setRole(role);
                userRepository.update(user);
            }
        }

        resp.sendRedirect(getServletContext().getContextPath() + "/admin/users");
    }
}

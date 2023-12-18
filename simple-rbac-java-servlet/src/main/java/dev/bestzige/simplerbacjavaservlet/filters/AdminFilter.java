package dev.bestzige.simplerbacjavaservlet.filters;

import dev.bestzige.simplerbacjavaservlet.entities.User;
import dev.bestzige.simplerbacjavaservlet.repositories.UserRepository;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter(filterName = "adminFilter", urlPatterns = {"/admin/*"})
public class AdminFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = ((HttpServletRequest) servletRequest);
        HttpSession session = httpRequest.getSession(false);
        HttpServletResponse response = ((HttpServletResponse) servletResponse);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect(httpRequest.getContextPath() + "/auth/login");
        } else {
            UserRepository userRepository = new UserRepository();
            User userSession = (User) session.getAttribute("user");
            User user = userRepository.findById(userSession.getId());
            if (user.getRole().equals("ADMIN")) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                response.sendRedirect(httpRequest.getContextPath() + "/no-permission");
            }
        }
    }
}

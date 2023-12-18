package dev.bestzige.simplerbacjavaservlet.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

@WebFilter(filterName = "loggerFilter", urlPatterns = {"/*"})
public class LoggerFilter implements Filter {
    private FilterConfig filterConfig;

    @Override
    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        long startTime = System.currentTimeMillis();

        filterChain.doFilter(servletRequest, servletResponse);

        long endTime = System.currentTimeMillis();

        String pathInfo = ((HttpServletRequest) servletRequest).getRequestURI();
        String httpMethod = ((HttpServletRequest) servletRequest).getMethod();

        String message = String.format("Request to %s with method %s took %d ms", pathInfo, httpMethod, endTime - startTime);

        filterConfig.getServletContext().log(message);
    }
}

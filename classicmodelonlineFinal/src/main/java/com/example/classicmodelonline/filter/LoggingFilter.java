package com.example.classicmodelonline.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

@WebFilter(filterName = "LoggingFilter" , servletNames = {"AddOfficeServlet","OfficeListServlet","ProductListServlet"})
// filter attributes can be (url pattern , servletName)
public class LoggingFilter implements Filter {
    private FilterConfig config;

    public void init(FilterConfig config) throws ServletException {
        this.config = config;
    }
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException, IOException {
        // in
        long before = System.currentTimeMillis();
        // before invoke resource
        chain.doFilter(request, response); // called duration filter
        // out
        // after invoke resource
        long duration = System.currentTimeMillis() - before;
        // use with many resource
        String msg = "Resource : " + ((HttpServletRequest) request).getRequestURI() +
                ", execution time : " + duration + "milliSeconds" ;
        config.getServletContext().log(msg);
    }

    public void destroy() {
    }


}
 

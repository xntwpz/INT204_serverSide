package com.example.classicmodelonline.servlets;

import java.io.IOException;

import com.example.classicmodelonline.entities.Customer;
import com.example.classicmodelonline.repositories.CustomerRepository;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "AuthenticationServlet", value = "/login")
public class AuthenticationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    // on doPost only***
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // client sent userName , password
        String password = request.getParameter("password");
        String userName = request.getParameter("userName");
        // validation **
        if (userName == null || userName.trim().length() == 0) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        // use method findByName in CustomerRepository
        // firstName lastName
        CustomerRepository customerRepository = new CustomerRepository();
        Customer customer = customerRepository.findByName(userName);

        // can;t find
        if (customer == null) {
            response.addHeader("error", "User account '" + userName + "' does not exits!!");
            // return 401 or 404
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            // can find , set password
        } else {
            Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2d, 16, 16);
            char[] passwordArray = password.toCharArray();
//            System.out.println("------------------------");
//            System.out.println(customer.getPassword());
//            System.out.println(password);

            // verify password
            boolean isOk = argon2.verify(customer.getPassword(), passwordArray);
            // isn't match
            if (!isOk) {
                response.addHeader("error","Incorrect Password ..... try again");
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            // if match , will add in session ; name user
            } else {
                request.getSession().setAttribute("user", customer);
                // will have object of that user in session.
            }
        }
    }
}
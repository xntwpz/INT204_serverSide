package com.example.classicmodelonline.servlets;

import com.example.classicmodelonline.repositories.OfficeRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "AddOfficeServlet", value = "/add-office")
public class AddOfficeServlet extends HttpServlet {
    private final OfficeRepository officeRepository = new OfficeRepository();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/add-office.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        officeRepository.insert(officeRepository.newOffice(request,response));
        officeRepository.close();
        response.sendRedirect(getServletContext().getContextPath()+ "/office-list");
    }
}
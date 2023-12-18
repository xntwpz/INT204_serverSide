package com.example.classicmodelonline.servlets;


import com.example.classicmodelonline.entities.Office;
import com.example.classicmodelonline.repositories.OfficeRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "UpdateOfficeServlet", value = "/update-office")
public class UpdateOfficeServlet extends HttpServlet {
    private final OfficeRepository officeRepository = new OfficeRepository();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String officeCode = request.getParameter("officeCode");
        if(officeRepository.isValidString(officeCode)){
            Office findOff = officeRepository.find(officeCode);
            if(findOff != null){
                request.setAttribute("office",findOff);
            }
        }
        officeRepository.close();
        request.getRequestDispatcher("/update-office.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       officeRepository.update(officeRepository.newOffice(request,response));
       officeRepository.close();
       response.sendRedirect(getServletContext().getContextPath()+ "/office-list");    }
}
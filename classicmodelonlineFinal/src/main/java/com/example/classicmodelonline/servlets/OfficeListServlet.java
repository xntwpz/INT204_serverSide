package com.example.classicmodelonline.servlets;

import com.example.classicmodelonline.repositories.OfficeRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "OfficeListServlet", value = "/office-list")
public class OfficeListServlet extends HttpServlet {
    private final OfficeRepository officeRepository = new OfficeRepository();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        request.setAttribute("offices" , officeRepository.findAll());
//        String officeCode = request.getParameter("officeCode");
//        String cityOrCountry = request.getParameter("cityOrCountry");
//        if(officeCode != null){
//            request.setAttribute("offices" , officeRepository.findByCityOrCountry(cityOrCountry));
//        }
//        if(officeRepository.isValidString(cityOrCountry)){
//            request.setAttribute("offices" , officeRepository.findByCityOrCountry(cityOrCountry));
//        }
//        getServletContext().getRequestDispatcher("/office-list.jsp").forward(request,response);
        OfficeRepository officeRepository = new OfficeRepository();
        String officeCode = request.getParameter("officeCode");
        String cityOrCountry = request.getParameter("cityOrCountry");
        if(officeCode != null) {
            request.setAttribute("offices", officeRepository.findAll());
        } else {
            request.setAttribute("offices", officeRepository.findByCityOrCountry(cityOrCountry));
        }
        getServletContext().getRequestDispatcher("/office-list.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String delelte = request.getParameter("delete");
        officeRepository.delete(delelte);
        officeRepository.close();
        doGet(request,response);
    }
}
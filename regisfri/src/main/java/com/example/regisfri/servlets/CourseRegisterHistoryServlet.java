package com.example.regisfri.servlets;

import java.io.IOException;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "CourseRegisterHistoryServlet", value = "/course-registered-history")
public class CourseRegisterHistoryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if(session==null || session.getAttribute("courseRegistered") == null ){
            request.setAttribute("message" , "ไม่มีข้อมูล การลงทะเบียน ขอให้ลงทะเบียนก่อน");
        } // messageเป็นตัวบอกว่า มีหรือไม่มีการลงทะเบียน เพื่อเอาไปบอกview
        getServletContext().getRequestDispatcher("/user_registered.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
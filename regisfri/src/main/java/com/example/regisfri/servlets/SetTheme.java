package com.example.regisfri.servlets;

import java.io.IOException;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "SetTheme", value = "/set-theme")
public class SetTheme extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/set_theme.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bgColor = request.getParameter("bgColor"); //select color
        Cookie ck = new Cookie("bg_color_cookie",bgColor);
        ck.setMaxAge(8*24*60*60);
        response.addCookie(ck); // browser keep cookie
        response.sendRedirect("index.jsp"); // respond back to client
    }
}
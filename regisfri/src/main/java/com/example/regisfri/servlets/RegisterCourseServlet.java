package com.example.regisfri.servlets;

import java.io.IOException;
import java.util.Map;

import com.example.regisfri.models.CourseRegistered;
import com.example.regisfri.models.CourseRepository;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "RegisterCourseServlet", value = "/register")
public class RegisterCourseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String , String[]> parameterMap = request.getParameterMap();
        int semester = Integer.valueOf(parameterMap.get("semester")[0]);

        HttpSession session = request.getSession();
        CourseRegistered courseRegistered = (CourseRegistered) session.getAttribute("courseRegistered");
        if(courseRegistered == null){ // regis firstname
            courseRegistered = new CourseRegistered();
            session.setAttribute("courseRegistered" , courseRegistered);
        } else {
            courseRegistered.removeAllRegisteredCourse(semester);
        }
        for (String subjectId:parameterMap.get("registeredSubjects")) {
            courseRegistered.registerSubject(semester, CourseRepository.getSubject(semester, subjectId));
        } // loop for regis ทีละวิชา
        getServletContext().getRequestDispatcher("/index.jsp").forward(request,response);
    }
}
package sit.int202.simplefri.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "FavoriteSubjectServlet", value = "/favoriteSubject") //value name = action in html
public class FavoriteSubjectServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doProcess(request , response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doProcess(request , response);
    }
    private void doProcess(HttpServletRequest request , HttpServletResponse response) throws ServletException,IOException {
        String name = request.getParameter("name");
        String id = request.getParameter("id");
        String subjects[] = request.getParameterValues("subjects"); // multiple values use getParameterValues cuz have many subject

        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>Your Favorite Subject</h1></hr>");
        out.println("<hr>");
        out.println("Student Id : " + id + "</br>");
        out.println("Student Name : " + name + "</br>");
        out.println("Your Favorite Subject : </br>");
        for (String subject : subjects) {
            out.println("&nbsp; &nbsp; - " + subject+ "<br>");
        }
        Map<String, String[]> params = request.getParameterMap();
        out.println("-------------------------------------</br>");
        out.println("Request Parameter from Map </br>");
        out.println("-------------------------------------</br>");
        out.println("Student Id : " + params.get("id")[0] + "</br>");
        out.println("Student Name : " + params.get("name")[0] + "</br>");
        out.println("Your Favorite Subject : </br>");
        for (String subject : params.get("subjects")) {
            out.println("&nbsp; &nbsp; - " + subject+ "<br>");
        }
        out.println("<hr>");
        out.println("<a href = 'favorite_subject.html'> Back </a>");
        out.println("</body></html>");
    }
}
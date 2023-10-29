package sit.int202.simplefri.servlet;

import java.io.IOException;
//import java.io.PrintWriter;
import java.util.List;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import sit.int202.simplefri.entities.Subject;
import sit.int202.simplefri.repositories.SubjectRepository;

@WebServlet(name = "SubjectListServlet", value = "/subject-List")
public class SubjectListServlet extends HttpServlet {
    private long startTime;

    @Override
    public void destroy() {
        // destroy when stop tomcat
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println("Duration of SubjectListServlet is " +
                (System.currentTimeMillis()-startTime) + " milli seconds");
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!");
    }

    @Override
    public void init() throws ServletException {
        // member time that init() start
        startTime = System.currentTimeMillis();
        // must understand how computer store DATE = store number that start from 1 Jan 1970 07:00 , 1milli sec = 1
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SubjectRepository subjectRepository = new SubjectRepository();
        List<Subject> subjects = subjectRepository.findAll();
        request.setAttribute("subjects" , subjects); // request is object that can contain attributes by setAttributes
        request.getRequestDispatcher("/subject_list.jsp").forward(request,response);
        // shared to other component(servlet => jsp) by .forward like send flow to other class = JSP



//        PrintWriter out = response.getWriter();
//        out.println("<html><body>");
//        out.println("<h1>Subject List :: </h1></hr>");
//        for (Subject subject: subjectRepository.findAll()) {
//            out.println(subject.getId());
//            out.println("   ");
//            out.println(subject.getTitle());
//            out.println("   ");
//            out.println(subject.getCredit());
//            out.println("</br>");
//        }
//        out.println("</br>");
//        out.println("</body></html>");

    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
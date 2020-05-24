package Server;


import school.Course;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class CourseList extends HttpServlet {



    ZonedDateTime startDate = ZonedDateTime.parse("2020-01-02T00:00:00.000+00:00[UTC]");
    ZonedDateTime endDate = ZonedDateTime.parse("2020-12-31T00:00:00.000+00:00[UTC]");

    DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    private Course course = new Course("English", 4, startDate, endDate);
    private Course course1 = new Course("French",4, startDate.plusMonths(6),  endDate.plusMonths(6));
    private Course course2 = new Course("Estonian",8, startDate.minusYears(1).minusDays(37), endDate.minusYears(2).plusDays(45));

    Course [] courses = {course, course1, course2};



    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

//        String name = request.getParameter("CourseList");

        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
        for (Course a : courses){
//            response.getWriter().println("<html><h1>"+a.toString()+"</h1></html>");
            response.getWriter().println("<html><h1>"+a.courseName + ", " + a.EAP + " EAP, starts on " + a.startDate.toLocalDateTime().format(format) +  " and ends on " +a.endDate.toLocalDateTime().format(format) +"</h1></html>");
        }

    }
}

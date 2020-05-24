package Server;

import school.Course;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.ZonedDateTime;

public class WorkDays extends HttpServlet{

    ZonedDateTime startDate = ZonedDateTime.parse("2020-01-02T00:00:00.000+00:00[UTC]");
    ZonedDateTime endDate = ZonedDateTime.parse("2020-12-31T00:00:00.000+00:00[UTC]");

    private Course course = new Course("English", 4, startDate, endDate);
    private Course course1 = new Course("French",4, startDate.plusMonths(6),  endDate.plusMonths(6));
    private Course course2 = new Course("Estonian",8, startDate.minusYears(1).minusDays(37), endDate.minusYears(2).plusDays(45));

    public Course [] courses = {course, course1, course2};

    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String id = request.getParameter("id");

        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);

        PrintWriter writer = response.getWriter();

        String htmlRespone = "<html>";
        htmlRespone += "<h1>" + courses[Integer.parseInt(id)].courseName + " course has " + courses[Integer.parseInt(id)].getWorkingDays() + " workdays</h1>";
        htmlRespone += "</html>";

        writer.println(htmlRespone);


    }
}

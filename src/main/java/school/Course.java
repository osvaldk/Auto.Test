package school;

import Service.PublicHolidayService;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

public class Course {

    public String courseName;
    public Integer EAP;
    public Integer maxStudentsQuantity;
    public ZonedDateTime startDate;
    public ZonedDateTime endDate;
    public Teacher teacher;
    PublicHolidayService service = new PublicHolidayService();


    public Course(String courseName, Integer EAP, ZonedDateTime startDate, ZonedDateTime endDate) {
        this.courseName = courseName;
        this.EAP = EAP;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Course(ZonedDateTime startDate, ZonedDateTime endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Course() {
    }

    public String getCourseName() {
        return courseName;
    }

    public Integer getEAP() {
        return EAP;
    }

    public Integer getMaxStudentsQuantity() {
        return maxStudentsQuantity;
    }


    public ZonedDateTime getStartDate() {
        return startDate;
    }

    public ZonedDateTime getEndDate() {
        return endDate;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public Long getLength() {
        return ChronoUnit.DAYS.between(startDate.minusDays(1), endDate);
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseName='" + courseName + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }

    public int getWorkingDays() throws IOException {

        Integer holidaysOnWorkingDays = service.getNumberOfPublicHolidaysOnWorkingDays(startDate, endDate);

        if (endDate.isAfter(startDate) && holidaysOnWorkingDays > 0) {

            final int startW = startDate.minusDays(1).getDayOfWeek().getValue();
            final int endW = endDate.getDayOfWeek().getValue();

            final int days = (int) ChronoUnit.DAYS.between(startDate.minusDays(1), endDate);

            int result = days - 2 * (days / 7); //remove weekends

            if (days % 7 != 0) { //deal with the rest days
                if (startW == 7) {
                    result -= 1;
                } else if (endW == 7) {  //they can't both be Sunday, otherwise rest would be zero
                    result -= 1;
                } else if (endW < startW) { //another weekend is included
                    result -= 2;
                }
            }


            return result - holidaysOnWorkingDays;
        } else if (holidaysOnWorkingDays < 0) {
            throw new IllegalArgumentException("Service returns - 1");
        } else if (holidaysOnWorkingDays == 0) {
            throw new IllegalArgumentException("Service returns 0");
        } else if (holidaysOnWorkingDays == null) {
            throw new IllegalArgumentException("Service returns null");
        } else if (holidaysOnWorkingDays.equals(Exception.class)) {
            throw new IllegalArgumentException("Service throws exception");
        } else {
            throw new IllegalArgumentException("wtf");
        }
    }
}

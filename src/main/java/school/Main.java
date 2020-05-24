package school;

import java.time.ZonedDateTime;

public class Main {
    public static void main(String[] args) {

        ZonedDateTime startDate = ZonedDateTime.parse("2020-01-02T00:00:00.000+00:00[UTC]");
        ZonedDateTime endDate = ZonedDateTime.parse("2020-12-31T00:00:00.000+00:00[UTC]");

        Course course = new Course(startDate, endDate);




    }
}

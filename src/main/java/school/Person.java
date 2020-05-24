package school;

import java.time.ZonedDateTime;

public interface Person {

    String getFullName();

    String getFirstName();

    ZonedDateTime getDateOfBirth();

    Long getAge();

    void sayHello();

}

package school;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

public class PersonImpl implements Person {

    private String name;
    private String surname;
    private ZonedDateTime DateOfBirth;

    public PersonImpl(String name, String surname, ZonedDateTime dateOfBirth) {
        this.name = name;
        this.surname = surname;
        DateOfBirth = dateOfBirth;
    }

    public String getFullName() {
        return this.name + " " + this.surname;
    }

    public String getFirstName() {
        return this.name;
    }

    @Override
    public ZonedDateTime getDateOfBirth() {
        return this.DateOfBirth;
    }

    public Long getAge() {
        return ChronoUnit.YEARS.between(this.DateOfBirth, ZonedDateTime.now());
    }


    public void sayHello() {
        System.out.println("Hello, world!");
    }
}

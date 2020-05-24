package school;

import java.time.ZonedDateTime;

public class Teacher extends PersonImpl {


    @Override
    public void sayHello() {
        System.out.println("Welcome, teacher!");
    }

    public Teacher(String name, String surname, ZonedDateTime dateOfBirth) {
        super(name, surname, dateOfBirth);
    }
}

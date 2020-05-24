package Utils;

import school.Student;

import java.util.List;

public class Utils {

    public static void printAllStudents(List<Student> students) {

        for (Student student : students) {
            System.out.println(student.getFullName());
        }

    }
}

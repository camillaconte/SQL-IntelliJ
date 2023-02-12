package Ex02;

import java.sql.PreparedStatement;

public class Student {

    String firstName;
    String lastName;
    int studentId;

    public Student(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String insertStudentDb(){
        String studentQuery = "INSERT INTO newdb.students (first_name, last_name) VALUES ('" + this.firstName + "\', \'" + this.lastName + "\');";
        return studentQuery;
    }
}

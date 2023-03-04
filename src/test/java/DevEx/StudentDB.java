package DevEx;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StudentDB {

    /**
     * Questo metodo serve sia per inserire nel DB un oggetto studente che è già stato creato
     * Sia per crearne uno nuovo al volo dentro al metodo createStudentAndInsertDb
     */
    public static void insertStudentDb(Student student) throws SQLException {
        ConnectionDB connection = new ConnectionDB();
        String studentQuery = "INSERT INTO newdb.students (first_name, last_name) VALUES ('"
                + student.firstName + "\', \'" + student.lastName + "\');";
        connection.connection.createStatement().executeUpdate(studentQuery);
        connection.connection.close();
    }

    public static void createStudentAndInsertDb(String firstName, String lastName) throws SQLException {
        Student student = new Student(firstName, lastName);
        insertStudentDb(student);
    }
}


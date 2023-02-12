package Ex02;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TableCreation {

    public static void main(String[] args) {

        Connection conn = null;
        PreparedStatement ps = null;

        try {
            // db parameters
            String url = "jdbc:mysql://localhost:3306/newdb";
            String user = "root";
            String password = "password";

            // create a connection to the database
            conn = DriverManager.getConnection(url, user, password);
            //create TABLE
            String createTable = "CREATE TABLE IF NOT EXISTS newdb.students(\n" +
                    "student_id INT(10) NOT NULL AUTO_INCREMENT,\n" +
                    "first_name VARCHAR(30),\n" +
                    "last_name VARCHAR(30),\n" +
                    "constraint student_pk PRIMARY KEY (student_id));";

            ps = conn.prepareStatement(createTable);
            ps.execute();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }

        List<Student> studentsToAdd = new ArrayList<>();
        studentsToAdd.add(0, new Student("Ringo", "Star"));
        studentsToAdd.add(1, new Student("Paul", "McCartney"));
        studentsToAdd.add(2, new Student("George", "Harrison"));
        studentsToAdd.add(3, new Student("John", "Lennon"));

        for(Student student : studentsToAdd) {
            try {
                String url = "jdbc:mysql://localhost:3306/newdb";
                String user = "root";
                String password = "password";

                conn = DriverManager.getConnection(url, user, password);
                ps = conn.prepareStatement(student.insertStudentDb());
                ps.execute();

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            } finally {
                try {
                    if (conn != null) {
                        conn.close();
                    }
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }

            /*
            Statement stmt = conn.prepareStatement();
            ResultSet rs = stmt.executeQuery("SELECT ");
            rs.next()*/
    }
}

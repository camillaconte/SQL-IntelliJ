package DevEx;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDB {

    public static void createStudentsTable(String database) throws SQLException {
        ConnectionDB connection = new ConnectionDB(database);
        String query = "CREATE TABLE IF NOT EXISTS students(\n" +
                "id INT(10) NOT NULL AUTO_INCREMENT,\n" +
                "first_name VARCHAR(30),\n" +
                "last_name VARCHAR(30),\n" +
                "constraint student_pk PRIMARY KEY (student_id));";
        connection.connection.createStatement().executeUpdate(query);
        System.out.printf("Table \"students\" has been created \n");
        connection.connection.close();
    }

    /**
     * Questo metodo serve sia per inserire nel DB un oggetto studente che è già stato creato
     * Sia per crearne uno nuovo al volo dentro al metodo createStudentAndInsertDb
     */
    public static void insertStudentDb(Student student) throws SQLException {
        ConnectionDB connection = new ConnectionDB();
        String studentQuery = "INSERT INTO newdb.students (first_name, last_name) VALUES ('"
                + student.firstName + "\', \'" + student.lastName + "\');";
        connection.connection.createStatement().executeUpdate(studentQuery);
        System.out.println("A new student has been inserted into database!");
        connection.connection.close();
    }

    public static void createStudentAndInsertDb(String firstName, String lastName) throws SQLException {
        Student student = new Student(firstName, lastName);
        insertStudentDb(student);
    }

    /**
     * @Author: Carlo Casiglia
     * @param country
     * @throws SQLException
     */
    public static void createFilterByCountryOnStudents(String country) throws SQLException {
        ConnectionDB connection = new ConnectionDB();
        String query = String.format("create view %s_students as (select * from students where country = \"%s\")", country, country);
        connection.connection.createStatement().executeUpdate(query);
        System.out.printf("Create filtered view on %s students\n", country);
    }

    /**
     * @Author: Carlo Casiglia
     */

    public static List<Student> createStudentList(String country) throws SQLException {
        ConnectionDB connection = new ConnectionDB();
        ResultSet rows = connection.connection.createStatement().executeQuery(String.format("select * from %s_students", country));
        List<Student> studentList = new ArrayList<>();
        // per ogni riga dentro al risultato vogliamo prendere le singole celle al loro interno
        // e usarle per costruire degli oggetti di tipo Student, e metterli dentro alla lista
        while (rows.next()) { // "finché ce n'è un'altra ..."
            studentList.add(new Student(
                    rows.getInt("id"),
                    rows.getString("first_name"),
                    rows.getString("last_name"),
                    rows.getString("country")));
        }
        return studentList;
    }

}


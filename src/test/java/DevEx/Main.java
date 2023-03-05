package DevEx;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        /**
         * Exercise SQL02: Create a TABLE called students (in your preferred database) and POPULATE it WITH 4 random students
         * (remember that you need only to insert last_name and first_name
         * because the primary key is auto-incremented)
         */

        try {
            ToolsDB.createTable("newdb", "students");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            ToolsDB.createNewColumn("newdb", "students", "first_name", "VARCHAR(30)");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            ToolsDB.createNewColumn("newdb", "students", "last_name", "VARCHAR(30)");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        List<Student> studentsToAdd = new ArrayList<>();
        studentsToAdd.add(0, new Student("Ringo", "Star"));
        studentsToAdd.add(1, new Student("Paul", "McCartney"));
        studentsToAdd.add(2, new Student("George", "Harrison"));
        studentsToAdd.add(3, new Student("John", "Lennon"));

        for(Student student : studentsToAdd) {
            try {
                StudentDB.insertStudentDb(student);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        /**
         * Exercise SQL03:
         * take the names and surnames of all the students (using ResultSet and its .next() method) and:
         * print the names on screen while executing the query
         * assign the surnames to an ArrayList called surnames
         * once the query is completed, print all the surnames
         */

        List <String> studentNames = new ArrayList<>();

        try {
            studentNames = StudentDAO.getAllStudents();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        /**
         * Exercise SQL4:
         * add a new string column of 30 chars called country to the students table
         * populate the new column with Italy for 2 students and Germany for the other 2 students
         */

        try {
            ToolsDB.createNewColumn("newdb", "students", "country", "VARCHAR(30)");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            ToolsDB.updateRowByKnownValue("newdb", "students", "country",
                                         "Italy", "last_name", "Star");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            ToolsDB.updateRowByKnownValue("newdb", "students", "country",
                    "Italy", "id", "2");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        //inserting a new column, "fiscalCode"
        try {
            ToolsDB.createNewColumn("newdb", "students", "fiscalCode", "VARCHAR(30)");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        //updating column "fiscalCode" for student whose last name is Harrison
        try {
            ToolsDB.updateRowByKnownValue("newdb", "students", "fiscalCode",
                    "HRRGRG45R13455D", "last_name", "Harrison");
            System.out.println("Harrison's fiscal code has been updated");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        //updating student Hallison's column "country" with "Germany" USING IS FISCALCODE
        try {
            ToolsDB.updateRowByKnownValue("newdb", "students", "country",
                    "Germany", "fiscalCode", "HRRGRG45R13455D");
            System.out.println("Hallison's country has been updated");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        //updating student Lennon's country with "Germany"
        try {
            ToolsDB.updateRowByKnownValue("newdb", "students", "country",
                    "Germany", "last_name", "Lennon");
            System.out.println("Lennon's country has been updated");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        /**
         * Exercise SQL05
         * create a view italian_students that gets all the name and surname of the Italian students
         * create a view german_students that gets all the name and surname of the German students
         * execute a select using the italian_students and put the result in an ArrayList of Student objects called italianStudents
         * execute a select using the german_students and put the result in an ArrayList of Student objects called germanStudents
         */

        try {
            StudentDB.createFilterByCountryOnStudents("Italy");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            StudentDB.createFilterByCountryOnStudents("Germany");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        List<Student> italianStudents;
        try {
            italianStudents = StudentDB.createStudentList("Italy");
            for (Student student: italianStudents) {
                System.out.println(student.toString());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        List<Student> germanStudents;
        try {
            germanStudents = StudentDB.createStudentList("Germany");
            for (Student student: germanStudents) {
                System.out.println(student.toString());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

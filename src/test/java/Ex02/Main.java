package Ex02;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        /**
         * Exercise SQL02: TABLE students and POPULATE WITH 4 random students
         * (emember that you need only to insert last_name and first_name
         * because the primary key is auto-incremented)
         */
        TableCreation.createTable();

        List<Student> studentsToAdd = new ArrayList<>();
        studentsToAdd.add(0, new Student("Ringo", "Star"));
        studentsToAdd.add(1, new Student("Paul", "McCartney"));
        studentsToAdd.add(2, new Student("George", "Harrison"));
        studentsToAdd.add(3, new Student("John", "Lennon"));

        //N.B. qui ci vorrebbe un modo per fare sì che non si possano
        //duplicare gli studenti ogni volta che faccio partire il programma!
        for(Student student : studentsToAdd) {
            StudentDatabase.createStudentAndInsertDb(student.firstName, student.lastName);
        }

        /**
         * Exercise SQL03:
         * take the names and surnames of all the students (using ResultSet and its .next() method) and:
         * print the names on screen while executing the query
         * assign the surnames to an ArrayList called surnames
         * once the query is completed, print all the surnames
         */

        List <String> firstNames = new ArrayList<>(); //GIA' CHE CI SONO MI SALVO ANCHE QUESTI
        List <String> lastNames = new ArrayList<>();

        try{
            Connection conn = null;
            PreparedStatement ps = null;

            //connection to DB with "shortcut"
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/newdb",  "root", "password");

            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM students");

            while (resultSet.next()){
                System.out.println(resultSet.getString("first_name"));
                firstNames.add(resultSet.getString("first_name"));
                lastNames.add(resultSet.getString("last_name"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        for(String lastName : lastNames){
            System.out.println(lastName);
        }

        Tools.createNewColumn("newdb", "students", "country");

        /**
         * Exercise SQL4:
         * add a new string column of 30 chars called country to the students table
         * populate the new column with Italy for 2 students and Germany for the other 2 students
         */
        //Vede Italy come una colonna e non come un valore della colonna
        //"Unknown column 'italy' in 'field list'"
        Tools.updateRow("newdb", "students", "country", "italy", "last_name", studentsToAdd.get(0).lastName);
}
}

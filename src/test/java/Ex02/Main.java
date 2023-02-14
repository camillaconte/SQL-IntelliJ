package Ex02;

import com.mysql.cj.xdevapi.Table;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        TableCreation.createTable();

        List<Student> studentsToAdd = new ArrayList<>();
        studentsToAdd.add(0, new Student("Ringo", "Star"));
        studentsToAdd.add(1, new Student("Paul", "McCartney"));
        studentsToAdd.add(2, new Student("George", "Harrison"));
        studentsToAdd.add(3, new Student("John", "Lennon"));

        //N.B. qui ci vorrebbe un modo per fare sì che non si possano
        //duplicare gli studenti ogni volta che faccio partire il programma!
        for(Student student : studentsToAdd) {
            StudentCreation.createStudentAndInsertDb(student.firstName, student.lastName);
        }

        List <String> firstNames = new ArrayList<>();
        List <String> lastNames = new ArrayList<>();

        try{
            Connection conn = null;
            PreparedStatement ps = null;

            //connection to DB with "shortcut"
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/newdb",  "root", "password");

            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM students");

            while (resultSet.next()){
                System.out.println(resultSet.getString("first_name") + " " + resultSet.getString("last_name"));
                firstNames.add(resultSet.getString("first_name"));
                lastNames.add(resultSet.getString("last_name"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        for(String lastName : lastNames){
            System.out.println(lastName);
        }
    }
}

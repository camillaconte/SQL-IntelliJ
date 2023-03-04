package DevEx;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    Student student;

    public static Student createStudent() {
        Student student = new Student();
        return student;
    }

    public static List<String> getAllStudents() throws SQLException {
        List<String> studentsNames = new ArrayList<>();
        ConnectionDB connection = new ConnectionDB();
        String query = "SELECT * FROM students;";
        Statement statement = connection.connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM students");
        while (resultSet.next()) {
            System.out.println(resultSet.getString("first_name") + " " +
                    resultSet.getString("last_name"));
            studentsNames.add(resultSet.getString("first_name" + " " +
                    resultSet.getString("last_name")));
        }
        return studentsNames;
    }
}







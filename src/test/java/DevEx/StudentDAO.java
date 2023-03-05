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

    /**
     * @Author: Carlo Casiglia
     */
    public List<Student> createStudentListFromDB(String country) throws SQLException {
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

    public static List<String> getAllStudents() throws SQLException {
        List<String> studentsNames = new ArrayList<>();
        ConnectionDB connection = new ConnectionDB();
        String query = "SELECT * FROM students;";
        Statement statement = connection.connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM students");
        while (resultSet.next()) {
            studentsNames.add(resultSet.getString("first_name") + " " + resultSet.getString("last_name"));
            System.out.println(resultSet.getString("first_name") + " " +
                    resultSet.getString("last_name"));
        }
        return studentsNames;
    }
}







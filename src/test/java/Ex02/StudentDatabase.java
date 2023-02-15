package Ex02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StudentDatabase {

    /**
     * Questo metodo serve sia per inserire nel DB un oggetto studente che è già stato creato
     * Sia per crearne uno nuovo al volo dentro al metodo createStudentAndInsertDb
     */
    public static String insertStudentDb(Student student){
        String studentQuery = "INSERT INTO newdb.students (first_name, last_name) VALUES ('"
                + student.firstName + "\', \'" + student.lastName + "\');";
        return studentQuery;
    }

    public static void createStudentAndInsertDb (String firstName, String lastName) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            String url = "jdbc:mysql://localhost:3306/newdb";
            String user = "root";
            String password = "password";

            conn = DriverManager.getConnection(url, user, password);
            ps = conn.prepareStatement(insertStudentDb(new Student(firstName, lastName)));
            //ps = conn.prepareStatement(new Student(firstName, lastName).insertStudentDb());
            ps.execute();

            //COME FACCIO ORA A FAR CORRISPONDERE IL NOME DELL'OGGETTO STUDENTE
            //ALL'ID NEL DATABASE???
            //1) DOVRò CREARE UNA QUERY CHE MI Dà L'ID
            //MA POI COME FACCIO A SALVARE QUESTO new Student?

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
}

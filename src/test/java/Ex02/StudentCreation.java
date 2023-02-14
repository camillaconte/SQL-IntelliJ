package Ex02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentCreation {

    public static void createStudentAndInsertDb (String firstName, String lastName) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            String url = "jdbc:mysql://localhost:3306/newdb";
            String user = "root";
            String password = "password";

            conn = DriverManager.getConnection(url, user, password);
            ps = conn.prepareStatement(new Student(firstName, lastName).insertStudentDb());
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

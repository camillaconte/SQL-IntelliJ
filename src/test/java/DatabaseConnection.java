import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DatabaseConnection {

    public static void main(String[] args) {

        /* PRIMA VERSIONE SENZA SALVARE LE database properties*/

        Connection conn = null;
        PreparedStatement ps = null;

        try {
            // db parameters
            String url       = "jdbc:mysql://localhost:3306/MedicalClinic";
            String user      = "root";
            String password  = "password";

            // create a connection to the database
            conn = DriverManager.getConnection(url, user, password);

            //Creazione della tabella
            String query = "CREATE TABLE IF NOT EXISTS clinicalPerformance ("  + "clinPerf_id INT(10) NOT NULL PRIMARY KEY AUTO_INCREMENT)";

            ps = conn.prepareStatement(query);
            ps.execute();

        } catch(SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try{
                if(conn != null){
                conn.close();}
            }catch(SQLException ex){
                System.out.println(ex.getMessage());
            }
        }


        /* ALTRA STRADA:

        Connection conn = null;

        try(FileInputStream f = new FileInputStream("db.properties")) {
            // load the properties file
            Properties pros = new Properties();
            pros.load(f);

            // assign db parameters
            String url       = pros.getProperty("url");
            String user      = pros.getProperty("user");
            String password  = pros.getProperty("password");
            // create a connection to the database
            conn = DriverManager.getConnection(url, user, password);
        } catch(IOException e) {
            System.out.println(e.getMessage());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try{
                if(conn != null)
                    conn.close();
            }catch(SQLException ex){
                System.out.println(ex.getMessage());
            }

        }
        */
    }
}

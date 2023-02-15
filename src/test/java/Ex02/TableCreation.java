package Ex02;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TableCreation {

    /**
     * il prossimo passo è rendere questo metodo generico come ho fatto per altri metodi!
     * ora è solo per la tabella "students"!
     */
        public static void createTable () {
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
                String createTable = "CREATE TABLE IF NOT EXISTS students(\n" +
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
        }

        /*
        try{
           //
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/newdb",  "root", "password");
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM students");
            while (resultSet.next()){
                System.out.println(resultSet.getString("first_name"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }*/

    }


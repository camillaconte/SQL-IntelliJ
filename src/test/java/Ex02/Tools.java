package Ex02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Tools {

    public static void createNewColumn (String database, String tableName, String newColumn) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            String url = "jdbc:mysql://localhost:3306/" + database;
            String user = "root";
            String password = "password";

            // create a connection to the database
            conn = DriverManager.getConnection(url, user, password);
            //create TABLE
            String query = "ALTER TABLE " + tableName + " ADD " + newColumn + " VARCHAR(30);";

            ps = conn.prepareStatement(query);
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

    public static void updateRow (String databaseName, String tableName, String columnName, String value, String referenceColumn, String referenceValue) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            String url = "jdbc:mysql://localhost:3306/" + databaseName;
            String user = "root";
            String password = "password";
            conn = DriverManager.getConnection(url, user, password);

            /*
            Esempi di QUERY simili sul web a cui mi sono ispirata suonano così:
            "Voglio cambiare il cognome dell'eMployee con ID = 3 (nuovo cognome: 'Hill')
            UPDATE employees SET lastname = 'Hill' WHERE employeeID = 3;

             */

            String query = "UPDATE " + tableName + " SET " +  columnName + " = \'"  + value + "\'"
                    + " WHERE " + referenceColumn + " = \'" + referenceValue + "\';";

            ps = conn.prepareStatement(query);
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


}

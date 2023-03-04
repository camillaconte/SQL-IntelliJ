package DevEx;

import java.sql.*;

/**
 * This class responsibility is: CONNECT TO DATABASE
 */
public class ConnectionDB {

    private static final String host = "jdbc:mysql://localhost:3306/";
    private static final String user = "root";
    private static final String password = "password";

    public static Connection connection;
    Date date;
    public ConnectionDB(String databaseName) {
        System.out.println("Connecting to " + host + "...");
        try {
        connection = DriverManager.getConnection(host + databaseName, user, password);
        System.out.println("You have been successfully connected!");
        } catch (SQLException e) {
            System.out.println("Connection failed!");
            System.out.println(e.getMessage());
        }
    }

    public ConnectionDB() {
        System.out.println("Connecting to " + host + "...");
        try {
            connection = DriverManager.getConnection(host + "newDB", user, password);
            System.out.println("You have been successfully connected!");
        } catch (SQLException e) {
            System.out.println("Connection failed!");
            System.out.println(e.getMessage());
        }
    }

}

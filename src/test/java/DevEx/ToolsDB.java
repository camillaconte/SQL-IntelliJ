package DevEx;

import java.sql.*;

public class ToolsDB {

    /**
     * Generic method (Not only for Tabel "students")
     * to CREATE A TABLE
     */
    public static void createTable(String database, String tableName) throws SQLException {
        ConnectionDB connection = new ConnectionDB(database);
        String query = "CREATE TABLE IF NOT EXISTS " + tableName + "(\n" +
                "id INT(10) NOT NULL AUTO_INCREMENT,\n" +
                "PRIMARY KEY (id));";
                //"constraint " + tableName + "_pk PRIMARY KEY (" + tableName + "_id));";
        connection.connection.createStatement().executeUpdate(query);
        System.out.printf("Table %s has been created \n", tableName);
        connection.connection.close();
    }

    /**
     * Generic method to create a new column in a given DB
     * @param database
     * @param tableName
     * @param newColumn
     * @param columnType
     * @throws SQLException
     */
    public static void createNewColumn(String database, String tableName, String newColumn, String columnType) throws SQLException {
        ConnectionDB connection = new ConnectionDB(database);
        String query = "ALTER TABLE " + tableName + " ADD " + newColumn + " " + columnType + ";";
        connection.connection.createStatement().executeUpdate(query);
        System.out.println("New column \"" + newColumn + "\" has been created!");
        connection.connection.close();
    }

    /**
     * Generic method to Update a field of a specific element of the table
     * (e.g. update the telephone number of a student, a patient...)
     * using a reference field (for example, the id or the fiscal code)
     * @param databaseName
     * @param tableName
     * @param columnName
     * @param value
     * @param referenceColumn
     * @param referenceValue
     * @throws SQLException
     */

    public static void updateRowByKnownValue (String databaseName, String tableName, String columnName, String value,
                                      String referenceColumn, String referenceValue) throws SQLException {
        ConnectionDB connection = new ConnectionDB(databaseName);
        String query = "UPDATE " + tableName + " SET " +  columnName + " = \'"  + value + "\'"
                    + " WHERE " + referenceColumn + " = \'" + referenceValue + "\';";

        connection.connection.createStatement().executeUpdate(query);
        connection.connection.close();
    }

    public static void insertElementIntoDB(Object object, String databaseName, String tableName,
                                           String columnName, String value) throws SQLException {
        ConnectionDB connection = new ConnectionDB(databaseName);
        String query = "INSERT INTO " + databaseName + "." + tableName + "(" + columnName + ") VALUES ('" +
                object + "." + value + "\');";
        connection.connection.createStatement().executeUpdate(query);
        connection.connection.close();
    }

    /*public static void createTable (String tableName) throws SQLException {
        PreparedStatement ps = null;
        String createTable = "CREATE TABLE IF NOT EXISTS" + tableName "(\n" +
                "id INT(10) NOT NULL AUTO_INCREMENT,\n" +
                "first_name VARCHAR(30),\n" +
                "last_name VARCHAR(30),\n" +
                "constraint student_pk PRIMARY KEY (student_id));";

        ps = connection.connection.prepareStatement(createTable);
        ps.executeUpdate();
    }*/

}


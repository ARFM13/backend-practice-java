package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/dev_manager";
    private static final String USER = "root";
    private static final String PASS = "root";

    private static Connection connection = null;

    // Constructor privado para que nadie pueda hacer "new DatabaseConnection()"
    private DatabaseConnection() {}

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(URL, USER, PASS);
            System.out.println("[DB]: New connection established.");
        }
        return connection;
    }
}
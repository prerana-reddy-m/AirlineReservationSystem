package controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static final String URL = "jdbc:mysql://localhost:3306/airline_reservation";
    private static final String USER = "root"; // Your MySQL username
    private static final String PASSWORD = "0409"; // Your MySQL password

    // Method to establish and return a database connection
    public static Connection getConnection() {
        Connection connection = null;
        try {
            // Establish the connection
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connection to database established successfully.");
        } catch (SQLException e) {
            System.err.println("Failed to connect to database: " + e.getMessage());
        }
        return connection; // Return the connection object
    }

    // Main method to test the connection
    public static void main(String[] args) {
        Connection connection = getConnection(); // Attempt to connect to the database
        if (connection != null) {
            try {
                // Close the connection
                connection.close();
                System.out.println("Connection closed.");
            } catch (SQLException e) {
                System.err.println("Failed to close the connection: " + e.getMessage());
            }
        }
    }
}

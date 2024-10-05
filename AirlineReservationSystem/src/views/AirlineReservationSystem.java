package views;

import controllers.Database;
import controllers.Reservation;
import models.Flight;
import models.User;

import java.sql.*;
import java.time.LocalDate;
import java.util.Scanner;

public class AirlineReservationSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Make sure to handle exceptions for database connections properly
        try {
            // Sample flight data (you can add functionality to input from users)
            Flight flight1 = new Flight(1, "AI202", "New York", LocalDate.now(), 500.00);
            Flight flight2 = new Flight(2, "BA303", "London", LocalDate.now(), 600.00);

            System.out.println("Welcome to Airline Reservation System");
            System.out.println("1. Register");
            System.out.println("2. Book Flight");
            System.out.println("Select an option:");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.println("Enter name:");
                    String name = scanner.nextLine();
                    System.out.println("Enter email:");
                    String email = scanner.nextLine();
                    System.out.println("Enter password:");
                    String password = scanner.nextLine();
                    registerUser(name, email, password);
                    break;

                case 2:
                    System.out.println("Available Flights:");
                    System.out.println("1. " + flight1.getFlightNumber() + " to " + flight1.getDestination() + " - $" + flight1.getPrice());
                    System.out.println("2. " + flight2.getFlightNumber() + " to " + flight2.getDestination() + " - $" + flight2.getPrice());
                    System.out.println("Select a flight:");
                    int flightChoice = scanner.nextInt();
                    System.out.println("Enter your email:");
                    String userEmail = scanner.next();
                    User user = getUserByEmail(userEmail);
                    if (user != null) {
                        Flight selectedFlight = (flightChoice == 1) ? flight1 : flight2;
                        new Reservation().makeReservation(user, selectedFlight);
                    } else {
                        System.out.println("User not found!");
                    }
                    break;

                default:
                    System.out.println("Invalid choice!");
                    break;
            }
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public static void registerUser(String name, String email, String password) throws SQLException {
        String sql = "INSERT INTO users(name, email, password) VALUES (?, ?, ?)";
        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, email);
            pstmt.setString(3, password);
            pstmt.executeUpdate();
            System.out.println("User registered successfully!");
        }
    }

    public static User getUserByEmail(String email) throws SQLException {
        String sql = "SELECT * FROM users WHERE email = ?";
        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new User(rs.getInt("id"), rs.getString("name"), rs.getString("email"), rs.getString("password"));
            }
        }
        return null;
    }
}

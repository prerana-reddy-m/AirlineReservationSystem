package controllers;

import models.Flight;
import models.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

public class Reservation {

    public void makeReservation(User user, Flight flight) {
        String sql = "INSERT INTO reservations(user_id, flight_id) VALUES (?, ?)";
        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, user.getId());
            pstmt.setInt(2, flight.getId());
            pstmt.executeUpdate();
            System.out.println("Reservation made successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Create a sample user and flight (ensure they exist in your database)
        User user = new User(1, "John Doe", "johndoe@example.com", "password123");
        Flight flight = new Flight(1, "AA101", "New York", new Date(), 250.00);

        // Create Reservation instance
        Reservation reservation = new Reservation();

        // Call makeReservation method
        reservation.makeReservation(user, flight);
    }
}

package models;

import java.time.LocalDate;

public class Flight {
    private int id;
    private String flightNumber;
    private String destination;
    private LocalDate departureDate;
    private double price;

    // Constructor
    public Flight(int id, String flightNumber, String destination, LocalDate departureDate, double price) {
        this.id = id;
        this.flightNumber = flightNumber;
        this.destination = destination;
        this.departureDate = departureDate;
        this.price = price;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public String getDestination() {
        return destination;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public double getPrice() {
        return price;
    }

    // Main method for testing
    public static void main(String[] args) {
        // Creating and testing flight objects
        Flight flight1 = new Flight(1, "AI202", "New York", LocalDate.now(), 500.00);
        Flight flight2 = new Flight(2, "BA303", "London", LocalDate.now(), 600.00);

        // Displaying flight details
        System.out.println("Flight 1: " + flight1.getFlightNumber() + " to " + flight1.getDestination() + " - $" + flight1.getPrice());
        System.out.println("Flight 2: " + flight2.getFlightNumber() + " to " + flight2.getDestination() + " - $" + flight2.getPrice());
    }
}

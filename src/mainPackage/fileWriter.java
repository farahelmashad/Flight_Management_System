package mainPackage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class fileWriter {
    public static void writeAdminsToFile(String adminFilePath, List<Admin> admins) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(adminFilePath))) {
            for (Admin admin : admins) {
                // Format: email,password
                String adminString = admin.getEmail() + "," + admin.getPassword();
                writer.write(adminString);
                writer.newLine();  // Add a new line after each admin
            }
        } catch (IOException e) {
            System.out.println("Error writing admins: " + e.getMessage());
        }
    }
    // Writes users to a file
    public static void writeUsers(String userFilePath, List<User> users) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(userFilePath))) {
            for (User user : users) {
                StringBuilder userString = new StringBuilder();
                userString.append(user.getEmail()).append(",").append(user.getPassword());

                // Write the passengers for this user
                for (Passenger passenger : user.getP()) {
                    userString.append(",")
                            .append(passenger.getName()).append(":")
                            .append(passenger.getGender()).append(":")
                            .append(passenger.getPhoneNumber()).append(":")
                            .append(passenger.getDateOfBirth());
                }

                writer.write(userString.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing users: " + e.getMessage());
        }
    }

    // Writes bookings to a file
    public static void writeBookings(String bookingFilePath, List<Booking> bookings) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(bookingFilePath))) {
            for (Booking booking : bookings) {
                StringBuilder bookingString = new StringBuilder();
                bookingString.append(booking.getBookingID()).append(",")
                        .append(booking.getBookingStatus());

                // Write the passengers for this booking
                for (Passenger passenger : booking.getPassenger()) {
                    bookingString.append(",")
                            .append(passenger.getName()).append(":")
                            .append(passenger.getGender()).append(":")
                            .append(passenger.getPhoneNumber()).append(":")
                            .append(passenger.getDateOfBirth());
                }

                writer.write(bookingString.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing bookings: " + e.getMessage());
        }
    }

    // Writes passengers to a file
    public static void writePassengers(String passengerFilePath, List<Passenger> passengers) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(passengerFilePath))) {
            for (Passenger passenger : passengers) {
                writer.write(passenger.getName() + ","
                        + passenger.getGender() + ","
                        + passenger.getPhoneNumber() + ","
                        + passenger.getDateOfBirth());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing passengers: " + e.getMessage());
        }
    }
        // Method to write flights to a file
        public static void writeFlightsToFile(String filename, ArrayList<Flight> flights) {
            try (BufferedWriter writer = new BufferedWriter(new java.io.FileWriter(filename))) {
                // Loop through each flight and write its data to the file
                for (Flight flight : flights) {
                    // Create a string representation for each flight
                    StringBuilder sb = new StringBuilder();

                    sb.append(flight.getFlightNumber()).append(",")
                            .append(flight.getDepartureAirport()).append(",")
                            .append(flight.getArrivalAirport()).append(",")
                            .append(flight.getDepartureTime()).append(",")
                            .append(flight.getArrivalTime()).append(",")
                            .append(flight.getBaseFare()).append(",")
                            .append(flight.getAirlineName()).append(",");

                    // Convert seatAvailability boolean array to a string of 1s and 0s
                    boolean[] seatAvailability = flight.getSeatAvailability();
                    StringBuilder seatAvailabilityString = new StringBuilder();
                    for (boolean seat : seatAvailability) {
                        seatAvailabilityString.append(seat ? "1" : "0");
                    }

                    sb.append(seatAvailabilityString).append(",")
                            .append(flight.getSpecialMealRequest()).append(",")
                            .append(flight.getHasPetTravel()).append(",")
                            .append(flight.getHasLoungeAccess()).append(",")
                            .append(flight.getHaswheelchair()).append("\n");

                    // Write the constructed string to the file
                    writer.write(sb.toString());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    public static void writeAirports(String filePath, ArrayList<Airport> airports) {
        try (BufferedWriter writer = new BufferedWriter(new java.io.FileWriter(filePath))) {
            for (Airport airport : airports) {
                writer.write(airport.getAirportCode() + "," + airport.getAirportName() + "," + airport.getAirportLocation());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    }

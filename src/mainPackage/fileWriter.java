package mainPackage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class fileWriter {

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
}

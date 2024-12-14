package mainPackage;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Filereader {

    public static List<Admin> loadAdmins(String fileName) {
        List<Admin> admins = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new java.io.FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] details = line.split(",");
                if (details.length == 2) {
                    String email = details[0];
                    String password = details[1];
                    Admin admin = new Admin(email, password);
                    admins.add(admin);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return admins;

    }

    // Reads users from a file
    public static List<User> readUsers(String userFilePath) {
        List<User> users = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(userFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(","); // Assuming email, password, and passengers are comma-separated
                String email = parts[0];
                String password = parts[1];

                ArrayList<Passenger> passengers = new ArrayList<>();
                // Read passengers if available
                for (int i = 2; i < parts.length; i++) {
                    String[] passengerDetails = parts[i].split(":");
                    passengers.add(new Passenger(passengerDetails[0], passengerDetails[1], Integer.parseInt(passengerDetails[2]), passengerDetails[3])); // Assuming format: Name,Gender,PhoneNumber,DateOfBirth
                }

                // Use the constructor that matches the data read from the file
                users.add(new User(email, password, passengers));
            }
        } catch (IOException e) {
            System.out.println("Error reading users: " + e.getMessage());
        }
        return users;
    }

    // Reads bookings from a file
    public static List<Booking> readBookings(String bookingFilePath) {
        List<Booking> bookings = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(bookingFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(","); // Assuming bookingID and passengers are separated by commas
                int bookingID = Integer.parseInt(parts[0]);
                String bookingStatus = parts[1]; // Assuming booking status is the second field
                ArrayList<Passenger> passengers = new ArrayList<>();

                // Read passengers if available
                for (int i = 2; i < parts.length; i++) {
                    String[] passengerDetails = parts[i].split(":");
                    passengers.add(new Passenger(passengerDetails[0], passengerDetails[1], Integer.parseInt(passengerDetails[2]), passengerDetails[3]));
                }

                // Assuming we don't have flight data here, you can add it separately if needed
                bookings.add(new Booking(bookingID, null, bookingStatus)); // Replace 'null' with the actual Flight object if available
            }
        } catch (IOException e) {
            System.out.println("Error reading bookings: " + e.getMessage());
        }
        return bookings;
    }

    // Reads passengers from a file
    public static List<Passenger> readPassengers(String passengerFilePath) {
        List<Passenger> passengers = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(passengerFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String name = parts[0];
                String gender = parts[1];
                int phoneNumber = Integer.parseInt(parts[2]);
                String dateOfBirth = parts[3];

                passengers.add(new Passenger(name, gender, phoneNumber, dateOfBirth));
            }
        } catch (IOException e) {
            System.out.println("Error reading passengers: " + e.getMessage());
        }
        return passengers;
    }
}
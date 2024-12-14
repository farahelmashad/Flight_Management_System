package mainPackage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Filereader {

    public static List<Admin> readAdmins(String fileName) {
        List<Admin> admins = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
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
    public static ArrayList<Flight> readFlightsFromFile(String filename) {
        ArrayList<Flight> flights = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Split the data based on commas
                String[] data = line.split(",");
                if (data.length != 12) {
                    System.out.println("Error: Incorrect number of elements in line. Expected 12, but found " + data.length);
                    continue;  // Skip this line if it doesn't have the expected number of fields
                }

                // Extracting flight details from the data array
                String flightID = data[0].trim();
                String departureAirport = data[1].trim();
                String arrivalAirport = data[2].trim();
                String departureDate = data[3].trim();
                String arrivalDate = data[4].trim();
                double price = 0.0;

                try {
                    price = Double.parseDouble(data[5].trim());  // Parsing price safely
                } catch (NumberFormatException e) {
                    System.out.println("Error: Invalid price format for flight ID " + flightID);
                    continue;  // Skip this flight if the price is invalid
                }

                String airline = data[6].trim();

                // Parsing the seat availability string (which is a sequence of 1's or 0's)
                String seatAvailabilityString = data[7].trim();
                boolean[] seatAvailability = new boolean[60]; // We expect 60 seats

                // Ensure that seatAvailabilityString has exactly 60 characters
                if (seatAvailabilityString.length() == 60) {
                    for (int i = 0; i < seatAvailabilityString.length(); i++) {
                        seatAvailability[i] = seatAvailabilityString.charAt(i) == '1';
                    }
                } else {
                    System.out.println("Warning: Seat availability data for flight " + flightID + " is invalid (length: " + seatAvailabilityString.length() + "). Filling with 'false'.");
                    Arrays.fill(seatAvailability, false); // Set all seats to unavailable as a fallback
                }

                // Parsing the service availability (true/false)
                boolean service1 = Boolean.parseBoolean(data[8].trim());
                boolean service2 = Boolean.parseBoolean(data[9].trim());
                boolean service3 = Boolean.parseBoolean(data[10].trim());
                boolean service4 = Boolean.parseBoolean(data[11].trim());

                // Creating a new Flight object and adding it to the ArrayList
                Flight flight = new Flight(flightID, departureAirport, arrivalAirport, departureDate, arrivalDate,
                        price, airline, seatAvailability, service1, service2, service3, service4);
                flights.add(flight);

                // Debugging: Print the flight details to ensure they're correct
                System.out.println("Flight ID: " + flightID);
                System.out.println("Departure Airport: " + departureAirport);
                System.out.println("Arrival Airport: " + arrivalAirport);
                System.out.println("Departure Date: " + departureDate);
                System.out.println("Arrival Date: " + arrivalDate);
                System.out.println("Price: " + price);
                System.out.println("Airline: " + airline);
                System.out.println("Seat Availability: " + Arrays.toString(seatAvailability));
                System.out.println("Service 1: " + service1);
                System.out.println("Service 2: " + service2);
                System.out.println("Service 3: " + service3);
                System.out.println("Service 4: " + service4);
                System.out.println(); // Blank line between flights
            }
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
            e.printStackTrace();
        }

        return flights;
    }
    public static ArrayList<Airport> readAirports(String filePath) {
        ArrayList<Airport> airports = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new java.io.FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] airportData = line.split(","); // Assuming CSV format
                if (airportData.length == 3) {
                    Airport airport = new Airport(airportData[0], airportData[1], airportData[2]);
                    airports.add(airport);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return airports;
    }
}
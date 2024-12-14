package mainPackage;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Read data from files
        List<User> users = Filereader.readUsers("src/users.txt");
        List<Booking> bookings = Filereader.readBookings("src/bookings.txt");
        List<Passenger> passengers = Filereader.readPassengers("src/passengers.txt");
        ArrayList<Flight> flights = Filereader.readFlightsFromFile("src/flights.txt");
        ArrayList<Airport> airports = Filereader.readAirports("src/airports.txt");  // Read airports

        System.out.println("==== Data Loaded From Files ====");
        System.out.println("Users: " + users.size());
        System.out.println("Bookings: " + bookings.size());
        System.out.println("Passengers: " + passengers.size());
        System.out.println("Flights: " + flights.size());
        System.out.println("Airports: " + airports.size());  // Display number of airports loaded
        System.out.println();

        // Check if the flights list is not empty before proceeding
        if (!flights.isEmpty()) {
            Flight firstFlight = flights.get(0);
            System.out.println("First flight details:");
            System.out.println("Flight ID: " + firstFlight.getFlightNumber());
            System.out.println("Departure: " + firstFlight.getDepartureAirport() + " -> " + firstFlight.getArrivalAirport());
            System.out.println("Departure Time: " + firstFlight.getDepartureTime());
            System.out.println("Arrival Time: " + firstFlight.getArrivalTime());
        } else {
            System.out.println("No flights available.");
        }

        // Example operations to test functionality
        System.out.println("==== Performing Operations ====");
        // 1. Add a new passenger
        Passenger newPassenger = new Passenger("John Doe", "Male", 123456789, "1990-01-01");
        passengers.add(newPassenger);
        System.out.println("Added new passenger: " + newPassenger.getName());

        // 2. Create a new user and associate the passenger
        ArrayList<Passenger> userPassengers = new ArrayList<>();
        userPassengers.add(newPassenger);
        User newUser = new User("john.doe@example.com", "password123", userPassengers);
        users.add(newUser);
        System.out.println("Created new user: " + newUser.getEmail());

        // 3. Create a new booking for the user
        Flight dummyFlight = new Flight("FL123", "JFK", "LAX", "10:00 AM", "1:00 PM");
        Booking newBooking = new Booking(1, dummyFlight, "Confirmed");
        newBooking.getPassenger().add(newPassenger); // Assign the passenger to the booking
        bookings.add(newBooking);
        System.out.println("Created new booking for flight: " + dummyFlight.getFlightNumber());

        // 4. Add the booking to the user
        newUser.addBooking(newBooking);
        System.out.println("Assigned booking to user: " + newUser.getEmail());

        // Add new admins to the admin list
        System.out.println("==== Adding New Admins ====");
        List<Admin> admins = new ArrayList<>();
        admins.add(new Admin("admin1@example.com", "password1"));
        admins.add(new Admin("admin2@example.com", "password2"));
        addNewAdminsAndSave("src/admins.txt", admins);  // Add the admins and save to file
        System.out.println("Admins added and saved to file.");

        // 5. Add new airports to the airport list
        System.out.println("==== Adding New Airports ====");
        Airport newAirport1 = new Airport("LAX", "Los Angeles International Airport", "Los Angeles, USA");
        Airport newAirport2 = new Airport("SFO", "San Francisco International Airport", "San Francisco, USA");
        airports.add(newAirport1);
        airports.add(newAirport2);
        System.out.println("Added new airports: ");
        System.out.println(newAirport1.getAirportInfo());
        System.out.println(newAirport2.getAirportInfo());

        // Display updated data
        System.out.println("==== Updated Data ====");
        System.out.println("Users: " + users.size());
        for (User user : users) {
            System.out.println("User: " + user.getEmail() + ", Passengers: " + user.getP().size() + ", Bookings: " + user.getBookings().size());
        }
        System.out.println("Bookings: " + bookings.size());
        System.out.println("Passengers: " + passengers.size());
        System.out.println("Airports: " + airports.size());

        System.out.println();
        // After operations, write the updated data back to the files
        fileWriter.writeUsers("src/users.txt", users);
        fileWriter.writeBookings("src/bookings.txt", bookings);
        fileWriter.writePassengers("src/passengers.txt", passengers);
        fileWriter.writeAirports("src/airports.txt", airports);  // Write updated airports to file
        System.out.println("==== Data Saved Back to Files ====");
    }

    // Method to add new admins to the existing list and save to file
    public static void addNewAdminsAndSave(String adminFilePath, List<Admin> newAdmins) {
        List<Admin> admins = Filereader.readAdmins(adminFilePath); // Read the existing admins from the file
        admins.addAll(newAdmins); // Add the new admins to the list
        fileWriter.writeAdminsToFile(adminFilePath, admins);  // Write the updated list back to the file
    }
}

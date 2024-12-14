package mainPackage;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Read data from files
        List<User> users = Filereader.readUsers("src/users.txt");
        List<Booking> bookings = Filereader.readBookings("src/bookings.txt");
        List<Passenger> passengers = Filereader.readPassengers("src/passengers.txt");

        System.out.println("==== Data Loaded From Files ====");
        System.out.println("Users: " + users.size());
        System.out.println("Bookings: " + bookings.size());
        System.out.println("Passengers: " + passengers.size());
        System.out.println();
        // Perform operations (e.g., user logs in, a booking is made, etc.)

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

        System.out.println();

        // Display updated data
        System.out.println("==== Updated Data ====");
        System.out.println("Users: " + users.size());
        for (User user : users) {
            System.out.println("User: " + user.getEmail() + ", Passengers: " + user.getP().size() + ", Bookings: " + user.getBookings().size());
        }
        System.out.println("Bookings: " + bookings.size());
        System.out.println("Passengers: " + passengers.size());

        System.out.println();
        // After operations, write the updated data back to the files
        fileWriter.writeUsers("src/users.txt", users);
        fileWriter.writeBookings("src/bookings.txt", bookings);
        fileWriter.writePassengers("src/passengers.txt", passengers);
        System.out.println("==== Data Saved Back to Files ====");

    }
}

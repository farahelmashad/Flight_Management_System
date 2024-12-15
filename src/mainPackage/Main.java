package mainPackage;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        // Initialize ArrayLists
        GlobalData.loadData(".idea/users.JSON", ".idea/booking.JSON", ".idea/flights.txt", ".idea/airport.txt", ".idea/admins.txt");
        ArrayList<User> users = GlobalData.users;
        ArrayList<Flight> flights = GlobalData.flights;
        ArrayList<Airport> airports = GlobalData.airports;
        ArrayList<Admin> admins = GlobalData.admins;
        ArrayList<Booking> bookings = GlobalData.bookings;}
}

        // Check if flights are available before creating a booking
//        if (!flights.isEmpty()) {
//            // Let's assume we pick the first flight for the booking (or any flight based on your logic)
//            Flight sampleFlight = flights.get(0); // Or use a flight search logic to find the appropriate flight
//            Booking booking = createBooking(sampleFlight); // Create booking using helper
//            booking.flightSearch(); // Proceed with other actions
//
//            // Optionally, add this booking to a user's bookings for testing
//            if (!users.isEmpty()) {
//                users.get(0).addBooking(booking); // Add the booking to the first user
//            }
//        }
//
//        // Write users to file for testing
//        String userFile = ".idea/users.JSON";
//        AirportFileWriter.writeUsersToFile(users, userFile);
//
//        // Write bookings to file for testing
//        String bookingFile = ".idea/bookings.JSON";
//        AirportFileWriter.writeBookingsToFile(bookings, bookingFile);
//
//        // Write flights to file for testing (using CSV format)
//        String flightFile = ".idea/flights.txt";
//        AirportFileWriter.writeFlightsToFile(flightFile, flights);
//
//        // Write airports to file for testing (using CSV format)
//        String airportFile = ".idea/airports.txt";
//        AirportFileWriter.writeAirportsToFile(airports, airportFile);
//
//        // Write admins to file for testing (using CSV format)
//        String adminFile = ".idea/admins.txt";
//        AirportFileWriter.writeAdminsToFile(admins, adminFile);
//
//        // Verify by reading the data back from the files
//        System.out.println("\n--- Verifying Data After Writing ---");
//        for (Admin admin : admins) {
//            System.out.println("Email: " + admin.getEmail());
//            System.out.println("Password: " + admin.getPassword());
//            System.out.println("---------------------------");
//        }
//        for (Airport airport : airports) {
//            System.out.println("Airport Code: " + airport.getAirportCode());
//            System.out.println("Airport Name: " + airport.getAirportName());
//            System.out.println("Airport Location: " + airport.getAirportLocation());
//            System.out.println("---------------------------");
//        }
//
//        System.out.println("\n--- Users and their Bookings ---");
//        for (User user : users) {
//            System.out.println("User Email: " + user.getEmail());  // Print user email
//            System.out.println("Bookings for " + user.getEmail() + ":");
//
//            for (Booking booking : user.getBookings()) {
//                System.out.println("\tBooking ID: " + booking.getBookingID());
//                System.out.println("\tFlight Number: " + booking.getFlightNum());
//                System.out.println("\tSpecial Services: ");
//                System.out.println("\t\tHas Wheelchair: " + booking.isHasPet());
//                System.out.println("\t\tHas Lounge: " + booking.isHasLounge());
//                System.out.println("\t\tHas Special Meal: " + booking.isHasSpecialMeal());
//                System.out.println("\t\tHas Pet: " + booking.isHasWheelchair());
//                System.out.println("\tNumber of Seats: " + booking.getNumberOfSeats());
//                System.out.println(" User info: " + booking.getCurrentUser().getEmail());
//                System.out.println("First class no: " + booking.getFirstClassSeats());
//                System.out.println("bus class no: " + booking.getBusinessSeats());
//                System.out.println("econ class no: " + booking.getEconomySeats());
//                System.out.println("flight det: " + booking.getFlightNumber().getArrivalAirport());
//
//                // Print each passenger for this booking
//                for (Passenger passenger : booking.getPassengers()) {
//                    System.out.println("\t\tPassenger ID: " + passenger.getPId());
//                    System.out.println("\t\tName: " + passenger.getName());
//                    System.out.println("\t\tSeat Number: " + passenger.getSeat().getSeatNumber());
//                    System.out.println("\t\tSeat Class: " + passenger.getSeat().getSeatClass());
//                    System.out.println("\t\tGender: " + passenger.getGender());
//                    System.out.println("\t\tPhone Number: " + passenger.getPhoneNumber());
//                    System.out.println("\t\tDate of Birth: " + passenger.getDateOfBirth());
//                }
//            }
//        }
//    }
//
//    // Helper method to create a Booking with Flight
//    public static Booking createBooking(Flight flight) {
//        // Create a new Booking instance
//        Booking booking = new Booking();  // Assuming the default constructor exists
//
//        // Set the flight manually using the appropriate setter
//        booking.setFlight(flight);  // Assuming there's a setFlight method
//
//        // Set other necessary values as you see fit
//        booking.setNumberOfSeats(10);
//        booking.setEconomySeats(5);
//        booking.setBusinessSeats(3);
//        booking.setFirstClassSeats(2);
//        booking.setHasWheelchair(false);
//        booking.setHasLounge(true);
//        booking.setHasSpecialMeal(true);
//        booking.setHasPet(false);
//        booking.setPassenger(new ArrayList<Passenger>());
//
//        return booking;
//    }


//    public class Main {
//        public static void main(String[] args) {
//
//            // Load airports from file when the application starts
//            Airport.loadAirportsFromFile(".idea/airport.txt");
//
//            // Add a new airport
//            Airport newAirport = new Airport("LAX", "Los Angeles International Airport", "Los Angeles, CA");
//            Airport.addAirport(newAirport);
//
//            // Save the updated list of airports back to the file
//            Airport.saveAirportsToFile(".idea/airport.txt");
//
//            // Optionally, you can print out the airports to check
//            for (Airport airport : Airport.getAirports()) {
//                System.out.println(airport);
//            }
//        }
//    }




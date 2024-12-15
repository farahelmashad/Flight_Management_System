package mainPackage;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class AirportFileHandler {

    public static ArrayList<Flight> readFlightsFromFile(String filename) {
        ArrayList<Flight> flights = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Split the data based on commas
                String[] data = line.split(",");
                if (data.length != 12) {
                    System.out.println("Error: Incorrect number of elements. Expected 12 but found " + data.length);
                    continue;
                }

                String flightID = data[0].trim();
                String departureAirport = data[1].trim();
                String arrivalAirport = data[2].trim();
                String departureDate = data[3].trim();
                String arrivalDate = data[4].trim();
                double price = Double.parseDouble(data[5].trim());
                String airline = data[6].trim();

                // Parsing the seat availability string (which is a sequence of 60 1's or 0's)
                String seatAvailabilityString = data[7].trim();
                boolean[] seatAvailability = new boolean[60];
                for (int i = 0; i < seatAvailabilityString.length(); i++) {
                    seatAvailability[i] = seatAvailabilityString.charAt(i) == '1';
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
//                System.out.println("Flight ID: " + flightID);
//                System.out.println("Departure Airport: " + departureAirport);
//                System.out.println("Arrival Airport: " + arrivalAirport);
//                System.out.println("Departure Date: " + departureDate);
//                System.out.println("Arrival Date: " + arrivalDate);
//                System.out.println("Price: " + price);
//                System.out.println("Airline: " + airline);
//                System.out.println("Seat Availability: " + Arrays.toString(seatAvailability));
//                System.out.println("Service 1: " + service1);
//                System.out.println("Service 2: " + service2);
//                System.out.println("Service 3: " + service3);
//                System.out.println("Service 4: " + service4);
//                System.out.println(); // Blank line between flights
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return flights;
    }
    public static ArrayList<User> readUsersFromFile(ArrayList<User> users, String filePath) {
      //ArrayList<User> users = new ArrayList<>();
        Gson gson = new Gson();

        try {
            // Read the JSON file
            FileReader reader = new FileReader(filePath);
            Type userListType = new TypeToken<List<UserJson>>() {}.getType();
            List<UserJson> userJsonList = gson.fromJson(reader, userListType);
            reader.close();

            // For each user in the list, create a User object and add bookings
            for (UserJson userJson : userJsonList) {
                // Create a new user object from JSON data
                User user = new User(userJson.email, userJson.password);

                // For each booking ID in the bookings list, create a new Booking object
                for (Integer bookingID : userJson.bookings) {  // Change to Integer, directly use integers
                    Booking booking = new Booking(bookingID);  // Create a booking with bookingID
                    user.addBooking(booking);
                       // Add the booking to the user's bookings list
                }

                // Add the user to the users list
                users.add(user);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return users;
    }

    // Helper class to map the JSON structure for User
    private static class UserJson {
        private String email;
        private String password;
        private ArrayList<Integer> bookings;  // Change this to ArrayList<Integer> for booking IDs as integers
    }
    public static void readBookingsFromFile(ArrayList<Booking> bookingData, ArrayList<User> users, String filePath) {
        Gson gson = new Gson();

        try {
            // Read the JSON file
            FileReader reader = new FileReader(filePath);
            Type bookingListType = new TypeToken<List<BookingJson>>() {}.getType();
            List<BookingJson> bookingJsonList = gson.fromJson(reader, bookingListType);
            reader.close();

            // For each booking in the JSON list
            int bookingIDCounter = 1;  // Start bookingID from 1
            for (BookingJson bookingJson : bookingJsonList) {
                bookingJson.bookingId = bookingIDCounter++;  // Increment bookingID for each booking

//                System.out.println("Processing Booking ID: " + bookingJson.bookingId);  // Debug: Show the booking being processed

                // Step 1: Search for the user based on the bookingID
                User user = null;
                for (User u : users) {
//                    System.out.println("Checking User: " + u.getEmail());  // Debug: Show user being checked
                    // Loop through each booking of the user
                    for (Booking userBooking : u.getBookings()) {
//                        System.out.println("User Booking ID: " + userBooking.getBookingID());  // Debug: Show user booking ID
                        if (userBooking.getBookingID() == bookingJson.bookingId) {
                            user = u;  // User found, break out of the loop
                            break;
                        }
                    }
                    if (user != null) {
                        break;  // Exit the outer loop once user is found
                    }
                }

                if (user == null) {
                    System.out.println("No matching user found for booking ID: " + bookingJson.bookingId);
                    continue;  // Skip this booking if no user is found
                }

                // Step 2: Check if the booking already exists for the user
                Booking existingBooking = null;
                for (Booking userBooking : user.getBookings()) {
                    if (userBooking.getBookingID() == bookingJson.bookingId) {
                        existingBooking = userBooking;
                        break;
                    }
                }

                // Step 3: Create a new booking or use the existing one
                Booking booking;
                if (existingBooking == null) {
                    // No existing booking, create a new one
                    booking = new Booking(
                            bookingJson.bookingId,
                            user, // Assign the found user to the booking
                            bookingJson.hasLounge,
                            bookingJson.hasSpecialMeal,
                            bookingJson.hasPet,
                            bookingJson.hasWheelchair,
                            bookingJson.numberOfSeats,
                            bookingJson.economySeats,
                            bookingJson.businessSeats,
                            bookingJson.firstClassSeats,
                            bookingJson.flightNumber
                    );
                    // Add the new booking to the user's list
                    user.addBooking(booking);
                } else {
                    // Use the existing booking and update its details
                    booking = existingBooking;
                    booking.setHasLounge(bookingJson.hasLounge);
                    booking.setHasSpecialMeal(bookingJson.hasSpecialMeal);
                    booking.setHasPet(bookingJson.hasPet);
                    booking.setHasWheelchair(bookingJson.hasWheelchair);
                    booking.setNumberOfSeats(bookingJson.numberOfSeats);
                    booking.setEconomySeats(bookingJson.economySeats);
                    booking.setBusinessSeats(bookingJson.businessSeats);
                    booking.setFirstClassSeats(bookingJson.firstClassSeats);
                    booking.setFlightNum(bookingJson.flightNumber);
                    booking.setCurrentUser(user);
                    booking.setBookingStatus("Confirmed");
                    booking.setFlight(booking.getFlightNumber());
                }

                // Step 4: Add passengers to the booking (either new or existing)
                int first=0,bus=0,econ=0;
                if (bookingJson.passengers != null) {
                    for (PassengerJson passengerJson : bookingJson.passengers) {
                        Seat seat = new Seat(
                                passengerJson.seat.seatNumber,
                                passengerJson.seat.seatClass
                        );
                        Passenger passenger = new Passenger(
                                passengerJson.name,
                                passengerJson.gender,
                                passengerJson.phoneNumber,
                                passengerJson.dateOfBirth,
                                passengerJson.PId,
                                seat
                        );
                        booking.addPassenger(passenger);  // Add the passenger to the booking
                        if(passengerJson.seat.seatClass.equalsIgnoreCase("First Class"))
                        {
                            first++;
                        }
                       else if(passengerJson.seat.seatClass.equalsIgnoreCase("Business Class"))
                        {
                            bus++;
                        } if(passengerJson.seat.seatClass.equalsIgnoreCase("Economy Class"))
                        {
                            econ++;
                        }
                    }
                    booking.setEconomySeats(econ);
                    booking.setBusinessSeats(bus);
                    booking.setFirstClassSeats(first);
                    booking.setNumberOfSeats(econ+bus+first);


                } else {
                    System.out.println("No passengers for booking ID: " + bookingJson.bookingId);
                }

                bookingData.add(booking);//msh lazem bas for double checking aal bookings aamtan

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // Helper class to map the JSON structure for Booking
    private static class BookingJson {
        @SerializedName("bookingId")
        private int bookingId;
        @SerializedName("flightNumber")
        private String flightNumber;
        private int numberOfSeats;
        private int economySeats;
        private int businessSeats;
        private int firstClassSeats;
        private List<PassengerJson> passengers = new ArrayList<>(); // Initialize the list to avoid null

        @SerializedName("hasWheelchair")
        private boolean hasWheelchair;
        @SerializedName("hasLounge")
        private boolean hasLounge;
        @SerializedName("hasSpecialMeal")
        private boolean hasSpecialMeal;
        @SerializedName("hasPet")
        private boolean hasPet;

        // Add getters and setters here if needed
    }

    // Helper class to map the JSON structure for Passenger
    private static class PassengerJson {
        private String name;
        private String gender;
        private int phoneNumber;
        private String dateOfBirth;
        private int PId;
        private SeatJson seat;

        // Add getters and setters here if needed
    }

    // Helper class to map the JSON structure for Seat
    private static class SeatJson {
        private String seatNumber;
        private String seatClass;

        // Add getters and setters here if needed
    }
    public static ArrayList<Airport> readAirportsFromFile(String fileName) {
        ArrayList<Airport> airports = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Split the line into parts
                String[] parts = line.split(",\\s*");
                if (parts.length == 3) {
                    // Create an Airport object and add it to the list
                    airports.add(new Airport(parts[0], parts[1], parts[2]));
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }
        return airports;
    }
    public static ArrayList<Admin> readAdminsFromFile(String filename) {
        ArrayList<Admin> admins = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) { // Ensure the format is correct
                    String email = parts[0].trim();
                    String password = parts[1].trim();
                    admins.add(new Admin(email, password));
                } else {
                    System.out.println("Invalid line format: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        return admins;
    }
}

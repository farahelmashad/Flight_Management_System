package mainPackage;

import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AirportFileWriter {

    private static final Gson gson = new Gson();

    // Write Flight data to a CSV file
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

    // Write Airport data to a CSV file
    public static void writeAirportsToFile(ArrayList<Airport> airports, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Airport airport : airports) {
                String airportData = String.join(",",
                        airport.getAirportCode(),
                        airport.getAirportName(),
                        airport.getAirportLocation()
                );
                writer.write(airportData);
                writer.newLine();
            }
            System.out.println("Successfully saved airports to file: " + filename);
        } catch (IOException e) {
            System.err.println("Error writing to airports file: " + e.getMessage());
        }
    }

    // Write Admin data to a CSV file
    public static void writeAdminsToFile(ArrayList<Admin> admins, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Admin admin : admins) {
                String adminData = admin.getEmail() + "," + admin.getPassword();
                writer.write(adminData);
                writer.newLine();
            }
            System.out.println("Successfully saved admins to file: " + filename);
        } catch (IOException e) {
            System.err.println("Error writing to admins file: " + e.getMessage());
        }
    }

    // Write User data to a JSON file using Gson
    public static void writeUsersToFile(ArrayList<User> users, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            List<UserJson> userJsonList = new ArrayList<>();

            // Iterate through each user
            for (User user : users) {
                // Get the list of bookings for the user
                List<Integer> bookingIds = new ArrayList<>();

                // Iterate through each booking and extract the booking ID
                for (Booking booking : user.getBookings()) {
                    bookingIds.add(booking.getBookingID());  // Add the booking ID to the list
                }

                // Create a UserJson object with the email, password, and list of booking IDs
                UserJson userJson = new UserJson(user.getEmail(), user.getPassword(), bookingIds);
                userJsonList.add(userJson);
            }

            // Convert the list of UserJson objects to JSON
            String json = gson.toJson(userJsonList);
            writer.write(json);
            System.out.println("Successfully saved users to file: " + filename);
        } catch (IOException e) {
            System.err.println("Error writing to users file: " + e.getMessage());
        }
    }


    // Write Booking data to a JSON file using Gson
    public static void writeBookingsToFile(ArrayList<Booking> bookings, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            List<BookingJson> bookingJsonList = new ArrayList<>();
            for (Booking booking : bookings) {
                // Ensure flight details are not null before accessing them
                Flight flight = booking.getFlightNumber();
                BookingJson bookingJson = new BookingJson(
                        booking.getBookingID(),
                        flight != null ? flight.getFlightNumber() : "No Flight", // Handle null flight
                        booking.getNumberOfSeats(),
                        booking.getEconomySeats(),
                        booking.getBusinessSeats(),
                        booking.getFirstClassSeats(),
                        booking.isHasWheelchair(),
                        booking.isHasLounge(),
                        booking.isHasSpecialMeal(),
                        booking.isHasPet(),
                        booking.getPassengers()
                );
                bookingJsonList.add(bookingJson);
            }

            String json = gson.toJson(bookingJsonList);
            writer.write(json);
            System.out.println("Successfully saved bookings to file: " + filename);
        } catch (IOException e) {
            System.err.println("Error writing to bookings file: " + e.getMessage());
        }
    }



    // Helper class to structure User JSON data
    private static class UserJson {
        private String email;
        private String password;
        private List<Integer> bookings;

        public UserJson(String email, String password, List<Integer> bookings) {
            this.email = email;
            this.password = password;
            this.bookings = bookings;
        }
    }

    // Helper class to structure Booking JSON data
    private static class BookingJson {
        private int bookingId;
        private String flightNumber;
        private int numberOfSeats;
        private int economySeats;
        private int businessSeats;
        private int firstClassSeats;
        private boolean hasWheelchair;
        private boolean hasLounge;
        private boolean hasSpecialMeal;
        private boolean hasPet;
        private List<PassengerJson> passengers;

        public BookingJson(int bookingId, String flightNumber, int numberOfSeats, int economySeats,
                           int businessSeats, int firstClassSeats, boolean hasWheelchair,
                           boolean hasLounge, boolean hasSpecialMeal, boolean hasPet,
                           List<PassengerJson> passengers) {
            this.bookingId = bookingId;
            this.flightNumber = flightNumber;
            this.numberOfSeats = numberOfSeats;
            this.economySeats = economySeats;
            this.businessSeats = businessSeats;
            this.firstClassSeats = firstClassSeats;
            this.hasWheelchair = hasWheelchair;
            this.hasLounge = hasLounge;
            this.hasSpecialMeal = hasSpecialMeal;
            this.hasPet = hasPet;
            this.passengers = passengers;
        }

        public BookingJson(int bookingID, String flightNum, int numberOfSeats, int economySeats, int businessSeats, int firstClassSeats, boolean haswheelchair, boolean hasLoungeAccess, boolean specialMealRequest, boolean hasPetTravel, ArrayList<Passenger> passengers) {
        }
    }

    // Helper class to structure Passenger JSON data
    private static class PassengerJson {
        private String name;
        private String gender;
        private String phoneNumber;
        private String dateOfBirth;
        private String PId;
        private String seatNumber;
        private String seatClass;

        public PassengerJson(String name, String gender, String phoneNumber, String dateOfBirth, String PId,
                             String seatNumber, String seatClass) {
            this.name = name;
            this.gender = gender;
            this.phoneNumber = phoneNumber;
            this.dateOfBirth = dateOfBirth;
            this.PId = PId;
            this.seatNumber = seatNumber;
            this.seatClass = seatClass;
        }
    }
}

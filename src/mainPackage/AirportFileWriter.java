package mainPackage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AirportFileWriter {

    private static void writeToFile(String filename, List<String> data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (String line : data) {
                writer.write(line);
                writer.newLine();
            }
            System.out.println("Successfully saved data to: " + filename);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
    public static void writeFlightsToFile() {
        ArrayList<Flight> flights = GlobalData.flights;

        if (flights.isEmpty()) {
            System.out.println("No flights available to save.");
            return;
        }

        List<String> flightDataList = new ArrayList<>();
        for (Flight flight : flights) {
            if (flight == null) {
                continue;
            }

            StringBuilder sb = new StringBuilder();
            sb.append(flight.getFlightNumber()).append("\t")
                    .append(flight.getDepartureAirport()).append("\t")
                    .append(flight.getArrivalAirport()).append("\t")
                    .append(flight.getDepartureTime()).append("\t")
                    .append(flight.getArrivalTime()).append("\t")
                    .append(flight.getBaseFare()).append("\t")
                    .append(flight.getAirlineName()).append("\t");

            boolean[] seatAvailability = flight.getSeatAvailability();
            StringBuilder seatAvailabilityString = new StringBuilder();
            for (boolean seat : seatAvailability) {
                seatAvailabilityString.append(seat ? "1" : "0");
            }
            sb.append(seatAvailabilityString).append("\t")
                    .append(flight.getSpecialMealRequest()).append("\t")
                    .append(flight.getHasPetTravel()).append("\t")
                    .append(flight.getHasLoungeAccess()).append("\t")
                    .append(flight.getHaswheelchair());

            flightDataList.add(sb.toString());
        }

        writeToFile(".idea/flights.txt", flightDataList);
    }

    public static void writeAirportsToFile() {
        ArrayList<Airport> airports = GlobalData.airports;

        if (airports.isEmpty()) {
            System.out.println("No airports available to save.");
            return;
        }

        List<String> airportDataList = new ArrayList<>();
        for (Airport airport : airports) {
            String airportData = airport.getAirportCode() + "\t"
                    + airport.getAirportName() + "\t"
                    + airport.getAirportLocation();
            airportDataList.add(airportData);
        }

        writeToFile(".idea/airport.txt", airportDataList);
    }

    public static void writeUsersToFile() {
        ArrayList<User> users = GlobalData.users;

        if (users.isEmpty()) {
            System.out.println("No users available to save.");
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(".idea/users.JSON"))) {
            List<UserJson> userJsonList = new ArrayList<>();
            for (User user : users) {
                List<Integer> bookingIds = new ArrayList<>();
                for (Booking booking : user.getBookings()) {
                    bookingIds.add(booking.getBookingID());
                }

                UserJson userJson = new UserJson(user.getEmail(), user.getPassword(), bookingIds);
                userJsonList.add(userJson);
            }

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(userJsonList, writer);
            System.out.println("Users successfully saved to users file");
        } catch (IOException e) {
            System.err.println("Error writing to users file: " + e.getMessage());
        }
    }

    public static void writeBookingsToFile() {
        ArrayList<Booking> bookings = GlobalData.bookings;

        if (bookings.isEmpty()) {
            System.out.println("No bookings available to save.");
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(".idea/bookings.JSON"))) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            List<BookingJson> bookingJsonList = new ArrayList<>();
            for (Booking booking : bookings) {
                Flight flight = booking.getFlightNumber();
                BookingJson bookingJson = new BookingJson(
                        booking.getBookingID(),
                        flight != null ? flight.getFlightNumber() : "No Flight",
                        booking.getNumberOfSeats(),
                        booking.getEconomySeats(),
                        booking.getBusinessSeats(),
                        booking.getFirstClassSeats(),
                        booking.isHasWheelchair(),
                        booking.isHasLounge(),
                        booking.isHasSpecialMeal(),
                        booking.isHasPet()
                );

                for (Passenger passenger : booking.getPassengers()) {
                    PassengerJson passengerJson = new PassengerJson();
                    passengerJson.name = passenger.getName();
                    passengerJson.gender = passenger.getGender();
                    passengerJson.phoneNumber = passenger.getPhoneNumber();
                    passengerJson.dateOfBirth = passenger.getDateOfBirth();
                    passengerJson.PId = passenger.getPId();
                    passengerJson.seat = new SeatJson(passenger.getSeat().getSeatNumber(), passenger.getSeat().getSeatClass());

                    bookingJson.passengers.add(passengerJson);
                }

                bookingJsonList.add(bookingJson);
            }

            gson.toJson(bookingJsonList, writer);
            System.out.println("Bookings successfully saved to booking file ");
        } catch (IOException e) {
            System.err.println("Error writing to bookings file: " + e.getMessage());
        }
    }

    // Helper class to map the JSON structure for User
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
    private static class BookingJson {
        @SerializedName("bookingId")
        private int bookingId;
        @SerializedName("flightNumber")
        private String flightNumber;
        private int numberOfSeats;
        private int economySeats;
        private int businessSeats;
        private int firstClassSeats;
        private boolean hasWheelchair;
        private boolean hasLounge;
        private boolean hasSpecialMeal;
        private boolean hasPet;
        private List<PassengerJson> passengers = new ArrayList<>();

        public BookingJson(int bookingId, String flightNumber, int numberOfSeats, int economySeats, int businessSeats, int firstClassSeats, boolean hasWheelchair, boolean hasLounge, boolean hasSpecialMeal, boolean hasPet) {
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
        }
    }


    private static class PassengerJson {
        private String name;
        private String gender;
        private int phoneNumber;
        private String dateOfBirth;
        private int PId;
        private SeatJson seat;
    }

    private static class SeatJson {
        private String seatNumber;
        private String seatClass;

        public SeatJson(String seatNumber, String seatClass) {
            this.seatNumber = seatNumber;
            this.seatClass = seatClass;
        }
    }
}

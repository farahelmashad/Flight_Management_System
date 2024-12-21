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
                String[] data = line.split(",");
                if (data.length != 12) {
                    continue;
                }

                String flightID = data[0].trim();
                String departureAirport = data[1].trim();
                String arrivalAirport = data[2].trim();
                String departureDate = data[3].trim();
                String arrivalDate = data[4].trim();
                double price = Double.parseDouble(data[5].trim());
                String airline = data[6].trim();

                String seatAvailabilityString = data[7].trim();
                boolean[] seatAvailability = new boolean[60];
                for (int i = 0; i < seatAvailabilityString.length(); i++) {
                    seatAvailability[i] = seatAvailabilityString.charAt(i) == '1';
                }

                boolean service1 = Boolean.parseBoolean(data[8].trim());
                boolean service2 = Boolean.parseBoolean(data[9].trim());
                boolean service3 = Boolean.parseBoolean(data[10].trim());
                boolean service4 = Boolean.parseBoolean(data[11].trim());

                Flight flight = new Flight(flightID, departureAirport, arrivalAirport, departureDate, arrivalDate,
                        price, airline, seatAvailability, service1, service2, service3, service4);
                flights.add(flight);

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
            FileReader reader = new FileReader(filePath);
            Type userListType = new TypeToken<List<UserJson>>() {}.getType();
            List<UserJson> userJsonList = gson.fromJson(reader, userListType);
            reader.close();

            for (UserJson userJson : userJsonList) {
                User user = new User(userJson.email, userJson.password);

                for (Integer bookingID : userJson.bookings) {
                    Booking booking = new Booking(bookingID);
                    user.addBooking(booking);
                }

                users.add(user);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return users;
    }

    private static class UserJson {
        private String email;
        private String password;
        private ArrayList<Integer> bookings;
    }
    public static void readBookingsFromFile(ArrayList<Booking> bookingData, ArrayList<User> users, String filePath) {
        Gson gson = new Gson();

        try {
            FileReader reader = new FileReader(filePath);
            Type bookingListType = new TypeToken<List<BookingJson>>() {}.getType();
            List<BookingJson> bookingJsonList = gson.fromJson(reader, bookingListType);
            reader.close();

            int bookingIDCounter = 1;
            for (BookingJson bookingJson : bookingJsonList) {
                bookingJson.bookingId = bookingIDCounter++;

                User user = null;
                for (User u : users) {
                    for (Booking userBooking : u.getBookings()) {
                        if (userBooking.getBookingID() == bookingJson.bookingId) {
                            user = u;
                            break;
                        }
                    }
                    if (user != null) {
                        break;
                    }
                }

                if (user == null) {
                    System.out.println("No matching user found for booking ID: " + bookingJson.bookingId);
                    continue;
                }

                Booking existingBooking = null;
                for (Booking userBooking : user.getBookings()) {
                    if (userBooking.getBookingID() == bookingJson.bookingId) {
                        existingBooking = userBooking;
                        break;
                    }
                }

                Booking booking;
                if (existingBooking == null) {
                    booking = new Booking(
                            bookingJson.bookingId,
                            user,
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
                    user.addBooking(booking);
                } else {
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
                        booking.addPassenger(passenger);
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


    private static class BookingJson {
        @SerializedName("bookingId")
        private int bookingId;
        @SerializedName("flightNumber")
        private String flightNumber;
        private int numberOfSeats;
        private int economySeats;
        private int businessSeats;
        private int firstClassSeats;
        private List<PassengerJson> passengers = new ArrayList<>();

        @SerializedName("hasWheelchair")
        private boolean hasWheelchair;
        @SerializedName("hasLounge")
        private boolean hasLounge;
        @SerializedName("hasSpecialMeal")
        private boolean hasSpecialMeal;
        @SerializedName("hasPet")
        private boolean hasPet;

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

    }
    public static ArrayList<Airport> readAirportsFromFile(String fileName) {
        ArrayList<Airport> airports = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",\\s*");
                if (parts.length == 3) {
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
                if (parts.length == 2) {
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

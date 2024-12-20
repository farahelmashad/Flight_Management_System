package mainPackage;

import java.util.ArrayList;

public class GlobalData {
    public static ArrayList<User> users = new ArrayList<>();
    public static ArrayList<Booking> bookings = new ArrayList<>();
    public static ArrayList<Flight>flights=new ArrayList<>();
    public static ArrayList<Airport> airports=new ArrayList<>();
    public static ArrayList<Admin> admins=new ArrayList<>();
    public static void loadData(String usersFile, String bookingsFile,String flightFile,String airplaneFile,String adminFile) {
        flights=AirportFileHandler.readFlightsFromFile(flightFile);

        AirportFileHandler.readUsersFromFile(users, usersFile);

        AirportFileHandler.readBookingsFromFile(bookings, users, bookingsFile);
        airports=AirportFileHandler.readAirportsFromFile(airplaneFile);
        admins=AirportFileHandler.readAdminsFromFile(adminFile);
    }
}

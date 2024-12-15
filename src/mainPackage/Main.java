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
        ArrayList<Booking> bookings = GlobalData.bookings;
        SignUp_LogIn S = new SignUp_LogIn();
        S.MainMenu();
    }
}







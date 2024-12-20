package mainPackage;

import java.util.ArrayList;

public class Main {

public static void main(String[] args) {
    // Initialize ArrayLists
    GlobalData.loadData(".idea/users.JSON",".idea/bookings.JSON",".idea/flights.txt",".idea/airport.txt",".idea/admins.txt");
    ArrayList<User>users=GlobalData.users;
    ArrayList<Flight>flights=GlobalData.flights;
    ArrayList<Airport>airports=GlobalData.airports;
    ArrayList<Admin>admins=GlobalData.admins;
    ArrayList<Booking>bookings=GlobalData.bookings;
//    for (Admin admin : admins) {
//        System.out.println("Email: " + admin.getEmail());
//        System.out.println("Password: " + admin.getPassword());
//        System.out.println("---------------------------");
//    }
//    for (Airport airport : airports) {
//        System.out.println("Airport Code: " + airport.getAirportCode());
//        System.out.println("Airport Name: " + airport.getAirportName());
//        System.out.println("Airport Location: " + airport.getAirportLocation());
//        System.out.println("---------------------------");
//    }
//
//    System.out.println("\n--- Users and their Bookings ---");
//    for (User user : users) {
//        System.out.println("User Email: " + user.getEmail());  // Print user email
//        System.out.println("Bookings for " + user.getEmail() + ":");
//
//        for (Booking booking : user.getBookings()) {
//            System.out.println("\tBooking ID: " + booking.getBookingID());
//            System.out.println("\tFlight Number: " + booking.getFlightNum());
//            System.out.println("\tSpecial Services: ");
//            System.out.println("\t\tHas Wheelchair: " + booking.isHasPet());
//            System.out.println("\t\tHas Lounge: " + booking.isHasLounge());
//            System.out.println("\t\tHas Special Meal: " + booking.isHasSpecialMeal());
//            System.out.println("\t\tHas Pet: " + booking.isHasWheelchair());
//            System.out.println("\tNumber of Seats: " + booking.getNumberOfSeats());
//            System.out.println(" User info: "+booking.getCurrentUser().getEmail());
//            System.out.println("First class no: "+booking.getFirstClassSeats());
//            System.out.println("bus class no: "+booking.getBusinessSeats());
//            System.out.println("econ class no: "+booking.getEconomySeats());
//            System.out.println("flight det: "+booking.getFlightNumber().getArrivalAirport()); //aayza arga3 llpart da
//
//            // Print each passenger for this booking
//            for (Passenger passenger : booking.getPassengers()) {
//                System.out.println("\t\tPassenger ID: " + passenger.getPId());
//                System.out.println("\t\tName: " + passenger.getName());
//                System.out.println("\t\tSeat Number: " + passenger.getSeat().getSeatNumber());
//                System.out.println("\t\tSeat Class: " + passenger.getSeat().getSeatClass());
//                System.out.println("\t\tGender: " + passenger.getGender());
//                System.out.println("\t\tPhone Number: " + passenger.getPhoneNumber());
//                System.out.println("\t\tDate of Birth: " + passenger.getDateOfBirth());
//            }
//        }
//    }
//
//cairo international airport

          Booking booking=new Booking();
          booking.flightSearch();

    }
}





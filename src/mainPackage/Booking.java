package mainPackage;

import java.util.ArrayList;
import java.util.Scanner;

public class Booking {
    private int bookingID;
    private Passenger passenger;
    private Flight flight;
    private Seat seat;
    private String bookingStatus ;

    private static ArrayList<Booking> bookings = new ArrayList<>();

    public Booking(int bookingID, Passenger passenger, Flight flight, Seat seat, String bookingStatus){
        this.bookingID = bookingID;
        this.passenger = passenger;
        this.flight = flight;
        this.seat = seat;
        this.bookingStatus = "Confirmed";


        if(seat.isAvailable()){
            seat.setAvailable(false);
        }else{
            System.out.println("Sorry! Seat is already booked");
        }
    }

    public int getBookingID(){
        return bookingID;
    }

    public void setBookingID(int bookingID) {
        this.bookingID = bookingID;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public String getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(String bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public static ArrayList<Booking> getBookings() {
        return bookings;
    }
    public static void listAllBookings(){
        if(bookings.isEmpty()){
            System.out.println("No bookings exist");

        }
        System.out.println("All Bookings: ");
        for(Booking b : bookings){
            b.BookingConfirmation();
        }

    }


    public void ManageBooking(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Manage Booking Options: ");
        System.out.println("1. Cancel Booking");
        System.out.println("2. Change Seat");
        System.out.println("3. Exit");
        System.out.println("Please Select an option. ");
        int choice = scanner.nextInt();
        switch(choice){
            case 1:
                cancelBooking();
                break;
            case 2:
                changeSeat();
                break;
            case 3:
                System.out.println("Exiting booking management...");
                break;
            case 4:
                System.out.println("Invalid choice ! Please try again.");
        }
    }
    public static Booking FindBookingById(int bookingID) {
        for (Booking b : bookings) {
            if (b.getBookingID() == bookingID) {
                return b;
            }
        }
        System.out.println("Booking not found with ID: " + bookingID);
        return null;
    }
    private void cancelBooking(){
        if(bookingStatus.equals("Cancelled")){
            System.out.println("Your Booking is already cancelled");
        }else{
            bookingStatus="Cancelled";
            seat.setAvailable(true);
            System.out.println("Your booking has been cancelled successfully.");
        }
    }

    private void changeSeat(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Available Seats: ");
        for(Seat s : flight.getSeats()){
            if(s.isAvailable()){
                System.out.println(s.getSeatNumber() + " ");
            }
        }
        System.out.println("Enter the seat number you want to switch to : ");
        String NewSeatNum = scanner.next();
        for(Seat s : flight.getSeats() ){
            if(s.getSeatNumber().equalsIgnoreCase(NewSeatNum) && s.isAvailable()){
                seat.setAvailable(true); // ba5aly el seat el adeem available
                s.setAvailable(false); // el seat el gdeed not available
                this.seat=s;
                System.out.println("Seat successfully changed to : " + s.getSeatNumber());
                return;
            }
        }
        System.out.println("Seat is already booked.");
    }

    public void BookingConfirmation(){
        System.out.println("Booking Details: ");
        System.out.println("Booking ID : " + bookingID);
        System.out.println("Passenger Name : " + passenger.getName());
        System.out.println("Flight Number : " + flight.getFlightNumber());
        System.out.println("Departure : " + flight.getDepartureAirport());
        System.out.println("Arrival : " + flight.getArrivalAirport());
        System.out.println("Departure Time : " + flight.getDepartureTime());
        System.out.println("Arrival Time : " + flight.getArrivalTime());
        System.out.println("Seat Assigned : " + seat.getSeatNumber());
        System.out.println("Booking status : " + bookingStatus);
    }
}

package mainPackage;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Admin extends User {
   public  List<Flight> flights;
    Scanner input;
    public Admin() {
        this.input = new Scanner(System.in);
        this.flights = new ArrayList<>();
    };
    public Admin(List<Flight> flights) {
        if (flights == null)
        { // Check if flights is null
            throw new IllegalArgumentException("Flights list cannot be null.");
        }
        this.flights = flights;
        this.input = new Scanner(System.in);

    }
    public Admin(String email, String password) {
        super(email, password);
        this.input = new Scanner(System.in);
        this.flights = new ArrayList<>();
    }

    private Flight findFlightByNumber(String flightNumber) {
        for (Flight flight : flights) {
            if (flight.getFlightNumber().equalsIgnoreCase(flightNumber)) {
                return flight;
            }
        }
        return null; // Flight not found
    }

    private void Addflight() {
        System.out.println("enter flight number");
        String FlightNumber = input.next();
        input.nextLine();
        while (findFlightByNumber(FlightNumber) != null) {
            System.out.println("Flight number already exists. Please enter a unique flight number.");
            System.out.println("Enter Flight Number: ");
            FlightNumber = input.next();
            input.nextLine();
        }

        System.out.println("enter DepartureAirport: ");
        String DepartureAirport = input.nextLine().trim();

        System.out.println("enter ArrivalAirport: ");
        String ArrivalAirport = input.nextLine().trim();
        System.out.println("enter  ArrivalTime: ");
        String ArrivalTime = input.nextLine().trim();
        System.out.println("enter DepartureTime: ");
        String DepartureTime = input.nextLine().trim();
        System.out.println("enter BaseFare: ");
        Double BaseFare = input.nextDouble();
        System.out.println("Enter Airline Code: ");
        String AirlineCode=input.next().trim();
        boolean []availability=new boolean[60];
        Flight flight = new Flight(FlightNumber, DepartureAirport, ArrivalAirport, ArrivalTime, DepartureTime, BaseFare,AirlineCode,availability);
        flights.add(flight);
        System.out.println("flight " + FlightNumber + " added successfully");
    }

    private void UpdateFlightschedule() {
        System.out.println("enter flight number");
        String FlightNumber = input.next();
        input.nextLine();
        Flight flight = findFlightByNumber(FlightNumber);
        while (flight == null) {
            System.out.println("Flight not found. Please enter flight number again");
            FlightNumber = input.nextLine().trim();
            flight = findFlightByNumber(FlightNumber);
        }
        System.out.println("enter new departure time:");
        String DepartureTime = input.nextLine().trim();
        flight.setDepartureTime(DepartureTime);
        System.out.println("enter new arrival time:");
        String ArrivalTime = input.nextLine().trim();
        flight.setArrivalTime(ArrivalTime);
        System.out.println("Flight " + FlightNumber + " updated successfully");


    }

    private void Cancelsaetbooking() {
        System.out.print("Enter Flight Number: ");
        String flightNumber = input.next();
        input.nextLine();

        Flight flight = findFlightByNumber(flightNumber);
        while (flight == null) {
            System.out.println("Flight not found. Please enter another flight number.");
            flightNumber = input.nextLine().trim();
            flight = findFlightByNumber(flightNumber);
        }

        System.out.print("Enter Seat Number to cancel booking: ");
        int SeatNumber = input.nextInt();
        input.nextLine();
        while (SeatNumber <= 0 || SeatNumber > flight.getSeats().size()) {
            System.out.println("Invalid seat number. Please try again.");
            SeatNumber = input.nextInt();
            input.nextLine();
        }

        Seat seat = flight.getSeats().get(SeatNumber - 1);
        if (seat.isAvailable()) {
            System.out.println("Seat is not currently booked.");
        } else {
            System.out.println("Canceling booking for seat: " + seat.getSeatNumber());
            seat.cancelBooking();
            System.out.println("Booking canceled successfully for seat number: " + seat.getSeatNumber());
        }
    }

    private void DisplayAvailbleSeats() {
        System.out.print("Enter Flight Number: ");
        String FlightNumber = input.nextLine().trim();

        Flight flight = findFlightByNumber(FlightNumber);
        while (flight == null) {
            System.out.println("Flight not found. Please enter another flight number.");
            FlightNumber = input.nextLine().trim();
        }
        for (Seat seat : flight.getSeats()) {
            System.out.println("Seat " + seat.getSeatNumber() + ": " +
                    (seat.isAvailable() ? "Available" : "Booked"));
        }
    }

    private void Deleteflight() {
        System.out.println("enter the number of the flight you want to delete: ");
        String FlightNumber = input.nextLine().trim();
        Flight flight = findFlightByNumber(FlightNumber);
        while ((flight == null)) {
            System.out.println("Flight not found. Please enter another flight number.");
            FlightNumber = input.nextLine().trim();
        }
        flights.remove(flight);
        System.out.println("Flight with Flight Number " + FlightNumber + " has been deleted successfully.");
    }
    private void Displayflights()
      {
          if (flights.isEmpty()) {
              System.out.println("No flights available.");
              return;}
      for(Flight flight:flights)
      {
          flight.displayFlightDetails();
          System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
      }
      }
    private void FindFlight() {
        System.out.println("Enter the flight number to search: ");

        String flightNumber = input.nextLine().trim(); // Read the flight number and trim any extra spaces

        Flight flight = findFlightByNumber(flightNumber);

        while (flight == null) {
            System.out.println("Flight not found. Please enter flight number again");
            flightNumber = input.nextLine().trim();
            flight = findFlightByNumber(flightNumber);
        }
        System.out.println("Flight found! Here are the flight  details:");
        flight.displayFlightDetails();
    }

    public void Adminpanel()
    {

        boolean running = true;
        while (running) {
            System.out.println("\nAdmin Panel:");
            System.out.println("1. Add Flight");
            System.out.println("2. Update Flight schedule");
            System.out.println("3. Display available seats for certain flight");
            System.out.println("4. Delete Flight");
            System.out.println("5. Cancel Seat Booking");
            System.out.println("6:Display all flights");
            System.out.println("7:Display  a certain flight");
            System.out.println("8. Exit");  //hnghirha w nkhliha lal mainmenu
            System.out.print("Enter your choice: ");


            int choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1:
                    this.Addflight();
                    break;
                case 2:
                    this.UpdateFlightschedule();
                    break;
                case 3:
                    this.DisplayAvailbleSeats();
                    break;
                case 4:
                    this.Deleteflight();
                    break;
                case 5:
                    this.Cancelsaetbooking();
                    break;
                case 6:
                    this.Displayflights();
                    break;
                case 7:
                    this.FindFlight();
                    break;

                case 8: {
                    running = false;
                    System.out.println("Exiting Admin Panel. Goodbye!");
                    break;
                }
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}



















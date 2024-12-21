package mainPackage;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import static mainPackage.GlobalData.users;

public class Admin extends User {
    public  ArrayList<Flight> flights=GlobalData.flights;
    Scanner input;
    public Admin() {
        this.input = new Scanner(System.in);
//        this.flights = new ArrayList<>();
    }
    public Admin(ArrayList<Flight> flights) {
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
        // this.flights = new ArrayList<>();
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

        try{
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
            Date departureDate, arrivalDate;
            while (true) {
                System.out.println("Enter Departure Time (dd-MM-yyyy HH:mm): ");
                String DepartureTime = input.nextLine().trim();

                System.out.println("Enter Arrival Time (dd-MM-yyyy HH:mm): ");
                String ArrivalTime = input.nextLine().trim();

                try {
                    departureDate = Flight.dateFormat.parse(DepartureTime);
                    arrivalDate = Flight.dateFormat.parse(ArrivalTime);

                    if (arrivalDate.before(departureDate)) {
                        System.out.println("Arrival time cannot be earlier than departure time. Please enter the dates again.");
                    } else {
                        break; // Dates are valid
                    }
                } catch (ParseException e) {
                    System.out.println("Invalid date format. Please use dd-MM-yyyy HH:mm.");
                }
            }

            System.out.println("enter BaseFare: ");
            Double BaseFare = input.nextDouble();
            System.out.println("Enter Airline Code: ");
            String AirlineCode=input.next().trim();
            boolean []availability=new boolean[60];
            System.out.println("Does this flight include lounge access? (yes/no)");
            boolean hasLounge;
            String answer=input.nextLine().trim();
            if(answer.equalsIgnoreCase("yes")){
                hasLounge=true;
            }
            else
                hasLounge=false;
            System.out.println("Does this flight include wheelchair access? (yes/no)");
            boolean hasWheel;
            answer=input.nextLine().trim();
            if(answer.equalsIgnoreCase("yes")){
                hasWheel=true;
            }
            else
                hasWheel=false;
            System.out.println("Does this flight include pet access? (yes/no)");
            boolean hasPet;
            answer=input.nextLine();
            if(answer.equalsIgnoreCase("yes")){
                hasPet=true;
            }
            else
                hasPet=false;
            System.out.println("Does this flight include special meal requests? (yes/no)");
            boolean hasMeal;
            answer=input.nextLine();
            if(answer.equalsIgnoreCase("yes")){
                hasMeal=true;
            }
            else
                hasMeal=false;


            Flight flight = new Flight(FlightNumber, DepartureAirport, ArrivalAirport,Flight.dateFormat.format(arrivalDate), Flight.dateFormat.format(departureDate), BaseFare,AirlineCode,availability,hasLounge,hasWheel,hasPet,hasMeal);
            flights.add(flight);
            AirportFileWriter.writeFlightsToFile();
            System.out.println("flight " + FlightNumber + " added successfully");
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }


    private void UpdateFlightschedule() {
        try {
            System.out.println("enter flight number");
            String FlightNumber = input.next();
            input.nextLine();
            Flight flight = findFlightByNumber(FlightNumber);
            while (flight == null) {
                System.out.println("Flight not found. Please enter flight number again");
                FlightNumber = input.nextLine().trim();
                flight = findFlightByNumber(FlightNumber);
            }
            Date departureDate, arrivalDate;
            while (true) {
                System.out.println("Enter new Departure Time (dd-MM-yyyy HH:mm):");
                String DepartureTime = input.nextLine().trim();

                System.out.println("Enter new Arrival Time (dd-MM-yyyy HH:mm):");
                String ArrivalTime = input.nextLine().trim();

                try {
                    departureDate = Flight.dateFormat.parse(DepartureTime);
                    arrivalDate = Flight.dateFormat.parse(ArrivalTime);

                    if (arrivalDate.before(departureDate)) {
                        System.out.println("Arrival time cannot be earlier than departure time. Please enter the dates again.");
                    } else {
                        break; // Dates are valid
                    }
                } catch (ParseException e) {
                    System.out.println("Invalid date format. Please use dd-MM-yyyy HH:mm.");
                }
            }
            flight.setDepartureTime(Flight.dateFormat.format(departureDate));
            flight.setArrivalTime(Flight.dateFormat.format(arrivalDate));
            AirportFileWriter.writeFlightsToFile();
            System.out.println("Flight " + FlightNumber + " updated successfully!");

        }
        catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }



    private void Cancelsaetbooking() {

        System.out.print("Enter Flight Number: ");
        String flightNumber = input.nextLine().trim();

        // Find the flight by number
        Flight flight = findFlightByNumber(flightNumber);
        while (flight == null) {
            System.out.println("Flight not found. Please enter another flight number.");
            flightNumber = input.nextLine().trim();
            flight = findFlightByNumber(flightNumber);
        }

        // Ask for the seat number to cancel booking
        System.out.print("Enter Seat Number to cancel booking: ");
        String seatNumber = input.nextLine().trim();

        // Check if the seat number is valid (present in the flight's seat list)
        Seat seat = findSeatByNumber(flight, seatNumber);
        while (seat == null) {
            System.out.println("Invalid seat number. Please try again.");
            seatNumber = input.nextLine().trim();
            seat = findSeatByNumber(flight, seatNumber);
        }

        // If the seat is already available, we can't cancel the booking
        if (seat.isAvailable()) {
            System.out.println("Seat " + seatNumber + " is not currently booked.");
        } else {
            // Proceed to cancel the booking
            System.out.println("Canceling booking for seat: " + seat.getSeatNumber());
            seat.cancelBooking();
            AirportFileWriter.writeBookingsToFile();
            System.out.println("Booking canceled successfully for seat number: " + seat.getSeatNumber());
        }
    }

    private Seat findSeatByNumber(Flight flight, String seatNumber) {
        for (Seat seat : flight.getSeats()) {
            if (seat.getSeatNumber().equals(seatNumber)) {
                return seat;
            }
        }
        return null; // Seat not found
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
        System.out.println("Enter the number of the flight you want to delete: ");
        String FlightNumber = input.nextLine().trim();
        Flight flight = findFlightByNumber(FlightNumber);
        while (flight == null) {
            System.out.println("Flight not found. Please enter another flight number.");
            FlightNumber = input.nextLine().trim();
            flight = findFlightByNumber(FlightNumber);
        }

        // Remove the flight
        flights.remove(flight);
        for (User user : users) {
            // Remove all bookings associated with the flight
            List<Booking> bookingsToRemove = new ArrayList<>();
            for (Booking booking : user.getBookings()) { // Assuming Booking.getBookings() retrieves all bookings
                if (booking.getFlight().equals(flight)) {
                    bookingsToRemove.add(booking);
                }
            }
            user.getBookings().removeAll(bookingsToRemove);

            System.out.println("Flight with Flight Number " + FlightNumber + " and all associated bookings have been deleted successfully.");
        }
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
        SignUp_LogIn s=new SignUp_LogIn();
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
                    s.MainMenu();
                    break;
                }
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
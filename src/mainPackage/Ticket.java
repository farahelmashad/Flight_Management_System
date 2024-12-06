import java.util.*;
import java.text.SimpleDateFormat;
public class Ticket {
    private String ticketNumber;
    private double fare;
    private String ticketStatus;
    private Date issueDate;
    private Passenger passenger;
    private Flight flight;

    public Ticket(String ticketNumber, Payment payment, Date issueDate, String ticketStatus, Passenger passenger, Flight flight) {
        this.ticketNumber = ticketNumber;
        this.fare = payment.getPaymentAmount();
        this.issueDate = issueDate;
        this.ticketStatus = ticketStatus;
        this.passenger = passenger;
        this.flight = flight;
    }

    public String getTicketNumber() {
        return ticketNumber;
    }

    public double getFare() {
        return fare;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public String getTicketStatus() {
        return ticketStatus;
    }

    public void setTicketStatus(String ticketStatus) {
        this.ticketStatus = ticketStatus;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public Flight getFlight() {
        return flight;
    }

    public void displayTicketInfo() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("--- Ticket Information ---");
        System.out.println("Ticket Number: " + ticketNumber);
        System.out.println("Fare: $" + fare);
        System.out.println("Ticket Status: " + ticketStatus);
        System.out.println("Issue Date: " + dateFormat.format(issueDate));

        System.out.println("\n--- Passenger Information ---");
        System.out.println("Name: " + passenger.getName());
        System.out.println("Phone: " + passenger.getPhoneNumber());
        System.out.println("Passenger ID: " + passenger.getPId());

        System.out.println("\n--- Flight Information ---");
        System.out.println("Flight Number: " + flight.getFlightNumber());
        System.out.println("Departure Airport: " + flight.getDepartureAirport());
        System.out.println("Arrival Airport: " + flight.getArrivalAirport());
        System.out.println("Departure Time: " + flight.getDepartureTime());
        System.out.println("Arrival Time: " + flight.getArrivalTime());
    }

    @Override
    public String toString() {
        return "Ticket [ticketNumber=" + ticketNumber + ", fare=" + fare + ", ticketStatus=" + ticketStatus +
                ", issueDate=" + issueDate + ", passenger=" + passenger.getName() +
                ", flight=" + flight.getFlightNumber() + "]";
    }
}


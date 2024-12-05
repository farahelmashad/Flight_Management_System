package mainPackage;

import java.util.ArrayList;
import java.util.List;

public class Flight {
  private String FlightNumber;
  private String DepartureAirport;
  private String ArrivalAirport;
  private String ArrivalTime;
  private String DepartureTime;
  private double BaseFare;
  public List<Seat> Seats;

  //constructor
  public Flight(String flightNumber, String departureAirport, String arrivalAirport, String arrivalTime, String departureTime, double baseFare ) {
   this.FlightNumber = flightNumber;
   this.DepartureAirport = departureAirport;
   this.ArrivalAirport = arrivalAirport;
   this.ArrivalTime = arrivalTime;
   this.DepartureTime = departureTime;
   this.BaseFare = baseFare;
   this.Seats = new ArrayList<>();

  }
//getters ans setters
  public String getFlightNumber() {
    return FlightNumber;
  }

  public void setFlightNumber(String flightNumber) {
    this.FlightNumber = flightNumber;
  }

  public String getDepartureAirport() {
    return DepartureAirport;
  }

  public void setDepartureAirport(String departureAirport) {
    this.DepartureAirport = departureAirport;
  }

  public String getArrivalAirport() {
    return ArrivalAirport;
  }

  public void setArrivalAirport(String arrivalAirport) {
    this.ArrivalAirport = arrivalAirport;
  }

  public String getArrivalTime() {
    return ArrivalTime;
  }

  public void setArrivalTime(String arrivalTime) {
    this.ArrivalTime = arrivalTime;
  }

  public String getDepartureTime() {
    return DepartureTime;
  }

  public void setDepartureTime(String departureTime) {
    this.DepartureTime = departureTime;
  }

  public double getBaseFare() {
    return BaseFare;
  }

  public void setBaseFare(double baseFare) {
    this.BaseFare = baseFare;
  }

  public List<Seat> getSeats() {
    return Seats;
  }

  public void AddSeat(Seat seat) {
    this.Seats.add(seat);
  }

//Displaying flight details
  public void displayFlightDetails() {
    System.out.println("Flight Number: " + FlightNumber);
    System.out.println("Departure Airport: " + DepartureAirport);
    System.out.println("Arrival Airport: " + ArrivalAirport);
    System.out.println("Arrival Time: " + ArrivalTime);
    System.out.println("Departure Time: " + DepartureTime);
    System.out.println("Flight Price:$" + BaseFare);


  }
}

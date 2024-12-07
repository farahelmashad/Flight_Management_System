package mainPackage;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Flight {
  private String FlightNumber;
  private String DepartureAirport;
  private String ArrivalAirport;
  private String ArrivalTime;
  private String DepartureTime;
  private Double BaseFare;
  public static ArrayList<Seat> Seats=new ArrayList<>(60);
  private String airlineCode;



  //constructor
  public Flight(String flightNumber, String departureAirport, String ArrivalAirport, String ArrivalTime, String departureTime, Double baseFare , String airlineCode) {
   this.FlightNumber = flightNumber;
   this.DepartureAirport = departureAirport;
   this.ArrivalAirport = ArrivalAirport;
   this.ArrivalTime = ArrivalTime;
   this.DepartureTime = departureTime;
   this.BaseFare = baseFare;
   this.airlineCode=airlineCode;
   this.Seats = new ArrayList<>(60);
    initializeSeats();
//    for (int i = 1; i <= 50; i++) { // Assuming 50 seats per flight
//      Seats.add(new Seat(i));
//

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

  public String getAirlineCode() {
    return airlineCode;
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
  public LocalDateTime toDateTime(String date){
    DateTimeFormatter format=DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    return LocalDateTime.parse(date, format);

  }
  public void FlightDuration(LocalDateTime departuretime,LocalDateTime arrivaltime){
    Duration flightDuration =Duration.between(departuretime,arrivaltime);
    long totalMinutes = flightDuration.toMinutes();
    long hours = totalMinutes / 60;
    long minutes = totalMinutes % 60;
    System.out.print( hours+ " h : "+ minutes+" m");
  }
  public int numberOfAvailableSeats(){

    int number=0;
    for(int i=0;i<50;i++){
      if(Seats.get(i).isAvailable()){
        number++;
      }
    }
    return number;
  }

  //Displaying flight details
  public void displayFlightDetails() {
    System.out.println("Flight Number: " + FlightNumber);
    System.out.println("Departure Airport: " + DepartureAirport);
    System.out.println("Arrival Airport: " + ArrivalAirport);
    System.out.println("Departure Time: " + DepartureTime);
    System.out.println("Arrival Time: " + ArrivalTime);
    System.out.print("Flight Duration: ");
    FlightDuration(toDateTime(DepartureTime),toDateTime(ArrivalTime));
    System.out.println("\n");
    System.out.println("Flight BaseFare without additional services/baggage/etc..):$" + BaseFare);

  }
  public static void initializeSeats() {
    // First Class: row 1 w 2
    for (int i = 1; i <= 2; i++) {
      Seats.add(new Seat("A" + String.format("%02d", i), "First Class"));
      Seats.add(new Seat("B" + String.format("%02d", i), "First Class"));
      Seats.add(new Seat("C" + String.format("%02d", i), "First Class"));
      Seats.add(new Seat("D" + String.format("%02d", i), "First Class"));
      Seats.add(new Seat("E" + String.format("%02d", i), "First Class"));
      Seats.add(new Seat("F" + String.format("%02d", i), "First Class"));
    }

    // Business Class: (men row 3-5)
    for (int i = 3; i <= 5; i++) {
      Seats.add(new Seat("A" + String.format("%02d", i), "Business Class"));
      Seats.add(new Seat("B" + String.format("%02d", i), "Business Class"));
      Seats.add(new Seat("C" + String.format("%02d", i), "Business Class"));
      Seats.add(new Seat("D" + String.format("%02d", i), "Business Class"));
      Seats.add(new Seat("E" + String.format("%02d", i), "Business Class"));
      Seats.add(new Seat("F" + String.format("%02d", i), "Business Class"));
    }

    // Economy Class (men row 6 l 10)
    for (int i = 6; i <= 10; i++) {
      Seats.add(new Seat("A" + String.format("%02d", i), "Economy Class"));
      Seats.add(new Seat("B" + String.format("%02d", i), "Economy Class"));
      Seats.add(new Seat("C" + String.format("%02d", i), "Economy Class"));
      Seats.add(new Seat("D" + String.format("%02d", i), "Economy Class"));
      Seats.add(new Seat("E" + String.format("%02d", i), "Economy Class"));
      Seats.add(new Seat("F" + String.format("%02d", i), "Economy Class"));
    }
  }
}

package mainPackage;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.lang.String;
public class Flight {
  private String FlightNumber;
  private String DepartureAirport;
  private String ArrivalAirport;
  private String ArrivalTime;
  private String DepartureTime;
  private Double BaseFare;
  private boolean hasSpecialMealRequest;
  private boolean hasPetTravel;
  private boolean hasLoungeAccess;
  private boolean haswheelchair;
  private String seatsAvailability;
  private String airlineName;
  private ArrayList<Seat> seats; // Instance-level seats list
  private static ArrayList<Seat> seatTemplate = new ArrayList<>(60);
  private boolean[] seatAvailability;
  private int baggageAllowance;

  static {
    initializeSeats();
  }




  //constructor
  public Flight(String flightNumber, String departureAirport, String ArrivalAirport, String ArrivalTime, String departureTime, Double baseFare , String airlineCode,boolean[] seatAvailability) {
   this.FlightNumber = flightNumber;
   this.DepartureAirport = departureAirport;
   this.ArrivalAirport = ArrivalAirport;
   this.ArrivalTime = ArrivalTime;
   this.DepartureTime = departureTime;
   this.BaseFare = baseFare;
   this.airlineCode=airlineCode;
    this.seatAvailability=seatAvailability;
    this.seats = new ArrayList<>(60);
    // Debugging output: Print availability array to ensure it's correct
    System.out.println("Seat availability array:");
    for (int i = 0; i < seatAvailability.length; i++) {
      System.out.print(seatAvailability[i] + " ");
    }
    System.out.println("\n");


    for (int i = 0; i < 60; i++) {
      boolean isAvailable = seatAvailability[i]; // Fetch availability
      Seat templateSeat = seatTemplate.get(i);   // Get seat template (number and class)

      // Debugging output: Print the seat availability
      System.out.println("Seat " + templateSeat.getSeatNumber() + " availability: " + isAvailable);

      // Add the seat with the correct availability status
      this.seats.add(new Seat(templateSeat.getSeatNumber(), templateSeat.getSeatClass(), isAvailable));

    }

//    for (int i = 0; i < 60; i++) {
//      boolean isAvailable = seatAvailability[i]; // Use the boolean directly
//      Seat templateSeat = seatTemplate.get(i); // Get the seat template (number and class)
//      this.seats.add(new Seat(templateSeat.getSeatNumber(), templateSeat.getSeatClass(), isAvailable));
//      System.out.println(seats.get(i).isAvailable());
//    }




  }

//getters ans setters

  public int getBaggageAllowance() {
    return baggageAllowance;
  }

  public boolean[] getSeatAvailability() {
    return seatAvailability;
  }

  public void setSeatAvailability(boolean[] seatAvailability) {
  if (seatAvailability.length != 60) {
    throw new IllegalArgumentException("seatAvailability array must be exactly 60 elements.");
  }
  this.seatAvailability = seatAvailability;
}

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
  public boolean getSpecialMealRequest() {
        return hasSpecialMealRequest;
    }

    public boolean getHasPetTravel() {
        return hasPetTravel;
    }

    public boolean getHasLoungeAccess() {
        return hasLoungeAccess;
    }

    public boolean getHaswheelchair() {
        return haswheelchair;
    }

  public void setArrivalAirport(String arrivalAirport) {
    this.ArrivalAirport = arrivalAirport;
  }

  public String getArrivalTime() {
    return ArrivalTime;
  }

  public String getAirlineName() {
    return airlineName;
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

  public ArrayList<Seat> getSeats() {
    return this.seats;
  }

  public void AddSeat(Seat seat) {
    this.seats.add(seat);
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
      if(seats.get(i).isAvailable()){
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
      seatTemplate.add(new Seat("A" + String.format("%02d", i), "First Class"));
      seatTemplate.add(new Seat("B" + String.format("%02d", i), "First Class"));
      seatTemplate.add(new Seat("C" + String.format("%02d", i), "First Class"));
      seatTemplate.add(new Seat("D" + String.format("%02d", i), "First Class"));
      seatTemplate.add(new Seat("E" + String.format("%02d", i), "First Class"));
      seatTemplate.add(new Seat("F" + String.format("%02d", i), "First Class"));
    }

    // Business Class: row 3-5
    for (int i = 3; i <= 5; i++) {
      seatTemplate.add(new Seat("A" + String.format("%02d", i), "Business Class"));
      seatTemplate.add(new Seat("B" + String.format("%02d", i), "Business Class"));
      seatTemplate.add(new Seat("C" + String.format("%02d", i), "Business Class"));
      seatTemplate.add(new Seat("D" + String.format("%02d", i), "Business Class"));
      seatTemplate.add(new Seat("E" + String.format("%02d", i), "Business Class"));
      seatTemplate.add(new Seat("F" + String.format("%02d", i), "Business Class"));
    }

    // Economy Class: row 6-10
    for (int i = 6; i <= 10; i++) {
      seatTemplate.add(new Seat("A" + String.format("%02d", i), "Economy Class"));
      seatTemplate.add(new Seat("B" + String.format("%02d", i), "Economy Class"));
      seatTemplate.add(new Seat("C" + String.format("%02d", i), "Economy Class"));
      seatTemplate.add(new Seat("D" + String.format("%02d", i), "Economy Class"));
      seatTemplate.add(new Seat("E" + String.format("%02d", i), "Economy Class"));
      seatTemplate.add(new Seat("F" + String.format("%02d", i), "Economy Class"));
    }  }
}

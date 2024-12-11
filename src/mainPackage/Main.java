package mainPackage;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main (String[] args){
        Scanner input = new Scanner(System.in);
        System.out.println("hi");
        List<Flight> flights = new ArrayList<>();
//        flights.add(new Flight("AA101", "JFK", "LAX", "14:00", "11:00", 300.00));
//        flights.add(new Flight("BA202", "LHR", "CDG", "09:30", "08:00", 120.00));
//        flights.add(new Flight("CA303", "PEK", "HKG", "16:00", "14:30", 250.00));
//        flights.add(new Flight("DA404", "DXB", "DEL", "22:00", "19:00", 200.00));
//        flights.add(new Flight("EA505", "SYD", "MEL", "18:45", "17:30", 150.00));
//        flights.add(new Flight("FA606", "ORD", "DFW", "13:15", "11:45", 180.00));
//        flights.add(new Flight("GA707", "ATL", "MIA", "20:00", "18:00", 220.00));
//        flights.add(new Flight("HA808", "SEA", "SFO", "15:00", "13:00", 210.00));
//        flights.add(new Flight("IA909", "ICN", "NRT", "11:00", "09:30", 270.00));
//        flights.add(new Flight("JA010", "YYZ", "YVR", "08:00", "06:30", 230.00));
//        Admin admin1=new Admin(flights);
//        admin1.Adminpanel();

                // Sample flight data
                String flight1Number = "F123";
                String flight1DepartureAirport = "JFK";
                String flight1ArrivalAirport = "LAX";
                String flight1DepartureTime = "2024-12-06T08:30";
                String flight1ArrivalTime = "2024-12-06T11:30";
                double flight1BaseFare = 299.99;
                String flight1AirlineCode = "AA";
                  String flight1SeatsAvailability = "111111111111111111111111011111101111111111111111011111111111"; // Correct: 60 characters
        System.out.println(flight1SeatsAvailability.length());
                // Another sample flight
                String flight2Number = "F456";
                String flight2DepartureAirport = "ORD";
                String flight2ArrivalAirport = "SFO";
                String flight2DepartureTime = "2024-12-06T15:00";
                String flight2ArrivalTime = "2024-12-06T18:00";
                double flight2BaseFare = 199.99;
                String flight2AirlineCode = "UA";
                String flight2SeatsAvailability = "000000111111000000111111000000111111000000111111"; // 60 seats
        boolean[] seatsAvailability = new boolean[60];
// Let's say the first 10 seats are available, and the rest are not
        for (int i = 0; i < 10; i++) {
            seatsAvailability[i] = true;  // Available seats
        }
        for (int i = 10; i < 60; i++) {
            seatsAvailability[i] = false; // Unavailable seats
        }
                // Create flights
                Flight flight1 = new Flight(
                        flight1Number,
                        flight1DepartureAirport,
                        flight1ArrivalAirport,
                        flight1ArrivalTime,
                        flight1DepartureTime,
                        flight1BaseFare,
                        flight1AirlineCode,
                        seatsAvailability
                );


//User user=new User("farahalmashad75@gmail.com","1234567");

//flight1.setSeatAvailability(seatsAvailability);
//        System.out.println(flight1.getSeats().get(12).isAvailable());
//     FlightService flightService = new FlightService(user);
//     flightService.SeatSelection(flight1);
//        flightService.displaySeats(flight1);

//        flightService.;

    }
}

package mainPackage;

import java.util.ArrayList;
import java.util.Scanner;

//white space issues // to be dealt with later!!
public class FlightService {

    ArrayList<Airline> Airlines = Airline.getAirlines();
    ArrayList<Airport> Airports = Airport.getAirports();
    public void flightSearch(){
        // Adding some test data to Airlines list
        Airlines.add(new Airline("AA", "American Airlines", 1000, "USA"));
        Airlines.add(new Airline("BA", "British Airways", 800, "UK"));
        Airlines.add(new Airline("CA", "Cathay Pacific", 500, "Hong Kong"));

        // Adding some test data to Airports list
        Airports.add(new Airport("JFK", "John F. Kennedy International Airport", "New York"));
        Airports.add(new Airport("LHR", "London Heathrow", "London"));
        Airports.add(new Airport("HKG", "Hong Kong International Airport", "Hong Kong"));


        System.out.println("Flight Search");
        Scanner in = new Scanner(System.in);

        System.out.print("Please enter your departure airport: [ ");
        for (Airport a : Airports) {
            System.out.print(a.getAirportName() + " - ");
        }
        System.out.println("]");

        String departureAirport = in.nextLine();
        departureAirport=departureAirport.toLowerCase();

        while (!isValidAirportName(departureAirport)) {
            System.out.print("Invalid Airport name. Please enter one of the following airports: [ ");
            for (Airport a : Airports) {
                System.out.print(a.getAirportName() + " - ");
            }
            System.out.println("]");

          departureAirport = in.nextLine();
        }
        System.out.print("Please enter your arrival airport: [ ");
        for (Airport a : Airports) {
            System.out.print(a.getAirportName() + " - ");
        }
        System.out.print("]");

        String arrivalAirport = in.nextLine();
        arrivalAirport=arrivalAirport.toLowerCase();
        while(arrivalAirport.replaceAll("\\s", "").equals(departureAirport.replaceAll("\\s", ""))){
            System.out.println("The arrival airport can't be the same as the departure airport, please enter another airport: ");
            arrivalAirport=in.nextLine();
            arrivalAirport=arrivalAirport.toLowerCase();
        }
        while (!isValidAirportName(arrivalAirport)) {
            System.out.println("Invalid Airport name. Please enter one of the following airports: [ ");
            for (Airport a : Airports) {
                System.out.print(a.getAirportName() + " - ");
            }
            System.out.println("]");

            arrivalAirport = in.nextLine();
            arrivalAirport=arrivalAirport.toLowerCase();
        }

    }

    private boolean isValidAirportName(String airportName) {
        for (Airport a : Airports) {
            if (airportName.replaceAll("\\s", "").equalsIgnoreCase(a.getAirportName().replaceAll("\\s", ""))) {
                return true;
            }
        }
        return false;
    }

}



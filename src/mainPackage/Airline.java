package mainPackage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Airline {
    private String airlineCode;
    private String airlineName;
    private int fleetSize;
    private int baggageAllowance;
    private String countryOfOrigin;
    private Map<String, Integer> seatAvailability;
    private List<String> specialServices;
    private boolean hasSpecialMealRequest;
    private boolean hasPetTravel;
    private boolean hasLoungeAccess;
    private boolean haswheelchair;
    private ArrayList<Flight> flights;
    private static ArrayList<Airline> airlines = new ArrayList<>();

    public Airline(String airlineCode, String airlineName, int fleetSize, String countryOfOrigin) {
        this.airlineCode = airlineCode;
        this.airlineName = airlineName;
        this.fleetSize = fleetSize;
        this.baggageAllowance = 0;
        this.countryOfOrigin = countryOfOrigin;
        this.seatAvailability = new HashMap<>();
        this.specialServices = new ArrayList<>();
        this.flights = new ArrayList<>();
    }

    // Static method to get all airlines
    public static ArrayList<Airline> getAirlines() {
        return airlines;  // Return the list of airlines
    }

    public String getAirlineCode() {
        return airlineCode;
    }

    public String getAirlineName() {
        return airlineName;
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

    public int getFleetSize() {
        return fleetSize;
    }

    public Map<String, Integer> getSeatAvailability() {
        return seatAvailability;
    }

    public int getBaggageAllowance() {
        return baggageAllowance;
    }

    public List<String> getSpecialServices() {
        return specialServices;
    }

    public String getCountryOfOrigin() {
        return countryOfOrigin;
    }

    public ArrayList<Flight> getFlights() {
        return flights;
    }

    // Static method to add or update an airline
    public static void addOrUpdateAirline(Airline newAirline) {
        boolean isFound = false;
        for (Airline airline : airlines) {
            if (airline.getAirlineCode().equalsIgnoreCase(newAirline.getAirlineCode())) {
                airline.airlineName = newAirline.getAirlineName();
                airline.fleetSize = newAirline.getFleetSize();
                airline.baggageAllowance = newAirline.getBaggageAllowance();
                airline.seatAvailability = newAirline.getSeatAvailability();
                airline.specialServices = newAirline.getSpecialServices();
                airline.flights = newAirline.getFlights(); // Update flights
                isFound = true;
                break;
            }
        }
        if (!isFound) {
            airlines.add(newAirline);
        }
    }

    // Method to delete an airline by its code
    public static void deleteAirline(String airlineCode) {
        airlines.removeIf(airline -> airline.getAirlineCode().equalsIgnoreCase(airlineCode));
    }

    @Override
    public String toString() {
        return "Airline Code: " + airlineCode + "\n" +
                "Airline Name: " + airlineName + "\n" +
                "Fleet Size: " + fleetSize + "\n" +
                "Country of Origin: " + countryOfOrigin + "\n" +
                "Seat Availability: " + seatAvailability + "\n" +
                "Baggage Allowance: " + baggageAllowance + " kg" + "\n" +
                "Special Services: " + specialServices + "\n" +
                "Flights: " + (flights.isEmpty() ? "No flights available" : flights.size() + " flights available");
    }
}

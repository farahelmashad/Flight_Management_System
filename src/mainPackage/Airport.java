package mainPackage;
import java.util.*;

public class Airport {
    private String airportCode;
    private String airportName;
    private String airportLocation;
    private static ArrayList<Airport> airports = new ArrayList<>();

    public static ArrayList<Airport> getAirports() {
        return airports;
    }


    public Airport(Airport airport) {
        setAirportCode(airport.getAirportCode());
        setAirportName(airport.getAirportName());
        setAirportLocation(airport.getAirportLocation());
    }

    public Airport(String airportCode, String airportName, String airportLocation) {
        setAirportCode(airportCode);
        setAirportName(airportName);
        setAirportLocation(airportLocation);
    }

    public String getAirportCode() {
        return airportCode;
    }

    public String getAirportName() {
        return airportName;
    }

    public String getAirportLocation() {
        return airportLocation;
    }

    public void setAirportCode(String airportCode) {
        if (airportCode == null || airportCode.trim().isEmpty()) {
            throw new IllegalArgumentException("Airport code cannot be null or empty. Please provide a valid code.");
        }
        this.airportCode = airportCode;
    }

    public void setAirportName(String airportName) {
        if (airportName == null || airportName.trim().isEmpty()) {
            throw new IllegalArgumentException("Airport name cannot be null or empty. Please provide a valid name.");
        }
        this.airportName = airportName;
    }

    public void setAirportLocation(String airportLocation) {
        if (airportLocation == null || airportLocation.trim().isEmpty()) {
            throw new IllegalArgumentException("Airport location cannot be null or empty. Please provide a valid location.");
        }
        this.airportLocation = airportLocation;
    }

    public String getAirportInfo() {
        return "Airport Code: " + airportCode + "\n" +
                "Airport Name: " + airportName + "\n" +
                "Location: " + airportLocation;
    }

    public static void addAirport(Airport airport) {
        airports.add(airport);
    }

    public static Airport AirportSearch(String searchValue) {
        for (Airport airport : airports) {
            if (airport.getAirportCode().equalsIgnoreCase(searchValue) ||
                    airport.getAirportName().equalsIgnoreCase(searchValue)) {
                return airport;
            }
        }
        System.out.println("Airport not found!");
        return null;
    }

    @Override
    public String toString() {
        return getAirportInfo();
    }
}

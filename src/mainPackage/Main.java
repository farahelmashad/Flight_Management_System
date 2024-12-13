package mainPackage;

public class Main {
    public static void main(String[] args) {
        // Load airports from the file
        AirportFileHandler.loadAirportsFromFile("airports.txt");

        // Display all loaded airports from the public list
        System.out.println("Airports loaded:");
        for (Airport airport : AirportFileHandler.airportList) {
            System.out.println(airport);
        }

        // Example: Search for an airport
        Airport foundAirport = Airport.AirportSearch("CAI");
        if (foundAirport != null) {
            System.out.println("Found Airport:");
            System.out.println(foundAirport);
        }
    }
}

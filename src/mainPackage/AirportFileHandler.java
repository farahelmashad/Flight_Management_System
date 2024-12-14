package mainPackage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class AirportFileHandler {

    // This method reads flight data from the file and adds them to the ArrayList
    public static ArrayList<Flight> readFlightsFromFile(String filename) {
        ArrayList<Flight> flights = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Split the data based on commas
                String[] data = line.split(",");
                if (data.length != 12) {
                    System.out.println("Error: Incorrect number of elements. Expected 12 but found " + data.length);
                    continue;
                }

                // Extracting flight details from the data array
                String flightID = data[0].trim();
                String departureAirport = data[1].trim();
                String arrivalAirport = data[2].trim();
                String departureDate = data[3].trim();
                String arrivalDate = data[4].trim();
                double price = Double.parseDouble(data[5].trim());
                String airline = data[6].trim();

                // Parsing the seat availability string (which is a sequence of 60 1's or 0's)
                String seatAvailabilityString = data[7].trim();
                boolean[] seatAvailability = new boolean[60];
                for (int i = 0; i < seatAvailabilityString.length(); i++) {
                    seatAvailability[i] = seatAvailabilityString.charAt(i) == '1';
                }

                // Parsing the service availability (true/false)
                boolean service1 = Boolean.parseBoolean(data[8].trim());
                boolean service2 = Boolean.parseBoolean(data[9].trim());
                boolean service3 = Boolean.parseBoolean(data[10].trim());
                boolean service4 = Boolean.parseBoolean(data[11].trim());

                // Creating a new Flight object and adding it to the ArrayList
                Flight flight = new Flight(flightID, departureAirport, arrivalAirport, departureDate, arrivalDate,
                        price, airline, seatAvailability, service1, service2, service3, service4);
                flights.add(flight);

                // Debugging: Print the flight details to ensure they're correct
                System.out.println("Flight ID: " + flightID);
                System.out.println("Departure Airport: " + departureAirport);
                System.out.println("Arrival Airport: " + arrivalAirport);
                System.out.println("Departure Date: " + departureDate);
                System.out.println("Arrival Date: " + arrivalDate);
                System.out.println("Price: " + price);
                System.out.println("Airline: " + airline);
                System.out.println("Seat Availability: " + Arrays.toString(seatAvailability));
                System.out.println("Service 1: " + service1);
                System.out.println("Service 2: " + service2);
                System.out.println("Service 3: " + service3);
                System.out.println("Service 4: " + service4);
                System.out.println(); // Blank line between flights
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return flights;
    }
}

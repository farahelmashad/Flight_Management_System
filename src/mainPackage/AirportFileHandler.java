package mainPackage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class AirportFileHandler {
    public static ArrayList<Airport> airportList = new ArrayList<>(); // Public static list

    public static void loadAirportsFromFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 3) {
                    String code = data[0].trim();
                    String name = data[1].trim();
                    String location = data[2].trim();
                    Airport airport = new Airport(code, name, location);
                    airportList.add(airport); // Add to the public static list
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }
    }
}

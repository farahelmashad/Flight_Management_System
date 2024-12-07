package mainPackage;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Main {
    public static void main (String[] args)  {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        Scanner input = new Scanner(System.in);

        List<Flight> flights = new ArrayList<>();
        try {
            // Initialize flights with sample data
            flights.add(new Flight("AB123", "JFK", "LAX",
                    "15-12-2024 10:30", "15-12-2024 13:30", 150.0));
            flights.add(new Flight("CD456", "ORD", "DFW",
                    "16-12-2024 08:00", "16-12-2024 11:00", 200.0));
            flights.add(new Flight("EF789", "SFO", "SEA",
                    "17-12-2024 14:00", "17-12-2024 15:30", 120.0));
            flights.add(new Flight("GH012", "ATL", "MIA",
                    "18-12-2024 09:00", "18-12-2024 11:30", 180.0));
            flights.add(new Flight("IJ345", "LGA", "BOS",
                    "19-12-2024 07:00", "19-12-2024 08:15", 100.0));
            flights.add(new Flight("KL678", "DEN", "PHX",
                    "20-12-2024 16:00", "20-12-2024 18:00", 170.0));
            flights.add(new Flight("MN901", "LAS", "SAN",
                    "21-12-2024 20:00", "21-12-2024 21:30", 110.0));
            flights.add(new Flight("OP234", "DCA", "IAH",
                    "22-12-2024 12:00", "22-12-2024 15:00", 210.0));
            flights.add(new Flight("QR567", "MSP", "DTW",
                    "23-12-2024 06:30", "23-12-2024 08:30", 130.0));
            flights.add(new Flight("ST890", "CLT", "TPA",
                    "24-12-2024 19:45", "24-12-2024 21:15", 160.0));

            System.out.println("Flights initialized successfully.");

        } catch (ParseException e) {
            System.out.println("Error parsing dates: " + e.getMessage());
        }

        Admin admin1=new Admin(flights);
        admin1.Adminpanel();

    }
}

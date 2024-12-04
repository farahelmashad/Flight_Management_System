import java.util.*;

public class Airline
{
    private String airlineCode;
    private String airlineName;
    private int fleetSize;
    private double baggageAllowance;
    private String countryOfOrigin;
    private Map<String, Integer> seatAvailability;
    private List<String> specialServices;
    private List<Flight> flights;
    // Static list to hold all airlines in memory
    private static ArrayList<Airline> airlines = new ArrayList<>();

    public Airline(String airlineCode, String airlineName, int fleetSize, String countryOfOrigin)
    {
        this.airlineCode = airlineCode;
        this.airlineName = airlineName;
        this.fleetSize = fleetSize;
        this.baggageAllowance = 0;
        this.countryOfOrigin = countryOfOrigin;
        this.seatAvailability = new HashMap<>();
        this.specialServices = new ArrayList<>();
        this.flights = new ArrayList<>();
    }

    public String getAirlineCode()
    {
        return airlineCode;
    }
    public String getAirlineName()
    {
        return airlineName;
    }
    public int getFleetSize()
    {
        return fleetSize;
    }
    public Map<String, Integer> getSeatAvailability()
    {
        return seatAvailability;
    }
    public double getBaggageAllowance()
    {
        return baggageAllowance;
    }
    public List<String> getSpecialServices()
    {
        return specialServices;
    }
    public String getCountryOfOrigin()
    {
        return countryOfOrigin;
    }
    public List<Flight> getFlights()
    {
        return flights;
    }

    public void setAirlineCode(String airlineCode)
    {
        this.airlineCode = airlineCode;
    }
    public void setAirlineName(String airlineName)
    {
        this.airlineName = airlineName;
    }
    public void setCountryOfOrigin(String countryOfOrigin)
    {
        this.countryOfOrigin = countryOfOrigin;
    }
    public void setBaggageAllowance(double baggageAllowance)
    {
        this.baggageAllowance = baggageAllowance;
    }
    public void setFleetSize(int fleetSize)
    {
        this.fleetSize = fleetSize;
    }
    public void setSeatAvailability(Map<String, Integer> seatAvailability)
    {
        this.seatAvailability = seatAvailability;
    }
    public void setSeatAvailability(String seatClass, int availableSeats)
    {
        seatAvailability.put(seatClass, availableSeats);
    }
    public void setSpecialServices(List<String> specialServices)
    {
        this.specialServices = specialServices;
    }
    public void addSpecialService(String service)
    {
        specialServices.add(service);
    }

    public void addFlight(Flight flight) {
        if (flight != null) {
            flights.add(flight);
        } else {
            throw new IllegalArgumentException("Flight cannot be null!");
        }
    }

    public Flight findFlightByNumber(String flightNumber)
    {
        for (Flight flight : flights) {
            if (flight.getFlightNumber().equalsIgnoreCase(flightNumber)) {
                return flight;
            }
        }
        return null;
    }

    public void displayAllFlights()
    {
        if (flights.isEmpty()) {
            System.out.println("No flights available for this airline.");
        } else {
            for (Flight flight : flights) {
                flight.displayFlightDetails();
                System.out.println();
            }
        }
    }

    public static void addOrUpdateAirline(Airline newAirline)
    {
        boolean isFound = false;
        for (Airline airline : airlines)
        {
            if (airline.getAirlineCode().equalsIgnoreCase(newAirline.getAirlineCode()))
            {
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

    public static void deleteAirline(String airlineCode)
    {
        airlines.removeIf(airline -> airline.getAirlineCode().equalsIgnoreCase(airlineCode));
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Airline airline = (Airline) obj;
        return airlineCode.equalsIgnoreCase(airline.airlineCode);
    }

    @Override
    public String toString()
    {
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

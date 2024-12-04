public class Airport
{
    private String airportCode;
    private String airportName;
    private String airportLocation;

    public Airport(Airport airport)
    {
        setAirportCode(airport.getAirportCode());
        setAirportName(airport.getAirportName());
        setAirportLocation(airport.getAirportLocation());
    }
    public Airport(String airportCode, String airportName, String airportLocation)
    {
        setAirportCode(airportCode);
        setAirportName(airportName);
        setAirportLocation(airportLocation);
    }

    public String getAirportCode()
    {
        return airportCode;
    }

    public String getAirportName()
    {
        return airportName;
    }

    public String getAirportLocation()
    {
        return airportLocation;
    }
    //.trim() removes whitespace characters (like spaces, tabs, or newline characters) from the string.
    public void setAirportCode(String airportCode)
    {
        if (airportCode == null || airportCode.trim().isEmpty())
        {
            throw new IllegalArgumentException("Airport code cannot be null or empty.Please provide a valid code");
        }
        this.airportCode = airportCode;
    }

    public void setAirportName(String airportName) {
        if (airportName == null || airportName.trim().isEmpty())
        {
            throw new IllegalArgumentException("Airport name cannot be null or empty.Please provide a valid name");
        }
        this.airportName = airportName;
    }

    public void setAirportLocation(String airportLocation)
    {
        if (airportLocation == null || airportLocation.trim().isEmpty())
        {
            throw new IllegalArgumentException("Airport location cannot be null or empty.Please provide a valid location");
        }
        this.airportLocation = airportLocation;
    }

    public String getAirportInfo()
    {
        return "Airport Code: " + airportCode + "\n" +
                "Airport Name: " + airportName + "\n" +
                "Location: " + airportLocation;
    }

    // Equals method to compare two airports based on airportCode, airportName, and airportLocation
    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Airport airport = (Airport) obj;
        return airportCode.equalsIgnoreCase(airport.airportCode) &&
                airportName.equalsIgnoreCase(airport.airportName) &&
                airportLocation.equalsIgnoreCase(airport.airportLocation);
    }

    // Override toString() to display airport information
    @Override
    public String toString()
    {
        return getAirportInfo();
    }
}

package mainPackage;

public class Main {
    public static void main (String[] args){
        Scanner input = new Scanner(System.in);
        System.out.println("hi");
        List<Flight> flights = new ArrayList<>();
        flights.add(new Flight("AA101", "JFK", "LAX", "14:00", "11:00", 300.00));
        flights.add(new Flight("BA202", "LHR", "CDG", "09:30", "08:00", 120.00));
        flights.add(new Flight("CA303", "PEK", "HKG", "16:00", "14:30", 250.00));
        flights.add(new Flight("DA404", "DXB", "DEL", "22:00", "19:00", 200.00));
        flights.add(new Flight("EA505", "SYD", "MEL", "18:45", "17:30", 150.00));
        flights.add(new Flight("FA606", "ORD", "DFW", "13:15", "11:45", 180.00));
        flights.add(new Flight("GA707", "ATL", "MIA", "20:00", "18:00", 220.00));
        flights.add(new Flight("HA808", "SEA", "SFO", "15:00", "13:00", 210.00));
        flights.add(new Flight("IA909", "ICN", "NRT", "11:00", "09:30", 270.00));
        flights.add(new Flight("JA010", "YYZ", "YVR", "08:00", "06:30", 230.00));
        Admin admin1=new Admin(flights);
        admin1.Adminpanel();

    }
}

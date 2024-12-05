package mainPackage;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

//white space issues // to be dealt with later!!
public class FlightService {
    ArrayList<Airline> Airlines = Airline.getAirlines();
    ArrayList<Airport> Airports = Airport.getAirports();
    public void flightSearch() {
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
        departureAirport = departureAirport.toLowerCase();

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
        arrivalAirport = arrivalAirport.toLowerCase();
        while (arrivalAirport.replaceAll("\\s", "").equals(departureAirport.replaceAll("\\s", ""))) {
            System.out.println("The arrival airport can't be the same as the departure airport, please enter another airport: ");
            arrivalAirport = in.nextLine();
            arrivalAirport = arrivalAirport.toLowerCase();
        }
        while (!isValidAirportName(arrivalAirport)) {
            System.out.println("Invalid Airport name. Please enter one of the following airports: [ ");
            for (Airport a : Airports) {
                System.out.print(a.getAirportName() + " - ");
            }
            System.out.println("]");

            arrivalAirport = in.nextLine();
            arrivalAirport = arrivalAirport.toLowerCase();
        }

        //Input the date
        LocalDate today = LocalDate.now();
        System.out.println("Please enter your departure date: (dd-mm-yyyy) ");
        String depDate;
        depDate = in.nextLine();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate deptTime = LocalDate.parse(depDate, format);
        LocalDate week=today.plusDays(7);
        while((deptTime.compareTo(week)>1)||deptTime.compareTo(today)<0){
            System.out.println("You can't book flights that aren't within a week, please enter a date that is less than a week from now: ");
            depDate = in.nextLine();
            deptTime = LocalDate.parse(depDate, format);

        }
        //special services by airlines
        boolean specialMeal=false, petTravel=false, wheelchair=false, loungeAccess=false;
        System.out.println("Would you like any additional services offered by the different airlines? (y/n)");
        String answer=in.nextLine();
        if(answer.equalsIgnoreCase("y")){
            do {

                System.out.println("Services offered by the airlines: " + "\n" + "1-Special Meal Requests" + "\n" + "2-Pet Travel" + "\n" + "3-Wheelchair Accessibility" + "\n" + "4-Lounge Access" + "5-None (Finish Selecting)");
                int choice = in.nextInt();
                in.nextLine();
                switch (choice) {
                    case 1:
                        specialMeal = true;
                        System.out.println("Special Meal Requests Selected");
                        break;
                    case 2:
                        petTravel = true;
                        System.out.println("Pet Travel Selected");
                        break;
                    case 3:
                        wheelchair = true;
                        System.out.println("Wheelchair Accessibility Selected");
                        break;
                    case 4:
                        loungeAccess = true;
                        System.out.println("Lounge Access Selected");
                        break;
                    case 5:
                        break;
                    default:
                        System.out.println("Invalid choice");
                        break;
                }
                System.out.println("Would you like to choose another service? (y/n)");
                answer = in.nextLine();
            }
            while(answer.equalsIgnoreCase("y"));

        }
//        FlightSelection( departureAirport,arrivalAirport,deptTime,specialMeal,petTravel,wheelchair,loungeAccess);
           //flights search
        ArrayList<Flight> AvailableFlights=new ArrayList<>();
          for(int i=0;i<Airlines.size();i++){
              if((Airlines.get(i).getSpecialMealRequest()==specialMeal)&&(Airlines.get(i).getHasPetTravel()==petTravel)&&(Airlines.get(i).getHasLoungeAccess()==loungeAccess)&&(Airlines.get(i).getHaswheelchair()==wheelchair)){
                ArrayList<Flight> f=Airlines.get(i).getFlights();
                for(Flight F:f){
                    if((F.getDepartureAirport().equalsIgnoreCase(departureAirport))&&(F.getArrivalAirport().equalsIgnoreCase(arrivalAirport))){
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                        String date= F.getDepartureTime();
                        LocalDateTime dateWithTime = LocalDateTime.parse(date, formatter);
                        LocalDate flightDepartDate = dateWithTime.toLocalDate();
                        if(deptTime.equals(flightDepartDate)){
                            AvailableFlights.add(F);
                        }

                    }
                }
              }
          }
          FlightSelection(AvailableFlights);
        }
        public void FlightSelection(ArrayList<Flight> Flightss){
        Scanner in=new Scanner(System.in);
        if(Flightss.size()==0){
            System.out.println("No flights with those specifications available, would you like to search for another flight? (y/n)");
            String choice= in.nextLine();
            if(choice.equalsIgnoreCase("y"))
                flightSearch();
            //need else to the main menu
        }
        else{
            System.out.println("--------------------Available Flights----------------------");
        for(Flight f:Flightss){
            f.displayFlightDetails();
        }
        }}
    private boolean isValidAirportName(String airportName) {
        for (Airport a : Airports) {
            if (airportName.replaceAll("\\s", "").equalsIgnoreCase(a.getAirportName().replaceAll("\\s", ""))) {return true;
            }
        }
        return false;
    }

}



package mainPackage;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

//white space issues // to be dealt with later!!
public class FlightService {
    private User currentUser;
    ArrayList<Airline> Airlines = Airline.getAirlines();
    ArrayList<Airport> Airports = Airport.getAirports();
     public ArrayList<Passenger>Passengers=new ArrayList<>();
//    ArrayList<Seat> seat = Flight.Seats;
     public FlightService(User currentUser){
         this.currentUser=currentUser;
     }
    public void flightSearch() {

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
        public void FlightSelection(ArrayList<Flight> f){
        Scanner in=new Scanner(System.in);
        if(f.isEmpty()){
            System.out.println("No flights with those specifications available, would you like to search for another flight? (y/n)");
            String choice= in.nextLine();
            if(choice.equalsIgnoreCase("y"))
                flightSearch();
            //need else to the main menu
        }
        else{
            System.out.println("--------------------Available Flights----------------------");
        for(int i=0;i<f.size();i++){
            if(f.get(i).numberOfAvailableSeats()==0){
                continue; //no available seats on this flight, check the next flight
            }
            System.out.println("---------------------------------------------------------------------------");
            f.get(i).displayFlightDetails();
            Airline thisFlightairline=findAirlineByCode(f.get(i).getAirlineCode());
            System.out.println("Airline : "+thisFlightairline.getAirlineName());
            System.out.println("Special Services offered by the Airline: ");
            boolean offersServices=false;
            if(thisFlightairline.getSpecialMealRequest()){
                System.out.println("Special Meal Requests");
                offersServices=true;
            }
            if(thisFlightairline.getHaswheelchair()){
                System.out.println("Wheelchair Accessibility");
                offersServices=true;
            }
            if(thisFlightairline.getHasPetTravel()){
                System.out.println("Pet Travel");
                offersServices=true;
            }
            if(thisFlightairline.getHasLoungeAccess()){
                System.out.println("Lounge Access");
                offersServices=true;
            }
            if(offersServices==false){
                System.out.println("The airline doesn't offer special services");
            }
            System.out.println("Baggage allowance (maximum number of bags per passenger) : "+thisFlightairline.getBaggageAllowance());
            System.out.println("Number of available seats : "+f.get(i).numberOfAvailableSeats());

        }
            System.out.print("Please enter the flight number of your preferred flight ( ");
        for(Flight fl:f ){
            System.out.print( fl.getFlightNumber()+" ");

        }
            System.out.print(")");
            System.out.println("\n");
            Flight foundFlight=null;
            while (foundFlight == null) {
                System.out.print("Enter the flight number to search: ");
                String flightNumberToSearch = in.nextLine();

                for (Flight flight : f) {
                    if (flight.getFlightNumber().equalsIgnoreCase(flightNumberToSearch)) {
                        foundFlight = flight;
                        break;
                    }
                }

                if (foundFlight == null) {
                    System.out.println("No flight found with the flight number " + flightNumberToSearch + ". Please try again.");
                }
            }
            SeatSelection(foundFlight);
        }
        }
        public void SeatSelection(Flight flight){
        ArrayList<Seat> flightSeats=flight.getSeats();
        int seatindex=-1;
            ArrayList<Seat> chosenSeats=new ArrayList<>();
            Scanner in=new Scanner(System.in);
            System.out.println("How many seats do you want to choose: ");
            int number=in.nextInt();
            in.nextLine();
            while(number>flight.numberOfAvailableSeats()){
                System.out.println("Please enter a number that is less than or equal to the number of available seats ("+flight.numberOfAvailableSeats()+")");
                number=in.nextInt();
                in.nextLine();
            }
            displaySeats(flight);
            for (int i = 0; i < number; i++) {
                Seat chosenSeat = null;
                boolean validSeatChosen = false;

                while (!validSeatChosen) {
                    if (number == 1) {
                        System.out.println("Enter the seat number: (e.g., A01)");
                    } else {
                        System.out.println("Enter the seat number for seat " + (i + 1) + " (e.g., A01):");
                    }
                    String seatno = in.nextLine();

                    boolean seatExists = false;
//
//                    for (Seat seat1 : flightSeats) {
//                        if (seatno.equalsIgnoreCase(seat1.getSeatNumber())) {
//                            seatExists = true;
//                            chosenSeat =seat1 ;
//                            break;
//                        }
//                    }
                    for(int j=0;j<flightSeats.size();j++){
                        if(seatno.equalsIgnoreCase(flightSeats.get(j).getSeatNumber())){
                            seatExists=true;
                            seatindex=j;
                            chosenSeat=flightSeats.get(j);
                            break;
                        }
                    }

                    if (!seatExists) {

                        System.out.println("Invalid seat number. Please enter a valid seat number (e.g., A01).");
                        continue;
                    }

                    if (!chosenSeat.isAvailable()) {
                        System.out.println("This seat is unavailable. Please choose another seat.");
                        continue;
                    }

                    chosenSeat.setAvailable(false);
                    flight.getSeatAvailability()[seatindex]=false;
                    System.out.println("Seat chosen successfully!");
                    chosenSeats.add(chosenSeat);
                    validSeatChosen = true;
//                    displaySeats();

                }
                Passenger passenger = new Passenger();
                getPassengerInformation(passenger);
//                passenger.setSeat(chosenSeat);

            }
//            getUserInformation(chosenSeats,number); when the method is added , hab3at elchosen seats w the number of chosen seats, n loop aala aadad el number of seats aashan
            //w n display for each seat : passenger details for seat no. (chosenSeats.get(i).getSeatNumber())

        }
          public void displaySeats(Flight flight) {
                  ArrayList<Seat> seat = flight.getSeats();
                for (int i = 0; i < seat.size(); i++) {
                    Seat s = seat.get(i);
                    if (i == 0)
                        System.out.println("First Class: \n ------------------------------------------");
                    if (i == 12)
                        System.out.println("Business Class: \n ------------------------------------------");
                    if (i == 30)
                        System.out.println("Economy Class: \n ------------------------------------------");
                    if (s.getSeatNumber().charAt(0) <= 'C') {
                        System.out.print(getSeat(s) + " ");
                        if(s.getSeatNumber().charAt(0)=='C')
                            System.out.print("  |  ");
                    } else {
                        if (s.getSeatNumber().charAt(0) == 'C') {
                    }
                        System.out.print(getSeat(s) + " ");

                    }

                    if (i % 6 == 5) {
                        System.out.println();
                    }
                }

              System.out.println("\n\n 🟩 : Available \t \uD83D\uDFE5 : Unavailable ");
              System.out.println("Seats with seat number A__ or F__ are window seats");
              System.out.println("Seats with seat number C__ or D__ are aisle seats");

          }

        public String getSeat(Seat s){
        String availableIcon="🟩";
        String unavailableIcon= "\uD83D\uDFE5";
        if(s.isAvailable()){
            return s.getSeatNumber()+availableIcon;
        }
        else{
            return s.getSeatNumber()+unavailableIcon;
        }
        }
    private boolean isValidAirportName(String airportName) {
        for (Airport a : Airports) {
            if (airportName.replaceAll("\\s", "").equalsIgnoreCase(a.getAirportName().replaceAll("\\s", ""))) {return true;
            }
        }
        return false;
    }
    public Airline findAirlineByCode(String airlineCode) {
        for (Airline airline : Airlines) {
            if (airline.getAirlineCode().equalsIgnoreCase(airlineCode)) {
                return airline; // will return el airline ely feha this flight
            }
        }
        return null; //law mala2etsh elairline
    }
    public void getPassengerInformation(Passenger passenger){
        Scanner in=new Scanner(System.in);
        System.out.println("Please enter passenger's name: ");
        passenger.setName(in.nextLine());
        System.out.println("Please enter passenger's gender (M/F)");
        passenger.setGender(in.nextLine());
        System.out.println("Please enter passenger's phone number : ");
        passenger.setPhoneNumber(in.nextInt());
        in.nextLine();
        System.out.println("Please enter passenger's date of birth in the format (dd/mm/yyyy)");
        passenger.setDateOfBirth(in.nextLine());
        System.out.println("Please enter passenger's ID (SSN)");
        passenger.setPId(in.nextInt());
        in.nextLine();
        passenger.setUserEmail(currentUser.getEmail()); //momken akhaly eluser ydakhal elemail bta3o abl ma ybda2 ydakhal elpassengers w astakhdem this email for getting the user ba w arboto blpassengers wkeda
        Passengers.add(passenger);
        // input handling+ special services w notes wlklam da
    }
}





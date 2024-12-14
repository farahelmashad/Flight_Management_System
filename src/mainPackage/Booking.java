package mainPackage;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

//white space issues // to be dealt with later!!
public class Booking {
    private int bookingID;
//    private ArrayList<Passenger> passengers;
    private Flight flight;
    private String flightNum;
   // private Seat seat;
    private User currentUser;
    private String bookingStatus ;
    private static int bookingCounter = 0;
    private int EconomySeats=0;
    private int BusinessSeats=0;
    private int FirstClassSeats=0;
    private int numberOfSeats;
    private boolean hasWheelchair;
    private boolean hasLounge;
    private boolean hasSpecialMeal;
    private boolean hasPet;

    ArrayList<Passenger> passengers = new ArrayList<>();

    //    ArrayList<Airline> Airlines = Airline.getAirlines();
    ArrayList<Airport> Airports = Airport.getAirports();
    public ArrayList<Passenger>Passengers=new ArrayList<>();
    public ArrayList<Flight> Flights=new ArrayList<Flight>();
    //    ArrayList<Seat> seat = Flight.Seats;
    private static ArrayList<Booking> bookings = new ArrayList<>();
    public ArrayList<User>users=new ArrayList<User>();
    public Booking() {
    }
    public Booking(int bookingID, Flight flight,  String bookingStatus){
        this.bookingID = bookingID;
        this.passengers = new ArrayList<>();
        this.flight = flight;
        this.bookingStatus = "Confirmed";

    }

    public int getBookingID(){
        return bookingID;
    }

    public void setBookingID(int bookingID) {
        this.bookingID = bookingID;
    }

    public ArrayList<Passenger> getPassenger() {
        return passengers;
    }

    public void setPassenger(ArrayList<Passenger> passengers) {
        this.passengers = passengers;
    }

    public void addPassenger(Passenger passenger) {
        this.passengers.add(passenger);
    }

    public int getBusinessSeats() {
        return BusinessSeats;
    }

    public void setEconomySeats(int economySeats) {
        EconomySeats = economySeats;
    }

    public void setBusinessSeats(int businessSeats) {
        BusinessSeats = businessSeats;
    }

    public void setFirstClassSeats(int firstClassSeats) {
        FirstClassSeats = firstClassSeats;
    }

    public void setHasWheelchair(boolean hasWheelchair) {
        this.hasWheelchair = hasWheelchair;
    }

    public void setHasLounge(boolean hasLounge) {
        this.hasLounge = hasLounge;
    }

    public void setHasSpecialMeal(boolean hasSpecialMeal) {
        this.hasSpecialMeal = hasSpecialMeal;
    }

    public void setHasPet(boolean hasPet) {
        this.hasPet = hasPet;
    }

    public int getEconomySeats() {
        return EconomySeats;
    }

    public int getFirstClassSeats() {
        return FirstClassSeats;
    }

    public boolean HasWheelchair() {
        return hasWheelchair;
    }

    public boolean HasLounge() {
        return hasLounge;
    }

    public boolean HasSpecialMeal() {
        return hasSpecialMeal;
    }

    public boolean HasPet() {
        return hasPet;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlightNum(String flightNum) {
        this.flightNum = flightNum;
    }

    public String getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(String bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public static ArrayList<Booking> getBookings() {
        return bookings;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public Booking(User currentUser){
        this.currentUser=currentUser;
        this.passengers = new ArrayList<>();
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
                        this.hasSpecialMeal=true;
                        System.out.println("Special Meal Requests Selected");
                        break;
                    case 2:
                        petTravel = true;
                        this.hasPet=true;
                        System.out.println("Pet Travel Selected");
                        break;
                    case 3:
                        wheelchair = true;
                        this.hasWheelchair=true;
                        System.out.println("Wheelchair Accessibility Selected");
                        break;
                    case 4:
                        loungeAccess = true;
                        this.hasLounge=true;
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
        for(int i=0;i<Flights.size();i++){
            if((Flights.get(i).getSpecialMealRequest()==specialMeal)&&(Flights.get(i).getHasPetTravel()==petTravel)&&(Flights.get(i).getHasLoungeAccess()==loungeAccess)&&(Flights.get(i).getHaswheelchair()==wheelchair)){
                for(Flight F:Flights){
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
        FlightSelection(AvailableFlights, currentUser);
    }
    public void FlightSelection(ArrayList<Flight> f, User currentUser){
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
                System.out.println("Special Services offered by the Airline: "+ f.get(i).getAirlineName());
                boolean offersServices=false;
                if(f.get(i).getSpecialMealRequest()){
                    System.out.println("Special Meal Requests");
                    offersServices=true;
                }
                if(f.get(i).getHaswheelchair()){
                    System.out.println("Wheelchair Accessibility");
                    offersServices=true;
                }
                if(f.get(i).getHasPetTravel()){
                    System.out.println("Pet Travel");
                    offersServices=true;
                }
                if(f.get(i).getHasLoungeAccess()){
                    System.out.println("Lounge Access");
                    offersServices=true;
                }
                if(offersServices==false){
                    System.out.println("The airline doesn't offer special services");
                }
                System.out.println("Baggage allowance (maximum number of bags per passenger) : "+f.get(i).getBaggageAllowance());
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

            this.flightNum=foundFlight.getFlightNumber();
        }
    }
    public void SeatSelection(Flight flight){
        ArrayList<Seat> flightSeats=flight.getSeats();
        int seatindex=-1;
        //ArrayList<Seat> chosenSeats=new ArrayList<>();
        Scanner in=new Scanner(System.in);
        System.out.println("How many seats do you want to choose: ");
        int number=in.nextInt();
        in.nextLine();

        while(number>flight.numberOfAvailableSeats()){
            System.out.println("Please enter a number that is less than or equal to the number of available seats ("+flight.numberOfAvailableSeats()+")");
            number=in.nextInt();
            in.nextLine();
        }
        this.numberOfSeats=number;
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

                if(chosenSeat.getSeatClass().trim().equalsIgnoreCase("First Class"))
                    this.FirstClassSeats++;
                else if(chosenSeat.getSeatClass().trim().equalsIgnoreCase("Business Class"))
                    this.BusinessSeats++;
                else
                    this.EconomySeats++;

                chosenSeat.setAvailable(false);
                flight.getSeatAvailability()[seatindex]=false; //hasa feh haga ghalat need to check this part
                System.out.println("Seat chosen successfully!");
                // chosenSeats.add(chosenSeat);
                validSeatChosen = true;
//                    displaySeats();

            }

//                passenger.setSeat(chosenSeat);

        }
//            getUserInformation(chosenSeats,number); when the method is added , hab3at elchosen seats w the number of chosen seats, n loop aala aadad el number of seats aashan
        //w n display for each seat : passenger details for seat no. (chosenSeats.get(i).getSeatNumber())

    }

    public Flight getFlightNumber(){
        for(Flight f : Flights){
            if(f.getFlightNumber().equalsIgnoreCase(flightNum)){
                return f;
            }
        }
        return null;
    }
    public void bookFlight(ArrayList<Flight> availableFlights , User currentUser , ArrayList<Seat> chosenSeats ){
        flightSearch();
        for(User u:users){
            if(u.isLoggedIn()==true){
                this.currentUser=u;
                break;
            }
        }
        for (int i = 0; i < numberOfSeats; i++) {
            Passenger passenger = new Passenger();
            getPassengerInformation();
            passenger.assignSeatToPassenger(chosenSeats.get(i));
            Passengers.add(passenger);
        }
        Booking booking = new Booking();
        booking.setBookingID(bookingCounter++);
        booking.setFlightNum(flightNum);
        booking.setPassenger(Passengers);
        booking.setBusinessSeats(BusinessSeats);
        booking.setEconomySeats(EconomySeats);
        booking.setFirstClassSeats(FirstClassSeats);
        booking.setHasLounge(hasLounge);
        booking.setHasPet(hasPet);
        booking.setHasSpecialMeal(hasSpecialMeal);
        booking.setHasWheelchair(hasWheelchair);
        booking.setBookingStatus("Confirmed");

        bookings.add(booking);
        currentUser.addBooking(booking);

        System.out.println("Booking confirmed! Your booking ID is " + booking.getBookingID());
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

    public Passenger getPassengerInformation(){
        Scanner in=new Scanner(System.in);
        System.out.println("Enter the number of passengers to book for: ");
        int NoPass = in.nextInt();
        Passenger passenger = new Passenger();

        for (int i = 0; i < NoPass; i++) {
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
//        passenger.setUserEmail(currentUser.getEmail()); //momken akhaly eluser ydakhal elemail bta3o abl ma ybda2 ydakhal elpassengers w astakhdem this email for getting the user ba w arboto blpassengers wkeda
            //passenger.assignSeatToPassenger();
            Passengers.add(passenger);
            // input handling+ special services w notes wlklam da
        }
        return passenger;
    }


    public void cancelBooking(){
        if(bookingStatus.equals("Cancelled")){
            System.out.println("Your Booking is already cancelled");
            return;
        }
        for(Passenger passenger : Passengers){
            Seat seat = passenger.getSeat();
            if(seat != null){
                seat.setAvailable(true);
            }
        }
        bookingStatus = "Cancelled";
        System.out.println("Your booking has been cancelled successfully. ");

    }

    public void changeSeat(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Available seats for switching : ");
        flight = getFlightNumber();
        displaySeats(flight);
        for (Passenger passenger : Passengers) {
            if (passenger.getSeat() != null) {
                System.out.println("Passenger " + passenger.getName() + " is currently assigned seat " + passenger.getSeat().getSeatNumber());
            }

            System.out.println("Enter the new seat number for " + passenger.getName() + ": ");
            String NewSeatNo = scanner.nextLine();

            boolean validSwitch = false;
            for (Seat s : flight.getSeats()) {
                if (s.getSeatNumber().equalsIgnoreCase(NewSeatNo) && s.isAvailable()) {
                    if(passenger.getSeat()!=null){
                        passenger.getSeat().setAvailable(true);
                        System.out.println("Previous seat " + passenger.getSeat().getSeatNumber() + " is now available.");
                    }
                    passenger.assignSeatToPassenger(s);
                }

                System.out.println("Seat for " + passenger.getName() + " successfully changed to: " + s.getSeatNumber());
                validSwitch = true;
                break;
            }
            if (!validSwitch) {
                System.out.println("Invalid or unavailable seat. Please try again. ");
            }
        }
    }
    public static void listAllBookings(){ //3ayza a5aleeha ta5od el user id men el sign up class 3shan t list el booking bt3t el user da
        if(bookings.isEmpty()){
            System.out.println("No bookings exist");

        }
        System.out.println("All Bookings: ");
        for(Booking b : bookings){
            b.BookingConfirmation();
        }

    }

    public void BookingConfirmation(){
        System.out.println("Booking Details: ");
        System.out.println("Booking ID : " + bookingID);
        System.out.println("Passengers: ");
        for (Passenger passenger : passengers) {
            System.out.println("- " + passenger.getName());
        }
        System.out.println("Flight Number : " + flightNum);
        System.out.println("Departure : " + flight.getDepartureAirport());
        System.out.println("Arrival : " + flight.getArrivalAirport());
        System.out.println("Departure Time : " + flight.getDepartureTime());
        System.out.println("Arrival Time : " + flight.getArrivalTime());
        System.out.println("Booking status : " + bookingStatus);
    }
}
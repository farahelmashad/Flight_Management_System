package mainPackage;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Booking {
    private int bookingID;
    private Flight flight;
    private String flightNum;
    private User currentUser;
    private String bookingStatus ;
    private static int bookingCounter = 1;
    private int EconomySeats=0;
    private int BusinessSeats=0;
    private int FirstClassSeats=0;
    private int numberOfSeats;
    private boolean hasWheelchair;
    private boolean hasLounge;
    private boolean hasSpecialMeal;
    private boolean hasPet;
    public ArrayList<Passenger>Passengers=new ArrayList<>();

    ArrayList<Passenger> passengers = new ArrayList<>();//eh elfar2?
    public Booking(int bookingID){
        this.bookingID=bookingID;
    }

    ArrayList<Airport> Airports = GlobalData.airports;
    public ArrayList<Flight> Flights=GlobalData.flights;
    // private static ArrayList<Booking> bookings = new ArrayList<>();
    public ArrayList<User>users=GlobalData.users;
    public Booking() {
    }
    public Booking(int bookingID, Flight flight,  String bookingStatus){
        this.bookingID = bookingID;
        this.passengers = new ArrayList<>();
        this.flight = flight;
        this.bookingStatus = "Confirmed";
        bookingCounter++;

    }

    public Booking(int bookingID, User currentUser, boolean hasLounge, boolean hasSpecialMeal, boolean hasPet, boolean hasWheelchair, int numberOfSeats, int economySeats, int businessSeats, int firstClassSeats, String flightNum) {
        this.bookingID = bookingID;
        this.currentUser = currentUser;
        this.hasLounge = hasLounge;
        this.hasSpecialMeal = hasSpecialMeal;
        this.hasPet = hasPet;
        this.hasWheelchair = hasWheelchair;
        this.numberOfSeats = numberOfSeats;
        EconomySeats = economySeats;
        BusinessSeats = businessSeats;
        FirstClassSeats = firstClassSeats;
        this.flightNum = flightNum;
        bookingCounter++;
    }

    public int getBookingID(){
        return bookingID;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
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

    public ArrayList<Passenger> getPassengers() {
        return passengers;
    }

    public String getFlightNum() {
        return flightNum;
    }

    public User getCurrentUser() {
        return currentUser;
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

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
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

    public boolean isHasWheelchair() {
        return hasWheelchair;
    }

    public boolean isHasLounge() {
        return hasLounge;
    }

    public boolean isHasSpecialMeal() {
        return hasSpecialMeal;
    }

    public boolean isHasPet() {
        return hasPet;
    }

    public static int getBookingCounter() {
        return bookingCounter;
    }


    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public Booking(User currentUser){
        this.currentUser=currentUser;
        this.passengers = new ArrayList<>();
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
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
        ArrayList<Flight> AvailableFlights = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

        for (Flight flight : Flights) {
            boolean matchesServices = (flight.getSpecialMealRequest() == specialMeal) ||
                    (flight.getHasPetTravel() == petTravel) ||
                    (flight.getHasLoungeAccess() == loungeAccess) ||
                    (flight.getHaswheelchair() == wheelchair);

            boolean matchesAirports = flight.getDepartureAirport().equalsIgnoreCase(departureAirport) &&
                    flight.getArrivalAirport().equalsIgnoreCase(arrivalAirport);

            LocalDateTime flightDateTime = LocalDateTime.parse(flight.getDepartureTime(), formatter);
            LocalDate flightDepartureDate = flightDateTime.toLocalDate();
            String formattedFlightDate = flightDepartureDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

            String formattedDepDate = deptTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

            if (matchesServices && matchesAirports && formattedDepDate.equals(formattedFlightDate)) {
                AvailableFlights.add(flight);
            }
        }

        for (Flight flight : AvailableFlights) {
            System.out.println(flight.getFlightNumber());
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
        }
        else{
            System.out.println("--------------------Available Flights----------------------");
            for(int i=0;i<f.size();i++){
                if(f.get(i).numberOfAvailableSeats()>0){
                    System.out.println("---------------------------------------------------------------------------");
                    f.get(i).displayFlightDetails();
                    System.out.println("Special Services offered by the Airline "+ f.get(i).getAirlineName()+":");
                    boolean offersServices=false;
                    if(f.get(i).getSpecialMealRequest()){
                        System.out.println("-Special Meal Requests");
                        offersServices=true;
                    }
                    if(f.get(i).getHaswheelchair()){
                        System.out.println("-Wheelchair Accessibility");
                        offersServices=true;
                    }
                    if(f.get(i).getHasPetTravel()){
                        System.out.println("-Pet Travel");
                        offersServices=true;
                    }
                    if(f.get(i).getHasLoungeAccess()){
                        System.out.println("-Lounge Access");
                        offersServices=true;
                    }
                    if(offersServices==false){
                        System.out.println("The airline doesn't offer special services");
                    }
                    System.out.println("Baggage allowance (maximum number of bags per passenger) : "+f.get(i).getBaggageAllowance());
                    System.out.println("Number of available seats : "+f.get(i).numberOfAvailableSeats());


                } }
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

            flightNum=foundFlight.getFlightNumber();
            flight=foundFlight;

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
                chosenSeats.add(chosenSeat);
                validSeatChosen = true;

            }


        }
        bookFlight(chosenSeats);
    }

    public Flight getFlightNumber(){
        Flight flight1 = null;
        for(Flight f : Flights){
            if(f.getFlightNumber().equalsIgnoreCase(flightNum)){
                flight1=f;
                break;
            }

        }
        return flight1 ;
    }
    public void getLoggedInUser(){
        for(User u:users){
            if(u.isLoggedIn()==true){
                this.currentUser=u;
                break;
            }
        }

    }
    public void bookFlight( ArrayList<Seat> chosenSeats ){
        //flightSearch();
        for(User u:users){
            if(u.isLoggedIn()==true){
                this.currentUser=u;
                break;
            }
        }
        for (int i = 0; i < numberOfSeats; i++) {
            //Passenger passenger = new Passenger();
            Passenger passenger=getPassengerInformation();
            passenger.assignSeatToPassenger(chosenSeats.get(i));
            Passengers.add(passenger);
        }

        //   Booking booking = new Booking();
        setBookingID(bookingCounter++);
        setFlightNum(flightNum);
        setPassenger(Passengers);
        setBusinessSeats(BusinessSeats);
        setEconomySeats(EconomySeats);
        setFirstClassSeats(FirstClassSeats);
        setHasLounge(hasLounge);
        setHasPet(hasPet);
        setHasSpecialMeal(hasSpecialMeal);
        setHasWheelchair(hasWheelchair);
        setBookingStatus("Confirmed");

        // bookings.add(booking);
        currentUser.addBooking(this);
        AirportFileWriter.writeBookingsToFile();
        System.out.println("Booking confirmed! ");
        BookingConfirmation();
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
        Passenger passenger = new Passenger();

        System.out.println("Please enter passenger's name: ");
        String PassName = in.nextLine();
        passenger.setName(PassName);

        String gender;
        while(true) {
            System.out.println("Please enter passenger's gender (M/F)");
            gender = in.nextLine().trim().toLowerCase();
            if (gender.equals("m") || gender.equals("f")) {
                passenger.setGender(gender);
                break;
            } else {
                System.out.println("Invalid choice. Please enter 'm' or 'f'.");
            }
        }
        int phoneNo;
        while(true) {
            System.out.println("Please enter passenger's phone number : ");
            String phoneInput = in.nextLine().trim();
            if(phoneInput.matches("\\d{11}")){
                phoneNo = Integer.parseInt(phoneInput);
                passenger.setPhoneNumber(phoneNo);
                break;
            }else {
                System.out.println("Invalid phone number. It must be exactly 11 digits.");
            }
        }
        String DateOfBirth;
        while(true){
            System.out.println("Please enter passenger's date of birth in the format (dd/mm/yyyy)");
            DateOfBirth = in.nextLine().trim();
            if (DateOfBirth.matches("\\d{2}/\\d{2}/\\d{4}")) {
                passenger.setDateOfBirth(DateOfBirth);
                break;
            } else {
                System.out.println("Invalid date of birth format. Please use (dd/mm/yyyy).");
            }
        }

        System.out.println("Please enter passenger's ID (SSN)");
        passenger.setPId(in.nextInt());
        in.nextLine();
        passengers.add(passenger);
        return passenger;

    }


    public void cancelBooking(){

        getLoggedInUser();

        for (Booking b : currentUser.getBookings()) {
            b.BookingConfirmation();
        }
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the booking id of the booking you want to cancel :");
        int bkId = in.nextInt();
        Booking CurrBook=null;
        for (Booking b : currentUser.getBookings()) {
            if(b.bookingID==bkId) {
                CurrBook=b;
            }
        }
        if(CurrBook!=null) {
            if (CurrBook.bookingStatus.equalsIgnoreCase("Confirmed")) {
                currentUser.getBookings().remove(CurrBook);
                // CurrBook.bookingStatus = "Cancelled";
                for (Passenger passenger : CurrBook.Passengers) {
                    Seat seat = passenger.getSeat();
                    if (seat != null) {
                        seat.setAvailable(true);
                    }
                }
                AirportFileWriter.writeBookingsToFile();
                System.out.println("Your booking has been cancelled successfully. ");
            }
        }
        else{
            System.out.println("Your Booking is already cancelled");
        }

    }

    public void changeSeat(){
        Scanner scanner = new Scanner(System.in);

        getLoggedInUser();
        for (Booking b : currentUser.getBookings()) {
            b.BookingConfirmation();
        }
        System.out.println("Enter the id booking of the booking you want to modify :");
        int bkId = scanner.nextInt();
        Booking CurrBook=null;
        for (Booking b : currentUser.getBookings()) {
            if(b.bookingID==bkId) CurrBook=b;
        }
        if(CurrBook!=null){
            System.out.println("Available seats for switching : ");
            flight = CurrBook.getFlightNumber();
            // Flights.get(0);

            displaySeats(flight);
            Scanner scanner1 = new Scanner(System.in);
            System.out.println("Choose which passenger you want to change the seat for : ");
            String PassName = scanner1.nextLine();

            Passenger CurrPass = null;
            for (Passenger passenger : CurrBook.passengers) {
                if(passenger.getName().equalsIgnoreCase(PassName)){
                    CurrPass = passenger;
                }
            }
            System.out.println("Enter the new seat number to switch to : " );
            String NewSeatNo = scanner1.nextLine();

            boolean validSwitch = false;
            for (Seat s : flight.getSeats()) {
                if (s.getSeatNumber().equalsIgnoreCase(NewSeatNo) && s.isAvailable()) {
                    if(CurrPass.getSeat()!=null){
                        CurrPass.getSeat().setAvailable(true);
                        System.out.println("Previous seat " + CurrPass.getSeat().getSeatNumber() + " is now available.");
                    }

                }

                break;
            }
            for (Seat s : flight.getSeats()) {
                if (s.getSeatNumber().equalsIgnoreCase(NewSeatNo) && s.isAvailable()) {
                    CurrPass.assignSeatToPassenger(s);
                    AirportFileWriter.writeBookingsToFile();
                    AirportFileWriter.writeFlightsToFile();
                    System.out.println("Seat for " + CurrPass.getName() + " successfully changed to: " + s.getSeatNumber());
                    validSwitch = true;
                    break;
                }
            }
            if (!validSwitch) {
                System.out.println("Invalid or unavailable seat. Please try again. ");
            }

        }

    }



    public void listAllBookings(){
        getLoggedInUser();

        if(currentUser.getBookings().isEmpty()){
            System.out.println("No bookings exist");

        } else {
            System.out.println("All Bookings: ");
            for (Booking b : currentUser.getBookings()) {
                b.BookingConfirmation();
            }
        }
    }

    public void BookingConfirmation() {
        //getLoggedInUser();
        // flight = getFlightNumber();
        if (flight == null) {
            System.out.println("Booking confirmation failed: Missing flight or booking information.");

        }
        else {

            PaymentPreProcessing paymentProcessor = new PaymentPreProcessing(flight, this);


            System.out.println("Booking ID: " + getBookingID());
            flight.displayFlightDetails();
            System.out.println("Booking Status: " + getBookingStatus());
            System.out.println("------------------------------------------");
            System.out.println("Total Cost (Incl. Services): " + paymentProcessor.calculateSubTotal());
            System.out.println("------------------------------------------");
            System.out.println("Passengers:");
            for (Passenger passenger : getPassenger()) {
                System.out.println("- " + passenger.getName());
            }
            System.out.println("==========================================");
        }
    }}
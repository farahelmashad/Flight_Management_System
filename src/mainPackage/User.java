package mainPackage;

import java.util.ArrayList;

public class User {
    protected String Email;
    protected String Password;
    private String Gender;
    private int PhoneNumber;
    private String name;
    private String DateOfBirth;
    private ArrayList<Passenger> p = new ArrayList<>();
    private ArrayList<Booking> bookings = new ArrayList<>();
    private Flight SelectedFlight;
    private int User_Id;
    public static int UserCount=0;
    private boolean loggedIn=false;

    public User() {
        User_Id = ++UserCount;
    }
    public void addBooking(Booking booking){
        this.bookings.add(booking);
    }
    public User(String email, String password) {
        Email = email;
        Password = password;
    }

    public User(String email, String password, ArrayList<Passenger> p) {
        Email = email;
        Password = password;
        this.p = p;
    }

    public User(String email, String password, ArrayList<Passenger> p, int user_Id) {
        Email = email;
        Password = password;
        this.p = p;

    }

    public User(String email, String password, String gender, int phoneNumber, String name) {
        Email = email;
        Password = password;
        Gender = gender;
        PhoneNumber = phoneNumber;
        this.name = name;
    }

    public User(String email, String password, String gender, int phoneNumber, String dateOfBirth, ArrayList<Passenger> p, ArrayList<Booking> bookings, Flight selectedFlight, int user_Id, boolean loggedIn) {
        Email = email;
        Password = password;
        Gender = gender;
        PhoneNumber = phoneNumber;
        DateOfBirth = dateOfBirth;
        this.p = p;
        this.bookings = bookings;
        SelectedFlight = selectedFlight;
        User_Id = user_Id;
        this.loggedIn = loggedIn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUserID() {
        return User_Id;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public Flight getSelectedFlight() {
        return SelectedFlight;
    }

    public ArrayList<Passenger> getP() {
        return p;
    }

    public ArrayList<Booking> getBookings() {
        return bookings;
    }

    public void setP(ArrayList<Passenger> p) {
        this.p = p;
    }
    public void addPassenger(Passenger passenger) {
        this.p  .add(passenger);
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public String getDateOfBirth() {
        return DateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        DateOfBirth = dateOfBirth;
    }

    public int getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }
}
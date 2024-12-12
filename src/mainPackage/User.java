package mainPackage;

import java.util.ArrayList;

public class User {
    protected String Email;
    protected String Password;
    private ArrayList<Passenger> p = new ArrayList<>();
    private ArrayList<Booking> bookings = new ArrayList<>();
    private Flight SelectedFlight;
    private int User_Id;
    public static int UserCount=0;

    public User() {
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
        User_Id = ++UserCount;
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
}

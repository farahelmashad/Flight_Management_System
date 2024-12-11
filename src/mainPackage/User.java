package mainPackage;

import java.util.ArrayList;

public class User {
    protected String Email;
    protected String Password;
    private ArrayList<Passenger> p = new ArrayList<>();

    public User() {
    }

    public User(String email, String password, ArrayList<Passenger> p) {
        Email = email;
        Password = password;
        this.p = p;
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

    public ArrayList<Passenger> getP() {
        return p;
    }

    public void setP(ArrayList<Passenger> p) {
        this.p = p;
    }
    public void addPassenger(Passenger passenger) {
        this.p  .add(passenger);
    }
}

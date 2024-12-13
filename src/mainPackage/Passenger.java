package mainPackage;

public class Passenger {
    private String Name;
    private String Gender;
    private int PhoneNumber;
    private String DateOfBirth;
    private int PId;
    private Seat seat;



    public Passenger(){
    }
    public Passenger(String Name, String Gender, int PhoneNumber, String DateOfBirth) {
        this.Name = Name;
        this.Gender = Gender;
        this.PhoneNumber = PhoneNumber;
        this.DateOfBirth = DateOfBirth;
    }



    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public int getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getDateOfBirth() {
        return DateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        DateOfBirth = dateOfBirth;
    }

    public int getPId() {
        return PId;
    }

    public void setPId(int PId) {
        this.PId = PId;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }
    public void assignSeatToPassenger(Seat chosenSeat) {
        this.seat = chosenSeat;
        chosenSeat.setAvailable(false); // Mark seat as unavailable
        System.out.println("Seat " + chosenSeat.getSeatNumber() + " assigned to " + this.Name);
    }

    /*
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
*/
}

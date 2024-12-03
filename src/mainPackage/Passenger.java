package mainPackage;

public class Passenger extends User{
    private String Name;
    private String Gender;
    private int PhoneNumber;
    private String DateOfBirth;
    private int PId;


    public Passenger() {
    }

    public Passenger(String email, String password, String name, String gender, int phoneNumber, String dateOfBirth ,int id) {
        super(email, password);
        Name = name;
        Gender = gender;
        PhoneNumber = phoneNumber;
        DateOfBirth = dateOfBirth;
        PId=id;
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



}

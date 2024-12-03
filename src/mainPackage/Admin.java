package mainPackage;

public class Admin extends User{
    private int AdminId;

    public Admin() {
    }

    public Admin(String email, String password, int adminId) {
        super(email, password);
        AdminId = adminId;
    }

    public int getAdminId() {
        return AdminId;
    }

    public void setAdminId(int adminId) {
        AdminId = adminId;
    }
}

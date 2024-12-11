package mainPackage;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileReader {

    public static List<Admin> loadAdmins(String fileName) {
        List<Admin> admins = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new java.io.FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] details = line.split(",");
                if (details.length == 2) {
                    String email = details[0];
                    String password = details[1];
                    Admin admin = new Admin(email, password);
                    admins.add(admin);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return admins;
    }

    public static void main(String[] args) {
        List<Admin> admins = loadAdmins("admins.txt");

    }
}

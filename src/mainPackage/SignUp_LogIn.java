package mainPackage;

import java.io.Console;
import java.util.Scanner;

public class SignUp_LogIn {

    static Passenger[] P =new Passenger[100];
    public static int numofusers;
    private static Scanner scanner = new Scanner(System.in);
    public  boolean SignUp() {
        String Email;
        String Pass;
        String name;
        String Gender;
        int PNum;
        int id;
        Passenger m = new Passenger();
        System.out.println("Please Enter Your Name");
        name = scanner.nextLine();
        m.setName(name);
        while (true) {
            System.out.println(" Please Enter Your Email");
            Email = scanner.nextLine();
            if (!Email.endsWith("@gmail.com")) {
                System.out.println("Invalid Email Format. Email must end with '@gmail.com'. Please Try Again!");
                continue;
            }
            boolean b = false;
            for (int i = 0; i < numofusers; i++) {
                if (P[i] != null && Email.equals(P[i].getEmail())) {
                    b = true;
                    System.out.println("Invalid Email ,Please Try Again!! ");
                }
            }
            if (b == false) {
                m.setEmail(Email);
                break;

            }


        }
        Console console = System.console();
        if (console != null) {
            char[] passwordArray;
            while (true) {
                System.out.println("Please Set A Password");
                passwordArray = console.readPassword();
                Pass = new String(passwordArray);
                if (Pass.length() >= 6) {
                    m.setPassword(Pass);
                    break;
                } else {
                    System.out.println("Password must be at least 6 characters long. Please Try Again!");
                }
            }
        }
        else {
            while (true) {
                System.out.println("Please Set A Password (Unmasked in IDE)");
                Pass = scanner.nextLine();

                if (Pass.length() >= 6) {
                    m.setPassword(Pass);
                    break;
                } else {
                    System.out.println("Password must be at least 6 characters long. Please Try Again!");
                }
            }
        }



        while (true) {
            System.out.println("Enter Your Gender :(Male/Female)");
            Gender = scanner.nextLine().trim();
            if (Gender.equalsIgnoreCase("Male") || Gender.equalsIgnoreCase("Female")) {
                m.setGender(Gender);
                break;

            } else {
                System.out.println("Invalid Choice,Please Try Again!!");

            }
        }
        while (true) {
            System.out.println("Enter Your Phone Number (must be numeric and at least 11 digits):");
            String phoneInput = scanner.nextLine().trim();

            if (phoneInput.matches("\\d{11,}")) {
                PNum = Integer.parseInt(phoneInput);
                m.setPhoneNumber(PNum);
                break;
            } else {
                System.out.println("Invalid Phone Number. Please Try Again!");
            }
        }

        System.out.println("Enter Your Date Of Birth");
        String BDate = scanner.nextLine();
        m.setDateOfBirth(BDate);
        m.setPId(numofusers + 1);
        P[numofusers] = m;
        numofusers += 1;
        System.out.println("Sign Up Successfully !!");
        System.out.println("Please Log In");
        return true;
    }
    public void display() {
        System.out.println(P[numofusers - 1].getName());
    }
    public boolean LogIn_P() {
        if (numofusers == 0) {
            System.out.println("No users found! Please sign up first.");
            return false;
        }

        System.out.println("Enter Your Email:");
        String email = scanner.nextLine();

        System.out.println("Enter Your Password:");
        String password = scanner.nextLine();

        for (int i = 0; i < numofusers; i++) {
            if (P[i] != null && email.equals(P[i].getEmail()) && password.equals(P[i].getPassword())) {
                System.out.println("Log-In Successful! Welcome back, " + P[i].getName() + "!");
                return true;
            }
        }

        System.out.println("Invalid Email or Password. Please try again.");
        return false;
    }
    public boolean LogIn_A(){
        System.out.println("Enter Your Email:");
        String email = scanner.nextLine();

        System.out.println("Enter Your Password:");
        String password = scanner.nextLine();
        return true;
    }
    public void MainMenu(){
        while (true) {
            System.out.println("\nWelcome to the Flight Booking System");
            System.out.println("1. Log In");
            System.out.println("2. Sign Up");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid Input. Please enter a valid choice.");
                continue;
            }

            switch (choice) {
                case 1:
                    String userType = "";
                    while (true) {
                        System.out.println("Do You Want To Enter As An Admin Or A Passenger? (Type 'Admin' or 'Passenger')");
                        userType = scanner.nextLine().trim();

                        if (userType.equalsIgnoreCase("Admin")) {
                            if (LogIn_A()) {
                                // Admin section logic here
                                System.out.println("Done!!! Admin Section");
                            }
                            break;
                        } else if (userType.equalsIgnoreCase("Passenger")) {
                            if (LogIn_P()) {
                                // Passenger section logic here
                                System.out.println("Done!!! Passenger Section");
                            }
                            break;
                        } else {
                            System.out.println("Invalid input! Please enter 'Admin' or 'Passenger'.");
                        }
                    }
                    break;


                case 2:
                    if (SignUp() && LogIn_P()) {
                        System.out.println("Done22222!!!");; // Automatically logs the user in after sign-up
                    }
                    break;

                case 3:
                    System.out.println("Thank you for using the Flight Booking System. Goodbye!");
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}

package mainPackage;

import java.io.Console;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;


public class SignUp_LogIn {

    ArrayList<Admin> admins = GlobalData.admins;
    ArrayList<User> users = GlobalData.users;
    Admin a=new Admin();
    Booking b =new Booking ();

    private int thisUserIndex = -1;
    private static Scanner scanner = new Scanner(System.in);

    public boolean SignUp() {
        String email;
        String pass;
        String name;
        String gender;
        String dateOfBirth;
        long phoneNumber;


        User newUser = new User();
        while (true) {
            System.out.println("Please Enter Your Name (Only letters and spaces are allowed):");
            name = scanner.nextLine().trim();

            if (name.matches("[a-zA-Z\\s]+")) { // Regular expression to allow letters and spaces only
                newUser.setName(name);
                break;
            } else {
                System.out.println("Invalid Name. Please Enter a Valid Name!");
            }
        }



        while (true) {
            System.out.println("Please Enter Your Email:");
            email = scanner.nextLine();

            if (!email.endsWith("@gmail.com")) {
                System.out.println("Invalid Email Format. Email must end with '@gmail.com'. Please Try Again!");
                continue;
            }

            boolean emailExists = false;
            for (User u : users) {
                if (u.getEmail().equals(email)) {
                    emailExists = true;
                    break;
                }
            }

            if (emailExists) {
                System.out.println("Invalid Email, Email already exists. Please Try Again!");
            } else {
                newUser.setEmail(email);
                break;
            }
        }

        Console console = System.console();
        if (console != null) {
            while (true) {
                System.out.println("Please Set A Password:");
                char[] passwordArray = console.readPassword();
                pass = new String(passwordArray);

                if (pass.length() >= 6) {
                    newUser.setPassword(pass);
                    break;
                } else {
                    System.out.println("Password must be at least 6 characters long. Please Try Again!");
                }
            }
        } else {
            while (true) {
                System.out.println("Please Set A Password (Unmasked in IDE):");
                pass = scanner.nextLine();

                if (pass.length() >= 6) {
                    newUser.setPassword(pass);
                    break;
                } else {
                    System.out.println("Password must be at least 6 characters long. Please Try Again!");
                }
            }
        }


        while (true) {
            System.out.println("Enter Your Gender (Male/Female):");
            gender = scanner.nextLine().trim();

            if (gender.equalsIgnoreCase("Male") || gender.equalsIgnoreCase("Female")) {
                newUser.setGender(gender);
                break;
            } else {
                System.out.println("Invalid Choice. Please Try Again!");
            }
        }

        while (true) {
            System.out.println("Enter Your Phone Number (must be numeric and at least 11 digits):");
            String phoneInput = scanner.nextLine().trim();

            if (phoneInput.matches("\\d{11,}")) {
                phoneNumber = Long.parseLong(phoneInput);
                newUser.setPhoneNumber(phoneNumber);
                break;
            } else {
                System.out.println("Invalid Phone Number. Please Try Again!");
            }
        }

        while (true) {
            try {
                System.out.println("Enter Your Date Of Birth (dd-MM-yyyy):");
                dateOfBirth = scanner.nextLine().trim();

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                LocalDate dob = LocalDate.parse(dateOfBirth, formatter);

                LocalDate today = LocalDate.now();
                Period period = Period.between(dob, today);

                if (period.getYears() < 18) {
                    System.out.println("You must be at least 18 years old. Please try again.");
                } else {
                    newUser.setDateOfBirth(dateOfBirth);
                    break;
                }
            } catch (Exception e) {
                System.out.println("Invalid Date format. Please enter in dd-MM-yyyy format.");
            }
        }




        users.add(newUser);

        System.out.println("Sign Up Successfully!");
        System.out.println("Please Log In.");
        return true;
    }

    public boolean LogIn_A() {
        while (true) {
            System.out.println("Enter Your Email:");
            String email = scanner.nextLine();

            System.out.println("Enter Your Password:");
            String password = scanner.nextLine();

            for (Admin admin : admins) {
                if (email.equals(admin.getEmail()) && password.equals(admin.getPassword())) {
                    System.out.println("Admin Log-In Successful! Welcome " );
                    return true;
                }
            }

            System.out.println("Invalid Admin Email or Password.");
            System.out.println("If you want to exit, type 'exit', or press Enter to try again.");

            String exitChoice = scanner.nextLine().trim();
            if (exitChoice.equalsIgnoreCase("exit")) {
                return false;
            }
        }
    }


    public boolean LogIn_U() {
        if (users.isEmpty()) {
            System.out.println("No users found! Please sign up first.");
            return false;
        }

        while (true) {
            System.out.println("Enter Your Email:");
            String email = scanner.nextLine();

            System.out.println("Enter Your Password:");
            String password = scanner.nextLine();

            for (User u : users) {
                if (email.equals(u.getEmail()) && password.equals(u.getPassword())) {
                    System.out.println("Log-In Successful! Welcome back ");
                    u.setLoggedIn(true);
                    return true;
                }
            }

            System.out.println("Invalid Email or Password.");
            System.out.println("If you want to exit, type 'exit', or press Enter to try again.");

            String exitChoice = scanner.nextLine().trim();
            if (exitChoice.equalsIgnoreCase("exit")) {
                return false;
            }
        }
    }


    public void MainMenu() {
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
                System.out.println("Invalid Input. Please Enter a Valid Choice.");
                continue;
            }

            switch (choice) {
                case 1:
                    while (true) {
                        System.out.println("Do You Want To Enter As An Admin Or A User? (Type 'Admin' or 'User')");
                        String userType = scanner.nextLine().trim();

                        if (userType.equalsIgnoreCase("Admin")) {
                            if (LogIn_A()) {
                                System.out.println("Welcome to the Admin Panel.");
                                a.Adminpanel();
                            }
                            break;
                        } else if (userType.equalsIgnoreCase("User")) {
                            if (LogIn_U()) {
                                System.out.println("Welcome to the User Section.");
                                this.MainMenu2();
                            }
                            break;
                        } else {
                            System.out.println("Invalid Input! Please Enter 'Admin' or 'User'.");
                        }
                    }
                    break;

                case 2:
                    if (SignUp() && LogIn_U()) {
                        System.out.println("Logged In After Sign-Up.");
                        this.MainMenu2();
                    }
                    break;

                case 3:
                    System.out.println("Thank You for Using the Flight Booking System. Goodbye!");
                    return;

                default:
                    System.out.println("Invalid Choice. Please Try Again.");
            }
        }
    }

    public void MainMenu2(){
        while (true) {
            System.out.println("\nWelcome To Our Flight Management System");
            System.out.println("1. Search And Book A Flight");
            System.out.println("2. Modify Your Flight");
            System.out.println("3. List All Bookings");
            System.out.println("4. Cancel Your Booking");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");



            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid Input. Please Enter a Valid Choice.");
                continue;
            }


            // Process the valid choice
            switch (choice) {
                case 1:
                    b.flightSearch();

                    break;

                case 2:
                    b.changeSeat();

                    break;

                case 3:
                    b.listAllBookings();

                    break;

                case 4:
                    b.cancelBooking();
                    break;

                case 5:
                    // Exit the system
                    System.out.println("Exiting the Flight Management System. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid Choice. Please Try Again.");


            }

        }


    }}
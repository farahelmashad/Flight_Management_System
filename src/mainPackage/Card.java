package mainPackage;
import java.util.Scanner ;

public class Card implements PaymentMethod{
    private String ownerName;
    private String number;
    private String expirationDate;
    private String cvv;
    private double baseFare;
    private double amount;
    public static final float FEES_PERCENTAGE = 0.03f;
    private Flight flight;
    private PaymentPreProcessing subTotal;

    public Card() {
    }

    public Card(String ownerName, String number, String expirationDate, String cvv, double baseFare, double amount, Flight flight, PaymentPreProcessing subTotal) {
        this.ownerName = ownerName;
        this.number = number;
        this.expirationDate = expirationDate;
        this.cvv = cvv;
        this.baseFare = baseFare;
//        this.amount = subTotal.calculateSubTotal();
        this.flight = flight;
    }

    public void setBaseFare(double baseFare) {
        this.baseFare = flight.getBaseFare();
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    @Override
    public double calculateTotalCost(double baseFare) {
        double fees = baseFare * FEES_PERCENTAGE;
        return amount + fees;
    }

    @Override
    public void getInfo () {
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.println("Enter card holder name: ");
            String name = input.nextLine();
            if (name.matches("[a-zA-Z ]+") && !name.trim().isEmpty()) {
                this.ownerName = name;
                break;
            } else {
                System.out.println("Invalid name. Please enter a valid name (letters only).");
            }
        }

        while (true) {
            System.out.println("Enter card number (16 digits): ");
            String cardNumber = input.next();
            if (cardNumber.matches("\\d{16}")) {
                this.number = cardNumber;
                break;
            } else {
                System.out.println("Invalid card number. Please enter exactly 16 digits.");
            }
        }

        while (true) {
            System.out.println("Enter expiration date (MM/YY): ");
            String date = input.next();
            if (date.matches("(0[1-9]|1[0-2])/\\d{2}")) {
                this.expirationDate = date;
                break;
            } else {
                System.out.println("Invalid date. Please enter in the format MM/YY.");
            }
        }


        while (true) {
            System.out.println("Enter CVV (3 digits): ");
            String cvvCode = input.next();
            if (cvvCode.matches("\\d{3}")) {
                this.cvv = cvvCode;
                break;
            } else {
                System.out.println("Invalid CVV. Please enter exactly 3 digits.");
            }
        }
    }

    @Override
    public void process () {
        System.out.println("Total cost : " + calculateTotalCost(baseFare));
    }

    @Override
    public String getMethodName () {
        return "Card";
    }

}

package mainPackage;
import java.util.Scanner;

public class ApplePay implements PaymentMethod {

    private String email;
    private final static float FEES_PERCENTAGE = 0.04f;
    private double amount;
    private PaymentPreProcessing subTotal;

    public ApplePay(String email, double amount) {
        this.email = email;
        this.amount = amount;
    }

    public ApplePay(PaymentPreProcessing subTotal) {
        this.subTotal = subTotal;
        this.amount = subTotal.calculateSubTotal();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public PaymentPreProcessing getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(PaymentPreProcessing subTotal) {
        this.subTotal = subTotal;
    }

    @Override
    public double calculateTotalCost(){
        double fees = amount * FEES_PERCENTAGE;
        double total = amount + fees;
        return Double.parseDouble(String.format("%.2f", total));
    }

    @Override
    public void getInfo () {
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.println("Enter your Apple Pay email: ");
            String userEmail = input.next();
            if (userEmail.matches("^[a-zA-Z0-9._%+-]+@applepay$")) {
                this.email = userEmail;
                break;
            } else {
                System.out.println("Invalid email. Please enter an email ending with '@applepay'.");
            }
        }
    }

    @Override
    public void process() {
        System.out.println("Total cost : $" + calculateTotalCost());
    }

    @Override
    public String getMethodName () {
        return "Apple Pay";
    }

}
//package mainPackage;
//import java.util.Scanner;
//public class ApplePay implements PaymentMethod {
//
//    private String email;
//    private final static float FEES_PERCENTAGE = 0.04f;
//    private double baseFare;
//    private double amount;
//    private Flight flight;
//    private PaymentPreProcessing subTotal;
//
//    public ApplePay() {
//    }
//
//    public ApplePay(String email, double baseFare, double amount, Flight flight, PaymentPreProcessing subTotal) {
//        this.email = email;
//        this.baseFare = baseFare;
//        this.amount = subTotal.calculateSubTotal();
//        this.flight = flight;
//        this.subTotal = subTotal;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public void setBaseFare(double baseFare) {
//        this.baseFare = flight.getBaseFare();
//    }
//
//    public PaymentPreProcessing getSubTotal() {
//        return subTotal;
//    }
//
//    public void setSubTotal(PaymentPreProcessing subTotal) {
//        this.subTotal = subTotal;
//    }
//
//
//    @Override
//    public double calculateTotalCost(double baseFare){
//        double fees = baseFare * FEES_PERCENTAGE;
//        return amount + fees;
//    }
//
//    @Override
//    public void getInfo () {
//        Scanner input = new Scanner(System.in);
//
//        while (true) {
//            System.out.println("Enter your Apple Pay email: ");
//            String userEmail = input.next();
//            if (userEmail.matches("^[a-zA-Z0-9._%+-]+@applepay$")) {
//                this.email = userEmail;
//                break;
//            } else {
//                System.out.println("Invalid email. Please enter an email ending with '@applepay'.");
//            }
//        }
//    }
//
//    @Override
//    public void process() {
//        System.out.println("Total cost : " + calculateTotalCost(baseFare));
//    }
//
//    @Override
//    public String getMethodName () {
//        return "Apple Pay";
//    }
//
//}

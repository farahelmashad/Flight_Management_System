package mainPackage;

import java.util.Scanner;

public class PaymentPreProcessing {
    private double baseFare;
    private final float taxRate = 0.14f;
    private float additionalServiceCost;
    private Flight flight;

    public PaymentPreProcessing(float additionalServiceCost, Flight flight) {
        this.additionalServiceCost = additionalServiceCost;
        this.flight = flight;
    }

    // FROM FLIGHT CLASS
    public void setBaseFare(float baseFare) {
        this.baseFare = flight.getBaseFare();
    }

    public float getTaxRate() {
        return taxRate;
    }

    public float getAdditionalServiceCost() {
        return additionalServiceCost;
    }

    public void setAdditionalServiceCost(float additionalServiceCost) {
        this.additionalServiceCost = additionalServiceCost;
    }

    public double calculateSubTotal() {
        double taxes = baseFare * taxRate;
        return baseFare + taxes + additionalServiceCost;
    }

    public void acceptPayment() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Total amount: $" + this.calculateSubTotal());
        System.out.println("Select a payment method:");
        System.out.println("1. Card");
        System.out.println("2. Apple Pay");
        int choice = scanner.nextInt();

    boolean running = true;
    while (running) {
        switch (choice) {
            case 1:
                Card card = new Card();
                card.getInfo();
                card.process();
                running = false;
                break;
            case 2:
                ApplePay applePay = new ApplePay();
                applePay.getInfo();
                applePay.process();
                running = false;
                break;
            default:
                System.out.println("Invalid choice! Please try again.\n");
                running = true;
                break;
        }
    }

    }

}

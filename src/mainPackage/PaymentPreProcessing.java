//package mainPackage;
//
//import java.util.Scanner;
//
//public class PaymentPreProcessing {
//    private final Flight flight;
//    private final Booking booking;
//    private final float taxRate = 0.14f;
//    private boolean[] additionalServices = new boolean[4];
//    double economyBaseFare;
//    int economySeats;
//    int businessSeats;
//    int firstClassSeats;
//
//    public PaymentPreProcessing(Flight flight, Booking booking) {
//        this.flight = flight;
//        this.booking = booking;
//        this.economyBaseFare = flight.getBaseFare();
//        this.economySeats = booking.getEconomySeats();
//        this.businessSeats = booking.getBusinessSeats();
//        this.firstClassSeats = booking.getFirstClassSeats();
//    }
//
//    public double calculateSubTotal() {
//        double businessBaseFare = economyBaseFare * 2;
//        double firstClassBaseFare = economyBaseFare * 3;
//
//        double totalEconomyFare = economySeats * economyBaseFare;
//        double totalBusinessFare = businessSeats * businessBaseFare;
//        double totalFirstClassFare = firstClassSeats * firstClassBaseFare;
//
//        double baseFareTotal = totalEconomyFare + totalBusinessFare + totalFirstClassFare;
//        double taxes = baseFareTotal * taxRate;
//
//        int additionalCost = 0;
//        for (boolean service : additionalServices) {
//            if (service) {
//                additionalCost += 1000;
//            }
//        }
//
//        return baseFareTotal + taxes + additionalCost * (booking.getTotalSeats());
//    }
//
//    public void setAdditionalServices(boolean[] additionalServices) {
//        if (additionalServices.length == 4) {
//            this.additionalServices = additionalServices;
//        } else {
//            throw new IllegalArgumentException("There must be exactly 4 additional service options.");
//        }
//    }
//
//    public void acceptPayment() {
//        Scanner scanner = new Scanner(System.in);
//
//        System.out.println("Total amount: $" + this.calculateSubTotal());
//
//        boolean running = true;
//        while (running) {
//            System.out.println("Select a payment method:");
//            System.out.println("1. Card");
//            System.out.println("2. Apple Pay");
//            int choice = scanner.nextInt();
//            switch (choice) {
//                case 1:
//                    Card card = new Card();
//                    card.getInfo();
//                    card.process();
//                    running = false;
//                    break;
//                case 2:
//                    ApplePay applePay = new ApplePay();
//                    applePay.getInfo();
//                    applePay.process();
//                    running = false;
//                    break;
//                default:
//                    System.out.println("Invalid choice! Please try again.\n");
//                    break;
//            }
//        }
//    }
//}

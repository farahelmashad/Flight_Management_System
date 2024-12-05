package mainPackage;

public class Payment {
    public static int paymentsCount;
    private int paymentID;
    private double paymentAmount;
    private double baseFare;
    private String paymentStatus;
    private PaymentMethod paymentMethod;

    public Payment(int paymentID, double paymentAmount, double baseFare , String paymentStatus, PaymentMethod paymentMethod) {
        this.paymentID = ++paymentsCount;
        this.paymentAmount = paymentMethod.calculateTotalCost(baseFare);
        this.baseFare = baseFare;
        this.paymentStatus = "Completed";
        this.paymentMethod = paymentMethod;
    }

    public int getPaymentID() {
        return paymentID;
    }

    public double getPaymentAmount() {
        return paymentAmount;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentID(int paymentID) {
        this.paymentID = paymentID;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public void Pay () {
        System.out.println("Your payment of $" + paymentAmount + " is " + paymentStatus + " by " + this.paymentMethod.getMethodName());
    }

}

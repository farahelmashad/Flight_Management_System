package mainPackage;

public class Payment {
    public static int paymentsCount;
    private int paymentID;
    private double paymentAmount;
    private double baseFare;
    private String paymentStatus = "Completed";
    private PaymentMethod paymentMethod;

    public Payment(int paymentID, double baseFare , String paymentStatus) {
        this.paymentID = ++paymentsCount;
        this.baseFare = baseFare;
        this.paymentStatus = paymentStatus;
    }

    public Payment(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
        this.paymentAmount = paymentMethod.calculateTotalCost();
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
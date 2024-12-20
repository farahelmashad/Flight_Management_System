package mainPackage;

public interface PaymentMethod {
    double calculateTotalCost();
    String getMethodName();
    void getInfo();
    void process();
}
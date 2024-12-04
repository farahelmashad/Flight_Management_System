package mainPackage;

public interface PaymentMethod {
    double calculateTotalCost(double baseFare);
    String getMethodName();
    void getInfo();
    void process();
}

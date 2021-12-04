package interfaces;

import customer.Address;
import services.Product;

public interface ICustomer {    // using concept of INTERFACE for 100% abstraction
    void infoInput();
    void accountUpdate();
    void showAccount();
    Address getAddress();
    void passwordCheckAndSet();
    void showDeliveries();
    void feeCalculate (Product[] products, int distance);       //using the concept METHOD OVERLOADING
    void feeCalculate(Product[] products, short numberOfDay);
    void payment();
}

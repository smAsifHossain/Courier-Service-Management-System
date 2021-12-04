package services;

import abstraction.Customer;
import customer.Person;
import customer.Company;
import java.util.Scanner;

public final class Product {
    String type;
    Customer owner;         //ONE-TO-ONE ASSOCIATION
    Person personOwner;     //ONE-TO-ONE ASSOCIATION
    Company companyOwner;   //ONE-TO-ONE ASSOCIATION
    long productId;
    double weight;
    Scanner input = new Scanner(System.in);

    public Product (Person personOwner) {
       this.personOwner = personOwner;
       owner = personOwner;
    }

    public Product (Company companyOwner) {
        this.companyOwner = companyOwner;
        owner = companyOwner;
    }

    public final void productInfoInput () {
        System.out.print("PRODUCT TYPE: ");
        type = input.nextLine();
        System.out.print("PRODUCT ID: ");
        productId = Long.parseLong(input.nextLine());
        System.out.print("PRODUCT WEIGHT: ");
        weight = Integer.parseInt(input.nextLine());
        System.out.println();
    }

    public final void productShow () {
        System.out.println("PRODUCT OWNER NAME: "+owner.name);
        System.out.println("PRODUCT TYPE: "+type);
        System.out.println("PRODUCT ID: "+productId);
        System.out.println("PRODUCT WEIGHT: "+weight);
        System.out.println();
    }

    public void setType (String type) {
        this.type = type;
    }

    public String getType () {
        return type;
    }

    public void setProductId (long productId) {
        this.productId = productId;
    }

    public long getProductId () {
        return productId;
    }

    public void setWeight (double weight) {
        this.weight = weight;
    }

    public double getWeight () {
        return weight;
    }

}

package services;
import abstraction.Customer;
import customer.Company;
import customer.Person;
import file.FileWriteAndRead;

import java.util.ArrayList;
import java.util.Scanner;

public final class Stock {
    Customer share;                             //ONE-TO-ONE ASSOCIATION
    Person personShare;                         //ONE-TO-ONE ASSOCIATION
    Company companyShare;                       //ONE-TO-ONE ASSOCIATION
    static final double totalCapacity = 1000;  //using concept of STATIC FINAL keywords
    static double remainingCapacity = 1000;
    ArrayList<Product> products = new ArrayList<>();    //using ARRAYLIST
    public short numberOfDay;
    Scanner input = new Scanner(System.in);

    public Stock (Person personSender) {
        this.personShare = personSender;
        share = personSender;
    }

    public Stock (Company companyShare) {
        this.companyShare = companyShare;
        share = companyShare;
    }

    public final boolean stockIn (Product productIn) {
        boolean flag = true;
        if (productIn.weight > remainingCapacity) {
            System.out.println("WAREHOUSE IS FULL");
        }
        else {
            for (Product product : products) {
                if (productIn.getProductId() == product.getProductId()) {
                    flag = false;
                }
            }
            if (flag) {
                products.add(productIn);
            }
            else {
                System.out.println("THERE IS PRODUCT WITH SAME ID");
                System.out.println();
            }
        }
        return flag;
    }

    public final Product stockOut () {
        Product product = null;
        System.out.print("ENTER THE PRODUCT ID: ");
        long productId = Long.parseLong(input.nextLine());
        System.out.println();
        boolean flag = false;
        for (int i = 0; i<products.size(); i++) {
            if (share.name.equals(products.get(i).owner.name) && productId == products.get(i).productId) {
                product = products.get(i);
                products.remove(i);
                flag = true;
                break;
            }
        }
        if (!flag) {
            System.out.println("PRODUCT NOT FOUND");
            System.out.println();
        }
        return product;
    }

    public final void stockShow () {
        if (products.size() != 0) {
            System.out.println("********STOCK********");
            for (int i = 0; i<products.size(); i++) {
                System.out.println("STOCK PRODUCT NO-"+(i+1));
                products.get(i).productShow();
                System.out.println();
            }
        }
        else {
            System.out.println("NO PRODUCT STORED IN STOCK");
            System.out.println();
        }
    }

    public final void RemainingCapacityCalculate () {
        for (Product product : products) {
            remainingCapacity -= product.weight;
        }
    }

    public final void setNumberOfDay () {
        System.out.print("NUMBER OF DAYS YOU WANT TO STOCK: ");
        numberOfDay = Short.parseShort(input.nextLine());
    }

    public final void stockWrite () {
        FileWriteAndRead file;
        if (share.customerType.equals("person")) {
            file = new FileWriteAndRead(personShare);
        }
        else {
            file = new FileWriteAndRead(companyShare);
        }
        for (Product product : products) {
            file.writeProduct(product);
        }
    }
}

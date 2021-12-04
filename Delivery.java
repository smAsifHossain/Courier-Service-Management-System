package services;

import abstraction.Customer;
import customer.Company;
import customer.Person;
import customer.Receiver;
import java.util.Random;
import java.util.Scanner;

public final class Delivery {
    Customer sender;        //ONE-TO-ONE ASSOCIATION
    Person personSender;    //ONE-TO-ONE ASSOCIATION
    Company companySender;  //ONE-TO-ONE ASSOCIATION
    Product[] product ;     //ONE-TO-MANY ASSOCIATION
    Receiver receiver;      //ONE-TO-ONE ASSOCIATION
    int[] deliveryDate = new int[2];
    String status;
    int distance;
    Scanner input = new Scanner(System.in);
    Random rand = new Random();

    public Delivery (Person personSender, Product[] product, Receiver receiver) {
        this.personSender = personSender;
        sender = personSender;
        this.product = product;
        this.receiver = receiver;
    }

    public Delivery (Company companySender, Product[] product, Receiver receiver) {
        this.companySender = companySender;
        sender = companySender;
        this.product = product;
        this.receiver = receiver;
    }

    public final void DeliveryInfoInput () {
        System.out.println("DELIVERY INFORMATION");
        System.out.println("DELIVERY DATE");
        System.out.print("  DAY: ");
        deliveryDate[0] = Integer.parseInt(input.nextLine());
        System.out.print("  MONTH: ");
        deliveryDate[1] = Integer.parseInt(input.nextLine());
        System.out.println();
    }

    public final void cancel () {
        status = "CANCELED";
    }

    public final void deliveryStatusInquiry () {
        int random = rand.nextInt(2);

        if (sender.getFee() < 25) {
            status = "PENDING";
        }
        else if (random == 0) {
            status = "IN-PROGRESS";
        }
        else {
            status = "DELIVERED";
        }
    }

    public final   void distanceCalculate () {
        distance = rand.nextInt(51);
    }

    public final void showDeliveryInfo () {
        System.out.println("SENDER NAME: "+sender.name);
        System.out.println();
        for (Product i: product) {
            i.productShow();
            System.out.println();
        }
        receiver.showAccount();
        System.out.println("DELIVERY DATE");
        System.out.println("  DAY NO: "+deliveryDate[0]);
        System.out.println("  MONTH NO: "+deliveryDate[1]);
        System.out.println("DELIVERY STATUS: "+status);
        System.out.println();
    }

    public final int getDistance () {
        return distance;
    }
}

package customer;

import abstraction.Customer;
import java.util.Scanner;

public class Person extends Customer {  //child class of the abstract class Customer
    public String lastName;
    private String nid;
    public String gander;
    Scanner input = new Scanner(System.in);

    public Person () {
        super();
    }

    public Person (String customerType, String firstName, String lastName, Address address, String mobileNumber, String email, int[] birthdate, String NID, String gander) {
        super(customerType, firstName, address, mobileNumber, email, birthdate);
        this.lastName = lastName;
        this.nid = NID;
        this.gander = gander;
    }

    public void infoInput () {  //METHOD OVERRIDING from Abstract Class
        super.customerType = "person";
        System.out.print("FIRST NAME: ");
        super.name = input.nextLine();
        System.out.print("LAST NAME: ");
        lastName = input.nextLine();
        address.updateAddress();
        System.out.print("MOBILE NUMBER: ");
        super.mobileNumber = input.nextLine();
        System.out.print("EMAIL: ");
        super.email = input.nextLine();
        System.out.println("BIRTHDATE ");
        System.out.print("  DAY NO: ");
        super.date[0] = Integer.parseInt(input.nextLine());
        System.out.print("  MONTH NO: ");
        super.date[1] = Integer.parseInt(input.nextLine());
        System.out.print("  YEAR: ");
        super.date[2] = Integer.parseInt(input.nextLine());
        System.out.println("1 - MALE    ");
        System.out.println("2 - FEMALE");
        System.out.print("SELECT YOUR GANDER: ");
        int choice = Integer.parseInt(input.nextLine());
        if (choice == 1){
            gander = "Male";
        }
        else if (choice == 2){
            gander = "Female";
        }
        System.out.print("NID: ");
        nid = input.nextLine();
    }

    public final void accountUpdate () {
        int choice;
        System.out.println("SELECT THE DATA YOU WANT TO UPDATE");
        do {
            System.out.println("1 - FIRST NAME");
            System.out.println("2 - LAST NAME");
            System.out.println("3 - ADDRESS");
            System.out.println("4 - MOBILE NUMBER");
            System.out.println("5 - EMAIL");
            System.out.println("6 - BIRTHDATE");
            System.out.println("7 - GANDER");
            System.out.println("8 - NID NO");
            System.out.println("9 - CHANGE PASSWORD");
            System.out.println("0 - QUIT");
            System.out.println();

            System.out.print("YOUR SELECTION: ");
            choice = Integer.parseInt(input.nextLine());
            System.out.println();

            if (choice == 1) {
                System.out.print("FIRSTNAME: ");
                super.name = input.nextLine();
                System.out.println();
            }
            else if (choice == 2) {
                System.out.print("LAST NAME: ");
                lastName = input.nextLine();
                System.out.println();
            }
            else if (choice == 3) {
                address.updateAddress();
                System.out.println();
            }
            else if (choice == 4) {
                System.out.print("MOBILE NUMBER: ");
                super.mobileNumber = input.nextLine();
                System.out.println();
            }
            else if (choice == 5) {
                System.out.print("EMAIL: ");
                super.email = input.nextLine();
                System.out.println();
            }
            else if (choice == 6) {
                System.out.println("BIRTHDATE ");
                System.out.print("  DAY: ");
                super.date[0] = Integer.parseInt(input.nextLine());
                System.out.print("  MONTH: ");
                super.date[1] = Integer.parseInt(input.nextLine());
                System.out.print("  YEAR: ");
                super.date[2] = Integer.parseInt(input.nextLine());
                System.out.println();
            }
            else if (choice == 7) {
                System.out.println("1 - MALE    ");
                System.out.println("2 - FEMALE");
                int ch = Integer.parseInt(input.nextLine());
                if (ch == 1){
                    gander = "Male";
                }
                else if (ch == 2){
                    gander = "Female";
                }
                System.out.println();
            }
            else if (choice == 8) {
                System.out.print("NID: ");
                nid = input.nextLine();
                System.out.println();
            }
            else if (choice == 9) {
                super.passwordCheckAndSet();
                System.out.println();
            }
        } while (choice != 0);
    }
    public void showAccount () {
        System.out.println("ACCOUNT TYPE: "+super.customerType);
        System.out.println("NAME: "+super.name+" "+lastName);
        super.address.showAddress();
        System.out.println("MOBILE NUMBER: "+super.mobileNumber);
        System.out.println("EMAIL: "+super.email);
        System.out.print("BIRTHDATE: ");
        for (int i = 0; i<3; i++) {
            if (i == 2){
                System.out.println(date[i]);
            }
            else {
                System.out.print(date[i]+"/");
            }
        }
        System.out.println("NID NUMBER: "+nid);
        System.out.println("GANDER: "+gander);
        System.out.println("FEE: "+super.getFee());
        System.out.println();
    }

    public final String getNID () {

        return nid;
    }
}

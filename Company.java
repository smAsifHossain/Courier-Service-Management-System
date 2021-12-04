package customer;
import abstraction.Customer;
import java.util.Scanner;

public final class Company extends Customer {   //child class of the abstract class Customer, using the concept of FINAL CLASS as we don't want Company class to be inherited in future
    public String website;
    private String regNo;
    Scanner input = new Scanner(System.in);

    public Company () {
        super();
    }

    public Company (String customerType, String name, Address address, String mobileNumber, String email, int[] established, String website ,String regNo) {
        super(customerType, name, address, mobileNumber, email, established);
        this.website = website;
        this.regNo = regNo;
    }

    public final void infoInput () {        //METHOD OVERRIDING of Abstract Class
        super.customerType = "company";     //using the concept of FINAL METHOD so that this method can not be overridden as it contains sensevive data
        System.out.print("COMPANY NAME: ");
        super.name = input.nextLine();
        address.updateAddress();
        System.out.print("MOBILE NUMBER: ");
        super.mobileNumber = input.nextLine();
        System.out.print("EMAIL: ");
        super.email = input.nextLine();
        System.out.println("ESTABLISHED");
        System.out.print("  DAY NO: ");
        super.date[0] = Integer.parseInt(input.nextLine());
        System.out.print("  MONTH NO: ");
        super.date[1] = Integer.parseInt(input.nextLine());
        System.out.print("  YEAR: ");
        super.date[2] = Integer.parseInt(input.nextLine());
        System.out.print("WEBSITE: ");
        website = input.nextLine();
        System.out.print("REG. NO: ");
        regNo = input.nextLine();
    }

    public final void accountUpdate () {
        int choice;
        System.out.println("SELECT THE DATA YOU WANT TO UPDATE");
        do {
            System.out.println("1 - FIRST NAME");
            System.out.println("2 - ADDRESS");
            System.out.println("3 - MOBILE NUMBER");
            System.out.println("4 - EMAIL");
            System.out.println("5 - ESTABLISH");
            System.out.println("6 - WEBSITE");
            System.out.println("7 - REG. NO");
            System.out.println("8 - CHANGE PASSWORD");
            System.out.println("0 - QUIT");
            System.out.println();

            System.out.print("YOUR SELECTION: ");
            choice = Integer.parseInt(input.nextLine());
            System.out.println();

            if (choice == 1) {
                System.out.print("NAME: ");
                super.name = input.nextLine();
                System.out.println();
            }
            else if (choice == 2) {
                address.updateAddress();
                System.out.println();
            }
            else if (choice == 3) {
                System.out.print("MOBILE NUMBER: ");
                super.mobileNumber = input.nextLine();
                System.out.println();
            }
            else if (choice == 4) {
                System.out.print("EMAIL: ");
                super.email = input.nextLine();
                System.out.println();
            }
            else if (choice == 5) {
                System.out.println("ESTABLISH ");
                System.out.print("  DAY: ");
                super.date[0] = Integer.parseInt(input.nextLine());
                System.out.print("  MONTH: ");
                super.date[1] = Integer.parseInt(input.nextLine());
                System.out.print("  YEAR: ");
                super.date[2] = Integer.parseInt(input.nextLine());
                System.out.println();
            }
            else if (choice == 6) {
                System.out.print("WEBSITE: ");
                website = input.nextLine();
                System.out.println();
            }
            else if (choice == 7) {
                System.out.print("REG. NO: ");
                regNo = input.nextLine();
                System.out.println();
            }
            else if (choice == 8) {
                super.passwordCheckAndSet();
                System.out.println();
            }
        } while (choice != 0);
    }

    public final void showAccount () {
        System.out.println("ACCOUNT TYPE: "+super.customerType);
        System.out.println("FIRST NAME: "+super.name);
        super.address.showAddress();
        System.out.println("MOBILE NUMBER: "+super.mobileNumber);
        System.out.println("EMAIL: "+super.email);
        System.out.print("ESTABLISHED: ");
        for (int i = 0; i<3; i++) {
            if (i == 2){
                System.out.println(super.date[i]);
            }
            else {
                System.out.print(super.date[i]+"/");
            }
        }
        System.out.println("WEBSITE: "+website);
        System.out.println("REGISTRATION NO: "+regNo);
        System.out.println("FEE: "+super.getFee());
        System.out.println();
    }

    public final String getRegNo () {
        return regNo;
    }
}

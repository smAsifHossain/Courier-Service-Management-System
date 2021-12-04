package abstraction;
import java.util.ArrayList;
import java.util.Scanner;
import customer.Address;
import interfaces.ICustomer;
import services.Delivery;
import services.Product;

public abstract class  Customer implements ICustomer {  //using the concept of ABSTRACTION and INTERFACE IMPLEMENTATION
        public String customerType;
        public String name;
        protected Address address;                      // used the concept ONE-TO-ONE ASSOCIATION
        protected String mobileNumber;
        public String email;
        protected int[] date = new int[3];              // Birthdate for person and Established for Company
        protected String password;
        ArrayList<Delivery> deliveries = new ArrayList<>(); //using ARRAYLIST
        protected double fee = 0;
        Scanner sc = new Scanner(System.in);

        public Customer () {
                address = new Address();
        }

        public Customer (String customerType, String name, Address address, String mobileNumber, String email, int[] date) {
                this.customerType = customerType;
                this.name = name;
                this.address = address;
                this.mobileNumber = mobileNumber;
                this.email = email;
                this.date = date;
        }

        public abstract void infoInput ();

        public abstract void accountUpdate ();
        public abstract void showAccount ();

        public final Address getAddress () {
                return address;
        }

        public final String getMobileNumber () {
                return mobileNumber;
        }

        public final int[] getDate () {
                return date;
        }

        public final void passwordCheckAndSet () {      //using the concept of FINAL METHOD so that this method can not be overridden as it contains sensitive data
                System.out.println("REQUIREMENT");
                System.out.println("* AT LEAST EIGHT CHARACTER LONG *");
                System.out.println("* AT LEAST ONE UPPER CASE LETTER *");
                System.out.println("* AT LEAST ONE LOWER CASE LETTER *");
                System.out.println("* AT LEAST ONE NUMBER *");
                System.out.print("ENTER THE PASSWORD: ");
                String password = sc.nextLine();
                boolean upperCaseFlag = false;
                boolean lowerCaseFlag = false;
                boolean numberFlag = false;

                if (password.length() < 8) {
                        System.out.println("PASSWORD MUST BE EIGHT CHARACTER LONG!!!");
                        passwordCheckAndSet();
                        return;
                }
                else {
                        for (int i = 0; i<password.length(); i++) {
                                if (password.charAt(i) >= 65 && password.charAt(i) <= 90) {
                                        upperCaseFlag = true;
                                }
                                else if (password.charAt(i) >= 97 && password.charAt(i) <= 122) {
                                        lowerCaseFlag = true;
                                }
                                else if (password.charAt(i) >= 48 && password.charAt(i) <= 57) {
                                        numberFlag = true;
                                }
                        }
                }

                if (!upperCaseFlag || !lowerCaseFlag || !numberFlag) {
                        if (!upperCaseFlag) {
                                System.out.println("THERE IS NO UPPER CASE LETTER IN THE PASSWORD");
                        }
                        if (!lowerCaseFlag) {
                                System.out.println("THERE IS NO LOWER CASE LETTER IN THE PASSWORD");
                        }
                        if (!numberFlag) {
                                System.out.println("THERE IS NO NUMBER IN THE PASSWORD");
                        }
                        passwordCheckAndSet();
                }
                else {
                        this.password = password;
                }
        }

        public final void directSetPassword (String password) {
                this.password = password;
        }

        public final String getPassword () {
                return password;
        }

        public final void feeCalculate (Product[] products, int distance) {
                double weight = 0;
                for (Product i: products) {
                        weight += i.getWeight();
                }
                fee += (weight*3)+(distance*5);
        }

        public final void feeCalculate (Product[] products, short numberOfDay) {
                double weight = 0;
                for (Product i: products) {
                        weight += i.getWeight();
                }
                fee += (weight*3)+(numberOfDay*3);
        }

        public final void addDelivery (Delivery delivery) {
                this.deliveries.add(delivery);
        }

        public final void showDeliveries () {
                if (deliveries.size() != 0) {
                        System.out.println("********DELIVERY********");
                        for (int i = 0; i<deliveries.size(); i++) {
                                System.out.println("DELIVERY NO-"+(i+1));
                                deliveries.get(i).showDeliveryInfo();
                                System.out.println();
                        }
                }
                else {
                        System.out.println("NO DELIVERY SERVICE TAKEN");
                        System.out.println();
                }
        }

        public final void setFee (double fee) {
                this.fee = fee;
        }

        public final double getFee () {
                return fee;
        }

        public final void payment () {
                System.out.println("TOTAL FEES: "+fee);
                System.out.print("ENTER THE AMOUNT YOU WANT TO PAY: ");
                double payment = Double.parseDouble(sc.nextLine());
                System.out.println();
                if(payment > fee) {
                        System.out.println("HERE IS YOUR EXTRA "+(payment - fee)+" TK BACK");
                        fee = 0;
                        System.out.println();
                }
                else {
                        fee -= payment;
                }
        }
}

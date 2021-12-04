package customer;

public final class Receiver extends Person {    // Using the concept of FINAL CLASS as we don't want Receiver class to be inherited in future
    public Receiver () {
        super();
    }

    public final void infoInput () {    //using the concept of FINAL METHOD so that this method can not be overridden as it contains sensevive data
        super.customerType = "person";
        System.out.println("ENTER RECEIVER INFORMATION");
        System.out.print("NAME: ");
        super.name = input.nextLine();
        System.out.println("ADDRESS");
        System.out.print("  STATE: ");
        super.address.setState(input.nextLine());
        System.out.print("  CITY: ");
        super.address.setCity(input.nextLine());
        System.out.print("  HOUSE: ");
        super.address.setHouse(input.nextLine());
        System.out.print("MOBILE NUMBER: ");
        super.mobileNumber = input.nextLine();
        System.out.print("EMAIL: ");
        super.email = input.nextLine();
        System.out.println();
    }

    public final void showAccount () {
        System.out.println("RECEIVER INFORMATION");
        System.out.println("NAME: "+super.name);
        super.address.showAddress();
        System.out.println("MOBILE NUMBER: "+super.mobileNumber);
        System.out.println("EMAIL: "+super.email);
    }
}

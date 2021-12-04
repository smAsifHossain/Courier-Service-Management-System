package customer;

import java.util.Scanner;

public final class Address {    //using the concept of FINAL CLASS as we don't want Address class to be inherited in future
    private String state;       // used this class as an attribute of the abstract class Customer for one-to-one association
    private String city;
    private String house;
    Scanner input = new Scanner(System.in);

    public Address () {

    }

    public Address (String state, String city, String house) {
        this.state = state;
        this.city = city;
        this.house = house;
    }

    public void updateAddress () {
        System.out.println("ADDRESS");
        System.out.print("  STATE: ");
        state = input.nextLine();
        System.out.print("  CITY: ");
        city = input.nextLine();
        System.out.print("  HOUSE: ");
        house = input.nextLine();
    }

    public  void showAddress () {
        System.out.println("ADDRESS");
        System.out.println("    STATE: "+state);
        System.out.println("    CITY: "+city);
        System.out.println("    HOUSE: "+house);
    }

    public void setState (String state) {
        this.state = state;
    }

    public String getState () {
        return state;
    }

    public void setCity (String city) {
        this.city = city;
    }

    public String getCity () {
        return city;
    }

    public void setHouse (String house) {
        this.house = house;
    }

    public String getHouse () {
        return  house;
    }
}

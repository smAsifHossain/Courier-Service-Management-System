package file;
import abstraction.Customer;
import customer.Address;
import customer.Company;
import customer.Person;
import services.Product;
import services.Stock;

import java.io.*;
import java.util.Scanner;

public final class FileWriteAndRead {
    static File dir;
    static String filePath;
    File customer;
    Customer ac;
    Person user;
    Company org;

    static {
        dir = new File("F:\\2021\\Spring\\Object-oriented programming 1 (Java) F\\Final\\Online Courier Service\\Online_Courier_Service\\src\\file");
        filePath = dir.getAbsolutePath();
    }

    public FileWriteAndRead () {

    }

    public FileWriteAndRead (Person user) {
        this.user = user;
        ac = user;
    }

    public FileWriteAndRead (Company org) {
        this.org = org;
        ac = org;
    }

    public final void creatPersonAccount () {
        customer = new File(filePath+"/"+user.customerType+"/"+user.email+".txt");
        try {
            if (customer.createNewFile()) {
                FileWriter fileWrite = new FileWriter(customer);
                fileWrite.write(user.getPassword()+"\r\n"+user.name+"\r\n"+user.lastName+"\r\n"+user.getAddress().getState()+"\r\n"+user.getAddress().getCity()+"\r\n"+user.getAddress().getHouse()+"\r\n"+user.getMobileNumber()+"\r\n"+user.getDate()[0] +" "+user.getDate()[1] +" "+user.getDate()[2] +"\r\n"+user.gander+"\r\n"+user.getNID()+"\r\n"+user.getFee());
                fileWrite.flush();
                fileWrite.close();
            }
            else {
                System.out.println("ACCOUNT IS ALREADY EXIST");
                System.out.println();
            }
        } catch (IOException e) {
            System.out.println(""+e);
        }
    }

    public final void creatCompanyAccount () {
        customer = new File(filePath+"/"+org.customerType+"/"+org.email+".txt");
        try {
            if (customer.createNewFile()) {
                FileWriter fileWrite = new FileWriter(customer);
                fileWrite.write(org.getPassword()+"\r\n"+org.name+"\r\n"+org.getAddress().getState()+"\r\n"+org.getAddress().getCity()+"\r\n"+org.getAddress().getHouse()+"\r\n"+org.getMobileNumber()+"\r\n"+org.getDate()[0] +" "+org.getDate()[1] +" "+org.getDate()[2] +"\r\n"+org.website+"\r\n"+org.getRegNo()+"\r\n"+org.getFee());
                fileWrite.flush();
                fileWrite.close();
            }
            else {
                System.out.println("ACCOUNT IS ALREADY EXIST");
                System.out.println();
            }
        } catch (IOException e) {
            System.out.println(" "+e);
        }
    }

    public final void writeProduct (Product product) {
        int productNo = 1;
        customer = new File(filePath+"\\stock product\\"+ac.email);
        customer.mkdir();
        if (customer.exists()) {
            while (true) {
                customer = new File(filePath+"/stock product/"+ac.email+"/"+productNo+".txt");
                try {
                    if (customer.createNewFile()) {
                        FileWriter fileWrite = new FileWriter(customer);
                        fileWrite.write(product.getProductId() + "\r\n" + product.getType() + "\r\n" + product.getWeight());
                        fileWrite.flush();
                        fileWrite.close();
                        break;
                    } else {
                        productNo++;
                    }
                } catch (IOException e) {
                    System.out.println(" " + e);
                }
            }
        }
        else {
            System.out.println("DIRECTORY DOES NOT EXIST");
        }
    }


    public final Person PersonSignIn (String customerType, String email, String password) {
        customer = new File(filePath+"/"+customerType+"/"+email+".txt");
        try {
            if (customer.exists()) {
                Scanner fileReader = new Scanner(customer);
                String tempPassword = fileReader.nextLine().trim();
                if (password.equals(tempPassword)) {
                    String firstName = fileReader.nextLine().trim();
                    String lastName = fileReader.nextLine().trim();
                    String state = fileReader.nextLine().trim();
                    String city = fileReader.nextLine().trim();
                    String house = fileReader.nextLine().trim();
                    String mobileNumber = fileReader.nextLine().trim();
                    int[] birthdate = new int[3];
                    birthdate[0] = Integer.parseInt(fileReader.next().trim());
                    birthdate[1] = Integer.parseInt(fileReader.next().trim());
                    birthdate[2] = Integer.parseInt(fileReader.nextLine().trim());
                    String gander = fileReader.nextLine().trim();
                    String NID = fileReader.nextLine().trim();
                    double fee = Double.parseDouble(fileReader.nextLine().trim());
                    Address address = new Address(state, city, house);
                    user = new Person("person", firstName, lastName, address, mobileNumber, email, birthdate, NID, gander);
                    user.directSetPassword(tempPassword);
                    user.setFee(fee);
                    fileReader.close();
                }
                else {
                    System.out.println("INCORRECT PASSWORD");
                    System.out.println();
                }
            }
            else {
                System.out.println("ACCOUNT DOES NOT EXIST");
                System.out.println();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return user;
    }

    public final Company companySignIn (String customerType, String email, String password) {
        customer = new File(filePath+"/"+customerType+"/"+email+".txt");
        try {
            if (customer.exists()) {
                Scanner fileReader = new Scanner(customer);
                String tempPassword = fileReader.nextLine().trim();
                if (password.equals(tempPassword)) {
                    String name = fileReader.nextLine().trim();
                    String state = fileReader.nextLine().trim();
                    String city = fileReader.nextLine().trim();
                    String house = fileReader.nextLine().trim();
                    String mobileNumber = fileReader.nextLine().trim();
                    int[] established = new int[3];
                    established[0] = Integer.parseInt(fileReader.next().trim());
                    established[1] = Integer.parseInt(fileReader.next().trim());
                    established[2] = Integer.parseInt(fileReader.nextLine().trim());
                    String website = fileReader.nextLine().trim();
                    String regNo = fileReader.nextLine().trim();
                    double fee = Double.parseDouble(fileReader.nextLine().trim());
                    Address address = new Address(state, city, house);
                    org = new Company("company", name, address, mobileNumber, email, established, website, regNo);
                    org.directSetPassword(password);
                    org.setFee(fee);
                    fileReader.close();
                }
                else {
                    System.out.println("INCORRECT PASSWORD");
                    System.out.println();
                }
            }
            else {
                System.out.println("ACCOUNT DOES NOT EXIST");
                System.out.println();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return org;
    }

    public final Product readProduct (int productNo) {
        Product product = null;
        customer = new File(filePath+"/stock product/"+ac.email+"/"+productNo+".txt");
        try {
            if (customer.exists()) {
                if (ac.customerType.equals("person")) {
                    product = new Product(user);
                }
                else {
                    product = new Product(org);
                }
                Scanner fileReader = new Scanner(customer);
                product.setProductId(Long.parseLong(fileReader.nextLine().trim()));
                product.setType(fileReader.nextLine().trim());
                product.setWeight(Double.parseDouble(fileReader.nextLine().trim()));
                fileReader.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return product;
    }

    public final void deleteAccount (String customerType, String email, String password) {
        customer = new File(filePath+"/"+customerType+"/"+email+".txt");
        try {
            if (customer.exists()) {
                Scanner fileReader = new Scanner(customer);
                if (password.equals(fileReader.nextLine().trim())) {
                    fileReader.close();
                    if (customer.delete()) {
                        System.out.println(customer.getName()+" - USER ACCOUNT HAS DELETED");
                    }
                    else {
                        System.out.println("FAILED TO DELETE THE FILE");
                    }
                }
                else {
                    System.out.println("INCORRECT PASSWORD");
                }
            }
            else {
                System.out.println("ACCOUNT DOES NOT EXIST");
            }
            System.out.println();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void removeProduct (long productId) {
        int productNo = 1;
        while (true) {
            customer = new File(filePath+"/stock product/"+ac.email+"/"+productNo+".txt");
            try {
                if (customer.exists()) {
                    Scanner fileReader = new Scanner(customer);
                    if (productId == Long.parseLong(fileReader.nextLine().trim())) {
                        fileReader.close();
                        if (customer.delete()) {

                        }
                        else {
                            System.out.println("FAILED TO REMOVED THE PRODUCT");
                        }
                        break;
                    }
                    else {
                        productNo++;
                    }
                }
                else {
                    break;
                }
                System.out.println();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public void reorder () {
        int i = 1;
        customer = new File(filePath+"/stock product/"+ac.email);
        for (File file : customer.listFiles()) {
            if (!file.getName().equals(i+".txt")) {
                File rename = new File(customer+"/"+i+".txt");
                file.renameTo(rename);
            }
            i++;
        }
    }

    public void deleteDir (String email) {
        customer = new File(filePath+"/stock product/"+email);
        customer.delete();
    }
}

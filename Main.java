import java.util.Scanner;

import customer.*;
import file.FileWriteAndRead;
import services.Delivery;
import services.Product;
import services.Stock;

public class Main {

    public static void main(String[] args) {
        System.out.println("---------------------------------");
        System.out.println("WELCOME TO ONLINE COURIER SERVICE");
        System.out.println("---------------------------------");
        Scanner input = new Scanner(System.in);
        FileWriteAndRead signUp = new FileWriteAndRead();
        boolean flag = false;
        boolean flag2;
        boolean flag3 = false;
        int option;
        do {
            System.out.println("1 - PERSON");
            System.out.println("2 - COMPANY");
            System.out.println("0 - END");
            System.out.println();

            System.out.print("ENTER YOUR OPTION - ");
            option = Integer.parseInt(input.nextLine());
            System.out.println();

            if (option == 1){
                Person p = null;
                Stock stock;
                int optionAccount;
                do {
                    System.out.println("1 - SIGN-IN");
                    System.out.println("2 - SIGN-UP");
                    System.out.println("3 - DELETE ACCOUNT");
                    System.out.println("0 - BACK");
                    System.out.println();

                    System.out.print("ENTER YOUR OPTION - ");
                    optionAccount = Integer.parseInt(input.nextLine());
                    System.out.println();

                    if (optionAccount == 1) {
                        System.out.print("EMAIL: ");
                        String email = input.nextLine();
                        System.out.print("PASSWORD: ");
                        String password = input.nextLine();
                        System.out.println();
                        p = signUp.PersonSignIn("person", email, password);
                        flag = true;
                    }
                    else if (optionAccount == 2) {
                        p = new Person();
                        p.infoInput();
                        p.passwordCheckAndSet();
                        System.out.println();
                        FileWriteAndRead ac = new FileWriteAndRead(p);
                        ac.creatPersonAccount();
                        flag = true;
                    }
                    else if (optionAccount == 3){
                        System.out.print("ENTER EMAIL: ");
                        String email = input.nextLine();
                        System.out.print("ENTER PASSWORD: ");
                        String password = input.nextLine();
                        System.out.println();
                        signUp.deleteAccount("person",email, password);
                        FileWriteAndRead ac = new FileWriteAndRead();
                        ac.deleteDir(email);
                        flag = false;
                    }
                    if (p != null && flag) {
                        stock = new Stock(p);
                        FileWriteAndRead ac = new FileWriteAndRead(p);
                        int productNo = 1;
                        while (true) {
                            Product product = ac.readProduct(productNo);
                            if (product != null) {
                                stock.stockIn(product);
                                productNo++;
                            }
                            else {
                                break;
                            }
                        }
                        int optionPerson;
                        do {
                            System.out.println("1 - SERVICES");
                            System.out.println("2 - TAKEN SERVICES");
                            System.out.println("3 - ACCOUNT MANAGE");
                            System.out.println("0 - SIGN-OUT");
                            System.out.println();

                            System.out.print("ENTER YOUR OPTION - ");
                            optionPerson = Integer.parseInt(input.nextLine());
                            System.out.println();

                            if (optionPerson == 1) {
                                int numberOfProduct;
                                Product[] product;
                                int optionPersonService;
                                do {
                                    System.out.println("1 - PARCEL DELIVERY");
                                    System.out.println("2 - WAREHOUSE");
                                    System.out.println("0 - BACK");
                                    System.out.println();

                                    System.out.print("ENTER YOUR OPTION - ");
                                    optionPersonService = Integer.parseInt(input.nextLine());
                                    System.out.println();

                                    if (optionPersonService == 1) {
                                        Delivery delivery = null;
                                        product = new Product[1];
                                        product[0] = new Product(p);
                                        int optionPersonServiceDeliver;
                                        do {
                                            System.out.println("1 - DELIVERY FROM ME");
                                            System.out.println("2 - DELIVERY FROM STOCK");
                                            System.out.println("3 - CANCEL DELIVERY");
                                            System.out.println("0 - BACK");
                                            System.out.println();

                                            System.out.print("ENTER YOUR OPTION - ");
                                            optionPersonServiceDeliver = Integer.parseInt(input.nextLine());
                                            System.out.println();

                                            if (optionPersonServiceDeliver == 1) {
                                                product[0].productInfoInput();
                                            }
                                            else if (optionPersonServiceDeliver == 2) {
                                                product[0] = stock.stockOut();
                                                if (product[0] != null){
                                                    ac = new FileWriteAndRead(p);
                                                    ac.removeProduct(product[0].getProductId());
                                                    ac.reorder();
                                                }
                                            }
                                            else if (optionPersonServiceDeliver == 3) {
                                                if (delivery == null) {
                                                    System.out.println("YOU HAVEN'T DELIVERY ANYTHING YET");
                                                    System.out.println();
                                                }
                                                else {
                                                    delivery.cancel();
                                                }
                                            }
                                            if (product[0] != null) {
                                                if (optionPersonServiceDeliver == 1 || optionPersonServiceDeliver == 2) {
                                                    Receiver receiver = new Receiver();
                                                    receiver.infoInput();
                                                    delivery = new Delivery(p, product, receiver);
                                                    delivery.DeliveryInfoInput();
                                                    delivery.distanceCalculate();
                                                    p.feeCalculate(product, delivery.getDistance());
                                                    System.out.println();
                                                    p.payment();
                                                    System.out.println();
                                                    delivery.deliveryStatusInquiry();
                                                    p.addDelivery(delivery);
                                                    signUp.deleteAccount("person", p.email, p.getPassword());
                                                    ac = new FileWriteAndRead(p);
                                                    ac.creatPersonAccount();
                                                }
                                            }
                                        } while (optionPersonServiceDeliver != 0);
                                    }
                                    else if (optionPersonService == 2) {

                                        int optionPersonServiceStock;
                                        do {
                                            System.out.println("1 - STOCK IN");
                                            System.out.println("2 - STOCK OUT");
                                            System.out.println("0 - BACK");
                                            System.out.println();

                                            System.out.print("ENTER YOUR OPTION - ");
                                            optionPersonServiceStock = Integer.parseInt(input.nextLine());
                                            System.out.println();

                                            if (optionPersonServiceStock == 1) {
                                                System.out.print("NUMBER OF PRODUCT YOU WANT TO STOCK IN: ");
                                                numberOfProduct = Integer.parseInt(input.nextLine());
                                                product = new Product[numberOfProduct];
                                                System.out.println();
                                                ac = new FileWriteAndRead(p);
                                                for (int i = 0; i<numberOfProduct; i++) {
                                                    System.out.println("PRODUCT NO-"+(i+1));
                                                    product[i] = new Product(p);
                                                    product[i].productInfoInput();
                                                    System.out.println();
                                                    flag2 = stock.stockIn(product[i]);
                                                    stock.RemainingCapacityCalculate();
                                                    if (flag2) {
                                                        ac.writeProduct(product[i]);
                                                        flag3 = true;
                                                    }
                                                }
                                                if (flag3) {
                                                    stock.setNumberOfDay();
                                                }
                                                System.out.println();
                                                p.feeCalculate(product, stock.numberOfDay);
                                            }
                                            else if (optionPersonServiceStock == 2) {
                                                System.out.print("NUMBER OF PRODUCT YOU WANT TO STOCK OUT: ");
                                                numberOfProduct = Integer.parseInt(input.nextLine());
                                                System.out.println();
                                                product = new Product[numberOfProduct];
                                                ac = new FileWriteAndRead(p);
                                                for (int i = 0; i<numberOfProduct; i++) {
                                                    System.out.println("PRODUCT NO-"+(i+1));
                                                    product[i] = stock.stockOut();
                                                    if (product[i] != null) {
                                                        ac.removeProduct(product[i].getProductId());
                                                    }
                                                    System.out.println();
                                                }
                                                ac.reorder();
                                            }
                                            else if (optionPersonServiceStock == 0) {
                                                p.payment();
                                                System.out.println();
                                                signUp.deleteAccount("person", p.email, p.getPassword());
                                                ac = new FileWriteAndRead(p);
                                                ac.creatPersonAccount();
                                            }
                                        } while (optionPersonServiceStock != 0);
                                    }
                                } while (optionPersonService != 0);
                            }
                            else if (optionPerson == 2) {
                                int optionPersonTakenService;
                                do {
                                    System.out.println("1 - SHOW DELIVERIES");
                                    System.out.println("2 - SHOW STOCK PRODUCT");
                                    System.out.println("0 - BACK");
                                    System.out.println();

                                    System.out.print("ENTER YOUR OPTION - ");
                                    optionPersonTakenService = Integer.parseInt(input.nextLine());
                                    System.out.println();

                                    if (optionPersonTakenService == 1) {
                                        p.showDeliveries();
                                    }
                                    else if (optionPersonTakenService == 2) {
                                        stock.stockShow();
                                    }
                                } while (optionPersonTakenService != 0);
                            }
                            else if (optionPerson != 0) {
                                int optionPersonAccountManage;
                                do {
                                    System.out.println("1 - UPDATE ACCOUNT");
                                    System.out.println("2 - SHOW ACCOUNT INFORMATION");
                                    System.out.println("3 - PAY DUE FEE");
                                    System.out.println("0 - BACK");
                                    System.out.println();

                                    System.out.print("ENTER YOUR OPTION - ");
                                    optionPersonAccountManage = Integer.parseInt(input.nextLine());
                                    System.out.println();

                                    if (optionPersonAccountManage == 1) {
                                        signUp.deleteAccount("person", p.email, p.getPassword());
                                        p.accountUpdate();
                                        ac = new FileWriteAndRead(p);
                                        ac.creatPersonAccount();
                                        p = signUp.PersonSignIn("person", p.email, p.getPassword());
                                        ac.deleteDir(p.email);
                                        stock.stockWrite();
                                    }
                                    else if (optionPersonAccountManage == 2) {
                                        p.showAccount();
                                    }
                                    else if (optionPersonAccountManage == 3) {
                                        p.payment();
                                    }
                                } while (optionPersonAccountManage != 0);
                            }
                        } while (optionPerson != 0);
                        flag = false;
                    }
                    else if (p == null && flag) {
                        System.out.println("SIGN-IN FAILED");
                        System.out.println();
                        flag = false;
                    }
                } while (optionAccount != 0);
            }
            else if (option == 2) {
                Company c = null;
                Stock stock;
                int optionAccount;
                do {
                    System.out.println("1 - SIGN-IN");
                    System.out.println("2 - SIGN-UP");
                    System.out.println("3 - DELETE ACCOUNT");
                    System.out.println("0 - BACK");
                    System.out.println();

                    System.out.print("ENTER YOUR OPTION - ");
                    optionAccount = Integer.parseInt(input.nextLine());
                    System.out.println();

                    if (optionAccount == 1) {
                        System.out.print("EMAIL: ");
                        String email = input.nextLine();
                        System.out.print("PASSWORD: ");
                        String password = input.nextLine();
                        System.out.println();
                        c = signUp.companySignIn("company", email, password);
                        flag = true;
                    }
                    else if (optionAccount == 2) {
                        c = new Company();
                        c.infoInput();
                        c.passwordCheckAndSet();
                        System.out.println();
                        FileWriteAndRead ac = new FileWriteAndRead(c);
                        ac.creatCompanyAccount();
                        flag = true;
                    }
                    else if (optionAccount == 3){
                        System.out.print("ENTER EMAIL: ");
                        String email = input.nextLine();
                        System.out.print("ENTER PASSWORD: ");
                        String password = input.nextLine();
                        System.out.println();
                        signUp.deleteAccount("company",email, password);
                        flag = false;
                    }
                    if (c != null && flag) {
                        stock = new Stock(c);
                        FileWriteAndRead ac = new FileWriteAndRead(c);
                        int productNo = 1;
                        while (true) {
                            Product product = ac.readProduct(productNo);
                            if (product != null) {
                                stock.stockIn(product);
                                productNo++;
                            }
                            else {
                                break;
                            }
                        }
                        int optionCompany;
                        do {
                            System.out.println("1 - SERVICES");
                            System.out.println("2 - TAKEN SERVICES");
                            System.out.println("3 - ACCOUNT MANAGE");
                            System.out.println("0 - SIGN-OUT");
                            System.out.println();

                            System.out.print("ENTER YOUR OPTION - ");
                            optionCompany = Integer.parseInt(input.nextLine());
                            System.out.println();

                            if (optionCompany == 1) {
                                int numberOfProduct;
                                Product[] product = new Product[1];
                                product[0] = null;
                                int optionCompanyService;
                                do {
                                    System.out.println("1 - MERCHANDISE DELIVERY");
                                    System.out.println("2 - WAREHOUSE");
                                    System.out.println("0 - BACK");
                                    System.out.println();

                                    System.out.print("ENTER YOUR OPTION - ");
                                    optionCompanyService = Integer.parseInt(input.nextLine());
                                    System.out.println();

                                    if (optionCompanyService == 1) {
                                        Delivery delivery = null;
                                        int optionCompanyServiceDeliver;
                                        do {
                                            System.out.println("1 - DELIVERY FROM ME");
                                            System.out.println("2 - DELIVERY FROM STOCK");
                                            System.out.println("3 - CANCEL DELIVERY");
                                            System.out.println("0 - BACK");
                                            System.out.println();

                                            System.out.print("ENTER YOUR OPTION - ");
                                            optionCompanyServiceDeliver = Integer.parseInt(input.nextLine());
                                            System.out.println();

                                            if (optionCompanyServiceDeliver == 1) {
                                                System.out.print("NUMBER OF PRODUCT: ");
                                                numberOfProduct = Integer.parseInt(input.nextLine());
                                                System.out.println();
                                                product = new Product[numberOfProduct];
                                                ac = new FileWriteAndRead(c);

                                                for (int i = 0; i < numberOfProduct; i++) {
                                                    System.out.println("PRODUCT NO-"+(i+1));
                                                    product[i] = new Product(c);
                                                    product[i].productInfoInput();
                                                    ac.removeProduct(product[i].getProductId());
                                                    ac.reorder();
                                                }
                                            }
                                            else if (optionCompanyServiceDeliver == 2) {
                                                System.out.print("NUMBER OF PRODUCT: ");
                                                numberOfProduct = Integer.parseInt(input.nextLine());
                                                System.out.println();
                                                product = new Product[numberOfProduct];
                                                ac = new FileWriteAndRead(c);

                                                for (int i = 0; i < numberOfProduct; i++) {
                                                    System.out.println("PRODUCT NO-"+(i+1));
                                                    product[i] = stock.stockOut();
                                                    System.out.println();
                                                    if (product[i] != null) {
                                                        ac.removeProduct(product[i].getProductId());
                                                    }
                                                }
                                                ac.reorder();
                                            }
                                            else if (optionCompanyServiceDeliver == 3) {
                                                if (delivery == null) {
                                                    System.out.println("YOU HAVEN'T DELIVERY ANYTHING YET");
                                                    System.out.println();
                                                }
                                                else {
                                                    delivery.cancel();
                                                }
                                            }
                                            if (product[0] != null) {
                                                if (optionCompanyServiceDeliver == 1 || optionCompanyServiceDeliver == 2) {
                                                    Receiver receiver = new Receiver();
                                                    receiver.infoInput();
                                                    delivery = new Delivery(c, product, receiver);
                                                    delivery.DeliveryInfoInput();
                                                    delivery.distanceCalculate();
                                                    c.feeCalculate(product, delivery.getDistance());
                                                    System.out.println();
                                                    c.payment();
                                                    System.out.println();
                                                    delivery.deliveryStatusInquiry();
                                                    c.addDelivery(delivery);
                                                    signUp.deleteAccount("company", c.email, c.getPassword());
                                                    ac = new FileWriteAndRead(c);
                                                    ac.creatCompanyAccount();
                                                }
                                            }
                                        } while (optionCompanyServiceDeliver != 0);
                                    }
                                    else if (optionCompanyService == 2) {
                                        int optionCompanyServiceStock;
                                        do {
                                            System.out.println("1 - STOCK IN");
                                            System.out.println("2 - STOCK OUT");
                                            System.out.println("0 - BACK");
                                            System.out.println();

                                            System.out.print("ENTER YOUR OPTION - ");
                                            optionCompanyServiceStock = Integer.parseInt(input.nextLine());
                                            System.out.println();

                                            if (optionCompanyServiceStock == 1) {
                                                System.out.print("NUMBER OF PRODUCT YOU WANT TO STOCK IN: ");
                                                numberOfProduct = Integer.parseInt(input.nextLine());
                                                product = new Product[numberOfProduct];
                                                System.out.println();
                                                ac = new FileWriteAndRead(c);
                                                for (int i = 0; i<numberOfProduct; i++) {
                                                    System.out.println("PRODUCT NO-"+(i+1));
                                                    product[i] = new Product(c);
                                                    product[i].productInfoInput();
                                                    System.out.println();
                                                    flag2 = stock.stockIn(product[i]);
                                                    stock.RemainingCapacityCalculate();
                                                    if (flag2) {
                                                        ac.writeProduct(product[i]);
                                                        flag3 = true;
                                                    }
                                                }
                                                if (flag3) {
                                                    stock.setNumberOfDay();
                                                }
                                                System.out.println();
                                                c.feeCalculate(product, stock.numberOfDay);
                                            }
                                            else if (optionCompanyServiceStock == 2) {
                                                System.out.print("NUMBER OF PRODUCT YOU WANT TO STOCK OUT: ");
                                                numberOfProduct = Integer.parseInt(input.nextLine());
                                                product = new Product[numberOfProduct];
                                                System.out.println();
                                                ac = new FileWriteAndRead(c);
                                                for (int i = 0; i<numberOfProduct; i++) {
                                                    System.out.println("PRODUCT NO-"+(i+1));
                                                    product[i] = stock.stockOut();
                                                    System.out.println();
                                                    if (product[i] != null) {
                                                        ac.removeProduct(product[i].getProductId());
                                                    }
                                                }
                                                ac.reorder();
                                            }
                                            else if (optionCompanyServiceStock ==0) {
                                                c.payment();
                                                System.out.println();
                                                signUp.deleteAccount("company", c.email, c.getPassword());
                                                ac = new FileWriteAndRead(c);
                                                ac.creatCompanyAccount();
                                            }
                                        } while (optionCompanyServiceStock != 0);
                                    }

                                } while (optionCompanyService !=0);
                            }
                            else if (optionCompany == 2) {
                                int optionCompanyTakenService;
                                do {
                                    System.out.println("1 - SHOW DELIVERIES");
                                    System.out.println("2 - SHOW STOCK PRODUCT");
                                    System.out.println("0 - BACK");
                                    System.out.println();

                                    System.out.print("ENTER YOUR OPTION - ");
                                    optionCompanyTakenService = Integer.parseInt(input.nextLine());
                                    System.out.println();

                                    if (optionCompanyTakenService == 1) {
                                        c.showDeliveries();
                                    }
                                    else if (optionCompanyTakenService == 2) {
                                        stock.stockShow();
                                    }
                                } while (optionCompanyTakenService != 0);
                            }
                            else if (optionCompany == 3) {
                                int optionCompanyAccountManage;
                                do {
                                    System.out.println("1 - UPDATE ACCOUNT");
                                    System.out.println("2 - SHOW ACCOUNT INFORMATION");
                                    System.out.println("3 - PAY DUE FEE");
                                    System.out.println("0 - BACK");
                                    System.out.println();

                                    System.out.print("ENTER YOUR OPTION - ");
                                    optionCompanyAccountManage = Integer.parseInt(input.nextLine());
                                    System.out.println();

                                    if (optionCompanyAccountManage == 1) {
                                        signUp.deleteAccount("company", c.email, c.getPassword());
                                        c.accountUpdate();
                                        ac = new FileWriteAndRead(c);
                                        ac.creatCompanyAccount();
                                        c = signUp.companySignIn("company", c.email, c.getPassword());
                                    }
                                    else if (optionCompanyAccountManage == 2) {
                                        c.showAccount();
                                    }
                                    else if (optionCompanyAccountManage == 3) {
                                        c.payment();
                                    }
                                } while (optionCompanyAccountManage != 0);
                            }
                        } while (optionCompany != 0);
                        flag = false;
                    }
                    else  if (c == null && flag) {
                        System.out.println("SIGN-IN FAILED");
                        System.out.println();
                        flag = false;
                    }
                } while (optionAccount != 0);
            }
        }while (option !=0);
        System.out.println("------------------------");
        System.out.println("THANK YOU - VISIT AGAIN");
        System.out.println("------------------------");
    }
}
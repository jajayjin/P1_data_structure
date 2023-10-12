import java.io.*;
import java.util.Scanner;
import java.text.DecimalFormat;
public class Payment {
    private static final DecimalFormat df = new DecimalFormat("0.00");
    Storage store;
    private double totalprice=0;
    private double totalpricewithdiscount=0;
    private boolean discountvalid=false;
    private double discount1=0;
    private int check=0;
    Linkedlist<Cart> cart;
    Arraylist<Items> item;
    boolean payValid;
    public Scanner s;
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public Payment(Storage store,Linkedlist<Cart> cart,Arraylist<Items> item){
        this.cart =cart;
        this.store=store;
        this.item = item;
        s = new Scanner(System.in);
        payValid=false;

    }
   public void totalprice(){
        for(int i=0;i< cart.size();i++) {
            Cart cc = (Cart) cart.nodeAt(i).getItem();
            totalprice += cc.getcartItems().getPrice() * cc.getquan();
        }
    }
    public void pay(int pay){
        if(pay==1){
            System.out.print("Put your cash in this machine e.g. 1520.2\n");
            Double cash;
            do{
                cash = s.nextDouble();
                if(cash < totalprice){
                    System.out.println("Not enough money! Please try again");
                }else{
                    checkout();
                    payValid=true;
                    for(int i=0;i<3;i++){
                        for (int j = 0; j < 35; j++ ){
                            if (i == 0){
                                if(j==0){
                                    System.out.print("Cash");
                                    j+=4;
                                } else if (j >= 30) {
                                    System.out.print(Double.toString(cash));
                                    j += Double.toString(cash).length();
                                } else {
                                    System.out.print(" ");
                                }
                            }else if(i==1){
                                System.out.print("-");
                            }else{
                                if(j==0){
                                    System.out.print("Balance");
                                    j+=7;
                                } else if (j >= 30) {
                                    System.out.print(df.format(cash-totalprice));
                                    j += Double.toString(cash-totalprice).length();
                                } else {
                                    System.out.print(" ");
                                }
                            }
                        }System.out.print("\n");
                    }
                    for (int j = 0; j < 35; j++) {
                        System.out.print("-");
                    }
                    System.out.print("\n");
                }
            }while(cash<totalprice);
        }else if(pay ==2){
            String visa = "";
            do{
                System.out.println("Input your VISA card number for 16 number :");
               // s.nextLine();
                visa = s.nextLine();
                if(visa.length()!=16){
                    System.out.println("Please try again! The number is wrong");
                }else{
                    checkout();
                    for (int j = 0; j < 35; j++ ){
                            if(j==0){
                                System.out.print("VISA**"+visa.substring(12));
                                j+=(6+ visa.substring(12).length());
                            } else if (j >= 30) {
                                System.out.print(df.format(totalprice));
                                j += Double.toString(totalprice).length();;
                            } else {
                                System.out.print(" ");
                            }

                        }
                    System.out.print("\n");
                    for (int j = 0; j < 35; j++) {
                        System.out.print("-");
                    }System.out.print("\n");
                    }
                    payValid=true;
            }while(visa.length()!=16);
        }else if(pay==3){
            System.out.println("Scan this QR");

            for(int i = 0;i<4;i++){
                for(int j = 0;j<10;j++){
                    if(j<3 || j>7){
                        System.out.print("x");
                    }else if(j==5 || j==7){
                        System.out.print("*");
                    }
                    else{
                        System.out.print("-");
                    }
                }
                System.out.print("\n");
            }
            System.out.print("\n");
            checkout();
            payValid=true;

        }else{
            System.out.print("Sorry, the number is not in the choice");
        }

    }
    public void checkout() {
            if(check==0){
                totalprice();
                check++;
            }
            System.out.print("\n");
            System.out.print("\n");
            for (int i = 0; i < (7); i++) {
                for (int j = 0; j < 35; j++) {
                    if (i == 0 || i == 2 || i == 4 || i == 6|| i==7) {
                        System.out.print("-");
                    } else if (i == 1) {
                        if (j < 11 || j >= 23) {
                            System.out.print("-");
                        } else {
                            System.out.print("Rich man shop");
                            j += 12;
                        }
                    } else if (i == 5) {
                        if (j == 0) {
                            System.out.print("Item  ID");
                            j += 8;
                        } else if (j >= 30) {
                            System.out.print("Price");
                            j += 5;
                        } else {
                            System.out.print(" ");
                        }
                    }
                }
                System.out.print("\n");
            }
            for(int i= 0;i< cart.size();i++) {
                for (int k = 0; k < 2; k++) {
                    Cart cc = (Cart) cart.nodeAt(i).getItem();
                    for (int j = 0; j < 35; j++) {
                        if (k % 2 == 0) {
                            if(j==0) {
                                System.out.print("Item  " +cc.getcartItems().getID());
                                j+=(6+Integer.toString(cc.getcartItems().getID()).length());
                            }else {
                                System.out.print(" ");
                            }
                        }else {
                            if(j==0) {
                                System.out.print("      " +cc.getcartItems().getName()+"x"+cc.getquan());
                                j+=(6+cc.getcartItems().getName().length()+1+Integer.toString(cc.getquan()).length());
                            }else if(j>=30) {
                                System.out.print(df.format(cc.getcartItems().getPrice()*cc.getquan()));
                                j += 5;
                            }else {
                                System.out.print(" ");
                            }
                        }
                    }System.out.print("\n");
                }
            }
            for (int j = 0; j < 35; j++) {
                System.out.print("-");
            }
            System.out.print("\n");
            for (int j = 0; j < 35; j++) {
                if(j==0){
                    System.out.print("Sub-Total:");
                    j+=10;
                } else if (j >= 30) {
                    System.out.print((df.format(totalprice)));
                    j += (df.format(totalprice)).length();
                } else {
                    System.out.print(" ");
                }
            }
            System.out.print("\n");
            for (int j = 0; j < 35; j++) {
                System.out.print("-");
            }
            System.out.print("\n");
            if(discountvalid){
                for (int j = 0; j < 35; j++) {
                    if(j==0){
                        System.out.print("Discount");
                        j+=8;
                    } else if (j >= 30) {
                        System.out.print(df.format(discount1));
                        j += df.format(discount1).length();
                    } else {
                        System.out.print(" ");
                    }
                }
                System.out.print("\n");
                for (int j = 0; j < 35; j++) {
                    System.out.print("-");
                }
                System.out.print("\n");
                for (int j = 0; j < 35; j++) {
                    if(j==0){
                        System.out.print("Total price:");
                        j+=12;
                    } else if (j >= 30) {
                        System.out.print(df.format(totalpricewithdiscount));
                        j += df.format(totalpricewithdiscount).length();
                    } else {
                        System.out.print(" ");
                    }
                }
                System.out.print("\n");
                for (int j = 0; j < 35; j++) {
                    System.out.print("-");
                }
                System.out.print("\n");
                System.out.print("\n");
            }
    }
    public void checkdiscount(){
        System.out.print("Do you want to use a discount?\nType Yes or No: ");
        String st = s.nextLine();
        if(st.equalsIgnoreCase("yes")){
            System.out.print("One order can use just one discount! Choose the most suitable chioce!\n");
            String date="";
            String[] dates = new String[3];
            do{
                System.out.print("Please enter your date of payment e.g. 12/08/66: ");

                date = s.nextLine();
                dates = date.split("/");
                if(!(Integer.parseInt(dates[0])>31 || Integer.parseInt(dates[0])<0 || Integer.parseInt(dates[1])<0||Integer.parseInt(dates[1])>12 )){
                    if(Integer.parseInt(dates[1])==12 || Integer.parseInt(dates[1])>=1&&Integer.parseInt(dates[1])<=2){
                        System.out.print("You got discount! This is summer time! Discount for 10%\n");
                        System.out.print("If you want to keep this discount: the code is\n" +"SUMMER120\n");
                        discount();
                    }else if(Integer.parseInt(dates[1])>3&&Integer.parseInt(dates[1])<=5){
                        System.out.print("You got discount! This is rainy time! Discount for 20%\n");
                        System.out.print("If you want to keep this discount: the code is\n" +"RAINY120\n");
                        discount();
                    }else if(Integer.parseInt(dates[1])>5&&Integer.parseInt(dates[1])<=8){
                        System.out.print("You got discount! This is Back to school time! Discount for 12%\n");
                        System.out.print("If you want to keep this discount: the code is\n" +"SCHOOL120\n");
                        discount();
                    }else if(Integer.parseInt(dates[1])>9&&Integer.parseInt(dates[1])<=10){
                        System.out.print("You got discount! This is Winter time! Discount for 25%\n");
                        System.out.print("If you want to keep this discount: the code is\n" +"WINTER120\n");
                        discount();
                    }else{
                        System.out.print(ANSI_RED+"Sorry, no this count TT\n"+ANSI_RESET);
                    }

                }else{
                    System.out.print("Please enter the correct date!\n");
                }

            }while((Integer.parseInt(dates[0])>31 || Integer.parseInt(dates[0])<0 || Integer.parseInt(dates[1])<0||Integer.parseInt(dates[1])>12));
    }}

    public void discount() {
        System.out.print("Now, you got the discount! Type it! e.g. SOMMAR: ");
        String discount = s.nextLine();
        checkout();
        if (discount.equals("SUMMER120")) {
            discountvalid = true;
            discount1 = totalprice * 0.1;
            totalpricewithdiscount = totalprice - discount1;
            System.out.print("You got discount for summer!\n");

        } else if (discount.equals("RAINY120")) {
            discountvalid = true;
            discount1 = totalprice * 0.2;
            totalpricewithdiscount = totalprice - discount1;
            System.out.print("You got discount for rainy!\n");
        } else if (discount.equals("SCHOOL120")) {
            discountvalid = true;
            discount1 = totalprice * 0.12;
            totalpricewithdiscount = totalprice - discount1;
            System.out.print("You got discount for back to school!\n");
        } else if (discount.equals("WINTER120")) {
            discountvalid = true;
            discount1 = totalprice * 0.25;
            totalpricewithdiscount = totalprice - discount1;
            System.out.print("You got discount for winter!\n");
        }
    }

    public boolean isPayValid(){
        return payValid;
    }
}


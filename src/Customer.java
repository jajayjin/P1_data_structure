import java.util.Scanner;

public class Customer {
    public Storage store;
    //public Linkedlist<Cart> cart;
    Scanner scan;
    public Payment payment;
    public Arraylist<Items> item;
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";

    public Customer(Storage store,Linkedlist<Cart> cart,Payment payment, Arraylist<Items> item){
        this.store = store;
      //  this.cart = cart;
        scan = new Scanner(System.in);
        this.payment=payment;
        this.item = item;
    }
    public void editforcus(String n){

        System.out.print("Hello! "+n+"\n");
        System.out.print("Now, your cart is clean. Let's put some stuff in!\n\n");
        System.out.print("This is our product!\n\n");
        store.printItem();
        System.out.print("\nNow, This is time to manage your cart!");
        editcart();
        System.out.print("\nFinally this cart finished\n");
        store.printcart();
        System.out.print("Are you confirm this cart? Type Yes or No\n");
        scan.nextLine();
        String confirm = scan.nextLine();
        if(confirm.equalsIgnoreCase("yes")){
            System.out.print("Checkout!");
            payment.checkout();
            payment.checkdiscount();
            System.out.print("There are three types for you to check out:\n1: Cash\n2: VISA card\n3: QR Promptpay\n");
            System.out.print("\nPlease press number\n");
            int pay = scan.nextInt();
            payment.pay(pay);
            if(payment.isPayValid()){
                store.updatequantity();
            }
        }else{
            editcart();
        }
    }
    public void editcart(){ //make choices for customer to choose to edit storage
        int manage=0;
        do {
            System.out.print("\nPlease press number that you want.\n1: add product\n2: remove quantity of product in cart\n3: remove item out of product" +
                    "\n4: clear all product\n5: check all list of goods\n6: find item by name\n7: find item by id \n8: find products by categories" +
                    "\n9: show product in the ranges of price\n10: order the cart\n11: exit the editing mode\n");
            manage = scan.nextInt();
            if (manage == 1) {
                store.foraddingproduct();
            }else if(manage == 2){
                store.removequanofcart();
            }else if(manage==3){
                store.forremovingproduct();
            }else if(manage==4){
                store.clearall();
            }else if(manage==6){
                System.out.print(findItembyname()+"\n"+"\n");
            }else if(manage==7){
                System.out.print(findItembyId()+"\n"+"\n");
            }else if(manage==5){
                store.printItem();
            }else if(manage==10){
                orderthelist();
            }else if(manage==8){
                findItemfromcategory();
            }else if(manage==9){
                findItembypricerange();
            }
        }while(manage != 11);
    }
    public void findItemfromcategory(){ // For customer to choose the categories for finding goods
        store.updatecategories();
        System.out.print("We have "+ Storage.category.size()+" categories\n");
        for(int i=0;i<Storage.category.size();i++){
            System.out.println((i+1)+": "+Storage.category.get(i));
        }
        int num=0;
        do{
            System.out.print("Enter number of category you want to search e.e. 1 (finish press 99): ");
            num = scan.nextInt();
            if(num!=99){
                if(num> Storage.category.size()||num<=0){
                    System.out.print(ANSI_RED+"There is no this index for categories. Try again!"+ANSI_RESET);
                }else{
                    for(int i=0;i<Storage.listitem.size();i++){
                        if(Storage.listitem.get(i).getCategory().equals(Storage.category.get(num-1))){
                            System.out.println("\n");
                            System.out.println(Storage.listitem.get(i));
                        }
                    }
                }
            }
        }while(num!=99);

    }
    public void findItembypricerange(){ //
        System.out.print("You can sort product from price range!\n");
        System.out.print("Please, enter price range that you want e.g. 500-1200: ");
        scan.nextLine();
        String pricer= scan.nextLine();
        String[] price=pricer.split("-");
        int j=0;
        for(int i=0;i< Storage.listitem.size();i++){
            if(Storage.listitem.get(i).getPrice()>=Double.parseDouble(price[0]) && Storage.listitem.get(i).getPrice()<=Double.parseDouble(price[1])){
                System.out.println("\n");
                System.out.println(Storage.listitem.get(i));
                j++;
            }
        }
        if(j==0){
            System.out.println(ANSI_RED+"Sorry, no product in this price-range!"+ANSI_RESET);
        }
    }
    public Items findItembyname(){
        System.out.print("\nYou can find and check our items in our shop by press the name of items");
        System.out.print("\nIf finish press 99\n");
        String id="";
        do{
            System.out.print("\nPlease press name e.g. Shabu(finish press 99)\n");
            id = scan.nextLine();
            if(!id.equals("99")){
                for(int i=0; i < Storage.listitem.size() ; i++){
                    if(Storage.listitem.get(i).getName().equals(id)){
                        return Storage.listitem.get(i);
                    }
                }

            }
        }while(!id.equals("99"));
        System.out.print(ANSI_RED+"Sorry,this item is not in your cart"+ANSI_RESET);
        return null;
    }
    public Items findItembyId(){
        System.out.print("\nYou can find and check our items in our shop by press ID of items");
        System.out.print("\nIf finish press 99\n");
        int id=0;
        do{
            System.out.print("\nPlease press Id e.g. 1234(finish press 99)\n");
            id = scan.nextInt();
            if(!(id==99)){
                for(int i=0; i < Storage.listitem.size() ; i++){
                    if(Storage.listitem.get(i).getID()==(id)){
                        return Storage.listitem.get(i);
                    }
                }

            }
        }while(!(id==99));
        System.out.print(ANSI_RED+"Sorry,this item is not in your cart"+ANSI_RESET);
        return null;
    }
    public void orderthelist(){

        String r="";
        do{System.out.print("You can order the product e.g. change from id1 to id2. Type 1,2( If ypu finish, please type 'finish')\n");
            scan.nextLine();
            r = scan.nextLine();
            if(!r.equalsIgnoreCase("finish")){
                String[] id =r.split(",");
                int id1 = Integer.parseInt(id[0])-1;
                int id2 = Integer.parseInt(id[1])-1;
                Cart cc= (Cart) Storage.cart.nodeAt(id1).getItem();
                ((Cart) Storage.cart.nodeAt(id1).getItem()).setID(id2+1);
                ((Cart) Storage.cart.nodeAt(id2).getItem()).setID(id1+1);
                Storage.cart.set(id1,(Cart) Storage.cart.nodeAt(id2).getItem());
                Storage.cart.set(id2, cc);

                store.printcart();
            }
        }while(!(r.equalsIgnoreCase("finish")));
    }
}

import java.util.InputMismatchException;
import java.util.Scanner;
public class Admin {
    public Storage store;
    public Arraylist<Items> listitem;
    Scanner scan;


    public Admin(Storage store,Arraylist<Items> listitem){
        this.store=store;
        this.listitem=listitem;
        scan = new Scanner(System.in);

    }
    public void editItem(){
        System.out.print("Hello! Admin\n");
        System.out.print("Now, our store already have stocks!\n");
        int choice=0;
        do{
            System.out.print("Do you want to change something?\n1: add new item\n2: remove item\n3: change the value in items\nif not press 99\n");
            choice=scan.nextInt();
            if(choice==1){
                System.out.print("Go start to put your items in stock\n");
                System.out.print("How many items do you want to add in?\n");
                int how = scan.nextInt();
                scan.nextLine();
                try{
                    for(int i=0;i<how;i++){
                        System.out.print("Name of items: \n");
                        String name = scan.nextLine();
                        System.out.print("Descriptions of items: \n");
                        String de = scan.nextLine();
                        System.out.print("Price of items: \n");
                        int price = scan.nextInt();
                        System.out.print("Quantity of items: \n");
                        int quan = scan.nextInt();
                        System.out.print("Categories of items: ");
                        System.out.print("\n");
                        scan.nextLine();
                        String cate = scan.nextLine();
                        //add item to cart
                        Items n =new Items(name,de,price,quan,cate);
                        store.addItemsbyvendor(n);
                        scan.nextLine();
                    }
                    System.out.print("\nThis is all your product\n");
                    store.printItem();

                    System.out.print("Do you want to change something? Press any number\nif not press 99\n");

                    int not= scan.nextInt();
                    if(not!=99) {
                        int x = 0;
                        do {
                            System.out.print("Press ID of item: ");
                            int id = scan.nextInt();
                            System.out.print("Press number 1 for changing name, 2 for changing description\n3 for changing price, 4 change numbers of stock, 5 change category: ");
                            int method = scan.nextInt();
                            store.updateproductforvendor(id,method);
                            System.out.print("When finish press 5, if not press 0\n");
                            x = scan.nextInt(); //this consume int
                            scan.nextLine();// this consume newline
                        } while (x > 5 || x < 5);

                        System.out.print("This is all your product\n");
                        store.printItem();
                    }
                } catch (InputMismatchException e){
                    System.out.print("Your input is not number");
                } catch (NumberFormatException e){
                    System.out.print("Cannot convert to number");
                } catch (Exception e) {
                    throw new RuntimeException("Out of bound list item");
                }
            }else if(choice==2){
                System.out.print("Go start to remove quantity of your items in stock\n");
                System.out.print("How many items do you want to remove in?\n");
                int how = scan.nextInt();
                scan.nextLine();
                try{
                    for(int i=0;i<how;i++){
                        System.out.print("Press id of Item you want to remove e.g. 1235\n");
                        int id = scan.nextInt();
                        Items item=store.getitemsbyarray(id);
                        System.out.print("How many quantity you want to remove? e.g. Coppers are in stock for 5 pieces, but want to remove 3 pieces. Type 3\n");
                        int howmany = scan.nextInt();
                        if(item!=null){
                            store.removeItemsbyvendor(item,howmany);
                        }
                    }
                    System.out.print("\nThis is all your product\n");
                    store.printItem();

                    System.out.print("Do you want to change something? Type any number\nIf not press 99\n");

                    int not= scan.nextInt();
                    if(not!=99) {
                        int x = 0;
                        do {
                            System.out.print("Press ID of item: ");
                            int id = scan.nextInt();
                            System.out.print("Press number 1 for changing name, 2 for changing description, 3 for changing price, 4 change numbers of stock: ");
                            int method = scan.nextInt();
                            store.updateproductforvendor(id,method);
                            System.out.print("When finish press 5, if not press 0\n");
                            x = scan.nextInt(); //this consume int
                            scan.nextLine();// this consume newline
                        } while (x > 5 || x < 5);

                        System.out.print("This is all your product\n");
                        store.printItem();
                    }
                } catch (InputMismatchException e){
                    System.out.print("Your input is not number");
                } catch (NumberFormatException e){
                    System.out.print("Cannot convert to number");
                } catch (Exception e) {
                    throw new RuntimeException("Out of bound list item");
                }
            }else if(choice ==3){
                System.out.print("Do you want to change something e.g. change name of product etc.?Type any number\n if not press 99\n");
                int not= scan.nextInt();
                if(not!=99) {
                    int x = 0;
                    do {
                        System.out.print("Press ID of item: ");
                        int id = scan.nextInt();
                        System.out.print("Press number 1 for changing name, 2 for changing description, 3 for changing price, 4 change numbers of stock: ");
                        int method = scan.nextInt();
                        store.updateproductforvendor(id,method);
                        System.out.print("When finish press 5, if not press 0\n");
                        x = scan.nextInt(); //this consume int
                        scan.nextLine();// this consume newline
                    } while (x > 5 || x < 5);

                    System.out.print("This is all your product\n");
                    store.printItem();
                }
            }
        }while(choice != 99);
    }
}

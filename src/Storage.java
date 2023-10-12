import java.util.NoSuchElementException;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.*;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
public class Storage {
    public static Arraylist<Items>  listitem;
    public static Linkedlist<Cart>  cart;
    public static Arraylist<String> category;
    public Scanner s;

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public Storage(Arraylist<Items> list,Linkedlist<Cart> cart){
        this.listitem = list;
        this.cart=cart;
        s = new Scanner(System.in);
    }
    public void readitemfromfile(String path){
        File file = new File(path);
        try {
            FileInputStream fs = new FileInputStream(file);
            InputStreamReader fr = new InputStreamReader(fs);
            BufferedReader br = new BufferedReader(fr);
            String headerline =br.readLine();
            String[] header = headerline.split(",");
            String line;

            while((line=br.readLine())!= null) {
                String[] value = line.split(",");
                String name = value[0];
                String des = value[2];
                double price = Double.parseDouble(value[3]);
                int totalstock = Integer.parseInt(value[4]);
                String cate = value[1];
                listitem.add(new Items(name,des,price,totalstock,cate));
            }
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch(NullPointerException e) {
            System.out.print("NullPointerException caught");
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
    //manage store products
    public void updatequantity(){
        for(int i=0;i< cart.size();i++){
            for(int j=0;j< listitem.size();j++){
                Items item = ((Cart) cart.nodeAt(i).getItem()).getcartItems();
                if(listitem.get(j) == item){
                    item.setQuantity(listitem.get(j).getQuantity()-((Cart)cart.nodeAt(i).getItem()).getquan());
                }
            }
        }
    }
    public Items getitemsbyarray(int id){
        for(int i=0;i<listitem.size();i++){
            if(listitem.get(i).getID() == id){
                return listitem.get(i);
            }
        }
        System.out.print(ANSI_RED+"Sorry, we cannot find this Id in our shop TT\n"+ANSI_RESET);
        return null;
    }
    public void printcart(){
        if(cart.isEmpty()){
            System.out.println("Cart is clean TT");
        }else{
            for(int i=0;i<cart.size();i++){
                System.out.println(cart.nodeAt(i).getItem().toString()+"\n");
            }
        }

    }
    public void printItem(){
        if(listitem.isEmpty()){
            System.out.println("All products are out of stock TT");
        }
        for(int i=0;i< listitem.size();i++){
            System.out.println(listitem.get(i).toString()+"\n");
        }
    }
    public void recheckquan(Items item, Cart c){
        if(item == null){
            System.out.print("Please select new Id\n");
        }else
        if(c.getquan() > item.getQuantity()){
            c.setQuanequamax(item.getQuantity());
            System.out.print(ANSI_RED+"We have item not enough for you TT\n"+ANSI_RESET);
        }

    }

    //For vendor to update products
    public void addItemsbyvendor(Items item){
        int j=0;
        for(int i=0;i<listitem.size();i++){
            if(listitem.get(i).getName().equals(item.getName()) &&listitem.get(i).getDecription().equals(item.getDecription())){
                j++;
                listitem.get(i).increaseQuan(item.getQuantity());
            }
        }
        if(j==0){
            listitem.add(item);
        }
    }
    public void removeItemsbyvendor(Items item,int u){
        int j=0;

        for(int i=0;i<listitem.size();i++){
            if(listitem.get(i).equals(item)){
                j++;
                listitem.get(i).decreaseQuan(u);
                if(listitem.get(i).getQuantity()<=0){
                    listitem.remove(i);
                }
            }
        }
        if(j==0){
            listitem.add(item);
        }
    }
    public void updateproductforvendor(int id,int number){
        Items n = getitemsbyarray(id);
        if(number == 1 ){
            System.out.print("New name:");
            String name = s.nextLine() ;
            n.setname(name);
        } else if (number == 2) {
            System.out.print("New description:");
            String name = s.nextLine() ;
            n.setDe(name);
        }else if(number == 3){
            System.out.print("New Price:");
            int name = s.nextInt() ;
            n.setPrice(name);
        }else if(number == 4){
            System.out.print("New Stocks:");
            int name = s.nextInt() ;
            n.setQuantity(name);
            if(n.getQuantity()==0){
                listitem.remove(n);
            }
        }else if(number ==5){
            System.out.print("New Category:");
            String name = s.nextLine() ;
            n.setCategory(name);
        }
        else{
            throw new IllegalArgumentException(ANSI_RED+"Out of method"+ANSI_RESET);
        }
    }
    //forcustomers
    private void addcart(int id,int quan){
        Items item = getitemsbyarray(id);
        Cart c = new Cart(item,quan);
            int j=0;
            for(int i=0;i<cart.size();i++){
                Cart cc =(Cart) cart.nodeAt(i).getItem();
                if(cc.getcartItems().equals(item)){
                    cc.setQuan(quan);
                    recheckquan(item,cc);
                    System.out.print( ANSI_GREEN+"Add already!\n"+ ANSI_RESET);
                    j++;
                    break;
                }
            }
            if(j==0 && item!=null){
                cart.add(c);
                recheckquan(item,c);
                System.out.print( ANSI_GREEN+"Add already!\n"+ ANSI_RESET);
            }
    }

    public void foraddingproduct(){
        System.out.print("\nYou can add items to your cart by press the Id of items");
        System.out.print("\nIf finish press 99\n");
        int id=0;
        do{
            System.out.print("\nPlease press Id e.g. 1234(finish press 99)\n");
            id = s.nextInt();
            if(id!=99){
                System.out.print("How much do you want e.g. 5\n");
                int q = s.nextInt();
                addcart(id,q);
            }
        }while(id != 99);
        System.out.print(ANSI_YELLOW+"\nFinally this cart finished\n"+ANSI_RESET);
        printcart();
    }
    public void forremovingproduct(){
        System.out.print("\nYou can remove items to your cart by press the Id of items");
        System.out.print("\nIf finish press 99\n");
        int id=0;
        do{
            System.out.print("\nPlease press Id e.g. 1234(finish press 99)\n");
            id = s.nextInt();
            if(id!=99){
                Items item = getitemsbyarray(id);
                for(int i=0; i < cart.size() ; i++){
                    Cart cc =(Cart) cart.nodeAt(i).getItem();
                    if(item.equals(cc.getcartItems()) ){
                        cart.removeAt(i);
                        System.out.print("remove already!\n");
                    }
                }
            }

        }while(id != 99);
        System.out.print(ANSI_YELLOW+"\nFinally this cart finished\n"+ANSI_RESET);
        printcart();
    }
    public void removequanofcart(){
        System.out.print("\nYou can remove items to your cart by press the Id of items");
        System.out.print("\nIf finish press 99\n");
        int id=0;
        int num=0;
        do{
            System.out.print("\nPlease press Id e.g. 1234(finish press 99)\n");
            id = s.nextInt();
            int j=0;
            if(id!=99){
                System.out.print("\nPress quantity that you want to reduce e.g. if you want to reduce qauntity incart from 5 to 3 press 2 \n");
               num =s.nextInt();
                if(num>=0){
                    Items item = getitemsbyarray(id);
                    for(int i=0; i < cart.size() ; i++){
                        Cart cc =(Cart) cart.nodeAt(i).getItem();
                        if(item.equals(cc.getcartItems()) ){
                            cc.removeQuan(num);
                            j++;
                            if(cc.getquan()<=0){
                                cart.removeAt(i);
                                System.out.print(ANSI_GREEN+"You remove the items from the cart! "+ANSI_RESET);
                            }else{
                                System.out.print("You get a new quantity for item's Id: "+id+"!");
                            }
                        }
                    }
                    if(j==0){
                        System.out.print(ANSI_RED+"Sorry,this item is not in your cart"+ANSI_RESET);
                    }
                }else{
                    System.out.print(ANSI_RED+"Enter positive number please"+ANSI_RESET);
                }
            }
        }while(id != 99 || num<0);
        System.out.print(ANSI_YELLOW+"\nFinally this cart finished\n"+ANSI_RESET);
        printcart();
    }

    public void clearall(){
        cart.clear();
        System.out.print(ANSI_GREEN+"Clear your cart already!\n"+ANSI_RESET);
        printcart();
    }

    //extra
    public void updatecategories(){ // For update the arraylist of categories
        int k=0;
        category = new Arraylist<>();
        for(int i=0;i<listitem.size();i++){
                for(int j=0;j< category.size();j++){
                    if(listitem.get(i).getCategory().equalsIgnoreCase(category.get(j))){
                        k++;
                    }
                }
                if(k==0){
                    category.add(listitem.get(i).getCategory());
                }
            k=0;
        }
    }


    public void writefile(){ // to manage items in listitem ( write final listitem after purchase
        PrintWriter out =null;
        try {
            out = new PrintWriter("ListItem.csv");
            out.println("Item Name,Category,Description,Price,Quantity");
            for(int i=0;i<listitem.size();i++){
                Items item = listitem.get(i);
                out.println(item.getName()+","+item.getCategory()+","+item.getDecription()+","+item.getPrice()+","+item.getQuantity());
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            out.close();
        }
    }

}

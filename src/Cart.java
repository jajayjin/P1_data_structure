public class Cart{
    //items from customers
    private int quan;
    private Items n;
    private static int id=1;
    private int order;
    public Cart(Items n,int q){
        this.n=n;
        this.quan = q;
        order=id;
        id++;
    }

    public int getquan(){ return quan;};
    public void setQuan(int n){
        quan += n;
    }
    public void removeQuan(int n){
        quan -= n;
    }
    public void setQuanequamax(int n){
        quan = n;
    }
    public Items getcartItems(){ return n;};
    public int getId(){
        return id;
    }
    public void setID(int n){
        order = n;
    }
    public String toString(){
        return ("Order: "+order+"\nID: "+n.getID()+	"\nName:"+ n.getName() + "\nDescription:"+ n.getDecription()
                + "\nPrice:" + n.getPrice() + "\nNow in the cart: " + quan);
    }
}

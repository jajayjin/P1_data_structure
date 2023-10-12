public class Items {
    public static int ID = 1234;
    public int id;
    public String name;
    public String description;
    public double price;
    public int totalinstock;
    public String category;

    public Items(String name,String description, double price,int totalinstock,String category) {
        id=ID;
        ID += 1;
        this.name = name;
        this.description = description;
        this.price= price;
        this.totalinstock = totalinstock;
        this.category = category;
    }

    public String getName() {
        return name;
    }
    public int getID() {
        return id;
    }

    public String getDecription(){
        return description;
    }
    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return totalinstock;
    }
    public String getCategory(){
        return category;
    }
    public void setQuantity(int n) {
            totalinstock =n;
    }
    public void decreaseQuan(int n){
        totalinstock -= n;
       setQuantity(totalinstock);
    }
    public void increaseQuan(int n){
        totalinstock += n;
        setQuantity(totalinstock);
    }
    public void setname( String m) {
        name = m;
    }
    public void setDe( String m){ description = m;}
    public void setPrice( int m){ price = m;}
    public void setCategory(String m){
        category=m;
    }
    public String toString(){
        return ("ID: "+getID()+	"\nCategory: "+getCategory()+"\nName: "+ getName() + "\nDescription: "+ getDecription()
                + "\nPrice: " + getPrice() + "\nStock in store now: " + getQuantity());
    }


}

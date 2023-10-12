import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.*;
public class Main {

	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_RED = "\u001B[31m";
    public static void main(String[] args){
		Scanner scan = new Scanner(System.in);
		Arraylist<Items> listitem = new Arraylist<Items>();
		Linkedlist<Cart> cart = new Linkedlist<>();
		Storage store = new Storage(listitem,cart);
		Payment payment = new Payment(store,cart,listitem);
		Customer cus = new Customer(store,cart,payment,listitem);
		Admin admin = new Admin(store,listitem);
		System.out.print("Hello! Welcome to our rich man shop\n");
		System.out.print("Please enter you name"+ ANSI_RED+" ***Must more than 3 characters***\n"+ANSI_RESET);
		store.readitemfromfile("ListItem.csv");
		String name = scan.nextLine();
		if(name.substring(0,3).equals("FWD")){
				admin.editItem();
				store.writefile();
				System.out.print(ANSI_YELLOW+"This time for shopping! Press 1 if you want to shopping. 2 for exit"+ANSI_RESET+"\n");
				int choice = scan.nextInt();
				if(choice == 1){
					cus.editforcus(name);
				}
		}else{
			cus.editforcus(name);
		}
		for(int i=0;i<2;i++){
			for (int j = 0; j < 35; j++) {
				if(i==1){
					System.out.print("-");
				}else{
					if(j==13){
						System.out.print("Thank you");
						j+=9;
					}else{
						System.out.print("-");
					}
				}
			}System.out.print("\n");
		}
		System.out.print("\nFinal list item:\n");
		store.writefile();
		}
}
package edu.bsu.cs222.finalProject;

import org.joda.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Display {
    public void displayHeader() {
        System.out.println("\n*---------------------------------------------*");
        System.out.println("|    Thank You for choosing to shop with      |" );
        System.out.println("|              Grocery Shop BSU               |" );
        System.out.println("*---------------------------------------------*\n");
    }

    public void displayMainMenu() {
        System.out.println( "Main Menu" );
        System.out.println( "---------" );
        System.out.println( "1. View items." );
        System.out.println( "2. View cart." );
        System.out.println( "3. Edit cart." );
        System.out.println( "4. Checkout." );
        System.out.println( "5. View order history." );
        System.out.println( "6. Switch Stores." );
        System.out.println( "7. Exit\n" );
    }

    public void displayPreviousOrders( ArrayList < Cart > previousOrders , LocalDate date ) {
        int orderCount = 0;
        for ( Cart userOrder : previousOrders ) {
            orderCount++;
            System.out.printf( "\nOrder %d" , orderCount );
            System.out.println( "\n--------" );
            displayCart( userOrder );
            System.out.println( "Date purchased: " + date + "\n" );
        }
    }

    public void displayCart( Cart cart ) {
        int counter = 1;
        if (cart.getCartItems().isEmpty()) {
            System.out.println("\nThe cart is empty.\n");
            return;
        }
        double sum = 0;
        for (Item item : cart.getCartItems()) {
            System.out.println(counter + ". " + item.getName() + " | " + item.getPrice());
            sum = cart.getPriceSum(sum, Double.parseDouble(item.getPrice()));
            counter++;
        }
        System.out.println("\nTotal: $" + Math.round(sum * 100.0) / 100.0 + "\n");
    }

    public int displayInventory( Inventory inventory ) {
        System.out.println( "-------------------------" );
        int counter = 1;
        for ( Item item : inventory.getItems() ) {
            System.out.println( counter + ". " + item.prettyPrintItem() );
            counter++;
        }
        return counter;
    }

    public int getItemIndexToAddCart(){
        System.out.println( "-------------------------" );
        Scanner scanner = new Scanner( System.in );
        System.out.println( "\nEnter the number of the item you'd like to order. Type 0 to go to main menu." );
        int itemIndex = Integer.parseInt( scanner.nextLine() );
        return itemIndex;
    }

    public int getQuantity(Item selectedItem ){
        Scanner scanner = new Scanner(System.in);
        System.out.println( "How much " + selectedItem.getName() + " would you like to add to your cart?" );
        int quantity = Integer.parseInt( scanner.nextLine() );
        return quantity;
    }
    public void successfulAddToCart(){
        System.out.println( "\nItem successfully added to cart. Returning to main menu...\n" );
    }
    public void itemNotFound(){
        System.out.println( "\nNo item exists under that number. Returning to main menu...\n" );
    }

    public int getItemToRemoveCart(){
        System.out.println( "\nSelect the number of the item you'd like to remove." );
        Scanner console = new Scanner( System.in );
        int selectedIndex = console.nextInt();
        return selectedIndex;
    }
    public String continueEditCart(){
        Scanner console = new Scanner(System.in);
        System.out.println( "Item successfully removed from cart. Would you like to continue editing? Y or N" );
        String continueResponse = console.next();
        return continueResponse;
    }
}
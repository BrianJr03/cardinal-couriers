package edu.bsu.cs222.finalProject;

import org.joda.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        System.out.println( "1. View items and add to cart." );
        System.out.println( "2. View cart." );
        System.out.println( "3. Edit cart." );
        System.out.println( "4. Checkout." );
        System.out.println( "5. View order history." );
        System.out.println( "6. Switch Stores." );
        System.out.println( "7. Exit\n" );
    }

    public static boolean isIntegerInput(String input) {
        Pattern pattern = Pattern.compile("[+-]?[0-9]+");
        Matcher matcher = pattern.matcher(input);
        if (!(matcher.find() && matcher.group().equals(input))) {
            return false;
        } else {
            return true;
        }
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
        for (Item item : cart.getCartItems()) {
            System.out.println(counter + ". " + item.getName() + " | " + item.getPrice());
            counter++;
        }
        System.out.println(String.format("\nTotal: $%.2f\n", cart.getTotalCartPrice()));
    }

    public void displayInventory( Inventory inventory ) {
        System.out.println( "-------------------------" );
        int counter = 1;
        for ( Item item : inventory.getItems() ) {
            System.out.println( counter + ". " + item.prettyPrintItem() );
            counter++;
        }
    }

    public int getItemIndexToAddCart(){
        System.out.println( "-----------------------------------------" );
        Scanner scanner = new Scanner( System.in );
        System.out.println( "\nEnter the number of the item you'd like to order. Type 0 to go to main menu." );
        String input = scanner.nextLine();
        if (isIntegerInput(input)) {
            return Integer.parseInt(input);
        } else {
            System.out.println("\nInput is not valid. Please try again.\n");
            return getItemIndexToAddCart();
        }
    }

    public int getQuantity(Item selectedItem ){
        Scanner scanner = new Scanner(System.in);
        System.out.println( "How much " + selectedItem.getName() + " would you like to add to your cart?" );
        String input = scanner.nextLine();
        if (isIntegerInput(input)) {
            return Integer.parseInt(input);
        } else {
            System.out.println("\nInput is not valid. Please try again.\n");
            return getQuantity(selectedItem);
        }
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
        String input = console.nextLine();
        if (isIntegerInput(input)) {
            return Integer.parseInt(input);
        } else {
            System.out.println("\nInput is not valid. Please try again.\n");
            return getItemToRemoveCart();
        }
    }

    public String continueEditCart(){
        Scanner console = new Scanner(System.in);
        System.out.println( "Item successfully removed from cart. Would you like to continue editing? Y or N" );
        return console.next();
    }
}
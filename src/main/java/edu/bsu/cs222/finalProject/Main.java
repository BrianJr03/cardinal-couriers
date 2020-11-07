package edu.bsu.cs222.finalProject;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import javax.mail.MessagingException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import static edu.bsu.cs222.finalProject.Inventory.collectItemsFromResources;
import static edu.bsu.cs222.finalProject.Inventory.createArrayListOfItems;

public class Main {
    public static LocalDate purchaseDate = DateTime.now().toLocalDate();
    public static ArrayList < Cart > previousOrders = new ArrayList <>();

    public static void main( String[] args ) throws IOException, MessagingException {
        displayHeader();
        Inventory storeInventory =
                new Inventory(createArrayListOfItems(collectItemsFromResources( storePicker() )));
        ArrayList < Item > cartItems = new ArrayList <>();
        Cart cart = new Cart( cartItems );
        String selection = selectMenuOption();
        while ( !selection.equals( "7" ) )
        {
            switch ( selection )
            {
                case "1" -> Cart.addItemsToCart( storeInventory , cart );
                case "2" -> Cart.displayCart( cart );
                case "3" -> Cart.editCart( cart );
                case "4" -> {
                    checkOut( cart );
                    cart = new Cart( new ArrayList <>() );
                }
                case "5" -> cart = reOrderPreviousOrder();
                case "6" -> storeInventory = new Inventory( createArrayListOfItems
                        (collectItemsFromResources( storePicker() )));
            }
            selection = selectMenuOption();
        }
    }

    public static String storePicker() {
        System.out.println("Which store would you like to shop from today?\n");
        System.out.println("1. Store A");
        System.out.println("2. Store B");
        System.out.println("3. Store C\n");
        Scanner input = new Scanner( System.in );
        String userChoice = input.nextLine();
        switch ( userChoice )
        {
            case "1" -> {
                System.out.println("\nStore A");
                return Inventory.getStore_A_Inventory(); }
            case "2" -> {
                System.out.println("\nStore B");
                return Inventory.getStore_B_Inventory(); }
            case "3" -> {
                System.out.println("\nStore C");
                return Inventory.getStore_C_Inventory(); }
            default -> {
                System.out.println( "\nPlease pick a valid Store." );
                System.out.println( "Redirecting to Store A...\n" );
                return Inventory.getStore_A_Inventory();
            }
        }
    }

    public static void displayHeader( ) {
        System.out.println( "\n*---------------------------------------------*" );
        System.out.println( "|    Thank You for choosing to shop with      |" );
        System.out.println( "|              Grocery Shop BSU               |" );
        System.out.println( "*---------------------------------------------*\n" );
    }

    public static void displayMainMenu( ) {
        System.out.println( "\nMain Menu" );
        System.out.println( "---------" );
        System.out.println( "1. View and select items to add to cart." );
        System.out.println( "2. View cart." );
        System.out.println( "3. Edit cart." );
        System.out.println( "4. Checkout." );
        System.out.println("5. View order history.");
        System.out.println("6. Switch Stores.");
        System.out.println( "7. Exit\n" );
    }

    public static String selectMenuOption( ) {
        Scanner console = new Scanner( System.in );
        displayMainMenu();
        return console.nextLine();
    }

    public static void checkOut( Cart cart ) throws MessagingException, IOException {
        previousOrders.add( cart );
        System.out.println( "\nThanks for purchasing your order!" );
        System.out.println( "Your shopping cart is now empty.\n" );
        System.out.println("Mark for contactless delivery? Y or N");
        Scanner input = new Scanner( System.in );
        String userChoice = input.nextLine();
        if(userChoice.equalsIgnoreCase( "y" )) {
            System.out.println("Marked for contactless delivery!");
        }
        SendReceipt.askUserForReceipt( cart );
    }

    public static void displayPreviousOrders( ArrayList < Cart > previousOrders , LocalDate date ) {
        int orderCount = 0;
        for ( Cart userOrder : previousOrders ) {
            orderCount++;
            System.out.printf( "\nOrder %d" , orderCount );
            System.out.println( "\n--------" );
            Cart.displayCart( userOrder );
            System.out.println( "Date purchased: " + date + "\n" );
        }
    }

    public static Cart reOrderPreviousOrder() {
        if (previousOrders == null)
        { System.out.println("There are no orders to display."); }

        Cart newCart = new Cart( new ArrayList <>() );
        Scanner in = new Scanner( System.in );

        System.out.println("\nOrder history");
        System.out.println("-------------");
        displayPreviousOrders( previousOrders, purchaseDate );

        System.out.println("Would you like to add a previous order to your current cart? Y or N");
        String userInput = in.nextLine();

        if (userInput.equalsIgnoreCase( "y" )) {
            System.out.println("Which order would you like to add to your cart?");
            int orderIndex = in.nextInt();

            for (Item item : previousOrders.get( orderIndex - 1 ).getCartItems())
            { newCart.add( item ); }
            System.out.printf("\nOrder %d has been added to your cart.\n", orderIndex);
        }
        return newCart;
    }
}
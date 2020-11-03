package edu.bsu.cs222.finalProject;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;

import javax.mail.MessagingException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import static edu.bsu.cs222.finalProject.Inventory.collectItemsFromResources;
import static edu.bsu.cs222.finalProject.Inventory.createArrayListOfItems;

public class Main {
    public static LocalDate purchaseDate = DateTime.now().toLocalDate();
    public static ArrayList < Cart > previousOrders = new ArrayList <>();
    public static SendReceipt email = new SendReceipt();

    public static void main( String[] args ) throws IOException, MessagingException {
        Inventory storeInventory = new Inventory( createArrayListOfItems( collectItemsFromResources() ) );
        ArrayList < Item > cartItems = new ArrayList <>();
        Cart cart = new Cart( cartItems );
        displayHeader();
        String selection = selectMenuOption();
        while ( !selection.equals( "6" ) )
        {
            switch ( selection )
            {
                case "1" -> addItemsToCart( storeInventory , cart );
                case "2" -> displayCart( cart );
                case "3" -> editCart( cart );
                case "4" -> {
                    checkOut( cart );
                    cart = new Cart( new ArrayList <>() );
                }
                case "5" -> cart = reOrderCart();
            }
            selection = selectMenuOption();
        }
    }

    public static void addItemsToCart( Inventory inventory , Cart cart ) {
        System.out.println( "-------------------------" );
        Scanner scanner = new Scanner( System.in );
        int counter = 1;
        for ( Item item : inventory.getItems() ) {
            System.out.println( counter + ". " + item.prettyPrintItem() );
            counter++;
        }
        System.out.println( "\nEnter the number of the item you'd like to order. Type 0 to go to main menu." );
        int itemIndex = Integer.parseInt( scanner.nextLine() );
        if ( itemIndex == 0 )
        {
            return;
        }
        if ( 1 <= itemIndex && itemIndex < counter ) {
            Item selectedItem = inventory.getItems().get( itemIndex - 1 );
            System.out.println( "How much " + selectedItem.getName() + " would you like to add to your cart?" );
            int quantity = Integer.parseInt( scanner.nextLine() );
            for ( int i = 0; i < quantity; i++ ) {
                cart.add( selectedItem );
            }
            System.out.println( "\nItem successfully added to cart. Returning to main menu...\n" );
        } else
        {
            System.out.println( "\nNo item exists under that number. Returning to main menu...\n" );
        }
    }

    public static void displayCart( Cart cart ) {
        int counter = 1;
        if ( cart.getCartItems().isEmpty() ) {
            System.out.println( "\nThe cart is empty.\n" );
            return;
        }
        double sum = 0;
        for ( Item item : cart.getCartItems() ) {
            System.out.println( counter + ". " + item.getName() + " | " + item.getPrice() );
            sum += Double.parseDouble( item.getPrice() );
            counter++;
        }
        System.out.println( "\nTotal: $" + Math.round( sum * 100.0 ) / 100.0 );
    }

    public static void editCart( Cart cart ) {
        displayCart( cart );
        System.out.println( "\nSelect the number of the item you'd like to remove." );
        Scanner console = new Scanner( System.in );
        int selectedIndex = console.nextInt();
        if ( 0 < selectedIndex && selectedIndex <= cart.getCartItems().size() )
        {
            cart.remove( cart.getCartItems().get( selectedIndex - 1 ) );
            System.out.println( "Item successfully removed from cart. Would you like to continue editing? Y or N" );
            String continueResponse = console.next();
            if ( continueResponse.equalsIgnoreCase( "y" ) )
            {
                editCart( cart );
            }
        } else
        {
            System.out.println( "There is no item with that index. Returning to main menu..." );
        }
    }

    public static void checkOut( Cart cart ) throws MessagingException, IOException {
        previousOrders.add( cart );
        Scanner input = new Scanner( System.in );
        System.out.println( "\nThanks for purchasing your order!" );
        System.out.println( "Your shopping cart is now empty." );
        System.out.print( "\nWould you like to be emailed a receipt?\n");
        String userInput = input.nextLine();
        if (userInput.equalsIgnoreCase( "yes" )) {
            System.out.println("\nPlease enter your email:");
            String userEmail = input.nextLine();
            writeReceipt( cart );
            email.sendReceipt( userEmail );
        }
    }

    public static void writeReceipt(Cart cart) throws IOException {
        int counter = 0;
        double sum = 0;
        File file = new File( "receiptGS_BSU.txt" );
        FileWriter writer = new FileWriter(file);
        writer.write( "Receipt" + "\n" + "------------" + "\n");
        for (Item item : cart.getCartItems()) {
            counter++;
            sum += Double.parseDouble( item.getPrice() );
            writer.write(counter + ". " + item.getName() + " | " + item.getPrice() + "\n" );
        }
        writer.write( "\nTotal: $" + Math.round(sum * 100.0 ) / 100.0);
        writer.close();
    }

    public static Cart reOrderCart() {
        if (previousOrders == null)
        { System.out.println("There are no orders to display."); }

        Cart newCart = new Cart( new ArrayList <>() );
        Scanner in = new Scanner( System.in );

        System.out.println("\nOrder history");
        System.out.println("-------------");
        displayPreviousOrders( previousOrders, purchaseDate );

        System.out.println("Would you like to add a previous order to your current cart?");
        String userInput = in.nextLine();

        if (userInput.equalsIgnoreCase( "yes" ))
        {
            System.out.println("Which order would you like to add to your cart?");
            int orderIndex = in.nextInt();

            for (Item item : previousOrders.get( orderIndex - 1 ).getCartItems())
            { newCart.add( item ); }
            System.out.printf("\nOrder %d has been added to your cart.\n", orderIndex);
        }
        return newCart;
    }

    public static void displayPreviousOrders( ArrayList < Cart > previousOrders , LocalDate date ) {
        int orderCount = 0;
        for ( Cart userOrder : previousOrders ) {
            orderCount++;
            System.out.printf( "\nOrder %d" , orderCount );
            System.out.println( "\n--------" );
            displayCart( userOrder );
            System.out.println( "Date purchased: " + date + "\n" );
        }
    }

    public static void displayHeader( ) {
        System.out.println( "\n*---------------------------------------------*" );
        System.out.println( "|    Thank You for choosing to shop with      |" );
        System.out.println( "|              Grocery Shop BSU               |" );
        System.out.println( "*---------------------------------------------*\n" );
        System.out.println( "Main Menu" );
        System.out.println( "---------" );
    }

    public static void displayMainMenu( ) {
        System.out.println( "\n1. View and select items to add to cart." );
        System.out.println( "2. View cart." );
        System.out.println( "3. Edit cart." );
        System.out.println( "4. Checkout." );
        System.out.println("5. View order history.");
        System.out.println( "6. Exit\n" );
    }

    public static String selectMenuOption( ) {
        Scanner console = new Scanner( System.in );
        displayMainMenu();
        return console.nextLine();
    }
}
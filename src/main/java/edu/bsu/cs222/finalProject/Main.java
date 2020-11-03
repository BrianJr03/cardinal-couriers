package edu.bsu.cs222.finalProject;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import javax.mail.MessagingException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static edu.bsu.cs222.finalProject.Cart.reOrderCart;
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
                case "1" -> Cart.addItemsToCart( storeInventory , cart );
                case "2" -> Cart.displayCart( cart );
                case "3" -> Cart.editCart( cart );
                case "4" -> {
                    Cart.checkOut( cart );
                    cart = new Cart( new ArrayList <>() );
                }
                case "5" -> cart = reOrderCart();
            }
            selection = selectMenuOption();
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
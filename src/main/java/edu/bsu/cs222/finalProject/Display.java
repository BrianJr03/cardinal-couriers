package edu.bsu.cs222.finalProject;

import org.joda.time.LocalDate;
import java.util.ArrayList;

public class Display {
    public void displayHeader() {
        System.out.println("\n*---------------------------------------------*");
        System.out.println("|    Thank You for choosing to shop with      |" );
        System.out.println("|              Grocery Shop BSU               |" );
        System.out.println("*---------------------------------------------*\n");
    }

    public void displayMainMenu() {
        System.out.println( "\nMain Menu" );
        System.out.println( "---------" );
        System.out.println( "1. View and select items to add to cart." );
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
        if ( cart.getCartItems().isEmpty() )
        { System.out.println( "\nThe cart is empty.\n" ); return; }
        double sum = 0;
        for ( Item item : cart.getCartItems() ) {
            System.out.println( counter + ". " + item.getName() + " | " + item.getPrice() );
            sum = cart.getPriceSum( sum, Double.parseDouble( item.getPrice() ));
            counter++;
        }
        System.out.println( "\nTotal: $" + Math.round( sum * 100.0 ) / 100.0 );
    }
}
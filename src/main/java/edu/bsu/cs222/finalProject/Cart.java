package edu.bsu.cs222.finalProject;

import org.joda.time.LocalDate;
import javax.mail.MessagingException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import static edu.bsu.cs222.finalProject.Main.*;

public class Cart {

    private final ArrayList<Item> cartItems;

    public Cart(ArrayList<Item> cartItems) {
        this.cartItems = cartItems;
    }

    public ArrayList<Item> getCartItems() {
        return cartItems;
    }

    public void add(Item itemToCart) {
        cartItems.add(itemToCart);
    }

    public void remove(Item itemToCart) {
        cartItems.remove(itemToCart);
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

    public static void editCart( Cart cart ) {
        Cart.displayCart( cart );
        System.out.println( "\nSelect the number of the item you'd like to remove." );
        Scanner console = new Scanner( System.in );
        int selectedIndex = console.nextInt();
        if ( 0 < selectedIndex && selectedIndex <= cart.getCartItems().size() ) {
            cart.remove( cart.getCartItems().get( selectedIndex - 1 ) );
            System.out.println( "Item successfully removed from cart. Would you like to continue editing? Y or N" );
            String continueResponse = console.next();
            if ( continueResponse.equalsIgnoreCase( "y" ) ) {
                editCart( cart );
            }
        } else
        {
            System.out.println( "There is no item with that index. Returning to main menu..." );
        }
    }

    public static Cart reOrderCart() {
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

    public static void checkOut( Cart cart ) throws MessagingException, IOException {
        previousOrders.add( cart );
        Scanner input = new Scanner( System.in );
        System.out.println( "\nThanks for purchasing your order!" );
        System.out.println( "Your shopping cart is now empty." );
        System.out.print( "\nWould you like to be sent a receipt? Y or N \n");
        String userInput = input.nextLine();
        if (userInput.equalsIgnoreCase( "y" )) {
            System.out.println("\n1. via Text\n2. via Email\n" +
                    "");
            String userChoice = input.nextLine();
            switch ( userChoice )
            {
                case "1" -> {
                    System.out.println( "\nPlease enter your phone number:" );
                    String phoneNumber = input.nextLine();
                    SendReceipt.writeReceipt( cart );
                    SendReceipt.sendReceiptAsTextMSG( phoneNumber );
                }

                case "2" -> {
                    System.out.println( "\nPlease enter your email:" );
                    String userEmail = input.nextLine();
                    SendReceipt.writeReceipt( cart );
                    SendReceipt.sendReceiptAsEmail( userEmail );
                }
            }
        }
    }
}
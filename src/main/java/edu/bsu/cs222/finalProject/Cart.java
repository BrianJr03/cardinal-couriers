package edu.bsu.cs222.finalProject;

import java.util.ArrayList;
import java.util.Scanner;


public class Cart {
    private final ArrayList<Item> cartItems;

    public Cart(ArrayList<Item> cartItems)
    { this.cartItems = cartItems; }

    public ArrayList<Item> getCartItems()
    { return cartItems; }

    public void add(Item itemToCart)
    { cartItems.add(itemToCart); }

    public void remove(Item itemToCart)
    { cartItems.remove(itemToCart); }

    public Double getPriceSum(double sum, double itemPrice)
    { return sum + itemPrice; }

    public void addItemsToCart( Inventory inventory , Cart cart ) {
        System.out.println( "-------------------------" );
        Scanner scanner = new Scanner( System.in );
        int counter = 1;
        for ( Item item : inventory.getItems() ) {
            System.out.println( counter + ". " + item.prettyPrintItem() );
            counter++;
        }
        System.out.println( "\nEnter the number of the item you'd like to order. Type 0 to go to main menu." );

        int itemIndex = Integer.parseInt( scanner.nextLine() );
        if ( itemIndex == 0 ){return;}
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

    public void editCart( Cart cart ) {
        Display display = new Display();
        display.displayCart( cart );
        System.out.println( "\nSelect the number of the item you'd like to remove." );
        Scanner console = new Scanner( System.in );
        int selectedIndex = console.nextInt();
        if (selectedIndex > 0 && selectedIndex <= cart.getCartItems().size()) {
            cart.remove( cart.getCartItems().get( selectedIndex - 1 ) );
            System.out.println( "Item successfully removed from cart. Would you like to continue editing? Y or N" );
            String continueResponse = console.next();
            if ( continueResponse.equalsIgnoreCase( "y" ) ) {
                editCart( cart );
            }
        } else {
            System.out.println( "There is no item with that index. Returning to main menu..." );
        }
    }
}
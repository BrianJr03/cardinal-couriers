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
        Display display = new Display();
        int counter = display.displayInventory(inventory);
        int itemIndex = display.getItemIndexToAddCart();
        if ( itemIndex == 0 ){return;}
        if ( 1 <= itemIndex && itemIndex < counter ) {
            Item selectedItem = inventory.getItems().get( itemIndex - 1 );
            int quantity = display.getQuantity(selectedItem);
            for ( int i = 0; i < quantity; i++ ) {
                cart.add( selectedItem );
            }
            display.successfulAddToCart();
        } else
        {
            display.itemNotFount();
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
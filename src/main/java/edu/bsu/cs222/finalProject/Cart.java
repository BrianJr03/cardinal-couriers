package edu.bsu.cs222.finalProject;

import java.util.ArrayList;

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

    public double getPriceSum(double sum, double itemPrice)
    { return sum + itemPrice; }

    public double getTotalCartPrice() {
        double total = 0;
        for (Item item : cartItems) {
            total += Double.parseDouble(item.getPrice());
        }
        return total;
    }

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
            display.itemNotFound();
        }
    }

    public void editCart( Cart cart ) {
        Display display = new Display();
        display.displayCart( cart );
        int selectedIndex = display.getItemToRemoveCart();
        if (selectedIndex > 0 && selectedIndex <= cart.getCartItems().size()) {
            cart.remove( cart.getCartItems().get( selectedIndex - 1 ) );
            String continueResponse = display.continueEditCart();
            if ( continueResponse.equalsIgnoreCase( "y" ) ) {
                editCart( cart );
            }
        } else {
            display.itemNotFound();
        }
    }
}
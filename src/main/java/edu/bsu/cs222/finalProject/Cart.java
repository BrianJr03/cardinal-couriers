package edu.bsu.cs222.finalProject;

import java.util.ArrayList;

public class Cart {
    private final ArrayList<Item> cartItems;

    public Cart(ArrayList<Item> cartItems)
    { this.cartItems = cartItems; }

    public ArrayList<Item> getItems()
    { return cartItems; }

    public void add(Item itemToCart)
    { cartItems.add(itemToCart); }

    public void remove(Item itemToCart)
    { cartItems.remove(itemToCart); }

    public double getTotalCartPrice() {
        double total = 0;
        for (Item item : cartItems) {
            total += Double.parseDouble(item.getPrice());
        }
        return total;
    }
}
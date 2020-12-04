package edu.bsu.cs222.finalProject;

import java.util.ArrayList;

public class Cart {
    private final ArrayList<Item> cartItems;
    private double totalCost = 0;

    public Cart(ArrayList<Item> cartItems)
    { this.cartItems = cartItems; }

    public ArrayList<Item> getItems()
    { return cartItems; }

    public void add(Item itemToCart) {
        cartItems.add(itemToCart);
        totalCost += itemToCart.getPrice();
        totalCost = Math.round(totalCost * 100.0) / 100.0;
    }

    public void remove(Item itemToCart) {
        cartItems.remove(itemToCart);
        totalCost -= itemToCart.getPrice();
        totalCost = Math.round(totalCost * 100.0) / 100.0;
    }

    public double getTotalCost() { return totalCost; }
}
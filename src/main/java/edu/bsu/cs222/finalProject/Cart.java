package edu.bsu.cs222.finalProject;

import javafx.collections.ObservableList;


public class Cart {
    private final ObservableList <Item> cartItems;
    private double totalCost = 0;

    public Cart(ObservableList<Item> cartItems)
    { this.cartItems = cartItems; }

    public ObservableList<Item> getItems()
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
package edu.bsu.cs222.finalProject;

import javafx.scene.control.Button;


public class Item {

    private final String name;
    private final double price;
    private int quantity;
    private final Button decButton;
    private final Button incButton;

    public Item(String name, Double price) {
        this.name = name;
        this.price = price;
        this.quantity = 0;
        this.decButton = new Button("-");
        this.incButton = new Button("+");
    }

    public Item(Item item, Integer quantity) {
        this.name = item.getName();
        this.price = item.getPrice();
        this.quantity = quantity;
        this.decButton = new Button("-");
        this.incButton = new Button("+");
    }

    public String getName() { return name; }

    public Double getPrice() { return price; }

    public int getQuantity() { return quantity; }

    public Button getDecButton() {
        return decButton;
    }

    public Button getIncButton() {
        return incButton;
    }

    public void increaseQuantity() {
        this.quantity += 1;
    }

    public void decreaseQuantity() {
        this.quantity -= 1;
        if (quantity < 0) {
            quantity = 0; }
    }

    public void setQuantity(int i)
    { this.quantity = i; }
}




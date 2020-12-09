package edu.bsu.cs222.finalProject;


import javafx.beans.property.ObjectProperty;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

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
        decButton.setOnMouseClicked(event -> {
            decreaseQuantity();
            System.out.println(quantity);
        });
        incButton.setOnMouseClicked(event -> {
            increaseQuantity();
            System.out.println(quantity);
        });
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
    }
}




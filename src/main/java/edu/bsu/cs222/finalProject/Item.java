package edu.bsu.cs222.finalProject;

public class Item {

    private final String name;
    private final double price;
    private final int quantity = 0;

    public Item(String name, Double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() { return name; }

    public Double getPrice() { return price; }

    public int getQuantity() { return quantity; }
}



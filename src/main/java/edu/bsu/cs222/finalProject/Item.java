package edu.bsu.cs222.finalProject;

public class Item {

    private final String name;
    private final String price;

    public Item(String name, String price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }
}



package edu.bsu.cs222.finalProject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static edu.bsu.cs222.finalProject.Inventory.collectItemsFromResources;
import static edu.bsu.cs222.finalProject.Inventory.createArrayListOfItems;

public class Main {
    public static void main(String[] args) throws IOException {
        displayHeader();
        displayItems();
        addItemsToCart();
    }

    public static void displayHeader() {
        System.out.println("\n*-----------------------------------------------*");
        System.out.println("|      Thank You for choosing to Shop with      |");
        System.out.println("|                Grocery Shop BSU               |");
        System.out.println("*-----------------------------------------------*\n");
        System.out.println("Our Available Items");
        System.out.println("-------------------");
    }

    public static void displayItems() throws IOException {
        Inventory inventory = new Inventory(createArrayListOfItems(collectItemsFromResources()));
        ArrayList<Item> items = inventory.getItems();

        int itemCounter = 0;
        for (Item item : items) {
            itemCounter++;
            String name = item.getName();
            String price = item.getPrice();
            System.out.printf("%d. %s | $%s\n", itemCounter, name, price);
        }
    }

    public static void addItemsToCart() throws IOException {
        Cart cart = new Cart(new ArrayList<>());
        Inventory inventory = new Inventory(createArrayListOfItems(collectItemsFromResources()));

        while (true) {
            Scanner input = new Scanner(System.in);

            System.out.println("\nWhat would you like to order today? ( '0' to exit )");

            int itemToOrder = input.nextInt();
            if (itemToOrder == 0) {
                break;
            }

            System.out.println("\nPlease enter the quantity of this item.");
            int quantity = input.nextInt();

            for (int i = 0; i < quantity; i++) {

                Item itemToCart = inventory.getItems().get(itemToOrder - 1);
                cart.add(itemToCart);
            }

            System.out.println("-------------------");
            System.out.println("Enter 'back' to add another item to your cart");
            System.out.println("Enter 'view cart' to see your current cart");
            System.out.println("-------------------");

            while (true) {
                String selection = input.nextLine();

                if (selection.equalsIgnoreCase("back")) {
                    break;
                } else if (selection.equalsIgnoreCase("view cart")) {
                    System.out.println("\nYour cart");
                    System.out.println("\nEnter 'edit' to edit your cart. Enter 'back' to go back.");
                    System.out.println("---------");

                    int itemCounter = 0;
                    for (Item item : cart.getCartItems()) {
                        itemCounter++;
                        String name = item.getName();
                        String price = item.getPrice();
                        System.out.printf("%d. %s | $%s\n", itemCounter, name, price);
                    }

                        System.out.println("---------");
                        String edit = input.nextLine();
                        if (edit.equalsIgnoreCase("edit")){
                            System.out.println("Enter the number of the item you would like to remove.");
                        }
                    }
                }
            }
        }
    }

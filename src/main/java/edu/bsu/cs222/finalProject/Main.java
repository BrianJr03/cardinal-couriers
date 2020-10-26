package edu.bsu.cs222.finalProject;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static edu.bsu.cs222.finalProject.Inventory.collectItemsFromResources;
import static edu.bsu.cs222.finalProject.Inventory.createArrayListOfItems;

public class Main {
    public static void main(String[] args) throws IOException {
        Inventory storeInventory = new Inventory(createArrayListOfItems(collectItemsFromResources()));
        ArrayList<Item> cartItems = new ArrayList<>();
        Cart cart = new Cart(cartItems);
        displayHeader();
        String selection = selectMenuOption();
        while (!selection.equals("5")) {
            if (selection.equals("1")) {
                addItemsToCart(storeInventory, cart);
            } else if (selection.equals("2")) {
                displayCart(cart);
            } else if (selection.equals("3")) {
                editCart(cart);
            } else if (selection.equals("4")) {
                //code to check out (not yet implemented)
            }
            selection = selectMenuOption();
        }
    }

    public static void addItemsToCart(Inventory inventory, Cart cart) {
        System.out.println("-------------------------");
        Scanner scanner = new Scanner(System.in);
        int counter = 1;
        for (Item item : inventory.getItems()) {
            System.out.println(counter + ". " + item.prettyPrintItem());
            counter++;
        }
        System.out.println("Enter the number of the item you'd like to order. Type 0 to go to main menu.");
        int itemIndex = Integer.parseInt(scanner.nextLine());
        if (itemIndex == 0) {
            return;
        }
        if (1 <= itemIndex && itemIndex < counter ) {
            Item selectedItem = inventory.getItems().get(itemIndex-1);
            System.out.println("How much " + selectedItem.getName() + " would you like to add to your cart?");
            int quantity = Integer.parseInt(scanner.nextLine());
            for (int i = 0; i < quantity; i++) {
                cart.add(selectedItem);
            }
            System.out.println("Item successfully added to cart. Returning to main menu...");
        } else {
            System.out.println("No item exists under that number. Returning to main menu...");
        }
    }

    public static void displayCart(Cart cart) {
        int counter = 1;
        if (cart.getCartItems().isEmpty()) {
            System.out.println("The cart is empty.\n");
            return;
        }
        for (Item item : cart.getCartItems()) {
            System.out.println(counter + ". " + item.getName() + " | " + item.getPrice());
            counter++;
        }
        System.out.println();
    }

    public static void editCart(Cart cart) {
        displayCart(cart);
        System.out.println("Select the number of the item you'd like to remove.");
        Scanner console = new Scanner(System.in);
        int selectedIndex = console.nextInt();
        if (cart.getCartItems().get(selectedIndex-1)!=null) {
            cart.remove(cart.getCartItems().get(selectedIndex - 1));
            System.out.println("Item successfully removed from cart. Would you like to continue editing? Y or N");
            String continueResponse = console.next();
            if (continueResponse.equalsIgnoreCase("y")) {
                editCart(cart);
            } else {
                return;
            }
        } else {
            System.out.println("There is no item with that index. Returning to main menu...");
        }
    }

    public static void displayHeader() {
        System.out.println("\n*-----------------------------------------------*");
        System.out.println("|      Thank You for choosing to Shop with      |");
        System.out.println("|                Grocery Shop BSU               |");
        System.out.println("*-----------------------------------------------*\n");
        System.out.println("Main Menu");
        System.out.println("-------------------");
    }

    public static void displayItems(Inventory inventory) throws IOException {
        ArrayList<Item> items = inventory.getItems();
        int itemCounter = 0;
        for (Item item : items) {
            itemCounter++;
            String name = item.getName();
            String price = item.getPrice();
            System.out.printf("%d. %s | $%s\n", itemCounter, name, price);
        }
    }

    public static void displayMainMenu() {
        System.out.println("1. View and select items to add to cart.");
        System.out.println("2. View cart.");
        System.out.println("3. Edit cart.");
        System.out.println("4. Checkout.");
        System.out.println("5. Exit");
    }

    public static String selectMenuOption() {
        Scanner console = new Scanner(System.in);
        displayMainMenu();
        return console.nextLine();
    }


    public static void addItemsToCart() throws IOException {
        Cart cart = new Cart(new ArrayList<>());
        Inventory inventory = new Inventory(createArrayListOfItems(collectItemsFromResources()));

        while (true) {
            Scanner input = new Scanner(System.in);

            displayItems(inventory);
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
                            System.out.println("\nEnter 'done' to finish editing.");
                            String itemEdit = input.nextLine();
                            while (!itemEdit.equalsIgnoreCase("done")){
                                int itemNumber = Integer.parseInt(itemEdit);
                                cart.getCartItems().remove(itemNumber-1);
                            }
                        }
                        else if (edit.equalsIgnoreCase("done")){
                            break;
                        }
                    }
                }
            }
        }
    }

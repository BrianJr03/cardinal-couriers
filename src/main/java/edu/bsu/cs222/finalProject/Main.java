package edu.bsu.cs222.finalProject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static edu.bsu.cs222.finalProject.Inventory.collectItemsFromResources;
import static edu.bsu.cs222.finalProject.Inventory.createArrayListOfItems;

public class Main {
    public static void main( String[] args) throws IOException {
        Inventory storeInventory = new Inventory(createArrayListOfItems(collectItemsFromResources()));
        ArrayList<Item> cartItems = new ArrayList<>();
        Cart cart = new Cart(cartItems);
        displayHeader();
        String selection = selectMenuOption();
        while (!selection.equals("5")) {
            switch (selection) {
                case "1" -> addItemsToCart(storeInventory, cart);
                case "2" -> displayCart(cart);
                case "3" -> editCart(cart);
                case "4" -> System.out.println("Will be implemented later...");
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
        System.out.println("\nEnter the number of the item you'd like to order. Type 0 to go to main menu.");
        int itemIndex = Integer.parseInt(scanner.nextLine());
        if (itemIndex == 0) {
            return;
        }
        if (1 <= itemIndex && itemIndex < counter) {
            Item selectedItem = inventory.getItems().get(itemIndex - 1);
            System.out.println("How much " + selectedItem.getName() + " would you like to add to your cart?");
            int quantity = Integer.parseInt(scanner.nextLine());
            for (int i = 0; i < quantity; i++) {
                cart.add(selectedItem);
            }
            System.out.println("\nItem successfully added to cart. Returning to main menu...\n");
        } else {
            System.out.println("\nNo item exists under that number. Returning to main menu...\n");
        }
    }


    public static void displayCart(Cart cart) {
        int counter = 1;
        if (cart.getCartItems().isEmpty()) {
            System.out.println("\nThe cart is empty.\n");
            return;
        }
        System.out.println("\nYour Cart");
        System.out.println("----");
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
        if (0 < selectedIndex && selectedIndex <= cart.getCartItems().size()) {
            cart.remove(cart.getCartItems().get(selectedIndex - 1));
            System.out.println("Item successfully removed from cart. Would you like to continue editing? Y or N");
            String continueResponse = console.next();
            if (continueResponse.equalsIgnoreCase("y")) {
                editCart(cart);
            } 
        } else {
            System.out.println("There is no item with that index. Returning to main menu...");
        }
    }


    public static void displayHeader() {
        System.out.println("\n*---------------------------------------------*");
        System.out.println("|    Thank You for choosing to shop with      |");
        System.out.println("|              Grocery Shop BSU               |");
        System.out.println("*---------------------------------------------*\n");
        System.out.println("Main Menu");
        System.out.println("---------");
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
}

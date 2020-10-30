import edu.bsu.cs222.finalProject.Cart;
import edu.bsu.cs222.finalProject.Inventory;
import edu.bsu.cs222.finalProject.Item;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static edu.bsu.cs222.finalProject.Inventory.collectItemsFromResources;
import static edu.bsu.cs222.finalProject.Inventory.createArrayListOfItems;

public class CartTest {

    @Test
    public void testCanAddItemToCart() throws IOException {
        Cart cart = new Cart(new ArrayList<>());
        Inventory inventory = new Inventory(createArrayListOfItems(collectItemsFromResources()));
        Item itemToCart = inventory.getItems().get(0);
        cart.add(itemToCart);
        Assertions.assertEquals("\"Apple\"", cart.getCartItems().get(0).getName());
        Assertions.assertEquals("1.99", cart.getCartItems().get(0).getPrice());
    }

    @Test
    public void testCanRemoveItemFromCart() throws IOException {
        Cart cart = new Cart(new ArrayList<>());
        Inventory inventory = new Inventory(createArrayListOfItems(collectItemsFromResources()));
        Item itemToCart = inventory.getItems().get(0);
        cart.add(itemToCart);
        cart.remove(itemToCart);
        Assertions.assertFalse(cart.getCartItems().contains(itemToCart));
    }

    @Test
    public void printPreviousOrders() throws IOException {
        Cart cart = new Cart(new ArrayList<>());
        Inventory inventory = new Inventory(createArrayListOfItems(collectItemsFromResources()));
        Item itemToCart = inventory.getItems().get(0);
        Item otherItemToCart = inventory.getItems().get(1);
        cart.add(itemToCart);
        cart.add( otherItemToCart );

        Cart cart2 = new Cart(new ArrayList<>());
        Inventory inventory2 = new Inventory(createArrayListOfItems(collectItemsFromResources()));
        Item itemToCart2 = inventory2.getItems().get(2);
        Item otherItemToCart2 = inventory.getItems().get(3);
        cart2.add(itemToCart2);
        cart2.add( otherItemToCart2 );

        ArrayList < Cart > previousOrders = new ArrayList<>();
        previousOrders.add( cart );
        previousOrders.add( cart2 );

        int orderCount = 0;
        for (Cart userOrder : previousOrders) {
            orderCount++;
            System.out.printf("\nOrder %d", orderCount);
            System.out.println("\n--------");
            displayCart( userOrder );
        }
    }

    @Test
    public static void displayCart(Cart cart) {
        int counter = 1;
        if (cart.getCartItems().isEmpty()) {
            System.out.println("\nThe cart is empty.\n");
            return;
        }

        for (Item item : cart.getCartItems()) {
            System.out.println(counter + ". " + item.getName() + " | " + item.getPrice());
            counter++;
        }
        System.out.println();
    }
}
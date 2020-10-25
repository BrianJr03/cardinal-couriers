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

}
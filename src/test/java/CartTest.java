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
    final Inventory inventory = new Inventory(createArrayListOfItems(collectItemsFromResources
            (Inventory.getInventory("walmart"))));

    public CartTest() throws IOException {}

    @Test
    public void testCanAddItemToCart() {
        Cart cart = new Cart(new ArrayList<>());
        Item itemToCart = new Item("Apple", "1.89");
        cart.add(itemToCart);
        Assertions.assertEquals( 1, cart.getCartItems().size());
    }

    @Test
    public void testCanAddItemWithLastIndexToCart(){
        Cart cart = new Cart(new ArrayList<>());
        Item itemToCart = inventory.getItems().get(10);
        cart.add(itemToCart);
        Assertions.assertEquals(1, cart.getCartItems().size());
    }

    @Test
    public void testAdd2orMoreItemsToCart(){
        Cart cart = new Cart(new ArrayList<>());
        Item item1 = new Item("Apple", "1.89");
        Item item2 = new Item("Ramen", "0.50");
        Item item3 = new Item("Water", "1.00");
        cart.add(item1);
        cart.add(item2);
        cart.add(item3);
        Assertions.assertEquals(3, cart.getCartItems().size());
    }

    @Test
    public void testFindPriceSum() {
        Cart cart = new Cart(new ArrayList<>());
        Item itemToCart = new Item("Apple", "1.90");
        Item itemToCart2 = new Item("Banana", "0.50");
        cart.add(itemToCart);
        cart.add(itemToCart2);
        Assertions.assertEquals(2.40, cart.getTotalCartPrice());
    }

    @Test
    public void testCanResetCart() {
        Cart cart = new Cart(new ArrayList<>());
        Item itemToCart = inventory.getItems().get(0);
        Item itemToCart1 = inventory.getItems().get(0);
        Item itemToCart2 = inventory.getItems().get(0);
        cart.add(itemToCart);
        cart.add(itemToCart1);
        cart.add(itemToCart2);
        cart = new Cart( new ArrayList <>() );
        Assertions.assertEquals(0, cart.getCartItems().size());
    }

    @Test
    public void testCanRemoveItemFromCart() {
        Cart cart = new Cart(new ArrayList<>());
        Item itemToCart = new Item("Apple", "1.89");
        cart.add(itemToCart);
        cart.remove(itemToCart);
        Assertions.assertFalse(cart.getCartItems().contains(itemToCart));
    }

}
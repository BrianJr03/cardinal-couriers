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
        Assertions.assertFalse(cart.getCartItems().contains(itemToCart) ||
                cart.getCartItems().contains(itemToCart1) || cart.getCartItems().contains(itemToCart2) );
    }

    @Test
    public void testCanRemoveItemFromCart() {
        Cart cart = new Cart(new ArrayList<>());
        Item itemToCart = inventory.getItems().get(0);
        cart.add(itemToCart);
        cart.remove(itemToCart);
        Assertions.assertFalse(cart.getCartItems().contains(itemToCart));
    }

    @Test
    public void printPreviousOrders() {
        ArrayList < Cart > previousOrders = new ArrayList<>();

        int orderCount = 0;
        for (Cart userOrder : previousOrders) {
            orderCount++;
            System.out.printf("\nOrder %d", orderCount);
            System.out.println("\n--------");

        }
    }

    @Test
    public void reOrderCart(){
        ArrayList < Cart > previousOrders = new ArrayList<>();
        Cart newCart = new Cart( new ArrayList <>() );



        System.out.println("\nOrder 1");
        System.out.println("--------");


        System.out.println("Order 2");
        System.out.println("--------");


        //Selects first order and adds it to cart
        for (Item item : previousOrders.get( 0 ).getCartItems())
        { newCart.add( item ); }

        System.out.println("New cart");
        System.out.println("--------");

    }
}
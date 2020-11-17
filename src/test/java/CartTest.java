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
    Inventory inventory = new Inventory(createArrayListOfItems(collectItemsFromResources
            (Inventory.getWalmartInventory())));

    public CartTest() throws IOException {}

    @Test
    public void testCanAddItemToCart() {
        ArrayList<String> expectedOrderInfo = new ArrayList <>();
        Cart cart = new Cart(new ArrayList<>());
        Item itemToCart = inventory.getItems().get(0);
        cart.add(itemToCart);

        ArrayList<String> actualOrderInfo = new ArrayList <>();
        actualOrderInfo.add( '"' + "Apple" + '"');
        actualOrderInfo.add( String.valueOf( 1.69 ) );

        for(Item item : cart.getCartItems()) {
            expectedOrderInfo.add( item.getName() );
            expectedOrderInfo.add( item.getPrice() );
        }
        Assertions.assertEquals( expectedOrderInfo, actualOrderInfo );
    }

    @Test
    public void testFindPriceSum() {
        Cart cart = new Cart(new ArrayList<>());
        Item itemToCart = inventory.getItems().get(0);
        Item itemToCart2 = inventory.getItems().get(1);
        cart.add(itemToCart);
        cart.add( itemToCart2 );
        double sum = 0.0;
        for (Item item : cart.getCartItems()) {
            sum += Double.parseDouble( item.getPrice() );
        }
        Assertions.assertEquals( sum , 3.38 );
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
        previousOrders.add( getOrder1() );
        previousOrders.add( getOrder2() );
        int orderCount = 0;
        for (Cart userOrder : previousOrders) {
            orderCount++;
            System.out.printf("\nOrder %d", orderCount);
            System.out.println("\n--------");
            displayCart( userOrder );
        }
    }

    public void displayCart(Cart cart) {
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

    @Test
    public void reOrderCart(){
        ArrayList < Cart > previousOrders = new ArrayList<>();
        Cart newCart = new Cart( new ArrayList <>() );

        previousOrders.add( getOrder1() );
        previousOrders.add( getOrder2() );

        System.out.println("\nOrder 1");
        System.out.println("--------");
        displayCart( previousOrders.get( 0 ) );

        System.out.println("Order 2");
        System.out.println("--------");
        displayCart( previousOrders.get( 1 ) );

        //Selects first order and adds it to cart
        for (Item item : previousOrders.get( 0 ).getCartItems())
        { newCart.add( item ); }

        System.out.println("New cart");
        System.out.println("--------");
        displayCart( newCart );
    }

    public Cart getOrder1() {
        Cart order1 = new Cart(new ArrayList<>());
        Item itemToCart = inventory.getItems().get(6);
        Item otherItemToCart = inventory.getItems().get(4);
        order1.add(itemToCart); order1.add( otherItemToCart );
        return order1;
    }

    public Cart getOrder2() {
        Cart order2 = new Cart(new ArrayList<>());
        Item itemToCart = inventory.getItems().get(7);
        Item otherItemToCart = inventory.getItems().get(9);
        order2.add(itemToCart); order2.add( otherItemToCart );
        return order2;
    }
}
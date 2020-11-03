import edu.bsu.cs222.finalProject.Cart;
import edu.bsu.cs222.finalProject.Inventory;
import edu.bsu.cs222.finalProject.Item;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import static edu.bsu.cs222.finalProject.Inventory.collectItemsFromResources;
import static edu.bsu.cs222.finalProject.Inventory.createArrayListOfItems;

public class CartTest {

    Inventory inventory = new Inventory(createArrayListOfItems(collectItemsFromResources()));

    public CartTest( ) throws IOException
    {}

    @Test
    public void testCanAddItemToCart() {
        Cart cart = new Cart(new ArrayList<>());
        Item itemToCart = inventory.getItems().get(0);
        cart.add(itemToCart);
        Assertions.assertEquals("\"Apple\"", cart.getCartItems().get(0).getName());
        Assertions.assertEquals("1.99", cart.getCartItems().get(0).getPrice());
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
        //Order 1
        Cart cart = new Cart(new ArrayList<>());
        Item itemToCart = inventory.getItems().get(0);
        Item otherItemToCart = inventory.getItems().get(1);
        cart.add(itemToCart);
        cart.add( otherItemToCart );

        //Order 2
        Cart cart2 = new Cart(new ArrayList<>());
        Item itemToCart2 = inventory.getItems().get(2);
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

    @Test
    public void reOrderCart(){
        //Order 1
        Cart cart = new Cart(new ArrayList<>());
        Item itemToCart = inventory.getItems().get(6);
        Item otherItemToCart = inventory.getItems().get(4);
        cart.add(itemToCart);
        cart.add( otherItemToCart );

        //Order 2
        Cart cart2 = new Cart(new ArrayList<>());
        Item itemToCart2 = inventory.getItems().get(7);
        Item otherItemToCart2 = inventory.getItems().get(9);
        cart2.add(itemToCart2);
        cart2.add( otherItemToCart2 );

        ArrayList < Cart > previousOrders = new ArrayList<>();
        Cart newCart = new Cart( new ArrayList <>() );

        previousOrders.add( cart );
        previousOrders.add( cart2 );

        System.out.println("Order 1");
        System.out.println("--------");
        displayCart( previousOrders.get( 0 ) );

        System.out.println("Order 2");
        System.out.println("--------");
        displayCart( previousOrders.get( 1 ) );

        //selects first order and adds it to cart
        for (Item item : previousOrders.get( 0 ).getCartItems())
        { newCart.add( item ); }

        System.out.println("New cart");
        System.out.println("--------");
        displayCart( newCart );
    }

    @Test
    public void writeReceipt() throws IOException {
        Cart cart = new Cart(new ArrayList<>());
        Item itemToCart = inventory.getItems().get(6);
        Item otherItemToCart = inventory.getItems().get(4);
        cart.add(itemToCart);
        cart.add( otherItemToCart );

        int counter = 0;
        File file = new File( "receiptGS_BSU.txt" );
        FileWriter writer = new FileWriter(file);

        for (Item item : cart.getCartItems()) {
            counter++;
            writer.write(counter + ". " + item.getName() + " | " + item.getPrice() + "\n" );
        }
        writer.close();
    }
}
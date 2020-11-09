import edu.bsu.cs222.finalProject.Cart;
import edu.bsu.cs222.finalProject.Inventory;
import edu.bsu.cs222.finalProject.Item;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import static edu.bsu.cs222.finalProject.Inventory.collectItemsFromResources;
import static edu.bsu.cs222.finalProject.Inventory.createArrayListOfItems;

public class SendReceiptTest {
    Inventory inventory = new Inventory(createArrayListOfItems(collectItemsFromResources
            (Inventory.getStore_A_Inventory())));

    public SendReceiptTest( ) throws IOException {}

    @Test //Will override receiptGS_BSU.txt
    public void writeReceipt() throws IOException {
        Cart cart = new Cart(new ArrayList <>());
        Item handSanitizer = inventory.getItems().get(6);
        Item macAndCheese = inventory.getItems().get(4);
        cart.add(handSanitizer);
        cart.add( macAndCheese );

        File file = new File( "receiptGS_BSU.txt" );
        FileWriter writer = new FileWriter(file);

        int counter = 0;
        writer.write( "TEST RECEIPT\n" );
        for (Item item : cart.getCartItems()) {
            counter++;
            writer.write(counter + ". " + item.getName() + " | " + item.getPrice() + "\n" );
        }
        writer.close();
    }
}

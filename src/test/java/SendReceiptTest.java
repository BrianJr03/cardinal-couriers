import edu.bsu.cs222.finalProject.Cart;
import edu.bsu.cs222.finalProject.Inventory;
import edu.bsu.cs222.finalProject.Item;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import static edu.bsu.cs222.finalProject.Inventory.collectItemsFromResources;
import static edu.bsu.cs222.finalProject.Inventory.createArrayListOfItems;

public class SendReceiptTest {
    Inventory inventory = new Inventory(createArrayListOfItems(collectItemsFromResources()));

    public SendReceiptTest( ) throws IOException {}

    @Test
    public void writeReceipt() throws IOException {
        Cart cart = new Cart(new ArrayList <>());
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

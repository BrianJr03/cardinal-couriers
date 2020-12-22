import edu.bsu.cs222.finalProject.Inventory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static edu.bsu.cs222.finalProject.Inventory.collectItemsAsArrayList;

public class InventoryTest {

    @Test
    public void testCanReturnFirstItemFromKroger() throws IOException {
        Inventory inventory = new Inventory(collectItemsAsArrayList("Kroger"));
        Assertions.assertEquals("Apple", inventory.getItems().get(0).getName());
    }

    @Test
    public void testCanReturnFirstItemFromALDI() throws IOException {
        Inventory inventory = new Inventory(collectItemsAsArrayList("ALDI"));
        Assertions.assertEquals("Chicken Noodle Soup", inventory.getItems().get(0).getName());
    }

    @Test
    public void testCanReturnFirstItemFromWalmart() throws IOException {
        Inventory inventory = new Inventory(collectItemsAsArrayList("Walmart"));
        Assertions.assertEquals("Apple", inventory.getItems().get(0).getName());
    }

    @Test
    public void canReturnFinalItemFromWalmart() throws IOException {
        Inventory inventory = new Inventory(collectItemsAsArrayList("Kroger"));
        int inventorySize = inventory.getItems().size();
        Assertions.assertEquals("Taco Sauce Supreme", inventory.getItems().get(inventorySize-1).getName());
    }

}
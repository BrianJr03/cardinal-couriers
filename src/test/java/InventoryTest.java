import edu.bsu.cs222.finalProject.Inventory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static edu.bsu.cs222.finalProject.Inventory.collectItemsAsArrayList;

public class InventoryTest {

    @Test
    public void testCanReturnFirstItemFromStoreA() throws IOException {
        Inventory inventory = new Inventory(collectItemsAsArrayList("kroger"));
        Assertions.assertEquals("\"Bread\"", inventory.getItems().get(0).getName());
    }

    @Test
    public void testCanReturnFirstItemFromStoreB() throws IOException {
        Inventory inventory = new Inventory(collectItemsAsArrayList("target"));
        Assertions.assertEquals("\"Chicken Noodle Soup\"", inventory.getItems().get(0).getName());
    }

    @Test
    public void testCanReturnFirstItemFromStoreC() throws IOException {
        Inventory inventory = new Inventory(collectItemsAsArrayList("walmart"));
        Assertions.assertEquals("\"Apple\"", inventory.getItems().get(0).getName());
    }

    @Test
    public void canReturnFinalItemFromStoreA() throws IOException {
        Inventory inventory = new Inventory(collectItemsAsArrayList("kroger"));
        int inventorySize = inventory.getItems().size();
        Assertions.assertEquals("\"Yogurt\"", inventory.getItems().get(inventorySize-1).getName());
    }

}
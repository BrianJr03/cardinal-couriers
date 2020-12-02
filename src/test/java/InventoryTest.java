import edu.bsu.cs222.finalProject.Inventory;
import edu.bsu.cs222.finalProject.Item;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static edu.bsu.cs222.finalProject.Inventory.collectItemsAsArrayList;

public class InventoryTest {


    @Test
    public void testCanReturnFirstItemFromKroger() throws IOException {
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
    public void testSearchForItem() throws IOException {
        Inventory inventory =
                new Inventory(collectItemsAsArrayList("walmart"));
        ArrayList<String> actualOrder = new ArrayList <>();
        ArrayList<String> expectedOrder = new ArrayList <>();

        String nameOfItemToQuery = '"' + "Orange" + '"' ;
        String priceOfItemToQuery = "1.69";
        expectedOrder.add(nameOfItemToQuery);
        expectedOrder.add(priceOfItemToQuery);

        for (int i = 0; i < inventory.getItems().size(); i++) {
            if (nameOfItemToQuery.equalsIgnoreCase(inventory.getItems().get( i ).getName())) {
                System.out.printf("\nQueried item: %s\n", nameOfItemToQuery);
                String name = inventory.getItems().get( i ).getName();
                String price = inventory.getItems().get( i ).getPrice();
                actualOrder.add( name ); actualOrder.add( price );
                Item queriedItem = new Item(name, price);
                System.out.printf("Found: %s\n", queriedItem.prettyPrintItem());
            }
        }
        Assertions.assertEquals(expectedOrder, actualOrder);
    }
}
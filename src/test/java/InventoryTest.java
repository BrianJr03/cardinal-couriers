import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import edu.bsu.cs222.finalProject.Inventory;
import edu.bsu.cs222.finalProject.Item;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import static edu.bsu.cs222.finalProject.Inventory.collectItemsFromResources;
import static edu.bsu.cs222.finalProject.Inventory.createArrayListOfItems;

public class InventoryTest {
    @Test
    public void getItemsFromStoreA() throws IOException {
        JsonParser parser = new JsonParser();
        FileReader reader = new FileReader( "src/main/resources/storeItems/storeA_items.json" );

        ArrayList<String[]> itemsList = new ArrayList<>();

        JsonArray inventory = parser.parse(reader).getAsJsonArray();
        reader.close();

        for (JsonElement item : inventory) {
            String[] singleItem = new String[2];
            singleItem[0] = item.getAsJsonObject().get("name").toString();
            singleItem[1] = item.getAsJsonObject().get("price").getAsString();
            itemsList.add(singleItem);
        }
        System.out.println(inventory);
        System.out.println(Arrays.toString(itemsList.get(0)));
    }

    @Test
    public void testCanReturnFirstItemFromStoreA() throws IOException {
        Inventory inventory =
                new Inventory(createArrayListOfItems( collectItemsFromResources(Inventory.getStore_A_Inventory()) ));
        Assertions.assertEquals("\"Apple\"", inventory.getItems().get(0).getName());
        Assertions.assertEquals("1.69", inventory.getItems().get(0).getPrice());
    }

    @Test
    public void testCanReturnFirstItemFromStoreB() throws IOException {
        Inventory inventory =
                new Inventory(createArrayListOfItems( collectItemsFromResources(Inventory.getStore_B_Inventory()) ));
        Assertions.assertEquals("\"Bread\"", inventory.getItems().get(0).getName());
        Assertions.assertEquals("0.99", inventory.getItems().get(0).getPrice());
    }

    @Test
    public void testCanReturnFirstItemFromStoreC() throws IOException {
        Inventory inventory =
                new Inventory(createArrayListOfItems( collectItemsFromResources(Inventory.getStore_C_Inventory()) ));
        Assertions.assertEquals("\"Chicken Noodle Soup\"", inventory.getItems().get(0).getName());
        Assertions.assertEquals("3.50", inventory.getItems().get(0).getPrice());
    }

    @Test
    public void searchForItemInInventory() throws IOException //Needs work
    {
        Inventory inventory =
                new Inventory(createArrayListOfItems( collectItemsFromResources(Inventory.getStore_B_Inventory()) ));
        String itemToSearchFor = "Orange";

        for ( Item item : inventory.getItems())
        {
            System.out.println(item.getName());
            if ( itemToSearchFor.equalsIgnoreCase( item.getName() ) )
            {
                System.out.printf("\nItem found: %s", item.getName());
            }
        }
        System.out.println("\nItem not found.");
    }
}
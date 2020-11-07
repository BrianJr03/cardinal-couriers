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
    public void testSearchForItem() throws IOException {
        Inventory inventory =
                new Inventory(createArrayListOfItems(collectItemsFromResources(Inventory.getStore_A_Inventory())));

        String nameOfItemToQuery = '"' + "Orange" + '"' ;
        String priceOfItemToQuery = "1.69";

        for (int i = 0; i < inventory.getItems().size(); i++) {
            if (nameOfItemToQuery.equalsIgnoreCase(inventory.getItems().get( i ).getName())) {
                System.out.printf("\nQueried item: %s\n", nameOfItemToQuery);
                String name = inventory.getItems().get( i ).getName();
                String price = inventory.getItems().get( i ).getPrice();
                Item queriedItem = new Item(name, price);
                System.out.printf("Found: %s\n", queriedItem.prettyPrintItem());
                Assertions.assertEquals(nameOfItemToQuery, queriedItem.getName());
                Assertions.assertEquals(priceOfItemToQuery, queriedItem.getPrice());
            }
        }
    }
}
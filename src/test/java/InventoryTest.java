import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import edu.bsu.cs222.finalProject.Inventory;
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
    public void getItemsFromInventory() throws IOException {
        JsonParser parser = new JsonParser();
        FileReader reader = new FileReader( "src/main/resources/items.json" );

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
    public void testCanReturnFirstItemFromList() throws IOException {
        Inventory inventory = new Inventory(createArrayListOfItems( collectItemsFromResources() ));
        Assertions.assertEquals("\"Apple\"", inventory.getItems().get(0).getName());
        Assertions.assertEquals("1.99", inventory.getItems().get(0).getPrice());
    }
}
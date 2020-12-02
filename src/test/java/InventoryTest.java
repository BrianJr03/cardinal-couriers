import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import edu.bsu.cs222.finalProject.Cart;
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
        FileReader reader = new FileReader( "src/main/resources/storeItems/Walmart_Items.json" );

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
    public void testCanReturnFirstItemFromKroger() throws IOException {
        Inventory inventory =
                new Inventory(createArrayListOfItems( collectItemsFromResources(Inventory.getInventory("kroger")) ));

        ArrayList<String> expectedOrderInfo = new ArrayList <>();
        Cart cart = new Cart(new ArrayList<>());
        Item itemToCart = inventory.getItems().get(0);
        cart.add(itemToCart);

        ArrayList<String> actualOrderInfo = new ArrayList <>();
        actualOrderInfo.add( '"' + "Bread" + '"');
        actualOrderInfo.add( String.valueOf( 0.99 ) );

        for(Item item : cart.getItems()) {
            expectedOrderInfo.add( item.getName() );
            expectedOrderInfo.add( item.getPrice() );
        }
        Assertions.assertEquals(expectedOrderInfo,actualOrderInfo);
    }

    @Test
    public void testCanReturnFirstItemFromStoreB() throws IOException {
        Inventory inventory =
                new Inventory(createArrayListOfItems( collectItemsFromResources(Inventory.getInventory("target")) ));

        ArrayList<String> expectedOrderInfo = new ArrayList <>();
        Cart cart = new Cart(new ArrayList<>());
        Item itemToCart = inventory.getItems().get(0);
        cart.add(itemToCart);

        ArrayList<String> actualOrderInfo = new ArrayList <>();
        actualOrderInfo.add( '"' + "Chicken Noodle Soup" + '"');
        actualOrderInfo.add( String.valueOf( 3.59 ) );

        for(Item item : cart.getItems()) {
            expectedOrderInfo.add( item.getName() );
            expectedOrderInfo.add( item.getPrice() );
        }
        Assertions.assertEquals(expectedOrderInfo,actualOrderInfo);
    }

    @Test
    public void testCanReturnFirstItemFromStoreC() throws IOException {
        Inventory inventory =
                new Inventory(createArrayListOfItems( collectItemsFromResources(Inventory.getInventory("walmart")) ));

        ArrayList<String> expectedOrderInfo = new ArrayList <>();
        Cart cart = new Cart(new ArrayList<>());
        Item itemToCart = inventory.getItems().get(0);
        cart.add(itemToCart);

        ArrayList<String> actualOrderInfo = new ArrayList <>();
        actualOrderInfo.add( '"' + "Apple" + '"');
        actualOrderInfo.add( String.valueOf( 1.69 ) );

        for(Item item : cart.getItems()) {
            expectedOrderInfo.add( item.getName() );
            expectedOrderInfo.add( item.getPrice() );
        }
        Assertions.assertEquals(expectedOrderInfo,actualOrderInfo);
    }

    @Test
    public void testSearchForItem() throws IOException {
        Inventory inventory =
                new Inventory(createArrayListOfItems(collectItemsFromResources(Inventory.getInventory("walmart"))));
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
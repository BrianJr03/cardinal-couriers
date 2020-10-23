import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;

public class TEST
{
    public TEST( ) throws FileNotFoundException {}

    JsonParser parser = new JsonParser();
    FileReader reader = new FileReader( "src/main/resources/items.json" );

    @Test
    public void retrieveItemsFromText() throws IOException
    {
        HashMap<String ,Double> inventoryMap = new HashMap <>();

        JsonArray inventory = parser.parse(reader).getAsJsonArray();
        reader.close();

        for (JsonElement item : inventory)
        {
            inventoryMap.put( item.getAsJsonObject().get( "name" ).toString(),
                    item.getAsJsonObject().get( "price" ).getAsDouble() );
        }

        Collection < String > keySet = inventoryMap.keySet();
        Object[] keys = keySet.toArray();

        Collection < Double > valueSet = inventoryMap.values();
        Object[] values = valueSet.toArray();

        System.out.println("--------");
        System.out.println("Inventory: " + inventoryMap);
        System.out.println("--------");
        System.out.println("1st item: " + keys[0]);
        System.out.println("--------");
        System.out.println("Price: " + "$" + values[0] + "\n");
    }

    @Test // Assumes user cart works the same as a store's inventory
    public void findOrderTotal() throws IOException
    {
        HashMap<String ,Double> userCart = new HashMap <>();

        JsonArray inventory = parser.parse(reader).getAsJsonArray();
        reader.close();

        for (JsonElement item : inventory)
        {
            userCart.put( item.getAsJsonObject().get( "name" ).toString(),
                    item.getAsJsonObject().get( "price" ).getAsDouble() );
        }

        Collection < Double > valueSet = userCart.values();

        double total = 0;
        for (Double dub : valueSet)
        { total += dub; }

        System.out.println("User Cart: " + userCart);
        System.out.println("--------");
        System.out.println("Prices: " + valueSet);
        System.out.println("--------");
        System.out.println("Total: " + "$" + total);
    }
}

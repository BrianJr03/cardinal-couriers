import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import edu.bsu.cs222.finalProject.Inventory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;

public class inventoryTEST
{
    @Test
    public void printInventoryAsList() throws IOException
    {
        System.out.println("-----");
        System.out.println("Available items");
        System.out.println("-----");
        getItemsFromInventory().forEach(( key, value) ->
                System.out.println(key + " : " + value));
    }

    @Test
    public HashMap<String, Double> getItemsFromInventory() throws IOException
    {
        JsonParser parser = new JsonParser();
        FileReader reader = new FileReader( "src/main/resources/items.json" );

        HashMap<String ,Double> inventoryMap = new HashMap <>();

        JsonArray inventory = parser.parse(reader).getAsJsonArray();
        reader.close();

        for (JsonElement item : inventory)
        {
            inventoryMap.put( item.getAsJsonObject().get( "name" ).toString(),
                    item.getAsJsonObject().get( "price" ).getAsDouble() );
        }

        HashMap<String, Double> expected =  new HashMap <>();

        expected.put( "Mac and Cheese", 1.29 );
        expected.put( "Apple", 1.99 );
        expected.put( "Orange", 2.99 );
        expected.put( "Ramen Noodles", 0.99 );
        expected.put( "Bottled Water", 1.89 );

        //Assertions.assertEquals( expected.values() , inventoryMap.values() ); | needs work
        System.out.println(inventoryMap);
        System.out.println(inventoryMap.values());
        System.out.println(expected.values());
        return inventoryMap;
    }
}
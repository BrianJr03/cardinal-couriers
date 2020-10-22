import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class TEST
{
    @Test
    public void retrieveItemsFromText() throws IOException
    {
        HashMap<String ,Double> inventoryMap = new HashMap <>();

        JsonParser parser = new JsonParser();
        FileReader reader = new FileReader( "src/main/resources/items.json" );

        JsonArray inventory = parser.parse(reader).getAsJsonArray();
        reader.close();

        for (JsonElement item : inventory)
        {
            inventoryMap.put( item.getAsJsonObject().get( "name" ).toString(), item.getAsJsonObject().get( "price" ).getAsDouble() );
        }

        System.out.println("--------");
        System.out.println(inventoryMap);
    }
}

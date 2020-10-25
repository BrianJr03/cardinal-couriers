import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.junit.jupiter.api.Test;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class InventoryTest
{

    @Test
    public void getItemsFromInventory() throws IOException
    {
        JsonParser parser = new JsonParser();
        FileReader reader = new FileReader( "src/main/resources/items.json" );

        ArrayList<String[]> itemsList = new ArrayList<>();

        JsonArray inventory = parser.parse(reader).getAsJsonArray();
        reader.close();

        for (JsonElement item : inventory)
        {
            String[] singleItem = new String[2];
            singleItem[0] = item.getAsJsonObject().get("name").toString();
            singleItem[1] = item.getAsJsonObject().get("price").getAsString();
            itemsList.add(singleItem);
        }

    }
}
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;

public class cartTEST
{
    public cartTEST() throws FileNotFoundException
    {}

    JsonParser parser = new JsonParser();
    FileReader reader = new FileReader( "src/main/resources/items.json" );

    @Test  // Assumes user cart works the same as a store's inventory
    public HashMap < String, Double > getInventoryFromJson() throws IOException
    {
        HashMap<String ,Double> userCartMap = new HashMap <>();

        JsonArray userCart = parser.parse(reader).getAsJsonArray();
        reader.close();

        for ( JsonElement item : userCart)
        {
            userCartMap.put( item.getAsJsonObject().get( "name" ).toString(),
                    item.getAsJsonObject().get( "price" ).getAsDouble() );
        }

        return userCartMap;
    }

    @Test
    public void findOrderTotal() throws IOException
    {
        HashMap <String ,Double> userCart = getInventoryFromJson();

        Collection < Double > valueSet = userCart.values();

        double total = 0;
        for (Double dub : valueSet)
        { total += dub; }

        System.out.println("--------");
        System.out.println("User Cart: " + userCart);
        System.out.println("--------");
        System.out.println("Prices: " + valueSet);
        System.out.println("--------");
        System.out.println("Total: " + "$" + total);

        Assertions.assertEquals( total, 9.15 );
    }
}
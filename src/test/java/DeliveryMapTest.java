import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileReader;
import java.io.IOException;

import static edu.bsu.cs222.finalProject.DeliveryMap.collectJsonObjectFromGoogle;
import static edu.bsu.cs222.finalProject.DeliveryMap.findDistanceFromBSU;

public class DeliveryMapTest {

    @Test
    public void testCanPullJSONDataFromGoogle() throws IOException {
        String address = "1708 W Bethel Ave";
        String city = "Muncie";
        String state = "IN";
        JsonObject results = collectJsonObjectFromGoogle(address,city,state);
        FileReader reader = new FileReader("src/test/resources/Static JSON Data.json");
        Assertions.assertEquals(JsonParser.parseReader(reader).getAsJsonObject(),results.getAsJsonObject());
    }

    @Test
    public void canReturnProperDistanceDouble() throws IOException {
        FileReader reader = new FileReader("src/test/resources/Static JSON Data.json");
        JsonObject object = JsonParser.parseReader(reader).getAsJsonObject();
        Assertions.assertEquals(1.7, findDistanceFromBSU(object));
    }
}

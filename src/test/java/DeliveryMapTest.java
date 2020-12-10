
import edu.bsu.cs222.finalProject.DeliveryMap;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DeliveryMapTest {

    @Test
    public void isGoogleURLFormattedCorrectly() {
        String address = "1708 W Bethel Ave";
        String city = "Muncie";
        String state = "IN";
        Assertions.assertEquals("1708+W+Bethel+Ave+Muncie+IN",DeliveryMap.formatAddressForGoogle(address, city, state));
    }
}

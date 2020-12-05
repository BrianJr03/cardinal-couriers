import edu.bsu.cs222.finalProject.Controllers.DeliveryUIController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DeliveryTest
{
    DeliveryUIController delivery = new DeliveryUIController();

    @Test
    public void isValidZip1()
    { Assertions.assertFalse( delivery.isValidZip( String.valueOf( 456 ))); }

    @Test
    public void isValidZip2()
    { Assertions.assertFalse( delivery.isValidZip( String.valueOf( 4765 ))); }

    @Test
    public void isValidZip3()
    { Assertions.assertFalse( delivery.isValidZip( String.valueOf( 45 ))); }

    @Test
    public void isValidZip4()
    { Assertions.assertTrue( delivery.isValidZip( String.valueOf( 47304 ))); }

    @Test
    public void isValidZip5()
    { Assertions.assertTrue( delivery.isValidZip( String.valueOf( 47303 ))); }

    @Test
    public void isValidZip6()
    { Assertions.assertTrue( delivery.isValidZip( String.valueOf( 47305 ))); }
}

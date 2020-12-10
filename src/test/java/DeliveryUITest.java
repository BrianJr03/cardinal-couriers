import edu.bsu.cs222.finalProject.Controllers.DeliveryUIController;
import edu.bsu.cs222.finalProject.DeliveryInfo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DeliveryUITest
{
    DeliveryInfo delivery = new DeliveryInfo("","","","","");

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

    @Test
    public void isValidCity1()
    { Assertions.assertTrue( delivery.isValidCity( "Muncie" ) ); }

    @Test
    public void isValidCity2()
    { Assertions.assertTrue( delivery.isValidCity( "Indianapolis" ) ); }

    @Test
    public void isValidCity3()
    { Assertions.assertTrue( delivery.isValidCity( "New Castle" ) ); }

    @Test
    public void isValidCity4()
    { Assertions.assertFalse( delivery.isValidCity( "Mu" ) ); }

    @Test
    public void isValidCity5()
    { Assertions.assertFalse( delivery.isValidCity( " Indy" ) ); }

    @Test
    public void isValidCity6()
    { Assertions.assertFalse( delivery.isValidCity( "New-" ) ); }

    @Test
    public void isValidState()
    { Assertions.assertTrue( delivery.isValidState_Abbreviation( "IN" ) ); }

    @Test
    public void isValidState1()
    { Assertions.assertTrue( delivery.isValidState_Abbreviation( "MN" ) ); }

    @Test
    public void isValidState2()
    { Assertions.assertTrue( delivery.isValidState_Abbreviation( "CA" ) ); }

    @Test
    public void isValidState3()
    { Assertions.assertFalse( delivery.isValidState_Abbreviation( "Indiana" ) ); }

    @Test
    public void isValidState4()
    { Assertions.assertFalse( delivery.isValidState_Abbreviation( "mn" ) ); }

    @Test
    public void isValidState5()
    { Assertions.assertFalse( delivery.isValidState_Abbreviation( "Cali" ) ); }

    @Test
    public void isValidStreetAddress1()
    { Assertions.assertTrue( delivery.isValidStreet_Address( "2000 W University Ave" ) ); }

    @Test
    public void isValidStreetAddress2()
    { Assertions.assertTrue( delivery.isValidStreet_Address( "1700 W Neely Ave" ) ); }

    @Test
    public void isValidStreetAddress3()
    { Assertions.assertTrue( delivery.isValidStreet_Address( "1618 W University Ave" ) ); }

    @Test
    public void isValidStreetAddress4()
    { Assertions.assertFalse( delivery.isValidStreet_Address( "West Avenue" ) ); }

    @Test
    public void isValidStreetAddress5()
    { Assertions.assertFalse( delivery.isValidStreet_Address( "22b2 Rex St" ) ); }

    @Test
    public void isValidStreetAddress6()
    { Assertions.assertFalse( delivery.isValidStreet_Address( "22 Neeley" ) ); }
}
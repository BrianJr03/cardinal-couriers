
import edu.bsu.cs222.finalProject.SendReceipt;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
public class SendReceiptTest {

    SendReceipt sendReceipt = new SendReceipt();

    @Test
    public void testIsValidNumber() {
        String phoneNumber = "2192192192";
        Assertions.assertFalse( sendReceipt.isValidPhoneNumber( phoneNumber ) );
    }

    @Test
    public void testIsValidNumber_1() {
        String phoneNumber = "219-219-2192";
        Assertions.assertTrue( sendReceipt.isValidPhoneNumber( phoneNumber ) );
    }

    @Test
    public void testIsValidNumber_2() {
        String phoneNumber = "573-898-9921";
        Assertions.assertTrue( sendReceipt.isValidPhoneNumber( phoneNumber ) );
    }

    @Test
    public void testIsValidNumber_3() {
        String phoneNumber = "21921921921";
        Assertions.assertFalse( sendReceipt.isValidPhoneNumber( phoneNumber ) );
    }

    @Test
    public void testIsValidNumber_4() {
        String phoneNumber = "2192";
        Assertions.assertFalse( sendReceipt.isValidPhoneNumber( phoneNumber ) );
    }

    @Test
    public void testIsValidEmail() {
        String email = "admin@bsu..edu";
        Assertions.assertFalse( sendReceipt.isValidEmail( email ) );
    }

    @Test
    public void testIsValidEmail1() {
        String email = "admin@bsu.edu";
        Assertions.assertTrue( sendReceipt.isValidEmail( email ) );
    }

    @Test
    public void testIsValidEmail2() {
        String email = "admin@gmail.com";
        Assertions.assertTrue( sendReceipt.isValidEmail( email ) );
    }

    @Test
    public void testIsValidEmail3() {
        String email = "admin@yahoo.co";
        Assertions.assertTrue( sendReceipt.isValidEmail( email ) );
    }

    @Test
    public void testIsValidEmail4() {
        String email = "admin@yahoo";
        Assertions.assertFalse( sendReceipt.isValidEmail( email ) );
    }
}
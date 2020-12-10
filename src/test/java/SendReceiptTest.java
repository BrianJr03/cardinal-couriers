import edu.bsu.cs222.finalProject.SendReceipt;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SendReceiptTest {

    @Test
    public void testIsValidNumber() {
        String phoneNumber = "2192192192";
        Assertions.assertNull( SendReceipt.isValidPhoneNumber( phoneNumber ) );
    }

    @Test
    public void testIsValidNumber_1() {
        String phoneNumber = "219-219-2192";
        Assertions.assertEquals(phoneNumber, SendReceipt.isValidPhoneNumber( phoneNumber ) );
    }

    @Test
    public void testIsValidNumber_2() {
        String phoneNumber = "573-898-9921";
        Assertions.assertEquals(phoneNumber, SendReceipt.isValidPhoneNumber( phoneNumber ) );
    }

    @Test
    public void testIsValidNumber_3() {
        String phoneNumber = "21921921921";
        Assertions.assertNull( SendReceipt.isValidPhoneNumber( phoneNumber ) );
    }

    @Test
    public void testIsValidNumber_4() {
        String phoneNumber = "2192";
        Assertions.assertNull( SendReceipt.isValidPhoneNumber( phoneNumber ) );

    }

    @Test
    public void testIsValidEmail() {
        String email = "admin@bsu..edu";
        Assertions.assertNull( SendReceipt.isValidEmail( email ) );

    }

    @Test
    public void testIsValidEmail1() {
        String email = "admin@bsu.edu";
        Assertions.assertEquals(email, SendReceipt.isValidEmail( email ) );
    }

    @Test
    public void testIsValidEmail2() {
        String email = "admin@gmail.com";
        Assertions.assertEquals(email, SendReceipt.isValidEmail( email ) );
    }

    @Test
    public void testIsValidEmail3() {
        String email = "admin@yahoo.co";
        Assertions.assertEquals(email, SendReceipt.isValidEmail( email ) );
    }

    @Test
    public void testIsValidEmail4() {
        String email = "admin@yahoo";
        Assertions.assertNull( SendReceipt.isValidEmail( email ) );
    }
}
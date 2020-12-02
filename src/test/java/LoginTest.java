import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static edu.bsu.cs222.finalProject.LoginLogic.isValidPassword;
import static edu.bsu.cs222.finalProject.LoginLogic.isValidUserName;

public class LoginTest
{
    @Test
    public void testIfValidUsername() {
        String username = "monika";
        Assertions.assertTrue(isValidUserName(username));
    }

    @Test
    public void testIfValidPassword() {
        String username = "monika";
        String password = "!PassiveWord786!";
        Assertions.assertTrue(isValidPassword(username, password));
    }
}
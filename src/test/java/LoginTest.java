import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginTest
{
    public static boolean isValidUserName(String username) {
        Pattern pattern = Pattern.compile("[a-zA-Z]+(@bsu\\.edu)?");
        Matcher matcher = pattern.matcher(username);
        return (matcher.find() && matcher.group().equals(username));
    }

    public static boolean isValidPassword(String password) {
        Pattern pattern = Pattern.compile("[a-zA-Z]+([+-]?[0-9]+)?");
        Matcher matcher = pattern.matcher(password);
        return (matcher.find() && matcher.group().equals(password));
    }

    // driver function to check for valid bsu username or password
    public static void main(String[] args) {
        boolean running = true;
        while (running){
            Scanner in = new Scanner( System.in );
            System.out.println("\n1. Check BSU username\n2. Check BSU password\n3. Exit\n");
            String userChoice = in.nextLine();
            switch ( userChoice ) {
                case "1" -> {System.out.print("\nEnter your BSU username: ");
                    String username = in.nextLine();
                    if (isValidUserName(username))
                        System.out.print("\nValid username.\n");
                    else
                        System.out.print("\nInvalid username.\n"); }

                case "2" -> {System.out.print("Enter your BSU password: ");
                    String password = in.nextLine();
                    if (isValidPassword(password))
                        System.out.print("\nValid password.\n");
                    else
                        System.out.print("\nInvalid password.\n");}
                case "3" -> running = false;
                default -> System.out.println("\nChoose a valid option.");
            }
        }
    }
}
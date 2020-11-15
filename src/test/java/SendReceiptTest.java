import edu.bsu.cs222.finalProject.Cart;
import edu.bsu.cs222.finalProject.Inventory;
import edu.bsu.cs222.finalProject.Item;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static edu.bsu.cs222.finalProject.Inventory.collectItemsFromResources;
import static edu.bsu.cs222.finalProject.Inventory.createArrayListOfItems;

public class SendReceiptTest {
    Inventory inventory = new Inventory(createArrayListOfItems(collectItemsFromResources
            (Inventory.getStore_A_Inventory())));

    public SendReceiptTest( ) throws IOException {}

    @Test //Will override receiptGS_BSU.txt
    public void writeReceipt() throws IOException {
        Cart cart = new Cart(new ArrayList <>());
        Item handSanitizer = inventory.getItems().get(6);
        Item macAndCheese = inventory.getItems().get(4);
        cart.add(handSanitizer);
        cart.add( macAndCheese );

        File file = new File( "receiptGS_BSU.txt" );
        FileWriter writer = new FileWriter(file);

        int counter = 0;
        writer.write( "TEST RECEIPT\n" );
        for (Item item : cart.getCartItems()) {
            counter++;
            writer.write(counter + ". " + item.getName() + " | " + item.getPrice() + "\n" );
        }
        writer.close();
    }

    public static boolean isValidNumber(String phoneNumber) {
        Pattern pattern = Pattern.compile("\\d{3}-\\d{3}-\\d{4}");
        Matcher matcher = pattern.matcher(phoneNumber);
        return (matcher.find() && matcher.group().equals(phoneNumber));
    }

    public static boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pattern = Pattern.compile(emailRegex);
        if (email == null) return false;
        return pattern.matcher(email).matches();
    }

    // driver function to check for valid email or phone number
    public static void main(String[] args) {
        Scanner in = new Scanner( System.in );
        System.out.println("\n1. Check email\n2. Check phone number");
        String userChoice = in.nextLine();
        switch ( userChoice ) {
            case "1" -> {System.out.print("\nEnter email: ");
                String email = in.nextLine();
                if (isValidEmail(email))
                    System.out.print("\nValid email.\n");
                else
                    System.out.print("\nNot a valid email.\n"); }

            case "2" -> {System.out.println("\nRequired format: xxx-xxx-xxxx");
                System.out.print("Enter phone number: ");
                String phoneNumber = in.nextLine();
                if (isValidNumber(phoneNumber))
                    System.out.print("\nValid phone number.\n");
                else
                    System.out.print("\nNot a valid phone number.\n");}

            default -> System.out.println("Choose valid option.");
        }
    }
}
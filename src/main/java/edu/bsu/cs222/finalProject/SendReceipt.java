package edu.bsu.cs222.finalProject;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SendReceipt {
    private final LocalDate purchaseDate = DateTime.now().toLocalDate();
    public void askUserForReceipt(Cart cart) throws MessagingException, IOException {
        Scanner input = new Scanner( System.in );
        System.out.print( "\nWould you like to be sent a receipt? Y or N \n");
        String userInput = input.nextLine();
        switch ( userInput.toUpperCase() ) {
            case "Y" -> {
                System.out.println( "\n1. via Text\n2. via Email\n" );
                String userChoice = input.nextLine();
                showReceiptOptions( userChoice , cart );
            }
            case "N" -> System.out.println("\nReturning to Main Menu..\n");
            default -> System.out.println("\nPlease enter Y or N. Main Menu..\n");
        }
    }

    public void showReceiptOptions(String userChoice, Cart cart) throws IOException, MessagingException {
        Scanner in = new Scanner( System.in );
        switch ( userChoice ) {
            case "1" -> {System.out.println("\nRequired format: xxx-xxx-xxxx");
                System.out.println( "Please enter your phone number:" );
                String phoneNumber = in.nextLine();
                if (isValidPhoneNumber( phoneNumber ))
                    { sendReceiptAsTextMSG( phoneNumber, cart ); }
                else
                    { System.out.println("\nPlease enter a valid phone number.\nMain menu..\n"); }

            }

            case "2" -> {System.out.println( "\nPlease enter your email:" );
                String userEmail = in.nextLine();
                if (isValidEmail( userEmail ))
                    { sendReceiptAsEmail( userEmail , cart ); }
                else
                    { System.out.println("\nPlease enter a valid email address.\nMain Menu..\n"); }
            }
            default -> System.out.println("\nPlease enter a valid choice.\nReturning to Main Menu..");
        }
    }

    public void writeReceipt(Cart cart) throws IOException {
        int lowerbound = 1225; int upperbound = 2590;
        int orderNumber = (int) (Math.random() * (upperbound - lowerbound + 1) + lowerbound);
        int counter = 0; double sum = 0;
        File file = new File( "receiptGS_BSU.txt" );
        FileWriter writer = new FileWriter(file);
        writer.write( "Receipt for order " + orderNumber + "N\n" + "----------------------" +
                "-----------------" + "\n");
        for (Item item : cart.getCartItems()) {
            counter++;
            sum = cart.getTotalCartPrice();
            writer.write(counter + ". " + item.getName() + " | " + item.getPrice() + "\n" );}
            writer.write( "\nTotal: $" + Math.round(sum * 100.0) / 100.0 + "\n" );
            writer.write( "Date purchased: " + purchaseDate + "\n");
            writer.write( "---------------------------------------------\n" );
            writer.write( "\nThanks for shopping with us!" );
            writer.close();
    }

    private boolean isValidPhoneNumber(String phoneNumber) {
        Pattern pattern = Pattern.compile("\\d{3}-\\d{3}-\\d{4}");
        Matcher matcher = pattern.matcher(phoneNumber);
        return (matcher.find() && matcher.group().equals(phoneNumber));
    }

    private boolean isValidEmail(String email) {
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9_+&*-]+(?:\\" +
                ".[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\" +
                ".)+[a-zA-Z]{2,7}$");
        if (email == null) return false;
        return pattern.matcher(email).matches();
    }

    public void sendReceiptAsEmail(String userEmail, Cart cart) throws MessagingException, IOException {
        writeReceipt( cart );
        Multipart emailContent = new MimeMultipart();
        MimeBodyPart textBodyPart = new MimeBodyPart();

        String companyEmail = "groceryshop.bsu@yahoo.com";
        Properties props = System.getProperties();
        props.put( "mail.smtp.auth", "true" );
        props.put( "mail.smtp.starttls.enable", "true" );
        props.put( "mail.smtp.host", "smtp.mail.yahoo.com" );
        props.put( "mail.smtp.port", "587" );

        Session session = Session.getInstance( props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication( "groceryshop.bsu","eikh xydc qbog pxhz" );
            }
        });

        MimeMessage msg = new MimeMessage( session );
        msg.setFrom(new InternetAddress( companyEmail ));
        msg.addRecipient(Message.RecipientType.TO, new InternetAddress( userEmail ));
        msg.setSubject( "Thanks for the order! [Receipt]" );

        String fileName = "receiptGS_BSU.txt";

        DataSource source = new FileDataSource( fileName );
        textBodyPart.setDataHandler( new DataHandler( source ) );

        emailContent.addBodyPart( textBodyPart );
        msg.setContent( emailContent );
        Transport.send( msg );

        System.out.println("\nYour receipt has been sent.\n");
    }

    public void sendReceiptAsTextMSG( String phoneNumber , Cart cart ) throws MessagingException, IOException {
        writeReceipt( cart );
        System.out.println("\nWhich carrier do you have service with?");
        System.out.println("1. AT&T");
        System.out.println("2. Sprint");
        System.out.println("3. Verizon");
        System.out.println("4. T-Mobile");
        Scanner input = new Scanner(System.in);
        String userCarrier = input.nextLine();
        switch ( userCarrier ) {
                    case "1" -> sendReceiptAsEmail( phoneNumber + "@mms.att.net", cart );
                    case "2" -> sendReceiptAsEmail( phoneNumber + "@pm.sprint.com", cart );
                    case "3" -> sendReceiptAsEmail( phoneNumber + "@vzwpix.com", cart );
                    case "4" -> sendReceiptAsEmail( phoneNumber + "@tmomail.net", cart );
                    default -> System.out.println("\nFailed to select a valid carrier. Main menu..\n");
        }
    }
}
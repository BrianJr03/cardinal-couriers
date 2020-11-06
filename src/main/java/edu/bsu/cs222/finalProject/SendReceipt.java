package edu.bsu.cs222.finalProject;

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

public class SendReceipt {
    static LocalDate purchaseDate = Main.purchaseDate;

    public static void askUserForReceipt(Cart cart) throws MessagingException, IOException {
        Scanner input = new Scanner( System.in );
        System.out.print( "\nWould you like to be sent a receipt? Y or N \n");
        String userInput = input.nextLine();
        if (userInput.equalsIgnoreCase( "y" )) {
            System.out.println("\n1. via Text\n2. via Email\n" +
                    "");
            String userChoice = input.nextLine();
            switch ( userChoice ) {
                case "1" -> {
                    System.out.println( "\nPlease enter your phone number:" );
                    String phoneNumber = input.nextLine();
                    writeReceipt( cart );
                    sendReceiptAsTextMSG( phoneNumber );
                }

                case "2" -> {
                    System.out.println( "\nPlease enter your email:" );
                    String userEmail = input.nextLine();
                    writeReceipt( cart );
                    sendReceiptAsEmail( userEmail );
                }
            }
        }
    }

    public static void writeReceipt(Cart cart) throws IOException {
        int lowerbound = 1225; int upperbound = 2590;
        int orderNumber = (int) (Math.random() * (upperbound - lowerbound + 1) + lowerbound);
        int counter = 0; double sum = 0;
        File file = new File( "receiptGS_BSU.txt" );
        FileWriter writer = new FileWriter(file);
        writer.write( "Receipt for order " + orderNumber + "N\n" + "----------------------" +
                "-----------------" + "\n");
        for (Item item : cart.getCartItems()) {
            counter++;
            sum += Double.parseDouble( item.getPrice() );
            writer.write(counter + ". " + item.getName() + " | " + item.getPrice() + "\n" );
        }
        writer.write( "\nTotal: $" + Math.round(sum * 100.0) / 100.0 + "\n" );
        writer.write( "Date purchased: " + purchaseDate + "\n");
        writer.write( "---------------------------------------------\n" );
        writer.write( "\nThanks for shopping with us!" );
        writer.close();
    }

    public static void sendReceiptAsEmail(String userEmail) throws MessagingException {
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

        //Attach .txt here
        String fileName = "receiptGS_BSU.txt";

        DataSource source = new FileDataSource( fileName );
        textBodyPart.setDataHandler( new DataHandler( source ) );

        emailContent.addBodyPart( textBodyPart );
        msg.setContent( emailContent );
        Transport.send( msg );

        System.out.println("\nYour receipt has been sent.\n");
    }

    public static void sendReceiptAsTextMSG(String phoneNumber) throws MessagingException {
        System.out.println("\nWhich carrier do you have service with?");
        System.out.println("1. AT&T");
        System.out.println("2. Sprint");
        System.out.println("3. Verizon");
        System.out.println("4. T-Mobile");

        Scanner input = new Scanner(System.in);
        String userCarrier = input.nextLine();
        switch ( userCarrier ) {
                    case "1" -> sendReceiptAsEmail( phoneNumber + "@mms.att.net" );
                    case "2" -> sendReceiptAsEmail( phoneNumber + "@pm.sprint.com" );
                    case "3" -> sendReceiptAsEmail( phoneNumber + "@vzwpix.com" );
                    case "4" -> sendReceiptAsEmail( phoneNumber + "@tmomail.net" );
                    default -> System.out.println("\nFailed to select a valid carrier. Main menu..");
        }
    }
}
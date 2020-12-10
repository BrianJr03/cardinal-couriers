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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SendReceipt {

    private final LocalDate purchaseDate = DateTime.now().toLocalDate();

    public void writeReceipt(Cart cart) throws IOException {
        int lowerbound = 1225; int upperbound = 2590;
        int orderNumber = (int) (Math.random() * (upperbound - lowerbound + 1) + lowerbound);
        double sum = 0;
        File file = new File( "receiptGS_BSU.txt" );
        FileWriter writer = new FileWriter(file);
        writer.write( "Receipt for order " + orderNumber + "N\n" + "----------------------" +
                "-----------------" + "\n");
        for (Item item : cart.getItems()) {
            sum = cart.getTotalCost();
            writer.write(item.getName() + " | " + item.getPrice() + " | QTY:" + item.getQuantity() + "\n" );}
            writer.write( "\nTotal: $" + Math.round(sum * 100.0) / 100.0 + "\n" );
            writer.write( "Date purchased: " + purchaseDate + "\n");
            writer.write( "---------------------------------------------\n" );
            writer.write( "\nThanks for shopping with us!" );
            writer.close();
    }

    public void sendReceiptAsEmail(String userEmail, Cart cart) throws MessagingException, IOException {
        writeReceipt( cart );

        Multipart emailContent = new MimeMultipart();
        MimeBodyPart textBodyPart = new MimeBodyPart();
        MimeMessage msg = new MimeMessage( establishEmailSession() );

        String companyEmail = "groceryshop.bsu@yahoo.com";
        msg.setFrom(new InternetAddress( companyEmail ));
        msg.addRecipient(Message.RecipientType.TO, new InternetAddress( userEmail ));
        msg.setSubject( "Thanks for the order! [Receipt]" );
        String fileName = "receiptGS_BSU.txt";

        DataSource source = new FileDataSource( fileName );
        textBodyPart.setDataHandler( new DataHandler( source ) );

        emailContent.addBodyPart( textBodyPart );
        msg.setContent( emailContent );
        Transport.send( msg );
    }

    public void sendReceiptAsTextMSG( String phoneNumber , Cart cart , String userCarrier) throws MessagingException, IOException {
        writeReceipt( cart );
        switch ( userCarrier ) {
            case "AT&T" -> sendReceiptAsEmail( phoneNumber + "@mms.att.net", cart );
            case "Sprint" -> sendReceiptAsEmail( phoneNumber + "@pm.sprint.com", cart );
            case "Verizon" -> sendReceiptAsEmail( phoneNumber + "@vzwpix.com", cart );
            case "T-Mobile" -> sendReceiptAsEmail( phoneNumber + "@tmomail.net", cart );
        }
    }

    public static Properties setEmailProperties() {
        Properties props = System.getProperties();
        props.put( "mail.smtp.auth", "true" );
        props.put( "mail.smtp.starttls.enable", "true" );
        props.put( "mail.smtp.host", "smtp.mail.yahoo.com" );
        props.put( "mail.smtp.port", "587" );
        return props;
    }

    private static Session establishEmailSession() {
        return Session.getInstance( setEmailProperties(), new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication( "groceryshop.bsu","eikh xydc qbog pxhz" );
            }
        });
    }

    public static String isValidEmail( String email ) {
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9_+&*-]+(?:\\" +
                ".[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\" +
                ".)+[a-zA-Z]{2,7}$");
        if (email == null) return null;
        boolean isMatch =  pattern.matcher(email).matches();
        if (isMatch){return email;}
        else return null;
    }

    public static String isValidPhoneNumber( String phoneNumber ) {
        Pattern pattern = Pattern.compile("\\d{3}-\\d{3}-\\d{4}");
        Matcher matcher = pattern.matcher(phoneNumber);
        boolean isMatch = (matcher.find() && matcher.group().equals(phoneNumber));
        if(isMatch){return phoneNumber;}
        else return null;
    }
}
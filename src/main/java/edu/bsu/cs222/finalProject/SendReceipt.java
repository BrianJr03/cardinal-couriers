package edu.bsu.cs222.finalProject;

import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import java.util.Properties;

public class SendReceipt
{
    public void sendReceipt(String userEmail) throws MessagingException {
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
        msg.setFrom(new InternetAddress(companyEmail) );
        msg.addRecipient( Message.RecipientType.TO, new InternetAddress( userEmail ) );
        msg.setSubject( "Thanks for the order! [Receipt]" );

        //Attach .txt here
        String fileName = "C:/Users/walte/OneDrive/Desktop/Fall " + //needs to reference receiptGS_BSU.txt in project folder, not local pc
                "2020/currentFinal_Grocery-Shop/src/main/resources/receiptGS_BSU.txt";

        DataSource source = new FileDataSource( fileName );
        textBodyPart.setDataHandler( new DataHandler( source ) );
        textBodyPart.setFileName( fileName );
        emailContent.addBodyPart( textBodyPart );
        msg.setContent( emailContent );
        Transport.send( msg );

        System.out.println("\nYour receipt has been sent.");
    }
}

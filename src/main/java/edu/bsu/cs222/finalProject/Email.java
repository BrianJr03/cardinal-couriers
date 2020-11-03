package edu.bsu.cs222.finalProject;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class Email
{
    public static void sendReceipt() throws MessagingException {
        String toEmail = "bkwalker@bsu.edu";
        String fromEmail = "groceryshop.bsu@yahoo.com";

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
        msg.setFrom(new InternetAddress(fromEmail) );
        msg.addRecipient( Message.RecipientType.TO, new InternetAddress(toEmail) );
        msg.setSubject( "Thanks for the order!" );
        msg.setText( "Your receipt is attached to this email." +
                " Thanks for shopping with us and have a wonderful day!" );
        Transport.send( msg );
        System.out.println("\nSent Message!");
    }
}

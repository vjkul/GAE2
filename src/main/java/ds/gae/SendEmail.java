package ds.gae;
//File Name SendEmail.java

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class SendEmail {

public void sendEmail(String subject, String body) {    
   String to = "vincentjanssen95@gmail.com";
   String from = "charlesvandamme2@gmail.com";
   String host = "localhost";

   Properties properties = System.getProperties();

   properties.setProperty("mail.smtp.host", host);

   Session session = Session.getDefaultInstance(properties);

   try {
      // Create a default MimeMessage object.
      MimeMessage message = new MimeMessage(session);

      // Set From: header field of the header.
      message.setFrom(new InternetAddress(from));

      // Set To: header field of the header.
      message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

      
      message.setSubject(subject);

      message.setText(body);

      // Send message
      Transport.send(message);
      System.out.println("Sent message successfully....");
   } catch (MessagingException mex) {
      mex.printStackTrace();
   }
}
}

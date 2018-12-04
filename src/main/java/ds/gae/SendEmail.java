package ds.gae;
//File Name SendEmail.java

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;

import com.google.appengine.api.mail.MailService;
import com.google.appengine.api.mail.MailService.Message;
import com.google.appengine.api.mail.MailServiceFactory;

public class SendEmail {

	public static void sendEmail(String subject, String body) {    
		MailServiceFactory msf = new MailServiceFactory();
		MailService ms = msf.getMailService();
		try {
		  Message msg = new Message();
		  msg.setSender("vincentjanssen95@gmail.com");
		  msg.setTo("client@example.com");
		  msg.setSubject(subject);  
		  msg.setTextBody(body);
		  ms.send(msg);
		  System.out.println("Body: " + msg.getTextBody());
		} catch (UnsupportedEncodingException e) {
		  // ...
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}

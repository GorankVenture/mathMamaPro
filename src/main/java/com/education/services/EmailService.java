package com.education.services;


import java.util.Properties;


import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;

import org.springframework.stereotype.Service;
@Service
public class EmailService 

{

	private org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(EmailService.class);



	public boolean sendEmail(String subject, String message, String to) {
		boolean flag = false;
		String from = "kansalgorank56@gmail.com";
		if (to != null) {
			String host = "smtp.gmail.com";

			// get the system properties
			Properties properties = System.getProperties();
			System.out.println("PROPERTIES" + properties);

			properties.put("mail.smtp.host", host);
			properties.put("mail.smtp.port", "465");
			properties.put("mail.smtp.ssl.enable", "true");
			properties.put("mail.smtp.auth", "true");
			// properties.put("mail.debug", "true");

			// to get the session object..
			Session session = Session.getInstance(properties, new Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("kansalgorank56@gmail.com", "veklkcbjpjzwbyyl");
				}
			});
			session.setDebug(true);

			// compose the message [text, multi-media]
			MimeMessage mimeMessage = new MimeMessage(session);

			try {
				mimeMessage.setFrom(from);

				mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
				mimeMessage.setSubject(subject);

				// Create MimeMultipart
				MimeMultipart mimeMultipart = new MimeMultipart();

				// Text part
				MimeBodyPart textMime = new MimeBodyPart();
				
				textMime.setText(message,"UTF-8","html");
				mimeMultipart.addBodyPart(textMime);

				
				mimeMessage.setContent(mimeMultipart);

				// Send the message
				Transport.send(mimeMessage);
				log.info("Sent email successfully");
				flag = true;
			} catch (Exception e) {
				log.error("Error sending email", e);
			}
		} else {
			log.warn("To address is null. Email not sent.");
		}
		return flag;
	}


}

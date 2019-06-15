package mailApp;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail {

	public static void send(String to, String subject, String text) {
		Properties properties = new Properties();
		properties.put("mail.smtp.host", MailApp.smtpHost);
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");

		Session session = Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(MailApp.user, MailApp.password);
			}
		});

		Transport transport = null;

		try {
			transport = session.getTransport("smtps");
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(MailApp.user));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			message.setSubject(subject);
			message.setText(text);
			transport.connect(MailApp.smtpHost, MailApp.user, MailApp.password);
			Transport.send(message);

			System.out.println("Wiadomość wysłana");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}

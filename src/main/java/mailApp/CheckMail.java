package mailApp;

import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.MimeMultipart;

import com.sun.mail.imap.IMAPStore;

import mailApp.repository.MailEntity;

public class CheckMail {

	public static void check() {

		try {
			Properties properties = new Properties();
			properties.put("mail.store.protocol", "imaps");
			properties.put("mail.imaps.starttls.enable", "true");
			Session emailSession = Session.getInstance(properties);
			Store store = emailSession.getStore("imaps");
			store.connect(MailApp.imapHost, MailApp.user, MailApp.password);

			IMAPStore imapStore = (IMAPStore) store;

			Folder emailFolder = imapStore.getFolder("INBOX");
			emailFolder.open(Folder.READ_ONLY);

			Message[] messages = emailFolder.getMessages();

			saveToDb(messages);
			
			emailFolder.close(false);
			store.close();

		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unused")
	private static void cliListMessages(Message[] messages) throws MessagingException, Exception {
		System.out.println("Liczba wiadomości: " + messages.length);
		for (int i = 0; i < messages.length; i++) {
			System.out.println("---------------------------------");
			System.out.println("Email nr " + (i + 1));
			System.out.println("Temat: " + messages[i].getSubject());
			System.out.println("Od: " + messages[i].getFrom()[0]);
			System.out.println("Treść: " + getTextFromMimeMultipart(messages[i].getContent()));
		}
	}

	private static void saveToDb(Message[] messages) throws Exception {
		
		for (int i = 0; i < messages.length; i++) {
			MailEntity mail = new MailEntity();
			mail.setSender(messages[i].getFrom()[0].toString());
			mail.setSubject(messages[i].getSubject());
			mail.setDate(messages[i].getSentDate());
			mail.setBody(getTextFromMimeMultipart(messages[i].getContent()));
			
			MailApp.repo.add(mail);

		}
	}

	private static String getTextFromMimeMultipart(Object content) throws Exception {

		String result = "";
		if (content instanceof MimeMultipart) {
			MimeMultipart mimeMultipart = (MimeMultipart) content;
			int partCount = mimeMultipart.getCount();
			for (int i = 0; i < partCount; i++) {
				BodyPart bodyPart = mimeMultipart.getBodyPart(i);
				if (bodyPart.isMimeType("text/plain")) {
					result = result + "\n" + bodyPart.getContent();
				} else if (bodyPart.isMimeType("text/html")) {
					String html = (String) bodyPart.getContent();
					result = html;
				} else if (bodyPart.getContent() instanceof MimeMultipart) {
					result = result + getTextFromMimeMultipart((MimeMultipart) bodyPart.getContent());
				}
			}
		} else {
			result = content.toString();
		}
		return result;
	}

}

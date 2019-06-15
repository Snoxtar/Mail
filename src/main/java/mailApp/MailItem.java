package mailApp;

import java.util.Date;

public class MailItem {
	
	private String subject;
	private String sender;
	private Date date;
	private String body;
	
	public MailItem() {}
	
	public MailItem(String subject, String sender, Date date, String body) {
		this.subject = subject;
		this.sender = sender;
		this.date = date;
		this.body = body;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

}

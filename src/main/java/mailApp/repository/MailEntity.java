package mailApp.repository;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import mailApp.MailItem;

@Entity
@Table(name = "mail_items")
public class MailEntity {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "int(11) comment 'identyfikator wiadomości'")
    private Integer id;
	
	@Column(name = "subject", columnDefinition = "varchar(255) comment 'tytuł wpisu'")
    private String subject;
	
	@Column(name = "sender", columnDefinition = "varchar(255) comment 'tytuł wpisu'")
    private String sender;

    @Column(name = "date", columnDefinition = "timestamp comment 'data wpisu'")
    private Date date;

    @Column(name = "body", columnDefinition = "text comment 'wpis'")
    private String body;

    public Integer getId() {
		return id;
	}

	public String getSubject() {
		return subject;
	}

	public String getSender() {
		return sender;
	}

	public Date getDate() {
		return date;
	}

	public String getBody() {
		return body;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public MailItem getMailItem() {
        return new MailItem(subject, sender, date, body);
    }

}

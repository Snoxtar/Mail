package mailApp;

import javafx.application.Application;
import javafx.stage.Stage;
import mailApp.gui.Gui;
import mailApp.repository.JPARepository;

@SuppressWarnings("restriction")
public class MailApp extends Application {
	static String user = "mailLogin";
	static String password = "yourPassword";
	static String imapHost = "imapHostOfYourMail";
	static String smtpHost = "smtpHostOfYourMail";
	public static JPARepository repo = new JPARepository();

	public static void main(String[] args) {

	CheckMail.check(); // after 1st usage it saves all recent mails from inbox to database (MariaDB)

		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		new Gui(primaryStage);

	}

}

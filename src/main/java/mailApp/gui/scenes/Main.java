package mailApp.gui.scenes;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import mailApp.MailApp;
import mailApp.MailItem;
import mailApp.repository.JPARepository;

@SuppressWarnings("restriction")
public class Main extends Scene {

	private static GridPane root = new GridPane();

	public Main() {
		super(root, 600, 400);
		createView();
	}

	private void createView() {
		Label msgListLabel = new Label("Lista wiadmosci");
		root.add(msgListLabel, 1, 0);
		TextArea msgText = new TextArea();
		msgText.setEditable(false);
		root.add(msgText, 1, 2);
		ListView<String> msgList = new ListView<String>();

		JPARepository repo = MailApp.repo;
		MailItem[] mails = repo.getAll();

		ObservableList<String> OLMsgs = FXCollections.observableArrayList();
		for (int i = 0; i < mails.length; i++) {
			OLMsgs.add(mails[i].getSubject() + ", Od: " + mails[i].getSender() + ", Data: " + mails[i].getDate());
		}

		msgList.setItems(OLMsgs);
		
//		msgList.setOnMouseClicked(new EventHandler<MouseEvent>() {		// klasa anonimowa
//				@Override
//				public void handle(MouseEvent event) {
//				}
//			});
		
		root.add(msgList, 1, 1);

		Button btnNew = new Button("Nowa wiadomość");
		btnNew.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				NewMail newmail = new NewMail();
				newmail.createWindow();
			}
		});

		root.add(btnNew, 1, 3);
	}

}

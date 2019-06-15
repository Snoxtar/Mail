package mailApp.gui.scenes;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import mailApp.SendMail;

public class NewMail {
	private static GridPane root = new GridPane();

	public NewMail() {

	}
	
	public void createWindow() {
		Scene scene = new Scene(root, 600, 400);
		Stage stage = new Stage();
		stage.setScene(scene);

		TextField to = new TextField();
		TextField subject = new TextField();
		TextArea body = new TextArea();
		Button btnSend = new Button("Wyślij");
		Label lblTo = new Label("Do:");
		Label lblSubject = new Label("Temat:");
		lblSubject.setMinWidth(50);
		root.add(lblTo, 0, 0);
		root.add(lblSubject, 0, 1);
		root.add(to, 1, 0);
		root.add(subject, 1, 1);
		root.add(body, 1, 2);
		root.add(btnSend, 1, 3);

		btnSend.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				SendMail.send(to.getText(), subject.getText(), body.getText());
				stage.close();
			}
		});

		stage.show();
	}
	
	

}

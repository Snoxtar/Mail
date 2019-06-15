package mailApp.gui;

import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import mailApp.gui.scenes.Main;

public class Gui {

	public Gui(Stage stage) {

		stage.setOnCloseRequest(new EventHandler<WindowEvent>() {

			@Override
			public void handle(WindowEvent event) {
				stage.close();
				System.exit(0);
			}
		});
		stage.setTitle("Program pocztowy");
		stage.setScene(new Main());
		stage.centerOnScreen();
		stage.show();
	}

}

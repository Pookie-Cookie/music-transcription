package main;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class Menu_Controller {
	private Button closeButton;
	
	public void closeButtonOnAction(ActionEvent event) {
		Stage stage = (Stage) closeButton.getScene().getWindow();
		stage.close();
	}
}

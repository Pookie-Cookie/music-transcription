package main;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Transcribe_controller implements Initializable{
	
	public void logOutButton(ActionEvent event) throws IOException{
		Parent MenuParent = FXMLLoader.load(getClass().getResource("Menu.fxml"));
		Scene MenuScene = new Scene(MenuParent);
		
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		window.setScene(MenuScene);
		window.show();
	}
	
	public void choosefile(ActionEvent event) throws IOException{
		File file = new File ("");
		Desktop desktop = Desktop.getDesktop();
		desktop.open(file);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

}

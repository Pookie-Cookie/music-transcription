package main;
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

import java.awt.Desktop;
import java.net.MalformedURLException;
import java.net.URISyntaxException;



public class Menu_Controller implements Initializable {
	
	public void closeButtonOnAction(ActionEvent event) throws IOException {
		Parent SignUpParent = FXMLLoader.load(getClass().getResource("Sign_Up.fxml"));
		Scene SignUpScene = new Scene(SignUpParent);
		
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		window.setScene(SignUpScene);
		window.show();
	}
	
	public void logInButton(ActionEvent event) throws IOException {
		Parent TranscribeParent = FXMLLoader.load(getClass().getResource("Transcribe_tab.fxml"));
		Scene TranscribeScene = new Scene(TranscribeParent);
		
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		window.setScene(TranscribeScene);
		window.show();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
}


//System.out.println("Hello");
//Desktop d = Desktop.getDesktop();
//try {
//	d.browse(new URL("https://www.youtube.com/watch?v=wpV-gGA4PSk").toURI());
//} catch (MalformedURLException e) {
//	// TODO Auto-generated catch block
//	e.printStackTrace();
//} catch (IOException e) {
//	// TODO Auto-generated catch block
//	e.printStackTrace();
//} catch (URISyntaxException e) {
//	// TODO Auto-generated catch block
//	e.printStackTrace();
//}
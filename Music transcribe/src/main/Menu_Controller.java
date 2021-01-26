package main;
import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.awt.Desktop;
import java.net.MalformedURLException;
import java.net.URISyntaxException;



public class Menu_Controller implements Initializable {
	MongoClient mongoClient = Main.mongo;
	DBCollection coll = Main.mongocoll;
	
	@FXML
	private TextField userEmailBox;
	@FXML
	private TextField passwordBox;
	
	
	public void signUpButton(ActionEvent event) throws IOException {
		Parent SignUpParent = FXMLLoader.load(getClass().getResource("Sign_Up.fxml"));
		Scene SignUpScene = new Scene(SignUpParent);
		
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		window.setScene(SignUpScene);
		window.show();
		window.initStyle(StageStyle.DECORATED);
	}
	
	public void quitButton(ActionEvent event) throws IOException {	
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		window.close();
	}
	
	public void logInButton(ActionEvent event) throws IOException, NoSuchAlgorithmException {
		DBCursor fi = coll.find();
		String pass_hash = Helpers.SHA_Hash.toHexString(Helpers.SHA_Hash.getSHA(passwordBox.getText()));
		boolean found = false;
		while(fi.hasNext()) {
			DBObject obj = fi.next();
			String Username = (String) obj.get("username");
			String email = (String) obj.get("email");
			String Password = (String) obj.get("password");
			if(userEmailBox.getText().equals(Username) || userEmailBox.getText().equals(email)) {
				if(pass_hash.equals(Password)) {
					found = true;
					Parent TranscribeParent = FXMLLoader.load(getClass().getResource("Transcribe_tab.fxml"));
					Scene TranscribeScene = new Scene(TranscribeParent);
					
					Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
					window.close();
					Stage newWindow = new Stage();
					newWindow.setScene(TranscribeScene);
					newWindow.getIcons().add(new Image("Images/icon.png"));
					newWindow.show();
				}
				else {
					JOptionPane.showMessageDialog(null, "incorrect username or password" );
				}
			}
		}
		if(found == false) {
			JOptionPane.showMessageDialog(null, "User does not exist" );
		}
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
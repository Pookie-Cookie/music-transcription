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
import javafx.stage.Stage;

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
	
	public void closeButtonOnAction(ActionEvent event) throws IOException {
		Parent SignUpParent = FXMLLoader.load(getClass().getResource("Sign_Up.fxml"));
		Scene SignUpScene = new Scene(SignUpParent);
		
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		window.setScene(SignUpScene);
		window.show();
	}
	
	public void logInButton(ActionEvent event) throws IOException, NoSuchAlgorithmException {
		DBCursor fi = coll.find();
		String pass_hash = Helpers.SHA_Hash.toHexString(Helpers.SHA_Hash.getSHA(passwordBox.getText()));
		while(fi.hasNext()) {
			DBObject obj = fi.next();
			String Username = (String) obj.get("username");
			String email = (String) obj.get("email");
			String Password = (String) obj.get("password");
			if(userEmailBox.getText().equals(Username) || userEmailBox.getText().equals(email)) {
				if(pass_hash.equals(Password)) {
					Parent TranscribeParent = FXMLLoader.load(getClass().getResource("Transcribe_tab.fxml"));
					Scene TranscribeScene = new Scene(TranscribeParent);
					
					Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
					window.setScene(TranscribeScene);
					window.show();
				}
				else {
					JOptionPane.showMessageDialog(null, "incorrect username or password" );
				}
			}
			else {
				JOptionPane.showMessageDialog(null, "This username or email does not exist in our system" );
			}
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
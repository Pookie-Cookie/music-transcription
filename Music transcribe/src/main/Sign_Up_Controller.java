package main;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.swing.JOptionPane;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Sign_Up_Controller {
	MongoClient mongoClient = Main.mongo;
	DBCollection coll = Main.mongocoll;
	
	@FXML
	private TextField usernameBox;
	@FXML 
	private TextField emailBox;
	@FXML
	private TextField passwordBox;
	@FXML
	private TextField passConfirmBox;
	
	
	
	
	public void sign_up_button(ActionEvent event) throws IOException, NoSuchAlgorithmException {
		if(!usernameBox.getText().isBlank() && !emailBox.getText().isBlank() && !passwordBox.getText().isBlank() && !passConfirmBox.getText().isBlank()){
			DBCursor fi = coll.find();
			String pass_hash = Helpers.SHA_Hash.toHexString(Helpers.SHA_Hash.getSHA(passwordBox.getText()));
			while(fi.hasNext()) {
				DBObject obj = fi.next();
				String Username = (String) obj.get("username");
				String email = (String) obj.get("email");
				if (usernameBox.getText().equals(Username)) {
					System.out.println("found");
					JOptionPane.showMessageDialog(null, "Username already exists" );
				}
				else if (emailBox.getText().equals(email)) {
					System.out.println("found1");
					JOptionPane.showMessageDialog(null, "Email already exists in system");
				}
			}
			if (!emailBox.getText().contains("@") || !emailBox.getText().contains("@")) {
				JOptionPane.showMessageDialog(null, "Please enter a valid email address");
			}
			else if (usernameBox.getText() == "" || passwordBox.getText() == "" || emailBox.getText() == "" || passConfirmBox.getText() == "" ) {
				JOptionPane.showMessageDialog(null, "Please fill out all fields");
			}
			else if (!passwordBox.getText().equals(passConfirmBox.getText())){
				JOptionPane.showMessageDialog(null, "The passwords do not match!");
			}
			else {
				BasicDBObject doc = new BasicDBObject();
				doc.append("username", usernameBox.getText());
				doc.append("password", pass_hash);
				doc.append("email", emailBox.getText());
				coll.insert(doc);
				
				Parent TranscribeParent = FXMLLoader.load(getClass().getResource("Transcribe_tab.fxml"));
				Scene TranscribeScene = new Scene(TranscribeParent);
				
				Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
				window.setScene(TranscribeScene);
				window.show();
			}
		}
		else {
			JOptionPane.showMessageDialog(null, "One of the boxes are empty, please check your input again!");
		}
	}
}

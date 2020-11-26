package main;

import javax.swing.JOptionPane;

import org.bson.Document;

import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class Sign_Up_Controller {
	MongoClient mongoClient = Main.mongo;
	DBCollection coll = Main.mongocoll;
	
	@FXML
	private TextField usernameBox;
	@FXML 
	private TextField emailBox;
	
	public void sign_up_button(ActionEvent event) {
		DBCursor fi = coll.find();
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
	}
}

package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.DBCursor;
import com.mongodb.Mongo;

import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.DBObject;


public class Main extends Application{

	
	@Override
	public void start(Stage stage) throws Exception{
		Parent root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			MongoClient mongoClient = new MongoClient("localhost", 27017);
			DB db = mongoClient.getDB("Music-transcription-DB");
			System.out.println("Connection established");
			
		}
		catch (UnknownHostException e){
			e.printStackTrace();
		}
		launch(args);
	}

}

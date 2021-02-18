package main;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;





public class Main extends Application{
	public static MongoClient mongo;
	public static DBCollection mongocoll;
	
	@Override
	public void start(Stage stage) throws Exception{
		
		Parent root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.initStyle(StageStyle.UNDECORATED);
		stage.getIcons().add(new Image("Images/icon.png"));
		stage.show();
	}
	
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		MongoClient mongoClient = new MongoClient("localhost", 27017);
		DB db = mongoClient.getDB("Music-transcription-DB");
		DBCollection coll = db.getCollection("music");
		mongo = mongoClient;
		mongocoll = coll;
		launch(args);
		mongoClient.close();
	}
	
	
}

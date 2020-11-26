package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.DBCursor;

import java.net.UnknownHostException;

import org.bson.BsonObjectId;
import org.bson.types.ObjectId;
import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;




public class Main extends Application{
	public static MongoClient mongo;
	public static DBCollection mongocoll;
	
	@Override
	public void start(Stage stage) throws Exception{
		
		Parent root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
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

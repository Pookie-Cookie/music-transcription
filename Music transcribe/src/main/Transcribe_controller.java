package main;
import java.awt.FileDialog;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import javax.swing.JFrame;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.PrintWriter;


public class Transcribe_controller{
	
	public void logOutButton(ActionEvent event) throws IOException{
		Parent MenuParent = FXMLLoader.load(getClass().getResource("Menu.fxml"));
		Scene MenuScene = new Scene(MenuParent);
		
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		window.setScene(MenuScene);
		window.show();
	}
	
	
	
	public void uploadFile(ActionEvent event) throws IOException{
		FileDialog fd = new FileDialog(new JFrame());
		fd.setVisible(true);
		File[] f = fd.getFiles();
		if(f.length > 0){
		    System.out.println(fd.getFiles()[0].getAbsolutePath());
		}
		String filePath = fd.getFiles()[0].getAbsolutePath();
		
		File upload = new File(filePath);
		
		Thread newThread = new Thread(() -> {
			try {
				transcribe(filePath);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		newThread.start();
	}
		
		


	public void transcribe(String filePath) throws IOException{
		String urlParameters = "access_id=ff2092da-30d6-4ab3-b2eb-a1bd423f60a9&input_file=http://www.sonicAPI.com/music/brown_eyes_by_ueberschall.mp3";
//		String urlParameters = "access_id=ff2092da-30d6-4ab3-b2eb-a1bd423f60a9&input_file="+filePath;
	    URL url = new URL("https://api.sonicAPI.com/analyze/melody");
	    URLConnection conn = url.openConnection();
	
	    conn.setDoOutput(true);
	
	    OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
	
	    writer.write(urlParameters);
	    writer.flush();
	
	    String line;
	    BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	
	    FileWriter myFile = new FileWriter("output.txt");
	    
	    while ((line = reader.readLine()) != null) {
	        System.out.println(line);
	        myFile.write(line + "\n");
	    }
	    writer.close();
	    reader.close(); 
	}
}

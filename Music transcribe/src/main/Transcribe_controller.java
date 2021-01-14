package main;
import java.awt.FileDialog;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import java.io.*;

import java.io.FileWriter;
import java.io.PrintWriter;


public class Transcribe_controller{
	public HashMap<Integer, String> pitchDict = new HashMap<Integer, String>();
	pitchDict.put(21, "a,,,");
	pitchDict.put(22, "ais,,,");
	pitchDict.put(23, "b,,,");
	pitchDict.put(24, "c,,");
	pitchDict.put(25, "cis,,");
	pitchDict.put(26, "d,,");
	pitchDict.put(27, "dis,,");
	pitchDict.put(28, "e,,");
	pitchDict.put(29, "f,,");
	pitchDict.put(30, "fis,,");
	pitchDict.put(31, "g,,");
	pitchDict.put(32, "gis,,");
	pitchDict.put(33, "a,,");
	pitchDict.put(34, "ais,,");
	pitchDict.put(35, "b,,");
	pitchDict.put(36, "c,");
	pitchDict.put(37, "cis,");
	pitchDict.put(38, "d,");
	pitchDict.put(39, "dis,");
	pitchDict.put(40, "e,");
	pitchDict.put(41, "f,");
	pitchDict.put(42, "fis,");
	pitchDict.put(43, "g,");
	pitchDict.put(44, "gis,");
	pitchDict.put(45, "a,");
	pitchDict.put(46, "ais,");
	pitchDict.put(47, "b,");
	pitchDict.put(48, "c");
	pitchDict.put(49, "cis");
	pitchDict.put(50, "d");
	pitchDict.put(51, "dis");
	pitchDict.put(52, "e");
	pitchDict.put(53, "f");
	pitchDict.put(54, "fis");
	pitchDict.put(55, "g");
	pitchDict.put(56, "gis");
	pitchDict.put(57, "a");
	pitchDict.put(58, "ais");
	pitchDict.put(59, "b");
	pitchDict.put(60, "c'");
	pitchDict.put(61, "cis'");
	pitchDict.put(62, "d'");
	pitchDict.put(63, "dis'");
	pitchDict.put(64, "e'");
	pitchDict.put(65, "f'");
	pitchDict.put(66, "fis'");
	pitchDict.put(67, "g'");
	pitchDict.put(68, "gis'");
	pitchDict.put(69, "a'");
	pitchDict.put(70, "ais'");
	pitchDict.put(71, "b'");
	pitchDict.put(72, "c''");
	pitchDict.put(73, "cis''");
	pitchDict.put(74, "d''");
	pitchDict.put(75, "dis''");
	pitchDict.put(76, "e''");
	pitchDict.put(77, "f''");
	pitchDict.put(78, "fis''");
	pitchDict.put(79, "g''");
	pitchDict.put(80, "gis''");
	pitchDict.put(81, "a''");
	pitchDict.put(82, "ais''");
	pitchDict.put(83, "b''");
	pitchDict.put(84, "c'''");
	pitchDict.put(85, "cis'''");
	pitchDict.put(86, "d'''");
	pitchDict.put(87, "dis'''");
	pitchDict.put(88, "e'''");
	pitchDict.put(89, "f'''");
	pitchDict.put(90, "fis'''");
	pitchDict.put(91, "g'''");
	pitchDict.put(92, "gis'''");
	pitchDict.put(93, "a'''");
	pitchDict.put(94, "ais'''");
	pitchDict.put(95, "b'''");
	pitchDict.put(96, "c''''");
	pitchDict.put(97, "cis''''");
	pitchDict.put(98, "d''''");
	pitchDict.put(99, "dis''''");
	pitchDict.put(100, "e''''");
	pitchDict.put(101, "f''''");
	pitchDict.put(102, "fis''''");
	pitchDict.put(103, "g''''");
	pitchDict.put(104, "gis''''");
	pitchDict.put(105, "a''''");
	pitchDict.put(106, "ais''''");
	pitchDict.put(107, "b''''");
	pitchDict.put(108, "c''''");
	

	
	try {
		FileWriter NewFile = new FileWriter("autolytest.ly");
		NewFile.write("version");
		for (int i=0; i<20; i++) {
			NewFile.write("potat");
		}
	}
	catch(IOException e) {
		System.out.println("An error occurred.");
		e.printStackTrace();
	}
	NewFile.close();
	public ArrayList<Integer> pitches = new ArrayList<Integer>();
	
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
			} catch (IOException | ParserConfigurationException | SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		newThread.start();
	}
		

	public void xmlReader(File xmlFile) throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse(xmlFile);
		document.getDocumentElement().normalize();
		Element root = document.getDocumentElement();
		System.out.println(root.getNodeName());
		NodeList nList = document.getElementsByTagName("note");
		
		for (int i = 0; i < nList.getLength(); i++) {
			org.w3c.dom.Node node = nList.item(i);
			Element element = (Element) node;
			int potat = Integer.parseInt(element.getAttribute("midi_pitch"));
			pitches.add(potat);
			System.out.println(element.getAttribute("midi_pitch"));
		}
	}

	public void transcribe(String filePath) throws IOException, ParserConfigurationException, SAXException{
		String urlParameters = "access_id=&input_file=http://www.sonicAPI.com/music/brown_eyes_by_ueberschall.mp3";
//		String urlParameters = "access_id=&input_file="+filePath;
	    URL url = new URL("https://api.sonicAPI.com/analyze/melody");
//	    URL url = new URL("https://api.sonicapi.com/file/upload?");
	    URLConnection conn = url.openConnection();
	
	    conn.setDoOutput(true);
	
	    OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
	
	    writer.write(urlParameters);
	    writer.flush();
	
	    String line;
	    BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	    FileWriter myFile = new FileWriter("output.xml");
	    while ((line = reader.readLine()) != null) {
	        System.out.println(line);
	        if (!line.contains("response")) {
	        	myFile.write(line+"\n");
	        }
	        else {
	        	myFile.write(line);
	        }
	    }
	    myFile.close();
	    writer.close();
	    reader.close(); 
	    File myfile = new File("output.xml");
	    xmlReader(myfile);
	    
	    
	    
	}
}

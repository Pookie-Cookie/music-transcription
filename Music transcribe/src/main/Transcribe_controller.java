package main;

import java.awt.Desktop;
import java.awt.FileDialog;
//import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.lang.Math;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.io.File;
import javafx.scene.media.*;
import javafx.scene.text.TextAlignment;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import java.io.*;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class Transcribe_controller{
	public ArrayList<Integer> pitches = new ArrayList<Integer>();
	public HashMap<Integer, String> pitchDict = new HashMap<Integer, String>();
	public HashMap<String, String> keyDict = new HashMap<String, String>();
	private static MediaPlayer player;
	private String file = null;
	
	@FXML
	private Label fileLabel;
	@FXML
	private TextField beginTime;
	@FXML
	private TextField endTime;
	
	
	public void addKey() {
		keyDict.put("C Maj", "c \\major");
		keyDict.put("C Min", "c \\minor");
		keyDict.put("C# Maj", "cis \\major");
		keyDict.put("C# Min", "cis \\minor");
		keyDict.put("D Maj", "d \\major");
		keyDict.put("D Min", "d \\minor");
		keyDict.put("D# Maj", "dis \\major");
		keyDict.put("D# Min", "dis \\minor");
		keyDict.put("E Maj", "e \\major");
		keyDict.put("E Min", "e \\minor");
		keyDict.put("F Maj", "f \\major");
		keyDict.put("F Min", "f \\minor");
		keyDict.put("F# Maj", "fis \\major");
		keyDict.put("F# Min", "fis \\minor");
		keyDict.put("G Maj", "g \\major");
		keyDict.put("G Min", "g \\minor");
		keyDict.put("G# Maj", "g# \\major");
		keyDict.put("G# Min", "g# \\minor");
		keyDict.put("A Maj", "a \\major");
		keyDict.put("A Min", "a \\minor");
		keyDict.put("A# Maj", "ais \\major");
		keyDict.put("A# Min", "ais \\minor");
		keyDict.put("B Major", "b \\major");
		keyDict.put("B Min", "b \\minor");
	}
	
	public void addPitch() {
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
	}

	public void quitButton(ActionEvent event) throws IOException {	
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		window.close();
	}
	 
	public void logOutButton(ActionEvent event) throws IOException{
		Parent MenuParent = FXMLLoader.load(getClass().getResource("Menu.fxml"));
		Scene MenuScene = new Scene(MenuParent);
		
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		window.close();
		Stage stage = new Stage();
		stage.initStyle(StageStyle.UNDECORATED);
		stage.getIcons().add(new Image("Images/icon.png"));
		stage.setScene(MenuScene);
		stage.show();
	}
	
	public void previewButton(ActionEvent event) {
		if (file == null) {
			JOptionPane.showMessageDialog(null, "Please choose a file!");
		}
		else {
			String path = file;
			Media media = new Media(new File(path).toURI().toString());
		    player = new MediaPlayer(media);
		    player.play();
		}
	}
	
	public void uploadFile(ActionEvent event) throws IOException{
		addPitch();
		addKey();
		FileDialog fd = new FileDialog(new JFrame());
		fd.setVisible(true);
		File[] f = fd.getFiles();
		if(f.length > 0){
		    System.out.println(fd.getFiles()[0].getAbsolutePath());
		}
		else {
			return;
		}
		String filePath = fd.getFiles()[0].getAbsolutePath();
		
		file = filePath;
		fileLabel.setTextAlignment(TextAlignment.CENTER);
		fileLabel.setText(f[0].getName());
	}
		
	public String xmlReader(File xmlFile) throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse(xmlFile);
		document.getDocumentElement().normalize();
		NodeList nList = document.getElementsByTagName("note");
		
		for (int i = 0; i < nList.getLength(); i++) {
			org.w3c.dom.Node node = nList.item(i);
			Element element = (Element) node;
			float potat = Float.parseFloat(element.getAttribute("midi_pitch"));
			pitches.add(Math.round(potat));
		}
		
		NodeList nlist = document.getElementsByTagName("melody_result");
		org.w3c.dom.Node Node = nlist.item(0);
		Element element = (Element) Node;
		System.out.println(element.getAttribute("key"));
		return(element.getAttribute("key"));
	}

	public void transcribe(ActionEvent event) throws IOException, ParserConfigurationException, ParseException, SAXException{
		if (file == null) {
			JOptionPane.showMessageDialog(null, "Please choose a file!");
			return;
		}
		Thread newThread = new Thread(() -> {
			try {
				CloseableHttpClient httpClient = HttpClients.createDefault();
			    HttpPost uploadFile = new HttpPost("https://api.sonicAPI.com/analyze/melody?");
			    MultipartEntityBuilder builder = MultipartEntityBuilder.create();
			    builder.addTextBody("access_id", "ff2092da-30d6-4ab3-b2eb-a1bd423f60a9", ContentType.TEXT_PLAIN);
			    
			    // This attaches the file to the POST:
			    File f = new File(file);
			    builder.addBinaryBody(
			        "input_file",
			        new FileInputStream(f),
			        ContentType.APPLICATION_OCTET_STREAM,
			        f.getName()
			    );
			    
			    String fileName = f.getName();
			    
			    builder.addTextBody("begin_seconds", beginTime.getText());
			    builder.addTextBody("end_seconds", endTime.getText());
			    
			    HttpEntity multipart = builder.build();
			    uploadFile.setEntity(multipart);
			    CloseableHttpResponse response = httpClient.execute(uploadFile);
			    HttpEntity responseEntity = response.getEntity();
			    int code = response.getStatusLine().getStatusCode();
			    
			    if (code >= 200 && code <= 299) {
			    	String result = EntityUtils.toString(responseEntity);
				    FileWriter myFile = new FileWriter("output.xml");
				    myFile.write(result);
				    File myfile = new File("output.xml");
				    myFile.close();
				    String key = xmlReader(myfile);
				    
				    
				    try {
						FileWriter NewFile = new FileWriter(".ly");
						NewFile.write("\\header { \ntitle = \""+fileName+"\" \n} \n");
						NewFile.write("\\version \"2.20.0\"{ \n");
						double average = 0;
						//get average pitch
						for (int i=1; i<pitches.size(); i++) {
							average = average+pitches.get(i);
						}
						//write with the correct clef
						if (average/pitches.size() < 47) {
							NewFile.write("\\clef bass \n");
						}
						else {
							NewFile.write("\\clef treble \n");
						}
						//write key signature
						NewFile.write("\\key "+keyDict.get(key)+"\n");
						//write the rest of the notes
						for (int i=0; i<pitches.size(); i++) {
							NewFile.write(pitchDict.get(pitches.get(i)) + " ");
						}
						NewFile.write("\n}");
						NewFile.close();
					}
					catch(IOException e) {
						System.out.println("An error occurred.");
						e.printStackTrace();
					}
				    
				    try {
				    	String[] params = new String[2];
				    	params[0] = "LilyPond\\usr\\bin\\lilypond.exe";
				    	params[1] = ".ly";
				    	Runtime.getRuntime().exec(params);
				    }
				    catch (IOException ex) {
				    	ex.printStackTrace();
				    }
				    
				    try {
				    	File File = new File("..\\Music transcribe.pdf");
				    	Desktop.getDesktop().open(File);
				    }
				    catch (IOException ex){
				    	ex.printStackTrace();
				    }
			    }
			    else if (code == 400) {
			    	JOptionPane.showMessageDialog(null, "The file chosen does not appear to be a valid file, please choose a mp3 or WAV file." );
			    }
			    file = null;
			}
			catch(IOException | ParserConfigurationException | SAXException e) {
				e.printStackTrace();
			}
		});
		newThread.start();
		fileLabel.setText("");
	}
}


package main;
import java.awt.FileDialog;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;
import java.util.UUID;
import java.lang.Math;
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
import org.apache.hc.*;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.entity.mime.MultipartEntityBuilder;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.HttpResponse;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;


public class Transcribe_controller{
	public ArrayList<Integer> pitches = new ArrayList<Integer>();
	public HashMap<Integer, String> pitchDict = new HashMap<Integer, String>();
	public HashMap<String, String> keyDict = new HashMap<>();
	
	public void addKey() {
		keyDict.put("c maj", "c \\major");
		keyDict.put("c min", "c \\minor");
		keyDict.put("c# maj", "cis \\major");
		keyDict.put("c# min", "cis \\minor");
		keyDict.put("d maj", "d \\major");
		keyDict.put("d min", "d \\minor");
		keyDict.put("d# maj", "dis \\major");
		keyDict.put("d# min", "dis \\minor");
		keyDict.put("e maj", "e \\major");
		keyDict.put("e min", "e \\minor");
		keyDict.put("f maj", "f \\major");
		keyDict.put("f min", "f \\minor");
		keyDict.put("f# maj", "fis \\major");
		keyDict.put("f# min", "fis \\minor");
		keyDict.put("g maj", "g \\major");
		keyDict.put("g min", "g \\minor");
		keyDict.put("g# maj", "g# \\major");
		keyDict.put("g# min", "g# \\minor");
		keyDict.put("a maj", "a \\major");
		keyDict.put("a min", "a \\minor");
		keyDict.put("a# maj", "ais \\major");
		keyDict.put("a# min", "ais \\minor");
		keyDict.put("b major", "b \\major");
		keyDict.put("b min", "b \\minor");
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
	

	private void sendFile(OutputStream out, String name, InputStream in, String fileName) throws IOException {
	    String o = "Content-Disposition: form-data; name=\"" + URLEncoder.encode(name,"UTF-8") 
	             + "\"; filename=\"" + URLEncoder.encode(fileName,"UTF-8") + "\"\r\n\r\n";
	    out.write(o.getBytes(StandardCharsets.UTF_8));
	    byte[] buffer = new byte[2048];
	    for (int n = 0; n >= 0; n = in.read(buffer))
	        out.write(buffer, 0, n);
	    out.write("\r\n".getBytes(StandardCharsets.UTF_8));
	}

	private void sendField(OutputStream out, String name, String field) throws IOException {
	    String o = "Content-Disposition: form-data; name=\"" 
	             + URLEncoder.encode(name,"UTF-8") + "\"\r\n\r\n";
	    out.write(o.getBytes(StandardCharsets.UTF_8));
	    out.write(URLEncoder.encode(field,"UTF-8").getBytes(StandardCharsets.UTF_8));
	    out.write("\r\n".getBytes(StandardCharsets.UTF_8));
	}
	
	public void lilypond_test() {
		try {
			FileWriter NewFile = new FileWriter("autolytest.ly");
			NewFile.write("version");
			NewFile.write("{");
			for (int i=0; i<20; i++) {
				NewFile.write(pitchDict.get(77));
			}
			NewFile.write("}");
			NewFile.close();
		}
		catch(IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}
	
	
	 
	public void logOutButton(ActionEvent event) throws IOException{
		Parent MenuParent = FXMLLoader.load(getClass().getResource("Menu.fxml"));
		Scene MenuScene = new Scene(MenuParent);
		
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		window.setScene(MenuScene);
		window.show();
	}
	
	
	
	public void uploadFile(ActionEvent event) throws IOException{
		addPitch();
		lilypond_test();
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
		
		File upload = new File(filePath);
		
		Thread newThread = new Thread(() -> {
			try {
				transcribe(filePath);
			} catch (IOException | ParserConfigurationException | SAXException | ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		newThread.start();
	}
		

	public String xmlReader(File xmlFile) throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse(xmlFile);
		document.getDocumentElement().normalize();
		Element root = document.getDocumentElement();
//		System.out.println(root.getNodeName());
		NodeList nList = document.getElementsByTagName("note");
		
		for (int i = 0; i < nList.getLength(); i++) {
			org.w3c.dom.Node node = nList.item(i);
			Element element = (Element) node;
			float potat = Float.parseFloat(element.getAttribute("midi_pitch"));
			pitches.add(Math.round(potat));
//			System.out.println(element.getAttribute("midi_pitch"));
		}
		
		NodeList nlist = document.getElementsByTagName("melody_result");
		org.w3c.dom.Node Node = nlist.item(0);
		Element element = (Element) Node;
//		System.out.println(element.getAttribute("key"));
		return(element.getAttribute("key"));
	}

	public void transcribe(String filePath) throws IOException, ParserConfigurationException, SAXException, ParseException{
//		String urlParameters = "access_id=ff2092da-30d6-4ab3-b2eb-a1bd423f60a9&input_file=D:\\Java code\\music-transcription\\Music transcribe\\src\\main\\Bruh Sound Effect 2.mp3";
//		String urlParameters = "access_id=ff2092da-30d6-4ab3-b2eb-a1bd423f60a9&input_file="+filePath;
	    URL url = new URL("https://api.sonicAPI.com/analyze/melody?");
	    
	    
	    CloseableHttpClient httpClient = HttpClients.createDefault();
	    HttpPost uploadFile = new HttpPost("https://api.sonicAPI.com/analyze/melody?");
	    MultipartEntityBuilder builder = MultipartEntityBuilder.create();
	    builder.addTextBody("access_id", "ff2092da-30d6-4ab3-b2eb-a1bd423f60a9", ContentType.TEXT_PLAIN);

	    // This attaches the file to the POST:
	    File f = new File(filePath);
	    builder.addBinaryBody(
	        "input_file",
	        new FileInputStream(f),
	        ContentType.APPLICATION_OCTET_STREAM,
	        f.getName()
	    );

	    HttpEntity multipart = builder.build();
	    uploadFile.setEntity(multipart);
	    CloseableHttpResponse response = httpClient.execute(uploadFile);
	    HttpEntity responseEntity = response.getEntity();
	    
	    String result = EntityUtils.toString(responseEntity);
	    System.out.println(result);
	    
//	    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
//        DocumentBuilder db = dbf.newDocumentBuilder();
//        if (responseEntity != null) {
//            InputStream inputStream = responseEntity.getContent();
//            Document doc = db.parse(inputStream);
//            doc.getDocumentElement().normalize(); 
//        System.out.println(doc);
	    
	
//	    String line;
//	    BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	    FileWriter myFile = new FileWriter("output.xml");
	    myFile.write(result);
//	    while ((result = result.readLine()) != null) {
//	        System.out.println(line);
//	        if (!line.contains("response")) {
//	        	myFile.write(line+"\n");
//	        }
//	        else {
//	        	myFile.write(line);
//	        }
//	    }
	    myFile.close();
//	    reader.close();
		
	    File myfile = new File("output.xml");
	    String key = xmlReader(myfile);
	    
	    try {
			FileWriter NewFile = new FileWriter(".ly");
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
			NewFile.write("\\key"+keyDict.get(key)+"\n");
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
	}
}


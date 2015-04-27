package edu.udel.cisc275_15S.evaluate;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.badlogic.gdx.utils.XmlWriter;

public class XMLWriter {
	private final static Charset ENCODING = StandardCharsets.UTF_8;
	
	//Singleton XMLWriter- only one reader necessary in game
	private static final XMLWriter INSTANCE = new XMLWriter();
	private XMLWriter() {}
	public static XMLWriter getInstance(){
		return INSTANCE;
	}

	public void writeFile(int episode){
		String ename = getEpisode(episode);
		String path = ename + "_eval.xml";
		StringWriter writer = new StringWriter();
		XmlWriter xml = new XmlWriter(writer);
		
		try {
			xml.element(ename)// root element is episode
				.element("child")
				.attribute("moo", "cow")
				.element("child")
					.attribute("moo", "cow")
					.text("XML is like violence. If it doesn't solve your problem, you're not using enough of it.")
				.pop()
			.pop()
 .pop();
			xml.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try (BufferedWriter w = new BufferedWriter(new FileWriter(path, true))){
			w.write(writer.toString());   
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// @code id should represent a number between 0 and 5 inclusive
	private String getEpisode(int id){
		if(id < 0 || id > 5){
			throw new IllegalArgumentException();
		}
		String episode = "";
		switch(id){
		case 1: episode = "Dorm";
				break;
		case 2: episode = "Academic";
				break;
		case 3: episode = "Library";
				break;
		case 4: episode = "Student Health";
				break;
		case 5: episode = "Advisement";
				break;
		case 6: episode = "Career Services";
				break;
		}
		return episode;
	}
}

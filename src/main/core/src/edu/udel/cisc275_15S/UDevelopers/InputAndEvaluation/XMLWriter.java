package edu.udel.cisc275_15S.UDevelopers.InputAndEvaluation;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;

import com.badlogic.gdx.utils.XmlWriter;

public class XMLWriter {
	ArrayList<Record> records = new ArrayList<Record>();
	
	//Singleton XMLWriter- only one reader necessary in game
	private static final XMLWriter INSTANCE = new XMLWriter();
	private XMLWriter() {}
	public static XMLWriter getInstance(){
		return INSTANCE;
	}

	//Write single file of information for each episode played
	//@code episode identifies which episode user is in
	//@code take identifies which "take" this is; has the user done this more than once?
	public void writeFile(ArrayList<Record> rlist, int episode, int take){
		records = rlist;
		String ename = getEpisode(episode);
		String t = "" + take;
		String path = ename + t + "_eval.xml";
		StringWriter writer = new StringWriter();
		XmlWriter xml = new XmlWriter(writer);
		
		try {
			XmlWriter root = xml.element(ename);// root element is episode
			for(Record r : records){
				root.element("Question").text(r.getQuestion()).pop()
				.element("Answer").text(r.getAnswer()).pop()
				.element("Result").text(r.getResult()).pop()
				.element("Try").text(r.getTake()).pop();
			}
			xml.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try (BufferedWriter w = new BufferedWriter(new FileWriter(path, true))){
			w.write(writer.toString());   
		} catch (IOException e) {
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
		case 0: episode = "Dorm";
				break;
		case 1: episode = "Academic";
				break;
		case 2: episode = "Morris Library";
				break;
		case 3: episode = "Student Health";
				break;
		case 4: episode = "Advisement";
				break;
		case 5: episode = "Career Services";
				break;
		}
		return episode;
	}
	
	//Write master file of information for client
	public void writeMaster(){
		
	}
}
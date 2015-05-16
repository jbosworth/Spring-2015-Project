package edu.udel.cisc275_15S.UDevelopers.InputAndEvaluation;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;

import com.badlogic.gdx.utils.XmlWriter;

public class XMLWriter {
	private HashMap<String, ArrayList<Record>> records = new HashMap<String, ArrayList<Record>>();
	private ArrayList<String> validNames = new ArrayList<String>();
	
	//Singleton XMLWriter- only one reader necessary in game
	private static final XMLWriter INSTANCE = new XMLWriter();
	private XMLWriter() {}
	public static XMLWriter getInstance(){
		return INSTANCE;
	}
	
	//Episode names: Academic, Advisement, Career, Dorm, Library, Student_Health
	public void addRecord(String ename, Record r){
		ArrayList<Record> rlist = records.get(ename);
		rlist.add(r);
		records.put(ename, rlist);
	}
	
	//Called at end of game; writes Q&A results to an XML file
	public void writeFile(){
		String data = ""; //String to be written
		String path = "Evaluation.xml"; //File name to write to
		
		//initialize
		BufferedWriter w = null;
		StringWriter writer = new StringWriter();
		XmlWriter xml = new XmlWriter(writer);
		XmlWriter root = null;
		validNames.add("Academic");validNames.add("Advisement");validNames.add("Career");
		validNames.add("Dorm");validNames.add("Library");validNames.add("Student_Health");
		
		//This starts the xml string with root <Evaluation>
		try {
			root = xml.element("Evaluation");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		//For each episode...
		for(String n : validNames){
			//Get the list of records for that episode
			ArrayList<Record> rlist = records.get(n);
			try {
				//Sub-root element <Academic> for example
				XmlWriter r2 = root.element(n);
				for(Record r : rlist){
						r2.element("Question").text(r.getQuestion()).pop()
						.element("Answer").text(r.getAnswer()).pop()
						.element("Result").text(r.getResult()).pop()
						.element("Try").text(r.getTake()).pop()
						.pop();
					
						data += root.toString();//Add to data
						System.out.print(data);//For testing
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		try {
			xml.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	
		
		try {
			w = new BufferedWriter(new FileWriter(path, true));
			w.write(data);   
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	//Write master file of information for client
	// -For other information like time it took to complete-
	public void writeMaster(){
		
	}
}

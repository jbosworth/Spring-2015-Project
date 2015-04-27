package edu.udel.cisc275_15S.evaluate;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.*;
import com.badlogic.gdx.utils.XmlReader.Element;

public class XMLReader {
	private ArrayList<Question> questions = new ArrayList<Question>();
	private ArrayList<Answer> answers = new ArrayList<Answer>();
	
	//Singleton XMLReader- only one reader necessary in game
	private static final XMLReader INSTANCE = new XMLReader();
	private XMLReader() {}
	public static XMLReader getInstance(){
		return INSTANCE;
	}
	
	public ArrayList<Question> getQuestions(){
		return questions;
	}
	
	public ArrayList<Answer> getAnswers(){
		return answers;
	}
	
	/* Reads from a given XML file. It parses and stores questions and answers
	 * within the XMLReader. @param file should be the name of the input file. For example, 
	 * "Student_Resources.xml" is what should be passed to readFile().
	 */
	public void readFile(String file){
		XmlReader reader = new XmlReader();
		Element root = null;
		try {
			root = reader.parse(Gdx.files.internal("assets/" + file));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Array<Element> questions = root.getChildrenByName("question");
		for (Element child : questions)
		{
		    String qid = child.getChildByName("Qid").getAttribute("q_id");
		    String aid = child.getChildByName("Aid").getAttribute("a_id");
		    String text = child.getChildByName("Text").getAttribute("text");
		    int q_id = Integer.parseInt(qid);
		    int a_id = Integer.parseInt(aid);
		    Question q = new Question(q_id, a_id, text);
		    this.questions.add(q);
		}
		
		Array<Element> answers = root.getChildrenByName("answer");
		for (Element child : answers)
		{
		    String qid = child.getChildByName("Qid").getAttribute("q_id");
		    String aid = child.getChildByName("Aid").getAttribute("a_id");
		    String text = child.getChildByName("Text").getAttribute("text");
		    String bool = child.getChildByName("Bool").getAttribute("correct");
		    int q_id = Integer.parseInt(qid);
		    int a_id = Integer.parseInt(aid);
		    boolean correct = false;
		    if(bool.equals("true")){
		    	correct = true;
		    }
		    Answer a = new Answer(q_id, a_id, text, correct);
		    this.answers.add(a);
		}
	}
}

package edu.udel.cisc275_15S.UDevelopers.InputAndEvaluation;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.*;
import com.badlogic.gdx.utils.XmlReader.Element;

//This class is used by the GUI to read in dialogue, Q&A, and other messages
public class XMLReader {
	//Store questions, answers, responses, and dialogue in lists 
	//to be retrieved by GUI through the use of special functions.
	private ArrayList<Question> questions = new ArrayList<Question>();
	private ArrayList<Answer> answers = new ArrayList<Answer>();
	private ArrayList<Response> responses = new ArrayList<Response>();
	private ArrayList<Dialogue> dialogue = new ArrayList<Dialogue>();
	//String for intro or end messages
	private String message = "";
	
	//Singleton XMLReader- only one reader necessary in game
	private static final XMLReader INSTANCE = new XMLReader();
	private XMLReader() {}
	public static XMLReader getInstance(){
		return INSTANCE;
	}
	
	// public getters for testing- commented for release
	public ArrayList<Question> getQuestions(){
		return questions;
	}
	
	public ArrayList<Answer> getAnswers(){
		return answers;
	}
	
	public ArrayList<Response> getResponses(){
		return responses;
	}

	
	//public getters
	public ArrayList<Dialogue> getDialogue(){
		arrange();//put dialogue in order
		return dialogue;
	}
	
	public String getMessage(){
		return message;
	}
	
	//GUI calls this to get one single, random question from the list
	//The resulting list is of the form:
	//Question, Answer, Answer, Answer, Answer
	public ArrayList<Text> getQuestion(){
		ArrayList<Text> question = new ArrayList<Text>();
		Random r1 = new Random();//random for question
		Random r2 = new Random();//random for answers
		if(this.questions.size() != 0){
			Question q = this.questions.remove(r1.nextInt(questions.size()));
			question.add(q);
			ArrayList<Answer> temp = new ArrayList<Answer>();
			while(this.answers.size() != 0){//randomize answers for question
				Answer a = this.answers.remove(r2.nextInt(this.answers.size()));
				if(a.getQ_id() == q.getQ_id()){
					question.add(a);
				}else{
					temp.add(a);
				}
			}
			for(Answer a : temp){//return unrelated answers back to the list
				this.answers.add(a);
			}
		}
		return question;
	}
	
	// Get response to selected answer @code a, return the text of the response
	public String getResponse(Answer a){
		String result="";
		for(Response r : this.responses){
			if( (r.getQ_id() == a.getQ_id()) && (r.getA_id() == a.getA_id() ) ){
				result = r.getText();
			}
		}
		return result;
	}
	
	/* Reads from a given XML file. It parses and stores questions, answers, responses, dialogue,
	 * and messages within the XMLReader. @param file should be the name of the input file. For example, 
	 * "Student_Resources.xml" is what should be passed to readFile().
	 */
	public void readFile(String file){
		//clear lists and message
		this.questions.clear();
		this.answers.clear();
		this.responses.clear();
		this.dialogue.clear();
		this.message = "";
		
		XmlReader reader = new XmlReader();
		Element root = null;
		try {
			root = reader.parse(Gdx.files.internal(file));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if(file.contains("Question")){
			//Retrieve questions
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
			
			//Retrieve answers
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
			
			//Retrieve responses
			Array<Element> responses = root.getChildrenByName("response");
			for (Element child : responses){
				String qid = child.getChildByName("Qid").getAttribute("q_id");
			    String aid = child.getChildByName("Aid").getAttribute("a_id");
			    String text = child.getChildByName("Text").getAttribute("text");
			    int q_id = Integer.parseInt(qid);
			    int a_id = Integer.parseInt(aid);
			    Response r = new Response(q_id, a_id, text);
			    this.responses.add(r);
			}
			//Retrieve dialogue from characters	
		}else if(file.contains("Dialogue")){
			if(root.getAttribute("cnum").equals("3")){
				threeCharacters(root);
			}else{
				twoCharacters(root);
			}
			//Retrieve intro and end messages
		}else if(file.contains("Intro") || file.contains("intro") || file.contains("end") || file.contains("pass") || file.contains("retake")){
			Array<Element> m = root.getChildrenByName("Message");
			for(Element child : m){
				this.message += child.getAttribute("text");
			}
		}
	}
	
	//Called if dialogue file has two characters
	private void twoCharacters(Element root){
		Array<Element> c1 = root.getChildrenByName("c1");
		String n1 = root.getAttribute("c1");// get name of character
		for (Element child : c1)
		{
		    String text = n1 + ": " + child.getAttribute("text");// add name to beginning of text
		    String n = child.getAttribute("num");
		    int num = Integer.parseInt(n);
		    Dialogue d = new Dialogue(text, num);
		    dialogue.add(d);
		}
		
		Array<Element> c2 = root.getChildrenByName("c2");
		String n2 = root.getAttribute("c2");// get name of character
		for (Element child : c2)
		{
		    String text = n2 + ": " + child.getAttribute("text");// add name to beginning of text
		    String n = child.getAttribute("num");
		    int num = Integer.parseInt(n);
		    Dialogue d = new Dialogue(text, num);
		    dialogue.add(d);
		}
	}
	
	//Called if dialogue file has three characters
	private void threeCharacters(Element root){
		twoCharacters(root);
		
		Array<Element> c3 = root.getChildrenByName("c3");
		String n3 = root.getAttribute("c3");// get name of character
		for (Element child : c3)
		{
		    String text = n3 + ": " + child.getAttribute("text");// add name to beginning of text
		    String n = child.getAttribute("num");
		    int num = Integer.parseInt(n);
		    Dialogue d = new Dialogue(text, num);
		    dialogue.add(d);
		}
	}
	
	//Sorts the dialogue in the correct order of characters' speech
	public void arrange(){
		ArrayList<Dialogue> temp = new ArrayList<Dialogue>();
		Dialogue d = new Dialogue("", 0);
		int max = dialogue.size();
		int max_constant = dialogue.size();
		for(int i=1; i<max_constant + 1; i++, max--){
			int j=0;
			boolean found = false;
			while(!found){
				if(j<max && j>=0){
					if(dialogue.get(j).getNum() == i){
						d = dialogue.remove(j);
						found = true;
					}
				}else if(j>max && !found){
					//throw new Exception();// dialogue i not found and j out of bounds
				}
				j++;
			}
			temp.add(d);
		}
		dialogue = temp;
	}

}
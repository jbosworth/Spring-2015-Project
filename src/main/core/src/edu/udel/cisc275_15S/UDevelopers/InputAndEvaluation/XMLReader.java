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

public class XMLReader {
	private ArrayList<Question> questions = new ArrayList<Question>();
	private ArrayList<Answer> answers = new ArrayList<Answer>();
	private ArrayList<Response> responses = new ArrayList<Response>();
	private ArrayList<Dialogue> dialogue = new ArrayList<Dialogue>();
	
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
	
	public ArrayList<Response> getResponses(){
		return responses;
	}
	
	public ArrayList<Dialogue> getDialogue(){
		arrange();
		return dialogue;
	}
	
	public ArrayList getQuestion(){
		ArrayList<Text> question = new ArrayList<Text>();
		readFile("Student Health.xml");
		Random r1 = new Random();
		Random r2 = new Random();
		Question q = questions.remove(r1.nextInt(questions.size()));
		question.add(q);
		ArrayList<Answer> temp = new ArrayList<Answer>();
		while(answers.size() != 0){
			Answer a = answers.remove(r2.nextInt(answers.size()));
			if(a.getQ_id() == q.getQ_id()){
				question.add(a);
			}else{
				temp.add(a);
			}
		}
		
		for(Answer a : temp){
			this.answers.add(a);
		}
		return question;
	}
	
	// Get response to selected answer @code a, return the text of the response
	public String getResponse(Answer a){
		String result="";
		for(Response r : responses){
			if( (r.getQ_id() == a.getQ_id()) && (r.getA_id() == a.getA_id() ) ){
				result = r.getText();
			}
		}
		return result;
	}
	
	/* Reads from a given XML file. It parses and stores questions and answers
	 * within the XMLReader. @param file should be the name of the input file. For example, 
	 * "Student_Resources.xml" is what should be passed to readFile().
	 */
	public void readFile(String file){
		XmlReader reader = new XmlReader();
		Element root = null;
		try {
			root = reader.parse(Gdx.files.internal(file));
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
		
		Array<Element> c1 = root.getChildrenByName("c1");
		for (Element child : c1)
		{
		    String text = child.getAttribute("text");
		    String n = child.getAttribute("num");
		    int num = Integer.parseInt(n);
		    Dialogue d = new Dialogue(text, num);
		    dialogue.add(d);
		}
		
		Array<Element> c2 = root.getChildrenByName("c2");
		for (Element child : c2)
		{
		    String text = child.getAttribute("text");
		    String n = child.getAttribute("num");
		    int num = Integer.parseInt(n);
		    Dialogue d = new Dialogue(text, num);
		    dialogue.add(d);
		}
		
		Array<Element> c3 = root.getChildrenByName("c3");
		for (Element child : c3)
		{
		    String text = child.getAttribute("text");
		    String n = child.getAttribute("num");
		    int num = Integer.parseInt(n);
		    Dialogue d = new Dialogue(text, num);
		    dialogue.add(d);
		}
	}
	
	private void arrange(){
		ArrayList<Dialogue> temp = new ArrayList<Dialogue>();
		Dialogue d = new Dialogue("", 0);
		int max = dialogue.size();
		for(int i=1; i<max + 1; i++){
			int j=0;
			boolean found = false;
			while(!found){
				if(j<max){
					if(dialogue.get(j).getNum() == i){
						d = dialogue.remove(j);
						found = true;
					}
				}else if(j>max && !found){
					throw new Exception;// dialogue i not found and j out of bounds
				}
				j++;
			}
			temp.add(d);
		}
		dialogue = temp;
	}
}
package edu.udel.cisc275_15S.UDevelopers.InputAndEvaluation;

import java.util.ArrayList;

public class XMLTest {
	
	public static void main(String[] args){
		XMLReader reader = XMLReader.getInstance();
		
		//Test read of questions (with answers and responses) using Q&A from Academic_Questions.xml
		System.out.print("Reading in file: TestQA.xml... \n\n");
		reader.readFile("TestQA.xml");
		ArrayList<Question> q = reader.getQuestions();
		ArrayList<Answer> a = reader.getAnswers();
		ArrayList<Response> r = reader.getResponses();
		System.out.print("Questions: \n");
		for(Question u : q){
			System.out.print(u.getText()+ "\n");
		}
		System.out.print("Answers: \n");
		for(Answer n : a){
			System.out.print(n.getText()+ "\n");
		}
		System.out.print("Responses: \n");
		for(Response e : r){
			System.out.print(e.getText()+ "\n");
		}
		
		//Test getQuestion()
		//Should provide a random question with its answers in a random order
		System.out.print("\n\n");
		System.out.print("Checking randomization of questions with random answers... \n");
		ArrayList<Text> t = new ArrayList<Text>();
		while(!q.isEmpty()){
			t = reader.getQuestion();
			for(int i = 0; i<5; i++){
				System.out.print(t.remove(i).getText()+ "\n");
			}
		}
		
		
		//Test getResponse(Answer a)
		//Check that each answer and response match
		System.out.print("\n\n");
		System.out.print("Checking matching answers and responses...\n");
		for(Answer n : a){
			System.out.print(n.getText()+ "\n");
			System.out.print(reader.getResponse(n)+ "\n");
		}
		
		//Test read of dialogue (using dialogue from Academic_Dialogue.xml)
		reader.readFile("TestDialogue.xml");
		//This should hold all dialogue in proper order
		ArrayList<Dialogue> d = reader.getDialogue();
		//Read through to make sure it is
		//Names of characters should precede text of dialogue
		for(Dialogue i : d){
			System.out.print(i.getText()+ "\n");
		}
		
		
	}
}

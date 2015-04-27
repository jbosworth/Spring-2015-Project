package edu.udel.cisc275_15S.evaluate;

import java.util.ArrayList;

public class XMLTest {
	
	public static void main(String[] args){
		XMLReader reader = XMLReader.getInstance();
		
		// Needs work. NullPointerException at present testing when trying to read file.
		reader.readFile("D:/Documents and Settings/Jabels/My Documents/"
				+ "Project/core/src/edu/udel/cisc275_15S/evaluate/NewFile.xml");
		ArrayList<Question> q = reader.getQuestions();
		ArrayList<Answer> a = reader.getAnswers();
		for(Question u : q){
			System.out.print(u.getText());
		}
		for(Answer n : a){
			System.out.print(n.getText());
		}
	}
}

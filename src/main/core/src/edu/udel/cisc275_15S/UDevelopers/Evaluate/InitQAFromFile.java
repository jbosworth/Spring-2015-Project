package edu.udel.cisc275_15S.evaluate;

import java.util.ArrayList;

/* Initialize list of questions and answers from a text file that uses the format:
 * Question
 * Answer
 * Answer
 * Answer
 * Question...
 * The resulting list will be used by GUI to ask questions after dialogue.
 */

public class InitQAFromFile {
	public ArrayList<Question> initQ(){
		ArrayList<Question> qpool = new ArrayList<Question>();
		Question q0 = new Question(0, 2, "Which resource do you use to register for classes?\n");
		Question q1 = new Question(1, 1, "Who should you talk to about scheduling classes?\n");
		qpool.add(q0);qpool.add(q1);
		return qpool;
	}
	
	public ArrayList<Answer> initA(){
		ArrayList<Answer> apool = new ArrayList<Answer>();
		Answer a0 = new Answer(0, 0, "Sakai.\n", false);
		Answer a1 = new Answer(0, 1, "Email.\n", false);
		Answer a2 = new Answer(0, 2, "UDSIS.\n", true);
		Answer a3 = new Answer(1, 0, "Your friend.\n", false);
		Answer a4 = new Answer(1, 1, "Your advisor.\n", true);
		Answer a5 = new Answer(1, 2, "A counselor.\n", false);
		apool.add(a0);apool.add(a1);apool.add(a2);
		apool.add(a3);apool.add(a4);apool.add(a5);
		return apool;
	}
	
	//getQuestion(){
		
	//}

}

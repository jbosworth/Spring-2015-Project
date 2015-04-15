package edu.udel.cisc275_15S.evaluate;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


public class Test {
	
	public static void main(String[] args){ //Test
		Scanner scan = new Scanner(System.in); // Scanner for user input
		UpdateFile u = new UpdateFile(); // Contains method to update file
		InitQAFromFile init = new InitQAFromFile(); // Contains method to initialize Q&A
		ArrayList<Question> q = init.initQ(); // list of questions from file or constant
		ArrayList<Answer> a = init.initA(); // list of answers from file or constant
		ArrayList<Answer> templist = new ArrayList<Answer>(); // list that holds irrelevant answers
		ArrayList<Answer> evallist = new ArrayList<Answer>(); // list that holds relevant answers
		
		Random r1 = new Random(); // random for questions
		Random r2 = new Random(); // random for answers
		System.out.print("Test Q&A:\n\n");
		while(q.size() != 0){ // iterates through each question, then its answers, then evaluates
			int q1 = r1.nextInt(q.size());
			Question temp = (Question) q.remove(q1);
			System.out.print("Q. " + temp.getText());
			int tempid = temp.getQ_id();
			while(a.size() != 0){
				int a1 = r2.nextInt(a.size());
				Answer atemp = (Answer) a.remove(a1);
				if (atemp.getQ_id() == tempid){
					System.out.print("A. " + atemp.getText());
					evallist.add(atemp);
				}else{
					templist.add(atemp);
				}
			}
			// return answers to a
			while(templist.size() != 0){
				a.add(templist.remove(0));
			}
			
			// Repeat until correct
			int take = 1;
			boolean correct = false;
			while(!correct){
				boolean check = false; // check for validation of user input
				
				// Ask user for answer: 1, 2, or 3
				System.out.print("\nPlease enter 1, 2, or 3 for your answer\n");
				
				// Validate input
				int i = -1;
				while(!check){
					i = scan.nextInt();
					check = validate(i);
				}
				
				// Evaluate
				String t = "" + take;
				Answer eval = evallist.remove(i - 1);
				System.out.print("Your answer is: " + eval.getText());
				if(eval.isCorrect()){
					correct = true;
					System.out.print("That is correct.\n\n");
					Record rc1 = new Record(temp.q_idToString(), "Correct", t);
					u.update(rc1); // Update file
				}else{
					System.out.print("That is incorrect.\n\n");
					Record rc2 = new Record(temp.q_idToString(), "Incorrect", t);
					u.update(rc2); // Update file
					evallist.add(i - 1, eval);
					take++;
				}
				
			}
			
			// clear evallist for next iteration
			while(evallist.size() != 0){
				evallist.remove(0);
			}
		}
		scan.close();
	}
	
	private static boolean validate(int i){
		if(i != 1 && i != 2 && i !=3){
			System.out.print("Please enter 1, 2, or 3 for your answer");
			return false;
		}else{
			return true;
		}
	}
	
}

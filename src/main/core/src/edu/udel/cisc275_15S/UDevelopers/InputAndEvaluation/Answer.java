package edu.udel.cisc275_15S.UDevelopers.InputAndEvaluation;

public class Answer extends Text {
	private int q_id; // Identify Question that this answer belongs to
	private int a_id; // Identify which answer this is
	private String text; // Answer to be displayed
	private boolean correct; // If answer is correct or not
	
	public Answer(int q_id, int a_id, String text, boolean correct){
		this.q_id = q_id;
		this.a_id = a_id;
		this.text = text;
		this.correct = correct;
	}
	
	///ToString methods
	public String q_idToString(){
		String s = "";
		s += q_id;
		return s;
	}
	
	public String a_idToString(){
		String s = "";
		s += a_id;
		return s;
	}
	
	public String correctToString(){
		String s = "";
		s += correct;
		return s;
	}

	//Public getters
	public int getQ_id() {
		return q_id;
	}

	public int getA_id() {
		return a_id;
	}

	public String getText() {
		return text;
	}
	
	public boolean isCorrect() {
		return correct;
	}
}
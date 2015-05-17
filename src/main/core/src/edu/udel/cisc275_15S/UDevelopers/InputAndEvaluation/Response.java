package edu.udel.cisc275_15S.UDevelopers.InputAndEvaluation;

public class Response extends Text {
	private int q_id; // Identify which question it is under
	private int a_id; // Identify the answer it is responding to
	private String text; // Response (Correct or Incorrect with explanation)
	
	public Response(int q_id, int a_id, String text){
		this.q_id = q_id;
		this.a_id = a_id;
		this.text = text;
	}
	
	//ToString methods
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
}
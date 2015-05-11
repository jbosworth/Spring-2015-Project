package edu.udel.cisc275_15S.UDevelopers.InputAndEvaluation;
/* Each record stores information about each question answered by user.
 * A record will be created every time a question is answered, and a
 * file will be updated.
 */
public class Record {
	//Include useful information to be stored
	private String q_id; // Identification of question answered
	private String answer; // The answer chosen
	private String result; // Was the answer correct?
	private String take; // Was this their first, second... try?
	
	public Record(String q_id, String answer, String result, String take){
		this.q_id = q_id;
		this.answer = answer;
		this.result = result;
		this.take = take;
	}
	
	public String toString(){
		String s = "";
		s += q_id;
		s += ", ";
		s += answer;
		s += ", ";
		s += result;
		s += ", ";
		s += take;
		s += "| \n"; // End of record- since \n doesn't work
		return s;
	}
	
	public String getQ_id() {
		return q_id;
	}
	
	public String getAnswer() {
		return answer;
	}

	public String getResult() {
		return result;
	}

	public String getTake() {
		return take;
	}

	

}

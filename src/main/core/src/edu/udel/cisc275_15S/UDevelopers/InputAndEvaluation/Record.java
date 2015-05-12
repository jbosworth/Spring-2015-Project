package edu.udel.cisc275_15S.UDevelopers.InputAndEvaluation;
/* Each record stores information about each question answered by user.
 * A record will be created every time a question is answered, and a
 * file will be updated.
 */
public class Record {
	//Include useful information to be stored
	private String question; // The text of the question answered
	private String answer; // The text of the answer chosen
	private String result; // Was the answer correct?
	private String take; // Was this their first, second... try?
	
	public Record(String question, String answer, String result, String take){
		this.question = question;
		this.answer = answer;
		this.result = result;
		this.take = take;
	}
	
	public String getQuestion() {
		return question;
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
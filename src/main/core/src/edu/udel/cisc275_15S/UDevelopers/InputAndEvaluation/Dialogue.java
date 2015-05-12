package edu.udel.cisc275_15S.UDevelopers.InputAndEvaluation;

public class Dialogue extends Text {
	private String text;
	private int num;
	
	public Dialogue(String text, int num){
		this.text = text;
		this.num = num;
	}
	
	public String getText(){
		return text;
	}
	
	public int getNum(){
		return num;
	}
	
	public String toString() {
		return "" + num;
	}
}

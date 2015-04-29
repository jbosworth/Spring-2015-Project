package parsing;

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
}

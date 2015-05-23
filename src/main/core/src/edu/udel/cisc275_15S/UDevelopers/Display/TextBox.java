package edu.udel.cisc275_15S.UDevelopers.Display;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

import edu.udel.cisc275_15S.UDevelopers.InputAndEvaluation.Answer;
import edu.udel.cisc275_15S.UDevelopers.InputAndEvaluation.Dialogue;
import edu.udel.cisc275_15S.UDevelopers.InputAndEvaluation.Question;
import edu.udel.cisc275_15S.UDevelopers.InputAndEvaluation.Record;
import edu.udel.cisc275_15S.UDevelopers.InputAndEvaluation.Response;
import edu.udel.cisc275_15S.UDevelopers.InputAndEvaluation.Text;
import edu.udel.cisc275_15S.UDevelopers.InputAndEvaluation.XMLReader;
import edu.udel.cisc275_15S.UDevelopers.InputAndEvaluation.XMLWriter;

public class TextBox {
	public final static int dialogueBox = 0;
	public final static int Answer1 = 1;
	public final static int Answer2 = 2;
	public final static int Answer3 = 3;
	public final static int Answer4 = 4;
	
	float width;
	float height;
	
	BitmapFont font;
	SpriteBatch batch; 
	float[] rectDim;
	ShapeRenderer shapeRenderer;
	Color rectColor;
	
	buttonHandler handle;
	ArrayList<genericButton> buttons;
	XMLReader reader;
	XMLWriter writer;
	ArrayList<Dialogue> dialogueList;
	ArrayList<Text> questions;
	Answer answer;
	String response;
	String question;
	String dialogue;
	String filename;
	boolean changeText;
	int ending;
	
	int currentDialogue;
	int answered;
	int mode; //0 = Dialogue, 1= Quiz, 2=Response, 3 = EndSectionMode
	int answeredCorrect;
	boolean endSection;
	boolean readIn;
	boolean readQuiz;
	boolean updateRecords;
	
	public TextBox(SpriteBatch batch) {
		this.width = Gdx.graphics.getWidth();
		this.height = Gdx.graphics.getHeight();
		this.batch = batch;
		this.shapeRenderer = new ShapeRenderer();
		this.rectDim = new float[]{width*0.05f, height*0.05f, width*0.9f, height*0.3f};
		this.rectColor = new Color(0, 0, 1, 0.5f);
		
		this.font = new BitmapFont();
		this.font.setColor(Color.WHITE);
		
		this.changeText = true;
		this.questions = new ArrayList<Text>();
     	this.question = "";
     	this.dialogue = "";
     	this.response = "";
     	
     	this.currentDialogue = 0;
     	this.mode = 4;
     	this.answered = -1;
     	this.handle = buttonHandler.getInstance();
//     	this.handle.clear();
     	this.reader = XMLReader.getInstance();
     	this.writer = XMLWriter.getInstance();
     	this.readIn = true;
     	this.readQuiz = true;
     	this.updateRecords = false;
     	this.buttons = new ArrayList<genericButton>();
     	this.dialogueList = reader.getDialogue();
//     	System.out.println(reader.getDialogue().toString());
		this.buttons.addAll(createBoxButtons());
		this.handle.addButtons(buttons);
		Answer answer = new Answer(0, 0, "", false);
		this.endSection = false;
		this.answeredCorrect = 0;
		
		
	}
	/**
	 * Renders the green translucent text box and the text inside of it
	 */
	public void render() {
		Gdx.gl.glEnable(GL30.GL_BLEND);
		Gdx.gl.glBlendFunc(GL30.GL_SRC_ALPHA, GL30.GL_ONE_MINUS_SRC_ALPHA);
		shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
		shapeRenderer.begin(ShapeType.Filled);
		shapeRenderer.setColor(rectColor);
		shapeRenderer.rect(rectDim[0],rectDim[1],rectDim[2],rectDim[3]);
		shapeRenderer.end();
		Gdx.gl.glDisable(GL30.GL_BLEND);
		renderText();
	}
	public void renderText() {
		if(mode == 0) {
//			mode = 1;
			renderDialogue();
//			handle.quizMode(true);
		}
		else if(mode == 1) {
			renderQuiz();
		}
		else if (mode == 2){
			renderResponse();
		}
		
		else if (mode == 3) {
			renderEnd();
		}
		
		else if (mode == 4) {
			renderIntro();
		}
		else if (mode == 5) {
			renderEndMessage();
		}
	}
	
	/**
	 * Creates genericButton forms of the answers buttons
	 * and general dialogue box button
	 */
	public ArrayList<genericButton> createBoxButtons() {
		int numButtonsX = 2;
		int numButtonsY = 2;
		int id = 1;
		ArrayList<genericButton> buttonList = new ArrayList<genericButton>();
		answerButton b;
		Pixmap pix = new Pixmap(100, 100, Format.RGBA8888);
		pix.setColor(rectColor);
//		pix.setColor(0,1,0,0.0f);
		pix.fill();
	    
		float x = rectDim[0];
		float y =  rectDim[1];
		float width =  rectDim[2]/numButtonsX;
		float height =  rectDim[3] * 0.725f/numButtonsY;
		
		//    A1    A2
		//    A3    A4
		for(int i=0; i<numButtonsX; i++) {
			for(int j=0; j<numButtonsY; j++) {
				b = new answerButton(new Texture(pix),x + width*i , y + height*j, width, height);
				b.setId(id);
				b.getButton().setVisible(false);
				buttonList.add(b);
				id++;
			}
		}

		b = new answerButton(new Texture(pix),x,y,rectDim[2],rectDim[3]);
		b.getButton().setVisible(true);
		b.setId(dialogueBox);
		buttonList.add(b);
		
		return buttonList;
	}
	
	public void renderDialogue() {
		if (readIn) {
			reader.readFile(filename + "_Dialogue.xml");
			reader.arrange();
			dialogueList = reader.getDialogue();
		}
//		XMLReader reader = XMLReader.getInstance();
//		ArrayList<Dialogue> dialogueList = reader.getDialogue();
		if(changeText && currentDialogue < dialogueList.size()) {
			dialogue = dialogueList.get(currentDialogue).getText();
			changeText = false;
		}
		else if(changeText && currentDialogue >= dialogueList.size()) {
			mode = 1;
			handle.quizMode(true);
			rectColor = new Color(1, 0, 0, 0.5f);
			readIn = true;
		}
		float x = rectDim[0];
		float y =  rectDim[1];
		float width =  rectDim[2];
		float height =  rectDim[3];
		float offset = width*0.025f;
		batch.begin();
		font.drawWrapped(batch, dialogue, x + offset, y + height*0.95f, width - offset, BitmapFont.HAlignment.LEFT);
		batch.end();
	
		if(handle.isButtonClicked(dialogueBox) || Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
			changeText = true;
			handle.getButton(dialogueBox).setChecked(false);
			currentDialogue++;
		}
		
		else if(Gdx.input.isKeyJustPressed(Input.Keys.LEFT) && currentDialogue > 0) {
			currentDialogue--;
			changeText = true;
			handle.getButton(dialogueBox).setChecked(false);
		}
		
	}
	/**
	 * Writes dialogue into the green text box. 
	 * This differentiates if the words are in quiz form or dialogue form.
	 * Relies on the XMLReader for input
	 */
	//Account for dialogue, questions, response modes
	public void renderQuiz() {
		if (readQuiz) {
			System.out.println("Read in Quiz");
			reader.readFile(filename + "_Questions.xml");
			readQuiz = false;
		}
		Question main = null;
//		if (reader.getQuestions().size() > 0) {
			if (changeText && reader.getQuestions().size() > 0) {
//				System.out.println("Get Questions here");
				questions = reader.getQuestion();
				question = questions.remove(0).getText();
				changeText= false;
			}
			float x = rectDim[0];
			float y =  rectDim[1];
			float width =  rectDim[2];
			float height =  rectDim[3];
			float offset = width*0.025f;
			//These are for displaying the answers in the correct positions
			int h = 0;
			
			batch.begin();
			//Display Question
			font.drawWrapped(batch, question, x + offset, y + height*0.95f, width - offset, BitmapFont.HAlignment.LEFT);
			for (int i=0; i<4; i++) {
	//				b =x + width*i , y + height*j, width, height;
				font.drawWrapped(batch, ((Answer)(questions.get(i))).getText(), x + offset + (h)*width/2f, y + height*0.35f*(1+i%2),width/2f - offset);
				h+=i%2;
			}
			
			batch.end();
			//Through the number of answer buttons
			for(int i = 1; i <= 4; i++) {
				if(handle.isButtonClicked(i)) {
					mode = 2;
					//Used for indexing 
					answered = i -1;
					handle.getButton(i).setChecked(false);
					handle.quizMode(false);
					changeText = true;
					boolean a =( (Answer)(questions.get(answered))).isCorrect();
					answeredCorrect += a? 1:0;
					
//					writer.addRecord(filename, new Record(question,questions.get(answered).toString(), a?"true":"false", "1"));
				}
			}
			
	

	}
	
	public void renderResponse() {
		
		float x = rectDim[0];
		float y =  rectDim[1];
		float width =  rectDim[2];
		float height =  rectDim[3];
		float offset = width*0.025f;
		
		
		if (changeText) {
			answer = (Answer) questions.get(answered);
			response = reader.getResponse(answer);
//			System.out.println(response);
			changeText = false;
			
			
		}
		batch.begin();
		font.drawWrapped(batch, question, x + offset, y + height*0.95f, width - offset, BitmapFont.HAlignment.LEFT);
		font.drawWrapped(batch, answer.getText(), x + offset, y + height*0.95f*2/3, width - offset, BitmapFont.HAlignment.LEFT);
		font.drawWrapped(batch, response, x + offset, y + height*0.95f*1/3, width - offset, BitmapFont.HAlignment.LEFT);
		batch.end();

		if(handle.isButtonClicked(dialogueBox)) {
			changeText = true;
			handle.getButton(dialogueBox).setChecked(false);
			mode = 1;
			handle.quizMode(true);
			
			if(reader.getQuestions().size() == 0) {
				mode = 5;
				rectColor = new Color(0, 0, 1, 0.5f);
				handle.quizMode(false);
			}
		}
		
	}
	
	public void renderEnd() {
		if(handle.isButtonClicked(dialogueBox) || Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
			this.endSection = true;
		}
		float x = rectDim[0];
		float y =  rectDim[1];
		float width =  rectDim[2];
		float height =  rectDim[3];
		float offset = width*0.025f;
		String message = "Click in the box to return to the map";
		batch.begin();
		font.drawWrapped(batch, message, x + offset, y + height*0.95f, width - offset, BitmapFont.HAlignment.LEFT);
		batch.end();
	}
	public ArrayList<genericButton> getButtons() {
		return buttons;
	}
	
	public void renderIntro() {
		if (readIn) {
			readIn = false;
			reader.readFile(filename + "_Intro.xml");
		}
		handle.quizMode(false);
		float x = rectDim[0];
		float y =  rectDim[1];
		float width =  rectDim[2];
		float height =  rectDim[3];
		float offset = width*0.025f;
		String message = reader.getMessage();
		batch.begin();
		font.drawWrapped(batch, message, x + offset, y + height*0.95f, width - offset, BitmapFont.HAlignment.LEFT);
		batch.end();
//		System.out.println(handle.isButtonClicked(dialogueBox));
		if(handle.isButtonClicked(dialogueBox) || Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
//			System.out.println(handle.isButtonClicked(dialogueBox));
			changeText = true;
			handle.getButton(dialogueBox).setChecked(false);
			
			System.out.println(filename);
			if (filename.contains("Start") || filename.contains("Finish")) {
				mode = 3;
				System.out.println("Clicked Start");
				changeText = true;
				readIn = true;
			}
			else {
				mode = 0;
				changeText = true;
				readIn = true;
			}
			
		}
	}
	
	public void renderEndMessage() {
		if (readIn && (filename.contains("Start") || filename.contains("Finish"))) {
			readIn = false;
			reader.readFile(filename + "_end_" + ending + ".xml");
		}
		else if (readIn && answeredCorrect >= 5) {
			readIn = false;
			reader.readFile("pass" + ".xml");
		}
		else if (readIn) {
			readIn = false;
			reader.readFile("retake" + ".xml");
		}
		
		float x = rectDim[0];
		float y =  rectDim[1];
		float width =  rectDim[2];
		float height =  rectDim[3];
		float offset = width*0.025f;
		String message = reader.getMessage();
		batch.begin();
		font.drawWrapped(batch, message, x + offset, y + height*0.95f, width - offset, BitmapFont.HAlignment.LEFT);
		batch.end();
		
		if(handle.isButtonClicked(dialogueBox)) {
			changeText = true;
			handle.getButton(dialogueBox).setChecked(false);
			mode = 3;
			
		}
	}
	/**
	 * mode 0 = Dialogue
	 * mode 1 = Quiz
	 * mode 2 = Response
	 * mode 3 = End Section
	 * mode 4 = intro message
	 * mode 5 = end pass/fail message for module
	 * @param mode
	 */
	public void setMode(int mode) {
		this.mode = mode;
	}
	
	public boolean isEnded() {
		return endSection;
	}
	
	public void setQuestionFile(String file) {
		reader.readFile(file);
	}
	
	public void setIntro(String file) {
		reader.readFile(file);
	}
	public void setEnd(String file) {
		reader.readFile(file);
	}
	public void setFilename(String file) {
		this.filename = file;
	}
	
	
	public void nextDialogue() {
		currentDialogue++;
	}
	public void backDialogue() {
		currentDialogue--;
	}
	
	public void setEnding(int ending) {
		this.ending = ending;
	}
	
	public boolean isPassed() {
		return answeredCorrect >= 5;
	}
	public void restart() {
		currentDialogue = 0;
		readQuiz = true;
		answeredCorrect = 0;
		readIn = true;
		endSection = false;
		mode = 4;
	}
}

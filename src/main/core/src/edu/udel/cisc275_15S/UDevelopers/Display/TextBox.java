package edu.udel.cisc275_15S.UDevelopers.Display;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
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
import edu.udel.cisc275_15S.UDevelopers.InputAndEvaluation.Response;
import edu.udel.cisc275_15S.UDevelopers.InputAndEvaluation.Text;
import edu.udel.cisc275_15S.UDevelopers.InputAndEvaluation.XMLReader;

public class TextBox {
	float width;
	float height;
	
	BitmapFont font;
	SpriteBatch batch; 
	float[] rectDim;
	ShapeRenderer shapeRenderer;
	
	buttonHandler handle;
	ArrayList<genericButton> buttons;
	ArrayList<Text> questions;
	Answer answer;
	String response;
	String question;
	String dialogue;
	boolean changeText;
	int answered;
	int mode; //0 = Dialogue, 1= Quiz, 2=Response
	public TextBox(SpriteBatch batch) {
		this.width = Gdx.graphics.getWidth();
		this.height = Gdx.graphics.getHeight();
		this.batch = batch;
		this.shapeRenderer = new ShapeRenderer();
		this.rectDim = new float[]{width*0.05f, height*0.05f, width*0.9f, height*0.3f};
		
		this.font = new BitmapFont();
		this.font.setColor(Color.WHITE);
		
		this.changeText = true;
		this.questions = new ArrayList<Text>();
     	this.question = "";
     	this.dialogue = "";
     	this.response = "";
     	this.mode = 0;
     	this.answered = -1;
     	this.handle = buttonHandler.getInstance();
     	this.buttons = new ArrayList<genericButton>();
		this.buttons.addAll(createBoxButtons());
		this.handle.addButtons(buttons);
		Answer answer = new Answer(0, 0, "", false);
//		handle.quizMode(true);
	}
	/**
	 * Renders the green translucent text box and the text inside of it
	 */
	public void render() {
		Gdx.gl.glEnable(GL30.GL_BLEND);
		Gdx.gl.glBlendFunc(GL30.GL_SRC_ALPHA, GL30.GL_ONE_MINUS_SRC_ALPHA);
		shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
		shapeRenderer.begin(ShapeType.Filled);
		shapeRenderer.setColor(new Color(0, 1, 0, 0.5f));
		shapeRenderer.rect(rectDim[0],rectDim[1],rectDim[2],rectDim[3]);
		shapeRenderer.end();
		Gdx.gl.glDisable(GL30.GL_BLEND);
		
		renderText();
	}
	public void renderText() {
		if(mode == 0) {
			renderDialogue();
		}
		else if(mode == 1) {
			renderQuiz();
		}
		else {
			renderResponse();
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
		pix.setColor(0,1,0,0.5f);
//		pix.setColor(0,1,0,0.0f);
		pix.fill();
	    
		float x = rectDim[0];
		float y =  rectDim[1];
		float width =  rectDim[2]/numButtonsX;
		float height =  rectDim[3] * 0.725f/numButtonsY;
		
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
		b.setId(0);
		buttonList.add(b);
		
		return buttonList;
	}
	
	public void renderDialogue() {
		XMLReader reader = XMLReader.getInstance();
		ArrayList<Dialogue> dialogueList = reader.getDialogue();
		if(changeText && !dialogueList.isEmpty()) {
			dialogue = dialogueList.remove(0).getText();
			changeText = false;
		}
		else if(changeText && dialogueList.isEmpty()) {
			mode = 1;
			handle.quizMode(true);
		}
		float x = rectDim[0];
		float y =  rectDim[1];
		float width =  rectDim[2];
		float height =  rectDim[3];
		float offset = width*0.025f;
		batch.begin();
		font.drawWrapped(batch, dialogue, x + offset, y + height*0.95f, width - offset, BitmapFont.HAlignment.LEFT);
		batch.end();
		for(genericButton b : buttons) {
			if(b.isChecked() && b.getId() == 0) {
				changeText = true;
				b.getButton().setChecked(false);
			}
		}
	}
	/**
	 * Writes dialogue into the green text box. 
	 * This differentiates if the words are in quiz form or dialogue form.
	 * Relies on the XMLReader for input
	 */
	//Account for dialogue, questions, response modes
	public void renderQuiz() {
		XMLReader reader = XMLReader.getInstance();
//		reader.readFile("Student_Resources.xml");
		
//		ArrayList<Answer> answers = reader.getAnswers();
		

		Question main = null;
//		questions.add(new Question(0, 0, ""));
		if (changeText) {
			questions = reader.getQuestion();
			main = (Question)(questions.remove(0));
			question = main.getText();
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
			font.drawWrapped(batch, ((Answer)(questions.get(i))).getText(), x + offset + (i%2)*width/2f, y + height*0.35f*(1+h),width/2f);
			h+=i%2;
		}
		
		batch.end();
		int id = -1;
		for(genericButton q : buttons) {
			id = q.getId();
			if(q.isChecked() && id >=1 && id <= 4) {
				mode = 2;
				answered = id;
				q.getButton().setChecked(false);
				handle.quizMode(false);
				changeText = true;
			}
		}
	}
	
	public void renderResponse() {
		XMLReader reader = XMLReader.getInstance();
		
		float x = rectDim[0];
		float y =  rectDim[1];
		float width =  rectDim[2];
		float height =  rectDim[3];
		float offset = width*0.025f;
		
		if (changeText) {
			answer = (Answer) questions.get(answered);
			response = reader.getResponse(answer);
			System.out.println(response);
			changeText = false;
		}
		batch.begin();
		font.drawWrapped(batch, question, x + offset, y + height*0.95f, width - offset, BitmapFont.HAlignment.LEFT);
		font.drawWrapped(batch, answer.getText(), x + offset, y + height*0.95f*2/3, width - offset, BitmapFont.HAlignment.LEFT);
		font.drawWrapped(batch, response, x + offset, y + height*0.95f*1/3, width - offset, BitmapFont.HAlignment.LEFT);
		batch.end();
		for(genericButton b : buttons) {
			if(b.isChecked() && b.getId() == 0) {
				changeText = true;
				b.getButton().setChecked(false);
				mode = 0;
			}
		}
	}
	public ArrayList<genericButton> getButtons() {
		return buttons;
	}
	
	/**
	 * mode 0 = Dialogue
	 * mode 1 = Quiz
	 * mode 2 = Response
	 * @param mode
	 */
	public void setMode(int mode) {
		this.mode = mode;
	}
}

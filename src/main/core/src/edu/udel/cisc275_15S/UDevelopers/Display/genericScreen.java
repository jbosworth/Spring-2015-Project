package edu.udel.cisc275_15S.UDevelopers.Display;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

import edu.udel.cisc275_15S.UDevelopers.InputAndEvaluation.*;


/**
 * Factory method for creating screens
 * @author Sean
 *
 */
public class genericScreen implements Screen{
	float width;
	float height;
	SpriteBatch batch; 
	Texture background;
	ShapeRenderer shapeRenderer;
	float[] rectDim;
	float[] bankDim;
	BitmapFont font;
	
	genericButton wordBankBook;
	BitmapFont bank;
	Texture wordBank;
	
	buttonHandler handle;
	ArrayList<genericButton> buttons;
	
	
	public genericScreen(SpriteBatch batch, String background, String player) {
		this.width = Gdx.graphics.getWidth();
		this.height = Gdx.graphics.getHeight();
		
		this.batch = batch;
		this.background = new Texture(background);
		this.wordBank = new Texture("open-notebook.png");
		
		this.shapeRenderer = new ShapeRenderer();
		this.rectDim = new float[]{width*0.05f, height*0.05f, width*0.9f, height*0.3f};
		this.bankDim = new float[]{0, height*0.5f, width*0.5f, height*0.5f};
		
		this.font = new BitmapFont();
		this.bank = new BitmapFont();
        this.font.setColor(Color.WHITE);
        this.bank.setColor(Color.BLUE);
        this.wordBankBook = new genericButton(new Texture("notebook.png"),width*0.85f, height*0.80f, width*0.15f, height*0.20f, "Word\nBank");
        this.buttons = new ArrayList<genericButton>();
        this.buttons.add(wordBankBook);
        createBoxButtons();
        handle = new buttonHandler(buttons);
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}
	/**
	 * Renders everything
	 */
	@Override
	public void render(float delta) {
		batch.begin();
		batch.disableBlending();
		batch.draw(background, 0, 0, width, height);
		batch.enableBlending();	
//		batch.draw(player, 0, 0, width/3, height*0.8f);
		batch.end();
		
		//Render Word Bank (including button)
		handle.render();
		renderWordBank();

		//Render transparent rectangle
	    renderRect();
	    //Render Text
		renderWrapped();

	}

	@Override
	public void resize(int width, int height) {
		handle.resize(width,height);
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		batch.dispose();
		background.dispose();
		handle.dispose();
		
	}
	/**
	 * Writes dialogue into the green text box. 
	 * This differentiates if the words are in quiz form or dialogue form.
	 * Relies on the XMLReader for input
	 */
	private void renderWrapped() {
		XMLReader reader = XMLReader.getInstance();
		reader.readFile("NotARealFile");
		ArrayList<Question> questions = reader.getQuestions();
		ArrayList<Answer> answers = reader.getAnswers();
		
		String text = "";
		for (Question q: questions) {
		text = q.getText();
		}
		float x = rectDim[0];
		float y =  rectDim[1];
		float width =  rectDim[2];
		float height =  rectDim[3];

		batch.begin();
		font.drawWrapped(batch, text, x + width*0.05f, y + height*0.9f, width, BitmapFont.HAlignment.LEFT);
		batch.end();
	}
	
	/**
	 * Renders the green translucent text box
	 */
	public void renderRect() {
		Gdx.gl.glEnable(GL30.GL_BLEND);
		Gdx.gl.glBlendFunc(GL30.GL_SRC_ALPHA, GL30.GL_ONE_MINUS_SRC_ALPHA);
		shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
		shapeRenderer.begin(ShapeType.Filled);
		shapeRenderer.setColor(new Color(0, 1, 0, 0.5f));
		shapeRenderer.rect(rectDim[0],rectDim[1],rectDim[2],rectDim[3]);
		shapeRenderer.end();
		Gdx.gl.glDisable(GL30.GL_BLEND);
	}
	
	/**
	 * Creates word bank when notebook button is clicked
	 */
	public void renderWordBank() {
		int words = 0;
		ArrayList<String> text = new ArrayList<String>();
		text.add("Text");
		text.add("Goes");
		text.add("Here");
		
		float x = bankDim[0];
		float y =  bankDim[1];
		float width =  bankDim[2];
		float height =  bankDim[3];

		if (wordBankBook.isChecked()) {
			
			batch.begin();
			batch.draw(wordBank, x, y, width, height);
			for (String s : text) {
				bank.drawWrapped(batch, s, x + width*0.17f, y + height*0.875f - height*0.1f*words, width, BitmapFont.HAlignment.LEFT);
				words++;
			}
			batch.end();		
		}	
	}
	
	/**
	 * Creates genericButton forms of the answers buttons
	 * and general dialogue box button
	 */
	public void createBoxButtons() {
		int numButtonsX = 2;
		int numButtonsY = 2;
		int id = 1;
		genericButton b;
		Pixmap pix = new Pixmap(100, 100, Format.RGBA8888);
		pix.setColor(0,1,0,0.5f);
		pix.fill();
	    
		float x = rectDim[0];
		float y =  rectDim[1];
		float width =  rectDim[2]/numButtonsX;
		float height =  rectDim[3] * 0.75f/numButtonsY;
		
		for(int i=0; i<numButtonsX; i++) {
			for(int j=0; j<numButtonsY; j++) {
				b = new genericButton(new Texture(pix),x + width*i , y + height*j, width, height);
				b.setId(id);
				b.getButton().setVisible(true);
				buttons.add(b);
				id++;
			}
		}

		b = new genericButton(new Texture(pix),x,y,rectDim[2],rectDim[3]);
		b.getButton().setVisible(false);
		b.setId(0);
		buttons.add(b);
	}
	

}

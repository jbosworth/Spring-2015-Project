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
	float[] bankDim;

	
	genericButton wordBankBook;
	BitmapFont bank;
	Texture wordBank;
	
	buttonHandler handle;
	ArrayList<genericButton> buttons;
	
	TextBox textbox;
	
	boolean changeText;
	public genericScreen(SpriteBatch batch, String background, String player) {
		this.width = Gdx.graphics.getWidth();
		this.height = Gdx.graphics.getHeight();
		
		this.batch = batch;
		this.background = new Texture(background);
		this.wordBank = new Texture("open-notebook.png");
		this.bankDim = new float[]{0, height*0.5f, width*0.5f, height*0.5f};
		this.bank = new BitmapFont();
        this.bank.setColor(Color.BLUE);
        this.wordBankBook = new genericButton(new Texture("notebook.png"),width*0.85f, height*0.80f, width*0.15f, height*0.20f, "Word\nBank");
        this.buttons = new ArrayList<genericButton>();
        this.buttons.add(wordBankBook);
        this.handle = buttonHandler.getInstance();
        this.textbox = new TextBox(batch);
        handle.addButtons(wordBankBook);
		handle.setUpStage();
		XMLReader.getInstance().readFile("Advisement_Dialogue.xml");
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
		textbox.render();
		renderWordBank();

		

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
	

}

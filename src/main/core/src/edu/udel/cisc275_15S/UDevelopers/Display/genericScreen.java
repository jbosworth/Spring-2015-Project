package edu.udel.cisc275_15S.UDevelopers.Display;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

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
	
	buttonHandler handle;
	ArrayList<genericButton> buttons;
	
	TextBox textbox;
//	wordBank bank;
	boolean changeText;
	XMLReader reader;
	
	public genericScreen(SpriteBatch batch, String background, String readfile) {
		this.width = Gdx.graphics.getWidth();
		this.height = Gdx.graphics.getHeight();
		
		this.batch = batch;
		this.background = new Texture(background);
	
        this.handle = buttonHandler.getInstance();
        this.handle.clear();
        
 
//        this.bank = new wordBank(batch);
		
		this.reader = XMLReader.getInstance();
		if (!readfile.contains("Start") && !readfile.contains("Finish")) {
			reader.readFile(readfile + "_Dialogue.xml");
			reader.arrange();
		}

		this.textbox = new TextBox(batch);
		this.textbox.setFilename(readfile);
		textbox.setIntro(readfile + "_intro.xml");
//		if (!readfile.contains("Start") && !readfile.contains("Finish")) {
//			this.textbox.setQuestionFile(readfile + "_Questions.xml");
//			
//			
//		}
		this.handle.setUpStage();
		
		
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
		
//		bank.render();

		

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
	
	public TextBox getTextBox() {
		return textbox;
	}

}

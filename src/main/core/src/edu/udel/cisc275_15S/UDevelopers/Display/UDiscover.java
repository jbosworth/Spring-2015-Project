package edu.udel.cisc275_15S.UDevelopers.Display;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import edu.udel.cisc275_15S.UDevelopers.GSM;



public class UDiscover extends ApplicationAdapter {
	SpriteBatch batch; 
	genericScreen screen;
	GSM gsm;
	BitmapFont font;
	String speech;
	Texture campus;
	float width;
	float height;
	
	
	 
	@Override
	public void create () {
		width = Gdx.graphics.getWidth();
		height = Gdx.graphics.getHeight();
		campus= new Texture("sound-off.png");
		batch = new SpriteBatch();
		gsm=new GSM(batch);
		screen = new genericScreen(batch, "sampleUD2.jpg", "transparent.png");
	}
	
	@Override
	public void render () {

		
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		gsm.render(); 
		batch.end();
		} 
}

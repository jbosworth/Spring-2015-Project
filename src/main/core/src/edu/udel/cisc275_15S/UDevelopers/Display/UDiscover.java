package edu.udel.cisc275_15S.UDevelopers.Display;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class UDiscover extends ApplicationAdapter {
	SpriteBatch batch; 
	genericScreen screen;
	
	BitmapFont font;
	String speech;

	float width;
	float height;
	
	
	 
	@Override
	public void create () {
		width = Gdx.graphics.getWidth();
		height = Gdx.graphics.getHeight();
		
		batch = new SpriteBatch();
		screen = new genericScreen(batch, "sampleUD2.jpg", "transparent.png");
	}
	
	@Override
	public void render () {

		
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		 
		screen.render(Gdx.graphics.getDeltaTime());
		
		} 
}

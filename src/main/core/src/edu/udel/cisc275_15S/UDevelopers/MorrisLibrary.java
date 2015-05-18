package edu.udel.cisc275_15S.UDevelopers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import edu.udel.cisc275_15S.UDevelopers.Display.genericScreen;

public class MorrisLibrary extends GameState{
	Texture campus= new Texture("Characters (Edited)-04.jpg");
	SpriteBatch batch;
	public MorrisLibrary(GSM gsm, SpriteBatch batch){
		this.gsm=gsm;
		in= new genericScreen(batch,"Morris_Library_testpic.jpg", "Library");
        this.batch=batch;
	}
	
	public void update(){this.goback();}
	

	@Override
	public void render(float delta) {
		in.render(0);
		this.draw(batch);

	}
	
	
}

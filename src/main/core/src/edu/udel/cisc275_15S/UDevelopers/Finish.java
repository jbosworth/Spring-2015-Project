package edu.udel.cisc275_15S.UDevelopers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import edu.udel.cisc275_15S.UDevelopers.Display.genericScreen;

public class Finish extends GameState{
	boolean isTouched = false;
	Texture finishBanner= new Texture("Finish Screens-03.jpg");
	Texture leaderBoard= new Texture("Finish Screens-04.jpg");
	
	public Finish(GSM gsm, SpriteBatch batch){
		this.gsm=gsm;
		this.batch=batch;
		
	}
	
	
	public void update(){
		
	}
	
	public void render(float delta){
	
	if(Gdx.input.isTouched()){
	
	isTouched = true;
	}
	batch.begin();
	if(isTouched){
	batch.draw(leaderBoard, 0,0, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
	}
	else batch.draw(finishBanner, 0,0, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
	batch.end();
	}
}

package edu.udel.cisc275_15S.UDevelopers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Start extends GameState{
	Texture campus= new Texture("Home Screen (3x4)-2.jpg");
	SpriteBatch batch;
	public Start(GSM gsm, SpriteBatch batch){
		this.gsm=gsm;
		this.batch=batch;
	}
	
	public void update(){
		if(Gdx.input.justTouched()){
			gsm.setState(GSM.Map);
		}
	}
	
	@Override
	public void render() {
		batch.begin();
		batch.draw(campus, 0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
	   batch.end();
	}
	
	
}

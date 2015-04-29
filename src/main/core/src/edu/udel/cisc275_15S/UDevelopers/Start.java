package edu.udel.cisc275_15S.UDevelopers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Start extends GameState{
	Texture campus= new Texture("Home Screen.jpg");
	Sprite sb= new Sprite(campus);
	public Start(GSM gsm){
		this.gsm=gsm;
	}
	
	public void update(){
		if(Gdx.input.justTouched()){
			gsm.setState(gsm.Map);
		}
		
	}
	
	@Override
	public void render(SpriteBatch batch) {
		System.out.println("hello");
		
		batch.draw(campus, 0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
			   
	}
	
	
}

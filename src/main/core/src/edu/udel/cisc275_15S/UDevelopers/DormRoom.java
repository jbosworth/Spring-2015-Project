package edu.udel.cisc275_15S.UDevelopers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class DormRoom extends GameState{
	Texture campus= new Texture("/assets/Home Screen.jpg");
	Sprite sb= new Sprite(campus);
	public DormRoom(GSM gsm){
		this.gsm=gsm;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void render(SpriteBatch batch) {
		batch.begin();
		batch.draw(sb, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
	batch.end();
	}

}

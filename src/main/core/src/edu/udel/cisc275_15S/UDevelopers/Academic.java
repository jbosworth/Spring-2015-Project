package edu.udel.cisc275_15S.UDevelopers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Academic extends GameState{
Texture campus= new Texture("blah.jpg");
	Sprite sb= new Sprite(campus);
	public Academic(GSM gsm){
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

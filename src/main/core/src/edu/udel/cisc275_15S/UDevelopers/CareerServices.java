package edu.udel.cisc275_15S.UDevelopers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import edu.udel.cisc275_15S.UDevelopers.Display.genericScreen;

public class CareerServices extends GameState{

	Texture campus= new Texture("Screen Shot 2015-04-30 at 3.26.54 AM.png");
	Sprite sb= new Sprite(campus);
	public CareerServices(GSM gsm, SpriteBatch batch){
		this.gsm=gsm;
		in= new genericScreen(batch,"Screen Shot 2015-04-30 at 3.26.54 AM.png", "Career");
		this.batch=batch;

	}

	@Override
	public void update() {
		this.goback();// TODO Auto-generated method stub
		
	}

	
	@Override
	public void render(float delta) {
		in.render(0);
		this.draw(batch);

	}
	
}

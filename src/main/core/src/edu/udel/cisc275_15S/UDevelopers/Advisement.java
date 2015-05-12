package edu.udel.cisc275_15S.UDevelopers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import edu.udel.cisc275_15S.UDevelopers.Display.genericScreen;

public class Advisement extends GameState{
Texture campus= new Texture("Advisment.png");
	Sprite sb= new Sprite(campus);
	public Advisement(GSM gsm, SpriteBatch batch){
		this.gsm=gsm;
		in= new genericScreen(batch,"Advisment.png", "bob");
		this.batch=batch;

	}

	@Override
	public void update() {
		this.goback();// TODO Auto-generated method stub
		
	}

	
	@Override
	public void render() {
		in.render(0);
		this.draw(batch);

	}
}

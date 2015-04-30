package edu.udel.cisc275_15S.UDevelopers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class StudentHealth extends GameState{

	Texture campus= new Texture("Screen Shot 2015-04-30 at 3.28.27 AM.png");
	SpriteBatch batch;
	
	public StudentHealth(GSM gsm,SpriteBatch batch){
		this.gsm=gsm;
		this.batch=batch;
	}
	@Override
	public void update() {
		this.goback();// TODO Auto-generated method stub
		
	}

	@Override
	public void render() {
		in.render(0);

	}

}

package edu.udel.cisc275_15S.UDevelopers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import edu.udel.cisc275_15S.UDevelopers.Display.genericScreen;

public class StudentHealth extends GameState{

	Texture campus= new Texture("Characters (Edited)-05.jpg");
	SpriteBatch batch;
	
	public StudentHealth(GSM gsm,SpriteBatch batch){
		this.gsm=gsm;
		in= new genericScreen(batch,"Student_Health_testpic.jpg", "Student_Health");
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

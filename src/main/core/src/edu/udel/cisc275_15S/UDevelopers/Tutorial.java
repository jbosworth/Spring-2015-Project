package edu.udel.cisc275_15S.UDevelopers;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import edu.udel.cisc275_15S.UDevelopers.Display.genericScreen;

public class Tutorial extends GameState{
	Texture teammates= new Texture("4 Named Characters-01.jpg");
	Texture selectBox= new Texture("Empty.png");
	ArrayList<SimpleButton> buttons= new ArrayList<SimpleButton>();
	SimpleButton person1L= new SimpleButton(selectBox, 50, 0, 100, 500);
	SimpleButton person2= new SimpleButton(selectBox, 200, 0, 100, 500);
	SimpleButton person3= new SimpleButton(selectBox, 350, 0, 100, 500);
	SimpleButton person4R= new SimpleButton(selectBox, 500, 0, 100, 500);
    boolean selected=false;
	
	public Tutorial(GSM gsm, SpriteBatch batch){
		this.gsm=gsm;
		this.batch=batch;
		buttons.add(person1L);
		buttons.add(person2);
		buttons.add(person3);
		buttons.add(person4R);
		this.in= new genericScreen(batch,"Start-02.jpg","Academic");


	}
	
	
	public void update(){
		for(SimpleButton s: buttons){
			s.checkIfClicked(Gdx.input.getX(), Gdx.input.getY());
			if(s.clicked)
				selected=true;
				
				if(in.getTextBox().isEnded())
				this.gsm.setState(GSM.Map);
		}
		
	}
	
	public void render(float delta){
		if(!selected){
		batch.begin();
		
		batch.draw(teammates, 0,0, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		for(SimpleButton s: buttons){
			s.render(batch);}
		
		batch.end();}
		else{
			in.render(0);
		}
		
		
	}
}

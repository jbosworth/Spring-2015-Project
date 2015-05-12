package edu.udel.cisc275_15S.UDevelopers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.TextInputListener;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import edu.udel.cisc275_15S.UDevelopers.Display.genericButton;

public class Start extends GameState{
	Texture campus= new Texture("Home Screen (3x4)-2.jpg");
	Texture shape= new Texture("Empty.png");
	MytextListener listener = new MytextListener();
	String message;
    SimpleButton userbutton= new SimpleButton(shape, 330, 293, 115, 16);
    SimpleButton passbutton= new SimpleButton(shape, 330, 250, 115, 16);

	SpriteBatch batch;
	public boolean user=false, pass=false;
	public Start(GSM gsm, SpriteBatch batch){
		this.gsm=gsm;
		this.batch=batch;
		

	
	
	}
	
	public void update(){
		userbutton.update(Gdx.input.getX(), Gdx.input.getY());
		passbutton.update(Gdx.input.getX(), Gdx.input.getY());
		if(Gdx.input.justTouched() ){
			
				if(userbutton.clicked){
			Gdx.input.getTextInput(listener, "username", "username", "username");
				user=true;}
				Texture campus= new Texture("Home Screen (3x4)-2.jpg");
 if(passbutton.clicked){
					Gdx.input.getTextInput(listener, "password", "password", "password");
						pass=true;}
				
				
		}
		if(user&&pass)
			this.gsm.setState(GSM.Map);
		
		else
			System.out.println(""+user+pass);
		
		
			
		
		
		
	}
	
	
	@Override
	public void render() {
		

		batch.begin();
		batch.draw(campus, 0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		userbutton.render(batch);
		passbutton.render(batch);

	   batch.end();
	}
	
	
}

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
	Texture campus= new Texture("Home Screen (3x4) copy copy.jpg");
	Texture shape= new Texture("Empty.png");
	Texture shape1= new Texture("Empty.png");
	String username, password;
	MytextListener listener = new MytextListener();
	String message;
    SimpleButton userbutton= new SimpleButton(shape, 370, 250, 200, 70);

	SpriteBatch batch;
	public boolean user=false, pass=false;
	public Start(GSM gsm, SpriteBatch batch){
		this.gsm=gsm;
		this.batch=batch;
		

	
	
	}
	
	public void update(){
		userbutton.update(Gdx.input.getX(), Gdx.input.getY());
		//passbutton.update(Gdx.input.getX(), Gdx.input.getY());
		
			
				if(userbutton.clicked){
					String a="";
			Gdx.input.getTextInput(listener, "password", "password", "password");
			password = listener.toString();
			Gdx.input.getTextInput(listener, "username", a, "username");
			username = listener.toString();
			System.out.println("username=" + a  + "password=");
				user=true;//}
			//	Texture campus= new Texture("Home Screen (3x4)-2.jpg");
 //if(passbutton.clicked){
					
					
						}	
		
		if(user){
			this.gsm.InitializeAllStates();
			this.gsm.setState(GSM.Tutorial);
		}
		else
			System.out.println(""+user+pass);
		
	}
	

	@Override
	public void render(float delta) {
		

		batch.begin();
		listener.canceled();
		batch.draw(campus, 0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		userbutton.render(batch);
	    batch.end();
	}
	
	
}

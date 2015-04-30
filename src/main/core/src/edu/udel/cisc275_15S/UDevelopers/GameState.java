package edu.udel.cisc275_15S.UDevelopers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import edu.udel.cisc275_15S.UDevelopers.Display.genericScreen;
 
public abstract class GameState {
GSM gsm;
genericScreen in;
public abstract void update();
public abstract void render();

public void goback(){
	if(Gdx.input.justTouched()){
		gsm.setState(GSM.Map);
	}
}
}


package edu.udel.cisc275_15S.UDevelopers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import edu.udel.cisc275_15S.UDevelopers.Display.genericScreen;
 
public abstract class GameState {
	SpriteBatch batch;
GSM gsm;
genericScreen in;
public boolean completed=false;

	//Texture campus= new Texture( Gdx.files.internal("Campus_Map.jpg"));
	Texture campus;
	SimpleButton b;
	public GameState(){
	
		this.campus = new Texture("Campus_Map.jpg");
		this.b= new SimpleButton(campus, 0, Gdx.graphics.getHeight()-100, 100, 100);
	
	
	}
	public abstract void update();
	public abstract void render();

	public void goback(){
		if((Gdx.input.justTouched()&&b.clicked)|| in.getTextBox().isEnded()){
			if( in.getTextBox().isEnded()) {
				gsm.getgamestate(gsm.getstate()).completed=true;
			}
			
			gsm.setState(GSM.Map);
			
		}
	}
	
	public void draw(SpriteBatch back){
		b.update(Gdx.input.getX(), Gdx.input.getY());
		back.begin();
		this.b.render(back);
		back.end();
	}


}


package core.src.edu.udel.cisc275_15S.UDevelopers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Start extends GameState{
	Texture campus= new Texture("arrow.png");
	Sprite sb= new Sprite(campus);
	public Start(GSM gsm){
		this.gsm=gsm;
	}
	
	public void update(){}
	
	@Override
	public void render(SpriteBatch batch) {
		batch.begin();
		batch.draw(sb, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
	batch.end();
	}
	
	
}

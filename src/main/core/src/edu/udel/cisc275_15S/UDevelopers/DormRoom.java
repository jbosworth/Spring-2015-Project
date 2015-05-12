package edu.udel.cisc275_15S.UDevelopers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import edu.udel.cisc275_15S.UDevelopers.Display.genericScreen;

public class DormRoom extends GameState{
	Texture campus= new Texture("Screen Shot 2015-04-30 at 3.27.46 AM.png");
	Sprite sb= new Sprite(campus);
	public DormRoom(GSM gsm,SpriteBatch batch){
		this.gsm=gsm;
		in= new genericScreen(batch,"Screen Shot 2015-04-30 at 3.27.46 AM.png", "bob");
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
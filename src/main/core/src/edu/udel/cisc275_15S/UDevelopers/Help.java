package edu.udel.cisc275_15S.UDevelopers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Help extends GameState{
	Texture MapDiagram= new Texture("Help Instructions2.png");
	public Help(GSM gsm, SpriteBatch batch){
		this.gsm=gsm;
		this.batch=batch;
	
	}
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
		this.goback();
	}
	
	public void goback(){
		if(b.clicked){
			
			
			b.clicked=false;
			gsm.setState(GSM.Map);
			
			
		}
	}
	

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		
		batch.begin();
		batch.draw(MapDiagram, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		batch.end();
		this.draw(batch);

	}

}

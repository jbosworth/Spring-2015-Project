package core.src.edu.udel.cisc275_15S.UDevelopers.Display;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import core.src.edu.udel.cisc275_15S.UDevelopers.GSM;

public class UDiscover extends ApplicationAdapter {
	SpriteBatch batch; 
	genericScreen screen;
	genericButton button;
	GSM gsm;
	BitmapFont font;
	String speech;

	float width;
	float height;
	
	
	 
	@Override
	public void create () {
		width = Gdx.graphics.getWidth();
		height = Gdx.graphics.getHeight();
		
		batch = new SpriteBatch();
		screen = new genericScreen(batch, "sampleUD2.jpg", "transparent.png");
        gsm=new GSM(batch);
        button = new genericButton("arrow.png",width*0.5f, height*0.7f, width*0.25f, height*0.25f);

	}

	@Override
	public void render () {

		
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		 gsm.render();
		//screen.render(Gdx.graphics.getDeltaTime());
		//button.render();
		
		} 
}

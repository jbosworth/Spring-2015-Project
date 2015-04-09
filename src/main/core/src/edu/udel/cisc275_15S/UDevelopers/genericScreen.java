package edu.udel.cisc275_15S.UDevelopers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

/**
 * Factory method for creating screens
 * @author Sean
 *
 */
public class genericScreen implements Screen{
	float width;
	float height;
	SpriteBatch batch; 
	Texture background;
	Texture player;
	ShapeRenderer shapeRenderer;
	
	
	
	public genericScreen(SpriteBatch batch, String background, String player) {
		this.batch = batch;
		this.background = new Texture(background);
		this.player = new Texture(player);
		this.shapeRenderer = new ShapeRenderer();
		this.width = Gdx.graphics.getWidth();
		this.height = Gdx.graphics.getHeight();
	}
	
	public void render() {
		batch.begin();
		batch.disableBlending();
		batch.draw(background, 0, 0, width, height);
		batch.enableBlending();
		batch.draw(player, 0, 0, width/3, height*0.8f);
		batch.end();
		
		//Render transparent rectangle
	    Gdx.gl.glEnable(GL30.GL_BLEND);
		Gdx.gl.glBlendFunc(GL30.GL_SRC_ALPHA, GL30.GL_ONE_MINUS_SRC_ALPHA);
		shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
		shapeRenderer.begin(ShapeType.Filled);
		shapeRenderer.setColor(new Color(0, 1, 0, 0.5f));
		shapeRenderer.rect(width*0.05f, height*0.05f, width*0.9f, height*0.3f);
		shapeRenderer.end();
		Gdx.gl.glDisable(GL30.GL_BLEND);
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
}

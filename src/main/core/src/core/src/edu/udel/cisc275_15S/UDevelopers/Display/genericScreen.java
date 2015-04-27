package core.src.edu.udel.cisc275_15S.UDevelopers.Display;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
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
	ShapeRenderer shapeRenderer;
	float[] rectDim;
	BitmapFont font;
	
	
	
	public genericScreen(SpriteBatch batch, String background, String player) {
		this.batch = batch;
		this.background = new Texture(background);
		this.shapeRenderer = new ShapeRenderer();
		this.width = Gdx.graphics.getWidth();
		this.height = Gdx.graphics.getHeight();
		this.rectDim = new float[]{width*0.05f, height*0.05f, width*0.9f, height*0.3f};
		this.font = new BitmapFont();
        this.font.setColor(Color.WHITE);
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		batch.begin();
		batch.disableBlending();
		batch.draw(background, 0, 0, width, height);
		batch.enableBlending();
//		batch.draw(player, 0, 0, width/3, height*0.8f);
		batch.end();
		
		//Render transparent rectangle
	    Gdx.gl.glEnable(GL30.GL_BLEND);
		Gdx.gl.glBlendFunc(GL30.GL_SRC_ALPHA, GL30.GL_ONE_MINUS_SRC_ALPHA);
		shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
		shapeRenderer.begin(ShapeType.Filled);
		shapeRenderer.setColor(new Color(0, 1, 0, 0.5f));
		shapeRenderer.rect(rectDim[0],rectDim[1],rectDim[2],rectDim[3]);
		shapeRenderer.end();
		Gdx.gl.glDisable(GL30.GL_BLEND);
		

		renderWrapped();

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
		batch.dispose();
		background.dispose();
		
	}
	
	private void renderWrapped() {
		String text = "Wrapped Wrapped Wrapped Wrapped Wrapped Wrapped Wrapped Wrapped Wrapped Wrapped Wrapped Wrapped";
		float x = rectDim[0];
		float y =  rectDim[1];
		float width =  rectDim[2];
		float height =  rectDim[3];

//		layout.setText(font, text, Color.WHITE, width, Align.left, true);
//		x += width / 2 - layout.width / 2;
//		y += height / 2 + layout.height / 2;
		batch.begin();
		font.drawWrapped(batch, text, x, y + height, width, BitmapFont.HAlignment.LEFT);
		batch.end();
		// More efficient to draw the layout used for bounds:
		// font.draw(spriteBatch, layout, x, y);

		// Note that wrapped text can be aligned:
		// font.draw(spriteBatch, text, x, y, width, Align.center, true);
	}
}

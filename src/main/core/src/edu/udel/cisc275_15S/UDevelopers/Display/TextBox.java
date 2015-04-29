package edu.udel.cisc275_15S.UDevelopers.Display;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class TextBox {
	float width;
	float height;
	
	BitmapFont font;
	SpriteBatch batch; 
	float[] rectDim;
	ShapeRenderer shapeRenderer;
	
	ArrayList<genericButton> buttons;
	
	public TextBox(SpriteBatch batch) {
		this.width = Gdx.graphics.getWidth();
		this.height = Gdx.graphics.getHeight();
		
		this.shapeRenderer = new ShapeRenderer();
		this.rectDim = new float[]{width*0.05f, height*0.05f, width*0.9f, height*0.3f};
		
		this.font = new BitmapFont();
		this.font.setColor(Color.WHITE);
	}
	
	public void render() {
		Gdx.gl.glEnable(GL30.GL_BLEND);
		Gdx.gl.glBlendFunc(GL30.GL_SRC_ALPHA, GL30.GL_ONE_MINUS_SRC_ALPHA);
		shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
		shapeRenderer.begin(ShapeType.Filled);
		shapeRenderer.setColor(new Color(0, 1, 0, 0.5f));
		shapeRenderer.rect(rectDim[0],rectDim[1],rectDim[2],rectDim[3]);
		shapeRenderer.end();
		Gdx.gl.glDisable(GL30.GL_BLEND);
	}
}

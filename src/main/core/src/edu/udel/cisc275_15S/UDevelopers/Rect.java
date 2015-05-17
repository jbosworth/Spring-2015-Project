package edu.udel.cisc275_15S.UDevelopers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;


public class Rect {
	float x, y;
	boolean within;
	int width, height;
	Texture shape= new Texture("Advisment.png");
	public Rect(float x, float y, int width, int height){
		this.x=x;
		this.y=y;
		this.width=width;
		this.height=height;
		within=false;
	}
	
	public void render(SpriteBatch batch){
		
		
		batch.draw(shape,x, y, width, height);
		
	}
	
	
	

}

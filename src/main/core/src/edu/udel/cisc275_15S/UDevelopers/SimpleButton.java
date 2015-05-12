package edu.udel.cisc275_15S.UDevelopers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SimpleButton {

    private Sprite skin;
   public  boolean clicked=false,down=false;

    public SimpleButton(Texture texture, float x, float y, float width, float height) {
      skin = new Sprite(texture); // your image
      skin.setPosition(x, y);
      skin.setSize(width, height);
    }

    public void render(SpriteBatch batch) {
       
        skin.draw(batch); // draw the button
    }

    public void update( float input_x, float input_y){
    	checkIfClicked(input_x,input_y);
    	
    }
    
    public void checkIfClicked (float ix, float iy) {
    	
        if (ix > skin.getX() && ix < skin.getX() + skin.getWidth()) {
            if (iy > Gdx.graphics.getHeight()-skin.getY() -skin.getHeight()&& iy < Gdx.graphics.getHeight()-skin.getY() ) {
                // the button was clicked, perform an action
                System.out.println("Button clicked !");
               clicked=true;
            }
        }
    	
        else clicked=false;
       
    }

}

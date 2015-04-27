package edu.udel.cisc275_15S.UDevelopers.Display;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

/**
 * Simplified factory method for navigation buttons
 * @author Sean
 *
 */
public class genericButton extends Button{

	Stage stage;
    TextButton button;
    TextButtonStyle textButtonStyle;
    BitmapFont buttonFont;
    Skin skin;
    TextureAtlas buttonAtlas;
    
	public genericButton(String image, float x, float y, float width, float height) {
		//create button
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        
        skin = new Skin();
		skin.add("white", new Texture(image));
 
		// Store the default libgdx font under the name "default".
		BitmapFont bfont=new BitmapFont();
		bfont.scale(1);
		skin.add("default",bfont);
 
		// Configure a TextButtonStyle and name it "default". Skin resources are stored by type, so this doesn't overwrite the font.
		textButtonStyle = new TextButtonStyle();
		textButtonStyle.up = skin.newDrawable("white", Color.DARK_GRAY);
		textButtonStyle.down = skin.newDrawable("white", Color.DARK_GRAY);
		textButtonStyle.checked = skin.newDrawable("white", Color.BLUE);
		textButtonStyle.over = skin.newDrawable("white", Color.LIGHT_GRAY);
		
 
		textButtonStyle.font = skin.getFont("default");
 
		skin.add("default", textButtonStyle);
 
		// Create a button with the "default" TextButtonStyle. A 3rd parameter can be used to specify a name other than "default".
		button=new TextButton("New \n Screen",textButtonStyle);
		button.setBounds(x, y, width, height);
		stage.addActor(button);
		stage.addActor(button);
		stage.addActor(button);
		
		button.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				System.out.println("Clicked! Is checked: " + button.isChecked());
//				button.setText("Starting new game");
//				g.setScreen( new GameScreen());
 
				
			}
		});
	}
	
	public void render() {
		stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
		stage.draw();
	}
}

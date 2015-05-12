package edu.udel.cisc275_15S.UDevelopers.Display;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class answerButton extends genericButton{

	
	public answerButton(Texture image, float x, float y, float width, float height) {
		super(image, x, y, width, height);
	}

	public void addListener() {
		button.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
//				System.out.println("Clicked! Is checked: " + button.isChecked());
//				button.setText("Starting new game");
//				g.setScreen( new GameScreen());
 
				
			}
		});
	}
	
}

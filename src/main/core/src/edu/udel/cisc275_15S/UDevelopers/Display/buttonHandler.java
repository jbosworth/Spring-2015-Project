package edu.udel.cisc275_15S.UDevelopers.Display;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class buttonHandler {

	ArrayList<genericButton> buttons;
	Stage stage;
	
	public buttonHandler(ArrayList<genericButton> buttons) {
		this.buttons = buttons;
		setUpStage();
	}
	
	public void render() {
		stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
		stage.draw();
	}
	/**
	 * Puts all buttons on the same stage
	 */
	public void setUpStage() {
		float width = Gdx.graphics.getWidth();
		float height = Gdx.graphics.getHeight();
		this.stage = new Stage(new StretchViewport(width, height));
		Gdx.input.setInputProcessor(stage);
		TextButton tb;
		for(genericButton b : buttons) {
			tb = b.getButton();
			for(int i=0; i<3; i++) {
				stage.addActor(tb);
			}
		}
	}
	public void dispose() {
		stage.dispose();
	}
	
	public void resize(int width,int height) {
		// use true here to center the camera
	    // that's what you probably want in case of a UI
	    stage.getViewport().update(width, height, false);
	}
	/**
	 * Switches the available buttons
	 * @param quiz
	 */
	public void quizMode(boolean quiz) {
		
		for (genericButton g : buttons) {
			if(5 >= g.getId() && g.getId() >= 1 ) {
				g.getButton().setVisible(quiz);
			}
			else if(g.getId()==0) {
				g.getButton().setVisible(!quiz);
			}
		}
	}
}

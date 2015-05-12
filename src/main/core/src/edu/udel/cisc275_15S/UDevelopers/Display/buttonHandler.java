package edu.udel.cisc275_15S.UDevelopers.Display;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.StretchViewport;


public class buttonHandler {

	ArrayList<genericButton> buttons;
	Stage stage;
	
	private static final buttonHandler INSTANCE = new buttonHandler();
	private buttonHandler() {
		this.buttons = new ArrayList<genericButton>();
	}
	public static buttonHandler getInstance() {
		return INSTANCE;
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
			for(int i=0; i<buttons.size(); i++) {
				stage.addActor(tb);
			}
		}
	}
	public void dispose() {
		stage.dispose();
		for (genericButton g : buttons) {
			g.dispose();
		}
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
	
	public void addButtons(ArrayList<genericButton> list) {
		this.buttons.addAll(list);
	}
	
	public void addButtons(genericButton button) {
		this.buttons.add(button);
	}
	
	public boolean isButtonClicked(int id) {
		boolean clicked = false; 
		for(genericButton g : buttons) {
			if(g.getId() == id && g.isChecked()) {
				clicked = true;
			}
		}
		return clicked;
	}
	
	public genericButton getButton(int id) {
		genericButton button = null;
		for(genericButton g : buttons) {
			if(g.getId() == id) {
				button = g;
			}
		}
		return button;
	}
}

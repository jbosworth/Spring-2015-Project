package edu.udel.cisc275_15S.UDevelopers;


import com.badlogic.gdx.Input.TextInputListener;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;

public class MytextListener implements TextInputListener{

public String text;
	@Override
	public void input(String text) {
		System.out.println("listener="+text);
		this.text=text;// TODO Auto-generated method stub
		
	}

	@Override
	public void canceled() {
		// TODO Auto-generated method stub
		
	}

}

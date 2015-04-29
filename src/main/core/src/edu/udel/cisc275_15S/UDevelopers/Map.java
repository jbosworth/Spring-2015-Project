package edu.udel.cisc275_15S.UDevelopers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class Map extends GameState{
	
public Map(GSM gsm){
	this.gsm=gsm;//System.out.println(Gdx.files.internal("libs/campus.jpg").file().getAbsolutePath());
}
Texture campus= new Texture(Gdx.files.internal("Campus Map-01 (WIDE).jpg"));
Sprite sb= new Sprite(campus);
Boolean dormroom, academic, morris,carrer,advisement,health;

public void render(SpriteBatch batch){
	sb.draw(batch);
}

public void update(){
	mouse_pos_check();
	mouse_click_check();
	
}

private void mouse_click_check() {
	if(clickedDormRoom()){
		gsm.setState(gsm.DormRoom);
	}
	if(clickedAcademic()){
		gsm.setState(gsm.Academic);
	}
	if(clickedMorrisLibrary()){
		gsm.setState(gsm.MorrisLibrary);
	}
	if(clickedStudentHealth()){
		gsm.setState(gsm.StudentHealth);
	}
	if(clickedAdvisement()){
		gsm.setState(gsm.Advisement);
	}
	if(clickedCareerServices()){
		gsm.setState(gsm.CareerServices);
	}
	
}

private boolean clickedDormRoom() {
	// TODO Auto-generated method stub
	return false;
}

private boolean clickedAcademic() {
	// TODO Auto-generated method stub
	return false;
}

private boolean clickedMorrisLibrary() {
	// TODO Auto-generated method stub
	return false;
}

private boolean clickedStudentHealth() {
	// TODO Auto-generated method stub
	return false;
}

private boolean clickedCareerServices() {
	// TODO Auto-generated method stub
	return false;
}

private boolean clickedAdvisement() {
	// TODO Auto-generated method stub
	return false;
}

private void mouse_pos_check() {
	// TODO Auto-generated method stub
	
}


}

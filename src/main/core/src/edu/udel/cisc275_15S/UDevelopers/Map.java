package edu.udel.cisc275_15S.UDevelopers;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Gdx2DPixmap;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

import edu.udel.cisc275_15S.UDevelopers.Display.buttonHandler;
import edu.udel.cisc275_15S.UDevelopers.Display.genericButton;


public class Map extends GameState{
	
public Map(GSM gsm, SpriteBatch batch){
	this.gsm=gsm;//System.out.println(Gdx.files.internal("libs/campus.jpg").file().getAbsolutePath());
	rects.add(a0_rect);
	rects.add(a1_rect);
	rects.add(a2_rect);
	rects.add(a3_rect);
	rects.add(a4_rect);
	rects.add(a5_rect);
	this.batch=batch;
}
buttonHandler handle=buttonHandler.getInstance();
Texture campus= new Texture(Gdx.files.internal("Campus Map-01 (3x4).jpg"));
Texture empty= new Texture("badlogic.jpg");
SpriteBatch batch;
Boolean dormroom, academic, morris,carrer,advisement,health;
float width = Gdx.graphics.getWidth();
float height = Gdx.graphics.getHeight();
Array<Rect> rects= new Array<Rect>();
int ac=0, student=4, advise=1, dorm=3, career=5, lib=2; 
Rect a0_rect= new Rect(width*.4f, height*.7f, 100, 100);
Rect a1_rect= new Rect(width*.2f, height*.5f, 100, 100);
Rect a2_rect= new Rect(width*.2f, height*.3f, 100, 100);
Rect a3_rect= new Rect(width*.4f, height*.1f, 100, 100);
Rect a4_rect= new Rect(width*.6f, height*.2f, 100, 100);
Rect a5_rect= new Rect(width*.6f, height*.6f, 100, 100);


public void render(){
	batch.begin();
	batch.draw(campus, 0,0,width,height);
	batch.end();
	
	
	
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
	if (rects.get(dorm).within && Gdx.input.justTouched()){
		return true;
	}
	return false;
}

private boolean clickedAcademic() {
	if (rects.get(this.ac).within&& Gdx.input.justTouched()){
		System.out.println("academic");

		return true;
	}
	return false;
}

private boolean clickedMorrisLibrary() {
	if (rects.get(this.lib).within&& Gdx.input.justTouched()){
		return true;
	}
	return false;
}

private boolean clickedStudentHealth() {
	if (rects.get(this.student).within&& Gdx.input.justTouched()){
		return true;
	}
	return false;
}

private boolean clickedCareerServices() {
	if (rects.get(this.career).within&& Gdx.input.justTouched()){
		return true;
	}
	return false;
}

private boolean clickedAdvisement() {
	if (rects.get(this.advise).within&& Gdx.input.justTouched()){
		return true;
	}
	return false;
}

private void mouse_pos_check() {
	for(Rect rect:rects)
	{
		if(Gdx.input.getX()>rect.x && Gdx.input.getX()<rect.x+rect.width){
          
			if((Gdx.input.getY()< (height-rect.y) && Gdx.input.getY()> (height-rect.y-rect.height))){
				System.out.println("hello");

				rect.within=true;
			}
		}
		else rect.within=false;
	}
	
}


}

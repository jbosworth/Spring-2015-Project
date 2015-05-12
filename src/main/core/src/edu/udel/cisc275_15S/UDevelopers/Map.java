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
	
	this.batch=batch;
	init_rects();//initialize the rectangle
	
}
buttonHandler handle=buttonHandler.getInstance();
Texture campus= new Texture(Gdx.files.internal("Campus_Map.jpg"));
Texture empty= new Texture("badlogic.jpg");
Texture check= new Texture("CheckMark.png");

SpriteBatch batch;
Boolean dormroom, academic, morris,carrer,advisement,health;
float width = Gdx.graphics.getWidth();
float height = Gdx.graphics.getHeight();
Array<Rect> rects= new Array<Rect>();
int ac=1, student=3, advise=4, dorm=0, career=5, lib=2, numstates=6; 
Rect acad_rect= new Rect(width*.4f, height*.7f, 100, 100);
Rect dorm_rect= new Rect(width*.2f, height*.5f, 100, 100);
Rect lib_rect= new Rect(width*.2f, height*.3f, 100, 100);
Rect advise_rect= new Rect(width*.4f, height*.1f, 100, 100);
Rect student_rect= new Rect(width*.6f, height*.2f, 100, 100);
Rect career_rect= new Rect(width*.6f, height*.6f, 100, 100);


public void render(){
	batch.begin();
	batch.draw(campus, 0,0,width,height);
	drawcompleted(batch);
	batch.end();
	
	
	
}

public void init_rects(){
	rects.add(acad_rect);
	rects.add(advise_rect);
	rects.add(lib_rect);
	rects.add(dorm_rect);
	rects.add(student_rect);
	rects.add(career_rect);
}

public void drawcompleted(SpriteBatch batch){
	for(GameState gs: this.gsm.GameStates()){
		int i=0;

	while(gs!= this.gsm.getgamestate(i)){
		i++;
	}
	if(this.gsm.getgamestate(i)==null)
		continue;
		
    if(i==ac && this.gsm.getgamestate(i).completed){
	batch.draw(check, acad_rect.x,acad_rect.y, 50, 50);
	System.out.println("b"+ ac);
    }
    else if(i==student && this.gsm.getgamestate(i).completed){
    	batch.draw(check, student_rect.x,student_rect.y,50, 50);
    	System.out.println("b"+student);

    }
    else if(i==advise && this.gsm.getgamestate(i).completed){
    	batch.draw(check, advise_rect.x,advise_rect.y,50, 50);
    	System.out.println("b"+advise);

    }
    else if(i==dorm && this.gsm.getgamestate(i).completed){
    	batch.draw(check, dorm_rect.x,dorm_rect.y,50, 50);
    	System.out.println("b"+dorm);

    }
    else if(i==career && this.gsm.getgamestate(i).completed){
    	batch.draw(check, career_rect.x,career_rect.y,50, 50);
    	System.out.println("b"+career);

    	}
    
    else if(i==lib && this.gsm.getgamestate(i).completed){
    	batch.draw(check, lib_rect.x,lib_rect.y,50, 50);
    	System.out.println("b");

    }
    i++;
	}
    
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

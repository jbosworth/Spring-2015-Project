package edu.udel.cisc275_15S.UDevelopers;

import java.text.DecimalFormat;
import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Gdx2DPixmap;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

import edu.udel.cisc275_15S.UDevelopers.Display.buttonHandler;
import edu.udel.cisc275_15S.UDevelopers.Display.genericButton;

public class Map extends GameState{
	buttonHandler handle=buttonHandler.getInstance();
	Texture campus= new Texture(Gdx.files.internal("Campus_Map.jpg"));
	Texture empty= new Texture("Empty.png");
	Texture check= new Texture("CheckMark.png");
	int check_size=40;
	SpriteBatch batch;
	Boolean dormroom, academic, morris,carrer,advisement,health;
	float width = Gdx.graphics.getWidth();
	float height = Gdx.graphics.getHeight();
	Array<Rect> rects= new Array<Rect>();
	int ac=1, student=3, advise=4, dorm=0, career=5, lib=2, numstates=6; 
	Rect dorm_rect= new Rect(width*.4f, height*.7f, 130, 100);
	Rect acad_rect= new Rect(width*.27f, height*.53f, 90, 95);
	Rect lib_rect= new Rect(width*.22f, height*.22f, 100, 138);
	Rect student_rect= new Rect(width*.42f, height*.1f, 120, 53);
	Rect advise_rect= new Rect(width*.63f, height*.23f, 100, 110);
	Rect career_rect= new Rect(width*.63f, height*.51f, 70, 100);
	SimpleButton HelpButton= new SimpleButton(empty, 500, 400, 100, 100);
	
	private int time;
	private String yourTimeName;
	BitmapFont yourBitmapFontName;
	
	public Map(GSM gsm, SpriteBatch batch){
		this.gsm=gsm;//System.out.println(Gdx.files.internal("libs/campus.jpg").file().getAbsolutePath());
		
		this.batch=batch;
		init_rects();//initialize the rectangle
		
	    yourTimeName = "score: 0";
	    yourBitmapFontName = new BitmapFont();
	}

	public void render(float delta){
		DecimalFormat myFormat = new DecimalFormat("0.00");
		yourTimeName= "Race Timer " ;
		String myDoubleString = myFormat.format(delta);
		yourTimeName= yourTimeName + myDoubleString;
		batch.begin();
		batch.draw(campus, 0,0,width,height);
		HelpButton.render(batch);
		drawcompleted(batch);
		yourBitmapFontName.setColor(1.0f, 0.0f, 0.0f, 1.0f);
		yourBitmapFontName.setScale(1.2f, 1.2f);;
		yourBitmapFontName.draw(batch, yourTimeName, 40, 460); 
		batch.end();	
	}
	
	public void init_rects(){
		rects.add(dorm_rect);
		rects.add(acad_rect);
		rects.add(lib_rect);
		rects.add(student_rect);
		rects.add(advise_rect);
		rects.add(career_rect);
	}
	
	public void drawcompleted(SpriteBatch batch){
		for(GameState gs: this.gsm.GameStates()){
			int i=0;
	
			while(gs!= this.gsm.getgamestate(i)){
				i++;
			}
			if(this.gsm.getgamestate(i)==null) {
				continue;
			}
		    if(i==ac && this.gsm.getgamestate(i).completed){
		    	batch.draw(check, width*.315f, height*.635f, check_size, check_size);
//		    	System.out.println("b"+ ac);
		    }
		    else if(i==student && this.gsm.getgamestate(i).completed){
		    	batch.draw(check,width*.56f, height*.13f,check_size, check_size);
//		    	System.out.println("b"+student);
		
		    }
		    else if(i==advise && this.gsm.getgamestate(i).completed){
		    	batch.draw(check,width*.7f, height*.295f,check_size, check_size);
//		    	System.out.println("b"+advise);
		
		    }
		    else if(i==dorm && this.gsm.getgamestate(i).completed){
		    	batch.draw(check, width*.575f, height*.78f,check_size, check_size);
//		    	System.out.println("b"+dorm);
		
		    }
		    else if(i==career && this.gsm.getgamestate(i).completed){
		    	batch.draw(check, width*.68f, height*.61f,check_size, check_size);
//		    	System.out.println("b"+career);
		
		    }
		    else if(i==lib && this.gsm.getgamestate(i).completed){
		    	batch.draw(check, width*.23f, height*.375f,check_size, check_size);
//		    	System.out.println("b");
		    }
		    i++;
		}
	    
	}
	
	public void update(){
		mouse_pos_check();
		mouse_click_check();
		HelpButton.checkIfClicked(Gdx.input.getX(),Gdx.input.getY());
		
		if(HelpButton.clicked)
			gsm.setState(GSM.Help);
		int i=0;
		for(GameState GS: this.gsm.GameStates()){
			if(GS == this.gsm.getgamestate(GSM.Map)){
				break;
			}
			if(GS.completed){
				i++;
				if (i == 6){
					this.gsm.setState(GSM.Finish);
				}
			}
		
		}
		
	}
	
	private void mouse_click_check() {
		if(clickedDormRoom()){
			gsm.setState(GSM.DormRoom);
		}
		if(clickedAcademic()){
			gsm.setState(gsm.Academic);
		}
		if(clickedMorrisLibrary()){
			gsm.setState(GSM.MorrisLibrary);
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
//			System.out.println("academic");
	
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
//					System.out.println("hello");
	
					rect.within=true;
				}
			}
			else rect.within=false;
		}
		
	}
	

}
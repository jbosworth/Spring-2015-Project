package edu.udel.cisc275_15S.UDevelopers;

import java.awt.Graphics2D;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;



public class GSM {
	private GameState[] gameStates;
	private int currentstate;//save the state you're in
	SpriteBatch batch;
	boolean maptime=false;
	float stateTime=0;
	public static final int NUMGAMESTATES=11;
	public static final int CareerServices = 5;
	public static final int Advisement = 4;
	public static final int StudentHealth = 3;
	public static final int MorrisLibrary = 2;
	public static final int Academic = 1;
	public static final int DormRoom = 0;
	public static final int Map=6;
	public static final int Start = 7;
	public static final int Tutorial=8;
	public static final int Finish=9;
	public static final int Help=10;

//The GSM switches between the gamestates
	
	public GSM(SpriteBatch batch){
		this.batch=batch;
    gameStates=new GameState[NUMGAMESTATES];
    this.loadState(this.Start);
   
    currentstate=Start;    
     setState(currentstate);
	}
	
	//initializes a state
	public void loadState(int state){
        currentstate=state;
		if(state==this.Academic){
			gameStates[state]=new Academic(this, batch);
		}
		else if(state==this.Advisement){
			gameStates[state]=new Advisement(this, batch);

		}
		else if(state==this.Finish){
			gameStates[state]=new Finish(this, batch);

		}
		else if(state==this.Tutorial){
			gameStates[state]=new Tutorial(this, batch);

		}
		else if(state==this.CareerServices){
			gameStates[state]=new CareerServices(this, batch);

		}
		else if(state==this.DormRoom){
			gameStates[state]=new DormRoom(this, batch);

		}
		else if(state==this.Map){
			gameStates[state]=new Map(this, batch);
			maptime=true;

		}
		else if(state==this.MorrisLibrary){
			gameStates[state]=new MorrisLibrary(this,batch);

		}
		else if(state==this.StudentHealth){
			gameStates[state]=new StudentHealth(this,batch);

		}
		else if(state==this.Start){
			gameStates[state]=new Start(this, batch);

		}
		else if(state==this.Help){
			gameStates[state]=new Help(this, batch);

		}
		
	}
	
	public void InitializeAllStates(){
		for(int i = 0; i < 6; i++){
			loadState(i);
			
		}
	}
	
	//deletes a state
	public void UnloadState(int state){
		gameStates[state]=null;
	}
	
	public GameState[] GameStates(){
		return this.gameStates;
	}
	
	public int getstate(){
		return currentstate;
	}
	public GameState getgamestate(int i){
		return gameStates[i];
	}
	
	//changes the current state that the gsm is on
	public void setState(int state){
		

		
		currentstate= state;

		if(this.gameStates[state]==null)
		loadState(state);
		
		//gameStates[currentstate].init();
	}
	
	//updates whatever state is the current state
	public void update(){
		if( gameStates[currentstate]==null)
			System.out.println("boop" + currentstate );
		
		else ;

try{
		gameStates[currentstate].update();}
catch(Exception e){
	e.printStackTrace();
}
			}
	
	public void render(){
		update();
		if(maptime && this.currentstate!=this.Help){
		 float deltaTime = Gdx.graphics.getDeltaTime();
		 this.stateTime += deltaTime;
		}
		gameStates[currentstate].render(this.stateTime);

	}
	
	




}

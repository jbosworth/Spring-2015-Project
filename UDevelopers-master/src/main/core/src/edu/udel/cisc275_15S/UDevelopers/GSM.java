import java.awt.Graphics2D;

import GameState.GameState;
import GameState.Level1State;
import GameState.MenuState;


public class GSM {
	private GameState[] gameStates;
	private int currentstate;//save the state you're in
	
	public static final int NUMGAMESTATES=2;
	public static final int MenuState=0;
	public static final int Level1State=1;
	
	public GSM(){
    gameStates=new GameState[NUMGAMESTATES];
    gameStates[MenuState]= new MenuState(this);
    gameStates[Level1State]= new Level1State(this);
    currentstate=MenuState;    
     setState(currentstate);
	}
	
	public void loadState(int state){
		System.out.println("poop"+ state );

		if(state==MenuState){
			gameStates[state]=new MenuState(this);
		}
		else if(state==Level1State){
			System.out.println("bloop" + currentstate);
			gameStates[state]=new Level1State(this);
if(gameStates[currentstate] ==null)
	System.out.println("loop" );

		}
		
	}
	public void UnloadState(int state){
		gameStates[state]=null;
	}
	
	public void setState(int state){
		System.out.println("current state is " + currentstate );
		System.out.println("state you wanna be in is " + state );


		
		currentstate= state;

		
		loadState(state);
		
		//gameStates[currentstate].init();
	}
	
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
	
	public void draw(Graphics2D g){
		gameStates[currentstate].draw(g);
	}
	
	public void keyPressed(int k){
		gameStates[currentstate].keyPressed(k);
	}
	
	public void keyReleased(int k){
		gameStates[currentstate].keyReleased(k);
	}

}


}

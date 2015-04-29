package edu.udel.cisc275_15S.UDevelopers;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
 
public abstract class GameState {
GSM gsm;

public abstract void update();
public abstract void render(SpriteBatch batch);
}


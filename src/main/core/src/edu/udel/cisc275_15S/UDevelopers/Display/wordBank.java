package edu.udel.cisc275_15S.UDevelopers.Display;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class wordBank {
	public final static int wordBankButton = 10;
	float width;
	float height;
	SpriteBatch batch; 
	
	float[] bankDim;
	genericButton wordBankBook;
	BitmapFont bankFont;
	Texture wordBank;
	buttonHandler handle;
	
	public wordBank(SpriteBatch batch) {
		this.width = Gdx.graphics.getWidth();
		this.height = Gdx.graphics.getHeight();
		this.batch = batch;
		
		this.wordBank = new Texture("open-notebook.png");
		this.bankDim = new float[]{0, height*0.5f, width*0.5f, height*0.5f};
		this.bankFont = new BitmapFont();
        this.bankFont.setColor(Color.BLUE);
        this.wordBankBook = new genericButton(new Texture("notebook.png"),width*0.85f, height*0.80f, width*0.15f, height*0.20f, "Word\nBank");
        this.wordBankBook.setId(wordBankButton);
        this.handle = buttonHandler.getInstance();
        handle.addButtons(wordBankBook);
	}
	
	public void render() {
		int words = 0;
		ArrayList<String> text = new ArrayList<String>();
		text.add("Text");
		text.add("Goes");
		text.add("Here");
		
		float x = bankDim[0];
		float y =  bankDim[1];
		float width =  bankDim[2];
		float height =  bankDim[3];

		if (handle.isButtonClicked(wordBankButton)) {
			
			batch.begin();
			batch.draw(wordBank, x, y, width, height);
			for (String s : text) {
				bankFont.drawWrapped(batch, s, x + width*0.17f, y + height*0.875f - height*0.1f*words, width, BitmapFont.HAlignment.LEFT);
				words++;
			}
			batch.end();		
		}	
	}
	public void dispose() {
		bankFont.dispose();
		wordBank.dispose();
	}
}

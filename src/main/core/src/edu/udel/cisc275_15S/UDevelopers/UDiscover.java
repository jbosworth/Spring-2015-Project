package edu.udel.cisc275_15S.UDevelopers;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;

public class UDiscover extends ApplicationAdapter {
	SpriteBatch batch; 
	Texture background;
	Texture player;
	double elapsed;
	
	double endTime;
	boolean running;
	
	BitmapFont font;
	String speech;
	
	ShapeRenderer shapeRenderer;
	Camera camera;
	
	float width;
	float height;
	
	Stage stage;
    TextButton button;
    TextButtonStyle textButtonStyle;
    BitmapFont buttonFont;
    Skin skin;
    TextureAtlas buttonAtlas;
	 
	@Override
	public void create () {
		
		width = Gdx.graphics.getWidth();
		height = Gdx.graphics.getHeight();
		
		batch = new SpriteBatch();
		background = new Texture("sampleUD2.jpg");
		player = new Texture("transparent.png");
		shapeRenderer = new ShapeRenderer();
//		camera = new OrthographicCamera(200, 200);
		
		font = new BitmapFont();
        font.setColor(Color.WHITE);
		
        //create button
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        
        skin = new Skin();
		// Generate a 1x1 white texture and store it in the skin named "white".
		Pixmap pixmap = new Pixmap(100, 100, Format.RGBA8888);
		pixmap.setColor(Color.GREEN);
		pixmap.fill();
 
		skin.add("white", new Texture(pixmap));
 
		// Store the default libgdx font under the name "default".
		BitmapFont bfont=new BitmapFont();
		bfont.scale(1);
		skin.add("default",bfont);
 
		// Configure a TextButtonStyle and name it "default". Skin resources are stored by type, so this doesn't overwrite the font.
		TextButtonStyle textButtonStyle = new TextButtonStyle();
		textButtonStyle.up = skin.newDrawable("white", Color.DARK_GRAY);
		textButtonStyle.down = skin.newDrawable("white", Color.DARK_GRAY);
		textButtonStyle.checked = skin.newDrawable("white", Color.BLUE);
		textButtonStyle.over = skin.newDrawable("white", Color.LIGHT_GRAY);
 
		textButtonStyle.font = skin.getFont("default");
 
		skin.add("default", textButtonStyle);
 
		// Create a button with the "default" TextButtonStyle. A 3rd parameter can be used to specify a name other than "default".
		final TextButton textButton=new TextButton("New \n Screen",textButtonStyle);
		textButton.setPosition(width*0.5f, height*0.8f);
		stage.addActor(textButton);
		stage.addActor(textButton);
		stage.addActor(textButton);
		
		//TEMPORARY dialog 
		speech = "Dialog goes here";
	}

	@Override
	public void render () {

		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		 
		//Render images 
		batch.begin();
		batch.disableBlending();
		batch.draw(background, 0, 0, width, height);
		batch.enableBlending();
		batch.draw(player, 0, 0, width/3, height*0.8f);
		
		font.draw(batch, speech, 180, 150);

		
		batch.end();
		
		//Draw Button
	//	Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
	//	Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
		stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
		stage.draw();
		//Table.drawDebug(stage);
		
		
		//Render transparent rectangle
	    Gdx.gl.glEnable(GL30.GL_BLEND);
		Gdx.gl.glBlendFunc(GL30.GL_SRC_ALPHA, GL30.GL_ONE_MINUS_SRC_ALPHA);
		shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
		shapeRenderer.begin(ShapeType.Filled);
		shapeRenderer.setColor(new Color(0, 1, 0, 0.5f));
		shapeRenderer.rect(width*0.05f, height*0.05f, width*0.9f, height*0.3f);
		shapeRenderer.end();
		Gdx.gl.glDisable(GL30.GL_BLEND);
		}
}

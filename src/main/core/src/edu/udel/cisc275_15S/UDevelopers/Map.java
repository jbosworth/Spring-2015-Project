import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class Map {
public Map(){
	//System.out.println(Gdx.files.internal("libs/campus.jpg").file().getAbsolutePath());
}
Texture campus= new Texture(Gdx.files.internal("res/campus.jpg"));
Sprite sb= new Sprite(campus);
Boolean dormroom, academic, morris,carrer,advisement,health;


public void draw(SpriteBatch batch){
	sb.draw(batch);
}

public void update(){
	mouse_pos_check();
	mouse_click_check();
	
}

private void mouse_click_check() {
	// TODO Auto-generated method stub
	
}

private void mouse_pos_check() {
	// TODO Auto-generated method stub
	
}


}

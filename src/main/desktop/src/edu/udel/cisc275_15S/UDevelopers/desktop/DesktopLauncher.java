package edu.udel.cisc275_15S.UDevelopers.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;


public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new core.src.edu.udel.cisc275_15S.UDevelopers.Display.UDiscover(), config);
	}
}

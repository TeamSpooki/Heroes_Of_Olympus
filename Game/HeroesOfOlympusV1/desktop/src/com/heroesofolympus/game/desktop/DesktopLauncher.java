package com.heroesofolympus.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.heroesofolympus.game.Main;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.foregroundFPS = 60;
		config.width = Main.WIDTH;
		config.height = Main.HEIGHT;
		config.resizable = false;
		new LwjglApplication(new Main(), config);
	}
}

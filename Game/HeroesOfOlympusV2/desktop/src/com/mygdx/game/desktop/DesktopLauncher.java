package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.HeroesOfOlympus;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.foregroundFPS = 60;
		config.width = HeroesOfOlympus.WIDTH;
		config.height = HeroesOfOlympus.HEIGHT;
		config.resizable = false;
		new LwjglApplication(new HeroesOfOlympus(), config);
	}
}

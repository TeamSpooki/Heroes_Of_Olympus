package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class HeroesOfOlympus extends Game {

	public static final int WIDTH = 1280;
	public static final int HEIGHT = 1080;
	public SpriteBatch batch;
	Level level;
	@Override
	public void create () {
		batch = new SpriteBatch();
		this.setScreen(new MainMenuScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
	
}

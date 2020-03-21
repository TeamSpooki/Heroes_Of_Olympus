package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.level.Level;
import com.mygdx.screen.MainMenuScreen;

/**
 * Game class representing a single instance of the game and the sprite batch
 */
public class HeroesOfOlympus extends Game {
	/**
	 * Width of the screen
	 */
	public static final int WIDTH = 1080;
	/**
	 * Height of the screen
	 */
	public static final int HEIGHT = 850;
	/**
	 * Single batch used throughout the game
	 */
	public SpriteBatch batch;
	/**
	 * Single instance of level that is overwritten
	 */
    public Level level;
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

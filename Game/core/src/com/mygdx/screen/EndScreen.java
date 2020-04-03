package com.mygdx.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.HeroesOfOlympus;

public class EndScreen implements Screen{
	/**
	 * Sets the width and height of the EndScreen
	 */
	private static final int WIDTH = HeroesOfOlympus.WIDTH;
	private static final int HEIGHT = HeroesOfOlympus.HEIGHT;
	/**
	 * Sets the width and height of the back button
	 */
	private static final int BACK_BUTTON_WIDTH = 200;
	private static final int BACK_BUTTON_HEIGHT = 100;
	/**
	 * Sets the positioning of the button
	 */
	private static final int BACK_BUTTON_Y = HEIGHT-BACK_BUTTON_HEIGHT;
	/**
	 * game variable instance of HeroesOfOlympus
	 */
	private HeroesOfOlympus game;
	/**
	 * mms variable instance of MainMenuScreen
	 */
	private MainMenuScreen mms;
	/**
	 * Create texture backButtonActive variable to highlight button on hover
	 * Create texture backButtonInactive variable to remove button highlight on hover
	 */
	private Texture backButtonActive;
	private Texture backButtonInactive;
	/**
	 * Create texture goImage variable 
	 */
	private Texture goImage;
	/**
	 * Create TextureRegion mainBackground variable for main background image
	 */
	private TextureRegion mainBackground;
	/**
	 * Declare music variable to enable sound in GameOverScreen
	 */
	private Music music;
	/**
	 * Declare variable x
	 */
	private float x;

	/**
	 * EndScreen Constructor
	 */	
	public EndScreen(HeroesOfOlympus game) {
		this.game = game;
		goImage = new Texture("YOU WIN.png");
		backButtonActive = new Texture("BackButtonHighlighted.png");
		backButtonInactive = new Texture("BackButton.png");
		mainBackground = new TextureRegion(goImage, 0, 0, 1280, 896);
		mms = new MainMenuScreen(game);

		music= Gdx.audio.newMusic(Gdx.files.internal("Sounds/Victory Screen.wav"));
		music.setLooping(true);
		music.setVolume(0.1f);
		this.resize(WIDTH,HEIGHT);
		game.level = null;
	}
	@Override
	public void show() {

	}
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		music.play();
		game.batch.begin();
		game.batch.draw(mainBackground, 0, 0, WIDTH, HEIGHT);
		x = 0;
		// Sets the positioning of the back button
		if (Gdx.input.getX() < x + BACK_BUTTON_WIDTH && Gdx.input.getX() > x && HEIGHT - Gdx.input.getY() < BACK_BUTTON_Y + BACK_BUTTON_HEIGHT
				&& HEIGHT - Gdx.input.getY() > BACK_BUTTON_Y) {
			game.batch.draw(backButtonActive, x, BACK_BUTTON_Y, BACK_BUTTON_WIDTH, BACK_BUTTON_HEIGHT);
			// When button is clicked on
			if (Gdx.input.isTouched()) {
				// Removes back button
				this.dispose();
				// Sets the screen back to main menu
				game.setScreen(mms);
			}
		} else {
			game.batch.draw(backButtonInactive, x, BACK_BUTTON_Y, BACK_BUTTON_WIDTH, BACK_BUTTON_HEIGHT);
		}
		game.batch.end();

	}
	@Override
	public void resize(int width, int height) {

	}
	@Override
	public void pause() {

	}
	@Override
	public void resume() {

	}
	@Override
	public void hide() {

	}
	@Override
	public void dispose() {
		music.dispose();
	}
}

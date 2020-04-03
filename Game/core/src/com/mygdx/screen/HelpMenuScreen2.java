package com.mygdx.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.HeroesOfOlympus;

public class HelpMenuScreen2 implements Screen {
	/**
	 * Sets the width and height of the HelpMenuScreen2
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
	 * Game variable
	 */
	private HeroesOfOlympus game;
	/**
	 * Create texture for enabling to display bgImage
	 */
	private Texture bgImage;
	/**
	 * Create texture for enabling to display main background
	 */
	private TextureRegion mainBackground;
	/**
	 * Create texture variable backButtonActive to highlight button on hover
	 * Create texture variable backButtonInactive to remove button highlight on hover
	 */
	private Texture backButtonActive;
	private Texture backButtonInactive;
	/**
	 * mms variable instance of MainMenuScreen
	 */
	private MainMenuScreen mms;
	/**
	 * Declare music variable to enable sound
	 */
	private Music music;
	/**
	 * Declare variable x
	 */
	private float x;
	/**
	 * HelpMenuScreen2 Constructor
	 */
	public HelpMenuScreen2(HeroesOfOlympus game) {
		this.game = game;
		backButtonActive = new Texture("BackButtonHighlighted.png");
		backButtonInactive = new Texture("BackButton.png");

		bgImage = new Texture("HELP SCREEN 2.png");
		mainBackground = new TextureRegion(bgImage, 0, 0, WIDTH, HEIGHT);
		mms = new MainMenuScreen(game);

		music= Gdx.audio.newMusic(Gdx.files.internal("Sounds/Help Menu.wav"));
		music.setLooping(true);
		music.setVolume(0.1f);
	}

	@Override
	public void show() {		
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		game.batch.begin();
		music.play();
		game.batch.draw(mainBackground, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		x = 0;
		// Sets the positioning of the back button
		if (Gdx.input.getX() < x + BACK_BUTTON_WIDTH && Gdx.input.getX() > x && HEIGHT - Gdx.input.getY() < BACK_BUTTON_Y + BACK_BUTTON_HEIGHT
				&& HEIGHT - Gdx.input.getY() > BACK_BUTTON_Y) {
			game.batch.draw(backButtonActive, x, BACK_BUTTON_Y, BACK_BUTTON_WIDTH, BACK_BUTTON_HEIGHT);
			// When button is clicked on
			if (Gdx.input.isTouched()) {
				// Removes back button
				this.dispose();
				// Sets back to main menu screen
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

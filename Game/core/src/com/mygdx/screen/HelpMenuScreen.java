package com.mygdx.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.HeroesOfOlympus;

public class HelpMenuScreen implements Screen{
	/**
	 * Sets the width and height of the HelpMenuScreen
	 */
	private static final int WIDTH = HeroesOfOlympus.WIDTH;
	private static final int HEIGHT = HeroesOfOlympus.HEIGHT;
	/**
	 * Sets the width and height of the back button
	 */
	private static final int BACK_BUTTON_WIDTH = 200;
	private static final int BACK_BUTTON_HEIGHT = 100;
	private static final int BACK_BUTTON_Y = HEIGHT-BACK_BUTTON_HEIGHT;
	/**
	 * Sets the width and height of the next button
	 */
	private static final int NEXT_BUTTON_WIDTH = 200;
	private static final int NEXT_BUTTON_HEIGHT = 100;
	private static final int NEXT_BUTTON_Y = HEIGHT-NEXT_BUTTON_HEIGHT;
	/**
	 * game variable instance of HeroesOfOlympus
	 */
	private HeroesOfOlympus game;
	/**
	 * Create texture bgImage variable
	 */
	private Texture bgImage;
	/**
	 * Create TextureRegion mainBackground variable to display help screen background
	 */
	private TextureRegion mainBackground;
	/**
	 * Create texture backButtonActive variable to highlight button on hover
	 * Create texture backButtonInactive variable to remove button highlight on hover
	 */
	private Texture backButtonActive;
	private Texture backButtonInactive;
	/**
	 * Create texture nextButtonActive variable to highlight button on hover
	 * Create texture nextButtonInactive variable to remove button highlight on hover
	 */
	private Texture nextButtonActive;
	private Texture nextButtonInactive;
	/**
	 * Create mms variable instance of MainMenuScreen
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
	 * HelpMenuScreen Constructor
	 */
	public HelpMenuScreen(HeroesOfOlympus game) {
		this.game = game;
		backButtonActive = new Texture("BackButtonHighlighted.png");
		backButtonInactive = new Texture("BackButton.png");
		nextButtonActive = new Texture("NextButtonHighlighted.png");
		nextButtonInactive = new Texture("NextButton.png");
		bgImage = new Texture("HELP SCREEN.png"); 
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

		//Sets the positioning of the back button 
		if (Gdx.input.getX() < x + BACK_BUTTON_WIDTH && Gdx.input.getX() > x && HEIGHT - Gdx.input.getY() < BACK_BUTTON_Y + BACK_BUTTON_HEIGHT
				&& HEIGHT - Gdx.input.getY() > BACK_BUTTON_Y) {
			game.batch.draw(backButtonActive, x, BACK_BUTTON_Y, BACK_BUTTON_WIDTH, BACK_BUTTON_HEIGHT);
			// When button is clicked on
			if (Gdx.input.isTouched()) {
				//Remove back button
				this.dispose();
				//Sets back to main menu screen
				game.setScreen(mms);
			}
		} else {
			game.batch.draw(backButtonInactive, x, BACK_BUTTON_Y, BACK_BUTTON_WIDTH, BACK_BUTTON_HEIGHT);
		}

		x = WIDTH-NEXT_BUTTON_WIDTH;
		//Sets the positioning of the next button 
		if (Gdx.input.getX() < x + NEXT_BUTTON_WIDTH && Gdx.input.getX() > x && HEIGHT - Gdx.input.getY() < NEXT_BUTTON_Y + NEXT_BUTTON_HEIGHT
				&& HEIGHT - Gdx.input.getY() > NEXT_BUTTON_Y) {
			game.batch.draw(nextButtonActive, WIDTH-NEXT_BUTTON_WIDTH, NEXT_BUTTON_Y, NEXT_BUTTON_WIDTH, NEXT_BUTTON_HEIGHT);
			// When button is clicked on
			if (Gdx.input.isTouched()) {
				//Remove next button
				this.dispose();
				//Sets screen to HelpMenuScreen2
				game.setScreen(new HelpMenuScreen2(game));
			}
		} else {
			game.batch.draw(nextButtonInactive, WIDTH-NEXT_BUTTON_WIDTH, NEXT_BUTTON_Y, NEXT_BUTTON_WIDTH, NEXT_BUTTON_HEIGHT);
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


package com.mygdx.screen;

import java.io.FileNotFoundException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.HeroesOfOlympus;
import com.badlogic.gdx.graphics.GL20;


public class MainMenuScreen implements Screen {
	/**
	 * Sets the width and height of the main menu screen
	 */
	private static final int WIDTH = HeroesOfOlympus.WIDTH;
	private static final int HEIGHT = HeroesOfOlympus.HEIGHT;
	/**
	 * Sets the height/position of the game logo
	 */
	private static final int GAME_LOGO_HEIGHT = HEIGHT / 4;
	/**
	 * Sets the width and height of the play button
	 */
	private static final int PLAY_BUTTON_WIDTH = 400;
	private static final int PLAY_BUTTON_HEIGHT = 200;
	/**
	 * Sets the width and height of the exit button
	 */
	private static final int EXIT_BUTTON_WIDTH = 400;
	private static final int EXIT_BUTTON_HEIGHT = 200;
	/**
	 * Sets the width and height of the help button
	 */
	private static final int HELP_BUTTON_WIDTH = 400;
	private static final int HELP_BUTTON_HEIGHT = 200;
	/**
	 * Sets the width and height of the team logo
	 */
	private static final int TEAM_LOGO_WIDTH = WIDTH / 5;
	private static final int TEAM_LOGO_HEIGHT = HEIGHT / 5;
	/**
	 * Sets the position of the buttons on the main menu screen
	 */
	private static final int PLAY_BUTTON_Y = HEIGHT - GAME_LOGO_HEIGHT-PLAY_BUTTON_HEIGHT;
	private static final int HELP_BUTTON_Y = PLAY_BUTTON_Y-HELP_BUTTON_HEIGHT;
	private static final int EXIT_BUTTON_Y = HELP_BUTTON_Y-EXIT_BUTTON_HEIGHT;
	/**
	 * Declare music variable 
	 */
	private Music music;
	/**
	 * Create texture playButtonActive variable to highlight button on hover
	 * Create texture playButtonInactive variable to remove button highlight on hover
	 */
	private Texture playButtonActive;
	private Texture playButtonInactive;
	/**
	 * Create texture helpButtonActive to highlight button on hover
	 * Create helpButtonInactive to remove button highlight on hover
	 */
	private Texture helpButtonActive;
	private Texture helpButtonInactive;
	/**
	 * Create texture exitButtonActive to highlight button on hover
	 * Create exitButtonInactive to remove button highlight on hover
	 */
	private Texture exitButtonActive;
	private Texture exitButtonInactive;
	/**
	 * Create texture gameLogo variable to display logo
	 */
	private Texture gameLogo;
	/**
	 * Create texture teamLogo variable to display team icon
	 */
	private Texture teamLogo;
	/**
	 * Create texture bgImage variable 
	 */
	private Texture bgImage;
	/**
	 * Create textureRegion mainBackground variable for main background image
	 */
	private TextureRegion mainBackground;
	/**
	 * Game variable
	 */
	private HeroesOfOlympus game;
	/**
	 * Declare variable x
	 */
	private float x;
	/**
	 * MainMenuScreen constructor
	 */
	public MainMenuScreen(HeroesOfOlympus game) {
		this.game = game;

		playButtonActive = new Texture("PlayHighlighted.png");
		playButtonInactive = new Texture("Play.png");
		exitButtonActive = new Texture("ExitHighlighted.png");
		exitButtonInactive = new Texture("Exit.png");
		helpButtonActive = new Texture("HelpHighlighted.png");
		helpButtonInactive = new Texture("Help.png");
		gameLogo = new Texture("gameLogo.png");
		teamLogo = new Texture("logo.png");
		music= Gdx.audio.newMusic(Gdx.files.internal("Sounds/Menu.wav"));
		music.setLooping(true);
		music.setVolume(0.1f);
		bgImage = new Texture("MainMenuBG.png");
		mainBackground = new TextureRegion(bgImage, 0, 0, WIDTH, HEIGHT);
	}

	@Override
	public void show() {

	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(192/255f,192/255f,192/255f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		music.play();
		game.batch.begin();
		game.batch.draw(mainBackground, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		x = WIDTH / 2 - PLAY_BUTTON_WIDTH / 2;
		// Sets the positioning of the play button on main menu
		if (Gdx.input.getX() < x + PLAY_BUTTON_WIDTH && Gdx.input.getX() > x && HEIGHT - Gdx.input.getY() < PLAY_BUTTON_Y + PLAY_BUTTON_HEIGHT 
				&& HEIGHT - Gdx.input.getY() > PLAY_BUTTON_Y) {
			game.batch.draw(playButtonActive, x, PLAY_BUTTON_Y, PLAY_BUTTON_WIDTH, PLAY_BUTTON_HEIGHT);
			// When the button is clicked on 
			if (Gdx.input.isTouched()) {
				// removes button
				this.dispose();
				// try/catch exception to load new story
				try {
					game.setScreen(new Story(game,"scene1.ogv"));
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
		} else {
			game.batch.draw(playButtonInactive, x, PLAY_BUTTON_Y, PLAY_BUTTON_WIDTH, PLAY_BUTTON_HEIGHT);
		}

		x = WIDTH / 2 - HELP_BUTTON_WIDTH / 2;
		// Sets the positioning of the help button on main menu
		if (Gdx.input.getX() < x + HELP_BUTTON_WIDTH && Gdx.input.getX() > x && HEIGHT - Gdx.input.getY() < HELP_BUTTON_Y + HELP_BUTTON_HEIGHT 
				&& HEIGHT - Gdx.input.getY() > HELP_BUTTON_Y) {
			game.batch.draw(helpButtonActive, x, HELP_BUTTON_Y, HELP_BUTTON_WIDTH, HELP_BUTTON_HEIGHT);
			// When the button is clicked on 
			if (Gdx.input.isTouched()) {
				// removes button
				this.dispose();
				// Sets the screen to Help
				game.setScreen(new HelpMenuScreen(game));
			}
		} else {
			game.batch.draw(helpButtonInactive, x, HELP_BUTTON_Y, HELP_BUTTON_WIDTH, HELP_BUTTON_HEIGHT);
		}

		int x = WIDTH / 2 - EXIT_BUTTON_WIDTH / 2;
		// Sets the positioning of the exit button on main menu
		if (Gdx.input.getX() < x + EXIT_BUTTON_WIDTH && Gdx.input.getX() > x && HEIGHT - Gdx.input.getY() < EXIT_BUTTON_Y + EXIT_BUTTON_HEIGHT 
				&& HEIGHT - Gdx.input.getY() > EXIT_BUTTON_Y) {
			game.batch.draw(exitButtonActive, x, EXIT_BUTTON_Y, EXIT_BUTTON_WIDTH, EXIT_BUTTON_HEIGHT);
			// When the button is clicked on
			if (Gdx.input.isTouched()) {
				// exits the application
				Gdx.app.exit();
			}
		} else {
			game.batch.draw(exitButtonInactive, x, EXIT_BUTTON_Y, EXIT_BUTTON_WIDTH, EXIT_BUTTON_HEIGHT);
		}

		game.batch.draw(gameLogo, 0, HEIGHT - GAME_LOGO_HEIGHT, WIDTH, GAME_LOGO_HEIGHT);
		game.batch.draw(teamLogo, WIDTH - TEAM_LOGO_WIDTH, 0, TEAM_LOGO_WIDTH, TEAM_LOGO_HEIGHT);

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

package com.mygdx.screen;

import java.io.FileNotFoundException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.HeroesOfOlympus;
import com.badlogic.gdx.graphics.GL20;

public class MainMenuScreen implements Screen {
	private static final int WIDTH = HeroesOfOlympus.WIDTH;
	private static final int HEIGHT = HeroesOfOlympus.HEIGHT;
	public static final float SPEED = 120;

	private static final int GAME_LOGO_HEIGHT = HeroesOfOlympus.HEIGHT / 4;

	private static final int PLAY_BUTTON_WIDTH = 400;
	private static final int PLAY_BUTTON_HEIGHT = 200;

	private static final int EXIT_BUTTON_WIDTH = 400;
	private static final int EXIT_BUTTON_HEIGHT = 200;

	private static final int HELP_BUTTON_WIDTH = 400;
	private static final int HELP_BUTTON_HEIGHT = 200;

	private static final int TEAM_LOGO_WIDTH = HeroesOfOlympus.WIDTH / 5;
	private static final int TEAM_LOGO_HEIGHT = HeroesOfOlympus.HEIGHT / 5;

	private static final int PLAY_BUTTON_Y = HeroesOfOlympus.HEIGHT / 2;
	private static final int HELP_BUTTON_Y = HeroesOfOlympus.HEIGHT / 3;
	private static final int EXIT_BUTTON_Y = HeroesOfOlympus.HEIGHT / 6;

	Texture playButtonActive;
	Texture playButtonInactive;

	Texture helpButtonActive;
	Texture helpButtonInactive;

	Texture exitButtonActive;
	Texture exitButtonInactive;

	Texture gameLogo;
	Texture teamLogo;

	Texture bgImage;
	TextureRegion mainBackground;

	HeroesOfOlympus game;

	float x;
	float y;

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

		bgImage = new Texture("MainMenuBG.png");
		mainBackground = new TextureRegion(bgImage, 0, 0, 1280, 1080);
	}

	@Override
	public void show() {

	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(192/255f,192/255f,192/255f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		game.batch.begin();
		game.batch.draw(mainBackground, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		x = WIDTH / 2 - PLAY_BUTTON_WIDTH / 2;

		if (Gdx.input.getX() < x + PLAY_BUTTON_WIDTH && Gdx.input.getX() > x && HEIGHT - Gdx.input.getY() < PLAY_BUTTON_Y + PLAY_BUTTON_HEIGHT 
				&& HEIGHT - Gdx.input.getY() > PLAY_BUTTON_Y) {
			game.batch.draw(playButtonActive, x, PLAY_BUTTON_Y, PLAY_BUTTON_WIDTH, PLAY_BUTTON_HEIGHT);
			if (Gdx.input.isTouched()) {
				this.dispose();
				try {
					game.setScreen(new Story(game,"scene1.ogv"));
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} else {
			game.batch.draw(playButtonInactive, x, PLAY_BUTTON_Y, PLAY_BUTTON_WIDTH, PLAY_BUTTON_HEIGHT);
		}

		x = WIDTH / 2 - HELP_BUTTON_WIDTH / 2;

		if (Gdx.input.getX() < x + HELP_BUTTON_WIDTH && Gdx.input.getX() > x && HEIGHT - Gdx.input.getY() < HELP_BUTTON_Y + HELP_BUTTON_HEIGHT 
				&& HEIGHT - Gdx.input.getY() > HELP_BUTTON_Y) {
			game.batch.draw(helpButtonActive, x, HELP_BUTTON_Y, HELP_BUTTON_WIDTH, HELP_BUTTON_HEIGHT);
			if (Gdx.input.isTouched()) {
				game.setScreen(new HelpMenuScreen(game));
				//Gdx.app.exit();
			}
		} else {
			game.batch.draw(helpButtonInactive, x, HELP_BUTTON_Y, HELP_BUTTON_WIDTH, HELP_BUTTON_HEIGHT);
		}

		int x = WIDTH / 2 - EXIT_BUTTON_WIDTH / 2;

		if (Gdx.input.getX() < x + EXIT_BUTTON_WIDTH && Gdx.input.getX() > x && HEIGHT - Gdx.input.getY() < EXIT_BUTTON_Y + EXIT_BUTTON_HEIGHT 
				&& HEIGHT - Gdx.input.getY() > EXIT_BUTTON_Y) {
			game.batch.draw(exitButtonActive, x, EXIT_BUTTON_Y, EXIT_BUTTON_WIDTH, EXIT_BUTTON_HEIGHT);
			if (Gdx.input.isTouched()) {
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
		// TODO Auto-generated method stub

	}
	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}
	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}
	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}
	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}
}

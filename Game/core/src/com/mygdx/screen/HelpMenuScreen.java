package com.mygdx.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.HeroesOfOlympus;

import java.io.FileNotFoundException;

public class HelpMenuScreen implements Screen{
	private static final int WIDTH = HeroesOfOlympus.WIDTH;
	private static final int HEIGHT = HeroesOfOlympus.HEIGHT;

	private static final int BACK_BUTTON_WIDTH = 200;
	private static final int BACK_BUTTON_HEIGHT = 100;
	private static final int BACK_BUTTON_Y = HEIGHT-BACK_BUTTON_HEIGHT;

	HeroesOfOlympus game;
	
	Texture bgImage;
	TextureRegion mainBackground;

	Texture backButtonActive;
	Texture backButtonInactive;
	
	MainMenuScreen mms;

	float x;
	
	public HelpMenuScreen(HeroesOfOlympus game) {
		// TODO Auto-generated constructor stub
		this.game = game;
		backButtonActive = new Texture("BackButtonHighlighted.png");
		backButtonInactive = new Texture("BackButton.png");
		bgImage = new Texture("HELP SCREEN.png"); 
		mainBackground = new TextureRegion(bgImage, 0, 0, WIDTH, HEIGHT);
		mms = new MainMenuScreen(game);
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();

		game.batch.draw(mainBackground, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		x = 0;

		if (Gdx.input.getX() < x + BACK_BUTTON_WIDTH && Gdx.input.getX() > x && HEIGHT - Gdx.input.getY() < BACK_BUTTON_Y + BACK_BUTTON_HEIGHT
				&& HEIGHT - Gdx.input.getY() > BACK_BUTTON_Y) {
			game.batch.draw(backButtonActive, x, BACK_BUTTON_Y, BACK_BUTTON_WIDTH, BACK_BUTTON_HEIGHT);
			if (Gdx.input.isTouched()) {
				this.dispose();
				game.setScreen(mms);
			}
		} else {
			game.batch.draw(backButtonInactive, x, BACK_BUTTON_Y, BACK_BUTTON_WIDTH, BACK_BUTTON_HEIGHT);
		}

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


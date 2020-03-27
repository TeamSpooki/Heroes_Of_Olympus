package com.mygdx.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.HeroesOfOlympus;

public class HelpMenuScreen implements Screen{
	
	HeroesOfOlympus game;
	
	Texture bgImage;
	TextureRegion mainBackground;
	SpriteBatch batch;
	
	MainMenuScreen mms;
	
	public HelpMenuScreen(HeroesOfOlympus game) {
		// TODO Auto-generated constructor stub
		this.game = game;
		bgImage = new Texture("HELP SCREEN.png"); 
		mainBackground = new TextureRegion(bgImage, 0, 0, 1080, 850);
		batch = new SpriteBatch();
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
        batch.begin();
        batch.draw(mainBackground, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();
        
        if (Gdx.input.isTouched()) {
			game.setScreen(mms);
		}
		
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


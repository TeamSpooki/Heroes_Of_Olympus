package com.mygdx.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.HeroesOfOlympus;

public class GameOverScreen implements Screen{
	
	HeroesOfOlympus game;
	MainMenuScreen mms;
	
	Texture goImage;
	TextureRegion mainBackground;
	SpriteBatch batch;
	
	
	public GameOverScreen(HeroesOfOlympus game) {
		this.game = game;
		goImage = new Texture("Game Over.png");
		mainBackground = new TextureRegion(goImage, 0, 0, 1280, 1080);
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

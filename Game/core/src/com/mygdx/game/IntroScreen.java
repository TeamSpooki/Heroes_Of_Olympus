package com.mygdx.game;

import java.util.concurrent.TimeUnit;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.world.GameMap;
import com.mygdx.world.TiledGameMap;

public class IntroScreen implements Screen{
	
	HeroesOfOlympus game;
	GameMap gameMap;
	Texture nextButton;
	TextureRegion myTextureRegion;
	TextureRegionDrawable myTexRegionDrawable;
	ImageButton button;
	FloatingText floatingText;
	Stage stage;
	float x;
	float y;
	String text;
	
	IntroScreen(HeroesOfOlympus game) {
		this.game = game;
		text = "Test";
		
		nextButton = new Texture("Next button.png");
		myTextureRegion = new TextureRegion(nextButton);
        myTexRegionDrawable = new TextureRegionDrawable(myTextureRegion);
        button = new ImageButton(myTexRegionDrawable);
        button.setSize(80, 80);
        button.setPosition(HeroesOfOlympus.WIDTH-button.getWidth(),HeroesOfOlympus.HEIGHT-button.getHeight());
		gameMap = new TiledGameMap();
		floatingText = new FloatingText(text, TimeUnit.SECONDS.toMillis(15));
		floatingText.setColor(Color.WHITE);
		floatingText.setPosition(HeroesOfOlympus.WIDTH/20, HeroesOfOlympus.HEIGHT/6);
		floatingText.setDeltaY(50);
		stage = new Stage();
		stage.addActor(floatingText);
		stage.addActor(button);
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0,0,0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		game.batch.begin();
		
		if (Gdx.input.isTouched()) {
			this.dispose();
			game.setScreen(new MainGameScreen(game));
		}
		if (!floatingText.isAnimated()) {
		    floatingText.animate();
		}
		stage.act();
		stage.draw();
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

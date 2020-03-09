package com.mygdx.game;

import java.util.concurrent.TimeUnit;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.world.GameMap;
import com.mygdx.world.TiledGameMap;

public class OutroScreen implements Screen{

	HeroesOfOlympus game;
	GameMap gameMap;
	Texture nextButton;
	FloatingText floatingText;
	Stage stage;
	float x;
	float y;
	String text;
	
	OutroScreen(HeroesOfOlympus game) {
		this.game = game;
		text = "And with a final blow, every skeleton blocking our heroes path have fallen, \r\n"
				+ "but this still does not answer the question, \r\n"
				+ "why would skeletons be so close to Mount Olympus?\r\n" + 
				"In light of this, our heroes race their way to the top of Mount Olympus, \r\n"
				+ "only to witness destruction and chaos along the way,\r\n"
				+ " fallen warriors and crushed bones,\r\n "
				+ "scattered across the floor of the mountain.\r\n" + 
				"As our heroes reach the top of Mount Olympus,\r\n"
				+ " all there is left to be seen is destruction and ruin,\r\n"
				+ " and standing among the wreckage, \r\n"
				+ "our heroes face their greatest challenge yet.\r\n";
		//gameMap = new TiledGameMap();
		floatingText = new FloatingText(text, TimeUnit.SECONDS.toMillis(15));
		floatingText.setColor(Color.WHITE);
		floatingText.setPosition(HeroesOfOlympus.WIDTH/4, HeroesOfOlympus.HEIGHT/4);
		floatingText.setDeltaY(50);
		stage = new Stage();
		stage.addActor(floatingText);
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
			game.setScreen(new MainGameScreen(game,new Level2()));
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

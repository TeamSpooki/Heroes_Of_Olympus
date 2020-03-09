package com.mygdx.game;

import java.io.FileNotFoundException;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.video.VideoPlayerCreator;
import com.badlogic.gdx.video.*;
import com.mygdx.world.GameMap;

public class IntroScreen extends JFrame implements Screen {
	
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
	VideoPlayer video;
	VideoPlayerCreator creator; 
	boolean finished = false;
	
	IntroScreen(HeroesOfOlympus game) throws FileNotFoundException {
		this.game = game;
		text = "The story begins with our five mighty heroes:\r\n"
				+ " Hercules, Achilles, Theseus, Helen and Hippolyta, \r\n\""
				+ "cheering and parading out of a jungle after a victorious battle against the Chimaera. \r\n" + 
				"Until our heroes are ambushed! A horde of skeletons pounce out of the treeline and \r\n"
				+ "swings their swords at full force! \r\n"
				+ "No match for our heroes, one by one the skeletons fall and with the final skeleton \r\n"
				+ "clashing swords with Hercules, our heroes once again stand proud in victory.\r\n" + 
				"However, the fight is not over yet, knowing that no skeleton would ever travel \r\n"
				+ "so close to Mount Olympus, our heroes brace themselves for what is next to come.\r\n" + 
				"Across the path to Mount Olympus, more skeletons gather, \r\n"
				+ "and so our heroes prepare themselves as the battle is only just beginning.\r\n";
		nextButton = new Texture("Next button.png");
		myTextureRegion = new TextureRegion(nextButton);
        myTexRegionDrawable = new TextureRegionDrawable(myTextureRegion);
        button = new ImageButton(myTexRegionDrawable);
        button.setSize(80, 80);
        button.setPosition(HeroesOfOlympus.WIDTH-button.getWidth(),HeroesOfOlympus.HEIGHT-button.getHeight());
		//gameMap = new TiledGameMap();
		floatingText = new FloatingText(text, TimeUnit.SECONDS.toMillis(30));
		floatingText.setColor(Color.WHITE);
		floatingText.setPosition(HeroesOfOlympus.WIDTH/20, HeroesOfOlympus.HEIGHT/4);
		floatingText.setDeltaY(20);
		stage = new Stage();
		stage.addActor(floatingText);
		stage.addActor(button);
		//Initialise creator
		creator = new VideoPlayerCreator();
		//Initialise video
		video = new VideoPlayer();
		//video = creator.createVideoPlayer();
		//Create file handle to locate internal file
		FileHandle fileHandle = Gdx.files.internal("scene1-act1.ogv");
		//Check if file exists
		System.out.println("Exists?: " + fileHandle.exists());
		//CompletionListener listener = new VideoPlayer.CompletionListener();
		//play the specified file
		video.play(fileHandle);
		//Set when the video is finished player to true on completion listener
		video.setOnCompletionListener(new VideoPlayer.CompletionListener() {
			@Override
			public void onCompletionListener(FileHandle file) {
				finished = true;
			}
		});
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
			game.setScreen(new MainGameScreen(game, new Level1()));
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

package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Weapon{
	private static final int FRAME_COLS = 5, FRAME_ROWS = 1;
	Animation<TextureRegion> walkAnimation;
	Texture walkSheet;
	TextureRegion[] regions = new TextureRegion[FRAME_COLS];
	private TextureRegion[] walkFrames;
	private Texture texture;
	public Weapon() {
	}

	public Animation<TextureRegion> takeAction() {
		 //texture = new Texture(Gdx.files.internal("achilles11.png"));
		// Load the sprite sheet as a Texture
			walkSheet = new Texture(Gdx.files.internal("weapons.png"));
			
			// Use the split utility method to create a 2D array of TextureRegions. This is 
			// possible because this sprite sheet contains frames of equal size and they are 
			// all aligned.
			TextureRegion[][] tmp = TextureRegion.split(walkSheet, 
					walkSheet.getWidth() / FRAME_COLS,
					walkSheet.getHeight() / FRAME_ROWS);
			
			// Place the regions into a 1D array in the correct order, starting from the top 
			// left, going across first. The Animation constructor requires a 1D array.
			 walkFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
			int index = 0;
			for (int i = 0; i < FRAME_ROWS; i++) {
				for (int j = 0; j < FRAME_COLS; j++) {
					walkFrames[index++] = tmp[i][j];
				}
			}
			
			// Initialize the Animation with the frame interval and array of frames
			walkAnimation = new Animation<TextureRegion>(0.7f, walkFrames);
		
		  return walkAnimation;
	}
	public TextureRegion[] getWeaponFrames() {
		takeAction();
		return walkFrames;
	}
	public Sprite thesiusweapon() {
		  texture = getWeaponFrames()[1].getTexture();
		  Sprite send = new Sprite(texture, 0, 0 , 64, 200);
		  send.flip(true, true);
		  
		  return send;
	}
	public Sprite herculesweapon() {
		  texture =getWeaponFrames()[1].getTexture();
		  Sprite send = new Sprite(texture, 80, 0 , 64, 200);
		  send.flip(true, true);
		  
		  return send;
	}
	public Sprite achillesweapon() {
		  texture = getWeaponFrames()[1].getTexture();
		  Sprite send = new Sprite(texture, 128, 0 , 64, 200);
		  send.flip(true, true);
		  		  
		  return  send;
	}
	public Sprite hippolytaweapon() {
		  texture = getWeaponFrames()[1].getTexture();
		  Sprite send = new Sprite(texture, 128, 0 , 64, 200);
		  send.flip(true, true);
		  		  
		  return  send;
	}
	
	public Sprite dammyweapon() {
		  texture = getWeaponFrames()[1].getTexture();
		  Sprite send = new Sprite(texture, 128, 0 , 64, 200);
		  send.flip(true, true);
		  		  
		  return  send;
	}

}

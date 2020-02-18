package com.heroesofolympus.game;



import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;



public class Achille extends Hero {
	private SpriteBatch sb;
	Texture texture;
	Hero achilles;
	TextureRegion[] keyframe = new TextureRegion[2];

	private static final int FRAME_COLS = 3, FRAME_ROWS = 1;
	Animation<TextureRegion> walkAnimation;
	Texture walkSheet;
	SpriteBatch spriteBatch;
	TextureRegion[] regions = new TextureRegion[3];

	// A variable for tracking elapsed time for the animation
	float stateTime;
	private TextureRegion[] walkFrames;
	private TextureRegion attackRegion;
	private TextureRegion NormRegion;



	public Achille(Sprite s, TiledMapTileLayer collisionLayer) {
		super(s, collisionLayer);
		// TODO Auto-generated constructor stub

	}


	public Sprite ownLogic() {

		//texture = new Texture(Gdx.files.internal("Achilles.png"));
		//sprite = new Sprite(texture,0,128,64,64);
		texture = new Texture(Gdx.files.internal("achilles5.png"));
		sprite = new Sprite(texture,64,0,64,64);
		sprite.flip(false, true);

		this.setPosition(256, 128);

		return sprite;

	}


	public Animation<TextureRegion> takeAction() {
		//texture = new Texture(Gdx.files.internal("achilles11.png"));
		// Load the sprite sheet as a Texture
		walkSheet = new Texture(Gdx.files.internal("achilles5.png"));

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

	public TextureRegion[] getAchilleFrames() {
		takeAction();
		return walkFrames;
	}

	public Sprite NormalLook() {
		texture = getAchilleFrames()[1].getTexture();
		Sprite send = new Sprite(texture, 128, 192 , 64, 64);
		send.flip(false, true);
		TextureRegion txr = new TextureRegion(texture,128, 192 , 64, 64);
		txr.flip(false, true);
		setNormRegion(txr);

		return send;
	}

	public Sprite combLookOne() {
		texture = getAchilleFrames()[1].getTexture();
		Sprite send = new Sprite(texture, 80, 0 , 64, 64);
		send.flip(false, true);

		return send;
	}

	public Sprite combLookTwo() {
		texture = getAchilleFrames()[1].getTexture();
		Sprite send = new Sprite(texture, 128, 0 , 64, 64);
		send.flip(false, true);

		return  send;
	}

	public Sprite attacknow() {

		texture = new Texture(Gdx.files.internal("achillesanim.png"));
		sprite = new Sprite(texture,0,0,64,64);
		sprite.flip(false, true);

		TextureRegion txr = new TextureRegion(texture, 0,0,64,64);
		txr.flip(false, true);
		setAttackRegion(txr);

		this.setPosition(256, 128);

		return sprite;
	}

	//////////////////////dynamic animation starts here///////////////   
	private void setAttackRegion(TextureRegion txr) {
		attackRegion = txr;

	}

	private void setNormRegion(TextureRegion txr) {
		NormRegion = txr;

	}




	public Animation<TextureRegion> animatehyplyta() {
		keyframe[1] = getNormRegion();
		keyframe[0] = getAttackRegion();

		return new Animation<TextureRegion>(0.7f, keyframe);
	}


	private TextureRegion getAttackRegion() {
		attacknow();
		return attackRegion;
	}

	private TextureRegion getNormRegion() {
		NormalLook();
		return NormRegion;
	}





}

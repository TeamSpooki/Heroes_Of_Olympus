/**
 * 
 */
package com.heroesofolympus.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector3;


public abstract class GameUnit extends Sprite{

	int health, numMoves, damage, action;
	float velocityY;
	Sprite sprite;
	Location location;
	Vector3 touch;
	boolean isDrawn=false;
	TextureRegion[] keyframe = new TextureRegion[4];

	// Created a collisionLayer to prevent heroes from moving onto the tile
	TiledMapTileLayer collisionLayer;
	private Texture texture;
	private TextureRegion fullheartRegion;
	private TextureRegion halfheartRegion;
	private TextureRegion emptyheartRegion;
	private TextureRegion blunkheartRegion;

	public GameUnit(Sprite s, TiledMapTileLayer collisionLayer) {
		this.sprite=s;
		setPosition(0, 0);
		this.collisionLayer = collisionLayer;

	}

	abstract void attack();

	public void setPosition(float x, float y) {
		sprite.setPosition(x, y);
		//location.setLocation(x, y);
	}

	public float getX(){
		return sprite.getX();
	}

	public float getY(){
		return sprite.getY();
	}

	void move(float x,float y) {
		sprite.setPosition(x + getX(), y + getY());
	}

	void draw(SpriteBatch batch) {
		sprite.draw(batch);
		isDrawn=true;
	}

	boolean getIsDrawn() {
		return isDrawn;
	}

	public Sprite fullHeart() {

		texture = new Texture(Gdx.files.internal("hearts.png"));
		sprite = new Sprite(texture,0,0,40,64);
		sprite.flip(false, true);

		TextureRegion txr = new TextureRegion(texture, 0,0,40,64);
		txr.flip(false, true);
		setfullHeart(txr);

		this.setPosition(256, 128);

		return sprite;
	}

	private void setfullHeart(TextureRegion txr) {
		fullheartRegion = txr;

	}

	public Sprite halfHeart() {

		texture = new Texture(Gdx.files.internal("hearts.png"));
		sprite = new Sprite(texture,40,0,40,64);
		sprite.flip(false, true);

		TextureRegion txr = new TextureRegion(texture, 40,0,40,64);
		txr.flip(false, true);
		sethalfHeart(txr);

		this.setPosition(256, 128);

		return sprite;
	}

	private void sethalfHeart(TextureRegion txr) {
		halfheartRegion = txr;

	}

	public Sprite emptyHeart() {

		texture = new Texture(Gdx.files.internal("hearts.png"));
		sprite = new Sprite(texture,80,0,40,64);
		sprite.flip(false, true);

		TextureRegion txr = new TextureRegion(texture, 80,0,40,64);
		txr.flip(false, true);
		setemptyHeart(txr);

		this.setPosition(256, 128);

		return sprite;
	}

	private void setemptyHeart(TextureRegion txr) {
		emptyheartRegion = txr;

	}

	public Sprite blunkHeart() {

		texture = new Texture(Gdx.files.internal("hearts.png"));
		sprite = new Sprite(texture,120,0,40,64);
		sprite.flip(false, true);

		TextureRegion txr = new TextureRegion(texture, 120,0,40,64);
		txr.flip(false, true);
		setblblunkHeart(txr);

		this.setPosition(256, 128);

		return sprite;
	}

	private void setblblunkHeart(TextureRegion txr) {
		blunkheartRegion = txr;

	}

	public Sprite enamyfall() {

		texture = new Texture(Gdx.files.internal("SkeletonSpearfall.png"));
		sprite = new Sprite(texture,0,0,40,64);
		sprite.flip(false, true);

		this.setPosition(256, 128);

		return sprite;
	}



	public Animation<TextureRegion> animateFulltoHalf() {

		TextureRegion[] keyframefth = new TextureRegion[3];
		keyframefth [0] = getfullHeaartRegion();
		keyframefth[1] = gethalfHeartRegion();
		keyframefth[2] = getblunkHeartRegion();

		return new Animation<TextureRegion>(0.7f, keyframefth);
	}

	public Animation<TextureRegion> animateHalftoEmpty() {

		TextureRegion[] keyframehte = new TextureRegion[3];
		keyframehte [0] = gethalfHeartRegion();
		keyframehte[1] = getemptyHeartRegion();
		keyframehte[2] = getblunkHeartRegion();

		return new Animation<TextureRegion>(0.7f, keyframehte);
	}

	public Animation<TextureRegion> animateHalftoFull() {
		TextureRegion[] keyframehtf = new TextureRegion[3];
		keyframehtf [0] =gethalfHeartRegion();
		keyframehtf[1] = getfullHeaartRegion();
		keyframehtf[2] = getblunkHeartRegion();

		return new Animation<TextureRegion>(0.7f, keyframehtf);
	}

	TextureRegion getblunkHeartRegion() {
		// TODO Auto-generated method stub
		blunkHeart();
		return blunkheartRegion;
	}

	private TextureRegion getemptyHeartRegion() {
		// TODO Auto-generated method stub
		emptyHeart();
		return emptyheartRegion;
	}

	private TextureRegion gethalfHeartRegion() {
		// TODO Auto-generated method stub
		halfHeart();
		return halfheartRegion;
	}

	private TextureRegion getfullHeaartRegion() {
		// TODO Auto-generated method stub
		fullHeart();
		return fullheartRegion;
	}



	///////////loading the titan//////////////////
	public TextureRegion titan() {

		texture = new Texture(Gdx.files.internal("titan.png"));
		sprite = new Sprite(texture,0,0,64,64);
		TextureRegion textureReg = new TextureRegion(texture, 0,0,64,64);
		textureReg.flip(false, true);	
		

		this.setPosition(256, 128);

		return textureReg;
	}
	
	public Sprite titanSprite() {

		texture = new Texture(Gdx.files.internal("titan.png"));
		sprite = new Sprite(texture,0,0,64,64);
		//TextureRegion textureReg = new TextureRegion(texture, 0,0,64,64);
		//textureReg.flip(false, true);	
		sprite.flip(true, true);

		this.setPosition(500,400);

		return sprite;
	}


	/***********************************COLLISION DETECTION*****************************************/
	/*
	 * public void update(float delta) {
	 * 
	 * float oldX = getX(), oldY = getY(), tileWidth =
	 * collisionLayer.getTileWidth(), tileHeight = collisionLayer.getTileHeight();
	 * boolean collisionX = false, collisionY = false;
	 * 
	 * // move on x setX(getX() + touch.x * delta);
	 * 
	 * if(touch.x < 0) { //top left collisionX = collisionLayer.getCell((int)
	 * (getX() / tileWidth), (int) ((getY() + getHeight()) /
	 * tileHeight)).getTile().getProperties().containsKey("blocked");
	 * 
	 * //middle left if(!collisionX) collisionX = collisionLayer.getCell((int)
	 * (getX() / tileWidth), (int) ((getY() + getHeight() / 2) /
	 * tileHeight)).getTile().getProperties().containsKey("blocked");
	 * 
	 * //bottom left if(!collisionX) collisionX = collisionLayer.getCell((int)
	 * (getX() / tileWidth), (int) (getY() /
	 * tileHeight)).getTile().getProperties().containsKey("blocked"); } else
	 * if(touch.x > 0) { // top right collisionX = collisionLayer.getCell((int)
	 * (getX() / tileWidth), (int) ((getY() + getHeight()) /
	 * tileHeight)).getTile().getProperties().containsKey("blocked");
	 * 
	 * // middle right if(!collisionX) collisionX = collisionLayer.getCell((int)
	 * (getX() / tileWidth), (int) ((getY() + getHeight() / 2) /
	 * tileHeight)).getTile().getProperties().containsKey("blocked");
	 * 
	 * // bottom right if(!collisionX) collisionX = collisionLayer.getCell((int)
	 * (getX() / tileWidth), (int) (getY() /
	 * tileHeight)).getTile().getProperties().containsKey("blocked"); }
	 * 
	 * 
	 * //react to x collision if(collisionX) { setX(oldX); touch.x = 0; }
	 * 
	 * // move on Y setY(getY() + touch.y * delta);
	 * 
	 * if(touch.y < 0) {
	 * 
	 * //bottom left collisionY = collisionLayer.getCell((int) (getX() / tileWidth),
	 * (int) (getY() /
	 * tileHeight)).getTile().getProperties().containsKey("blocked");
	 * 
	 * //bottom middle if(!collisionY) collisionY = collisionLayer.getCell((int)
	 * ((getX() + getWidth() / 2) / tileWidth), (int) (getY() /
	 * tileHeight)).getTile().getProperties().containsKey("blocked");
	 * 
	 * //bottom right if(!collisionY) collisionY = collisionLayer.getCell((int)
	 * ((getX() + getWidth()) / tileWidth), (int) (getY() /
	 * tileHeight)).getTile().getProperties().containsKey("blocked");
	 * 
	 * } else if(touch.y > 0) { //top left collisionY = collisionLayer.getCell((int)
	 * (getX() / tileWidth), (int) ((getY() + getHeight()) /
	 * tileHeight)).getTile().getProperties().containsKey("blocked");
	 * 
	 * //top middle if(!collisionY) collisionY = collisionLayer.getCell((int)
	 * ((getX() + getWidth() / 2) / tileWidth), (int) (getY() /
	 * tileHeight)).getTile().getProperties().containsKey("blocked");
	 * 
	 * //top right if(!collisionY) collisionY = collisionLayer.getCell((int)
	 * ((getX() + getWidth()) / tileWidth), (int) (getY() /
	 * tileHeight)).getTile().getProperties().containsKey("blocked"); }
	 * 
	 * //react to y collision if(collisionY) { setY(oldY); touch.y=0; }
	 * 
	 * 
	 * }
	 */

}

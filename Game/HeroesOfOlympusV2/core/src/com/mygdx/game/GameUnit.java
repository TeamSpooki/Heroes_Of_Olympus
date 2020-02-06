package com.mygdx.game;

import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public abstract class GameUnit {
	int health=0;
	int damage=0;
	Sprite sprite;
	Location location;
	String name;
	boolean isDrawn;
	boolean moved;
	TextureRegion[] keyframe = new TextureRegion[4];
	List<TextureRegion[]> animations;
	
	private Texture texture;
	private TextureRegion fullheartRegion;
	private TextureRegion halfheartRegion;
	private TextureRegion emptyheartRegion;
	private TextureRegion blunkheartRegion;
	
	Animation<TextureRegion> animation;
	public GameUnit(Sprite s,String name, List<TextureRegion[]> animations) {
		this.animations = animations;
		this.sprite=s;
		this.name=name;
		location = new Location(0,0);
		setPosition(0, 0);
		isDrawn=false;
		moved=false;
	}
	abstract void attack();
	void setPosition(float x, float y) {
		sprite.setPosition(x, y);
		location.setLocation(x, y);
		
	}
	boolean isMoved() {
		return moved;
	}
	void setMoved(boolean move) {
		moved=move;
	}
	Location getLocation() {
		return location;
	}
	float getX(){
		return location.getX();
	}
	String getName(){
		return name;
	}
	float getY(){
		return location.getY();
	}
	void draw(SpriteBatch batch) {
		sprite.draw(batch);
		isDrawn=true;
	}
	void deleteSprite() {
		this.sprite=null;
	}
	boolean getIsDrawn() {
		return isDrawn;
	}
	void setDamage(int damage) {
		this.damage=damage;
	}
	void setHealth(int health) {
		this.health=health;
	}
	int getDamage() {
		return damage;
	}
	int getHealth() {
		return health;
	}
	void moveLeft() {
		this.setPosition(sprite.getX()-64, sprite.getY()+0);
		
	}
	void moveRight() {
		this.setPosition(sprite.getX()+64, sprite.getY()+0);
	}
	void moveUp() {
		this.setPosition(sprite.getX()+0, sprite.getY()-64);
		//animation = new Animation<TextureRegion>(1f/30f,animations.get(1));
	}
	void moveDown() {
		this.setPosition(sprite.getX()+0, sprite.getY()+64);
	}
	
	public Sprite fullHeart() {

		texture = new Texture(Gdx.files.internal("hearts.png"));
		sprite = new Sprite(texture,0,0,40,64);
		sprite.flip(false, true);

		TextureRegion txr = new TextureRegion(texture, 0,0,40,64);
		txr.flip(false, true);
		setFullHeart(txr);

		return sprite;
	}
	private void setFullHeart(TextureRegion txr) {
		fullheartRegion = txr;
	}
	public Sprite halfHeart() {

		texture = new Texture(Gdx.files.internal("hearts.png"));
		sprite = new Sprite(texture,40,0,40,64);
		sprite.flip(false, true);

		TextureRegion txr = new TextureRegion(texture, 40,0,40,64);
		txr.flip(false, true);
		setHalfHeart(txr);

		return sprite;
	}
	private void setHalfHeart(TextureRegion txr) {
		halfheartRegion = txr;
	}
	public Sprite emptyHeart() {

		texture = new Texture(Gdx.files.internal("hearts.png"));
		sprite = new Sprite(texture,80,0,40,64);
		sprite.flip(false, true);

		TextureRegion txr = new TextureRegion(texture, 80,0,40,64);
		txr.flip(false, true);
		setemptyHeart(txr);

		return sprite;
	}

	private void setemptyHeart(TextureRegion txr) {
		emptyheartRegion = txr;

	}
	public Sprite blankHeart() {

		texture = new Texture(Gdx.files.internal("hearts.png"));
		sprite = new Sprite(texture,120,0,40,64);
		sprite.flip(false, true);

		TextureRegion txr = new TextureRegion(texture, 120,0,40,64);
		txr.flip(false, true);
		setblankHeart(txr);

		return sprite;
	}

	private void setblankHeart(TextureRegion txr) {
		blunkheartRegion = txr;

	}
	public Sprite enamyfall() {

		texture = new Texture(Gdx.files.internal("SkeletonSpearfall.png"));
		sprite = new Sprite(texture,0,0,64,64);
		sprite.flip(false, true);

		return sprite;
	}

	public Animation<TextureRegion> animateFulltoHalf() {

		TextureRegion[] keyframefth = new TextureRegion[3];
		keyframefth [0] = getFullHeartRegion();
		keyframefth[1] = getHalfHeartRegion();
		keyframefth[2] = getBlankHeartRegion();

		return new Animation<TextureRegion>(0.7f, keyframefth);
	}

	public Animation<TextureRegion> animateHalftoEmpty() {

		TextureRegion[] keyframehte = new TextureRegion[3];
		keyframehte [0] = getHalfHeartRegion();
		keyframehte[1] = getEmptyHeartRegion();
		keyframehte[2] = getBlankHeartRegion();

		return new Animation<TextureRegion>(0.7f, keyframehte);
	}

	public Animation<TextureRegion> animateHalftoFull() {
		TextureRegion[] keyframehtf = new TextureRegion[3];
		keyframehtf [0] =getHalfHeartRegion();
		keyframehtf[1] = getFullHeartRegion();
		keyframehtf[2] = getBlankHeartRegion();

		return new Animation<TextureRegion>(0.7f, keyframehtf);
	}
	TextureRegion getBlankHeartRegion() {
		// TODO Auto-generated method stub
		blankHeart();
		return blunkheartRegion;
	}

	private TextureRegion getEmptyHeartRegion() {
		// TODO Auto-generated method stub
		emptyHeart();
		return emptyheartRegion;
	}

	private TextureRegion getHalfHeartRegion() {
		// TODO Auto-generated method stub
		halfHeart();
		return halfheartRegion;
	}

	private TextureRegion getFullHeartRegion() {
		// TODO Auto-generated method stub
		fullHeart();
		return fullheartRegion;
	}
}

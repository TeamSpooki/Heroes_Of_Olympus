package com.mygdx.game;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public abstract class GameUnit {
	protected int health=100;
	protected int damage=0;
	protected int movementRange=0;
	protected int attackRange=0;
	boolean dead=false;
	Sprite sprite;
	Location location;
	String name;
	boolean isDrawn;
	boolean moved;
	TextureRegion[] stayAnimation,walkAnimation,attackAnimation,dieAnimation,healthAnimation;
	Animation<TextureRegion> animation;
	protected Animate animate;
	Map<Integer,TextureRegion> healthSprites;
	public GameUnit(TextureRegion[][] t,String name,int movementRange, int attackRange, int damage) {
		animate = Animate.STAY;
		this.movementRange=movementRange;
		this.attackRange=attackRange;
		this.damage= damage;
		setAnimation(t);
		healthSprites = new HashMap<Integer,TextureRegion>();
		setHealthBar();
		sprite = new Sprite();
		this.name=name;
		location = new Location(0,0);
		setPosition(0, 0);
		isDrawn=false;
	}
	private void setHealthBar(){
		healthSprites.put(100, healthAnimation[0]);
		healthSprites.put(80, healthAnimation[1]);
		healthSprites.put(60, healthAnimation[2]);
		healthSprites.put(40, healthAnimation[3]);
		healthSprites.put(20, healthAnimation[4]);
		healthSprites.put(0, healthAnimation[5]);
	}
	private void setAnimation(TextureRegion[][] t){
		stayAnimation = t[0];
		walkAnimation = t[1];
		attackAnimation = t[2];
		dieAnimation = t[3];
		healthAnimation=t[4];
		for(int i = 0; i < 6; i++) {
			stayAnimation[i].flip(false, true);
			walkAnimation[i].flip(false, true);
			attackAnimation[i].flip(false, true);
			dieAnimation[i].flip(false, true);
			healthAnimation[i].flip(false, true);
		}
	}
	protected void setAnimation(Animate animation){
		animate = animation;
	}
	public void setPosition(float x, float y) {
		sprite.setPosition(x, y);
		location.setLocation(x, y);
	}
	protected void setHealth(int health){
		this.health= health;
	}
	protected boolean isMoved() {
		return moved;
	}
	protected void setMoved(boolean move) {
		moved=move;
	}
	Location getLocation() {
		return location;
	}
	protected float getX(){
		return sprite.getX();
	}
	protected String getName(){
		return name;
	}
	protected float getY(){
		return sprite.getY();
	}
	protected void draw(SpriteBatch batch) {
		if(animate.equals(animate.STAY)) {
			TextureRegion currentFrame;
			if(health==100) {
				currentFrame=healthSprites.get(100);
			}else if(health<=100&&health>80) {
				currentFrame=healthSprites.get(80);
			}else if(health<=80&&health>60) {
				currentFrame=healthSprites.get(60);
			}else if(health<=60&&health>40) {
				currentFrame=healthSprites.get(40);
			}else if(health<=40&&health>20) {
				currentFrame=healthSprites.get(20);
			}else {
				currentFrame=healthSprites.get(0);
				setAnimation(animate.DIE);
				setMoved(false);
				dead=true;
			}
			batch.draw(currentFrame, getX(), getY());
		}else if(animate.equals(animate.WALK)) {
			animation = new Animation<>(0.0007f, walkAnimation);
			TextureRegion currentFrame = animation.getKeyFrame(MainGameScreen.elapsedTime,true);
			batch.draw(currentFrame, getX(), getY());
		}else if(animate.equals(animate.ATTACK)) {
			animation = new Animation<>(0.0007f, attackAnimation);
			TextureRegion currentFrame = animation.getKeyFrame(MainGameScreen.elapsedTime,true);
			batch.draw(currentFrame, getX(), getY());
		}else {
			animation = new Animation<>(0.0007f, dieAnimation);
			TextureRegion currentFrame = animation.getKeyFrame(MainGameScreen.elapsedTime,false);
			batch.draw(currentFrame, getX(), getY());
		}
	}
	protected int getDamage() {
		return damage;
	}
	protected int getHealth() {
		return health;
	}
	protected int getMovementRange() {
		return movementRange;
	}
	protected boolean isInBounds(float x, float y, int range){
		float leftBound = this.getX()-range*64;
		float rightBound= this.getX()+range*64;
		float upBound=this.getY()-range*64;
		float downBound=this.getY()+range*64;
		if(x>=leftBound&&x<=rightBound&&y>=upBound&&y<= downBound) {
			return true;
		}
		return false;
	}
	void moveLeft() {
		setPosition(sprite.getX()-64, sprite.getY()+0);
	}
	void moveRight() {
		setPosition(sprite.getX(), sprite.getY());
	}
	void moveUp() {
		setPosition(sprite.getX()+0, sprite.getY()-64);
	}
	void moveDown() {
		setPosition(sprite.getX()+0, sprite.getY()+64 );
	}
}
enum Animate{
	STAY,WALK,ATTACK,DIE;
}


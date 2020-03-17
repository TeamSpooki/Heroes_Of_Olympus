package com.mygdx.game;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.screen.MainGameScreen;

public abstract class GameUnit {
	protected int health;
	protected int damage;
	protected int movementRange;
	protected int attackRange;
	protected boolean dead;
	protected Sprite sprite;
	protected Location location;
	protected String name;
	protected boolean isDrawn;
	protected boolean moved;
	protected TextureRegion[] stayAnimation,walkAnimation,attackAnimation,dieAnimation,healthAnimation;
	protected Animation<TextureRegion> animation;
	protected Animate animate;
	protected Map<Integer,TextureRegion> healthSprites;
	public GameUnit(TextureRegion[][] t,String name,int movementRange, int attackRange, int damage) {
		this.animate = Animate.STAY;
		this.health=100;
		this.dead=false;
		this.movementRange=movementRange;
		this.attackRange=attackRange;
		this.damage= damage;
		setAnimation(t);
		healthSprites = new HashMap<>();
		setHealthBar();
		this.sprite = new Sprite();
		this.name=name;
		this.location = new Location(0,0);
		setPosition(0, 0);
		this.isDrawn=false;
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
	public void setAnimation(Animate animation){
		animate = animation;
	}
	public void setPosition(float x, float y) {
		sprite.setPosition(x, y);
		location.setLocation(x, y);
	}
	public void setHealth(int health){
		this.health= health;
	}
	public boolean isMoved() {
		return moved;
	}
	public void setMoved(boolean move) {
		moved=move;
	}
	public Location getLocation() {
		return location;
	}
	public float getX(){
		return sprite.getX();
	}
	public float getY(){
		return sprite.getY();
	}
	public void draw(SpriteBatch batch) {
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
	public int getDamage() {
		return damage;
	}
	public int getHealth() {
		return health;
	}
	public int getMovementRange() {
		return movementRange;
	}
	public int getAttackRange() {
		return attackRange;
	}
	public boolean isDead() {
		return dead;
	}
	public boolean isInBounds(float x, float y, int range){
		float leftBound = this.getX()-range*64;
		float rightBound= this.getX()+range*64;
		float upBound=this.getY()-range*64;
		float downBound=this.getY()+range*64;
		if(x>=leftBound&&x<=rightBound&&y>=upBound&&y<= downBound) {
			return true;
		}
		return false;
	}
}


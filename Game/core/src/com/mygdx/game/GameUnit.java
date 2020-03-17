package com.mygdx.game;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public abstract class GameUnit {
	int healthBar=100;
	int damage=0;
	int movementRange=0;
	int attackRange=0;
	boolean dead=false;
	Sprite sprite;
	Location location;
	String name;
	boolean isDrawn;
	boolean moved;
	TextureRegion[] stay,walk,attack,die,health;
	Animation<TextureRegion> animation;
	Animate a ;
	Map<Integer,TextureRegion> healthSprites;
	
	public GameUnit(TextureRegion[][] t,String name) {
		a = Animate.STAY;
		stay = t[0];
		walk = t[1];
		attack = t[2];
		die = t[3];
		health=t[4];
		for(int i = 0; i < 6; i++) {
			stay[i].flip(false, true);
			walk[i].flip(false, true);
			attack[i].flip(false, true);
			die[i].flip(false, true);
			health[i].flip(false, true);
		}
		healthSprites = new HashMap<Integer,TextureRegion>();
		healthSprites.put(100, health[0]);
		healthSprites.put(80, health[1]);
		healthSprites.put(60, health[2]);
		healthSprites.put(40, health[3]);
		healthSprites.put(20, health[4]);
		healthSprites.put(0, health[5]);
		sprite = new Sprite();
		this.name=name;
		location = new Location(0,0);
		setPosition(0, 0);
		isDrawn=false;
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
		return sprite.getX();
	}
	
	String getName(){
		return name;
	}
	
	float getY(){
		return sprite.getY();
	}
	
	void draw(SpriteBatch batch) {
		if(a.equals(a.STAY)) {
			//animation = new Animation<TextureRegion>(0.0007f,stay);
			//TextureRegion currentFrame = animation.getKeyFrame(MainGameScreen.elapsedTime,true);
			//batch.draw(currentFrame, getX(), getY());
			TextureRegion currentFrame;
			if(healthBar==100) {
				currentFrame=healthSprites.get(100);
			}else if(healthBar<=100&&healthBar>80) {
				currentFrame=healthSprites.get(80);
			}else if(healthBar<=80&&healthBar>60) {
				currentFrame=healthSprites.get(60);
			}else if(healthBar<=60&&healthBar>40) {
				currentFrame=healthSprites.get(40);
			}else if(healthBar<=40&&healthBar>20) {
				currentFrame=healthSprites.get(20);
			}else {
				currentFrame=healthSprites.get(0);
				a=a.DIE;
				setMoved(false);
				dead=true;
			}
			
			batch.draw(currentFrame, getX(), getY());
		}else if(a.equals(a.WALK)) {
			animation = new Animation<TextureRegion>(0.0007f,walk);
			TextureRegion currentFrame = animation.getKeyFrame(MainGameScreen.elapsedTime,true);
			batch.draw(currentFrame, getX(), getY());
		}else if(a.equals(a.ATTACK)) {
			animation = new Animation<TextureRegion>(0.0007f,attack);
			TextureRegion currentFrame = animation.getKeyFrame(MainGameScreen.elapsedTime,true);
			batch.draw(currentFrame, getX(), getY());
		}else {
			animation = new Animation<TextureRegion>(0.0007f,die);
			TextureRegion currentFrame = animation.getKeyFrame(MainGameScreen.elapsedTime,false);
			batch.draw(currentFrame, getX(), getY());
		}
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
		this.healthBar=health;
	}
	
	int getDamage() {
		return damage;
	}
	
	int getHealth() {
		return healthBar;
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


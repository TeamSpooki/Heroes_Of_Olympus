package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public abstract class GameUnit {
	int health=0;
	int damage=0;
	int movementRange=0;
	int attackRange=0;
	Sprite sprite;
	Location location;
	String name;
	boolean isDrawn;
	boolean moved;
	TextureRegion[] stay,walk,attack,die;
	Animation<TextureRegion> animation;
	Animate a ;
	public GameUnit(TextureRegion[][] t,String name) {
		a = Animate.STAY;
		stay = t[0];
		walk = t[1];
		attack = t[2];
		die = t[3];
		for(int i = 0; i < 6; i++) {
			stay[i].flip(false, true);
			walk[i].flip(false, true);
			attack[i].flip(false, true);
			die[i].flip(false, true);
		}
		sprite = new Sprite();
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
			animation = new Animation<TextureRegion>(0.0007f,stay);
			TextureRegion currentFrame = animation.getKeyFrame(MainGameScreen.elapsedTime,true);
			batch.draw(currentFrame, getX(), getY());
			
			//sprite.draw(batch);
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
			TextureRegion currentFrame = animation.getKeyFrame(MainGameScreen.elapsedTime,true);
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
		this.health=health;
	}
	int getDamage() {
		return damage;
	}
	int getHealth() {
		return health;
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

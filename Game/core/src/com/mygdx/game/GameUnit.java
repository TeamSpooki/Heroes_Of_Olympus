package com.mygdx.game;

import java.util.LinkedList;
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
	int range=0;
	Sprite sprite;
	Location location;
	String name;
	boolean isDrawn;
	boolean moved;
	TextureRegion[] keyframe = new TextureRegion[4];
	List<Sprite[]> animations;
	
	Animation<TextureRegion> animation;
	public GameUnit(TextureRegion[][] t,String name) {
		Sprite[] sprites= new Sprite[6];
		animations= new LinkedList<Sprite[]>();
		//TextureRegion[] walkFrames = new TextureRegion[6];
		for(int i = 0;i<t.length;i++) {
			for(int j = 0;j<6;j++) {
				//walkFrames[j]=t[i][j];
				Sprite s = new Sprite(t[i][j]);
				s.flip(false, true);
				sprites[j]=s;
			}
			animations.add(sprites);
		}

		this.sprite=animations.get(3)[0];
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
		for(int i=0;i<=64;i+=12) {
			this.setPosition(sprite.getX()+i, sprite.getY()+0);
		}
		
	}
	void moveUp() {
		this.setPosition(sprite.getX()+0, sprite.getY()-64);
		animation = new Animation<TextureRegion>(1f/30f,animations.get(0));
	}
	void moveDown() {
		this.setPosition(sprite.getX()+0, sprite.getY()+64);
	}
	//public Animation<TextureRegion> animateToLeft() {
	//	return new Animation<TextureRegion>(0.7f, animations.get(1).toArray());
	//}

}

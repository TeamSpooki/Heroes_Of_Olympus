package com.mygdx.game;

import java.util.LinkedList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Timer;

public abstract class GameUnit {
	int health=0;
	int damage=0;
	int range=0;
	Sprite sprite;
	//TextureRegion[][] animations;
	Location location;
	String name;
	boolean isDrawn;
	boolean moved;
	TextureRegion[] keyframe = new TextureRegion[4];
	List<TextureRegion[]> animations;
	
	Animation<TextureRegion> animation;
	public GameUnit(TextureRegion[][] t,String name) {
		
		//this.animations = t;
		animations= new LinkedList<TextureRegion[]>();
		TextureRegion[] walkFrames = new TextureRegion[6];
		for(int i = 0;i<t.length;i++) {
			for(int j = 0;j<6;j++) {
				walkFrames[j]=t[i][j];
				walkFrames[j].flip(false, true);
			}
			animations.add(walkFrames);
		}
		//animation = new Animation<TextureRegion>(1f/30f,animations.get(2));
		sprite = new Sprite(animations.get(2)[0]);
		//sprite.flip(false, true);
		this.name=name;
		location = new Location(0,0);
		setPosition(0, 0);
		isDrawn=false;
		moved=false;
	}
	abstract void attack();
	void setPosition(float x, float y) {
		/*if(moved) {
			if(y==0) {
				float i = x+getX();
				while(getX()!=i) {
					if(getX()<i) {
						sprite.translate(getX()+1, y);
					}else {
						sprite.translate(getX()-1, y);
					}
				}
			}
			if(x==0) {
				float i = y+getY();
				while(getY()!=i) {
					if(getY()<i) {
						sprite.translate(x, getY()+1);
					}else {
						sprite.translate(x, getY()-1);
					}
				}
			}
		}*/
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
		sprite.draw(batch);
		/*if(moved) {
			//animation.setPlayMode(PlayMode.LOOP);
			
			TextureRegion currentFrame = animation.getKeyFrame(MainGameScreen.elapsedTime,true);
			//currentFrame.flip(false, true);
			sprite.setRegion(currentFrame);
			sprite.draw(batch);
			//batch.draw(currentFrame, getX(), getY());
			
			moved=false;
		}
		else {
			sprite.draw(batch);
		}
		*/
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
		setPosition(sprite.getX()-64, sprite.getY()+0);
		//sprite.translate(-64, 0);
		animation = new Animation<TextureRegion>(0.7f, animations.get(1));
		//setPosition(-64,0);
	}
	void moveRight() {
		//sprite.translate(64, 0);
		//setPosition(64,0);
		setPosition(sprite.getX(), sprite.getY());
	}
	void moveUp() {
		
		setPosition(sprite.getX()+0, sprite.getY()-64);
		//sprite.translate(0,-64);
		
		//setPosition(0,-64);
		
		
	}
	void moveDown() {
		setPosition(sprite.getX()+0, sprite.getY()+64 );
		//sprite.translate(0, 64);
		//setPosition(0,64);
	}
	void animateUp() {
		animation = new Animation<TextureRegion>(1f/30f,animations.get(0));
		//draw(HeroesOfOlympus.batch);
	}
}

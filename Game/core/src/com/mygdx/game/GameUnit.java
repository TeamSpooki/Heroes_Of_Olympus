package com.mygdx.game;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.screen.MainGameScreen;

/**
 * GameUnit class representing a unit of the game
 */
public abstract class GameUnit {

	/**
	 * Health of a GameUnit
	 */
	protected int health;
	/**
	 * Damage of GameUnit
	 */
	protected int damage;
	/**
	 * Range of movement of GameUnit
	 */
	protected int movementRange;
	/**
	 * Range of attack of GameUnit
	 */
	protected int attackRange;
	/**
	 * Check if GameUnit is dead
	 */
	protected boolean dead;
	/**
	 * Sprite variable
	 */
	protected Sprite sprite;
	/**
	 * Location variable
	 */
	protected Location location;
	/**
	 * Name of GameUnit
	 */
	protected String name;
	/**
	 * Check if GameUnit has moved
	 */
	protected boolean moved;
	/**
	 * Collection of animations textures
	 */
	protected TextureRegion[] stayAnimation,walkAnimation,attackAnimation,dieAnimation,healthAnimation;
	/**
	 * Animation variable for execution of animation
	 */
	protected Animation<TextureRegion> animation;
	/**
	 * Position of animation
	 */
	protected Animate animate;
	/**
	 * Sprite collection of GameUnits with healthbar
	 */
	protected Map<Integer,TextureRegion> healthSprites;
	/**
	 * Sounds for attack,death,moving
	 */
	private Sound attack,death,moving;
	
	public GameUnit(TextureRegion[][] t,String name,int movementRange, int attackRange, int damage) {
		this.animate = Animate.STAY;
		this.health=100;
		this.dead=false;
		this.name=name;
		this.location = new Location(0,0);
		this.sprite = new Sprite();
		this.death= Gdx.audio.newSound(Gdx.files.internal("Sounds/Death.mp3"));
		this.moving=Gdx.audio.newSound(Gdx.files.internal("Sounds/Move.wav"));
		this.attack= Gdx.audio.newSound(Gdx.files.internal("Sounds/Enemy.wav"));
		if(movementRange<5&&movementRange>0){
			this.movementRange=movementRange;
		}else{
			System.out.println("Error: Wrong movement range value for " + name);
		}
		if(attackRange<20&&attackRange>0){
			this.attackRange=attackRange;
		}else{
			System.out.println("Error: Wrong attack range value for "+ name);
		}
		if(damage<=100&&damage>=0){
			this.damage=damage;
		}else{
			System.out.println("Error: Wrong damage value for "+ name);
		}
		setAnimation(t);
		setHealthBar();
		setPosition(0, 0);
	}

	/**
	 * Creates the healthbars
	 */
	private void setHealthBar(){
		healthSprites = new HashMap<>();
		healthSprites.put(100, healthAnimation[0]);
		healthSprites.put(80, healthAnimation[1]);
		healthSprites.put(60, healthAnimation[2]);
		healthSprites.put(40, healthAnimation[3]);
		healthSprites.put(20, healthAnimation[4]);
		healthSprites.put(0, healthAnimation[5]);
	}

	/**
	 * Creates animations
	 * @param t
	 */
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

	/**
	 * Set new animation
	 * @param animation
	 */
	public void setAnimation(Animate animation){
		animate = animation;
	}

	/**
	 * Set GameUnit position
	 * @param x
	 * @param y
	 */
	public void setPosition(float x, float y) {
		if(x<=0&&x>=MainGameScreen.WIDTH&&y<=0&&y>=MainGameScreen.HEIGHT){
			System.out.println(name + " is out of bounds");
		}else{
			sprite.setPosition(x, y);
			location.setLocation(x, y);
		}
	}

	/**
	 * Set health
	 * @param health
	 */
	public void setHealth(int health){
		if(health<=100){
			this.health= health;
		}else{
			System.out.println(name+ " health is too high");
		}
	}
	/**
	 * Set movement value
	 * @param move
	 */
	public void setMoved(boolean move) {
		this.moved=move;
	}

	/**
	 * Set attack sound
	 * @param sound
	 */
	public void setSound(Sound sound) {
		this.attack=sound;
	}

	/**
	 * Set damage
	 * @param damage
	 */
	public void setDamage(int damage){
		if(damage<=100&&damage>=0){
			this.damage=damage;
		}else{
			System.out.println("Error: Wrong damage value for "+ name);
		}
	}

	/**
	 * Set ne attack range
	 * @param attack
	 */
	public void setAttackRange(int attack){
		if(attack<20&&attack>0){
			this.attackRange=attack;
		}else{
			System.out.println("Error: Wrong attack range value for "+ name);
		}
	}
	/**
	 * True if unit has moved false otherwise
	 * @return moved
	 */
	public boolean isMoved() {
		return moved;
	}

	/**
	 * Play the attack sound
	 */
	public void playAttack() {
		this.attack.play(1.0f);
	}
	/**
	 * Play the mvoe sound
	 */
	public void playMove() {
		this.moving.play(1.0f);
	}

	/**
	 * Get GameUnit location
	 * @return location
	 */
	public Location getLocation() {
		return this.location;
	}

	/**
	 * Get GameUnit x coordinate
	 *
	 * @return x
	 */
	public float getX(){
		return sprite.getX();
	}

	/**
	 * Get GameUnit y coordinate
	 * @return y
	 */
	public float getY(){
		return sprite.getY();
	}

	/**
	 * Draw method to draw all sprites used in game render method
	 * @param batch
	 */
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
				death.play(1.0f);
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

	/**
	 * Return GameUnit damage
	 * @return damage
	 */
	public int getDamage() {
		return damage;
	}

	/**
	 * Return GameUnit health
	 * @return health
	 */
	public int getHealth() {
		return health;
	}

	/**
	 * Return GameUnit movement range
	 * @return movementRange
	 */
	public int getMovementRange() {
		return movementRange;
	}

	/**
	 * Return GameUnit attack range
	 * @return attackRange
	 */
	public int getAttackRange() {
		return attackRange;
	}

	/**
	 * Return GameUnit name
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * Check if GameUnit is dead
	 * @return dead
	 */
	public boolean isDead() {
		return dead;
	}

	/**
	 * Check if GameUnit is dead or not
	 * @return dead
	 */
	public boolean toggleDead() {
		return dead = !dead;
	}

	/**
	 * Check if a given location is in bounds of a given range
	 * @param x
	 * @param y
	 * @param range
	 * @return
	 */
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

	/**
	 * toString method for different Gameunit values
	 * @return
	 */
	public String toString(){
		return name+"\n"+" Movement Range: "+movementRange+
				"\t"+ " Damage: "+damage+
				"\t"+ " Attack Range: "+attackRange+
				"\t"+" Health: "+health;
	}
}


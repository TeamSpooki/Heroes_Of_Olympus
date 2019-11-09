/**
 * 
 */
package com.heroesofolympus.game;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector3;


public abstract class GameUnit extends Sprite{
	
	int health, numMoves, damage, action;
	float velocityY;
	Sprite sprite;
	Location location;
	Vector3 touch;
	boolean isDrawn=false;
	
	// Created a collisionLayer to prevent heroes from moving onto the tile
	TiledMapTileLayer collisionLayer;
	
	
	
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
		sprite.setPosition(x, y);
	}
	
	void draw(SpriteBatch batch) {
		sprite.draw(batch);
		isDrawn=true;
	}
	
	boolean getIsDrawn() {
		return isDrawn;
	}
	
	//Turn Based
	public void playerTurn() {
		
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

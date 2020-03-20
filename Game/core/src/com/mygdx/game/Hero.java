package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Hero class representing a GameUnit
 */
public class Hero extends GameUnit{
	
	public Hero(TextureRegion[][] t,String name,int movementRange, int attackRange, int damage) {
		super(t, name,movementRange,attackRange,damage);
		this.moved=false;
	}

}

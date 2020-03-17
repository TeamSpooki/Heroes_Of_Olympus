package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Enemy extends GameUnit{

	public Enemy(TextureRegion[][] t,String name,int movementRange, int attackRange, int damage) {
		super(t, name,movementRange,attackRange,damage);
		this.moved=true;
	}
	
}

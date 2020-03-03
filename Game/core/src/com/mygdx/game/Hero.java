package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Hero extends GameUnit{
	int numMoves=0;
	
	public Hero(TextureRegion[][] t,String name,int movementRange, int attackRange, int damage) {
		super(t, name);
		this.movementRange=movementRange;
		this.attackRange=attackRange;
		this.damage= damage;
	}

	void attack() {}
	void setMoves(int moves) {
		this.numMoves=moves;
	}
}

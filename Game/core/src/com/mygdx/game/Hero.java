package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Hero extends GameUnit{
	int numMoves=0;
	
	public Hero(TextureRegion[][] t,String name,int movementRange, int attackRange) {
		super(t, name);
		this.movementRange=movementRange;
		this.attackRange=attackRange;
	}

	
	void setMoves(int moves) {
		this.numMoves=moves;
	}
}

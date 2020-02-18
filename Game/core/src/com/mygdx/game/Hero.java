package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Hero extends GameUnit{
	int numMoves=0;
	
	public Hero(TextureRegion[][] t,String name) {
		super(t, name);
	}

	void attack() {}
	void setMoves(int moves) {
		this.numMoves=moves;
	}
}

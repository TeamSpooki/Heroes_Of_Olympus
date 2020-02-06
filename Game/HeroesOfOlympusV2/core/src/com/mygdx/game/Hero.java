package com.mygdx.game;

import java.util.List;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Hero extends GameUnit{
	int numMoves=0;
	
	public Hero(Sprite s, String name, List<TextureRegion[]> animations) {
		super(s,name, animations);
	}

	void attack() {}
	void setMoves(int moves) {
		this.numMoves=moves;
	}
}

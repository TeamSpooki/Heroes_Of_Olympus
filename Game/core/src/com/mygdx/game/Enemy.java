package com.mygdx.game;

import java.util.List;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Enemy extends GameUnit{

	public Enemy(TextureRegion[][] t,String name) {
		super(t, name);
		
	}

	void attack() {}

}

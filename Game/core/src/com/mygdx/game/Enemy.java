package com.mygdx.game;

import java.util.List;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Enemy extends GameUnit{

	public Enemy(Sprite s, String name, List<TextureRegion[]> animations) {
		super(s, name, animations);
		
	}

	void attack() {}

}

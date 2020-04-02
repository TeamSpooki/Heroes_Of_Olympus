package com.mygdx.level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.Enemy;
import com.mygdx.game.Location;
import com.mygdx.screen.MainGameScreen;

public class Level1 extends AbstractLevel{

	private Enemy titan,enemy1,enemy2,enemy3;
	
	public Level1()
	{
		//64,128,192,256,320,384,448,512,576,640,704,768,832,896,960,1024,1088,1152,1216,1280
		achille.setPosition(320, 256);
		mapCollisions.add(new Location(320, 256));
		heroes.add(achille);

		helen.setPosition(64, 192);
		mapCollisions.add(new Location(64, 192));
		heroes.add(helen);
		
		hercules.setPosition(192, 384);
		mapCollisions.add(new Location(192, 384));
		heroes.add(hercules);
		
		hypolyta.setPosition(384,640);
		mapCollisions.add(new Location(384, 640));
		heroes.add(hypolyta);
		
		thesius.setPosition(64, 512);
		mapCollisions.add(new Location(64, 512));
		heroes.add(thesius);

		titan = new Enemy(TextureRegion.split(new Texture(Gdx.files.internal("Level1/CyclopsHealthBar.png")), 64, 64),"CYCLOPS",1,3,25);
		titan.setPosition(MainGameScreen.WIDTH-64, MainGameScreen.HEIGHT/2);
		mapCollisions.add(new Location(MainGameScreen.WIDTH-64, MainGameScreen.HEIGHT/2));
		enemies.add(titan);	
		
		enemy1 = new Enemy(TextureRegion.split(new Texture(Gdx.files.internal("Level1/SkeletonBowHealthBar.png")), 64, 64),"SKELETON BOW",1,3,5);
		enemy1.setPosition(MainGameScreen.WIDTH-192, MainGameScreen.HEIGHT/2-128);
		mapCollisions.add(new Location(MainGameScreen.WIDTH-192, MainGameScreen.HEIGHT/2-128));
		enemies.add(enemy1);

		enemy2 = new Enemy(TextureRegion.split(new Texture(Gdx.files.internal("Level1/SkeletonBowHealthBar.png")), 64, 64),"SKELETON BOW",1,3,5);
		enemy2.setPosition(MainGameScreen.WIDTH-192, MainGameScreen.HEIGHT/2+128);
		mapCollisions.add(new Location(MainGameScreen.WIDTH-192, MainGameScreen.HEIGHT/2+128));
		enemies.add(enemy2);

		enemy3 = new Enemy(TextureRegion.split(new Texture(Gdx.files.internal("Level1/SkeletonSpearHealthBar.png")), 64, 64),"SKELETON SPEAR",1,1,10);
		enemy3.setPosition(MainGameScreen.WIDTH-320, MainGameScreen.HEIGHT/2-64);
		mapCollisions.add(new Location(MainGameScreen.WIDTH-320, MainGameScreen.HEIGHT/2-64));
		enemies.add(enemy3);

	}
}

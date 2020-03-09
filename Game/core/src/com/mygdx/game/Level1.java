package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Level1 extends AbstractLevel{

	Hero achille,helen,hercules,hypolyta,thesius;
	Texture achilleTexture,helenTexture,herculesTexture,hypolytaTexture,thesiusTexture;
	Enemy titan,enemy1,enemy2,enemy3,enemy4,enemy5,enemy6;
	
	public Level1()
	{
		for(int i=0;i<MainGameScreen.WIDTH;i+=64) {
			for(int j=0;j<MainGameScreen.HEIGHT;j+=64) {
				Location loc = new Location(i,j);
				board.put(loc, null);
			}
		}
		
		achille = new Hero(TextureRegion.split(new Texture(Gdx.files.internal("Heroes/AchillesHealthBar.png")),64,64),"Achille",1,1,15);
		achille.setPosition(320, 256);
		board.put(new Location(achille.getX(),achille.getY()), achille);
		heroes.add(achille);
		
		helen = new Hero(TextureRegion.split(new Texture(Gdx.files.internal("Heroes/HelenHealthBar.png")),64,64),"Helen",3,5,10);
		helen.setPosition(64, 192);
		board.put(new Location(helen.getX(),helen.getY()), helen);
		heroes.add(helen);
		
		hercules = new Hero(TextureRegion.split(new Texture(Gdx.files.internal("Heroes/HerculesHealthBar.png")),64,64),"Hercules",2,3,20);
		hercules.setPosition(192, 384);
		board.put(new Location(hercules.getX(),hercules.getY()), hercules);
		heroes.add(hercules);
		
		hypolyta = new Hero(TextureRegion.split(new Texture(Gdx.files.internal("Heroes/HippolytaHealthBar.png")),64,64),"Hypolyta",3,2,10);
		hypolyta.setPosition(384,640);
		board.put(new Location(hypolyta.getX(),hypolyta.getY()), hypolyta);
		heroes.add(hypolyta);
		
		thesius = new Hero(TextureRegion.split( new Texture(Gdx.files.internal("Heroes/ThesiusHealthBar.png")),64,64),"Thesius",1,15,100);
		thesius.setPosition(64, 512);
		board.put(new Location(thesius.getX(),thesius.getY()), thesius);
		heroes.add(thesius);
		
		titan = new Enemy(TextureRegion.split(new Texture(Gdx.files.internal("Level1/CyclopsHealthBar.png")), 64, 64),"Titan",1,15,100);
		titan.setPosition(MainGameScreen.WIDTH-64, MainGameScreen.HEIGHT/2);
		board.put(new Location(MainGameScreen.WIDTH-64,MainGameScreen.HEIGHT/2), titan);
		enemies.add(titan);	
		
		enemy1 = new Enemy(TextureRegion.split(new Texture(Gdx.files.internal("Level1/SkeletonBowHealthBar.png")), 64, 64),"Enemy1",1,15,100);
		enemy1.setPosition(MainGameScreen.WIDTH-192, MainGameScreen.HEIGHT/2-128);
		board.put(new Location(MainGameScreen.WIDTH-192,MainGameScreen.HEIGHT/2-128), enemy1);
		enemies.add(enemy1);
		
		enemy2 = new Enemy(TextureRegion.split(new Texture(Gdx.files.internal("Level1/SkeletonBowHealthBar.png")), 64, 64),"Enemy2",1,15,100);
		enemy2.setPosition(MainGameScreen.WIDTH-192, MainGameScreen.HEIGHT/2);
		board.put(new Location(MainGameScreen.WIDTH-192,MainGameScreen.HEIGHT/2), enemy2);
		enemies.add(enemy2);
		
		enemy3 = new Enemy(TextureRegion.split(new Texture(Gdx.files.internal("Level1/SkeletonBowHealthBar.png")), 64, 64),"Enemy3",1,15,100);
		enemy3.setPosition(MainGameScreen.WIDTH-192, MainGameScreen.HEIGHT/2+128);
		board.put(new Location(MainGameScreen.WIDTH-192,MainGameScreen.HEIGHT/2+128), enemy3);
		enemies.add(enemy3);
		
		enemy4 = new Enemy(TextureRegion.split(new Texture(Gdx.files.internal("Level1/SkeletonSpearHealthBar.png")), 64, 64),"Enemy4",1,15,100);
		enemy4.setPosition(MainGameScreen.WIDTH-320, MainGameScreen.HEIGHT/2-192);
		board.put(new Location(MainGameScreen.WIDTH-320,MainGameScreen.HEIGHT/2-192), enemy4);
		enemies.add(enemy4);
		
		enemy5 = new Enemy(TextureRegion.split(new Texture(Gdx.files.internal("Level1/SkeletonSpearHealthBar.png")), 64, 64),"Enemy5",1,15,100);
		enemy5.setPosition(MainGameScreen.WIDTH-320, MainGameScreen.HEIGHT/2-64);
		board.put(new Location(MainGameScreen.WIDTH-320,MainGameScreen.HEIGHT/2-64), enemy5);
		enemies.add(enemy5);
		
		enemy6 = new Enemy(TextureRegion.split(new Texture(Gdx.files.internal("Level1/SkeletonSpearHealthBar.png")), 64, 64),"Enemy6",1,15,100);
		enemy6.setPosition(MainGameScreen.WIDTH-320, MainGameScreen.HEIGHT/2+64);
		board.put(new Location(MainGameScreen.WIDTH-320,MainGameScreen.HEIGHT/2+64), enemy6);
		enemies.add(enemy6);
		
	}
}

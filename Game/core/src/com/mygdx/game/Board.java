package com.mygdx.game;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Board {
	List<GameUnit> heroes;
	List<GameUnit> enemies;
	LinkedList<Location> validMoves;
	LinkedList<Location> validAttacks;
	LinkedList<Location> possibleAttacks; 
	
	Map<Location,GameUnit> board;
	
	Hero achille,helen,hercules,hypolyta,thesius;
	Texture achilleTexture,helenTexture,herculesTexture,hypolytaTexture,thesiusTexture,position,attack;
	Enemy titan,enemy1,enemy2,enemy3,enemy4,enemy5,enemy6;
	
	public Board()
	{	
		board = new HashMap<Location, GameUnit>();
		for(int i=0;i<MainGameScreen.WIDTH;i+=64) {
			for(int j=0;j<MainGameScreen.HEIGHT;j+=64) {
				Location loc = new Location(i,j);
				board.put(loc, null);
			}
		}
		enemies = new LinkedList<GameUnit>();
		heroes = new LinkedList<GameUnit>();
		validMoves = new LinkedList<Location>();
		validAttacks = new LinkedList<Location>();
		possibleAttacks = new LinkedList<Location>();
		
		position = new Texture(Gdx.files.internal("yellowSelect.png"));
		attack = new Texture(Gdx.files.internal("redSelect.png"));
		
		achille = new Hero(TextureRegion.split(new Texture(Gdx.files.internal("Achilles46.png")),64,64),"Achille",1,1);
		achille.setPosition(64, 128);
		board.put(new Location(64,128), achille);
		heroes.add(achille);
		
		helen = new Hero(TextureRegion.split(new Texture(Gdx.files.internal("Helen46.png")),64,64),"Helen",3,5);
		helen.setPosition(128, 256);
		board.put(new Location(128,256), helen);
		heroes.add(helen);
		
		hercules = new Hero(TextureRegion.split(new Texture(Gdx.files.internal("Hercules46.png")),64,64),"Hercules",2,3);
		hercules.setPosition(0, 320);
		board.put(new Location(0,320), hercules);
		heroes.add(hercules);
		
		hypolyta = new Hero(TextureRegion.split(new Texture(Gdx.files.internal("Hippolyta46.png")),64,64),"Hypolyta",3,2);
		hypolyta.setPosition(128, 448);
		board.put(new Location(128,448), hypolyta);
		heroes.add(hypolyta);
		
		thesius = new Hero(TextureRegion.split( new Texture(Gdx.files.internal("Thesius46.png")),64,64),"Thesius",1,15);
		thesius.setPosition(192,576);
		board.put(new Location(192,576), thesius);
		heroes.add(thesius);
		
		titan = new Enemy(TextureRegion.split(new Texture(Gdx.files.internal("Minotaur46.png")), 64, 64),"Titan");
		titan.setPosition(MainGameScreen.WIDTH-64, MainGameScreen.HEIGHT/2);
		board.put(new Location(MainGameScreen.WIDTH-64,MainGameScreen.HEIGHT/2), titan);
		enemies.add(titan);	
		
		enemy1 = new Enemy(TextureRegion.split(new Texture(Gdx.files.internal("SkeletonBow46.png")), 64, 64),"Enemy1");
		enemy1.setPosition(MainGameScreen.WIDTH-192, MainGameScreen.HEIGHT/2-128);
		board.put(new Location(MainGameScreen.WIDTH-192,MainGameScreen.HEIGHT/2-128), enemy1);
		enemies.add(enemy1);
		
		enemy2 = new Enemy(TextureRegion.split(new Texture(Gdx.files.internal("SkeletonBow46.png")), 64, 64),"Enemy2");
		enemy2.setPosition(MainGameScreen.WIDTH-192, MainGameScreen.HEIGHT/2);
		board.put(new Location(MainGameScreen.WIDTH-192,MainGameScreen.HEIGHT/2), enemy2);
		enemies.add(enemy2);
		
		enemy3 = new Enemy(TextureRegion.split(new Texture(Gdx.files.internal("SkeletonBow46.png")), 64, 64),"Enemy3");
		enemy3.setPosition(MainGameScreen.WIDTH-192, MainGameScreen.HEIGHT/2+128);
		board.put(new Location(MainGameScreen.WIDTH-192,MainGameScreen.HEIGHT/2+128), enemy3);
		enemies.add(enemy3);
		
		enemy4 = new Enemy(TextureRegion.split(new Texture(Gdx.files.internal("SkeletonSpear46.png")), 64, 64),"Enemy4");
		enemy4.setPosition(MainGameScreen.WIDTH-320, MainGameScreen.HEIGHT/2-192);
		board.put(new Location(MainGameScreen.WIDTH-320,MainGameScreen.HEIGHT/2-192), enemy4);
		enemies.add(enemy4);
		
		enemy5 = new Enemy(TextureRegion.split(new Texture(Gdx.files.internal("SkeletonSpear46.png")), 64, 64),"Enemy5");
		enemy5.setPosition(MainGameScreen.WIDTH-320, MainGameScreen.HEIGHT/2-64);
		board.put(new Location(MainGameScreen.WIDTH-320,MainGameScreen.HEIGHT/2-64), enemy5);
		enemies.add(enemy5);
		
		
		enemy6 = new Enemy(TextureRegion.split(new Texture(Gdx.files.internal("SkeletonSpear46.png")), 64, 64),"Enemy6");
		enemy6.setPosition(MainGameScreen.WIDTH-320, MainGameScreen.HEIGHT/2+64);
		board.put(new Location(MainGameScreen.WIDTH-320,MainGameScreen.HEIGHT/2+64), enemy6);
		enemies.add(enemy6);
		
		
	}
	
	
	public void Draw( SpriteBatch batch)
	{	
		for(GameUnit u:heroes)
		{
				u.draw(batch);
		}
		for(GameUnit e:enemies)
		{
				e.draw(batch);
		}
		if(!validMoves.isEmpty()) {
			for(Location l:validMoves)
			{
				batch.draw(position, l.getX(), l.getY());
			}
			
		}
		if(!validAttacks.isEmpty()) {
			for(Location l:validAttacks)
			{
				batch.draw(attack, l.getX(), l.getY());
			}
			
		}
	}
	public GameUnit GetPieceHero(Location location)
	{
		for(GameUnit u:heroes)
		{
			if(u.location.equals(location)) {
				return u;
			}
		}
		return null;
	}
	public GameUnit GetPieceEnemy(Location location)
	{
		for(GameUnit e:enemies)
		{
			if(e.location.equals(location)) {
				return e;
			}
		}
		return null;
	}
	public GameUnit findNearestHero(float x, float y)
	{
		for(GameUnit u:heroes)
		{
			if(x>=u.getX()&&x<=u.getX()+64&&y>=u.getY()&&y<=u.getY()+64) {
				return u;
			}
		}
		return null;
	}
	public GameUnit findNearestEnemy(float x, float y)
	{
		for(GameUnit e:enemies)
		{
			if(x>=e.getX()&&x<=e.getX()+64&&y>=e.getY()&&y<=e.getY()+64) {
				return e;
			}
		}
		return null;
	}
	public Location findNearestLocation(float x, float y)
	{
		for(Location l:validMoves)
		{
			if(x>=l.getX()&&x<=l.getX()+64&&y>=l.getY()&&y<=l.getY()+64) {
				return l;
			}
		}
		return null;
	}
	public void MovePiece(Location from,Location to)
	{
		
		for(GameUnit u:heroes)
		{
			if(u.location.equals(from)) {
				u.setPosition(to.getX(), to.getY());
			}
		}
	}
	public void resetMovement() {
		for(GameUnit u:heroes)
		{
			u.setMoved(false);
		}
	}
	public void moveEnemies() {
		Random rand = new Random();
		int n=0;
		n= rand.nextInt(5);
		for(GameUnit e:enemies)
		{ 
			if(!e.name.equals("Titan")) {
				n = rand.nextInt(5)+1;
				switch(n) {
				case 1:
					e.moveUp();
					break;
				case 2:
					e.moveDown();
					break;
				case 3:
					e.moveRight();
					break;
				case 4:
					e.moveLeft();
					break;
				default:
					break;
				}
			}
			
		}
	}
}

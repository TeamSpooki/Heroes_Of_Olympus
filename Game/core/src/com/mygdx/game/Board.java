package com.mygdx.game;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Board {
	List<GameUnit> heroes;
	List<GameUnit> enemies;
	List<Location> validMoves;
	
	
	Hero achille,helen,hercules,hypolyta,thesius;
	Texture achilleTexture,helenTexture,herculesTexture,hypolytaTexture,thesiusTexture,position;
	Enemy titan,enemy1,enemy2,enemy3,enemy4,enemy5,enemy6;

	
	public Board()
	{	
		enemies = new LinkedList<GameUnit>();
		heroes = new LinkedList<GameUnit>();
		validMoves = new LinkedList<Location>();
		
		position = new Texture(Gdx.files.internal("yellowSelect.png"));
		
		achilleTexture = new Texture(Gdx.files.internal("Achilles.png"));
		achille = new Hero(TextureRegion.split(achilleTexture,achilleTexture.getWidth()/6 ,achilleTexture.getHeight()/ 9),"Achille");
		achille.setPosition(64, 128);
		heroes.add(achille);
		
		helenTexture = new Texture(Gdx.files.internal("Helen.png"));
		helen = new Hero(TextureRegion.split(helenTexture,helenTexture.getWidth()/6,helenTexture.getHeight()/9),"Helen");
		helen.setPosition(128, 256);
		heroes.add(helen);
		
		herculesTexture = new Texture(Gdx.files.internal("Hercules.png"));
		hercules = new Hero(TextureRegion.split(herculesTexture,herculesTexture.getWidth()/6,herculesTexture.getHeight()/9),"Hercules");
		hercules.setPosition(0, 320);
		heroes.add(hercules);
		
		hypolytaTexture= new Texture(Gdx.files.internal("Hypolyta.png"));
		hypolyta = new Hero(TextureRegion.split(hypolytaTexture,hypolytaTexture.getWidth()/6,hypolytaTexture.getHeight()/9),"Hypolyta");
		hypolyta.setPosition(128, 448);
		heroes.add(hypolyta);
		
		thesiusTexture = new Texture(Gdx.files.internal("Thesius.png"));
		thesius = new Hero(TextureRegion.split(thesiusTexture,thesiusTexture.getWidth()/6,thesiusTexture.getHeight()/9),"Thesius");
		thesius.setPosition(192,576);
		heroes.add(thesius);
		
		
		titan = new Enemy(TextureRegion.split(new Texture(Gdx.files.internal("Minotaur.png")), 64, 64),"Titan");
		titan.setPosition(MainGameScreen.WIDTH-64, MainGameScreen.HEIGHT/2);
		enemies.add(titan);	
		
		enemy1 = new Enemy(TextureRegion.split(new Texture(Gdx.files.internal("SkeletonBow.png")), 64, 64),"Enemy1");
		enemy1.setPosition(MainGameScreen.WIDTH-192, MainGameScreen.HEIGHT/2-128);
		enemies.add(enemy1);
		
		enemy2 = new Enemy(TextureRegion.split(new Texture(Gdx.files.internal("SkeletonBow.png")), 64, 64),"Enemy2");
		enemy2.setPosition(MainGameScreen.WIDTH-192, MainGameScreen.HEIGHT/2);
		enemies.add(enemy2);
		
		enemy3 = new Enemy(TextureRegion.split(new Texture(Gdx.files.internal("SkeletonBow.png")), 64, 64),"Enemy3");
		enemy3.setPosition(MainGameScreen.WIDTH-192, MainGameScreen.HEIGHT/2+128);
		enemies.add(enemy3);
		
		enemy4 = new Enemy(TextureRegion.split(new Texture(Gdx.files.internal("SkeletonSpear.png")), 64, 64),"Enemy4");
		enemy4.setPosition(MainGameScreen.WIDTH-320, MainGameScreen.HEIGHT/2-192);
		enemies.add(enemy4);
		
		enemy5 = new Enemy(TextureRegion.split(new Texture(Gdx.files.internal("SkeletonSpear.png")), 64, 64),"Enemy5");
		enemy5.setPosition(MainGameScreen.WIDTH-320, MainGameScreen.HEIGHT/2-64);
		enemies.add(enemy5);
		
		
		enemy6 = new Enemy(TextureRegion.split(new Texture(Gdx.files.internal("SkeletonSpear.png")), 64, 64),"Enemy6");
		enemy6.setPosition(MainGameScreen.WIDTH-320, MainGameScreen.HEIGHT/2+64);
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
	public Location GetPoint(int x,int y)
	{
		return new Location(x/64,7-y/64);
	}
	
	public boolean IsInBounds(Location location)
	{
		return location.getX()<8&&location.getX()>=0&&location.getY()<8&&location.getY()>=0;
	}
	
}

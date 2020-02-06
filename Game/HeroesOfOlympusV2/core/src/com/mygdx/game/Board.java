package com.mygdx.game;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Board {
	List<GameUnit> heroes;
	List<GameUnit> enemies;
	
	Hero achille,helen,hercules,hypolyta,thesius;
	Enemy titan,enemy1,enemy2,enemy3,enemy4,enemy5,enemy6;
	Location lastFrom;
	Location lastTo;

	public Board()
	{	
		enemies = new LinkedList<GameUnit>();
		heroes = new LinkedList<GameUnit>();
		
		List<Sprite> sprites= new LinkedList<Sprite>();
		List<TextureRegion[]> animations= new LinkedList<TextureRegion[]>();
		TextureRegion[] walkFrames = new TextureRegion[6];
		TextureRegion[][] t = TextureRegion.split(new Texture(Gdx.files.internal("Achilles.png")), 64, 64);
		for(int i = 0;i<t.length;i++) {
			for(int j = 0;j<6;j++) {
				walkFrames[j]=t[i][j];
				Sprite s = new Sprite(t[i][j]);
				s.flip(false, true);
				sprites.add(s);
				animations.add(walkFrames);
			}
		}
		achille = new Hero(sprites.get(12),"Achille",animations);
		achille.setPosition(64, 128);
		heroes.add(achille);
		sprites.clear();
		animations.clear();
		
		t = TextureRegion.split(new Texture(Gdx.files.internal("Helen.png")), 64, 64);
		for(int i = 0;i<t.length;i++) {
			for(int j = 0;j<6;j++) {
				walkFrames[j]=t[i][j];
				Sprite s = new Sprite(t[i][j]);
				s.flip(false, true);
				sprites.add(s);
				animations.add(walkFrames);
			}
		}
		helen = new Hero(sprites.get(12),"Helen",animations);
		helen.setPosition(128, 256);
		heroes.add(helen);
		sprites.clear();
		animations.clear();
		
		t = TextureRegion.split(new Texture(Gdx.files.internal("Hercules.png")), 64, 64);
		for(int i = 0;i<t.length;i++) {
			for(int j = 0;j<6;j++) {
				walkFrames[j]=t[i][j];
				Sprite s = new Sprite(t[i][j]);
				s.flip(false, true);
				sprites.add(s);
				animations.add(walkFrames);
			}
		}
		hercules = new Hero(sprites.get(12),"Hercules",animations);
		hercules.setPosition(0, 320);
		heroes.add(hercules);
		sprites.clear();
		animations.clear();
		
		t = TextureRegion.split(new Texture(Gdx.files.internal("Hypolyta.png")), 64, 64);
		for(int i = 0;i<t.length;i++) {
			for(int j = 0;j<6;j++) {
				walkFrames[j]=t[i][j];
				Sprite s = new Sprite(t[i][j]);
				s.flip(false, true);
				sprites.add(s);
				animations.add(walkFrames);
			}
		}
		hypolyta = new Hero(sprites.get(12),"Hypolyta",animations);
		hypolyta.setPosition(128, 448);
		heroes.add(hypolyta);
		sprites.clear();
		animations.clear();
		
		t = TextureRegion.split(new Texture(Gdx.files.internal("Thesius.png")), 64, 64);
		for(int i = 0;i<t.length;i++) {
			for(int j = 0;j<6;j++) {
				walkFrames[j]=t[i][j];
				Sprite s = new Sprite(t[i][j]);
				s.flip(false, true);
				sprites.add(s);
				animations.add(walkFrames);
			}
		}
		thesius = new Hero(sprites.get(12),"Thesius",animations);
		thesius.setPosition(192,576);
		heroes.add(thesius);
		sprites.clear();
		animations.clear();
		
		t = TextureRegion.split(new Texture(Gdx.files.internal("Minotaur.png")), 64, 64);
		for(int i = 0;i<t.length;i++) {
			for(int j = 0;j<6;j++) {
				walkFrames[j]=t[i][j];
				Sprite s = new Sprite(t[i][j]);
				s.flip(false, true);
				sprites.add(s);
				animations.add(walkFrames);
			}
		}
		titan = new Enemy(sprites.get(26),"Titan",animations);
		titan.setPosition(MainGameScreen.WIDTH-64, MainGameScreen.HEIGHT/2);
		enemies.add(titan);	
		sprites.clear();
		animations.clear();
		
		t = TextureRegion.split(new Texture(Gdx.files.internal("SkeletonBow.png")), 64, 64);
		for(int i = 0;i<t.length;i++) {
			for(int j = 0;j<6;j++) {
				walkFrames[j]=t[i][j];
				Sprite s = new Sprite(t[i][j]);
				s.flip(false, true);
				sprites.add(s);
				animations.add(walkFrames);
			}
		}
		enemy1 = new Enemy(sprites.get(12),"Enemy1",animations);
		enemy1.setPosition(MainGameScreen.WIDTH-192, MainGameScreen.HEIGHT/2-128);
		enemies.add(enemy1);
		sprites.clear();
		animations.clear();
		
		
		t = TextureRegion.split(new Texture(Gdx.files.internal("SkeletonBow.png")), 64, 64);
		for(int i = 0;i<t.length;i++) {
			for(int j = 0;j<6;j++) {
				walkFrames[j]=t[i][j];
				Sprite s = new Sprite(t[i][j]);
				s.flip(false, true);
				sprites.add(s);
				animations.add(walkFrames);
			}
		}
		enemy2 = new Enemy(sprites.get(12),"Enemy2",animations);
		enemy2.setPosition(MainGameScreen.WIDTH-192, MainGameScreen.HEIGHT/2);
		enemies.add(enemy2);
		sprites.clear();
		animations.clear();
		
		t = TextureRegion.split(new Texture(Gdx.files.internal("SkeletonBow.png")), 64, 64);
		for(int i = 0;i<t.length;i++) {
			for(int j = 0;j<6;j++) {
				walkFrames[j]=t[i][j];
				Sprite s = new Sprite(t[i][j]);
				s.flip(false, true);
				sprites.add(s);
				animations.add(walkFrames);
			}
		}
		enemy3 = new Enemy(sprites.get(12),"Enemy3",animations);
		enemy3.setPosition(MainGameScreen.WIDTH-192, MainGameScreen.HEIGHT/2+128);
		enemies.add(enemy3);
		sprites.clear();
		animations.clear();
		
		t = TextureRegion.split(new Texture(Gdx.files.internal("SkeletonSpear.png")), 64, 64);
		for(int i = 0;i<t.length;i++) {
			for(int j = 0;j<6;j++) {
				walkFrames[j]=t[i][j];
				Sprite s = new Sprite(t[i][j]);
				s.flip(false, true);
				sprites.add(s);
				animations.add(walkFrames);
			}
		}
		enemy4 = new Enemy(sprites.get(12),"Enemy4",animations);
		enemy4.setPosition(MainGameScreen.WIDTH-320, MainGameScreen.HEIGHT/2-192);
		enemies.add(enemy4);
		sprites.clear();
		animations.clear();
		
		t = TextureRegion.split(new Texture(Gdx.files.internal("SkeletonSpear.png")), 64, 64);
		for(int i = 0;i<t.length;i++) {
			for(int j = 0;j<6;j++) {
				walkFrames[j]=t[i][j];
				Sprite s = new Sprite(t[i][j]);
				s.flip(false, true);
				sprites.add(s);
				animations.add(walkFrames);
			}
		}
		enemy5 = new Enemy(sprites.get(12),"Enemy5",animations);
		enemy5.setPosition(MainGameScreen.WIDTH-320, MainGameScreen.HEIGHT/2-64);
		enemies.add(enemy5);
		sprites.clear();
		animations.clear();
		
		t = TextureRegion.split(new Texture(Gdx.files.internal("SkeletonSpear.png")), 64, 64);
		for(int i = 0;i<t.length;i++) {
			for(int j = 0;j<6;j++) {
				walkFrames[j]=t[i][j];
				Sprite s = new Sprite(t[i][j]);
				s.flip(false, true);
				sprites.add(s);
				animations.add(walkFrames);
			}
		}
		enemy6 = new Enemy(sprites.get(12),"Enemy6",animations);
		enemy6.setPosition(MainGameScreen.WIDTH-320, MainGameScreen.HEIGHT/2+64);
		enemies.add(enemy6);
		sprites.clear();
		animations.clear();
		
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
	public void MovePiece(Location from,Location to)
	{
		lastFrom=from;
		lastTo=to;
		for(GameUnit u:heroes)
		{
			if(u.location.equals(from)) {
				u.sprite.setX(to.getX());
				u.sprite.setY(to.getY());
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
	/*public IntRect GetRectangle(Location point)
	{	
		return new IntRect(point.getX()*64,(point.getY())*64,64,64);
	}*/
	public boolean IsInBounds(Location location)
	{
		return location.getX()<8&&location.getX()>=0&&location.getY()<8&&location.getY()>=0;
	}
	
}

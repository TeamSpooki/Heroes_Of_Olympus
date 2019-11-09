package com.heroesofolympus.game;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Board {

	/*
	 * List<GameUnit> heroes;
	 * 
	 * Texture achille_img, helen_img,hercules_img,hypolyta_img,thesius_img;
	 * 
	 * List<Sprite>
	 * achilleSprites,helenSprites,herculesSprites,hypolytaSprites,thesiusSprites;
	 * Hero achille,helen,hercules,hypolyta,thesius; Location lastFrom; Location
	 * lastTo; GameUnit lastRemoved;
	 * 
	 * int size=0;
	 * 
	 * public Board() { heroes = new LinkedList<GameUnit>(); achilleSprites= new
	 * ArrayList<Sprite>(); achille_img= new
	 * Texture(Gdx.files.internal("Achilles.png")); for(int i =
	 * 0;i<achille_img.getHeight();i+=64) { for(int j =
	 * 0;j<achille_img.getWidth();j+=64) { Sprite s = new
	 * Sprite(achille_img,i,j,64,64); s.flip(false, true); achilleSprites.add(s); }
	 * } achille = new Hero(achilleSprites.get(0)); heroes.add(achille);
	 * 
	 * helenSprites= new ArrayList<Sprite>(); helen_img= new
	 * Texture(Gdx.files.internal("Helen.png")); for(int i =
	 * 0;i<helen_img.getHeight();i+=64) { for(int j =
	 * 0;j<helen_img.getWidth();j+=64) { Sprite s = new Sprite(helen_img,i,j,64,64);
	 * s.flip(false, true); helenSprites.add(s); } } helen = new
	 * Hero(helenSprites.get(0));
	 * 
	 * heroes.add(helen);
	 * 
	 * herculesSprites= new ArrayList<Sprite>(); hercules_img= new
	 * Texture(Gdx.files.internal("Hercules.png")); for(int i =
	 * 0;i<hercules_img.getHeight();i+=64) { for(int j =
	 * 0;j<hercules_img.getWidth();j+=64) { Sprite s = new
	 * Sprite(hercules_img,i,j,64,64); s.flip(false, true); herculesSprites.add(s);
	 * } } hercules = new Hero(herculesSprites.get(0));
	 * 
	 * heroes.add(hercules);
	 * 
	 * hypolytaSprites= new ArrayList<Sprite>(); hypolyta_img= new
	 * Texture(Gdx.files.internal("Hypolyta.png")); for(int i =
	 * 0;i<hypolyta_img.getHeight();i+=64) { for(int j =
	 * 0;j<hypolyta_img.getWidth();j+=64) { Sprite s = new
	 * Sprite(hypolyta_img,i,j,64,64); s.flip(false, true); hypolytaSprites.add(s);
	 * } } hypolyta = new Hero(hypolytaSprites.get(0));
	 * 
	 * heroes.add(hypolyta);
	 * 
	 * thesiusSprites= new ArrayList<Sprite>(); thesius_img= new
	 * Texture(Gdx.files.internal("Thesius.png")); for(int i =
	 * 0;i<thesius_img.getHeight();i+=64) { for(int j =
	 * 0;j<thesius_img.getWidth();j+=64) { Sprite s = new
	 * Sprite(thesius_img,i,j,64,64); s.flip(false, true); thesiusSprites.add(s); }
	 * } thesius = new Hero(thesiusSprites.get(0));
	 * 
	 * heroes.add(thesius);
	 * 
	 * }
	 * 
	 * 
	 * public void Draw( SpriteBatch batch) { //while(size!=heroes.size()) {
	 * 
	 * for(GameUnit u:heroes) { //if(!u.getIsDrawn()) { batch.begin(); Random r=new
	 * Random(); u.setPosition(r.nextInt(257), 0); u.draw(batch); batch.end(); //}
	 * size++; } System.out.print(size);
	 * 
	 * //} }
	 * 
	 * public GameUnit GetPiece(Location location) { for(GameUnit u:heroes) {
	 * if(u.location.equals(location)) { return u; } } return null; }
	 * 
	 * public void MovePiece(Location from,Location to) { lastFrom=from; lastTo=to;
	 * for(GameUnit u:heroes) { if(u.location.equals(from)) {
	 * u.sprite.setX(to.getX()); u.sprite.setY(to.getY()); } } }
	 * 
	 * public Location GetPoint(int x,int y) { return new Location(x/64,7-y/64); }
	 * public IntRect GetRectangle(Location point) { return new
	 * IntRect(point.getX()*64,(point.getY())*64,64,64); } public boolean
	 * IsInBounds(Location location) { return
	 * location.getX()<8&&location.getX()>=0&&location.getY()<8&&location.getY()>=0;
	 * }
	 */
}


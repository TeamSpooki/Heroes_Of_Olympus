package com.heroesofolympus.enemy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.heroesofolympus.game.Enemy;
import com.heroesofolympus.game.Hero;

public class Robot extends Enemy {
	States state[] =States.values();
	int[] track = {1,2,3,4,5};
	int picker;
	private Random rand;
	private ArrayList<Hero> target=new ArrayList<Hero>();
	private Enemy hit;
	private ArrayList<Hero> heroes = new ArrayList<Hero>();
	private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	

	public Robot(Sprite s, TiledMapTileLayer collisionLayer) {
		super(s, collisionLayer);
		// TODO Auto-generated constructor stub
		
		
	}

	
public void makeAmove(ArrayList<Enemy> enemy, int val) {
	     rand = new Random();
	     int index = rand.nextInt(5);
	     List cont = randomgenerator();
	     picker  = (int) cont.get(index);
	     
	     
		    
		    	enemy.get(picker).setPosition(enemy.get(picker).getX()- 64, enemy.get(picker).getY());
		    	
		    	System.out.println("enemy number is: " +picker);
		   
					
		
	}


/////////////the enemy attack is based on the moves heros make.
public void autonomousAttack(ArrayList<Hero> hero, ArrayList<Enemy> enemy) {
	
	     for(int i = 0; i < enemy.size(); i++) {
	    	 for(int j = 0; j < hero.size(); j++) {
	    		 if (hero.get(j).getY() ==enemy.get(i).getY()) {
	    			  target.add(hero.get(i));
	    			  
	    		 }
	    	 }
	     }
}


public void passHero(ArrayList<Hero> packHeroes) {
	// TODO Auto-generated method stub
	heroes = packHeroes;
	
	
}


public void passEnemy(ArrayList<Enemy> packEnemies) {
	// TODO Auto-generated method stub
	enemies = packEnemies;
	
}
	
public void strike() {
	autonomousAttack(heroes, enemies);
	System.out.println("Number of heroes in target: "+ target.size());
}


public void passBatch(SpriteBatch batch) {
	// TODO Auto-generated method stub
	
	
}


	 
    public  List randomgenerator() {
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < 5; i++) {
            list.add(new Integer(i));
        }
       
         Collections.shuffle(list);
//        for (int i = 0; i < 5; i++) {
//            System.out.println(list.get(i));
//        }
//        
         return list;
         
    }
	
}	  
	


package com.mygdx.game;

public class Animator {
	float weapnSpeed = 10.0f; // 10 pixels per second.
	float marioX;
	float marioY;
	
	 float[] location = new float[2];
	private float weaponX;
	private float weaponY; 

	public float[] render(float animator) {
			    
	      weaponX += animator * weapnSpeed;
	     		
	      weaponY = animator * weapnSpeed;
	      location[0]= weaponX;
	      location[1]= weaponY;
	      
	      return location;
	} 
}

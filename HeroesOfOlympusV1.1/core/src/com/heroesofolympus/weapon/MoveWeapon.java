package com.heroesofolympus.weapon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.heroesofolympus.game.Hero;

public class MoveWeapon {
	
	private TextureRegion attackRegion;
	final float SPEED = 400;

	public MoveWeapon() {
		
	}
	
	public Sprite attack() {

		 Texture texture = new Texture(Gdx.files.internal("projectile.png"));
		   Sprite sprite = new Sprite(texture,0,0,64,64);
		   sprite.flip(true, true);
		   TextureRegion txr = new TextureRegion(texture, 0,0,64,64);
		   txr.flip(false, true);
		   setAttackRegion(txr);
		   
		   sprite.setPosition(256, 128);
		  
		   
		   return sprite;
	}

	private void setAttackRegion(TextureRegion txr) {
		// TODO Auto-generated method stub
		attackRegion = txr;
		
	}
	
	public TextureRegion getAttachRegion() {
		attack();
		return attackRegion;
	}
	Sprite sprite = attack();
	private float time=0;
	public void drawWeapon(SpriteBatch sb, float delta, Hero hero) {
		  time +=delta;
		 
		 sprite.setOrigin((hero.getX() + 32), (hero.getY() - 32));
		   sprite.setRotation(45);
		
		sb.draw(attack(), (hero.getX() + 32)+(float)Math.sqrt(time)*SPEED, (hero.getY() - 32)+ (float)Math.sqrt(time)*SPEED );
		   
		
		
	}

}

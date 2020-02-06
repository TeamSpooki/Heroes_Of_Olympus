package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class FloatingText extends Actor {
	
	private final String text;
    private final long animationDuration;
    private float deltaX;
    private float deltaY;
    private boolean animated = false;
    private long animationStart;
    Stage stage;
 
    private BitmapFont font = new BitmapFont();
 
    public FloatingText(String text, long animationDuration) {
        this.text = text;
        this.animationDuration = animationDuration;
        font.getData().setScale(1.5f);
    }

    
    public void setDeltaX(float deltaX) {
        this.deltaX = deltaX;
    }
     
    public void setDeltaY(float deltaY) {
        this.deltaY = deltaY;
    }
    
    public void animate() {
        animated = true;
        animationStart = System.currentTimeMillis();
    }
     
    public boolean isAnimated() {
        return animated;
    }
    
    @Override
    public void draw(Batch batch, float parentAlpha) {
       
           float elapsed = System.currentTimeMillis() - animationStart;
     
            // The text will be fading.
            font.setColor(getColor().r, getColor().g, getColor().b, parentAlpha * (1 - elapsed / animationDuration));
     
            font.draw(batch, text, getX() + deltaX * elapsed / 1000f, getY() + deltaY * elapsed / 1000f);
     }
 }

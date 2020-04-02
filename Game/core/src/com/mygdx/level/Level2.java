package com.mygdx.level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.Enemy;
import com.mygdx.game.Location;
import com.mygdx.screen.MainGameScreen;

public class Level2 extends AbstractLevel{

    private Enemy titan,enemy1,enemy2,enemy3;

    public Level2() {
        //64,128,192,256,320,384,448,512,576,640,704,768,832,896,960,1024,1088,1152,1216,1280
        super();
        achille.setPosition(320, 192);
        mapCollisions.add(new Location(320, 192));
        heroes.add(achille);

        helen.setPosition(256, 320);
        mapCollisions.add(new Location(256, 320));
        heroes.add(helen);

        hercules.setPosition(320, 448);
        mapCollisions.add(new Location(320, 448));
        heroes.add(hercules);

        hypolyta.setPosition(128, 576);
        mapCollisions.add(new Location(128, 576));
        heroes.add(hypolyta);

        thesius.setPosition(192,704);
        mapCollisions.add(new Location(192,704));
        heroes.add(thesius);

        titan = new Enemy(TextureRegion.split(new Texture(Gdx.files.internal("Level2/goblinsword.png")), 64, 64),"GOBLIN",1,2,30);
        titan.setPosition(MainGameScreen.WIDTH-320, MainGameScreen.HEIGHT/2);
        mapCollisions.add(new Location(MainGameScreen.WIDTH-320, MainGameScreen.HEIGHT/2));
        enemies.add(titan);

        enemy1 = new Enemy(TextureRegion.split(new Texture(Gdx.files.internal("Level2/OrcMace.png")), 64, 64),"ORC MACE",1,2,10);
        enemy1.setPosition(MainGameScreen.WIDTH-320, MainGameScreen.HEIGHT/2-128);
        mapCollisions.add(new Location(MainGameScreen.WIDTH-320, MainGameScreen.HEIGHT/2-128));
        enemies.add(enemy1);

        enemy2 = new Enemy(TextureRegion.split(new Texture(Gdx.files.internal("Level2/OrcWizard.png")), 64, 64),"ORC WIZARD",1,4,5);
        enemy2.setPosition(MainGameScreen.WIDTH-448, MainGameScreen.HEIGHT/2);
        mapCollisions.add(new Location(MainGameScreen.WIDTH-448, MainGameScreen.HEIGHT/2));
        enemies.add(enemy2);

        enemy3 = new Enemy(TextureRegion.split(new Texture(Gdx.files.internal("Level2/OrcMace.png")), 64, 64),"ORC MACE",1,2,10);
        enemy3.setPosition(MainGameScreen.WIDTH-320, MainGameScreen.HEIGHT/2+128);
        mapCollisions.add(new Location(MainGameScreen.WIDTH-320, MainGameScreen.HEIGHT/2+128));
        enemies.add(enemy3);

    }
}

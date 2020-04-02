package com.mygdx.level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.Enemy;
import com.mygdx.game.Location;
import com.mygdx.screen.MainGameScreen;

public class Level4 extends AbstractLevel {

    private Enemy titan,enemy1,enemy2,enemy3,enemy4,enemy5;

    public Level4() {
        super();
        //64,128,192,256,320,384,448,512,576,640,704,768,832,896,960,1024,1088,1152,1216,1280
        achille.setPosition(64, 704);
        mapCollisions.add(new Location(64, 128));
        heroes.add(achille);

        helen.setPosition(128, 256);
        mapCollisions.add(new Location(128, 256));
        heroes.add(helen);

        hercules.setPosition(64, 320);
        mapCollisions.add(new Location(0, 320));
        heroes.add(hercules);

        hypolyta.setPosition(128, 448);
        mapCollisions.add(new Location(128, 448));
        heroes.add(hypolyta);

        thesius.setPosition(192, 576);
        mapCollisions.add(new Location(192, 576));
        heroes.add(thesius);

        titan = new Enemy(TextureRegion.split(new Texture(Gdx.files.internal("Level4/KnightCaptain.png")), 64, 64), "KNIGHT CAPTAIN", 2, 1, 40);
        titan.setPosition(MainGameScreen.WIDTH - 128, MainGameScreen.HEIGHT / 2);
        mapCollisions.add(new Location(MainGameScreen.WIDTH - 128, MainGameScreen.HEIGHT / 2));
        enemies.add(titan);

        enemy1 = new Enemy(TextureRegion.split(new Texture(Gdx.files.internal("Level4/KnightArcher.png")), 64, 64), "KNIGHT ARCHER", 1, 5, 15);
        enemy1.setPosition(MainGameScreen.WIDTH - 192, MainGameScreen.HEIGHT / 2-128);
        mapCollisions.add(new Location(MainGameScreen.WIDTH - 192, MainGameScreen.HEIGHT / 2-128 ));
        enemies.add(enemy1);

        enemy2 = new Enemy(TextureRegion.split(new Texture(Gdx.files.internal("Level4/KnightArcher.png")), 64, 64), "KNIGHT ARCHER", 1, 5, 15);
        enemy2.setPosition(MainGameScreen.WIDTH - 192, MainGameScreen.HEIGHT / 2 + 128);
        mapCollisions.add(new Location(MainGameScreen.WIDTH - 192, MainGameScreen.HEIGHT / 2 + 128));
        enemies.add(enemy2);

        enemy3 = new Enemy(TextureRegion.split(new Texture(Gdx.files.internal("Level4/KnightSpear.png")), 64, 64), "KNIGHT SPEAR", 2, 2, 10);
        enemy3.setPosition(MainGameScreen.WIDTH - 448, MainGameScreen.HEIGHT / 2 - 192);
        mapCollisions.add(new Location(MainGameScreen.WIDTH - 448, MainGameScreen.HEIGHT / 2 - 192));
        enemies.add(enemy3);

        enemy4 = new Enemy(TextureRegion.split(new Texture(Gdx.files.internal("Level4/KnightSpear.png")), 64, 64), "KNIGHT SPEAR", 2, 2, 10);
        enemy4.setPosition(MainGameScreen.WIDTH - 448, MainGameScreen.HEIGHT / 2 - 64);
        mapCollisions.add(new Location(MainGameScreen.WIDTH - 448, MainGameScreen.HEIGHT / 2 - 64));
        enemies.add(enemy4);

        enemy5 = new Enemy(TextureRegion.split(new Texture(Gdx.files.internal("Level4/KnightSpear.png")), 64, 64), "KNIGHT SPEAR", 2, 2, 10);
        enemy5.setPosition(MainGameScreen.WIDTH - 448, MainGameScreen.HEIGHT / 2 + 64);
        mapCollisions.add(new Location(MainGameScreen.WIDTH - 448, MainGameScreen.HEIGHT / 2 + 64));
        enemies.add(enemy5);

    }
}

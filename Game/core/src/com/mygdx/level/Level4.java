package com.mygdx.level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.Enemy;
import com.mygdx.game.Hero;
import com.mygdx.game.Location;
import com.mygdx.screen.MainGameScreen;

public class Level4 extends AbstractLevel {
    private Hero achille,helen,hercules,hypolyta,thesius;
    private Enemy titan,enemy1,enemy2,enemy3,enemy4,enemy5;

    public Level4() {
        //64,128,192,256,320,384,448,512,576,640,704,768,832,896,960,1024,1088,1152,1216,1280
        achille = new Hero(TextureRegion.split(new Texture(Gdx.files.internal("Heroes/AchillesHealthBar.png")), 64, 64), "ACHILLE", 1, 1, 30);
        achille.setPosition(64, 704);
        mapCollisions.add(new Location(64, 128));
        heroes.add(achille);

        helen = new Hero(TextureRegion.split(new Texture(Gdx.files.internal("Heroes/HelenHealthBar.png")), 64, 64), "HELEN", 3, 5, 25);
        helen.setPosition(128, 256);
        mapCollisions.add(new Location(128, 256));
        heroes.add(helen);

        hercules = new Hero(TextureRegion.split(new Texture(Gdx.files.internal("Heroes/HerculesHealthBar.png")), 64, 64), "HERCULES", 2, 3, 35);
        hercules.setPosition(64, 320);
        mapCollisions.add(new Location(0, 320));
        heroes.add(hercules);

        hypolyta = new Hero(TextureRegion.split(new Texture(Gdx.files.internal("Heroes/HippolytaHealthBar.png")), 64, 64), "HYPOLYTA", 3, 2, 25);
        hypolyta.setPosition(128, 448);
        mapCollisions.add(new Location(128, 448));
        heroes.add(hypolyta);

        thesius = new Hero(TextureRegion.split(new Texture(Gdx.files.internal("Heroes/ThesiusHealthBar.png")), 64, 64), "THESIUS", 1, 15, 100);
        thesius.setPosition(192, 576);
        mapCollisions.add(new Location(192, 576));
        heroes.add(thesius);

        titan = new Enemy(TextureRegion.split(new Texture(Gdx.files.internal("Level4/KnightCaptain.png")), 64, 64), "KNIGHT CAPTAIN", 4, 1, 40);
        titan.setPosition(MainGameScreen.WIDTH - 128, MainGameScreen.HEIGHT / 2);
        mapCollisions.add(new Location(MainGameScreen.WIDTH - 128, MainGameScreen.HEIGHT / 2));
        enemies.add(titan);

        enemy1 = new Enemy(TextureRegion.split(new Texture(Gdx.files.internal("Level4/KnightArcher.png")), 64, 64), "KNIGHT ARCHER", 1, 5, 15);
        enemy1.setPosition(MainGameScreen.WIDTH - 192, MainGameScreen.HEIGHT / 2 - 64);
        mapCollisions.add(new Location(MainGameScreen.WIDTH - 192, MainGameScreen.HEIGHT / 2 - 64));
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

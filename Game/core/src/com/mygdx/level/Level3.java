package com.mygdx.level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.Enemy;
import com.mygdx.game.Location;
import com.mygdx.screen.MainGameScreen;

public class Level3 extends AbstractLevel {

    private Enemy titan,enemy1,enemy2,enemy3,enemy4;

    public Level3() {
        super();
        //64,128,192,256,320,384,448,512,576,640,704,768,832,896,960,1024,1088,1152,1216,1280
        achille.setPosition(192, 128);
        mapCollisions.add(new Location(64, 128));
        heroes.add(achille);

        helen.setPosition(128, 256);
        mapCollisions.add(new Location(128, 256));
        heroes.add(helen);

        hercules.setPosition(0, 320);
        mapCollisions.add(new Location(0, 320));
        heroes.add(hercules);

        hypolyta.setPosition(128, 512);
        mapCollisions.add(new Location(128, 512));
        heroes.add(hypolyta);

        thesius.setPosition(192, 640);
        mapCollisions.add(new Location(192, 640));
        heroes.add(thesius);

        titan = new Enemy(TextureRegion.split(new Texture(Gdx.files.internal("Level3/MinotaurHealthBar.png")), 64, 64), "MINOTAUR", 2, 2, 40);
        titan.setPosition(MainGameScreen.WIDTH - 128, MainGameScreen.HEIGHT / 2+64);
        mapCollisions.add(new Location(MainGameScreen.WIDTH - 128, MainGameScreen.HEIGHT / 2+64));
        enemies.add(titan);

        enemy1 = new Enemy(TextureRegion.split(new Texture(Gdx.files.internal("Level3/ElfBowHealthBar.png")), 64, 64), "ELF BOW", 1, 3, 5);
        enemy1.setPosition(MainGameScreen.WIDTH - 256, MainGameScreen.HEIGHT / 2 - 128);
        mapCollisions.add(new Location(MainGameScreen.WIDTH - 256, MainGameScreen.HEIGHT / 2 - 128));
        enemies.add(enemy1);

        enemy2 = new Enemy(TextureRegion.split(new Texture(Gdx.files.internal("Level3/ElfBowHealthBar.png")), 64, 64), "ELF BOW", 1, 3, 5);
        enemy2.setPosition(MainGameScreen.WIDTH - 256, MainGameScreen.HEIGHT / 2 + 128);
        mapCollisions.add(new Location(MainGameScreen.WIDTH - 256, MainGameScreen.HEIGHT / 2 + 128));
        enemies.add(enemy2);

        enemy3 = new Enemy(TextureRegion.split(new Texture(Gdx.files.internal("Level3/ElfDaggerHealthBar.png")), 64, 64), "ELF DAGGER", 2, 2, 10);
        enemy3.setPosition(MainGameScreen.WIDTH - 384, MainGameScreen.HEIGHT / 2 - 192);
        mapCollisions.add(new Location(MainGameScreen.WIDTH - 384, MainGameScreen.HEIGHT / 2 - 192));
        enemies.add(enemy3);

        enemy4 = new Enemy(TextureRegion.split(new Texture(Gdx.files.internal("Level3/ElfDaggerHealthBar.png")), 64, 64), "ELF DAGGER", 2, 2, 10);
        enemy4.setPosition(MainGameScreen.WIDTH - 384, MainGameScreen.HEIGHT / 2 + 64);
        mapCollisions.add(new Location(MainGameScreen.WIDTH - 384, MainGameScreen.HEIGHT / 2 + 64));
        enemies.add(enemy4);

    }
}

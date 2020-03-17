package com.mygdx.level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.mygdx.game.GameUnit;
import com.mygdx.game.Location;

import java.util.LinkedList;
import java.util.List;
import java.util.Timer;

public interface Level {
    Timer timer = new Timer();
    List<GameUnit> heroes = new LinkedList<GameUnit>();
    List<GameUnit> enemies = new LinkedList<GameUnit>();
    List<Location> validMoves = new LinkedList<Location>();
    List<Location> validAttacks = new LinkedList<Location>();
    List<Location> mapCollisions = new LinkedList<Location>();
    Texture position = new Texture(Gdx.files.internal("yellowSelect.png"));
    Texture attack = new Texture(Gdx.files.internal("redSelect.png"));
    void Draw(SpriteBatch batch);
    GameUnit getPieceHero(Location location);
    GameUnit getPieceEnemy(Location location);
    GameUnit findNearestHero(float x, float y);
    GameUnit findNearestHeroTouch(float x, float y);
    GameUnit findNearestEnemyTouch(float x, float y);
    Location findNearestLocation(float x, float y);
    void movePiece(Location from, Location to);
    void resetMovement();
    void act();
    boolean enemiesDead();
    void removeAll();
    boolean collide(Location l);
    void addLayer(TiledMapTileLayer layer);
}

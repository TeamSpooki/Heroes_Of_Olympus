package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public interface Level {
    public List<GameUnit> heroes = new LinkedList<GameUnit>();
    public List<GameUnit> enemies = new LinkedList<GameUnit>();
    public List<Location> validMoves = new LinkedList<Location>();
    public List<Location> validAttacks = new LinkedList<Location>();
    Map<Location,GameUnit> board= new HashMap<Location, GameUnit>();
    public Texture position = new Texture(Gdx.files.internal("yellowSelect.png"));
    public Texture attack = new Texture(Gdx.files.internal("redSelect.png"));
    public void Draw( SpriteBatch batch);
    public GameUnit GetPieceHero(Location location);
    public GameUnit GetPieceEnemy(Location location);
    public GameUnit findNearestHero(float x, float y);
    public GameUnit findNearestHeroTouch(float x, float y);
    public GameUnit findNearestEnemyTouch(float x, float y);
    public Location findNearestLocation(float x, float y);
    public void movePiece(Location from,Location to);
    public void resetMovement();
    public void act();
    public boolean enemiesDead();
    public void removeAll();
}

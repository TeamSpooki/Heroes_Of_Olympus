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

/**
 * Level interface implemented following template method design pattern
 */
public interface Level {
    /**
     * Timer used for delaying animation
     */
    Timer timer = new Timer();
    /**
     * List of heroes
     */
    List<GameUnit> heroes = new LinkedList<GameUnit>();
    /**
     * List of enemies
     */
    List<GameUnit> enemies = new LinkedList<GameUnit>();
    /**
     * List of valid moves for a hero
     */
    List<Location> validMoves = new LinkedList<Location>();
    /**
     * List of valid enemies location to attack for hero
     */
    List<Location> validAttacks = new LinkedList<Location>();
    /**
     * List of locations to identify collisions
     */
    List<Location> mapCollisions = new LinkedList<Location>();
    /**
     * List of locations to identify hazards
     */
    List<Location> hazard = new LinkedList<Location>();
    /**
     * List of locations for flowers
     */
    List<Location> flowers = new LinkedList<Location>();
    /**
     * Texture for valid moves
     */
    Texture position = new Texture(Gdx.files.internal("yellowSelect.png"));
    /**
     * Texture for valid attacks
     */
    Texture attack = new Texture(Gdx.files.internal("redSelect.png"));
    /**
     * Texture for valid flower
     */
    Texture flower = new Texture(Gdx.files.internal("lotusflower.png"));

    /**
     * draw method used in render for drawing different textures
     * @param batch
     */
    void draw(SpriteBatch batch);

    /**
     * Returns a specific hero from a give location
     * @param location
     * @return GameUnit
     */
    GameUnit getPieceHero(Location location);

    /**
     * Returns a specific enemy from a give location
     * @param location
     * @return
     */
    GameUnit getPieceEnemy(Location location);

    /**
     * Returns the nearest hero
     * @param x
     * @param y
     * @return GameUnit
     */
    GameUnit findNearestHero(float x, float y);

    /**
     * Returns the nearest hero to the touch of screen
     * @param x
     * @param y
     * @return GameUnit
     */
    GameUnit findNearestHeroTouch(float x, float y);

    /**
     * Returns the nearest enemy to the touch of screen
     * @param x
     * @param y
     * @return GameUnit
     */
    GameUnit findNearestEnemyTouch(float x, float y);

    /**
     * Returns the nearest valid move location
     * @param x
     * @param y
     * @return Location
     */
    Location findNearestLocation(float x, float y);

    /**
     * Move a GameUnit from one location to another
     * @param from
     * @param to
     */
    void movePiece(Location from, Location to);

    /**
     * Reset all the movements
     */
    void resetMovement();

    /**
     * Simple Artificial Intelligence for enemies
     */
    void act();

    /**
     * Returns True if all enemies are dead, False otherwise
     * @return boolean
     */
    boolean enemiesDead();

    /**
     * Returns True if all heroes are dead, False otherwise
     * @return boolean
     */
    boolean heroesDead();

    /**
     * Clear all list variables
     */
    void removeAll();

    /**
     * Check if location collide with list of collisions
     * @param loc
     * @return boolean
     */
    boolean collide(Location loc);

    /**
     * Add layers of maps to mapCollision
     * @param layer
     */
    void addLayer(TiledMapTileLayer layer);

    /**
     * Return information of all units
     * @return String
     */
    String toString();

    /**
     * Add layers of hazard
     * @param layer
     */
    void addHazard(TiledMapTileLayer layer);

    /**
     * Get heroes list size
     * @return
     */
    int getHeroesSize();

    /**
     * Developer mode
     */
    void devMode();
}

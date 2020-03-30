package com.mygdx.level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.mygdx.game.Animate;
import com.mygdx.game.GameUnit;
import com.mygdx.game.Hero;
import com.mygdx.game.Location;

import java.util.Random;
import java.util.TimerTask;

/**
 * Abstract class that implements level
 */
abstract class AbstractLevel implements Level{
    protected Hero achille,helen,hercules,hypolyta,thesius;
    private Sound action;
    public AbstractLevel(){

        achille = new Hero(TextureRegion.split(new Texture(Gdx.files.internal("Heroes/AchillesHealthBar.png")),64,64),"ACHILLE",1,1,15);
        action = Gdx.audio.newSound(Gdx.files.internal("Sounds/Spear.mp3"));
        achille.setSound(action);

        helen = new Hero(TextureRegion.split(new Texture(Gdx.files.internal("Heroes/HelenHealthBar.png")),64,64),"HELEN",3,4,10);
        action = Gdx.audio.newSound(Gdx.files.internal("Sounds/Magic.mp3"));
        helen.setSound(action);

        hercules = new Hero(TextureRegion.split(new Texture(Gdx.files.internal("Heroes/HerculesHealthBar.png")),64,64),"HERCULES",2,3,20);
        action = Gdx.audio.newSound(Gdx.files.internal("Sounds/Spear.mp3"));
        hercules.setSound(action);

        hypolyta = new Hero(TextureRegion.split(new Texture(Gdx.files.internal("Heroes/HippolytaHealthBar.png")),64,64),"HYPOLYTA",3,2,10);
        action = Gdx.audio.newSound(Gdx.files.internal("Sounds/Sword.mp3"));
        hypolyta.setSound(action);

        thesius = new Hero(TextureRegion.split( new Texture(Gdx.files.internal("Heroes/ThesiusHealthBar.png")),64,64),"THESIUS",1,15,100);
        action = Gdx.audio.newSound(Gdx.files.internal("Sounds/Bow.ogg"));
        thesius.setSound(action);
    }
    public void draw(SpriteBatch batch){
        for(GameUnit hero:heroes)
        {
            hero.draw(batch);
        }
        for(GameUnit enemy:enemies)
        {
            enemy.draw(batch);
        }
        if(!validMoves.isEmpty()) {
            for(Location loc:validMoves)
            {
                batch.draw(position, loc.getX(), loc.getY());
            }
        }
        if(!validAttacks.isEmpty()) {
            for(Location loc:validAttacks)
            {
                batch.draw(attack, loc.getX(), loc.getY());
            }
        }
    }
    public GameUnit getPieceHero(Location location) throws NullPointerException{
        for(GameUnit hero:heroes)
        {
            if(hero.getLocation().equals(location)) {
                return hero;
            }
        }
        return null;
    }
    public GameUnit getPieceEnemy(Location location) throws NullPointerException{
        for(GameUnit enemy:enemies)
        {
            if(enemy.getLocation().equals(location)) {
                return enemy;
            }
        }
        return null;
    }
    public GameUnit findNearestHeroTouch(float x, float y) throws NullPointerException{
        for(GameUnit hero:heroes)
        {
            if(x>=hero.getX()&&x<=hero.getX()+64&&y>=hero.getY()&&y<=hero.getY()+64) {
                return hero;
            }
        }
        return null;
    }
    public GameUnit findNearestHero(float x, float y) throws NullPointerException{
        GameUnit nearestHero=null;
        double distance= 0;
        double checkDistance;
        for(GameUnit hero:heroes)
        {
            if(!hero.isDead()){
                if(nearestHero==null){
                    nearestHero=hero;
                    distance = Math.sqrt((nearestHero.getX()-x)*(nearestHero.getX()-x)+(nearestHero.getY()-y)*(nearestHero.getY()-y));
                }else{
                    checkDistance = Math.sqrt((hero.getX()-x)*(hero.getX()-x)+(hero.getY()-y)*(hero.getY()-y));
                    if(checkDistance<distance){
                        distance=checkDistance;
                        nearestHero=hero;
                    }
                }
            }
        }
        return nearestHero;
    }
    public GameUnit findNearestEnemyTouch(float x, float y) throws NullPointerException{
        for(GameUnit enemy:enemies)
        {
            if(x>=enemy.getX()&&x<=enemy.getX()+64&&y>=enemy.getY()&&y<=enemy.getY()+64) {
                return enemy;
            }
        }
        return null;
    }
    public Location findNearestLocation(float x, float y) throws NullPointerException{
        for(Location loc:validMoves)
        {
            if(x>=loc.getX()&&x<=loc.getX()+64&&y>=loc.getY()&&y<=loc.getY()+64) {
                return loc;
            }
        }
        return null;
    }
    public void movePiece(Location from,Location to){
        for(GameUnit hero:heroes)
        {
            if(hero.getLocation().equals(from)&&!collide(new Location(to.getX(),to.getY()))) {
                mapCollisions.remove(hero.getLocation());
                hero.setPosition(to.getX(), to.getY());
                mapCollisions.add(hero.getLocation());
            }
        }
        for(GameUnit enemy:enemies)
        {
            if(enemy.getLocation().equals(from) &&!collide(new Location(to.getX(), to.getY()))) {
                mapCollisions.remove(enemy.getLocation());
                enemy.setPosition(to.getX(), to.getY());
                mapCollisions.add(enemy.getLocation());
            }
        }
    }
    public void resetMovement(){
        for(GameUnit hero:heroes)
        {
            hero.setMoved(false);
        }
        for(GameUnit enemy:enemies)
        {
            enemy.setMoved(true);
        }
    }
    public void act(){
        Location movement;
        Random rand = new Random();
        final GameUnit enemy= enemies.get(rand.nextInt(enemies.size()));
        final GameUnit nearestHero = findNearestHero(enemy.getX(),enemy.getY());
        if(!enemy.isDead()){
            if(enemy.isInBounds(nearestHero.getX(),nearestHero.getY(),enemy.getAttackRange())){
                enemy.setAnimation(Animate.ATTACK);
                timer.schedule(new TimerTask() {
                    public void run() {
                        getPieceHero(nearestHero.getLocation()).setHealth(getPieceHero(nearestHero.getLocation()).getHealth()-enemy.getDamage());
                        enemy.setAnimation(Animate.STAY);
                    }}, 1000);
            } else if (enemy.isMoved()) {
                for(int i=0;i<enemy.getMovementRange();i++){
                    movement = enemy.getLocation();
                    if (nearestHero.getX() <= enemy.getX()) {
                        if(!collide(movement.leftLocation())){
                            movement = movement.leftLocation();
                        }
                    } if (nearestHero.getX() >= enemy.getX()) {
                        if(!collide(movement.rightLocation())){
                            movement = movement.rightLocation();
                        }
                    }
                    if (nearestHero.getY() <= enemy.getY()) {
                        if(!collide(movement.aboveLocation())){
                            movement = movement.aboveLocation();
                        }
                    } if (nearestHero.getY() >= enemy.getY()) {
                        if(!collide(movement.belowLocation())){
                            movement = movement.belowLocation();
                        }
                    }
                    final Location finalMovement = movement;
                    enemy.setAnimation(Animate.WALK);
                    timer.schedule(new TimerTask() {
                        public void run() {
                            movePiece(enemy.getLocation(), finalMovement);
                            enemy.setAnimation(Animate.STAY);
                        }}, 1000);
                }
            }
        }
    }
    public boolean enemiesDead() {
        int size= enemies.size();
        int counter = 0;
        for (GameUnit enemy : enemies){
            if(enemy.isDead()){
                counter++;
            }
        }
        if(counter ==size){
            return true;
        }else{
            return false;
        }
    }
    public boolean heroesDead(){
        int counter = 0;
        for (GameUnit hero : heroes){
            if(hero.isDead()){
                counter++;
            }
        }
        if(counter == 5){
            return true;
        }else{
            return false;
        }
    }
    public void removeAll(){
        heroes.removeAll(heroes);
        enemies.removeAll(enemies);
        validMoves.removeAll(validMoves);
        validAttacks.removeAll(validAttacks);
        mapCollisions.removeAll(mapCollisions);
    }
    public boolean collide(Location location){
        if(mapCollisions.contains(location)){
            return true;
        }
        return false;
    }
    public void addLayer(TiledMapTileLayer layer){
        for (int x = 0; x < layer.getWidth(); x++) {
            for (int y = 0; y < layer.getHeight(); y++) {
                TiledMapTileLayer.Cell cell = layer.getCell(x, y);
                if (cell != null) {
                    Location loc = new Location(((x * 64)), ((y * 64)));
                    mapCollisions.add(loc);
                }
            }
        }

    }

    @Override
    public String toString() {
        String str="";
        for (GameUnit hero : heroes){
            str += hero.toString()+"\n";
        }
        for (GameUnit enemy : enemies){
            str += enemy.toString()+"\n";
        }
        System.out.println(str);
        return str;
    }

    public int getHeroesSize(){
        int counter=0;
        for (GameUnit hero : heroes){
            if(!hero.isDead()){
                counter++;
            }
        }
        return counter;
    }
    public int getEnemiesSize(){
        int counter=0;
        for (GameUnit enemy : enemies){
            if(!enemy.isDead()){
                counter++;
            }
        }
        return counter;
    }
}
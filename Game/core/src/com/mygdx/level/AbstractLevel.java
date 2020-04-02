package com.mygdx.level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.mygdx.game.Animate;
import com.mygdx.game.GameUnit;
import com.mygdx.game.Hero;
import com.mygdx.game.Location;

import java.util.List;
import java.util.Random;
import java.util.TimerTask;

/**
 * Abstract class that implements level
 */
abstract class AbstractLevel implements Level{
    protected Hero achille,helen,hercules,hypolyta,thesius;
    public AbstractLevel(){

        achille = new Hero(TextureRegion.split(new Texture(Gdx.files.internal("Heroes/AchillesHealthBar.png")),64,64),"ACHILLE",1,1,20);
        achille.setSound(Gdx.audio.newSound(Gdx.files.internal("Sounds/Spear.mp3")));

        helen = new Hero(TextureRegion.split(new Texture(Gdx.files.internal("Heroes/HelenHealthBar.png")),64,64),"HELEN",3,4,15);
        helen.setSound(Gdx.audio.newSound(Gdx.files.internal("Sounds/Magic.mp3")));

        hercules = new Hero(TextureRegion.split(new Texture(Gdx.files.internal("Heroes/HerculesHealthBar.png")),64,64),"HERCULES",2,2,25);
        hercules.setSound(Gdx.audio.newSound(Gdx.files.internal("Sounds/Spear.mp3")));

        hypolyta = new Hero(TextureRegion.split(new Texture(Gdx.files.internal("Heroes/HippolytaHealthBar.png")),64,64),"HYPPOLYTA",3,2,15);
        hypolyta.setSound(Gdx.audio.newSound(Gdx.files.internal("Sounds/Sword.mp3")));

        thesius = new Hero(TextureRegion.split( new Texture(Gdx.files.internal("Heroes/ThesiusHealthBar.png")),64,64),"THESEUS",1,6,20);
        thesius.setSound(Gdx.audio.newSound(Gdx.files.internal("Sounds/Bow.ogg")));

        Random rand = new Random();
        for(int i=0;i<5;i++){
            flowers.add(new Location(64*rand.nextInt(21),64*rand.nextInt(10)));
        }
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
        for(Location loc:flowers)
        {
            if(!collide(loc)){
                batch.draw(flower,loc.getX(),loc.getY());
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
            if(hero.getLocation().equals(from)&&!collide(to)) {
                mapCollisions.remove(hero.getLocation());
                hero.setPosition(to.getX(), to.getY());
                mapCollisions.add(hero.getLocation());
                if(hazard.contains(hero.getLocation())){
                    hero.setHealth(hero.getHealth()-10);
                }
                if(flowers.contains(hero.getLocation())){
                    flowers.remove(hero.getLocation());
                    hero.setHealth(100);
                }
            }
        }
        for(GameUnit enemy:enemies)
        {
            if(enemy.getLocation().equals(from) &&!collide(to)&&!hazard.contains(to)) {
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
        GameUnit tempEnemy;
        do{
            tempEnemy= enemies.get(rand.nextInt(enemies.size()));
        }while(tempEnemy.isDead());
        final GameUnit enemy=tempEnemy;
        final GameUnit nearestHero = findNearestHero(enemy.getX(),enemy.getY());

        if(enemy.isInBounds(nearestHero.getX(),nearestHero.getY(),enemy.getAttackRange())){
            enemy.setAnimation(Animate.ATTACK);
            timer.schedule(new TimerTask() {
                    public void run() { enemy.playAttack();getPieceHero(nearestHero.getLocation()).setHealth(getPieceHero(nearestHero.getLocation()).getHealth()-enemy.getDamage());enemy.setAnimation(Animate.STAY); }}, 1000);
        } else if (enemy.isMoved()) {
            movement = enemy.getLocation();
            for(int i=0;i<enemy.getMovementRange();i++){
                if (nearestHero.getX() <= enemy.getX()) {
                    if(!collide(movement.leftLocation())){
                        movement = movement.leftLocation();
                    }
                }
                if (nearestHero.getX() >= enemy.getX()) {
                    if(!collide(movement.rightLocation())){
                        movement = movement.rightLocation();
                    }
                }
                if (nearestHero.getY() <= enemy.getY()) {
                    if(!collide(movement.aboveLocation())){
                        movement = movement.aboveLocation();
                    }
                }
                if (nearestHero.getY() >= enemy.getY()) {
                    if(!collide(movement.belowLocation())){
                        movement = movement.belowLocation();
                    }
                }
            }
            final Location finalMovement = movement;
            enemy.setAnimation(Animate.WALK);
            timer.schedule(new TimerTask() {
                public void run() { enemy.playMove();movePiece(enemy.getLocation(), finalMovement);enemy.setAnimation(Animate.STAY); }}, 500);
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
        if(counter == heroes.size()){
            return true;
        }else{
            return false;
        }
    }
    
    public List<GameUnit> getHeroes(){
    	return heroes;
    }
    
    public List<GameUnit> getEnemies(){
    	return enemies;
    }
    
    public void removeAll(){
        heroes.removeAll(heroes);
        enemies.removeAll(enemies);
        validMoves.removeAll(validMoves);
        validAttacks.removeAll(validAttacks);
        mapCollisions.removeAll(mapCollisions);
        hazard.removeAll(hazard);
        flowers.removeAll(flowers);
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

    public void addHazard(TiledMapTileLayer layer){
        for (int x = 0; x < layer.getWidth(); x++){
            for (int y = 0; y < layer.getHeight(); y++) {
                TiledMapTileLayer.Cell cell = layer.getCell(x, y);
                if (cell != null) {
                    Location loc = new Location(((x * 64)), ((y * 64)));
                    hazard.add(loc);
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

    @Override
    public void devMode() {
        for (GameUnit hero : heroes){
            hero.setDamage(100);
            hero.setAttackRange(15);
        }
    }
}
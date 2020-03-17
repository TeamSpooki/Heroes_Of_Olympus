package com.mygdx.level;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.mygdx.game.Animate;
import com.mygdx.game.GameUnit;
import com.mygdx.game.Location;

import java.util.Random;
import java.util.TimerTask;

abstract class AbstractLevel implements Level{

    public void Draw( SpriteBatch batch){

        for(GameUnit u:heroes)
        {
            u.draw(batch);
        }
        for(GameUnit e:enemies)
        {
            e.draw(batch);
        }
        if(!validMoves.isEmpty()) {
            for(Location l:validMoves)
            {
                batch.draw(position, l.getX(), l.getY());
            }

        }
        if(!validAttacks.isEmpty()) {
            for(Location l:validAttacks)
            {
                batch.draw(attack, l.getX(), l.getY());
            }

        }
    }
    public GameUnit getPieceHero(Location location){
        for(GameUnit u:heroes)
        {
            if(u.getLocation().equals(location)) {
                return u;
            }
        }
        return null;
    }
    public GameUnit getPieceEnemy(Location location){
        for(GameUnit e:enemies)
        {
            if(e.getLocation().equals(location)) {
                return e;
            }
        }
        return null;
    }
    public GameUnit findNearestHeroTouch(float x, float y){
        for(GameUnit u:heroes)
        {
            if(x>=u.getX()&&x<=u.getX()+64&&y>=u.getY()&&y<=u.getY()+64) {
                return u;
            }
        }
        return null;
    }
    public GameUnit findNearestHero(float x, float y){
        GameUnit hero=null;
        double distance= 0;
        double checkDistance=0;
        for(GameUnit u:heroes)
        {
            if(!u.isDead()){
                if(hero==null){
                    hero=u;
                    distance = Math.sqrt((hero.getX()-x)*(hero.getX()-x)+(hero.getY()-y)*(hero.getY()-y));
                }else{
                    checkDistance = Math.sqrt((u.getX()-x)*(u.getX()-x)+(u.getY()-y)*(u.getY()-y));
                    if(checkDistance<distance){
                        distance=checkDistance;
                        hero=u;
                    }
                }
            }
        }
        return hero;
    }
    public GameUnit findNearestEnemyTouch(float x, float y){
        for(GameUnit e:enemies)
        {
            if(x>=e.getX()&&x<=e.getX()+64&&y>=e.getY()&&y<=e.getY()+64) {
                return e;
            }
        }
        return null;
    }
    public Location findNearestLocation(float x, float y){
        for(Location l:validMoves)
        {
            if(x>=l.getX()&&x<=l.getX()+64&&y>=l.getY()&&y<=l.getY()+64) {
                return l;
            }
        }
        return null;
    }
    public void movePiece(Location from,Location to){
        for(GameUnit u:heroes)
        {
            if(u.getLocation().equals(from)&&!collide(new Location(to.getX(),to.getY()))) {
                mapCollisions.remove(u.getLocation());
                u.setPosition(to.getX(), to.getY());
                mapCollisions.add(u.getLocation());
            }
        }
        for(GameUnit e:enemies)
        {
            if(e.getLocation().equals(from) &&!collide(new Location(to.getX(), to.getY()))) {
                mapCollisions.remove(e.getLocation());
                e.setPosition(to.getX(), to.getY());
                mapCollisions.add(e.getLocation());
            }
        }
    }
    public void resetMovement(){
        for(GameUnit u:heroes)
        {
            u.setMoved(false);
        }
        for(GameUnit e:enemies)
        {
            e.setMoved(true);
        }
    }
    public void act(){
        Location movement;
        Random rand = new Random();
        final GameUnit e= enemies.get(rand.nextInt(enemies.size()));
        final GameUnit nearestHero = findNearestHero(e.getX(),e.getY());
        if(e.isInBounds(nearestHero.getX(),nearestHero.getY(),e.getAttackRange())){
            e.setAnimation(Animate.ATTACK);
            timer.schedule(new TimerTask() {
                  public void run() {
                      getPieceHero(nearestHero.getLocation()).setHealth(getPieceHero(nearestHero.getLocation()).getHealth()-e.getDamage());
                      e.setAnimation(Animate.STAY);
                    }}, 1000);
        }
        if (e.isMoved()) {
            movement = e.getLocation();
            if (nearestHero.getX() <= e.getX()) {
                movement = movement.leftLocation();
            } else if (nearestHero.getX() >= e.getX()) {
                movement = movement.rightLocation();
            }
            if (nearestHero.getY() <= e.getY()) {
                movement = movement.aboveLocation();
            } else if (nearestHero.getY() >= e.getY()) {
                movement = movement.belowLocation();
            }
            final Location finalMovement = movement;
            e.setAnimation(Animate.WALK);
            timer.schedule(new TimerTask() {
                public void run() {
                    movePiece(e.getLocation(), finalMovement);
                    e.setMoved(false);
                    e.setAnimation(Animate.STAY);
                }}, 1000);


        }

    }
    public boolean enemiesDead() {
        int size= enemies.size();
        int counter = 0;
        for (GameUnit e : enemies){
            if(e.isDead()){
                counter++;
            }
        }
        if(counter ==size){
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
    }
    public boolean collide(Location l){
        if(mapCollisions.contains(l)){
            return true;
        }
        return false;
    }
    public void addLayer(TiledMapTileLayer layer){
        for (int x = 0; x < layer.getWidth(); x++) {
            for (int y = 0; y < layer.getHeight(); y++) {
                TiledMapTileLayer.Cell cell = layer.getCell(x, y);
                if (cell != null) {
                    Location l = new Location(((x * 64)), ((y * 64)));
                    mapCollisions.add(l);
                }
            }
        }

    }
}

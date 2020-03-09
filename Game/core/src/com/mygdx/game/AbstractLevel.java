package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Random;

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
    public GameUnit GetPieceHero(Location location){
        for(GameUnit u:heroes)
        {
            if(u.location.equals(location)) {
                return u;
            }
        }
        return null;
    }
    public GameUnit GetPieceEnemy(Location location){
        for(GameUnit e:enemies)
        {
            if(e.location.equals(location)) {
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
            if(!u.dead){
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
            if(u.location.equals(from)) {
                u.setPosition(to.getX(), to.getY());
            }
        }
        for(GameUnit e:enemies)
        {
            if(e.location.equals(from)) {
                e.setPosition(to.getX(), to.getY());
            }
        }
    }
    public void resetMovement(){
        for(GameUnit u:heroes)
        {
            u.setMoved(false);
        }
    }
    public void act(){
        GameUnit nearestHero = null;
        Location movement = null;
        for(GameUnit e:enemies)
        {
            nearestHero = findNearestHero(e.getX(),e.getY());
            if(e.isInBounds(nearestHero.getX(),nearestHero.getY(),e.attackRange)){
                GetPieceHero(nearestHero.location).setHealth(GetPieceHero(nearestHero.location).getHealth()-e.getDamage());
            }
            if(e.moved) {
                movement = e.getLocation();
                if(nearestHero.getX()<=e.getX()){
                    movement = movement.leftLocation();
                }else if (nearestHero.getX()>=e.getX()){
                        movement = movement.rightLocation();
                }
                if(nearestHero.getY()<=e.getY()){
                    movement=movement.aboveLocation();
                }else if(nearestHero.getY()>=e.getY()){
                    movement = movement.belowLocation();
                }
                movePiece(e.getLocation(),movement);
            }
        }
    }

    public boolean enemiesDead() {
        int size= enemies.size();
        int counter = 0;
        for (GameUnit e : enemies){
            if(e.dead){
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
        board.clear();
    }
}

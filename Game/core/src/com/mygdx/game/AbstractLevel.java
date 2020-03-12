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
    public GameUnit findNearestHero(float x, float y){
        for(GameUnit u:heroes)
        {
            if(x>=u.getX()&&x<=u.getX()+64&&y>=u.getY()&&y<=u.getY()+64) {
                return u;
            }
        }
        return null;
    }
    public GameUnit findNearestEnemy(float x, float y){
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
    }
    public void resetMovement(){
        for(GameUnit u:heroes)
        {
            u.setMoved(false);
        }
    }
    public void moveEnemies(){
        Random rand = new Random();
        int n=0;
        n= rand.nextInt(5);
        for(GameUnit e:enemies)
        {
            if(e.moved) {
                n = rand.nextInt(5)+1;
                switch(n) {
                    case 1:
                        e.moveUp();
                        break;
                    case 2:
                        e.moveDown();
                        break;
                    case 3:
                        e.moveRight();
                        break;
                    case 4:
                        e.moveLeft();
                        break;
                    default:
                        break;
                }
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

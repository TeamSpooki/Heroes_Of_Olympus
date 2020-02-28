package com.mygdx.game;

import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mygdx.world.GameMap;
import com.mygdx.world.TiledGameMap;

public class MainGameScreen implements Screen {
	public static final int WIDTH = 1280;
	public static final int HEIGHT = 850;
	Board board;
	public OrthographicCamera camera;
	Vector3 touch;
	Skin skin;
	OptionDialog options;
	Stage stage;
	GameUnit current,oldCurrent,enemy;
	HeroesOfOlympus game;
	Timer timer;
	GameMap gameMap;
	public static float elapsedTime= 0f;
	boolean move = false;
	boolean attack = false;
	int movements;
	public MainGameScreen (HeroesOfOlympus game) {
		this.game = game;
		gameMap = new TiledGameMap();
		stage = new Stage();
		skin = new Skin(Gdx.files.internal("uiskin.json"));
		camera = new OrthographicCamera();
		camera.setToOrtho(true, WIDTH, HEIGHT);
		camera.update();
		board= new Board();
		touch=new Vector3();
		movements=0;
		options = new OptionDialog("Options",skin);
		options.button("Attack", 1);
		options.button("Move", 2);
		options.button("Stay",3);
		Gdx.input.setInputProcessor(stage);
		timer = new Timer();
	}

	public void show() {}


	public void render (float delta) {
		elapsedTime+=Gdx.graphics.getDeltaTime();
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gameMap.render(camera);
		camera.update();
		game.batch.setProjectionMatrix(camera.combined);
		game.batch.begin();
		board.Draw(game.batch);

		game.batch.end();
		stage.act();
		stage.draw();
		if(Gdx.input.isTouched()) {
			touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(touch);
			if(board.findNearestHero(touch.x,touch.y) instanceof Hero) {
				oldCurrent=current;
				current=board.findNearestHero(touch.x,touch.y);
				if(oldCurrent!=current) {
					options.setHero(current);
					options.show(stage);
				}
			}
			if(attack) {
				if(board.findNearestEnemy(touch.x,touch.y) instanceof Enemy) {
					enemy=board.findNearestEnemy(touch.x,touch.y);
					board.GetPieceHero(current.getLocation()).a=board.GetPieceHero(current.getLocation()).a.ATTACK;
					timer.schedule(new TimerTask() {

						public void run() {
							board.GetPieceEnemy(enemy.getLocation()).deleteSprite();
							board.enemies.remove(enemy);
							board.validAttacks.clear();
							board.GetPieceHero(current.getLocation()).a=board.GetPieceHero(current.getLocation()).a.STAY;
						}
					}, 1000);
					attack=false;
					
				}
			}
			if(move){

				if(movements==5) {
					movements=0;
					board.resetMovement();

				}
				if(!board.GetPieceHero(current.getLocation()).isMoved()&&movements<5) {
					board.GetPieceHero(current.getLocation()).setMoved(true);
					board.GetPieceHero(current.getLocation()).a=board.GetPieceHero(current.getLocation()).a.WALK;
					timer.schedule(new TimerTask() {

						public void run() {
							board.MovePiece(current.getLocation(), board.findNearestLocation(touch.x,touch.y));
							board.moveEnemies();
							board.validMoves.clear();
							board.GetPieceHero(current.getLocation()).setMoved(false);
							board.GetPieceHero(current.getLocation()).a=board.GetPieceHero(current.getLocation()).a.STAY;
							movements++;
						}
					}, 1000);
					move=false;
				}
			}
			
		}
		
		if(board.enemies.isEmpty()) {
			this.dispose();
			game.setScreen(new OutroScreen(game));
		}
	}
	public void attack() {


	}

	public void dispose () {
		game.batch.dispose();
		stage.dispose();
	}

	public Object getBatch() { return null;}

	public void hide() {}

	public void resize(int width, int height) {}

	public void pause() {}

	public void resume() {}

	public class OptionDialog extends Dialog {
		GameUnit currentHero;

		public OptionDialog(String title, Skin skin) {
			super(title, skin);
			currentHero =null;

		}
		protected void setHero(GameUnit current) {
			this.currentHero=current;
		}

		protected void result (Object object) {
			if(object.equals(1)) {
				//attack
				for(GameUnit e : board.board.values() ) {
					if (e instanceof Enemy){
						float boundX = board.GetPieceHero(currentHero.getLocation()).attackRange*64+board.GetPieceHero(currentHero.getLocation()).getX();
						float boundY = board.GetPieceHero(currentHero.getLocation()).attackRange*64+board.GetPieceHero(currentHero.getLocation()).getY();
						if(e.location.getX()<boundX&&e.location.getY()<boundY) {
							board.validAttacks.add(e.location);
						}
						
					}
				}
				
				/*
				Location up =currentHero.getLocation().aboveLocation();
				Location down =currentHero.getLocation().belowLocation();
				Location left =currentHero.getLocation().leftLocation();
				Location right =currentHero.getLocation().rightLocation();
				board.possibleAttacks.add(up);
				board.possibleAttacks.add(down);
				board.possibleAttacks.add(left);
				board.possibleAttacks.add(right);
				for(int i = 1; i < board.GetPieceHero(currentHero.getLocation()).attackRange;i++) {
					for(Location l :board.possibleAttacks ) {
						if(board.GetPieceEnemy(l)==null) {
							board.possibleAttacks.remove(l);
						}else {
							board.possibleAttacks.add(l.aboveLocation());
							board.possibleAttacks.add(l.belowLocation());
							board.possibleAttacks.add(l.leftLocation());
							board.possibleAttacks.add(l.rightLocation());
							board.validAttacks.add(l);
						}
					}
					for(Location l :board.possibleAttacks ) {
						
					}
						//if(!board.possibleAttacks.contains(l)) {
							//board.possibleAttacks.add(l.aboveLocation());
							//board.possibleAttacks.add(l.belowLocation());
							//board.possibleAttacks.add(l.leftLocation());
							//board.possibleAttacks.add(l.rightLocation());
						//}
						
					//}
				}
				board.possibleAttacks.clear();
				*/
				if(!board.validAttacks.isEmpty()) {
					attack=true;
				}
				
			}
			else if (object.equals(2)){
				//movement
				Location up =currentHero.getLocation().aboveLocation();
				Location down =currentHero.getLocation().belowLocation();
				Location left =currentHero.getLocation().leftLocation();
				Location right =currentHero.getLocation().rightLocation();
				board.validMoves.add(up);
				board.validMoves.add(down);
				board.validMoves.add(left);
				board.validMoves.add(right);
				for(int i = 1; i < board.GetPieceHero(currentHero.getLocation()).movementRange;i++) {
					int j= board.validMoves.size()-1; 
					while(j>=0) {
					  //if(!board.validMoves.contains(board.validMoves.get(j).aboveLocation())) {
						  board.validMoves.add(board.validMoves.get(j).aboveLocation()); 
					  //}
					  //if(!board.validMoves.contains(board.validMoves.get(j).belowLocation())) {
					  board.validMoves.add(board.validMoves.get(j).belowLocation()); 
					  //}
					  //if(!board.validMoves.contains(board.validMoves.get(j).leftLocation())) {
					  board.validMoves.add(board.validMoves.get(j).leftLocation()); 
					  //}
					 // if(!board.validMoves.contains(board.validMoves.get(j).rightLocation())) {
					  board.validMoves.add(board.validMoves.get(j).rightLocation()); 
					  //} 
					j--; 
					 }
					 
				}
				move=true;
			}
			else {
				board.GetPieceHero(currentHero.getLocation()).a=board.GetPieceHero(currentHero.getLocation()).a.STAY;
			}
		}
	}
}

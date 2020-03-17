package com.mygdx.game;

import java.io.FileNotFoundException;
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
	public static final int HEIGHT = 1080;
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
	public MainGameScreen (HeroesOfOlympus game, Level level) {

		this.game = game;
		stage = new Stage();
		skin = new Skin(Gdx.files.internal("uiskin.json"));
		camera = new OrthographicCamera();
		camera.setToOrtho(true,WIDTH ,HEIGHT );
		camera.update();
		game.level = level;
		if(level instanceof Level1){
			gameMap = new TiledGameMap("Level1/Level1.tmx");
		}else if(level instanceof Level2){
			gameMap = new TiledGameMap("Level2/Level2.tmx");
		}
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
		game.level.Draw(game.batch);
		game.batch.end();
		stage.act();
		stage.draw();

		if(Gdx.input.isTouched()) {
			touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(touch);

			if(game.level.findNearestHero(touch.x,touch.y) instanceof Hero) {
				oldCurrent=current;
				current= game.level.findNearestHero(touch.x,touch.y);
				if(oldCurrent!=current) {
					options.setHero(current);
					options.show(stage);
				}
			}
			if(attack) {
				if(game.level.findNearestEnemy(touch.x,touch.y) instanceof Enemy) {
					enemy= game.level.findNearestEnemy(touch.x,touch.y);
					game.level.GetPieceHero(current.getLocation()).a= game.level.GetPieceHero(current.getLocation()).a.ATTACK;
					timer.schedule(new TimerTask() {
						public void run() {
							game.level.GetPieceEnemy(enemy.getLocation()).healthBar= game.level.GetPieceEnemy(enemy.getLocation()).healthBar- game.level.GetPieceHero(current.getLocation()).damage;
							game.level.validAttacks.clear();
							game.level.moveEnemies();
							game.level.GetPieceHero(current.getLocation()).a= game.level.GetPieceHero(current.getLocation()).a.STAY;
						}
					}, 1000);
					attack=false;
					
				}
			}
			if(move){

				if(movements==5) {
					movements=0;
					game.level.resetMovement();

				}
				if(!game.level.GetPieceHero(current.getLocation()).isMoved()&&movements<5) {
					game.level.GetPieceHero(current.getLocation()).setMoved(true);
					game.level.GetPieceHero(current.getLocation()).a= game.level.GetPieceHero(current.getLocation()).a.WALK;
					timer.schedule(new TimerTask() {

						public void run() {
							if(game.level.findNearestLocation(touch.x,touch.y)!=null) {
								game.level.movePiece(current.getLocation(), game.level.findNearestLocation(touch.x,touch.y));
								game.level.moveEnemies();
								game.level.validMoves.clear();
								game.level.GetPieceHero(current.getLocation()).setMoved(false);
								game.level.GetPieceHero(current.getLocation()).a= game.level.GetPieceHero(current.getLocation()).a.STAY;
								movements++;
								
							}else {
								game.level.validMoves.clear();
								game.level.GetPieceHero(current.getLocation()).setMoved(false);
								game.level.GetPieceHero(current.getLocation()).a= game.level.GetPieceHero(current.getLocation()).a.STAY;
								current=null;
							}
						}
					}, 1000);
					move=false;
				}
			}
			
		}

		if(game.level.enemiesDead()) {
			game.level.removeAll();

			try {
				game.setScreen(new OutroScreen(game));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void dispose () {
		game.dispose();
		stage.dispose();
	}

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
				for(GameUnit e : game.level.board.values() ) {
					if (e instanceof Enemy){
						float boundX = game.level.GetPieceHero(currentHero.getLocation()).attackRange*64+ game.level.GetPieceHero(currentHero.getLocation()).getX();
						float boundY = game.level.GetPieceHero(currentHero.getLocation()).attackRange*64+ game.level.GetPieceHero(currentHero.getLocation()).getY();
						if(e.location.getX()<boundX&&e.location.getY()<boundY) {
							game.level.validAttacks.add(e.location);
						}
					}
				}
				if(!game.level.validAttacks.isEmpty()) {
					attack=true;
				}
				
			}
			else if (object.equals(2)){
				//movement
				Location up =currentHero.getLocation().aboveLocation();
				Location down =currentHero.getLocation().belowLocation();
				Location left =currentHero.getLocation().leftLocation();
				Location right =currentHero.getLocation().rightLocation();
				game.level.validMoves.add(up);
				game.level.validMoves.add(down);
				game.level.validMoves.add(left);
				game.level.validMoves.add(right);
				for(int i = 1; i < game.level.GetPieceHero(currentHero.getLocation()).movementRange; i++) {
					int j= game.level.validMoves.size()-1;
					while(j>=0) {
						game.level.validMoves.add(game.level.validMoves.get(j).aboveLocation());
						game.level.validMoves.add(game.level.validMoves.get(j).belowLocation());
						game.level.validMoves.add(game.level.validMoves.get(j).leftLocation());
						game.level.validMoves.add(game.level.validMoves.get(j).rightLocation());
						j--; 
					 } 
				}
				move=true;
			}
			else {
				game.level.GetPieceHero(currentHero.getLocation()).a= game.level.GetPieceHero(currentHero.getLocation()).a.STAY;
			}
		}
	}
}

package com.mygdx.game;

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
	GameMap gameMap;
	public static float elapsedTime= 0f;
	boolean move = false;
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
		
	}
	
	public void show() {
	}


	public void render (float delta) {
		elapsedTime+=Gdx.graphics.getDeltaTime()*100;
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
			if(move) {
				
				//movement
				if(movements==5) {
					movements=0;
					board.resetMovement();
					
				}
				if(!board.GetPieceHero(current.getLocation()).isMoved()&&movements<5) {
					board.MovePiece(current.getLocation(), board.findNearestLocation(touch.x,touch.y));
					board.GetPieceHero(current.getLocation()).setMoved(true);
					board.moveEnemies();
					board.validMoves.clear();
					movements++;
					move=false;
				}

			}
		}
		if(board.findNearestEnemy(touch.x,touch.y) instanceof Enemy) {
			enemy=board.findNearestEnemy(touch.x,touch.y);
			board.GetPieceEnemy(enemy.getLocation()).deleteSprite();
			board.enemies.remove(enemy);
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
			
			// TODO Auto-generated constructor stub
		}
		protected void setHero(GameUnit current) {
			this.currentHero=current;
		}
		
		protected void result (Object object) {
			if(object.equals(1)) {
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
				move=true;
			}
			else {}
		}
	}
}

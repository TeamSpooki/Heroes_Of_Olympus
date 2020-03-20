package com.mygdx.screen;

import java.io.FileNotFoundException;
import java.util.Timer;
import java.util.TimerTask;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mygdx.game.*;
import com.mygdx.level.*;
import com.mygdx.world.TiledGameMap;

public class MainGameScreen implements Screen {
	public static final float WIDTH = 1280;
	public static final float HEIGHT = 1024;
	public OrthographicCamera camera;
	private Vector3 touch;
	private Skin skin;
	private OptionDialog options;
	private Stage stage;
	private GameUnit current,oldCurrent,enemy;
	private HeroesOfOlympus game;
	private Timer timer;
	private TiledGameMap gameMap;
	private TiledMapTileLayer layer;
	public static float elapsedTime= 0f;
	private boolean move = false;
	private boolean attack = false;
	private int movements;
	public MainGameScreen (HeroesOfOlympus game, Level level) {
		this.game = game;
		stage = new Stage();
		skin = new Skin(Gdx.files.internal("uiskin.json"));
		camera = new OrthographicCamera();
		camera.setToOrtho(true,WIDTH ,HEIGHT );
		camera.update();
		game.level = level;
		if(level instanceof Level1){
			gameMap = new TiledGameMap("Level1/level1.tmx");
			layer = (TiledMapTileLayer) gameMap.getTiledMap().getLayers().get("waterCollide");
			level.addLayer(layer);
			layer = (TiledMapTileLayer) gameMap.getTiledMap().getLayers().get("bushCollide");
			level.addLayer(layer);
		}else if(level instanceof Level2){
			gameMap = new TiledGameMap("Level2/level2.tmx");
			layer = (TiledMapTileLayer) gameMap.getTiledMap().getLayers().get("wallCollide");
			level.addLayer(layer);
			layer = (TiledMapTileLayer) gameMap.getTiledMap().getLayers().get("pillarCollide");
			level.addLayer(layer);
			layer = (TiledMapTileLayer) gameMap.getTiledMap().getLayers().get("gateCollide");
			level.addLayer(layer);
		}
		else if(level instanceof Level3){
			gameMap = new TiledGameMap("Level3/level3.tmx");
			layer = (TiledMapTileLayer) gameMap.getTiledMap().getLayers().get("coffinCollide");
			level.addLayer(layer);
			layer = (TiledMapTileLayer) gameMap.getTiledMap().getLayers().get("pillarCollide");
			level.addLayer(layer);
			layer = (TiledMapTileLayer) gameMap.getTiledMap().getLayers().get("fountainCollide");
			level.addLayer(layer);
		}
		else if(level instanceof Level4){
			gameMap = new TiledGameMap("Level4/level4.tmx");
		}
		else if(level instanceof Level5){
			gameMap = new TiledGameMap("Level5/level5.tmx");
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
		game.level.draw(game.batch);
		game.batch.end();
		stage.act();
		stage.draw();
		if(Gdx.input.isTouched()) {
			touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(touch);
			if(game.level.findNearestHeroTouch(touch.x,touch.y) instanceof Hero) {
				oldCurrent=current;
				current= game.level.findNearestHero(touch.x,touch.y);
				if(oldCurrent!=current) {
					options.setHero(current);
					options.show(stage);
				}
			}
			if(attack) {
				if(game.level.findNearestEnemyTouch(touch.x,touch.y) instanceof Enemy) {
					enemy= game.level.findNearestEnemyTouch(touch.x,touch.y);
					game.level.getPieceHero(current.getLocation()).setAnimation(Animate.ATTACK);
					timer.schedule(new TimerTask() {
						public void run() {
							game.level.getPieceEnemy(enemy.getLocation()).setHealth(game.level.getPieceEnemy(enemy.getLocation()).getHealth()-game.level.getPieceHero(current.getLocation()).getDamage());
							game.level.validAttacks.clear();
							game.level.act();
							game.level.getPieceHero(current.getLocation()).setAnimation(Animate.STAY);
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
				if(!game.level.getPieceHero(current.getLocation()).isMoved()&&movements<5) {
					game.level.getPieceHero(current.getLocation()).setMoved(true);
					game.level.getPieceHero(current.getLocation()).setAnimation(Animate.WALK);
					timer.schedule(new TimerTask() {

						public void run() {
							if(game.level.findNearestLocation(touch.x,touch.y)!=null) {
								game.level.movePiece(current.getLocation(), game.level.findNearestLocation(touch.x,touch.y));
								game.level.act();
								game.level.validMoves.clear();
								game.level.getPieceHero(current.getLocation()).setMoved(false);
								game.level.getPieceHero(current.getLocation()).setAnimation(Animate.STAY);
								movements++;
							}else {
								game.level.validMoves.clear();
								game.level.getPieceHero(current.getLocation()).setMoved(false);
								game.level.getPieceHero(current.getLocation()).setAnimation(Animate.STAY);
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
				if(game.level instanceof Level1){
					game.setScreen(new Story(game,"scene2.mp4"));
				}else if(game.level instanceof Level2){
					game.setScreen(new Story(game,"scene3.mp4"));
				}
				else if(game.level instanceof Level3){
					game.setScreen(new Story(game,"scene4.mp4"));
				}
				else if(game.level instanceof Level4){
					game.setScreen(new Story(game,"scene5.mp4"));
				}
				else{
					game.setScreen(new Story(game,"scene6.mp4"));
				}

			} catch (FileNotFoundException e) {
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
				for(GameUnit e : game.level.enemies ) {
						if(game.level.getPieceHero(currentHero.getLocation()).isInBounds(e.getX(),e.getY(),game.level.getPieceHero(currentHero.getLocation()).getAttackRange())){
							game.level.validAttacks.add(e.getLocation());
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

				for(int i = 1; i < game.level.getPieceHero(currentHero.getLocation()).getMovementRange(); i++) {
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
				game.level.getPieceHero(current.getLocation()).setAnimation(Animate.STAY);
			}
		}
	}
}

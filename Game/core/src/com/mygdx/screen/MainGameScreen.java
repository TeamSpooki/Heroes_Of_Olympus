package com.mygdx.screen;

import java.io.FileNotFoundException;
import java.util.Timer;
import java.util.TimerTask;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mygdx.game.*;
import com.mygdx.level.*;
import com.mygdx.world.TiledGameMap;

/**
 * Main game screen that handles all the different instances of the game
 */
public class MainGameScreen implements Screen {
	/**
	 * Width of the screen
	 */
	public static final float WIDTH = HeroesOfOlympus.WIDTH;
	/**
	 * Height of the screen
	 */
	public static final float HEIGHT = HeroesOfOlympus.HEIGHT;
	/**
	 * Time variable used for the animation
	 */
	public static float elapsedTime= 0f;

	/**
	 * 3D vector for the representation of the mouse touch
	 */
	private Vector3 touch;
	/**
	 * UI widgets
	 */
	private Skin skin;
	/**
	 * Modal window containing a content table with a button table underneath it.
	 * Used for showing different GameUnit options.
	 */
	private OptionDialog options;
	/**
	 * 2D scene graph
	 */
	private Stage stage;
	/**
	 * Unit of the game used in one session
	 */
	private GameUnit current,oldCurrent,enemy;
	/**
	 * Game variable
	 */
	private HeroesOfOlympus game;
	/**
	 * Variable that executes tasks in a give time frame
	 */
	private Timer timer;
	/**
	 * Represents a tiled map
	 */
	private TiledGameMap gameMap;
	/**
	 * Layer for a TiledMap
	 */
	private TiledMapTileLayer layer;
	/**
	 * Check if user has chosen to move
	 */
	private boolean move = false;
	/**
	 * Check if user has chosen to attack
	 */
	private boolean attack = false;
	/**
	 * Counts the number of movements
	 */
	private int movements;

	private Music music;

	private Sound moving;

	public MainGameScreen (HeroesOfOlympus game, Level level) {
		this.game = game;
		this.moving= Gdx.audio.newSound(Gdx.files.internal("Sounds/Move.wav"));
		stage = new Stage();
		skin = new Skin(Gdx.files.internal("uiskin.json"));
		game.camera.setToOrtho(true);
		game.level = level;
		if(level instanceof Level1){
			music= Gdx.audio.newMusic(Gdx.files.internal("Sounds/Level1.wav"));
			gameMap = new TiledGameMap("Level1/level1.tmx");
			layer = (TiledMapTileLayer) gameMap.getTiledMap().getLayers().get("waterCollide");
			level.addLayer(layer);
		}
		else if(level instanceof Level2){
			music= Gdx.audio.newMusic(Gdx.files.internal("Sounds/Level2.wav"));
			gameMap = new TiledGameMap("Level2/level2.tmx");
			layer = (TiledMapTileLayer) gameMap.getTiledMap().getLayers().get("wallCollide");
			level.addLayer(layer);
			layer = (TiledMapTileLayer) gameMap.getTiledMap().getLayers().get("pillarCollide");
			level.addLayer(layer);
			layer = (TiledMapTileLayer) gameMap.getTiledMap().getLayers().get("gateCollide");
			level.addLayer(layer);
		}
		else if(level instanceof Level3){
			music= Gdx.audio.newMusic(Gdx.files.internal("Sounds/Level3.wav"));
			gameMap = new TiledGameMap("Level3/level3.tmx");
			layer = (TiledMapTileLayer) gameMap.getTiledMap().getLayers().get("coffinDontCollide");
			level.addLayer(layer);
			layer = (TiledMapTileLayer) gameMap.getTiledMap().getLayers().get("pillarCollide");
			level.addLayer(layer);
			layer = (TiledMapTileLayer) gameMap.getTiledMap().getLayers().get("fountainCollide");
			level.addLayer(layer);
		}
		else if(level instanceof Level4){
			music= Gdx.audio.newMusic(Gdx.files.internal("Sounds/Level4.wav"));
			gameMap = new TiledGameMap("Level4/level4.tmx");
			layer = (TiledMapTileLayer) gameMap.getTiledMap().getLayers().get("throneCollide");
			level.addLayer(layer);
			layer = (TiledMapTileLayer) gameMap.getTiledMap().getLayers().get("pillarCollide");
			level.addLayer(layer);
			layer = (TiledMapTileLayer) gameMap.getTiledMap().getLayers().get("wallCollide");
			level.addLayer(layer);
		}
		else if(level instanceof Level5){
			music= Gdx.audio.newMusic(Gdx.files.internal("Sounds/Level5.wav"));
			gameMap = new TiledGameMap("Level5/level5.tmx");
		}
		music.setLooping(true);
		music.setVolume(0.1f);
		music.play();
		touch=new Vector3();
		movements=0;
		options = new OptionDialog("Options",skin);
		options.button("Attack", 1);
		options.button("Move", 2);
		options.button("Stay",3);
		options.setVisible(false);
		Gdx.input.setInputProcessor(stage);
		timer = new Timer();
	}

	public void show() {}

	public void render (float delta) {
		elapsedTime+=Gdx.graphics.getDeltaTime();
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gameMap.render(game.camera);
		game.batch.setProjectionMatrix(game.camera.combined);
		game.batch.begin();
		game.level.draw(game.batch);
		game.batch.end();
		stage.act();
		stage.draw();
		if(Gdx.input.isTouched()) {
			touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			game.camera.unproject(touch);
			System.out.println(options.isVisible());
			if(!options.isVisible()){
				if(game.level.findNearestHeroTouch(touch.x,touch.y) instanceof Hero) {
					oldCurrent=current;
					current= game.level.findNearestHero(touch.x,touch.y);
					if(oldCurrent!=current) {


						options.setHero(current);
						options.show(stage);
						options.setVisible(true);
					}else if(game.level.getHeroesSize()==1){

						options.setHero(current);
						options.show(stage);
						options.setVisible(true);
					}
				}
			}
			if(move){
				if(movements>=game.level.getHeroesSize()) {
					movements=0;
					game.level.resetMovement();
				}
				if(!game.level.getPieceHero(current.getLocation()).isMoved()&&movements<=game.level.getHeroesSize()) {
					game.level.getPieceHero(current.getLocation()).setMoved(true);
					game.level.getPieceHero(current.getLocation()).setAnimation(Animate.WALK);
					timer.schedule(new TimerTask() {

						public void run() {
							if(game.level.findNearestLocation(touch.x,touch.y)!=null) {
								moving.play(1.0f);
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
					}, 500);
					move=false;
				}
			}
			if(attack) {
				if(!game.level.validAttacks.isEmpty()) {
					if(game.level.findNearestEnemyTouch(touch.x,touch.y) instanceof Enemy) {
						enemy= game.level.findNearestEnemyTouch(touch.x,touch.y);
						if(!enemy.isDead()&&game.level.validAttacks.contains(enemy.getLocation())){
							game.level.getPieceHero(current.getLocation()).setAnimation(Animate.ATTACK);
							timer.schedule(new TimerTask() {
								public void run() {
									game.level.getPieceHero(current.getLocation()).playSound();
									game.level.getPieceEnemy(enemy.getLocation()).setHealth(game.level.getPieceEnemy(enemy.getLocation()).getHealth()-game.level.getPieceHero(current.getLocation()).getDamage());
									game.level.validAttacks.clear();
									game.level.act();
									game.level.getPieceHero(current.getLocation()).setAnimation(Animate.STAY);
								}
							}, 500);
						}else{
							game.level.validAttacks.clear();
							game.level.act();
							game.level.getPieceHero(current.getLocation()).setAnimation(Animate.STAY);
							current=null;
						}
					}
				}
			}
		}
		if(Gdx.input.isKeyJustPressed(Input.Keys.I)){
			Dialog dialog = new Dialog("Information", skin) {
				public void result(Object obj) {
					System.out.println("result "+obj);
				}
			};
			dialog.text(""+game.level.toString());
			dialog.button("OK", true); //sends "true" as the result
			dialog.show(stage);
		}
		if(Gdx.input.isKeyJustPressed(Input.Keys.P)){
			game.level.devMode();
		}
		if(game.level.enemiesDead()) {
			game.level.removeAll();
			this.dispose();
			try {
				if(game.level instanceof Level1){
					game.setScreen(new Story(game,"scene2.ogv"));
				}else if(game.level instanceof Level2){
					game.setScreen(new Story(game,"scene3.ogv"));
				}
				else if(game.level instanceof Level3){
					game.setScreen(new Story(game,"scene4.ogv"));
				}
				else if(game.level instanceof Level4){
					game.setScreen(new Story(game,"scene5.ogv"));
				}
				else{
					game.setScreen(new Story(game,"scene6.ogv"));
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		else if(game.level.heroesDead()){
			game.level.removeAll();
			this.dispose();
			game.setScreen(new GameOverScreen(game));
		}
	}

	public void dispose () {
		game.camera.setToOrtho(false);
		game.batch.setProjectionMatrix(game.camera.combined);
		music.dispose();
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
				for(GameUnit enemy : game.level.enemies ) {
						if(game.level.getPieceHero(currentHero.getLocation()).isInBounds(enemy.getX(),enemy.getY(),game.level.getPieceHero(currentHero.getLocation()).getAttackRange())){
							game.level.validAttacks.add(enemy.getLocation());
						}
				}
				if(!game.level.validAttacks.isEmpty()) {
					attack=true;
				}else {
					game.level.act();
				}
				this.setVisible(false);
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
				this.setVisible(false);
			}
			else {
				game.level.getPieceHero(current.getLocation()).setAnimation(Animate.STAY);
				game.level.act();
				this.setVisible(false);
			}
		}
	}
}
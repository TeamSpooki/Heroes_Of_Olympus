package com.heroesofolympus.game;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.DelayAction;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.heroesofolympus.collision.HerosCollision;
import com.heroesofolympus.enemy.Robot;
import com.heroesofolympus.weapon.MoveWeapon;

public class Main extends Game implements Screen {

	// Set screen size
	public static final int WIDTH = 1050;
	public static final int HEIGHT = 800;

	volatile int counter = 0;

	// this variable switches on the attack
	volatile boolean check = false;
	volatile boolean achilleswitch = false;
	volatile boolean herculesswitch = false;
	volatile boolean hypolytaswitch = false;
	volatile boolean thesiusswitch = false;
	float helencurrentstartTime =0;
	private Hero titan;
	private float currentstartTime =0;
	private float achillecurrentstartTime =0;
	// this variable define when to load the titan
	volatile int checkaction = 0;

	String[] enemystr = {"skeleton", "skele_1","skele_2", "skele_3", "skele_4"};
	int enemycounter = 0;
	SpriteBatch batch;
	Texture achille_img, helen_img, hercules_img, hypolyta_img, thesius_img, skeleton_img, skeletonArmy1, skeletonArmy2,
	skeletonArmy3, skeletonArmy4, skeletonArmy5;
	Sprite achilleStartPosition, helenStartPosition, herculesStartPosition, hypolytaStartPosition, thesiusStartPosition,
	skeletonStartPosition, skeleton1StartPosition, skeleton2StartPosition, skeleton3StartPosition,
	skeleton4StartPosition, skeleton5StartPosition;

	/* GameScreen game_screen; */
	GameMap gameMap;

	// general Sprite handle
	Sprite sprite;
	Hero achille;
	Hero conthypo;
	SpriteBatch spriteBatch;
	
	////collision handler/////////////
	HerosCollision collision;
	MoveWeapon mweapon;
	Robot robot;

	// A variable for tracking elapsed time for the animation
	float stateTime, tracker;

	// Check if selected dialog option is shown
	boolean optionsShow = false;

	OrthographicCamera camera;
	// Hero achille;
	Hero helen;
	Hero hercules;
	Hero hypolyta;
	Hero thesius;
	Enemy skeleton;
	Enemy skele_1, skele_2, skele_3, skele_4, skele_5;
	Vector3 touch;
	Dialog achilleOptions, achilleMove, helenOptions, helenMove, herculesOptions, herculesMove, hypolytaOptions,
	hypolytaMoves, thesiusOptions, thesiusMove;
	Stage stage;
	Skin skin;
	Viewport viewport;

	// An ArrayList used to store/check whether the player has had their turn or not
	ArrayList<String> playerTurn;
	private TextureRegion holder;
	private Achille achilleAdvan;
	private Helen helenAdvan;
	private Hercules herculesAdvan;
	private Hypolyta hypolytaAdvan;
	private Thesius thesiusAdvan;
	private Weapons thesiusweapon;
	private Weapons weapon;
	private Weapons weaponAdvan;
	private float[] location = new float[2];
	private volatile boolean heartswitch2 = false;
	private Hero generalHero;
	private Texture general_img;
	private Sprite generalStartPosition;
	private Texture titan_img;
	private Sprite titanStartPosition;
	Achille titanAdva;
	private int escape =1;
	private volatile boolean startingPoint = true;
	Enemy currentenemy;
	private volatile boolean activator = false;
	@Override
	public void create() {

		batch = new SpriteBatch();
		
		collision = new HerosCollision();
		
		mweapon = new MoveWeapon();

		sprite = new Sprite();
		generalHero = new Hero(sprite, null);
		achilleAdvan = new Achille(sprite, null);
		helenAdvan = new Helen(sprite, null);
		herculesAdvan = new Hercules(sprite, null);
		hypolytaAdvan = new Hypolyta(sprite, null);
		thesiusAdvan = new Thesius(sprite, null);
		robot = new Robot(sprite, null);
	    currentenemy =new Enemy(sprite, null);
		new Animetor();
		weaponAdvan = new Weapons(sprite, null);
		new Sprite();
		titanAdva = new Achille(sprite, null);
		// Animation animation = new Animation();

		camera = new OrthographicCamera();
		camera.setToOrtho(true, 1280, 1080);
		touch = new Vector3();
		stage = new Stage();
		skin = new Skin(Gdx.files.internal("default/skin/uiskin.json"));
		playerTurn = new ArrayList<>();

		// initializing animation variables.
		spriteBatch = new SpriteBatch();
		stateTime = 0f;

		// Achille Dialog Menu Box
		achilleOptions = new Dialog("Achille", skin) {
			protected void result(Object object) {
				if (object.equals(1)) {
					// Set dialog state to false when checked on
					System.out.println("this is swiches on the attack");
					achilleswitch = true;
					optionsShow = false;
					checkaction++;
				} else if (object.equals(2)) {
					// New Dialog box to enable specific movement for player
					achilleMove = new Dialog("Move", skin) {
						protected void result(Object object) {
							// Move Up
							if (object.equals(1) && collision.achilleUpward()) {
								achille.setPosition(achille.getX() + 0, achille.getY() - 64);
							}
							// Move Down
							else if (object.equals(2) && collision.achilleDownward()) {
								achille.setPosition(achille.getX() + 0, achille.getY() + 64);

							}
							// Move Left
							else if (object.equals(3) && collision.achilleBackward()) {
								achille.setPosition(achille.getX() - 64, achille.getY() + 0);
							}
							// Move Right
							else if(collision.achilleForward() && object.equals(4)) {
								achille.setPosition(achille.getX() + 64, achille.getY() + 0);
							}
							optionsShow = false;
							robot.makeAmove(packEnemies(), 5);
						}
					}

					.button("Up", 1).button("Down", 2).button("Left", 3).button("Right", 4).show(stage);

					// System.out.println("Button Pressed");

				} else {
					optionsShow = false;
				}
			};
		};

		achilleOptions.button("Attack", 1);
		achilleOptions.button("Move", 2);
		achilleOptions.button("Stay", 3);

		// Helen Dialog Menu Box
		helenOptions = new Dialog("Helen", skin) {
			protected void result(Object object) {
				if (object.equals(1)) {
					checkaction++;
					check = true;

					optionsShow = false;
				} else if (object.equals(2)) {
					helenMove = new Dialog("Move", skin) {

						protected void result(Object object) {
							// Move Up
							if (object.equals(1) && collision.helenUpward()) {
								helen.setPosition(helen.getX() + 0, helen.getY() - 64);
							} else if (object.equals(2) && collision.helenDownward()) {
								helen.setPosition(helen.getX() + 0, helen.getY() + 64);

							} else if (object.equals(3) && collision.helenBackward() ) {
								System.out.println("Hellen id Healing");
								 helen.setPosition(helen.getX()-64, helen.getY()+0);
								render(stateTime / (float) Math.acos(60));
							} else if(collision.helenForward() && object.equals(4)) {
								helen.setPosition(helen.getX() + 64, helen.getY() + 0);
							}
							optionsShow = false;
							robot.makeAmove(packEnemies(), 5);
							
						}
					}

					.button("Up", 1).button("Down", 2).button("Left", 3).button("Right", 4).show(stage);
					// System.out.println("Button Pressed");

				} else {
					optionsShow = false;
					activator = true;
				}

			};
		};

		helenOptions.button("Attack", 1);
		helenOptions.button("Move", 2);
		helenOptions.button("Stay", 3);

		// Hercules Dialog Menu Box
		herculesOptions = new Dialog("Hercules", skin) {
			protected void result(Object object) {
				if (object.equals(1)) {
					optionsShow = false;
					herculesswitch = true;
					checkaction++;
				} else if (object.equals(2)) {
					herculesMove = new Dialog("Move", skin) {
						protected void result(Object object) {
							// Move Up
							if (object.equals(1) && collision.herculesUpward()) {
								hercules.setPosition(hercules.getX() + 0, hercules.getY() - 64);
							} else if (object.equals(2) && collision.herculesDownward()) {
								hercules.setPosition(hercules.getX() + 0, hercules.getY() + 64);

							} else if (object.equals(3) && collision.herculesBackward()) {
								hercules.setPosition(hercules.getX() - 64, hercules.getY() + 0);
							} else if(object.equals(4) && collision.herculesForward()) {
								hercules.setPosition(hercules.getX() + 64, hercules.getY() + 0);
							}
							optionsShow = false;
							robot.makeAmove(packEnemies(), 5);
						}
					}

					.button("Up", 1).button("Down", 2).button("Left", 3).button("Right", 4).show(stage);
					System.out.println("Button Pressed");

				} else {
					optionsShow = false;
				}
			};
		};

		herculesOptions.button("Attack", 1);
		herculesOptions.button("Move", 2);
		herculesOptions.button("Stay", 3);

		// Hypolyta Dialog Menu Box
		hypolytaOptions = new Dialog("Hypolyta", skin) {
			protected void result(Object object) {
				if (object.equals(1) ) {
					// Set dialog state to false when checked on
					hypolytaswitch = true;
					optionsShow = false;
					checkaction++;

				} else if (object.equals(2) ) {
					// New Dialog box to enable specific movement for player
					hypolytaMoves = new Dialog("Move", skin) {
						protected void result(Object object) {
							// Move Up
							if (object.equals(1) && collision.hypolytaUpward()  ) {
								hypolyta.setPosition(hypolyta.getX() + 0, hypolyta.getY() - 64);
							}
							// Move Down
							else if (object.equals(2) && collision.hypolytaDownward() ) {
								hypolyta.setPosition(hypolyta.getX() + 0, hypolyta.getY() + 64);

							}
							// Move Left
							else if (object.equals(3) && collision.hypolytaBackward()) {
								hypolyta.setPosition(hypolyta.getX() - 64, hypolyta.getY() + 0);
							}
							// Move Right
							else if ( object.equals(4) && collision.hypolytaForward()) {
								hypolyta.setPosition(hypolyta.getX() + 64, hypolyta.getY() + 0);
							}
							optionsShow = false;
							robot.makeAmove(packEnemies(), 5);
						}
					}

					.button("Up", 1).button("Down", 2).button("Left", 3).button("Right", 4).show(stage);

					System.out.println("Button Pressed");

				} else {
					optionsShow = false;
				}
			};
		};

		hypolytaOptions.button("Attack", 1);
		hypolytaOptions.button("Move", 2);
		hypolytaOptions.button("Stay", 3);

		// thesius Dialog Menu Box
		thesiusOptions = new Dialog("Thesius", skin) {
			protected void result(Object object) {
				if (object.equals(1)) {
					// Set dialog state to false when checked on
					thesiusswitch = true;
					optionsShow = false;
					checkaction++;
				} else if (object.equals(2)) {
					activator = true;
					// New Dialog box to enable specific movement for player
					thesiusMove = new Dialog("Move", skin) {
						protected void result(Object object) {
							// Move Up
							if (object.equals(1) && collision.thesiusUpward()) {
								thesius.setPosition(thesius.getX() + 0, thesius.getY() - 64);
							}
							// Move Down
							else if (object.equals(2) && collision.thesiusDownward() ) {
								thesius.setPosition(thesius.getX() + 0, thesius.getY() + 64);

							}
							// Move Left
							else if (object.equals(3) && collision.thesiusBackward()) {
								thesius.setPosition(thesius.getX() - 64, thesius.getY() + 0);
							}
							// Move Right
							else if(object.equals(4) && collision.thesiusUpward()) {
								thesius.setPosition(thesius.getX() + 64, thesius.getY() + 0);
							}
							optionsShow = false;
							robot.makeAmove(packEnemies(), 5);
						}
					}

					.button("Up", 1).button("Down", 2).button("Left", 3).button("Right", 4).show(stage);

					System.out.println("Button Pressed");

				} else {
					optionsShow = false;

				}
			};
		};

		thesiusOptions.button("Attack", 1);
		thesiusOptions.button("Move", 2);
		thesiusOptions.button("Stay", 3);

		gameMap = new TiledGameMap();
		Gdx.input.setInputProcessor(stage);

		/******************** Heroes *****************************/

		// Reading achille image file
		achille_img = new Texture(Gdx.files.internal("Achilles.png"));

		// Get the specific sprite from the sprite sheet - pass in achilles image,
		// read from top left of sprite sheet, X-axis = 0 and from the y-axis we add 64
		// each time to get the sprite. 64 by 64 is done to only get the width & height
		// of that single sprite.

		holder = achilleAdvan.getAchilleFrames()[0];
		achille_img = holder.getTexture();
		achilleStartPosition = new Sprite(achille_img, 0, 64 , 64, 64);
		achille = new Hero(achilleStartPosition, null);
		achilleStartPosition.flip(true, true);
		achille.setPosition(256, 128);

		// Define achille sprite
		// achille = new Hero(achilleStartPosition, null);
		// Set position on the Tile Map - Add 64 to X-axis and Y-axis to place on
		// specific tile
		// achille.setPosition(256, 128);

		holder = helenAdvan.getAchilleFrames()[0];
		helen_img = holder.getTexture(); // new Texture(Gdx.files.internal("Helen.png"));
		// helenStartPosition = new Sprite(helen_img,0,128,64,64);
		helenStartPosition = new Sprite(helen_img, 0, 192, 64, 64);
		// helenStartPosition.setTexture( achille.getAchilleFrames()[0].getTexture());
		helenStartPosition.flip(false, true);
		helen = new Hero(helenStartPosition, null);
		helen.setPosition(128, 64);

		// hercules_img= new Texture(Gdx.files.internal("Hercules.png")); done
		holder = herculesAdvan.getAchilleFrames()[0];
		hercules_img = holder.getTexture();
		herculesStartPosition = new Sprite(hercules_img, 128, 192 , 64, 64);
		herculesStartPosition.flip(false, true);
		hercules = new Hero(herculesStartPosition, null);
		hercules.setPosition(256, 320);

		holder = hypolytaAdvan.getAchilleFrames()[0]; // done
		hypolyta_img = holder.getTexture();
		// hypolyta_img= new Texture(Gdx.files.internal("Hypolyta.png"));
		hypolytaStartPosition = new Sprite(hypolyta_img, 0, 192, 64, 64);
		hypolytaStartPosition.flip(false, true);
		hypolyta = new Hero(hypolytaStartPosition, null);
		hypolyta.setPosition(128, 320);
		conthypo = hypolyta;

		holder = thesiusAdvan.getAchilleFrames()[0];
		thesius_img = holder.getTexture();
		// thesius_img= new Texture(Gdx.files.internal("Thesius.png"));
		thesiusStartPosition = new Sprite(thesius_img, 0, 192, 64, 64);
		thesiusStartPosition.flip(false, true);
		thesius = new Hero(thesiusStartPosition, null);
		thesius.setPosition(128, 512);

		holder = thesiusAdvan.getAchilleFrames()[0];
		general_img = holder.getTexture();
		// thesius_img= new Texture(Gdx.files.internal("Thesius.png"));
		generalStartPosition = new Sprite(general_img, 64, 256 , 64, 64);
		generalStartPosition.flip(false, true);
		generalHero = new Hero(generalStartPosition, null);
		generalHero.setPosition(128, 512);

		///////////////// titan curve ///////////////////////

		holder = titanAdva.getAchilleFrames()[0];
		titan_img = holder.getTexture();
		titanStartPosition = new Sprite((new Hero(sprite,null)).getblunkHeartRegion());
		titanStartPosition.flip(false, true);
		titan = new Hero(titanStartPosition, null);
		 titan.setPosition(500, 400);

		/*
		 * this is a dammy weapon holder = weaponAdvan.getWeaponFrames()[0]; weapon_img=
		 * holder.getTexture(); //thesius_img= new
		 * Texture(Gdx.files.internal("Thesius.png")); weaponStartPosition = new
		 * Sprite(weapon_img,0,0,160,400); weaponStartPosition.flip(false, true);
		 * weaponAdvan = new Weapons(weaponStartPosition, null);
		 * weaponAdvan.setPosition(128, 512);
		 */

		/********************** Enemies *********************/
		skeleton_img = new Texture(Gdx.files.internal("skeletonSpear.png"));
		skeletonStartPosition = new Sprite(skeleton_img, 0, 320, 64, 64);
		skeletonStartPosition.flip(false, true);
		skeleton = new Enemy(skeletonStartPosition, null);
		skeleton.setPosition(832, 320);

		skeletonArmy1 = new Texture(Gdx.files.internal("skeletonSpear.png"));
		skeleton1StartPosition = new Sprite(skeletonArmy1, 64, 320, 64, 64);
		skeleton1StartPosition.flip(false, true);
		skele_1 = new Enemy(skeleton1StartPosition, null);
		skele_1.setPosition(832, 384);

		skeletonArmy2 = new Texture(Gdx.files.internal("skeletonSpear.png"));
		skeleton2StartPosition = new Sprite(skeletonArmy2, 128, 320, 64, 64);
		skeleton2StartPosition.flip(false, true);
		skele_2 = new Enemy(skeleton2StartPosition, null);
		skele_2.setPosition(832, 448);

		skeletonArmy3 = new Texture(Gdx.files.internal("skeletonSpear.png"));
		skeleton3StartPosition = new Sprite(skeletonArmy3, 192, 320, 64, 64);
		skeleton3StartPosition.flip(false, true);
		skele_3 = new Enemy(skeleton3StartPosition, null);
		skele_3.setPosition(832, 512);

		skeletonArmy4 = new Texture(Gdx.files.internal("skeletonSpear.png"));
		skeleton4StartPosition = new Sprite(skeletonArmy4, 256, 320, 64, 64);
		skeleton4StartPosition.flip(false, true);
		skele_4 = new Enemy(skeleton4StartPosition, null);
		skele_4.setPosition(832, 576);

	}

	@Override
	public void render() {
		/* update(); */

		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		stateTime += Gdx.graphics.getDeltaTime(); // Accumulate elapsed animation time

		batch.setProjectionMatrix(camera.combined);
		gameMap.render(camera);
		camera.update();

		batch.begin();

		// render Achille
        
		achille.draw(batch);
		helen.draw(batch);
		hercules.draw(batch);
		hypolyta.draw(batch);
		thesius.draw(batch);
		skeleton.draw(batch);
		skele_1.draw(batch);
		skele_2.draw(batch);
		skele_3.draw(batch);
		skele_4.draw(batch);
		titan.draw(batch);
		
		if (check == true && testrow(helen)) {
			Enemy enemy=render(Gdx.graphics.getDeltaTime(), batch, new Helen(sprite, null));
			//mweapon.drawWeapon(batch, Gdx.graphics.getDeltaTime(), helen);
			enemyFalls(enemy).draw(batch);
		}
		//		 
		if (achilleswitch == true && testrow(achille)) {
			Enemy enemy = achillerender(Gdx.graphics.getDeltaTime(),  batch, new Achille(sprite, null));
			enemyFalls(enemy).draw(batch);

		}
		//	        //herculesswitch
		if (herculesswitch == true && testrow(hercules) ) {
			Enemy enemy= herculesrender(Gdx.graphics.getDeltaTime(), batch, new Hercules(sprite, null));
			enemyFalls(enemy).draw(batch);
		}
		// hypolytasswitch
		if (hypolytaswitch == true  && testrow(hypolyta)) {
			Enemy enemy=hypolytarender(Gdx.graphics.getDeltaTime(), batch, new Hypolyta(sprite, null));
			enemyFalls(enemy).draw(batch);
		}
		// theseus switched on.
		if (thesiusswitch == true  && testrow(thesius)) {
			Enemy enemy=thesiusrender(Gdx.graphics.getDeltaTime(), batch, new Thesius(sprite, null));
			enemyFalls(enemy).draw(batch);
		}

		///// check if all the heros have attacked and all the enemies dead
		if (checkaction == 5 && escape == 1 ) {
			//System.out.println("am in the loop");
			//loadTitan().draw(batch);
			 //Sprite trick = new Hero(sprite, null).titanSprite();
			//titan= new Hero(trick, null);
			//titan.setPosition(500, 400);
						
		}
		
		if(checkaction == 8) {
			escape =2;
		}
		
		// ///////////// enemy move is activated here////////////
		if (activator == true) {
			System.out.println("Number of heroes in target: ");
			robot.passHero(packHeros());
			robot.passEnemy(packEnemies());
			robot.passBatch(batch);
			//robot.strike();
			activator = false;
			
		}
		//mweapon.drawWeapon(batch, Gdx.graphics.getDeltaTime(), helen);

		batch.end();

		stage.act();
		stage.draw();

		// When the player/hero is clicked on
		if (Gdx.input.isTouched()) {

			System.out.println("Hero has been touched");
			touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(touch);

			// This will check if the current options/dialog box are displayed or not and if
			// the ArrayList contains/counted 'Achille'
			// Get the X and Y of the sprite when clicked on
			if (!optionsShow && !playerTurn.contains("achille") && touch.x >= achille.getX()
					&& touch.x <= achille.getX() + 64 && touch.y >= achille.getY() && touch.y <= achille.getY() + 64) {

				// Display Main dialog box
				achilleOptions.show(stage);

				// If 'Achille' has not yet had a turn, then add it to the count in the
				// ArrayList
				playerTurn.add("achille");

				// If no other hero/player options are currently shown it will display the
				// dialog box
				optionsShow = true;

			}
			if (!optionsShow && !playerTurn.contains("helen") && touch.x >= helen.getX() && touch.x <= helen.getX() + 64
					&& touch.y >= helen.getY() && touch.y <= helen.getY() + 64) {

				helenOptions.show(stage);
				playerTurn.add("helen");
				optionsShow = true;
			}
			if (!optionsShow && !playerTurn.contains("hercules") && touch.x >= hercules.getX()
					&& touch.x <= hercules.getX() + 64 && touch.y >= hercules.getY()
					&& touch.y <= hercules.getY() + 64) {

				herculesOptions.show(stage);
				playerTurn.add("hercules");
				optionsShow = true;
			}
			if (!optionsShow && !playerTurn.contains("hypolyta") && touch.x >= hypolyta.getX()
					&& touch.x <= hypolyta.getX() + 64 && touch.y >= hypolyta.getY()
					&& touch.y <= hypolyta.getY() + 64) {

				hypolytaOptions.show(stage);
				playerTurn.add("hypolyta");
				optionsShow = true;
			}
			if (!optionsShow && !playerTurn.contains("thesius") && touch.x >= thesius.getX()
					&& touch.x <= thesius.getX() + 64 && touch.y >= thesius.getY() && touch.y <= thesius.getY() + 64) {

				thesiusOptions.show(stage);
				playerTurn.add("thesius");
				optionsShow = true;
			}

			// Here we check or alternatively set the count/size of the ArrayList to be 5
			// because we have 5 heroes.
			if (playerTurn.size() == 5) {
				playerTurn = new ArrayList<>();
			}

		}
		
		updataLocation(helen, achille, hercules, hypolyta, thesius);
		 

	}

	/*********************
	 * CHECK WHETHER TWO SPRITES ARE ON THE SAME TILE
	 *********************/
	/*
	 * public boolean hitTest(Hero achille, Hero helen) {
	 * 
	 * if(achille.getX() < helen.getX() + helen.getWidth() && achille.getX() +
	 * achille.getWidth() > helen.getWidth() && achille.getY() < helen.getY() +
	 * helen.getHeight() && achille.getY() + achille.getHeight() > helen.getY()) {
	 * return true; }
	 * 
	 * return false; }
	 * 
	 * public void update() {
	 * 
	 * if(hitTest(achille, helen)) { spriteShow = false; } }
	 */

	@Override
	public void dispose() {
		batch.dispose();
		stage.dispose();

		spriteBatch.dispose();

	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	///// helen rendere
	public Enemy render(float delta, SpriteBatch sb, Helen helen2) {
		Hero cont = helen;
		//Enemy enemy =new Enemy(sprite, null);
		boolean test = false;

		helencurrentstartTime += delta;
		//if( helencurrentstartTime < 0.8) {  
			 Sprite trick = new Sprite((new Hero(sprite,null)).getblunkHeartRegion());
			 helen= new Hero(trick, null);
			 helen.setPosition(cont.getX(), cont.getY());
			TextureRegion tregion = helen2.animatehyplyta().getKeyFrame(helencurrentstartTime, false);
			TextureRegion herolife = helen2.animateHalftoFull().getKeyFrame(helencurrentstartTime, false);
			TextureRegion enemylife = helen2.animateFulltoHalf().getKeyFrame(helencurrentstartTime, false);
			for(Enemy find : packEnemies()) {
            	enemycounter++;
        	 if(find.getY() == cont.getY()) {
        		 System.out.println("the counter is: "+ enemycounter);
        		 affectedenemy = enemycounter;
        		 this.currentenemy = find;
        		 test = true;
        		 enemycounter =0;
        		 break;
        		 //return enemy;
        		 //skeleton = enemy1;
        	 }//else {enemy =skele_1;}
         }
         if( test) {



		sb.draw(tregion, cont.getX(), cont.getY());
		sb.draw(enemylife, currentenemy.getX() -32, currentenemy.getY() -32);
         
	//}
		 if(helen2.animatehyplyta().isAnimationFinished(helencurrentstartTime)) {
			 check = false;
			 Sprite trick2 = (new Helen(sprite, null)).NormalLook();
			 helen= new Hero(trick2, null);
			 helen.setPosition(cont.getX(), cont.getY());
			  System.out.println("new counter value"+ affectedenemy);
		     if(affectedenemy == 1) {
		    	 	Enemy enemy = skeleton;
											 
					skeleton = new Enemy(enemyFalls( skeleton), null);
					skeleton.setPosition(enemy.getX()  , enemy.getY());
						
		     }
		     
		     if(affectedenemy == 2) {
		    	 	Enemy enemy = skele_1;
											 
		    	 	skele_1 = new Enemy(enemyFalls( skele_1), null);
		    	 	skele_1.setPosition(enemy.getX()  , enemy.getY());
						
		     }
		     
		     if(affectedenemy == 3) {
		    	 	Enemy enemy = skele_2;
											 
		    	 	skele_2 = new Enemy(enemyFalls( skele_2), null);
		    	 	skele_2.setPosition(enemy.getX()  , enemy.getY());
						
		     }
		     
		     if(affectedenemy == 4) {
		    	 	Enemy enemy = skele_3;
											 
		    	 	skele_3 = new Enemy(enemyFalls( skele_3), null);
		    	 	skele_3.setPosition(enemy.getX()  , enemy.getY());
						
		     }
		     
		     if(affectedenemy == 5) {
		    	 	Enemy enemy = skele_4;
											 
		    	 	skele_4 = new Enemy(enemyFalls( skele_4), null);
		    	 	skele_4.setPosition(enemy.getX()  , enemy.getY());
						
		     }
			 			 
			 
             }
         }
		 return currentenemy;
	}

	// achille's animation rendere. its switch is "achilleswitch"
	float val = 0;
	private float herculescurrentstartTime=0;
	private float thesiuscurrentstartTime=0;
	private int affectedenemy =0;


	public Enemy achillerender(float delta, SpriteBatch sb, Achille achille2) {
		Hero cont = achille;
		boolean test = false;
		achillecurrentstartTime  += delta;
		 Sprite trick = new Sprite((new Hero(sprite,null)).getblunkHeartRegion());
		 achille= new Hero(trick, null);
		 achille.setPosition(cont.getX(), cont.getY());

			TextureRegion tregion = achille2.animatehyplyta().getKeyFrame(achillecurrentstartTime, false);
			TextureRegion enemylife = achille2.animateHalftoEmpty().getKeyFrame(achillecurrentstartTime, false);

//			

			for(Enemy find : packEnemies()) {
            	enemycounter++;
        	 if(find.getY() == cont.getY()) {
        		 System.out.println("the counter is: "+ enemycounter);
        		 affectedenemy = enemycounter;
        		 this.currentenemy = find;
        		 test = true;
        		 enemycounter =0;
        		 break;
        		 //return enemy;
        		 //skeleton = enemy1;
        	 }//else {enemy =skele_1;}
         }
         if( test) {



		sb.draw(tregion, cont.getX(), cont.getY());
		sb.draw(enemylife, currentenemy.getX() -32, currentenemy.getY() -32);
         
	//}
		 if(achille2.animatehyplyta().isAnimationFinished(achillecurrentstartTime)) {
			 achilleswitch = false;
			 Sprite trick2 = (new Achille(sprite, null)).NormalLook();
			 achille= new Hero(trick2, null);
			 achille.setPosition(cont.getX(), cont.getY());
			  System.out.println("newcounter value"+ affectedenemy);
		     if(affectedenemy == 1) {
		    	 	Enemy enemy = skeleton;
											 
					skeleton = new Enemy(enemyFalls( skeleton), null);
					skeleton.setPosition(enemy.getX()  , enemy.getY());
						
		     }
		     
		     if(affectedenemy == 2) {
		    	 	Enemy enemy = skele_1;
											 
		    	 	skele_1 = new Enemy(enemyFalls( skele_1), null);
		    	 	skele_1.setPosition(enemy.getX()  , enemy.getY());
						
		     }
		     
		     if(affectedenemy == 3) {
		    	 	Enemy enemy = skele_2;
											 
		    	 	System.out.println("count is :" + affectedenemy );
		    	 	skele_2 = new Enemy(enemyFalls( skele_2), null);
		    	 	skele_2.setPosition(enemy.getX()  , enemy.getY());
						
		     }
		     
		     if(affectedenemy == 4) {
		    	 	Enemy enemy = skele_3;
		    	 	System.out.println("count is :" + affectedenemy );
											 
		    	 	skele_3 = new Enemy(enemyFalls( skele_3), null);
		    	 	skele_3.setPosition(enemy.getX()  , enemy.getY());
						
		     }
		     
		     if(affectedenemy == 5) {
		    	 	Enemy enemy = skele_4;
		    	 	System.out.println("count is :" + affectedenemy );
											 
		    	 	skele_4 = new Enemy(enemyFalls( skele_4), null);
		    	 	skele_4.setPosition(enemy.getX()  , enemy.getY());
						
		     }
			 			 
			 
             }
         }
		 return currentenemy;

	}

	// hercules's animation renderer. its switch is "herculesswitch"
	
	public Enemy herculesrender(float delta, SpriteBatch sb, Hercules hercules2) {
		Hero cont = hercules;
		
		boolean test = false;
		//startingPoint =false;
		
		herculescurrentstartTime += delta;

		Sprite trick = new Sprite((new Hercules(sprite,null)).getblunkHeartRegion());
		hercules= new Hero(trick, null);
		hercules.setPosition(cont.getX(), cont.getY());
		
			TextureRegion tregion = hercules2.animatehyplyta().getKeyFrame(herculescurrentstartTime, false);
			TextureRegion enemylife = hercules2.animateFulltoHalf().getKeyFrame(herculescurrentstartTime, false);
			
			 for(Enemy find : packEnemies()) {
	            	enemycounter++;
            	 if(find.getY() == cont.getY()) {
            		 System.out.println("the counter is: "+ enemycounter);
            		 affectedenemy = enemycounter;
            		 this.currentenemy = find;
            		 test = true;
            		 enemycounter =0;
            		 break;
            		 //return enemy;
            		 //skeleton = enemy1;
            	 }//else {enemy =skele_1;}
             }
             if( test) {



			sb.draw(tregion, cont.getX(), cont.getY());
			sb.draw(enemylife, currentenemy.getX() -32, currentenemy.getY() -32);
             
		//}
			 if(hercules2.animatehyplyta().isAnimationFinished(herculescurrentstartTime)) {
				 herculesswitch = false;
				 Sprite trick2 = (new Hercules(sprite, null)).NormalLook();
				 hercules= new Hero(trick2, null);
				 hercules.setPosition(cont.getX(), cont.getY());
				  System.out.println("newcounter value"+ affectedenemy);
			     if(affectedenemy == 1) {
			    	// System.out.println("count is :" + affectedenemy );
			    	 System.out.println("count is :" + affectedenemy );
			    	 	Enemy enemy = skeleton;
												 
						skeleton = new Enemy(enemyFalls( skeleton), null);
						skeleton.setPosition(enemy.getX()  , enemy.getY());
							
			     }
			     
			     if(affectedenemy == 2) {
			    	 System.out.println("count is :" + affectedenemy );
			    	 	Enemy enemy = skele_1;
												 
			    	 	skele_1 = new Enemy(enemyFalls( skele_1), null);
			    	 	skele_1.setPosition(enemy.getX()  , enemy.getY());
							
			     }
			     
			     if(affectedenemy == 3) {
			    	 System.out.println("count is :" + affectedenemy );
			    	 	Enemy enemy = skele_2;
												 
			    	 	skele_2 = new Enemy(enemyFalls( skele_2), null);
			    	 	skele_2.setPosition(enemy.getX()  , enemy.getY());
							
			     }
			     
			     if(affectedenemy == 4) {
			    	 System.out.println("count is :" + affectedenemy );
			    	 	Enemy enemy = skele_3;
												 
			    	 	skele_3 = new Enemy(enemyFalls( skele_3), null);
			    	 	skele_3.setPosition(enemy.getX()  , enemy.getY());
							
			     }
			     
			     if(affectedenemy == 5) {
			    	 System.out.println("count is :" + affectedenemy );
			    	 	Enemy enemy = skele_4;
												 
			    	 	skele_4 = new Enemy(enemyFalls( skele_4), null);
			    	 	skele_4.setPosition(enemy.getX()  , enemy.getY());
							
			     }
				 			 
				 
	             }
             }
			 return currentenemy;

	}

	// hypolyta's animation rendere. its switch is "hypolytaswitch"
	public Enemy hypolytarender(float delta, SpriteBatch sb, Hypolyta hypo) {
		currentstartTime += delta;
		Hero cont = hypolyta;
		boolean test = false;

		Sprite trick = new Sprite((new Hypolyta(sprite,null)).getblunkHeartRegion());
		hypolyta= new Hero(trick, null);
		hypolyta.setPosition(cont.getX(), cont.getY());

			TextureRegion tregion = hypo.animatehyplyta().getKeyFrame(currentstartTime, false);
			TextureRegion enemylife = hypo.animateHalftoEmpty().getKeyFrame(currentstartTime, false);

			for(Enemy find : packEnemies()) {
            	enemycounter++;
        	 if(find.getY() == cont.getY()) {
        		 //System.out.println("the counter is: "+ enemycounter);
        		 affectedenemy = enemycounter;
        		 this.currentenemy = find;
        		 test = true;
        		 enemycounter =0;
        		 break;
        		 //return enemy;
        		 //skeleton = enemy1;
        	 }//else {enemy =skele_1;}
         }
         if( test) {



		sb.draw(tregion, cont.getX(), cont.getY());
		sb.draw(enemylife, currentenemy.getX() -32, currentenemy.getY() -32);
         
	//}
		 if(hypo.animatehyplyta().isAnimationFinished(currentstartTime)) {
			 hypolytaswitch = false;
			 Sprite trick2 = (new Hypolyta(sprite, null)).NormalLook();  
			 hypolyta= new Hero(trick2, null);
			 hypolyta.setPosition(cont.getX(), cont.getY());
			  System.out.println("newcounter value"+ affectedenemy);
		     if(affectedenemy == 1) {
		    	 	Enemy enemy = skeleton;
											 
					skeleton = new Enemy(enemyFalls( skeleton), null);
					skeleton.setPosition(enemy.getX()  , enemy.getY());
						
		     }
		     
		     if(affectedenemy == 2) {
		    	 	Enemy enemy = skele_1;
											 
		    	 	skele_1 = new Enemy(enemyFalls( skele_1), null);
		    	 	skele_1.setPosition(enemy.getX()  , enemy.getY());
						
		     }
		     
		     if(affectedenemy == 3) {
		    	 	Enemy enemy = skele_2;
											 
		    	 	skele_2 = new Enemy(enemyFalls( skele_2), null);
		    	 	skele_2.setPosition(enemy.getX()  , enemy.getY());
						
		     }
		     
		     if(affectedenemy == 4) {
		    	 	Enemy enemy = skele_3;
											 
		    	 	skele_3 = new Enemy(enemyFalls( skele_3), null);
		    	 	skele_3.setPosition(enemy.getX()  , enemy.getY());
						
		     }
		     
		     if(affectedenemy == 5) {
		    	 	Enemy enemy = skele_4;
											 
		    	 	skele_4 = new Enemy(enemyFalls( skele_4), null);
		    	 	skele_4.setPosition(enemy.getX()  , enemy.getY());
						
		     }
			 			 
			 
             }
         }
		 return currentenemy;

	}

	// thesius's animation rendere. its switch is "thesiusswitch"
	public Enemy thesiusrender(float delta, SpriteBatch sb, Thesius thesius2) {
		Hero cont = thesius;
		boolean test = false;
		thesiuscurrentstartTime += delta;
		
		Sprite trick = new Sprite((new Thesius(sprite,null)).getblunkHeartRegion());
		thesius= new Hero(trick, null);
		thesius.setPosition(cont.getX(), cont.getY());

			TextureRegion tregion = thesius2.animatehyplyta().getKeyFrame(thesiuscurrentstartTime, false);
			TextureRegion enemylife = thesius2.animateHalftoEmpty().getKeyFrame(thesiuscurrentstartTime, false);

			for(Enemy find : packEnemies()) {
            	enemycounter++;
        	 if(find.getY() == cont.getY()) {
        		 //System.out.println("the counter is: "+ enemycounter);
        		 affectedenemy = enemycounter;
        		 this.currentenemy = find;
        		 test = true;
        		 enemycounter =0;
        		 break;
        		 //return enemy;
        		 //skeleton = enemy1;
        	 }//else {enemy =skele_1;}
         }
         if( test) {



		sb.draw(tregion, cont.getX(), cont.getY());
		sb.draw(enemylife, currentenemy.getX() -32, currentenemy.getY() -32);
         
	//}
		 if(thesius2.animatehyplyta().isAnimationFinished(thesiuscurrentstartTime)) {
			 thesiusswitch = false;
			 Sprite trick2 = (new Thesius(sprite, null)).NormalLook();  
			 thesius= new Hero(trick2, null);
			 thesius.setPosition(cont.getX(), cont.getY());
			 // System.out.println("newcounter value"+ affectedenemy);
		     if(affectedenemy == 1) {
		    	 	Enemy enemy = skeleton;
											 
					skeleton = new Enemy(enemyFalls( skeleton), null);
					skeleton.setPosition(enemy.getX()  , enemy.getY());
						
		     }
		     
		     if(affectedenemy == 2) {
		    	 	Enemy enemy = skele_1;
											 
		    	 	skele_1 = new Enemy(enemyFalls( skele_1), null);
		    	 	skele_1.setPosition(enemy.getX()  , enemy.getY());
						
		     }
		     
		     if(affectedenemy == 3) {
		    	 	Enemy enemy = skele_2;
											 
		    	 	skele_2 = new Enemy(enemyFalls( skele_2), null);
		    	 	skele_2.setPosition(enemy.getX()  , enemy.getY());
						
		     }
		     
		     if(affectedenemy == 4) {
		    	 	Enemy enemy = skele_3;
											 
		    	 	skele_3 = new Enemy(enemyFalls( skele_3), null);
		    	 	skele_3.setPosition(enemy.getX()  , enemy.getY());
						
		     }
		     
		     if(affectedenemy == 5) {
		    	 System.out.println("am in the last if");
		    	 	Enemy enemy = skele_4;
											 
		    	 	skele_4 = new Enemy(enemyFalls( skele_4), null);
		    	 	skele_4.setPosition(enemy.getX()  , enemy.getY());
						
		     }
			 			 
			 
             }
         }
		 return currentenemy;

			
	}

	// thesius attack

	public void thesiusAdvanattack(float delta) {

		// thesiusweapon.thesiusweapon()
		Animetor animateval = new Animetor();

		weaponAdvan = new Weapons(thesiusweapon.thesiusweapon(), null);
		location = animateval.render(delta);

		weaponAdvan.setPosition(weapon.getX() + location[0], weapon.getY() + location[1]);
		System.out.println();

	}


	public Sprite enemyFalls(Sprite sp) {
		GameUnit heart;
		heart = new Hero(sprite, null);
		Sprite fullheart = heart.enamyfall();
		fullheart.setPosition(sp.getX(), sp.getY());
		return fullheart;
	}


	@Override
	public void hide() {

		// TODO Auto-generated method stub

	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub

	}
	
	/////  location tracker method ////////////////
	public void updataLocation(Hero helen2, Hero achille2, Hero hercules2, Hero hypolyta2, Hero thesius2) {
		collision.setHelen(helen2);
		collision.setAchille(achille2);
		collision.setHercules(hercules2);
		collision.setHypolyta(hypolyta2);
		collision.setThesius(thesius2);
		//System.out.println("achille x is: "+ collision.getAchille().getX());
		//System.out.println("achille y is: "+ collision.getAchille().getY());
				
	}
	
	//// enemies are bundled together for processing/////////////////////////////////////////
	 ArrayList<Enemy>bad= new ArrayList<Enemy>();
	public ArrayList<Enemy> packEnemies() {
		
		bad.add(skeleton);
		bad.add(skele_1);  //skele_1
		bad.add(skele_2); 
		bad.add(skele_3); 
		bad.add(skele_4); 
		
		return bad;
		
	}
	
////enemies are bundled together for processing/////////////////////////////////////////
	 ArrayList<Hero> good = new ArrayList<Hero>();
	public ArrayList<Hero> packHeros() {
		
		good.add(helen);
		good.add(achille);  
		good.add(hercules); 
		good.add(hypolyta); 
		good.add(thesius); 
		
		return good;
		
	}
	
	public boolean testrow(Hero cont) {
		
		for(Enemy find : packEnemies() ) {
			
       	 if(find.getY() == cont.getY()) {
       		 return true;
			
		}
		
	}
		return false;
	
	}
	
	
}

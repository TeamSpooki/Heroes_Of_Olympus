 package com.heroesofolympus.game;

import java.util.ArrayList;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Main extends Game implements Screen{
	
	// Set screen size
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	
	SpriteBatch batch;
	Texture achille_img, helen_img,hercules_img,hypolyta_img,thesius_img, skeleton_img, skeletonBow_img;
	Sprite achilleStartPosition,helenStartPosition,herculesStartPosition,hypolytaStartPosition,thesiusStartPosition, skeletonStartPosition, skeletonBowStartPosition;
	
	/* GameScreen game_screen; */
	GameMap gameMap;
	TiledMap tiledMap;
	boolean spriteShow = true;
	
	//Check if selected dialog option is shown
	boolean optionsShow = false;
	
	OrthographicCamera camera;
	Hero achille;
	Hero helen;
	Hero hercules;
	Hero hypolyta;
	Hero thesius;
	Enemy skeleton;
	Enemy skeletonBow;
	Vector3 touch;
	Dialog achilleOptions, achilleMove, helenOptions, helenMove, herculesOptions, herculesMove, hypolytaOptions, hypolytaMoves, thesiusOptions, thesiusMove;
	Stage stage;
	Skin skin;
	Viewport viewport;
	Board board;
	int drawing=0;
	
	// An ArrayList used to store/check whether the player has had their turn or not
	ArrayList<String> playerTurn;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		
		camera = new OrthographicCamera();
		camera.setToOrtho(true, 1280, 1080);
		touch=new Vector3();
		stage = new Stage();
		skin = new Skin(Gdx.files.internal("default/skin/uiskin.json"));
		playerTurn = new ArrayList<>();
		
		//Achille Dialog Menu Box
		achilleOptions = new Dialog("Achille",skin){
			protected void result(Object object)
            {
				if(object.equals(1)) {
					// Set dialog state to false when checked on
					optionsShow = false;
				}
				else if (object.equals(2))
			    {
					// New Dialog box to enable specific movement for player
					achilleMove = new Dialog("Move",skin) {
						protected void result(Object object)
			            {
							//Move Up
							if(object.equals(1)) 
							{
								achille.setPosition(achille.getX()+0, achille.getY()-64);
							}
							// Move Down
							else if (object.equals(2))
						    {
								achille.setPosition(achille.getX()+0, achille.getY()+64);
								
						    } 
							// Move Left
							else if(object.equals(3))
							{
						    	achille.setPosition(achille.getX()-64, achille.getY()+0);
						    }
							//Move Right
							else 
						    {
						    	achille.setPosition(achille.getX()+64, achille.getY()+0);
						    }
							optionsShow = false;
			            }
					}
					
					.button("Up", 1)
					.button("Down", 2)
					.button("Left",3)
					.button("Right", 4)
					.show(stage);
					
			        System.out.println("Button Pressed");
			
			    }
				else {
					optionsShow = false;
				}
            };
		};
		
		
		achilleOptions.button("Attack", 1);
		achilleOptions.button("Move", 2);
		achilleOptions.button("Stay",3);
		
				//Helen Dialog Menu Box
				helenOptions = new Dialog("Helen",skin){
					protected void result(Object object)
		            {
						if(object.equals(1)) {
							optionsShow = false;
						}
						else if (object.equals(2))
					    {
							helenMove = new Dialog("Move",skin) {
								
								protected void result(Object object)
					            {
									//Move Up
									if(object.equals(1)) 
									{
										helen.setPosition(helen.getX()+0, helen.getY()-64);
									}
									else if(object.equals(2))
								    {
										helen.setPosition(helen.getX()+0, helen.getY()+64);
										
								    } 
									else if(object.equals(3))
									{
								    	helen.setPosition(helen.getX()-64, helen.getY()+0);
								    }
									else 
								    {
								    	helen.setPosition(helen.getX()+64, helen.getY()+0);
								    }
									optionsShow = false;
					            }
							}
							
							.button("Up", 1)
							.button("Down", 2)
							.button("Left",3)
							.button("Right", 4)
							.show(stage);
					        System.out.println("Button Pressed");
					
					    }
						else {
							optionsShow = false;
						}
		            };
				};
				helenOptions.button("Attack", 1);
				helenOptions.button("Move", 2);
				helenOptions.button("Stay",3);
				
				//Hercules Dialog Menu Box
				herculesOptions = new Dialog("Hercules",skin){
					protected void result(Object object)
		            {
						if(object.equals(1)) {
							optionsShow = false;
						}
						else if (object.equals(2))
					    {
							herculesMove = new Dialog("Move",skin) {
								protected void result(Object object)
					            {
									//Move Up
									if(object.equals(1)) 
									{
										hercules.setPosition(hercules.getX()+0, hercules.getY()-64);
									}
									else if(object.equals(2))
								    {
										hercules.setPosition(hercules.getX()+0, hercules.getY()+64);
										
								    } 
									else if(object.equals(3))
									{
										hercules.setPosition(hercules.getX()-64, hercules.getY()+0);
								    }
									else 
								    {
										hercules.setPosition(hercules.getX()+64, hercules.getY()+0);
								    }
									optionsShow = false;
					            }
							}
							
							.button("Up", 1)
							.button("Down", 2)
							.button("Left",3)
							.button("Right", 4)
							.show(stage);
					        System.out.println("Button Pressed");
					
					    }
						else {
							optionsShow = false;
						}
		            };
				};
				herculesOptions.button("Attack", 1);
				herculesOptions.button("Move", 2);
				herculesOptions.button("Stay",3);
				
				//hypolyta Dialog Menu Box
				hypolytaOptions = new Dialog("Hypolyta",skin){
					protected void result(Object object)
		            {
						if(object.equals(1)) {
							optionsShow = false;
						}
						else if (object.equals(2))
					    {
							hypolytaOptions = new Dialog("Move",skin) {
								protected void result(Object object)
					            {
									//Move Up
									if(object.equals(1)) 
									{
										hypolyta.setPosition(hypolyta.getX()+0, hypolyta.getY()-64);
									}
									else if(object.equals(2))
								    {
										hypolyta.setPosition(hypolyta.getX()+0, hypolyta.getY()+64);
										
								    } 
									else if(object.equals(3))
									{
										hypolyta.setPosition(hypolyta.getX()-64, hypolyta.getY()+0);
								    }
									else 
								    {
										hypolyta.setPosition(hypolyta.getX()+64, hypolyta.getY()+0);
								    }
									optionsShow = false;
					            }
							}
							
							.button("Up", 1)
							.button("Down", 2)
							.button("Left",3)
							.button("Right", 4)
							.show(stage);
					        System.out.println("Button Pressed");
					
					    }
						else {
							optionsShow = false;
						}
		            };
				};
				hypolytaOptions.button("Attack", 1);
				hypolytaOptions.button("Move", 2);
				hypolytaOptions.button("Stay",3);
				
				//thesius Dialog Menu Box
				thesiusOptions = new Dialog("Thesius",skin){
					protected void result(Object object)
		            {
						if(object.equals(1)) {
							optionsShow = false;
						}
						else if (object.equals(2))
					    {
							thesiusOptions = new Dialog("Move",skin) {
								protected void result(Object object)
					            {
									//Move Up
									if(object.equals(1)) 
									{
										thesius.setPosition(thesius.getX()+0, thesius.getY()-64);
									}
									else if(object.equals(2))
								    {
										thesius.setPosition(thesius.getX()+0, thesius.getY()+64);
										
								    } 
									else if(object.equals(3))
									{
										thesius.setPosition(thesius.getX()-64, thesius.getY()+0);
								    }
									else 
								    {
										thesius.setPosition(thesius.getX()+64, thesius.getY()+0);
								    }
									optionsShow = false;
					            }
							}
							
							.button("Up", 1)
							.button("Down", 2)
							.button("Left",3)
							.button("Right", 4)
							.show(stage);
					        System.out.println("Button Pressed");
					
					    }
						else {
							optionsShow = false;
						}
		            };
				};
				thesiusOptions.button("Attack", 1);
				thesiusOptions.button("Move", 2);
				thesiusOptions.button("Stay",3);
		
		gameMap = new TiledGameMap();
		
		Gdx.input.setInputProcessor(stage);
		
		//board= new Board();
		
		/*
		 * achille_img = new Hero(new Sprite(new Texture("Achilles.png")),
		 * (TiledMapTileLayer) tiledMap.getLayers().get(0));
		 */
		
		// Heroes
		achille_img= new Texture(Gdx.files.internal("Achilles.png"));
		achilleStartPosition = new Sprite(achille_img,0,128,64,64);
		achilleStartPosition.flip(true, true);
		achille = new Hero(achilleStartPosition, null);
		achille.setPosition(256, 128);
		
		helen_img= new Texture(Gdx.files.internal("Helen.png"));
		helenStartPosition = new Sprite(helen_img,0,128,64,64);
		helenStartPosition.flip(false, true);
		helen = new Hero(helenStartPosition, null);
		helen.setPosition(128, 64);
		
		hercules_img= new Texture(Gdx.files.internal("Hercules.png"));
		herculesStartPosition = new Sprite(hercules_img,0,128,64,64);
		herculesStartPosition.flip(false, true);
		hercules = new Hero(herculesStartPosition, null);
		hercules.setPosition(256, 320);
		
		hypolyta_img= new Texture(Gdx.files.internal("Hypolyta.png"));
		hypolytaStartPosition = new Sprite(hypolyta_img,0,128,64,64);
		hypolytaStartPosition.flip(false, true);
		hypolyta = new Hero(hypolytaStartPosition, null);
		hypolyta.setPosition(128, 320);
		
		thesius_img= new Texture(Gdx.files.internal("Thesius.png"));
		thesiusStartPosition = new Sprite(thesius_img,0,128,64,64);
		thesiusStartPosition.flip(false, true);
		thesius = new Hero(thesiusStartPosition, null);
		thesius.setPosition(128, 512);
		
		//Enemies
		skeleton_img = new Texture(Gdx.files.internal("skeletonSpear.png"));
		skeletonStartPosition = new Sprite(skeleton_img, 0, 320, 128, 128);
		skeletonStartPosition.flip(false, true);
		skeleton = new Enemy(skeletonStartPosition, null);
		skeleton.setPosition(830,613);
		
		skeletonBow_img = new Texture(Gdx.files.internal("skeletonBow.png"));
		skeletonBowStartPosition = new Sprite(skeleton_img, 0, 320, 128, 128);
		skeletonBowStartPosition.flip(false, true);
		skeletonBow = new Enemy(skeletonBowStartPosition, null);
		skeletonBow.setPosition(960,300);
		
		//Titan
		
		
	}
	

	@Override
	public void render () {
		/* update(); */
		
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.setProjectionMatrix(camera.combined);
		gameMap.render(camera);
		camera.update();
        
        batch.begin();
		achille.draw(batch);
		helen.draw(batch);
		hercules.draw(batch);
		hypolyta.draw(batch);
		thesius.draw(batch);
		skeleton.draw(batch);
		skeletonBow.draw(batch);
		batch.end();
		stage.act();
		stage.draw();
		
		/*if(drawing==0) {
			board.Draw(batch);
			drawing++;
		}
		
		Gdx.graphics.setContinuousRendering(false);
		*/
		
		// When the player/hero is clicked on 
		if(Gdx.input.isTouched()) {
			
			touch.set(Gdx.input.getX(), Gdx.input.getY(), 0); 
			camera.unproject(touch);
			
			// This will check if the current options/dialog box are displayed or not and if the ArrayList contains/counted 'Achille'
			// Get the X and Y of the sprite when clicked on
			if(!optionsShow && !playerTurn.contains("achille") && touch.x >= achille.getX() && touch.x <= achille.getX() + 64
					&& touch.y >= achille.getY() && touch.y <= achille.getY() + 64) {
				
				//Display Main dialog box
				achilleOptions.show(stage);
				
				//If 'Achille' has not yet had a turn, then add it to the count in the ArrayList
				playerTurn.add("achille");
				
				// If no other hero/player options are currently shown it will display the dialog box
				optionsShow = true;
				
			}
			if(!optionsShow && !playerTurn.contains("helen") && touch.x >= helen.getX() && touch.x <= helen.getX() +64
					&& touch.y >= helen.getY() && touch.y <= helen.getY() + 64) {
				 
				helenOptions.show(stage); 
				playerTurn.add("helen");
				optionsShow = true;
			}
			if(!optionsShow && !playerTurn.contains("hercules") && touch.x>=hercules.getX() && touch.x <= hercules.getX() + 64
					&& touch.y >= hercules.getY() && touch.y <= hercules.getY() + 64) {
				
				herculesOptions.show(stage);
				playerTurn.add("hercules");
				optionsShow = true;
			}
			if(!optionsShow && !playerTurn.contains("hypolyta") && touch.x >= hypolyta.getX() && touch.x <= hypolyta.getX() + 64
					&& touch.y >= hypolyta.getY() && touch.y <= hypolyta.getY() + 64) {
				
				hypolytaOptions.show(stage);
				playerTurn.add("hypolyta");
				optionsShow = true;
			}
			if(!optionsShow && !playerTurn.contains("thesius") && touch.x >= thesius.getX() && touch.x <= thesius.getX() + 64
					&& touch.y >= thesius.getY() && touch.y <= thesius.getY() + 64) {
				
				thesiusOptions.show(stage);
				playerTurn.add("thesius");
				optionsShow = true;
			}
			
			// Here we check or alternatively set the count/size of the ArrayList to be 5 because we have 5 heroes.
			if(playerTurn.size() == 5) {
				playerTurn = new ArrayList<>();
			}
			
		}
		
	}
	
	/*********************CHECK WHETHER TWO SPRITES ARE ON THE SAME TILE*********************/
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
	public void dispose () {
		batch.dispose();
		stage.dispose();
		
	}


	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

}

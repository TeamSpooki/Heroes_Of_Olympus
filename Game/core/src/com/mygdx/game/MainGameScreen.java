package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Timer;
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
	GameUnit current,enemy;
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
				current=board.findNearestHero(touch.x,touch.y);
				options.setHero(current);
				options.show(stage);
				
			}
			if(move) {
				//board.MovePiece(current.getLocation(), board.findNearestLocation(touch.x,touch.y));
				
				board.GetPieceHero(current.getLocation()).setPosition(board.findNearestLocation(touch.x,touch.y).getX(), board.findNearestLocation(touch.x,touch.y).getY());
				board.GetPieceHero(current.getLocation()).setMoved(true);
				//board.validMoves.clear();
				movements++;
			}
		}
		if(board.findNearestEnemy(touch.x,touch.y) instanceof Enemy) {
			enemy=board.findNearestEnemy(touch.x,touch.y);
			System.out.println("works"+enemy.name);
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
	@Override
	public void dispose () {
		game.batch.dispose();
		stage.dispose();
	}

	public Object getBatch() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}
	
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
				
				//if(Gdx.input.) {
					System.out.println("works");
					//touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
					//camera.unproject(touch);
					//currentEnemy=(Enemy)board.findNearestEnemy(touch.x,touch.y);
					//while(enemy==null) {
						
					//}
					
					attack();
				//}
			}
			else if (object.equals(2))
		    {
				//new Dialog("Move",this.getSkin()) {
					//protected void result(Object object)
		           // {
						if(movements==5) {
							movements=0;
							board.resetMovement();
							board.moveEnemies();
						}
						
						Location up =currentHero.getLocation().aboveLocation();
						Location down =currentHero.getLocation().belowLocation();
						Location left =currentHero.getLocation().leftLocation();
						Location right =currentHero.getLocation().rightLocation();
						board.validMoves.add(up);
						board.validMoves.add(down);
						board.validMoves.add(left);
						board.validMoves.add(right);
						move=true;
						/*while(!moved) {
							if(Gdx.input.isTouched()){
								touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
								camera.unproject(touch);
								board.MovePiece(currentHero.getLocation(), board.findNearestLocation(touch.x,touch.y));
								board.GetPieceHero(currentHero.getLocation()).setMoved(true);
								board.validMoves.clear();
								movements++;
								moved=true;
							}
						}
						//get location
						//add valid moves to list valid moves in board
						//when board.draw is called, check if there are valid moves and highlight the surrounding boxes
						//remove all moves after move is complete
						//validmoves.clear()
						//use pixmap to colour the surrounding boxes.
						/*
						 Sprite overlayBoxSpriteUp,down,left,right;
						 
						 overlayBoxSprite = new Sprite(new Texture("url of image"))
						 overlayBoxSprite.setPosition(board.GetPieceHero(currentHero.getLocation()).getX()-64, board.GetPieceHero(currentHero.getLocation()).getY());
						Color color=Color.YELLOW;(optional)
						overlayBoxSprite.setColor(color);(optional)
						overlayBoxSprite.draw(batch);
						if(validmoves.isempty)
						overlayBoxSpriteUp.dispose();
						
						if(object.equals(1)) {
							if(!board.GetPieceHero(currentHero.getLocation()).isMoved()&&movements<5) {
								board.GetPieceHero(currentHero.getLocation()).setMoved(true);
								
								
								board.GetPieceHero(currentHero.getLocation()).animateUp();
								Timer t =new Timer();
								t.delay(10);
								t.stop();
								board.GetPieceHero(currentHero.getLocation()).moveUp();
								
								movements++;
							}
						}
						else if (object.equals(2))
					    {
							if(!board.GetPieceHero(currentHero.getLocation()).isMoved()&&movements<5) {
								board.GetPieceHero(currentHero.getLocation()).moveDown();
								board.GetPieceHero(currentHero.getLocation()).setMoved(true);
								movements++;
							}
					    }else if(object.equals(3) ){
					    	if(!board.GetPieceHero(currentHero.getLocation()).isMoved()&&movements<5) {
					    		board.GetPieceHero(currentHero.getLocation()).moveLeft();
					    		board.GetPieceHero(currentHero.getLocation()).setMoved(true);
								movements++;
							}
					    }else {
					    	if(!board.GetPieceHero(currentHero.getLocation()).isMoved()&&movements<5) {
					    		board.GetPieceHero(currentHero.getLocation()).moveRight();
					    		board.GetPieceHero(currentHero.getLocation()).setMoved(true);
								movements++;
							}
					    }
						*/
		            //}
		            
				//}
				
		    }
			else {
				
			}
		}
	}
}

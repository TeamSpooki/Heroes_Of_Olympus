package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mygdx.game.world.GameMap;
import com.mygdx.game.world.TiledGameMap;

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
	float elapsedTime= 0f;
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
				current=board.findNearestHero(touch.x,touch.y);
				options.setHero(current);
				options.show(stage);
			}
		}
		if(board.findNearestEnemy(touch.x,touch.y) instanceof Enemy) {
			enemy=board.findNearestEnemy(touch.x,touch.y);
			System.out.println("works"+enemy.name);
			board.GetPieceEnemy(enemy.getLocation()).deleteSprite();
			board.enemies.remove(enemy);
		}
		//System.out.println("works,"+	touch.x+","+touch.y);
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
				new Dialog("Move",this.getSkin()) {
					protected void result(Object object)
		            {
						if(movements==5) {
							movements=0;
							board.resetMovement();
							board.moveEnemies();
						}
						if(object.equals(1)) {
							if(!board.GetPieceHero(currentHero.getLocation()).isMoved()&&movements<5) {
								board.GetPieceHero(currentHero.getLocation()).moveUp();
								/*game.batch.begin();
								TextureRegion currentFrame = board.GetPieceHero(currentHero.getLocation()).animation.getKeyFrame(elapsedTime,true);		
								game.batch.draw(currentFrame, board.GetPieceHero(currentHero.getLocation()).getX(), board.GetPieceHero(currentHero.getLocation()).getY());
								game.batch.end();
								*/
								board.GetPieceHero(currentHero.getLocation()).setMoved(true);
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
						
		            }
				}.button("up", 1)
				.button("down", 2)
				.button("left",3)
				.button("right", 4)
				.show(stage);
		    }
			else {
				
			}
		}
	}
}

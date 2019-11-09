package com.heroesofolympus.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class TiledGameMap extends GameMap {
	
	TiledMap tiledMap;
	OrthogonalTiledMapRenderer tiledMapRenderer;
	Hero achille;
	Sprite achilleStartPosition,helenStartPosition,herculesStartPosition,hypolytaStartPosition,thesiusStartPosition;
	Texture achille_img, helen_img,hercules_img,hypolyta_img,thesius_img;


	public TiledGameMap() {
		tiledMap = new TmxMapLoader().load("NewMap.tmx");
		tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
	}

	@Override
	public void render(OrthographicCamera camera) {
		tiledMapRenderer.setView(camera);
		tiledMapRenderer.render();
	}

	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		tiledMap.dispose();
	}

	@Override
	public TileType getTileTypeByCoordinate(int layer, int col, int row) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getWidth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getLayers() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
	
		
		/*
		 * achille_img= new Texture(Gdx.files.internal("Achilles.png"));
		 * achilleStartPosition = new Sprite(achille_img,0,128,64,64);
		 * achilleStartPosition.flip(true, true); achille = new
		 * Hero(achilleStartPosition, null); achille.setPosition(256, 128);
		 */
	}

}

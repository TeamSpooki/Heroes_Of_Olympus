package com.mygdx.world;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class TiledGameMap{
	
	TiledMap tiledMap;
	OrthogonalTiledMapRenderer tiledMapRenderer;

	public TiledGameMap(String path) {
		tiledMap = new TmxMapLoader().load(path);
		tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
		
	}

	public void render(OrthographicCamera camera) {
		tiledMapRenderer.setView(camera);
		tiledMapRenderer.render();
	}

	public TiledMap getTiledMap() {
		return tiledMap;
	}
}

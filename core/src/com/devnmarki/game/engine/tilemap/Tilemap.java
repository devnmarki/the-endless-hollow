package com.devnmarki.game.engine.tilemap;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.devnmarki.game.engine.Engine;
import com.devnmarki.game.engine.entities.Entity;
import com.devnmarki.game.engine.entities.physics.BoxCollider;
import com.devnmarki.game.engine.math.Vector2f;
import com.devnmarki.game.engine.math.Vector2i;

public class Tilemap {

	private Engine engine;
	private TiledMap map;
	private OrthogonalTiledMapRenderer mapRenderer;
	
	public Tilemap(Engine engine, TiledMap map) {
		this.engine = engine;
		this.map = map;
		
		this.mapRenderer = new OrthogonalTiledMapRenderer(map, Engine.gameScale);
	}
	
	public void render(String layerName) {
		mapRenderer.setView(engine.getCurrentState().getCamera());
		
		mapRenderer.getBatch().begin();
		mapRenderer.renderTileLayer((TiledMapTileLayer) map.getLayers().get(layerName));
		mapRenderer.getBatch().end();
	}
	
	public void loadColliders(String layerName) {
		MapLayer collisionLayer = map.getLayers().get(layerName);
		MapObjects colliderObjects = collisionLayer.getObjects();

		for (MapObject colliderObject : colliderObjects) {
			float w = colliderObject.getProperties().get("width", Float.class) * Engine.gameScale;
			float h = colliderObject.getProperties().get("height", Float.class) * Engine.gameScale;
			float x = (colliderObject.getProperties().get("x", Float.class) * Engine.gameScale) + w / 2f;
			float y = colliderObject.getProperties().get("y", Float.class) * Engine.gameScale + h / 2f;

			Vector2f colliderPos = new Vector2f(x, y);
			Vector2f colliderSize = new Vector2f(w, h);

			Entity colliderEntity = new Entity(engine, colliderPos);
			colliderEntity.addCollider(new BoxCollider(new Vector2i((int) colliderSize.x, (int) colliderSize.y)));

			engine.getCurrentState().addEntity(colliderEntity);
		}
	}
}

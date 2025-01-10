package com.devnmarki.game.engine.states;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.devnmarki.game.engine.Engine;
import com.devnmarki.game.engine.entities.Entity;

public abstract class State {

	protected Engine engine = null;
	
	private List<Entity> entities = new ArrayList<Entity>();
	
	private OrthographicCamera camera;
	
	protected State(Engine engine) {
		this.engine = engine;
	
		this.init();
	}
	
	private void init() {
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	}
	
	public abstract void enter();
	public abstract void update();
	public abstract void render();
	public abstract void leave();
	
	public void addEntity(Entity entity) {
		this.entities.add(entity);
	}
	
	public void removeEntity(Entity entity) {
		this.entities.remove(entity);
	}
	
	public Engine getEngine() {
		return engine;
	}
	
	public List<Entity> getEntities() {
		return entities;
	}
	
	public OrthographicCamera getCamera() {
		return camera;
	}
}

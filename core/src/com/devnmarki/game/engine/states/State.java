package com.devnmarki.game.engine.states;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.devnmarki.game.engine.Engine;
import com.devnmarki.game.engine.entities.Entity;
import com.devnmarki.game.engine.ui.UIComponent;

public abstract class State {

	protected Engine engine = null;
	
	private List<Entity> entities = new ArrayList<Entity>();
	private List<UIComponent> uiComponents = new ArrayList<UIComponent>();
	
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

	public void addUIComponent(UIComponent component) {
		this.uiComponents.add(component);
	}

	public void removeUIComponent(UIComponent component) {
		this.uiComponents.remove(component);
	}

	public Entity findEntityWithTag(String tag) {
		Entity target = null;

		for (Entity entity : entities) {
			if (!entity.getTag().equals(tag)) continue;

			target = entity;
		}

		return target;
	}

	public List<Entity> findEntitiesWithTag(String tag) {
		List<Entity> targetList = new ArrayList<Entity>();

		for (Entity entity : entities) {
			if (!entity.getTag().equals(tag)) continue;

			targetList.add(entity);
		}

		return targetList;
	}
	
	public Engine getEngine() {
		return engine;
	}
	
	public List<Entity> getEntities() {
		return entities;
	}

	public List<UIComponent> getUIComponents() {
		return uiComponents;
	}
	
	public OrthographicCamera getCamera() {
		return camera;
	}
}

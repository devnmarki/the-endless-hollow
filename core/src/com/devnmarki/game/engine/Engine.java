package com.devnmarki.game.engine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.devnmarki.game.engine.entities.Entity;
import com.devnmarki.game.engine.entities.physics.MyContactListener;
import com.devnmarki.game.engine.math.Vector2i;
import com.devnmarki.game.engine.states.State;

public class Engine {

	public static final SpriteBatch SPRITE_BATCH = new SpriteBatch();
	public static final ShapeRenderer SHAPE_RENDERER = new ShapeRenderer();
	public static final World WORLD = new World(new Vector2(0, -15f), true);
	public static final float PPM = 100f;
	
	public static float gameScale = 1f;
	
	private Map<String, State> states = new HashMap<String, State>();
	private State currentState = null;
	private State previousState = null;

	private Box2DDebugRenderer debugRenderer = new Box2DDebugRenderer();
	
	public static boolean debugMode = true;
	
	public Engine() {
		this.start();
	}
	
	public void start() {
		WORLD.setContactListener(new MyContactListener());
	}
	
	public void update() {
		if (currentState != null) {
			currentState.update();
			
			WORLD.step(1 / 60f, 6, 2);
			
			SPRITE_BATCH.begin();
			
			currentState.render();
			
			List<Entity> entitiesCopy = new ArrayList<Entity>(currentState.getEntities());
			for (Entity e : entitiesCopy) {
				e.onUpdate();
			}
			
			SPRITE_BATCH.end();

			SPRITE_BATCH.setProjectionMatrix(getCurrentState().getCamera().combined);

			if (debugMode) {
				debugRenderer.render(WORLD, SPRITE_BATCH.getProjectionMatrix().cpy().scale(Engine.PPM, Engine.PPM, 1));
			}

			processPendingDestruction();
		}
		
		if (Gdx.input.isKeyJustPressed(Keys.TAB)) {
			debugMode = !debugMode;
		}
	}
	
	public void addState(String stateName, State state) {
		if (states.containsKey(stateName)) return;
		
		states.put(stateName, state);
	}
	
	public void reloadCurrentState() {
		if (currentState != null) {
			currentState.getEntities().clear();
			
			Array<Body> bodyArray = new Array<>();
	        WORLD.getBodies(bodyArray);

	        for (Body body : bodyArray) {
	            WORLD.destroyBody(body); 
	        }
	        
			currentState.enter();
		}
	}
	
	public void switchState(String stateName) {
		State newState = null;
		
		if (states.containsKey(stateName)) {
			newState = states.get(stateName);
			
			if (currentState != newState) {
				previousState = currentState;
				currentState = newState;
				
				if (previousState != null)
					previousState.leave();
				
				reloadCurrentState();
			}
		} else {
			System.out.println("Unable to find state: " + stateName);
		}
	}

	public void processPendingDestruction() {
		List<Entity> entitiesToRemove = new ArrayList<>();

		List<Entity> entitiesCopy = new ArrayList<>(currentState.getEntities());
		for (Entity entity : entitiesCopy) {
			if (entity.isPendingDestroy()) {
				entity.destroy();
				entitiesToRemove.add(entity);
			}
		}

		currentState.getEntities().removeAll(entitiesToRemove);
	}
	
	public void clear(Color color) {
		ScreenUtils.clear(color);
	}
	
	public void clear(float r, float g, float b) {
		ScreenUtils.clear(r / 255f, g / 255f, b /255f, 1);
	}
	
	public State getCurrentState() {
		return currentState;
	}
	
}
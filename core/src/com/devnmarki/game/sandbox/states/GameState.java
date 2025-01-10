package com.devnmarki.game.sandbox.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.devnmarki.game.engine.AssetPool;
import com.devnmarki.game.engine.Engine;
import com.devnmarki.game.engine.states.State;
import com.devnmarki.game.engine.tilemap.EntityLoader;
import com.devnmarki.game.engine.tilemap.Tilemap;

public class GameState extends State {

	private Tilemap map;
	
	public GameState(Engine engine) {
		super(engine);
	}

	@Override
	public void enter() {
		System.out.println("Entered game state");

		map = new Tilemap(engine, AssetPool.getTilemap("maps/test_map.tmx"));
		map.loadColliders("Collision");
		EntityLoader entityLoader = new EntityLoader(engine);
		entityLoader.loadGameObjects(map, "Game Objects");
	}

	@Override
	public void update() {
	}

	@Override
	public void render() {
		map.render("Tiles");
	}

	@Override
	public void leave() {
		System.out.println("Left game state");
	}


}

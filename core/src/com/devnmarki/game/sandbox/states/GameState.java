package com.devnmarki.game.sandbox.states;

import com.devnmarki.game.engine.AssetPool;
import com.devnmarki.game.engine.Engine;
import com.devnmarki.game.engine.math.Vector2f;
import com.devnmarki.game.engine.states.State;
import com.devnmarki.game.engine.tilemap.Tilemap;
import com.devnmarki.game.sandbox.characters.SorcererEntity;

public class GameState extends State {

	private Tilemap map;
	
	public GameState(Engine engine) {
		super(engine);
	}

	@Override
	public void enter() {
		System.out.println("Entered game state");
		
		this.addEntity(new SorcererEntity(this.engine, new Vector2f(200, 500)));
		
		map = new Tilemap(engine, AssetPool.getTilemap("maps/test_map.tmx"));
		map.loadColliders("Collision");
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

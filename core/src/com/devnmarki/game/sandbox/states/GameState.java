package com.devnmarki.game.sandbox.states;

import com.devnmarki.game.engine.AssetPool;
import com.devnmarki.game.engine.Engine;
import com.devnmarki.game.engine.states.State;
import com.devnmarki.game.engine.tilemap.EntityLoader;
import com.devnmarki.game.engine.tilemap.Tilemap;
import com.devnmarki.game.sandbox.characters.SorcererEntity;

public class GameState extends State {

	private Tilemap map;
	private SorcererEntity sorcerer;
	
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

		getCamera().position.set(getCamera().viewportHeight / 2, 816f + getCamera().viewportHeight / 2, 0);
		getCamera().update();

		sorcerer = (SorcererEntity) findEntityWithTag("player");
	}

	@Override
	public void update() {
		if (sorcerer == null) return;

		if (sorcerer.getPosition().x < getCamera().position.x) {
			getCamera().position.set(getCamera().position.x - 768f, getCamera().position.y, 0);
		}
		if (sorcerer.getPosition().x > getCamera().position.x + getCamera().viewportWidth / 2) {
			getCamera().position.set(getCamera().position.x + 768f, getCamera().position.y, 0);
		}

		if (sorcerer.getPosition().y < getCamera().position.y) {
			getCamera().position.set(getCamera().position.x, getCamera().position.y - 768f, 0);
		}
		if (sorcerer.getPosition().y > getCamera().position.y + getCamera().viewportHeight / 2) {
			getCamera().position.set(getCamera().position.x, getCamera().position.y + 768f, 0);
		}

		getCamera().update();
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

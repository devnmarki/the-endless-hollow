package com.devnmarki.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.devnmarki.game.engine.Engine;
import com.devnmarki.game.engine.tilemap.EntityLoader;
import com.devnmarki.game.sandbox.characters.SorcererEntity;
import com.devnmarki.game.sandbox.states.GameState;
import com.devnmarki.game.sandbox.states.TestState;

public class Game extends ApplicationAdapter {
	
	private Engine engine;
	
	@Override
	public void create () {
		engine = new Engine();
		Engine.gameScale = 6f;

		EntityLoader.addEntityToLoad("Player", () -> new SorcererEntity(engine));
		
		engine.addState("game", new GameState(engine));
		engine.addState("test", new TestState(engine));
		engine.switchState("game");
	}

	@Override
	public void render () {
		engine.clear(13f, 19f, 48f);
		engine.update();
		
		if (Gdx.input.isKeyJustPressed(Keys.Q)) {
			engine.switchState("game");
		} else if (Gdx.input.isKeyJustPressed(Keys.E)) {
			engine.switchState("test");
		}
		
		if (Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
			Gdx.app.exit();
		}
	}
	
	@Override
	public void dispose () {
		
	}
}

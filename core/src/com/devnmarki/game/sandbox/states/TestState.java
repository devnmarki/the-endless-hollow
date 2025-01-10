package com.devnmarki.game.sandbox.states;

import com.devnmarki.game.engine.Engine;
import com.devnmarki.game.engine.states.State;

public class TestState extends State {

	public TestState(Engine engine) {
		super(engine);
	}

	@Override
	public void enter() {
		System.out.println("Entered test state");
	}

	@Override
	public void update() {
		
	}

	@Override
	public void render() {
		
	}

	@Override
	public void leave() {
		System.out.println("Left test state");
	}

}

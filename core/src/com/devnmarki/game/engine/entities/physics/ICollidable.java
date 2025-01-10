package com.devnmarki.game.engine.entities.physics;

import com.badlogic.gdx.math.Vector2;

public interface ICollidable {

	void onCollisionEnter(BoxCollider other, Vector2 normal);
	void onCollisionExit(BoxCollider other);
	
}

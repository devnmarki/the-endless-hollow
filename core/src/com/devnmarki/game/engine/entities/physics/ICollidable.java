package com.devnmarki.game.engine.entities.physics;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Contact;

public interface ICollidable {

	void onCollisionEnter(Collider other, Vector2 normal, Contact contact);
	void onCollisionExit(Collider other);
	void onCollisionPreSolve(Collider other, Contact contact);
	
}

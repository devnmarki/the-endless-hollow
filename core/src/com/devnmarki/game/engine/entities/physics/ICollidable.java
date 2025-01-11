package com.devnmarki.game.engine.entities.physics;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Contact;

public interface ICollidable {

	void onCollisionEnter(BoxCollider other, Vector2 normal, Contact contact);
	void onCollisionExit(BoxCollider other);
	void onCollisionPreSolve(BoxCollider other, Contact contact);
	
}

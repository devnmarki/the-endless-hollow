package com.devnmarki.game.engine.entities.physics;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.devnmarki.game.engine.entities.Entity;

public class MyContactListener implements ContactListener {
	
	@Override
	public void beginContact(Contact contact) {
		Fixture fa = contact.getFixtureA();
        Fixture fb = contact.getFixtureB();
        
        Entity entityA = (Entity) fa.getBody().getUserData();
        Entity entityB = (Entity) fb.getBody().getUserData();
        
        Vector2 normal = contact.getWorldManifold().getNormal();
        
        if (entityA != null && entityA instanceof ICollidable) {
            ((ICollidable) entityA).onCollisionEnter((Collider) fb.getUserData(), normal, contact);
        }

        if (entityB != null && entityB instanceof ICollidable) {
            ((ICollidable) entityB).onCollisionEnter((Collider) fa.getUserData(), normal.scl(-1), contact);
        }
	}

	@Override
	public void endContact(Contact contact) {
		Fixture fa = contact.getFixtureA();
        Fixture fb = contact.getFixtureB();
        
        Entity entityA = (Entity) fa.getBody().getUserData();
        Entity entityB = (Entity) fb.getBody().getUserData();
        
        if (entityA != null && entityA instanceof ICollidable) {
            ((ICollidable) entityA).onCollisionExit((Collider) fb.getUserData());
        }

        if (entityB != null && entityB instanceof ICollidable) {
            ((ICollidable) entityB).onCollisionExit((Collider) fa.getUserData());
        }
	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
        Fixture fa = contact.getFixtureA();
        Fixture fb = contact.getFixtureB();

        Entity entityA = (Entity) fa.getBody().getUserData();
        Entity entityB = (Entity) fb.getBody().getUserData();

        if (entityA != null && entityA instanceof ICollidable) {
            ((ICollidable) entityA).onCollisionPreSolve((Collider) fb.getUserData(), contact);
        }

        if (entityB != null && entityB instanceof ICollidable) {
            ((ICollidable) entityB).onCollisionPreSolve((Collider) fa.getUserData(), contact);
        }
	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
		
	}

}

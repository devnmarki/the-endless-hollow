package com.devnmarki.game.engine.entities;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.Vector2;
import com.devnmarki.game.engine.Engine;
import com.devnmarki.game.engine.entities.physics.BoxCollider;
import com.devnmarki.game.engine.entities.renderEntity.SpriteRenderer;
import com.devnmarki.game.engine.math.Vector2f;

public class Entity implements IEntity {

	protected Engine engine;

	protected Vector2f position;
	protected float rotation;
	
	private List<BoxCollider> colliders = new ArrayList<BoxCollider>();
	
	private SpriteRenderer spriteRenderer;
	
	public Entity(Engine engine) {
		this.engine = engine;
		this.position = Vector2f.ZERO;
		this.rotation = 0f;
	
		onStart();
	}
	
	public Entity(Engine engine, Vector2f position) {
		this.engine = engine;
		this.position = position;
		this.rotation = 0f;
		
		onStart();
	}
	
	public void onStart() {
		spriteRenderer = new SpriteRenderer(this, null);
		spriteRenderer.setScale(Engine.gameScale);
	}
	
	public void onUpdate() {
		for (BoxCollider collider : colliders) {
			collider.update();
		}
		
		if (spriteRenderer.getSprite() != null)
			spriteRenderer.render();
	}
	
	@Override
	public void addCollider(BoxCollider collider) {
		collider.setEntity(this);
		
		if (collider.getEntity() != null) {
			colliders.add(collider);
		}
	}
	
	@Override
	public void onCollisionEnter(BoxCollider other, Vector2 normal) {
		
	}
	
	@Override
	public void onCollisionExit(BoxCollider other) {
		
	}
	
	public void destroy() {
		engine.getCurrentState().removeEntity(this); 
	}

	public void setPosition(Vector2f position) {
		this.position = position;

		for (BoxCollider collider : colliders) {
			collider.setPosition(position);
		}
	}

	public void setRotation(float rotation) {
		this.rotation = rotation;
	}
	
	public Vector2f getPosition() {
		return this.position;
	}
	
	public float getRotation() {
		return this.rotation;
	}
	
	public List<BoxCollider> getColliders() {
		return this.colliders;
	}
	
	public SpriteRenderer getSpriteRenderer() {
		return this.spriteRenderer;
	}
}

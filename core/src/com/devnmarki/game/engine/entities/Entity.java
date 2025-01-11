package com.devnmarki.game.engine.entities;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.Filter;
import com.devnmarki.game.engine.Engine;
import com.devnmarki.game.engine.entities.physics.BoxCollider;
import com.devnmarki.game.engine.entities.renderEntity.SpriteRenderer;
import com.devnmarki.game.engine.math.Vector2f;

public class Entity implements IEntity {

	protected Engine engine;

	protected String tag = "untagged";
	protected String name = "Entity";

	protected Vector2f position;
	protected float rotation;

	private MapObject mapObject;
	
	private List<BoxCollider> colliders = new ArrayList<BoxCollider>();
	
	private SpriteRenderer spriteRenderer;

	private boolean isPendingDestroy = false;
	
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
	public void onCollisionEnter(BoxCollider other, Vector2 normal, Contact contact) {
		
	}
	
	@Override
	public void onCollisionExit(BoxCollider other) {
		
	}

	@Override
	public void onCollisionPreSolve(BoxCollider other, Contact contact) {

	}

	public void destroy() {
		for (BoxCollider collider : colliders) {
			if (collider != null && collider.getBody() != null) {
				Engine.WORLD.destroyBody(collider.getBody());
			}
		}
		colliders.clear();

		engine.getCurrentState().removeEntity(this);
	}

	public void markForDestroy() {
		isPendingDestroy = true;
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

	public void setMapObject(MapObject mapObject) {
		this.mapObject = mapObject;
	}

	public String getTag() {
		return tag;
	}

	public String getName() {
		return name;
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

	public MapObject getMapObject() {
		return mapObject;
	}

	public SpriteRenderer getSpriteRenderer() {
		return this.spriteRenderer;
	}

	public boolean isPendingDestroy() {
		return isPendingDestroy;
	}

}

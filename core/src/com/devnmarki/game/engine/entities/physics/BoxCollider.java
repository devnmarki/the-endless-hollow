package com.devnmarki.game.engine.entities.physics;

import com.badlogic.gdx.physics.box2d.*;
import com.devnmarki.game.engine.Engine;
import com.devnmarki.game.engine.entities.Entity;
import com.devnmarki.game.engine.math.Vector2f;
import com.devnmarki.game.engine.math.Vector2i;

public class BoxCollider {

	private Entity entity;
	
	private Vector2i size;
	private Vector2f offset;
	private BodyDef.BodyType type = BodyDef.BodyType.StaticBody;
	
	private Body body;
	private Fixture fixture;
	
	public BoxCollider(Vector2i size) {
		this(size, Vector2f.ZERO);
	}
	
	public BoxCollider(Vector2i size, Vector2f offset) {
		this.size = size;
		this.offset = offset;
	}
	
	public void update() {
		if (body == null || entity == null) return;

		Vector2f bodyPosition = new Vector2f(
				body.getPosition().x * Engine.PPM - offset.x,
				body.getPosition().y * Engine.PPM - offset.y
		);

		entity.setPosition(bodyPosition);
	}
	
	private void createBody() {
		if (entity == null) return;
		
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = type;
		bodyDef.position.set((entity.getPosition().x + offset.x) / Engine.PPM, (entity.getPosition().y + offset.y) / Engine.PPM);
		bodyDef.fixedRotation = true;
		
		body = Engine.WORLD.createBody(bodyDef);
		
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(size.x / 2f / Engine.PPM, size.y / 2f / Engine.PPM);
		
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
        fixtureDef.density = 1f;
        fixtureDef.friction = 0f;
        fixtureDef.restitution = 0f;
        
        fixture = body.createFixture(fixtureDef);
        fixture.setUserData(this);
        body.setUserData(entity);
        
        shape.dispose();
	}

	public Filter createFilter(short category, short mask) {
		Filter filter = new Filter();
		filter.categoryBits = category;
		filter.maskBits = mask;
		return filter;
	}
	
	private void destroyBody() {
		if (body != null) {
			Engine.WORLD.destroyBody(body);
		}
	}

	public void setEntity(Entity entity) {
		this.entity = entity;
		if (body == null) {
			createBody();
		}
	}

	public void setSize(Vector2i size) {
		this.size = size;
		
		destroyBody();
		createBody();
	}

	public void setPosition(Vector2f position) {
		if (body != null) {
			body.setTransform(
					(position.x + offset.x) / Engine.PPM,
					(position.y + offset.y) / Engine.PPM,
					body.getAngle()
			);
		}
	}
	
	public void setOffset(Vector2f offset) {
		this.offset = offset;
	}
	
	public void setType(BodyDef.BodyType type) {
		this.type = type;
		
		destroyBody();
		createBody();
	}
	
	public Entity getEntity() {
		return this.entity;
	}
	
	public Body getBody() {
		return this.body;
	}

	public Fixture getFixture() {
		return fixture;
	}
	
}

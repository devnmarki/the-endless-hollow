package com.devnmarki.game.sandbox.characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.devnmarki.game.engine.AssetPool;
import com.devnmarki.game.engine.Engine;
import com.devnmarki.game.engine.entities.Entity;
import com.devnmarki.game.engine.entities.physics.BoxCollider;
import com.devnmarki.game.engine.entities.renderEntity.Spritesheet;
import com.devnmarki.game.engine.math.Vector2f;
import com.devnmarki.game.engine.math.Vector2i;

public class SorcererEntity extends Entity {

	private Spritesheet sheet;
	private BoxCollider collider;

	private static final float SPEED = 2f;
	private static final float JUMP_FORCE = 0.9f;

	private Vector2f velocity = Vector2f.ZERO;
	private boolean grounded = false;
	
	public SorcererEntity(Engine engine) {
		super(engine);
	}

	@Override
	public void onStart() {
		super.onStart();
		
		collider = new BoxCollider(new Vector2i(6, 7).mul((int)Engine.gameScale), new Vector2f(4f, -4.5f).mul(Engine.gameScale));
		collider.setType(BodyDef.BodyType.DynamicBody);
		this.addCollider(collider);
		
		sheet = new Spritesheet(AssetPool.getTexture("sprites/characters/player_sheet.png"), 2, 2, new Vector2i(8), false);
		getSpriteRenderer().setSprite(sheet.getSprite(0));
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		
		handleInputs();
		move();
	}
	
	private void handleInputs() {
		if (Gdx.input.isKeyPressed(Keys.LEFT)) {
			velocity.x = -1f;
		} else if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
			velocity.x = 1f;
		} else {
			velocity.x = 0f;
		}
		
		
		if (Gdx.input.isKeyPressed(Keys.Z) && grounded) {
			jump();
		}
	}
	
	private void move() {
		collider.getBody().setLinearVelocity(velocity.x * SPEED, collider.getBody().getLinearVelocity().y);
	}
	
	private void jump() {
		collider.getBody().applyLinearImpulse(0, JUMP_FORCE, collider.getBody().getWorldCenter().x, collider.getBody().getWorldCenter().y, true);
		grounded = false;
	}

	@Override
	public void onCollisionEnter(BoxCollider other, Vector2 normal) {
		super.onCollisionEnter(other, normal);	
		
		if (normal.y < 0) {
			grounded = true;
		}
   	}
	
}

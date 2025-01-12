package com.devnmarki.game.sandbox.characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Contact;
import com.devnmarki.game.engine.AssetPool;
import com.devnmarki.game.engine.Engine;
import com.devnmarki.game.engine.entities.Entity;
import com.devnmarki.game.engine.entities.physics.BoxCollider;
import com.devnmarki.game.engine.entities.renderEntity.Spritesheet;
import com.devnmarki.game.engine.entities.renderEntity.animations.Animation;
import com.devnmarki.game.engine.entities.renderEntity.animations.Animator;
import com.devnmarki.game.engine.math.Vector2f;
import com.devnmarki.game.engine.math.Vector2i;
import com.devnmarki.game.engine.ui.Image;
import com.devnmarki.game.engine.ui.UIComponent;
import com.devnmarki.game.sandbox.CollisionConstants;
import com.devnmarki.game.sandbox.Globals;
import com.devnmarki.game.sandbox.characters.enemies.Enemy;
import com.devnmarki.game.sandbox.objects.LaddersEntity;
import com.devnmarki.game.sandbox.objects.SorcererBulletEntity;

import java.util.ArrayList;
import java.util.List;

public class SorcererEntity extends Entity implements IDamageable {

	private Spritesheet sheet;
	private BoxCollider collider;
	private Animator animator;

	private static final float SPEED = 3f;
	private static final float JUMP_FORCE = 0.9f;
	private static final float CLIMB_SPEED = 1f;

	private Vector2f velocity = Vector2f.ZERO;
	private boolean grounded = false;
	private int facingDirection = 0;
	private float currentSpeed = SPEED;
	private boolean onLadders = false;

	private Vector2f shootPoint;
	private int hp = 3;
	private List<UIComponent> uiHp = new ArrayList<UIComponent>();
	
	public SorcererEntity(Engine engine) {
		super(engine);
	}

	@Override
	public void onStart() {
		super.onStart();

		this.tag = "player";
		this.name = "Sorcerer";

		collider = new BoxCollider(new Vector2i(6, 7).mul((int)Engine.gameScale), new Vector2f(4f, -4.5f).mul(Engine.gameScale));
		collider.setType(BodyDef.BodyType.DynamicBody);
		this.addCollider(collider);
		collider.getFixture().getFilterData().categoryBits = CollisionConstants.CATEGORY_SORCERER;
		collider.getFixture().getFilterData().maskBits = CollisionConstants.MASK_SORCERER;

		sheet = new Spritesheet(AssetPool.getTexture("sprites/characters/player_sheet.png"), 2, 2, new Vector2i(8), false);

		animator = new Animator(this);
		animator.addAnimation("idle_left", new Animation(sheet, new int[] { 0 }, 0.1f, true, false));
		animator.addAnimation("idle_right", new Animation(sheet, new int[] { 0 }, 0.1f, true, true));
		animator.addAnimation("walk_left", new Animation(sheet, new int[] { 2, 3 }, 0.2f, true, false));
		animator.addAnimation("walk_right", new Animation(sheet, new int[] { 2, 3 }, 0.2f, true, true));

		shootPoint = position;

		for (int i = 0; i < hp; i++) {
			Image hpImage = new Image(
					new Vector2f(32f + 40f * i, Gdx.graphics.getHeight() - 64f),
					new Vector2i(32),
					Globals.Assets.SHEET_OBJECTS_ATLAS.getSprite(12).getTexture()
			);

			engine.getCurrentState().addUIComponent(hpImage);
			uiHp.add(hpImage);
		}

	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		
		handleInputs();
		move();
		handleShootPoint();

		handleAnimations();

		animator.update();
		animator.render();
	}
	
	private void handleInputs() {
		if (onLadders) {
			if (Gdx.input.isKeyPressed(Keys.UP)) {
				collider.getBody().setLinearVelocity(0, CLIMB_SPEED);
			} else if (Gdx.input.isKeyPressed(Keys.DOWN)) {
				collider.getBody().setLinearVelocity(0, -CLIMB_SPEED);
			} else {
				collider.getBody().setLinearVelocity(0, 0);
			}
		}

		if (Gdx.input.isKeyPressed(Keys.LEFT)) {
			velocity.x = -1f;
			facingDirection = 0;
		} else if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
			velocity.x = 1f;
			facingDirection = 1;
		} else {
			velocity.x = 0f;
		}
		
		
		if (Gdx.input.isKeyPressed(Keys.Z) && grounded) {
			jump();
		}

		if (Gdx.input.isKeyJustPressed(Keys.X)) {
			shoot();
		}
	}
	
	private void move() {
		if (onLadders)
			currentSpeed = CLIMB_SPEED;
		else
			currentSpeed = SPEED;

		collider.getBody().setLinearVelocity(velocity.x * currentSpeed, collider.getBody().getLinearVelocity().y);
	}
	
	private void jump() {
		collider.getBody().applyLinearImpulse(0, JUMP_FORCE, collider.getBody().getWorldCenter().x, collider.getBody().getWorldCenter().y, true);
		grounded = false;
	}

	private void handleShootPoint() {
		if (facingDirection == 0) {
			shootPoint = new Vector2f(position.x - 45f, position.y);
		} else {
			shootPoint = new Vector2f(position.x + 45f, position.y);
		}
	}

	private void shoot() {
		SorcererBulletEntity bullet = new SorcererBulletEntity(engine, shootPoint, facingDirection);
		engine.getCurrentState().addEntity(bullet);
	}

	private void handleAnimations() {
		if (velocity.x == 0f) {
			switch (facingDirection) {
				case 0:
					animator.playAnimation("idle_left");
					break;
				case 1:
					animator.playAnimation("idle_right");
					break;
				default:
					break;
			}
		} else {
			switch (facingDirection) {
				case 0:
					animator.playAnimation("walk_left");
					break;
				case 1:
					animator.playAnimation("walk_right");
					break;
				default:
					break;
			}
		}
	}

	@Override
	public void onCollisionEnter(BoxCollider other, Vector2 normal, Contact contact) {
		super.onCollisionEnter(other, normal, contact);

		if (!(other.getEntity() instanceof Enemy)) {
			if (normal.y < 0) {
				grounded = true;
			}
		}

		if (other.getEntity() instanceof LaddersEntity) {
			onLadders = true;
			collider.getBody().setGravityScale(0f);
			collider.getBody().setLinearVelocity(0, 0f);
			grounded = false;
		}
   	}

	@Override
	public void onCollisionExit(BoxCollider other) {
		super.onCollisionExit(other);

		if (other.getEntity() instanceof LaddersEntity) {
			onLadders = false;
			collider.getBody().setGravityScale(1f);
		}
	}

	@Override
	public void onCollisionPreSolve(BoxCollider other, Contact contact) {
		super.onCollisionPreSolve(other, contact);

		short firstBit = contact.getFixtureA().getFilterData().categoryBits;
		short secondBit = contact.getFixtureB().getFilterData().categoryBits;

		if ((firstBit | secondBit) == (CollisionConstants.CATEGORY_SORCERER | CollisionConstants.CATEGORY_ENEMY)) {
			contact.setEnabled(false);
		}
	}

	@Override
	public void damage(int points) {
		hp -= points;

		engine.getCurrentState().removeUIComponent(uiHp.get(uiHp.size() - 1));
		uiHp.remove(uiHp.size() - 1);

		setHealthPoints(points);

		if (hp <= 0) {
			System.out.println("LoL loser ded");
		}
	}

	@Override
	public int getHealthPoints() {
		return hp;
	}

	@Override
	public void setHealthPoints(int hp) {
		this.hp = hp;
	}

	public List<UIComponent> getUiHp() {
		return uiHp;
	}
}

package com.devnmarki.game.sandbox.characters.enemies;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Contact;
import com.devnmarki.game.engine.AssetPool;
import com.devnmarki.game.engine.Engine;
import com.devnmarki.game.engine.entities.physics.BoxCollider;
import com.devnmarki.game.engine.entities.renderEntity.Spritesheet;
import com.devnmarki.game.engine.entities.renderEntity.animations.Animation;
import com.devnmarki.game.engine.math.Vector2f;
import com.devnmarki.game.engine.math.Vector2i;
import com.devnmarki.game.sandbox.CollisionConstants;
import com.devnmarki.game.sandbox.characters.SorcererEntity;

public class WatcherEnemy extends Enemy {

    private BoxCollider collider;
    private Spritesheet sheet;

    private static final float SPEED = 1f;

    private Vector2f velocity = new Vector2f(1f, -0.3f);
    private int facingDirection = 0;

    public WatcherEnemy(Engine engine) {
        super(engine);
    }

    @Override
    public void onStart() {
        super.onStart();

        this.name = "Watcher";

        collider = new BoxCollider(new Vector2i(7, 6).mul((int)Engine.gameScale), new Vector2f(3.5f, -5f).mul(Engine.gameScale));
        collider.setType(BodyDef.BodyType.DynamicBody);

        this.addCollider(collider);
        collider.getFixture().getFilterData().categoryBits = CollisionConstants.CATEGORY_ENEMY;
        collider.getFixture().getFilterData().maskBits = CollisionConstants.MASK_SORCERER;

        this.setCurrentState(Enemy.STATE_IDLE);

        sheet = new Spritesheet(AssetPool.getTexture("sprites/characters/watcher_sheet.png"), 2, 1, new Vector2i(8), false);

        this.getAnimator().addAnimation("left", new Animation(sheet, new int[] { 0 }, 0.1f, true, false));
        this.getAnimator().addAnimation("right", new Animation(sheet, new int[] { 1 }, 0.1f, true, false));
        this.getAnimator().playAnimation("left");
    }

    @Override
    public void onUpdate() {
        super.onUpdate();

        if (collider.getBody() != null) {
            collider.getBody().setGravityScale(0f);

            collider.getBody().setLinearVelocity(velocity.x * SPEED, velocity.y * SPEED);
        }

        if (velocity.x > 0 && facingDirection != 1) {
            facingDirection = 1;
            this.getAnimator().playAnimation("right");
        } else if (velocity.x < 0 && facingDirection != 0) {
            facingDirection = 0;
            this.getAnimator().playAnimation("left");
        }
    }

    @Override
    protected void onPatrolState() {
        super.onPatrolState();
    }

    @Override
    public void onCollisionEnter(BoxCollider other, Vector2 normal, Contact contact) {
        super.onCollisionEnter(other, normal, contact);

        if (other.getEntity() instanceof SorcererEntity) return;

        if (normal.x > 0) velocity.x = -1f;
        else if (normal.x < 0) velocity.x = 1f;

        if (normal.y != 0) velocity.y *= -1f;
    }

    @Override
    public void onCollisionPreSolve(BoxCollider other, Contact contact) {
        super.onCollisionPreSolve(other, contact);

        short firstBit = contact.getFixtureA().getFilterData().categoryBits;
        short secondBit = contact.getFixtureB().getFilterData().categoryBits;

        if ((firstBit | secondBit) == (CollisionConstants.CATEGORY_ENEMY | CollisionConstants.CATEGORY_SORCERER)) {
            System.out.println("Contact " + firstBit + " " + secondBit);
            contact.setEnabled(false);
        }
    }
}

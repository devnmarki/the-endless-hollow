package com.devnmarki.game.sandbox.characters.enemies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Contact;
import com.devnmarki.game.engine.Engine;
import com.devnmarki.game.engine.entities.Entity;
import com.devnmarki.game.engine.entities.physics.BoxCollider;
import com.devnmarki.game.engine.entities.physics.Collider;
import com.devnmarki.game.engine.entities.renderEntity.animations.Animator;
import com.devnmarki.game.engine.math.Vector2f;
import com.devnmarki.game.sandbox.characters.IDamageable;
import com.devnmarki.game.sandbox.characters.SorcererEntity;

public abstract class Enemy extends Entity implements IDamageable {

    public static final int STATE_IDLE = 0;
    public static final int STATE_PATROL = 1;

    private static final float DAMAGE_WAIT_TIME = 0.5f;

    private SorcererEntity sorcerer;

    private Animator animator;

    private int currentState = STATE_IDLE;

    private int hp;
    private int damage;

    private float damageTimer = 0f;

    public Enemy(Engine engine) {
        super(engine);
    }

    @Override
    public void onStart() {
        super.onStart();

        this.tag = "enemy";

        sorcerer = (SorcererEntity) engine.getCurrentState().findEntityWithTag("player");

        animator = new Animator(this);
    }

    @Override
    public void onUpdate() {
        super.onUpdate();

        switch (currentState) {
            case STATE_IDLE:
                onIdleState();
                break;
            case STATE_PATROL:
                onPatrolState();
                break;
            default:
                break;
        }

        animator.update();
        animator.render();
    }

    protected void onIdleState() {

    }

    protected void onPatrolState() {

    }

    @Override
    public void damage(int points) {
        hp -= points;

        setHealthPoints(hp);

        if (hp <= 0) {
            die();
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

    protected void die() {

    }

    public void knockback(Vector2f force) {

    }

    @Override
    public void onCollisionEnter(Collider other, Vector2 normal, Contact contact) {
        super.onCollisionEnter(other, normal, contact);

        damageTimer += Gdx.graphics.getDeltaTime();
        if (damageTimer >= DAMAGE_WAIT_TIME) {
            if (other.getEntity() instanceof SorcererEntity) {
                IDamageable sorcererDamageable = (IDamageable) other.getEntity();
                sorcererDamageable.damage(getDamage());
            }

            damageTimer = 0f;
        }
    }

    @Override
    public void onCollisionExit(Collider other) {
        super.onCollisionExit(other);

        damageTimer = DAMAGE_WAIT_TIME;
    }

    public void setCurrentState(int state) {
        this.currentState = state;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public SorcererEntity getSorcerer() {
        return sorcerer;
    }

    public Animator getAnimator() {
        return animator;
    }

    public int getCurrentState() {
        return currentState;
    }

    public int getDamage() {
        return damage;
    }
}

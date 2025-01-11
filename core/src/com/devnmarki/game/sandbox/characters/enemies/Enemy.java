package com.devnmarki.game.sandbox.characters.enemies;

import com.devnmarki.game.engine.Engine;
import com.devnmarki.game.engine.entities.Entity;
import com.devnmarki.game.engine.entities.renderEntity.animations.Animator;
import com.devnmarki.game.sandbox.characters.IDamageable;
import com.devnmarki.game.sandbox.characters.SorcererEntity;

public abstract class Enemy extends Entity implements IDamageable {

    public static final int STATE_IDLE = 0;
    public static final int STATE_PATROL = 1;

    private SorcererEntity sorcerer;

    private Animator animator;

    private int currentState = STATE_IDLE;

    private int hp;

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

    public void setCurrentState(int state) {
        this.currentState = state;
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
}

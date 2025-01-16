package com.devnmarki.game.engine.entities.physics;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.devnmarki.game.engine.entities.Entity;
import com.devnmarki.game.engine.math.Vector2f;

public abstract class Collider {

    protected Entity entity;

    protected Body body;
    protected BodyDef.BodyType type = BodyDef.BodyType.StaticBody;
    protected Fixture fixture;

    public abstract void update();

    public abstract void setEntity(Entity entity);
    public abstract void setPosition(Vector2f position);
    public abstract void setType(BodyDef.BodyType type);

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

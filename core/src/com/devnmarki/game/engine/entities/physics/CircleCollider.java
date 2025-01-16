package com.devnmarki.game.engine.entities.physics;

import com.badlogic.gdx.physics.box2d.*;
import com.devnmarki.game.engine.Engine;
import com.devnmarki.game.engine.entities.Entity;
import com.devnmarki.game.engine.math.Vector2f;
import com.devnmarki.game.engine.math.Vector2i;

public class CircleCollider extends Collider {

    private float radius;
    private Vector2f offset;

    public CircleCollider(float radius, Vector2f offset) {
        this.radius = radius * Engine.gameScale;
        this.offset = offset;
    }

    public CircleCollider(float radius) {
        this(radius * Engine.gameScale, Vector2f.ZERO);
    }

    @Override
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
        bodyDef.position.set((entity.getPosition().x + offset.x) / Engine.PPM,
                (entity.getPosition().y + offset.y) / Engine.PPM);
        bodyDef.fixedRotation = true;

        bodyDef.bullet = (type == BodyDef.BodyType.DynamicBody);

        body = Engine.WORLD.createBody(bodyDef);

        CircleShape shape = new CircleShape();
        shape.setRadius(radius / Engine.PPM);

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

    @Override
    public void setEntity(Entity entity) {
        this.entity = entity;
        if (body == null) {
            createBody();
        }
    }

    @Override
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

    @Override
    public void setType(BodyDef.BodyType type) {
        this.type = type;

        destroyBody();
        createBody();
    }

}

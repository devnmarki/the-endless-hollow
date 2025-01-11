package com.devnmarki.game.sandbox.objects;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.devnmarki.game.engine.Engine;
import com.devnmarki.game.engine.entities.Entity;
import com.devnmarki.game.engine.entities.physics.BoxCollider;
import com.devnmarki.game.engine.math.Vector2f;
import com.devnmarki.game.engine.math.Vector2i;

public class LaddersEntity extends Entity {

    private BoxCollider collider;

    public LaddersEntity(Engine engine) {
        super(engine);

        this.tag = "object";
        this.name = "Ladders";
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onUpdate() {
        super.onUpdate();

        if (getColliders().isEmpty()) {
            Vector2f colliderSize = new Vector2f(getMapObject().getProperties().get("width", Float.class), getMapObject().getProperties().get("height", Float.class)).mul(Engine.gameScale);
            Vector2f colliderPos = new Vector2f(getMapObject().getProperties().get("x", Float.class), getMapObject().getProperties().get("y", Float.class)).mul(Engine.gameScale);

            collider = new BoxCollider(new Vector2i((int) colliderSize.x, (int) colliderSize.y), new Vector2f(colliderSize.x / 2f, colliderSize.y / 2f));
            collider.setPosition(colliderPos);
            this.addCollider(collider);

            collider.getFixture().setSensor(true);
        }
    }
}

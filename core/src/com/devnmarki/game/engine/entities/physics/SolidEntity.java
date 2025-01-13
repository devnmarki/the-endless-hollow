package com.devnmarki.game.engine.entities.physics;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.devnmarki.game.engine.Engine;
import com.devnmarki.game.engine.entities.Entity;
import com.devnmarki.game.engine.math.Vector2f;
import com.devnmarki.game.engine.math.Vector2i;
import com.devnmarki.game.sandbox.CollisionConstants;

public class SolidEntity extends Entity {

    private Vector2i size;

    public SolidEntity(Engine engine, Vector2f position, Vector2i size) {
        super(engine, position);

        this.size = size;

        this.tag = "solid";
        this.name = "Solid Object";

        BoxCollider collider = new BoxCollider(new Vector2i(size.x, size.y));
        this.addCollider(collider);
        collider.setType(BodyDef.BodyType.StaticBody);
        collider.createFilter(CollisionConstants.CATEGORY_WALL, CollisionConstants.MASK_WALL);
    }
}

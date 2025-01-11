package com.devnmarki.game.sandbox.objects;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Contact;
import com.devnmarki.game.engine.Engine;
import com.devnmarki.game.engine.entities.Entity;
import com.devnmarki.game.engine.entities.physics.BoxCollider;
import com.devnmarki.game.engine.entities.physics.ICollidable;
import com.devnmarki.game.engine.entities.renderEntity.Sprite;
import com.devnmarki.game.engine.math.Vector2f;
import com.devnmarki.game.engine.math.Vector2i;
import com.devnmarki.game.sandbox.CollisionConstants;
import com.devnmarki.game.sandbox.Globals;
import com.devnmarki.game.sandbox.characters.IDamageable;
import com.devnmarki.game.sandbox.characters.SorcererEntity;
import com.devnmarki.game.sandbox.characters.enemies.Enemy;

public class SorcererBulletEntity extends Entity {

    private BoxCollider collider;

    private static final float SPEED = 7f;
    private static final int DAMAGE = 1;

    private int shootDirection;
    private float velocityX;

    public SorcererBulletEntity(Engine engine, Vector2f initialPos, int shootDirection) {
        super(engine, initialPos);

        this.shootDirection = shootDirection;
    }

    @Override
    public void onStart() {
        super.onStart();

        this.tag = "object";
        this.name = "Bullet";

        collider = new BoxCollider(new Vector2i(5, 3).mul((int)Engine.gameScale), new Vector2f(4.5f, -4.5f).mul(Engine.gameScale));
        collider.setType(BodyDef.BodyType.DynamicBody);
        this.addCollider(collider);
    }

    @Override
    public void onUpdate() {
        super.onUpdate();

        Sprite originalSprite = Globals.Assets.SHEET_OBJECTS_ATLAS.getSprite(2);
        Sprite flippedSprite = Globals.Assets.SHEET_OBJECTS_ATLAS.getSprite(3);

        if (shootDirection == 0) {
            this.getSpriteRenderer().setSprite(originalSprite);
            velocityX = -1;
        } else {
            this.getSpriteRenderer().setSprite(flippedSprite);
            velocityX = 1;
        }

        collider.getBody().setGravityScale(0f);

        collider.getBody().setLinearVelocity(velocityX * SPEED, 0f);
    }

    @Override
    public void onCollisionEnter(BoxCollider other, Vector2 normal, Contact contact) {
        super.onCollisionEnter(other, normal, contact);

        if (other.getEntity() instanceof Enemy) {
            Enemy enemy = (Enemy) other.getEntity();
            if (enemy instanceof IDamageable) {
                ((IDamageable) enemy).damage(DAMAGE);
            }
        }

        if (other.getEntity() instanceof SorcererEntity) return;

        this.markForDestroy();
    }
}

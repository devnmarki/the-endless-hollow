package com.devnmarki.game.sandbox.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Contact;
import com.devnmarki.game.engine.Engine;
import com.devnmarki.game.engine.entities.Entity;
import com.devnmarki.game.engine.entities.physics.BoxCollider;
import com.devnmarki.game.engine.entities.physics.Collider;
import com.devnmarki.game.engine.math.Vector2f;
import com.devnmarki.game.engine.math.Vector2i;
import com.devnmarki.game.sandbox.Globals;
import com.devnmarki.game.sandbox.characters.IDamageable;
import com.devnmarki.game.sandbox.characters.SorcererEntity;

public class SpikesEntity extends Entity {

    private static final float DAMAGE_WAIT_TIME = 1f;

    private BoxCollider collider;

    private float damageTimer = DAMAGE_WAIT_TIME;

    public SpikesEntity(Engine engine) {
        super(engine);

        this.tag = "object";
        this.name = "Spikes";
    }

    @Override
    public void onStart() {
        super.onStart();

        collider = new BoxCollider(new Vector2i(8, 3).mul((int)Engine.gameScale), new Vector2f(4f, -6.5f).mul(Engine.gameScale));
        this.addCollider(collider);
        collider.getFixture().setSensor(true);

        this.getSpriteRenderer().setSprite(Globals.Assets.SHEET_OBJECTS_ATLAS.getSprite(9));
    }

    @Override
    public void onCollisionEnter(Collider other, Vector2 normal, Contact contact) {
        super.onCollisionEnter(other, normal, contact);

        damageTimer += Gdx.graphics.getDeltaTime();
        if (damageTimer >= DAMAGE_WAIT_TIME) {
            if (other.getEntity() instanceof SorcererEntity) {
                IDamageable sorcererDamageable = (IDamageable) other.getEntity();
                sorcererDamageable.damage(1);
            }

            damageTimer = 0f;
        }
    }

    @Override
    public void onCollisionExit(Collider other) {
        super.onCollisionExit(other);

        damageTimer = DAMAGE_WAIT_TIME;
    }
}

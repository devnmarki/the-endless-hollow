package com.devnmarki.game.sandbox.objects;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Contact;
import com.devnmarki.game.engine.Engine;
import com.devnmarki.game.engine.entities.Entity;
import com.devnmarki.game.engine.entities.physics.BoxCollider;
import com.devnmarki.game.engine.math.Vector2f;
import com.devnmarki.game.engine.math.Vector2i;
import com.devnmarki.game.sandbox.Globals;
import com.devnmarki.game.sandbox.characters.SorcererEntity;

public class CobwebEntity extends Entity {

    private BoxCollider collider;

    private static final float SLOWNESS = 0.2f;

    public CobwebEntity(Engine engine) {
        super(engine);

        this.tag = "object";
        this.name = "Cobweb";
    }

    @Override
    public void onStart() {
        super.onStart();

        getSpriteRenderer().setSprite(Globals.Assets.SHEET_OBJECTS_ATLAS.getSprite(0));

        collider = new BoxCollider(new Vector2i(8, 8).mul((int)Engine.gameScale), new Vector2f(4f, -4f).mul(Engine.gameScale));
        this.addCollider(collider);
        collider.getFixture().setSensor(true);
    }

    @Override
    public void onCollisionEnter(BoxCollider other, Vector2 normal, Contact contact) {
        super.onCollisionEnter(other, normal, contact);

        if (other.getEntity() instanceof SorcererEntity) {
            SorcererEntity sorcererEntity = (SorcererEntity) other.getEntity();
            if (!sorcererEntity.isSlowed()) {
                sorcererEntity.setIsSlowed(true);
                sorcererEntity.setCurrentSpeed(sorcererEntity.getCurrentSpeed() * SLOWNESS);
            }
        }
    }

    @Override
    public void onCollisionExit(BoxCollider other) {
        super.onCollisionExit(other);

        if (other.getEntity() instanceof SorcererEntity) {
            SorcererEntity sorcererEntity = (SorcererEntity) other.getEntity();
            sorcererEntity.setIsSlowed(false);
            sorcererEntity.setCurrentSpeed(SorcererEntity.SPEED);
        }
    }
}

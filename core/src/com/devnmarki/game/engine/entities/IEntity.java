package com.devnmarki.game.engine.entities;

import com.devnmarki.game.engine.entities.physics.BoxCollider;
import com.devnmarki.game.engine.entities.physics.Collider;
import com.devnmarki.game.engine.entities.physics.ICollidable;

public interface IEntity extends ICollidable {

    void addCollider(Collider collider);
}

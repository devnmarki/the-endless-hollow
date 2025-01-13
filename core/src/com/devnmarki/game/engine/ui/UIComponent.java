package com.devnmarki.game.engine.ui;

import com.devnmarki.game.engine.math.Vector2f;
import com.devnmarki.game.engine.math.Vector2i;

public abstract class UIComponent {

    protected Vector2f position;
    protected Vector2i size;

    public UIComponent(Vector2f position, Vector2i size) {
        this.position = position;
        this.size = size;

        build();
    }

    public UIComponent(Vector2f position) {
        this(position, Vector2i.ZERO);
    }

    public abstract void build();
    public abstract void render();

    public void setPosition(Vector2f position) {
        this.position = position;
    }

    public void setX(float x) {
        this.position.x = x;
    }

    public void setY(float y) {
        this.position.y = y;
    }

    public Vector2f getPosition() {
        return position;
    }

    public Vector2i getSize() {
        return size;
    }

}

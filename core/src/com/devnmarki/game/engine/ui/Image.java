package com.devnmarki.game.engine.ui;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.devnmarki.game.engine.Engine;
import com.devnmarki.game.engine.math.Vector2f;
import com.devnmarki.game.engine.math.Vector2i;

public class Image extends UIComponent {

    private TextureRegion image;

    public Image(Vector2f position, Vector2i size, TextureRegion image) {
        super(position, size);

        this.image = image;
    }

    @Override
    public void build() {

    }

    @Override
    public void render() {
        Engine.UI_BATCH.draw(image, position.x, position.y, size.x, size.y);
    }

}

package com.devnmarki.game.sandbox;

import com.devnmarki.game.engine.AssetPool;
import com.devnmarki.game.engine.entities.renderEntity.Spritesheet;
import com.devnmarki.game.engine.math.Vector2i;

public class Globals {

    public static class Assets {

        public static final Spritesheet SHEET_OBJECTS_ATLAS = new Spritesheet(AssetPool.getTexture("sprites/objects/objects_atlas.png"), 3, 3, new Vector2i(8), false);

    }

}

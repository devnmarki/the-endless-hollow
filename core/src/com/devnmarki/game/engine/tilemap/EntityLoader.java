package com.devnmarki.game.engine.tilemap;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.devnmarki.game.engine.Engine;
import com.devnmarki.game.engine.entities.Entity;
import com.devnmarki.game.engine.entities.physics.BoxCollider;
import com.devnmarki.game.engine.math.Vector2f;

import java.io.Console;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class EntityLoader {

    private Engine engine;
    private static final Map<String, Supplier<Entity>> entities = new HashMap<String, Supplier<Entity>>();

    public EntityLoader(Engine engine) {
        this.engine = engine;
    }

    public static void addEntityToLoad(String name, Supplier<Entity> entity) {
        entities.put(name, entity);
    }

    public void loadGameObjects(Tilemap map, String layerName) {
        MapLayer entitiesLayer = map.getTiledMap().getLayers().get(layerName);

        if (entitiesLayer != null) {
            for (MapObject obj : entitiesLayer.getObjects()) {
                String objName = obj.getName();

                Supplier<Entity> entityFactory = entities.get(objName);
                if (entityFactory != null) {
                    Entity entity = entityFactory.get();

                    float x = obj.getProperties().get("x", Float.class) * Engine.gameScale;
                    float y = obj.getProperties().get("y", Float.class) * Engine.gameScale;
                    entity.setPosition(new Vector2f(x, y));

                    if (obj.getProperties().containsKey("rotation")) {
                        float rotation = obj.getProperties().get("rotation", Float.class);
                        entity.setRotation(rotation);
                    }

                    engine.getCurrentState().addEntity(entity);

                    System.out.println("Loaded entity at position X: " + x + ", Y: " + y);
                }
            }
        }
    }

}

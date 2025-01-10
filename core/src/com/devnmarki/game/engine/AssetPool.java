package com.devnmarki.game.engine;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

public class AssetPool {

	private static Map<String, TextureRegion> textures = new HashMap<String, TextureRegion>();
	private static Map<String, TiledMap> tilemaps = new HashMap<String, TiledMap>();
	
	public static TextureRegion getTexture(String resourceName) {
		if (AssetPool.textures.containsKey(resourceName)) {
			return AssetPool.textures.get(resourceName);
		} else {
			TextureRegion texture = new TextureRegion(new Texture(Gdx.files.internal(resourceName)));
			AssetPool.textures.put(resourceName, texture);
			return texture;
		}
	}
	
	public static TiledMap getTilemap(String resourceName) {
		if (AssetPool.textures.containsKey(resourceName)) {
			return AssetPool.tilemaps.get(resourceName);
		} else {
			TiledMap tilemap = new TmxMapLoader().load(resourceName);
			return tilemap;
		}
	}
	
}

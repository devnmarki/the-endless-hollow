package com.devnmarki.game.engine.entities.renderEntity;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.devnmarki.game.engine.math.Vector2i;

public class Spritesheet {

	private TextureRegion texture;
	private int cols;
	private int rows;
	private Vector2i spriteSize;
	private boolean flip;
	
	private List<Sprite> sprites = new ArrayList<Sprite>();
	
	public Spritesheet(TextureRegion texture, int cols, int rows, Vector2i spriteSize, boolean flip) {
		this.texture = texture;
		this.cols = cols;
		this.rows = rows;
		this.spriteSize = spriteSize;
		this.flip = flip;
		
		this.generateSprites();
	}
	
	private void generateSprites() {
		TextureRegion[][] textures = texture.split(spriteSize.x, spriteSize.y);
		
		for (int i = 0; i < textures.length; i++) {
			for (int j = 0; j < textures[i].length; j++) {
				Sprite sprite = new Sprite(textures[i][j]);
				sprite.setFlip(flip);
				sprites.add(sprite);
			}
		}
	}
	
	public Sprite getSprite(int index) {
		return sprites.get(index);
	}

	public List<Sprite> getSprites() {
		return sprites;
	}

	public TextureRegion getTexture() {
		return texture;
	}
	
}

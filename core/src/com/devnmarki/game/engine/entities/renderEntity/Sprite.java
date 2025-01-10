package com.devnmarki.game.engine.entities.renderEntity;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Sprite {

	private TextureRegion texture;
	private boolean flip;
	
	public Sprite(TextureRegion texture) {
		this.texture = texture;
		this.flip = false;
	}
	
	public Sprite(TextureRegion texture, boolean flip) {
		this.texture = texture;
		this.flip = flip;
	}
	
	public TextureRegion getTexture() {
		return this.texture;
	}
	
	public void setFlip(boolean flip) {
		texture.flip(false, false);
		if (this.flip != flip) {
			texture.flip(true, false);
			this.flip = flip;
		}
	}
	
	public boolean getFlip() {
		return this.flip;
	}
	
}

package com.devnmarki.game.engine.entities.renderEntity;

import com.devnmarki.game.engine.Engine;
import com.devnmarki.game.engine.entities.Entity;

public class SpriteRenderer {

	private Entity entity;
	private Sprite sprite;
	private float scale;
	
	public SpriteRenderer(Entity entity) {
		this.entity = entity;
		this.sprite = null;
	}
	
	public SpriteRenderer(Entity entity, Sprite sprite) {
		this.entity = entity;
		this.sprite = sprite;
	}
	
	public void render() {
		Engine.SPRITE_BATCH.draw(
				sprite.getTexture(), 
				entity.getPosition().x, 
				entity.getPosition().y, 
				0, 0, 
				sprite.getTexture().getRegionWidth(), 
				sprite.getTexture().getRegionHeight(), 
				scale, scale, 
				entity.getRotation() - 90, 
				false
				);
	}
	
	public void setScale(float scale) {
		this.scale = scale;
	}
	
	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}
	
	public Sprite getSprite() {
		return this.sprite;
	}
	
	public float getScale() {
		return this.scale;
	}
	
}

package com.devnmarki.game.engine.math;

public class Vector2f {

	public float x, y;
	
	public static final Vector2f ZERO = new Vector2f(0.0f);
	public static final Vector2f ONE = new Vector2f(1.0f);
	public static final Vector2f HALF = new Vector2f(0.5f);
	public static final Vector2f UNIT_X = new Vector2f(1.0f, 0.0f);
	public static final Vector2f UNIT_Y = new Vector2f(0.0f, 1.0f);
	
	public Vector2f() {
		this.x = 0.0f;
		this.y = 0.0f;
	}
	
	public Vector2f(float value) {
		this.x = value;
		this.y = value;
	}
	
	public Vector2f(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public static float distance(Vector2f current, Vector2f other) {
		return (float) Math.sqrt(Math.pow(current.x - other.x, 2) + Math.pow(current.y - other.y, 2));
	}

	public String toString() {
		return "X: " + this.x + " Y: " + this.y;
	}
	
	public Vector2f add(Vector2f vec) {
		float newX = this.x + vec.x;
		float newY = this.y + vec.y;
		
		return new Vector2f(newX, newY);
	}
	
	public Vector2f sub(Vector2f vec) {
		float newX = this.x - vec.x;
		float newY = this.y - vec.y;
		
		return new Vector2f(newX, newY);
	}
	
	public Vector2f mul(float value) {
		float newX = this.x * value;
		float newY = this.y * value;
		
		return new Vector2f(newX, newY);
	}
	
}

package com.devnmarki.game.engine.math;

public class Vector2i {
	
	public int x, y;
	
	public static final Vector2i ZERO = new Vector2i(0);
	public static final Vector2i ONE = new Vector2i(1);
	public static final Vector2i UNIT_X = new Vector2i(1, 0);
	public static final Vector2i UNIT_Y = new Vector2i(0, 1);
	
	public Vector2i() {
		this.x = 0;
		this.y = 0;
	}
	
	public Vector2i(int value) {
		this.x = value;
		this.y = value;
	}
	
	public Vector2i(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Vector2i add(Vector2i vec) {
		int newX = this.x + vec.x;
		int newY = this.y + vec.y;
		
		return new Vector2i(newX, newY);
	}
	
	public Vector2i sub(Vector2i vec) {
		int newX = this.x - vec.x;
		int newY = this.y - vec.y;
		
		return new Vector2i(newX, newY);
	}
	
	public Vector2i mul(Vector2i vec) {
		int newX = this.x * vec.x;
		int newY = this.y * vec.y;
		
		return new Vector2i(newX, newY);
	}
	
	public Vector2i mul(int value) {
		int newX = this.x * value;
		int newY = this.y * value;
		
		return new Vector2i(newX, newY);
	}
	
	public String toString() {
		return "X: " + this.x + " Y: " + this.y;
	}
	
}

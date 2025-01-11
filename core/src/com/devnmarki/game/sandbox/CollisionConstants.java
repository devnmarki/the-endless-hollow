package com.devnmarki.game.sandbox;

public class CollisionConstants {

    public static final short CATEGORY_SORCERER = 0x0001; // Category for the player
    public static final short CATEGORY_ENEMY = 0x0002;    // Category for the enemy
    public static final short CATEGORY_WALL = 0x0004;     // Category for walls

    // Masks define what each category can collide with
    public static final short MASK_SORCERER = CATEGORY_WALL | CATEGORY_ENEMY | CATEGORY_SORCERER; // Sorcerer collides with walls and enemies
    public static final short MASK_WALL = CATEGORY_SORCERER | CATEGORY_ENEMY | CATEGORY_WALL; // Wall collides with everything


}

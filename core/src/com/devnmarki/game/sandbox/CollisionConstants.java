package com.devnmarki.game.sandbox;

public class CollisionConstants {

    public static final short CATEGORY_SORCERER = 0x0001;
    public static final short CATEGORY_ENEMY = 0x0002;
    public static final short CATEGORY_WALL = 0x0004;
    public static final short CATEGORY_OBJECT = 0x0008;

    public static final short MASK_SORCERER = CATEGORY_ENEMY | CATEGORY_SORCERER | CATEGORY_OBJECT;
    public static final short MASK_WALL = CATEGORY_SORCERER | CATEGORY_ENEMY | CATEGORY_OBJECT;
    public static final short MASK_OBJECT = CATEGORY_ENEMY | CATEGORY_WALL;


}

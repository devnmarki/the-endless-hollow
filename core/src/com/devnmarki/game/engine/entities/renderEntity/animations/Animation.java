package com.devnmarki.game.engine.entities.renderEntity.animations;

import com.badlogic.gdx.Gdx;
import com.devnmarki.game.engine.entities.renderEntity.Spritesheet;

public class Animation {

    private final Spritesheet sheet;    // Spritesheet containing the frames
    private final int[] frames;        // Indices of frames in the animation
    private final float frameDuration; // Duration of each frame
    private final boolean loop;        // Should the animation loop?
    private final boolean flip;        // Should the frames be flipped?

    private int currentFrameIndex = 0; // Index of the current frame
    private float elapsedTime = 0f;    // Time elapsed for the current frame

    public Animation(Spritesheet sheet, int[] frames, float frameDuration, boolean loop, boolean flip) {
        this.sheet = sheet;
        this.frames = frames;
        this.frameDuration = frameDuration;
        this.loop = loop;
        this.flip = flip;
    }

    // Advances the animation based on delta time
    public void update() {
        elapsedTime += Gdx.graphics.getDeltaTime();
        if (elapsedTime >= frameDuration) {
            elapsedTime = 0f;
            currentFrameIndex++;
            if (currentFrameIndex >= frames.length) {
                if (loop) {
                    currentFrameIndex = 0;
                } else {
                    currentFrameIndex = frames.length - 1; // Stay on the last frame
                }
            }
        }
    }

    // Resets the animation to the first frame
    public void reset() {
        currentFrameIndex = 0;
        elapsedTime = 0f;
    }

    // Checks if the animation has finished playing (non-looping animations only)
    public boolean hasFinished() {
        return !loop && currentFrameIndex == frames.length - 1 && elapsedTime >= frameDuration;
    }

    // Retrieves the spritesheet
    public Spritesheet getSheet() {
        return sheet;
    }

    // Retrieves the current frame index
    public int getCurrentFrame() {
        return frames[currentFrameIndex];
    }

    // Getters
    public float getFrameDuration() {
        return frameDuration;
    }

    public boolean isLoop() {
        return loop;
    }

    public boolean isFlip() {
        return flip;
    }
}

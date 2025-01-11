package com.devnmarki.game.engine.entities.renderEntity.animations;

import com.devnmarki.game.engine.entities.Entity;
import java.util.HashMap;
import java.util.Map;

public class Animator {

    private final Entity entity;                       // The entity this animator belongs to
    private final Map<String, Animation> animations;  // Map of animation names to Animation objects
    private Animation currentAnimation;               // The currently playing animation

    public Animator(Entity entity) {
        this.entity = entity;
        this.animations = new HashMap<>();
    }

    // Adds an animation to the animator
    public void addAnimation(String name, Animation animation) {
        animations.put(name, animation);
    }

    // Plays a new animation by name
    public void playAnimation(String name) {
        Animation newAnimation = animations.get(name);
        if (newAnimation != null && currentAnimation != newAnimation) {
            currentAnimation = newAnimation;
            currentAnimation.reset();
        }
    }

    // Updates the current animation
    public void update() {
        if (currentAnimation != null) {
            currentAnimation.update();
        }
    }

    // Renders the current frame of the animation
    public void render() {
        if (currentAnimation != null) {
            int frameIndex = currentAnimation.getCurrentFrame();
            currentAnimation.getSheet().getSprite(frameIndex).setFlip(currentAnimation.isFlip());
            entity.getSpriteRenderer().setSprite(currentAnimation.getSheet().getSprite(frameIndex));
        }
    }

    // Checks if the current animation has finished
    public boolean isCurrentAnimationFinished() {
        return currentAnimation != null && currentAnimation.hasFinished();
    }

    // Retrieves the current animation
    public Animation getCurrentAnimation() {
        return currentAnimation;
    }
}

/**
 * @author Roei Mesilaty
 * @version 6.0 (current version number of program)
 * @since 2023-06-09
 * @StuID 315253336
 */
package Animations;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * Represents an animation that can be stopped by a key press.
 * It wraps another animation and checks for the specified key press to stop the animation.
 */
public class KeyPressStoppableAnimation implements Animation {
    private final KeyboardSensor sensor;
    private final String key;
    private final Animation animation;
    private boolean isAlreadyPressed;
    private boolean stop;

    /**
     * Constructs a KeyPressStoppableAnimation.
     *
     * @param sensor    The keyboard sensor to check for key press.
     * @param key       The key to stop the animation.
     * @param animation The animation to wrap and control.
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.sensor = sensor;
        this.key = key;
        this.animation = animation;
        this.isAlreadyPressed = true;
        this.stop = false;
    }

    /**
     * Performs one frame of the wrapped animation.
     * If the specified key is pressed, the animation will stop.
     *
     * @param d The DrawSurface to draw the animation on.
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        // Perform one frame of the wrapped animation
        this.animation.doOneFrame(d);

        // Check if the specified key is pressed
        if (this.sensor.isPressed(this.key)) {
            // If the key was already pressed, return and do not stop the animation
            if (isAlreadyPressed) {
                return;
            }

            // Set the stop flag to true to stop the animation
            this.stop = true;
        } else {
            // Update the isAlreadyPressed flag
            isAlreadyPressed = false;
        }
    }

    /**
     * Checks if the animation should stop.
     * The animation will stop if the specified key was pressed.
     *
     * @return true if the animation should stop, false otherwise.
     */
    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
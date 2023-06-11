/**
 * @author Roei Mesilaty
 * @version 6.0 (current version number of program)
 * @since 2023-06-09
 * @StuID 315253336
 */
package Animations;

import biuoop.DrawSurface;

/**
 * Represents a pause screen animation that is displayed when the game is paused.
 * It shows a message to press the space key to continue.
 */
public class PauseScreen implements Animation {
    private static final int TEXT_SIZE = 32;

    /**
     * Performs one frame of the pause screen animation.
     *
     * @param d The DrawSurface to draw the animation on.
     */
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", TEXT_SIZE);
    }

    /**
     * Checks if the pause screen animation should stop.
     *
     * @return true if the animation should stop, false otherwise.
     */
    public boolean shouldStop() {
        return false;
    }
}
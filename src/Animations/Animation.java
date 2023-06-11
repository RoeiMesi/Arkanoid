/**
 * @author Roei Mesilaty
 * @version 6.0 (current version number of program)
 * @since 2023-06-09
 * @StuID 315253336
 */
package Animations;

import biuoop.DrawSurface;
/**
 * The Animation interface represents an animation in the game.
 * It defines methods for performing one frame of the animation
 * and determining whether the animation should stop.
 */
public interface Animation {

    /**
     * Performs one frame of the animation.
     *
     * @param d the DrawSurface to draw on
     */
    void doOneFrame(DrawSurface d);

    /**
     * Checks if the animation should stop.
     *
     * @return true if the animation should stop, false otherwise
     */
    boolean shouldStop();
}
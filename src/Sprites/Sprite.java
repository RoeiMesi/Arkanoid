/**
 * @author Roei Mesilaty
 * @version 6.0 (current version number of program)
 * @since 2023-06-09
 * @StuID 315253336
 */
package Sprites;

import biuoop.DrawSurface;
/**
 * An interface for a sprite, which can be drawn to the screen and
 * notified of the passage of time.
 */
public interface Sprite {
    /**
     * Draws the sprite onto the given DrawSurface.
     *
     * @param d the DrawSurface on which to draw the sprite.
     */
    void drawOn(DrawSurface d);

    /**
     * Notifies the sprite that a unit of time has passed.
     */
    void timePassed();
}
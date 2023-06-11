/**
 * @author Roei Mesilaty
 * @version 6.0 (current version number of program)
 * @since 2023-06-09
 * @StuID 315253336
 */
package Sprites;

import biuoop.DrawSurface;

/**
 * The AbstractSprite class is an implementation of the Sprite interface.
 * It provides empty implementations for the drawOn() and timePassed() methods,
 * which can be overridden by subclasses.
 */
public class AbstractSprite implements Sprite {
    @Override
    public void drawOn(DrawSurface d) {
        // Empty implementation
    }

    @Override
    public void timePassed() {
        // Empty implementation
    }
}
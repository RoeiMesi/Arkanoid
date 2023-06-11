/**
 * @author Roei Mesilaty
 * @version 6.0 (current version number of program)
 * @since 2023-06-09
 * @StuID 315253336
 */
package Sprites;

import biuoop.DrawSurface;
import java.util.LinkedList;
import java.util.List;
/**
 * A collection of sprites that can be added to and modified.
 */
public class SpriteCollection {
    private final LinkedList<Sprite> sprites;

    /**
     * Constructs a new, empty SpriteCollection.
     */
    public SpriteCollection() {
        this.sprites = new LinkedList<>();
    }

    /**
     * Adds the given sprite to the collection.
     * @param s the sprite to add to the collection
     */
    public void addSprite(Sprite s) {
        sprites.add(s);
    }
    /**
     * Calls timePassed() on all sprites in the collection.
     */
    public void notifyAllTimePassed() {
        List<Sprite> spritesCopy = new LinkedList<>(this.sprites);
        for (Sprite sprite: spritesCopy) {
            sprite.timePassed();
        }
    }

    /**
     * Calls drawOn(d) on all sprites in the collection.
     * @param d the DrawSurface on which to draw the sprites
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite sprite: sprites) {
            sprite.drawOn(d);
        }
    }
    /**
     * Removes the specified sprite object from the collection of sprites.
     *
     * @param s the sprite object to be removed
     */
    public void removeSprite(Sprite s) {
        sprites.remove(s);
    }
}
/**
 * @author Roei Mesilaty
 * @version 6.0 (current version number of program)
 * @since 2023-06-09
 * @StuID 315253336
 */
package Sprites;
import Base.Ball;
import Logic.Collidable;
import Logic.GameLevel;
import Logic.HitListener;
import Logic.HitNotifier;
import biuoop.DrawSurface;
import Base.Rectangle;
import Base.Point;
import Base.Velocity;

import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * The Block class represents a collidable and sprite block.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private final Rectangle block;
    private final List<HitListener> hitListeners = new LinkedList<>();

    /**
     * Construct a block with given upper left point, height and width.
     *
     * @param upperLeft the upper left point of the block
     * @param height    the height of the block
     * @param width     the width of the block
     * @param color     the color of the block.
     * @throws RuntimeException if the given upperLeft point is null
     */
    public Block(Point upperLeft, double height, double width, Color color) {
        if (upperLeft == null) {
            throw new RuntimeException("Error at creating block because "
                    + "point given is null!");
        }
        this.block = new Rectangle(upperLeft, width, height);
        this.block.setColor(color);
    }

    /**
     * brighten the block color.
     */
    public void darken() {
        this.block.setColor(this.block.getColor().darker());
    }

    /**
     * Sets the color of the rectangle.
     *
     * @param color the color to set
     */
    public void setColor(Color color) {
        this.block.setColor(color);
    }

    /**
     * Return the collision rectangle of the block.
     *
     * @return the collision rectangle of the block
     */
    public Rectangle getCollisionRectangle() {
        return this.block;
    }

    /**
     * Do nothing on time passed.
     */
    public void timePassed() {
    }
    /**
     * Draws the block on the given draw surface.
     *
     * @param d the draw surface to draw the block on.
     */
    public void drawOn(DrawSurface d) {
        this.block.drawOn(d);
    }

    /**
     * Adds the block to the game by adding it to the game's sprite and
     * collidable lists.
     *
     * @param g the game to add the block to.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
    /**
     * Calculates a new velocity after a hit occurred on this block.
     * @param collisionPoint the point of collision with the block
     * @param currentVelocity the current velocity of the object colliding
     *                        with the block
     * @param hitter the ball that hits the collidable.
     * @return the new velocity after the hit
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        if (collisionPoint == null || currentVelocity == null) {
            return null;
        }
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();
        if (this.block.getRight().isOnTheLine(collisionPoint)) {
            dx = Math.abs(dx);
        }
        if (this.block.getLeft().isOnTheLine(collisionPoint)) {
            dx = (-1) * Math.abs(dx);
        }
        if (this.block.getBottom().isOnTheLine(collisionPoint)) {
            dy = Math.abs(dy);
        }
        if (this.block.getTop().isOnTheLine(collisionPoint)) {
            dy = (-1) * Math.abs(dy);
        }
        this.notifyHit(hitter);
        return new Velocity(dx, dy);
    }
    /**
     * Removes the sprite from the specified game and also removes it as a collidable.
     *
     * @param game the game from which the sprite will be removed
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
        game.removeCollidable(this);
    }

    /**
     * Adds a hit listener to the sprite.
     *
     * @param hl the hit listener to be added
     */
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    /**
     * Removes a hit listener from the sprite.
     *
     * @param hl the hit listener to be removed
     */
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * Notifies all registered hit listeners about a hit event with the specified ball.
     *
     * @param hitter the ball that caused the hit event
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }
}

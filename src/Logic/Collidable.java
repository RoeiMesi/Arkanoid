/**
 * @author Roei Mesilaty
 * @version 6.0 (current version number of program)
 * @since 2023-06-09
 * @StuID 315253336
 */
package Logic;

import Base.Ball;
import Base.Rectangle;
import Base.Velocity;
import Base.Point;
/**
 * This interface represents a collidable object in the game.
 */
public interface Collidable {
    /**
     * Returns the collision rectangle of this collidable object.
     *
     * @return the collision rectangle of this collidable object
     */
    Rectangle getCollisionRectangle();

    /**
     * This method is called when a collision occurs with this collidable
     * object.
     * It receives the collision point and the current velocity of the
     * colliding object, and returns a new velocity.
     *
     * @param collisionPoint the point of collision
     * @param currentVelocity the current velocity of the colliding object
     * @param hitter the ball that hits the collidable
     * @return the new velocity after the collision
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}
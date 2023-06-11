/**
 * @author Roei Mesilaty
 * @version 6.0 (current version number of program)
 * @since 2023-06-09
 * @StuID 315253336
 */
package Logic;

import Base.Point;
/**
 * Information about a collision between a collidable object and a line.
 */
public class CollisionInfo {
    // the point at which the collision occurs.
    private final Collidable collisionObject;
    private final Point collisionPoint;

    /**
     * Creates a new CollisionInfo object with the given collision point
     * and collidable object.
     *
     * @param collisionPoint the point at which the collision occurs.
     * @param collisionObject the collidable object involved in the collision.
     * @throws RuntimeException if either argument is null.
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        if (collisionPoint == null || collisionObject == null) {
            throw new RuntimeException("Error at assigning collision info.");
        }
        this.collisionObject = collisionObject;
        this.collisionPoint = collisionPoint;
    }

    /**
     * Returns the point at which the collision occurs.
     *
     * @return the point at which the collision occurs.
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * Returns the collidable object involved in the collision.
     *
     * @return the collidable object involved in the collision.
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }
}
/**
 * @author Roei Mesilaty
 * @version 6.0 (current version number of program)
 * @since 2023-06-09
 * @StuID 315253336
 */
package Logic;

import Base.Line;
import Base.Point;
import java.util.LinkedList;
/**
 * This class represents the environment in which the game is played.
 * It holds a collection of collidables, as well as two gaming balls.
 */
public class GameEnvironment {

    // A linked list of collidables
    private final LinkedList<Collidable> collidables;

    /**
     * Constructs a new game environment with an empty list of collidables.
     */
    public GameEnvironment() {
        this.collidables = new LinkedList<>();
    }

    /**
     * Adds the given collidable to the environment.
     *
     * @param c the collidable to add to the environment
     */
    public void addCollidable(Collidable c) {
        // If the collidable is null, return without adding it to the list
        if (c == null) {
            return;
        }
        // Add the collidable to the list of collidables
        this.collidables.add(c);
    }
    /**
     * Removes the specified collidable object from the environment.
     *
     * @param c the collidable object to be removed
     */
    public void removeCollidable(Collidable c) {
        this.collidables.remove(c);
    }
    /**
     * Gets the list of the collidables.
     *
     * @return the list of collidables.
     */
    public LinkedList<Collidable> getCollidables() {
        return collidables;
    }
    /**
     * Returns information about the closest collision between a given
     * trajectory and the collidable objects.
     *
     * @param trajectory the trajectory to check for collisions
     *                   collidable objects.
     * @return the information about the closest collision between the given
     * trajectory and the collidable objects, or null if there is no collision.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        // if there are no collidable objects, return null.
        if (this.collidables.isEmpty()) {
            return null;
        }
        // Set the initial minimum length to the length of the trajectory.
        double minLength = trajectory.length();
        // initialize the collision point to null.
        Point collisionPoint = null;
        // initialize the collidable object to null.
        Collidable collisionObject = null;

        // loop through all the collidable objects to find the closest
        // collision point and collidable object.
        for (Collidable col : collidables) {
            Point curCollision
                    = trajectory.closestIntersectionToStartOfLine(col.getCollisionRectangle());
            // if there is a collision point, check if it is closer than the
            // current closest collision point.
            if (curCollision != null) {
                if (trajectory.start().distance(curCollision) <= minLength) {
                    minLength = trajectory.start().distance(curCollision);
                    collisionPoint = curCollision;
                    collisionObject = col;
                }
            }
        }
        // if there is a collision, return the collision information.
        if (collisionPoint != null) {
            return new CollisionInfo(collisionPoint, collisionObject);
        }
        return null; // if there is no collision, return null.
    }
}
/**
 * @author Roei Mesilaty
 * @version 6.0 (current version number of program)
 * @since 2023-06-09
 * @StuID 315253336
 */
package Logic;

import Base.Ball;
import Sprites.Block;

/**
 * The HitListener interface represents an object that listens for hit events.
 */
public interface HitListener {
    /**
     * This method is called whenever the specified block is hit by a ball.
     *
     * @param beingHit the block that was hit
     * @param hitter   the ball that hit the block
     */
    void hitEvent(Block beingHit, Ball hitter);
}
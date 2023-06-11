/**
 * @author Roei Mesilaty
 * @version 6.0 (current version number of program)
 * @since 2023-06-09
 * @StuID 315253336
 */
package Logic;

import Base.Ball;
import Base.Counter;
import Sprites.Block;

/**
 * The ScoreTrackingListener class implements the HitListener interface
 * and keeps track of the score by increasing it whenever a block is hit.
 */
public class ScoreTrackingListener implements HitListener {
    private final Counter currentScore;
    /**
     * Constructs a ScoreTrackingListener object with the specified score counter.
     *
     * @param scoreCounter the counter to track the score
     */

    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }
    /**
     * Updates the score by increasing it when a block is hit, and removes the
     * current listener from the beingHit block.
     *
     * @param beingHit the block being hit
     * @param hitter   the ball that hits the block
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeHitListener(this);
        this.currentScore.increase(5);
    }
}
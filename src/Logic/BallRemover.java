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
 * The BallRemover class implements the HitListener interface and is responsible for removing balls that have
 * fallen down from the game.
 */
public class BallRemover implements HitListener {
    private final GameLevel game;
    private final Counter removedBalls;

    /**
     * Constructs a BallRemover with the specified game and counter for removed balls.
     *
     * @param game         the game from which balls will be removed
     * @param removedBalls the counter to track the number of removed balls
     */
    public BallRemover(GameLevel game, Counter removedBalls) {
        this.game = game;
        this.removedBalls = removedBalls;
    }

    /**
     * Handles the hit event when a ball hits a block. Removes the ball from the game and increases the count of
     * removed balls.
     * @param beingHit the block being hit
     * @param hitter   the ball that hit the block
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(game);
        this.removedBalls.increase(1);
    }
}

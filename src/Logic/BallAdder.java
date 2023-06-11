/**
 * @author Roei Mesilaty
 * @version 6.0 (current version number of program)
 * @since 2023-06-09
 * @StuID 315253336
 */
package Logic;
import Base.Ball;
import Base.Counter;
import Base.Point;
import Base.Velocity;
import Sprites.Block;

import java.awt.Color;

/**
 * The BallAdder class implements the HitListener interface and is responsible for adding new balls when a block is hit.
 */
public class BallAdder implements HitListener {
    private final GameLevel game;
    private final Counter addedBalls;

    /**
     * Constructs a BallAdder with the specified game and counter for added balls.
     *
     * @param game       the game to which new balls will be added
     * @param addedBalls the counter to track the number of added balls
     */
    public BallAdder(GameLevel game, Counter addedBalls) {
        this.game = game;
        this.addedBalls = addedBalls;
    }
    /**
     * Handles the hit event when a block is hit by a ball. Adds a new ball to the game, sets its initial position,
     * velocity, and adjusts its bounds. Also increases the count of added balls.
     *
     * @param beingHit the block being hit
     * @param hitter   the ball that hit the block
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        Ball newBall = new Ball(new Point(40, 560), 5, new Color(0, 255, 155));
        newBall.setVelocity(Velocity.fromAngleAndSpeed(30, 5));
        newBall.adjustBounds(25, 25, 750, 550);
        newBall.addToGame(game);
        this.addedBalls.increase(1);
    }
}

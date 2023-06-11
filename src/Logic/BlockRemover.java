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
 * The BlockRemover class implements the HitListener interface and is responsible for removing blocks that
 * have been hit from the game.
 */
public class BlockRemover implements HitListener {
    private final GameLevel game;
    private final Counter removedBlocks;

    /**
     * Constructs a BlockRemover with the specified game and counter for removed blocks.
     *
     * @param game          the game from which blocks will be removed
     * @param removedBlocks the counter to track the number of removed blocks
     */
    public BlockRemover(GameLevel game, Counter removedBlocks) {
        this.game = game;
        this.removedBlocks = removedBlocks;
    }

    /**
     * Handles the hit event when a block is hit by a ball. Removes the block from the game and increases the
     * count of removed blocks.
     * Also removes this listener from the block being removed from the game.
     *
     * @param beingHit the block being hit
     * @param hitter   the ball that hit the block
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeHitListener(this);
        beingHit.removeFromGame(game);
        this.removedBlocks.increase(1);
    }
}
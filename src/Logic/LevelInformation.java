/**
 * @author Roei Mesilaty
 * @version 6.0 (current version number of program)
 * @since 2023-06-09
 * @StuID 315253336
 */
package Logic;

import Base.Velocity;
import Sprites.Block;
import Sprites.Sprite;
import java.util.List;
import java.awt.Color;

/**
 * The LevelInformation interface represents the information for a single level in the game.
 * It provides methods to retrieve various properties of the level such as the number of balls,
 * initial ball velocities, ball colors, paddle speed, paddle width, level name, background sprite,
 * blocks, and the number of blocks to be removed.
 */
public interface LevelInformation {
    /**
     * Returns the number of balls in the level.
     *
     * @return the number of balls
     */
    int numberOfBalls();

    /**
     * Returns a list of initial velocities for the balls in the level.
     * The size of the list should be equal to the number of balls.
     *
     * @return the list of initial ball velocities
     */
    List<Velocity> initialBallVelocities();

    /**
     * Returns a list of colors for the balls in the level.
     * The size of the list should be equal to the number of balls.
     *
     * @return the list of ball colors
     */
    List<Color> ballColors();

    /**
     * Returns the speed of the paddle in the level.
     *
     * @return the paddle speed
     */
    int paddleSpeed();

    /**
     * Returns the width of the paddle in the level.
     *
     * @return the paddle width
     */
    int paddleWidth();

    /**
     * Returns the name of the level.
     * The level name will be displayed at the top of the screen.
     *
     * @return the level name
     */
    String levelName();

    /**
     * Returns the background sprite for the level.
     *
     * @return the background sprite
     */
    Sprite getBackground();

    /**
     * Returns a list of blocks that make up the level.
     *
     * @return the list of blocks
     */
    List<Block> blocks();

    /**
     * Returns the number of blocks that should be removed before the level is considered cleared.
     * This number should be less than or equal to the total number of blocks in the level.
     *
     * @return the number of blocks to remove
     */
    int numberOfBlocksToRemove();
}
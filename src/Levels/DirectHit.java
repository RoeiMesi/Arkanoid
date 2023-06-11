/**
 * @author Roei Mesilaty
 * @version 6.0 (current version number of program)
 * @since 2023-06-09
 * @StuID 315253336
 */
package Levels;

import Base.Velocity;
import Sprites.Block;
import Logic.LevelInformation;
import Sprites.DirectHitBackground;
import Sprites.Sprite;
import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import Base.Point;

/**
 * The DirectHit class represents the level information for the "Direct Hit" level.
 * It implements the LevelInformation interface.
 */
public class DirectHit implements LevelInformation {
    private static final int NUMBER_OF_BALLS = 1;
    private static final int NUMBER_OF_BLOCKS = 1;
    private static final int PADDLE_SPEED = 10;
    private static final int PADDLE_WIDTH = 80;

    /**
     * Returns a list of colors for the balls in the level.
     *
     * @return a list of ball colors
     */
    @Override
    public List<Color> ballColors() {
        List<Color> ballColors = new LinkedList<>();
        ballColors.add(Color.WHITE);
        return ballColors;
    }

    /**
     * Returns the number of balls in the level.
     *
     * @return the number of balls
     */
    @Override
    public int numberOfBalls() {
        return NUMBER_OF_BALLS;
    }

    /**
     * Returns a list of initial velocities for the balls in the level.
     *
     * @return a list of ball velocities
     */
    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> ballVels = new LinkedList<>();
        Velocity velocity = Velocity.fromAngleAndSpeed(360, 7);
        ballVels.add(velocity);
        return ballVels;
    }

    /**
     * Returns the speed of the paddle in pixels per frame.
     *
     * @return the paddle speed
     */
    @Override
    public int paddleSpeed() {
        return PADDLE_SPEED;
    }

    /**
     * Returns the width of the paddle in pixels.
     *
     * @return the paddle width
     */
    @Override
    public int paddleWidth() {
        return PADDLE_WIDTH;
    }

    /**
     * Returns the name of the level.
     *
     * @return the level name
     */
    @Override
    public String levelName() {
        return "Direct Hit";
    }

    /**
     * Returns the background sprite for the level.
     *
     * @return the background sprite
     */
    @Override
    public Sprite getBackground() {
        return new DirectHitBackground();
    }

    /**
     * Returns a list of blocks in the level.
     *
     * @return a list of blocks
     */
    @Override
    public List<Block> blocks() {
        List<Block> collisionBlocks = new ArrayList<>();
        Point upperLeft = new Point(386, 146);
        collisionBlocks.add(new Block(upperLeft, 30, 30, Color.RED));
        return collisionBlocks;
    }

    /**
     * Returns the number of blocks to be removed in the level.
     *
     * @return the number of blocks to remove
     */
    @Override
    public int numberOfBlocksToRemove() {
        return NUMBER_OF_BLOCKS;
    }
}
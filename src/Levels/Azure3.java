/**
 * @author Roei Mesilaty
 * @version 6.0 (current version number of program)
 * @since 2023-06-09
 * @StuID 315253336
 */
package Levels;

import Base.Point;
import Base.Velocity;
import Sprites.Block;
import Logic.LevelInformation;
import Sprites.Azure3Background;
import Sprites.Sprite;
import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
/**
 * The Spongebob class represents the level information for the "Spongebob Squarepants" level.
 * It implements the LevelInformation interface.
 */
public class Azure3 implements LevelInformation {
    private static final int NUMBER_OF_BALLS = 3;
    private static final int NUMBER_OF_BLOCKS = 57;
    private static final int PADDLE_SPEED = 10;
    private static final int PADDLE_WIDTH = 120;
    private static final int BALL_SPEED = 5;
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
        List<Velocity> ballVel = new LinkedList<>();
        ballVel.add(Velocity.fromAngleAndSpeed(300, BALL_SPEED));
        ballVel.add(Velocity.fromAngleAndSpeed(320, BALL_SPEED));
        ballVel.add(Velocity.fromAngleAndSpeed(340, BALL_SPEED));
        return ballVel;
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
     * Returns a list of colors for the balls in the level.
     *
     * @return a list of ball colors
     */
    @Override
    public List<Color> ballColors() {
        List<Color> ballColors = new LinkedList<>();
        ballColors.add(Color.BLACK);
        ballColors.add(Color.RED);
        ballColors.add(Color.CYAN);
        return ballColors;
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
        return "Azure 3";
    }

    /**
     * Returns the background sprite for the level.
     *
     * @return the background sprite
     */
    @Override
    public Sprite getBackground() {
        return new Azure3Background();
    }

    @Override
    public List<Block> blocks() {
        List<Block> blockList = new ArrayList<>();
        for (int i = 0, j = 170; i < 12; i++) {
            Block block = new Block(new Point(j, 140), 25, 50, Color.GRAY);
            blockList.add(block);
            j += 50;
        }
        for (int i = 0, j = 220; i < 11; i++) {
            Block block = new Block(new Point(j, 165), 25, 50, Color.RED);
            blockList.add(block);
            j += 50;
        }
        for (int i = 0, j = 270; i < 10; i++) {
            Block block = new Block(new Point(j, 190), 25, 50, Color.YELLOW);
            blockList.add(block);
            j += 50;
            // This block will be a special block that will add a ball when being hit!
        }
        for (int i = 0, j = 320; i < 9; i++) {
            Block block = new Block(new Point(j, 215), 25, 50, Color.BLUE);
            blockList.add(block);
            j += 50;
        }
        for (int i = 0, j = 370; i < 8; i++) {
            Block block = new Block(new Point(j, 240), 25, 50, Color.PINK);
            blockList.add(block);
            j += 50;
        }
        for (int i = 0, j = 420; i < 7; i++) {
            Block block = new Block(new Point(j, 265), 25, 50, Color.GREEN);
            blockList.add(block);
            j += 50;
        }
        return blockList;
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

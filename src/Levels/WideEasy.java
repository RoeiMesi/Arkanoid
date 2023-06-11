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
import Sprites.Sprite;
import Sprites.WideEasyBackground;
import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * The WideEasy class represents the level information for the "Wide Easy" level.
 * It implements the LevelInformation interface.
 */
public class WideEasy implements LevelInformation {
    private static final int NUMBER_OF_BALLS = 10;
    private static final int NUMBER_OF_BLOCKS = 15;
    private static final int PADDLE_SPEED = 5;
    private static final int PADDLE_WIDTH = 680;

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
        int angle = 300;
        for (int i = 0; i < 10; i++) {
            if (i == 5) {
                angle = 20;
            }
            Velocity velocity = Velocity.fromAngleAndSpeed(angle, 5);
            angle += 10;
            ballVels.add(velocity);
        }
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
        return "Wide Easy";
    }

    /**
     * Returns the background sprite for the level.
     *
     * @return the background sprite
     */
    @Override
    public Sprite getBackground() {
        return new WideEasyBackground();
    }

    /**
     * Returns a list of colors for the balls in the level.
     *
     * @return a list of ball colors
     */
    @Override
    public List<Color> ballColors() {
        List<Color> colors = new ArrayList<>();
        colors.add(Color.RED);
        colors.add(Color.BLACK);
        colors.add(Color.BLUE);
        colors.add(Color.GREEN);
        colors.add(Color.YELLOW);
        colors.add(Color.CYAN);
        return colors;
    }

    /**
     * Returns a list of blocks in the level.
     *
     * @return a list of blocks
     */
    @Override
    public List<Block> blocks() {
        List<Block> blocks = new LinkedList<>();
        int width = 49;
        int height = 20;
        blocks.add(new Block(new Point(30, 200), height, width, Color.RED));
        blocks.add(new Block(new Point(79, 200), height, width, Color.RED));
        blocks.add(new Block(new Point(128, 200), height, width, Color.ORANGE));
        blocks.add(new Block(new Point(177, 200), height, width, Color.ORANGE));
        blocks.add(new Block(new Point(226, 200), height, width, Color.YELLOW));
        blocks.add(new Block(new Point(275, 200), height, width, Color.YELLOW));
        blocks.add(new Block(new Point(324, 200), height, width, Color.GREEN));
        blocks.add(new Block(new Point(373, 200), height, width + 5, Color.GREEN));
        blocks.add(new Block(new Point(427, 200), height, width, Color.GREEN));
        blocks.add(new Block(new Point(476, 200), height, width, Color.BLUE));
        blocks.add(new Block(new Point(525, 200), height, width, Color.BLUE));
        blocks.add(new Block(new Point(574, 200), height, width, Color.PINK));
        blocks.add(new Block(new Point(623, 200), height, width, Color.PINK));
        blocks.add(new Block(new Point(672, 200), height, width, Color.CYAN));
        blocks.add(new Block(new Point(721, 200), height, width, Color.CYAN));
        return blocks;
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

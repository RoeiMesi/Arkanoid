/**
 * @author Roei Mesilaty
 * @version 6.0 (current version number of program)
 * @since 2023-06-09
 * @StuID 315253336
 */
package Logic;

import Animations.Animation;
import Animations.AnimationRunner;
import Animations.PauseScreen;
import Animations.CountdownAnimation;
import Animations.KeyPressStoppableAnimation;
import Base.Paddle;
import Base.Counter;
import Base.Ball;
import Base.Point;
import Base.Velocity;
import Sprites.Block;
import Sprites.ScoreIndicator;
import Sprites.Sprite;
import Sprites.SpriteCollection;
import biuoop.KeyboardSensor;
import biuoop.DrawSurface;
import java.awt.Color;
import java.util.List;
import java.util.Random;

/**
 * This class is responsible for running the game.
 */
public class GameLevel implements Animation {
    private boolean isWon;
    private Paddle paddle;
    private final LevelInformation levelInfo;
    private final KeyboardSensor keyboard;
    private final AnimationRunner runner;
    private boolean running;
    private final SpriteCollection sprites = new SpriteCollection();
    private final GameEnvironment environment = new GameEnvironment();
    private final Counter removedBlocks = new Counter();
    private final Counter removedBalls = new Counter();
    private final Counter addedBalls = new Counter();
    private final BallAdder ballAdder = new BallAdder(this, addedBalls);
    private final BlockRemover blockRemover = new BlockRemover(this, removedBlocks);
    private final BallRemover ballRemover = new BallRemover(this, removedBalls);
    private final Counter score;
    private final ScoreTrackingListener scoreCounter;
    private final ScoreIndicator scoreIndicator;
    private final List<Color> colors;
    private static final int HUNDRED = 100;

    /**
     * Instantiates a new Game level.
     *
     * @param levelInfo the level info
     * @param keyboard  the keyboard
     * @param runner    the runner
     * @param score     the score
     */
    public GameLevel(LevelInformation levelInfo, KeyboardSensor keyboard, AnimationRunner runner, Counter score) {
        this.levelInfo = levelInfo;
        this.keyboard = keyboard;
        this.runner = runner;
        this.score = score;
        scoreCounter = new ScoreTrackingListener(score);
        scoreIndicator = new ScoreIndicator(score);
        colors = levelInfo.ballColors();
    }

    /**
     * Returns the game environment associated with this game.
     *
     * @return the game environment
     */
    public GameEnvironment getEnvironment() {
        return this.environment;
    }

    /**
     * Adds a collidable object to the game environment.
     *
     * @param c the collidable object to add
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }

    /**
     * Adds a sprite object to the game.
     *
     * @param s the sprite object to add
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    /**
     * Initialize the game by creating and adding all game objects to the game.
     *
     * @return the boolean
     */
    public boolean gameResult() {
        return isWon;
    }

    /**
     * Creates background blocks for the game.
     * The background blocks include left, top, bottom, and right walls
     * to enclose the game area.
     * Additionally, the bottom wall has a special behavior: it removes the ball that hits it.
     */
    public void createBackgroundBlocks() {
        // Create the left wall block
        Block leftWall = new Block(new Point(0, 30), 570, 30, Color.GRAY);

        // Create the top wall block
        Block topWall = new Block(new Point(0, 0), 30, 800, Color.GRAY);

        // Create the bottom wall block
        Block bottomWall = new Block(new Point(30, 570), 30, 740, Color.GRAY);

        // Create the right wall block
        Block rightWall = new Block(new Point(770, 30), 570, 30, Color.GRAY);

        // Array to hold the main blocks
        Block[] mainBlocks = new Block[]{
                leftWall, topWall, bottomWall, rightWall
        };

        // Add the main blocks to the game
        for (Block block : mainBlocks) {
            block.addToGame(this);
        }

        // Add a hit listener to the bottom wall block to remove the ball that hits it
        bottomWall.addHitListener(ballRemover);
    }

    /**
     * Creates balls on top of the paddle.
     * The number of balls and their initial velocities are determined by the level information.
     * Each ball is assigned a random color.
     * The balls are added to the game and their bounds are adjusted to ensure they stay within the game area.
     * Additional logic can be implemented to handle the created balls, such as adding them to the game or updating
     * the ball count.
     */
    private void createBallsOnTopOfPaddle() {
        // Calculate the starting position for the balls on top of the paddle
        int x = this.paddle.getX() + (int) (this.paddle.getWidth() / 2);
        int y = this.paddle.getY() - (3 * 5);

        // Create balls based on the initial ball velocities from the level information
        for (Velocity velocity : levelInfo.initialBallVelocities()) {
            // Generate a random color for each ball
            Random random = new Random();
            Color randomColor = colors.get(random.nextInt(colors.size()));

            // Create a ball with the calculated center, radius, and color
            Point center = new Point(x, y);
            Ball ball = new Ball(center, 5, randomColor);

            // Set the ball's velocity
            ball.setVelocity(velocity);

            // Add the ball to the game
            ball.addToGame(this);

            // Adjust the ball's bounds to ensure it stays within the game area
            ball.adjustBounds(25, 25, 750, 550);
        }
    }

    /**
     * Create the gaming blocks.
     */
    public void createGamingBlocks() {
        for (Block block: levelInfo.blocks()) {
            block.addToGame(this);
            block.addHitListener(blockRemover);
            block.addHitListener(scoreCounter);
            Random random = new Random();
            // This makes random blocks in the game "special blocks" that if you hit them they add a new ball.
            if (random.nextInt(levelInfo.blocks().size()) == 0) {
                // Gives that specific block a darker color.
                block.darken();
                block.addHitListener(ballAdder);
            }
        }
    }

    /**
     * Adds the background to the game.
     * The background is obtained from the level information and added to the game's sprite collection.
     */
    private void addBackground() {
        // Add the background sprite to the game's sprite collection
        this.sprites.addSprite(this.levelInfo.getBackground());
    }

    /**
     * Removes the specified collidable object from the environment.
     *
     * @param c the collidable object to be removed
     */
    public void removeCollidable(Collidable c) {
        environment.removeCollidable(c);
    }

    /**
     * Removes the specified sprite object from the collection of sprites.
     *
     * @param s the sprite object to be removed
     */
    public void removeSprite(Sprite s) {
        sprites.removeSprite(s);
    }

    /**
     * Initialize the game objects and the graphical user interface and add
     * them to the game.
     */
    public void initialize() {
        // Initialize the GUI, paddle, gaming blocks and gaming balls.
        addBackground();
        createBackgroundBlocks();
        Paddle paddle = new Paddle(400 - (levelInfo.paddleWidth() / 2), 550, levelInfo.paddleWidth(), 20,
                this.keyboard, this.levelInfo.paddleSpeed());
        paddle.addToGame(this);
        this.paddle = paddle;
        createBallsOnTopOfPaddle();
        createGamingBlocks();
        scoreIndicator.addToGame(this);
    }
    /**
     * Performs one frame of the game.
     * Handles the game logic for each frame, including checking for pause key press,
     * updating sprites, drawing sprites on the draw surface, displaying the level name,
     * and checking for game completion conditions.
     *
     * @param d The DrawSurface to draw the game on.
     */
    public void doOneFrame(DrawSurface d) {
        // Check if the pause key is pressed
        if (keyboard.isPressed("p")) {
            // Display the pause screen animation
            Animation pauseScreen = new PauseScreen();
            runner.run(new KeyPressStoppableAnimation(keyboard, KeyboardSensor.SPACE_KEY, pauseScreen));
            runner.run(new CountdownAnimation(2, 3, sprites, levelInfo.levelName()));
        }

        // Notify all sprites that one frame has passed
        sprites.notifyAllTimePassed();

        // Draw all sprites on the draw surface
        sprites.drawAllOn(d);

        // Display the level name
        d.setColor(new Color(50, 7, 98));
        d.drawText(350, 22, "Level Name: " + levelInfo.levelName(), 20);

        // Check if the number of removed blocks equals the required number of blocks to remove
        if (removedBlocks.getValue() == levelInfo.numberOfBlocksToRemove()) {
            // Game is won
            isWon = true;
            score.increase(HUNDRED);
            running = false;
            // Check if the difference between the number of removed balls and added balls equals the number
            // of balls in the level.
        }   else if (removedBalls.getValue() - addedBalls.getValue() == levelInfo.numberOfBalls()) {
            // Game is lost
            isWon = false;
            running = false;
        }
    }

    /**
     * Checks if the game should stop.
     * The game should stop if the "running" flag is false.
     *
     * @return true if the game should stop, false otherwise.
     */
    public boolean shouldStop() {
        return !running;
    }

    /**
     * Runs the game.
     * Starts the game by displaying a countdown animation and then continuously runs the game.
     * The game is considered running when the "running" flag is set to true.
     */
    public void run() {
        // Display a countdown animation before starting the game
        this.runner.run(new CountdownAnimation(2, 3, sprites, levelInfo.levelName()));

        // Set the "running" flag to true
        this.running = true;

        // Continuously run the game until the "running" flag is false
        this.runner.run(this);
    }

}
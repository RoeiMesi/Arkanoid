/**
 * @author Roei Mesilaty
 * @version 6.0 (current version number of program)
 * @since 2023-06-09
 * @StuID 315253336
 */
package Base;

import Sprites.Sprite;
import Logic.Collidable;
import biuoop.DrawSurface;
import Logic.GameLevel;
import biuoop.KeyboardSensor;
import java.awt.Color;
/**
 * Represents a paddle in a game. Implements the Sprite and Collidable interfaces.
 */
public class Paddle implements Sprite, Collidable {
    private final double speed;
    // The keyboard sensor used to detect user input
    private final KeyboardSensor keyboard;
    // create a new Color object with RGB values of bronze
    private final Color color = new Color(218, 165, 32);
    // The x-coordinate of the top-left corner of the paddle
    private int x;
    // The y-coordinate of the top-left corner of the paddle
    private final int y;
    // The width of the paddle
    private final double width;
    // The height of the paddle
    private final double height;
    // The collision rectangle of the paddle
    private final Rectangle collisionRectangle;
    // The first region line of the paddle
    private Line reg1;
    // The second region line of the paddle
    private Line reg2;
    // The third region line of the paddle
    private Line reg3;
    // The fourth region line of the paddle
    private Line reg4;
    // The fifth region line of the paddle
    private Line reg5;
    /**
     * Constructs a new paddle with the specified parameters.
     *
     * @param x         the x-coordinate of the top-left corner of the paddle
     * @param y         the y-coordinate of the top-left corner of the paddle
     * @param width     the width of the paddle
     * @param height    the height of the paddle
     * @param keyboard  the keyboard sensor used to detect user input
     * @param speed     the speed of the paddle
     */
    public Paddle(int x, int y, int width, int height,
                  KeyboardSensor keyboard, double speed) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.keyboard = keyboard;
        this.collisionRectangle = new Rectangle(new Point(x, y), width, height);
        this.speed = speed;
        adjustRegions(); // adjust the region lines of the paddle
    }

    /**
     * Gets the X axis of the paddle.
     * @return the X axis of the paddle.
     */
    public int getX() {
        return this.x;
    }

    /**
     * Gets the Y axis of the paddle.
     * @return the Y axis of the paddle.
     */
    public int getY() {
        return this.y;
    }

    /**
     * Gets the width of the paddle.
     * @return the paddle's width.
     */
    public double getWidth() {
        return this.width;
    }
    /**
     * Moves the paddle to the left by 10 pixels if possible.
     */
    public void moveLeft() {
        if (x <= 30) { // If the paddle is already at the leftmost position
            return; // do nothing
        }
        // Move the paddle left according to its speed.
        x -= speed;
        // Update the collision rectangle of the paddle
        collisionRectangle.setRectangle(x, y);
        adjustRegions(); // Adjust the region lines of the paddle
    }

    /**
     * Moves the paddle to the right by 10 pixels if possible.
     */
    public void moveRight() {
        if (x >= 770 - width) { // If the paddle is already at the rightmost position
            return; // do nothing
        }
        // Move the paddle right by 10 pixels
        x += speed;
        // Update the collision rectangle of the paddle
        collisionRectangle.setRectangle(x, y);
        adjustRegions(); // Adjust the region lines of the paddle
    }
    /**
     * Moves the paddle left or right based on keyboard input.
     * If the left arrow key is pressed, the paddle moves left by 10 units.
     * If the right arrow key is pressed, the paddle moves right by 10 units.
     */
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        } else if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }

    /**
     * Draws the paddle on a given DrawSurface object.
     * The paddle is filled with a bronze color, and then outlined with black.
     *
     * @param d the DrawSurface on which to draw the paddle
     */
    public void drawOn(DrawSurface d) {
        // set the DrawSurface color to the bronze color
        d.setColor(this.color);
        // fill a rectangle at the paddle's current position and size
        d.fillRectangle(x, y, (int) width, (int) height);
        d.setColor(Color.BLACK); // set the DrawSurface color to black.
        d.drawRectangle(x, y, (int) width, (int) height);
    }

    /**
     * Adjusts the regions of the paddle to detect collisions with other
     * objects.
     * The paddle is divided into five regions (reg1-reg5) of equal width,
     * and each region is represented by a Line object.
     * The first point of each line is located on the top edge of the
     * collision rectangle.
     * The regions are then assigned to their respective Line objects.
     */
    public void adjustRegions() {

        // the starting point of the first region is at the left edge of
        // the paddle's collision rectangle.
        Point start
                = new Point(this.collisionRectangle.getTop().first().getX(), y);
        // create a new Line object for the first region, with an end point
        // 1/5th of the way across the paddle
        Point end = new Point(this.collisionRectangle.getTop().first().getX()
                + width / 5, y);
        reg1 = new Line(start, end);

        // set the starting point of the second region to the
        // end point of the first region
        start = new Point(this.collisionRectangle.getTop().first().getX()
                + width / 5, y);

        // create a new Line object for the second region, with an end point
        // 2/5ths of the way across the paddle
        end = new Point(this.collisionRectangle.getTop().first().getX()
                + 2 * (width / 5), y);
        reg2 = new Line(start, end);

        // set the starting point of the third region to the end point
        // of the second region
        start = new Point(this.collisionRectangle.getTop().first().getX()
                + 2 * (width / 5), y);

        // create a new Line object for the third region, with an end
        // point 3/5ths of the way across the paddle
        end = new Point(this.collisionRectangle.getTop().first().getX()
                + 3 * (width / 5), y);
        reg3 = new Line(start, end);

        // set the starting point of the fourth region to the end point
        // of the third region
        start = new Point(this.collisionRectangle.getTop().first().getX()
                + 3 * (width / 5), y);

        // create a new Line object for the fourth region, with an end point
        // 4/5ths of the way across the paddle
        end = new Point(this.collisionRectangle.getTop().first().getX()
                + 4 * (width / 5), y);
        reg4 = new Line(start, end);
        // set the starting point of the fifth region to the end point
        // of the fourth region.
        start = new Point(this.collisionRectangle.getTop().first().getX()
                + 4 * (width / 5), y);
        // create a new Line object for the fifth region, with an end point
        // of all the way across the paddle.
        end = new Point(this.collisionRectangle.getTop().first().getX()
                + width, y);
        reg5 = new Line(start, end);
    }
    /**
     * Returns the collision rectangle of the block.
     *
     * @return the collision rectangle of the block
     */
    public Rectangle getCollisionRectangle() {
        return this.collisionRectangle;
    }

    /**
     * Returns a new velocity based on the collision point and the current velocity.
     * If there is no collision, returns null.
     *
     * @param hitter the ball that hits the paddle.
     * @param collisionPoint the point of collision
     * @param currentVelocity the current velocity of the ball
     * @return the new velocity based on the collision point and the
     * current velocity, or null if there is no collision
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        if (collisionPoint == null || currentVelocity == null) {
            return null;
        }
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();
        if (this.reg1.isOnTheLine(collisionPoint)) {
            return Velocity.fromAngleAndSpeed(300,
                    currentVelocity.getSpeed());
        } else if (this.reg2.isOnTheLine(collisionPoint)) {
            return Velocity.fromAngleAndSpeed(330,
                    currentVelocity.getSpeed());
        } else if (this.reg3.isOnTheLine(collisionPoint)) {
            return new Velocity(currentVelocity.getDx(),
                    (-1) * Math.abs(currentVelocity.getDy()));
        } else if (this.reg4.isOnTheLine(collisionPoint)) {
            return Velocity.fromAngleAndSpeed(30,
                    currentVelocity.getSpeed());
        } else if (this.reg5.isOnTheLine(collisionPoint)) {
            return Velocity.fromAngleAndSpeed(60,
                    currentVelocity.getSpeed());
        }
        if (this.collisionRectangle.getBottom().isOnTheLine(collisionPoint)) {
            dy = (-1) * Math.abs(dy);
        }
        if (this.collisionRectangle.getRight().isOnTheLine(collisionPoint)) {
            dx = Math.abs(dx);
        } else if (this.collisionRectangle.getLeft().isOnTheLine(collisionPoint)) {
            dx = (-1) * Math.abs(dx);
        }
        return new Velocity(dx, dy);
    }
    /**
     * Adds the paddle to the game by adding it as both a sprite and
     * collidable object.
     *
     * @param g the game to add the object to.
     */
    public void addToGame(GameLevel g) {
        // Add this object as a sprite to the game
        g.addSprite(this);
        // Add this object as a collidable to the game.
        g.addCollidable(this);
    }
}
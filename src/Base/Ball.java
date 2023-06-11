/**
 * @author Roei Mesilaty
 * @version 6.0 (current version number of program)
 * @since 2023-06-09
 * @StuID 315253336
 */
package Base;

import Logic.GameEnvironment;
import Logic.GameLevel;
import Logic.Collidable;
import Logic.CollisionInfo;
import Sprites.Sprite;
import biuoop.DrawSurface;
import java.awt.Color;

/**
 * The Ball class represents a ball in a two-dimensional space.
 * The ball is represented by its center point, radius and color.
 */
public class Ball implements Sprite {
    private Rectangle ballFrame;
    private Point center;
    private int r;
    private final Color color;
    private Velocity velocity;
    private GameEnvironment gameEnvironment;
    /**
     * Creates a new Ball with the specified center point, radius, and color.
     *
     * @param center The center point of the Ball.
     * @param r      The radius of the Ball.
     * @param color  The color of the Ball.
     * @throws RuntimeException if either center or color is null.
     */
    public Ball(Point center, int r, Color color) {
        if (center == null || color == null) {
            throw new RuntimeException("At new Ball: "
                    + "Can't create Ball from null object!");
        }
        if (r < 0) {
            // Radius of a ball cannot be negative.
            // Radius has been set to 0.
            System.out.println("At new Ball: can't create a ball with"
                    + "negative value!");
            System.out.println("The radius has been set to zero.");
            this.r = 0;
        } else {
            this.r = r;
        }
        this.center = center;
        this.color = color;
        this.ballFrame = new Rectangle(new Point(0, 0),
                200, 200);
    }

    /**
     * Sets the game environment for this ball.
     *
     * @param gameEnvironment the game environment to set
     */
    public void setGameEnvironment(GameEnvironment gameEnvironment) {
        this.gameEnvironment = gameEnvironment;
    }

    /**
     * Adds this ball to the specified game.
     *
     * @param g the game to add this ball to
     */
    public void addToGame(GameLevel g) {
        // Set the game environment
        this.setGameEnvironment(g.getEnvironment());

        // Add this ball as a sprite to the game
        g.addSprite(this);
    }

    /**
     * Returns the x-coordinate of the center of this Ball.
     *
     * @return The x-coordinate of the center of this Ball.
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * Returns the y-coordinate of the center of this Ball.
     *
     * @return The y-coordinate of the center of this Ball.
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * Returns the size (radius) of the ball.
     *
     * @return the size (radius) of the ball.
     */
    public int getSize() {
        return this.r;
    }

    /**
     * Returns the color of the ball.
     *
     * @return the color of the ball.
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * Draws the ball on the given surface.
     *
     * @param surface the surface to draw on.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle((int) this.center.getX(),
                (int) this.center.getY(), this.r);
        surface.drawCircle((int) this.center.getX(),
                (int) this.center.getY(), this.r);
        surface.setColor(Color.RED);
        surface.fillCircle((int) this.center.getX(),
                (int) this.center.getY(), 2);
    }
    /**
     * Updates the ball's position for the next time step.
     */
    public void timePassed() {
        // Move the ball one step
        this.moveOneStep();
    }

    /**
     * Adjusts the bounds of the ball frame by creating a new instance of
     * RectangleFrames with the provided parameters.
     *
     * @param startingPointX the starting point x coordinate for the ball frame
     * @param startingPointY the starting point y coordinate for the ball frame
     * @param stretchRight   the amount to stretch the ball frame to the right
     *                       from the starting point.
     * @param stretchDown    the amount to stretch the ball frame downwards
     *                       from the starting point.
     */
    public void adjustBounds(int startingPointX, int startingPointY,
                             int stretchRight, int stretchDown) {
        // Create a new instance of RectangleFrames with the provided parameters
        this.ballFrame = new Rectangle(new Point(startingPointX,
                startingPointY), stretchRight, stretchDown);
    }

    /**
     * Sets the velocity of the ball.
     *
     * @param v the new velocity to set
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * Sets the velocity of the ball.
     *
     * @param dx the change in position on the x-axis
     * @param dy the change in position on the y-axis
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * Adjusts the velocity of the ball by setting a new Velocity object with
     * a random angle and speed based on the size of the ball.
     */
    public void adjustVelocity() {
        // Create a new instance of Random
        java.util.Random rand = new java.util.Random();
        // Generate a random angle and speed based on the size of the ball
        Velocity v = Velocity.fromAngleAndSpeed(rand.nextInt(360),
                (double) 50 / this.getSize());
        // Set the velocity of the ball to the new Velocity object
        this.setVelocity(v);
    }

    /**
     * Returns the trajectory line of the ball for the next time step.
     *
     * @return the trajectory line of the ball
     */
    public Line getTrajectory() {
        // Calculate the trajectory vector, make it twice the nextstep.
        Velocity v = Velocity.fromAngleAndSpeed(this.velocity.getAngle(),
                2 * Math.max(this.getVelocity().getSpeed(),
                        this.getSize()));

        // Calculate the end point of the trajectory
        Point endOfTrajectory = v.applyToPoint(this.center);

        // Return a line representing the trajectory
        return new Line(this.center, endOfTrajectory);
    }

    /**
     * Gets the velocity of the ball.
     *
     * @return the velocity of the ball
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * Checks if the ball is out of bounds and adjusts its position if
     * necessary.
     */
    public void isOutOfBounds() {
        // Get the current x and y coordinates of the center of the ball.
        double x = this.center.getX();
        double y = this.center.getY();

        // Check if the ball's right edge is beyond the right edge of the
        // ball frame.
        if (this.center.getX() + this.getSize()
                > this.ballFrame.getUpperLeft().getX()
                + this.ballFrame.getWidth()) {
            // If so, adjust the x coordinate so that the ball's right edge
            // is at the right edge of the ball frame.
            x = this.ballFrame.getUpperLeft().getX()
                    + this.ballFrame.getWidth() - this.getSize();
        } else if (this.center.getX() - this.getSize()
                < this.ballFrame.getUpperLeft().getX()) {
            // Check if the ball's left edge is beyond the left edge of
            // the ball frame.
            // If so, adjust the x coordinate so that the ball's left edge is
            // at the left edge of the ball frame.
            x = this.ballFrame.getUpperLeft().getX() + this.getSize();
        }

        // Check if the ball's bottom edge is beyond the bottom edge of
        // the ball frame.
        if (this.center.getY() + this.getSize()
                > this.ballFrame.getUpperLeft().getY()
                + this.ballFrame.getHeight()) {
            // If so, adjust the y coordinate so that the ball's bottom edge
            // is at the bottom edge of the ball frame.
            y = this.ballFrame.getUpperLeft().getY()
                    + this.ballFrame.getHeight() - this.getSize();
        } else if (this.center.getY() - this.getSize()
                < this.ballFrame.getUpperLeft().getY()) {
        // Check if the ball's top edge is beyond the top edge of
            // the ball frame.
            // If so, adjust the y coordinate so that the ball's top edge is
            // at the top edge of the ball frame.
            y = this.ballFrame.getUpperLeft().getY() + this.getSize();
        }

        // Update the center of the ball to the adjusted x and y coordinates.
        this.center = new Point(x, y);
    }

    /**
     * Checks if the ball is inside a block, if true, remove it from the block.
     */
    public void isInsideBlock() {
        for (Collidable col : gameEnvironment.getCollidables()) {
            Rectangle curRect = col.getCollisionRectangle();
            if (this.center.getX() > curRect.getUpperLeft().getX()
            && this.center.getX() < curRect.getUpperLeft().getX()
                    + curRect.getWidth()) {
                if (this.center.getY() > curRect.getUpperLeft().getY()
                && this.center.getY() < curRect.getUpperLeft().getY()
                        + curRect.getHeight()) {
                    double x = this.center.getX();
                    double y = curRect.getUpperLeft().getY() - this.getSize();
                    this.center = new Point(x, y);
                }
            }
        }
    }

    /**
     * Sets radius.
     *
     * @param radius the radius
     */
    public void setRadius(int radius) {
        this.r = radius;
    }

    /**
     * Extremes check for ball when moveonestep is called.
     */
    public void extremesCheck() {
        // If the ball's velocity is null, return without moving
        if (this.velocity == null) {
            return;
        }
        // If the dx or dy bigger than the screen height or width, reset
        // it to the minimum of them.
        if (this.velocity.getDx() > this.ballFrame.getWidth()) {
            this.setVelocity(this.ballFrame.getWidth()
                    - 2 * this.getSize(), this.velocity.getDy());
            System.out.println("Dx was bigger than screen size, reset to"
                    + "maximum speed!");
        }
        if (this.velocity.getDy() > this.ballFrame.getHeight()) {
            this.setVelocity(this.velocity.getDx(),
                    this.ballFrame.getHeight() - 2 * this.getSize());
            System.out.println("Dy was bigger than screen size, reset to"
                    + "maximum speed!");
        }
        // If the ball's size is bigger than the screen size, set to maximum.
        if (this.getSize() * 2 > this.ballFrame.getWidth()
                || this.getSize() * 2 > this.ballFrame.getHeight()) {
            this.setRadius((int) Math.min(this.ballFrame.getWidth(),
                    this.ballFrame.getHeight()) / 2);
            System.out.println("Ball size was bigger than the screen"
                    + " size, size changed to maximum size.");
        }
        // Checks if the ball went out of bounds (shouldn't happen, just
        // in-case), and also if the ball gets inside a block, take it out.
        isInsideBlock();
        isOutOfBounds();
    }

    /**
     * Moves the ball one step according to its current velocity, and updates
     * its position and velocity if it collides with an object in the
     * game environment.
     */
    public void moveOneStep() {
        // Check all the extreme cases that could happen to the ball.
        extremesCheck();

        // Get the ball's current velocity.
        double dx = this.getVelocity().getDx();
        double dy = this.getVelocity().getDy();

        // Find the closest collision between the ball and an object in
        // the game environment.
        CollisionInfo closestColInfo
                = this.gameEnvironment.getClosestCollision(this.getTrajectory());

        if (closestColInfo != null) {
            // If a collision has been detected, update the ball's
            // velocity and position accordingly.
            Point collisionPoint = closestColInfo.collisionPoint();
            Velocity newVelocity
                    = closestColInfo.collisionObject().hit(this, collisionPoint,
                    this.getVelocity());
            this.setVelocity(newVelocity);
            this.center = this.getVelocity().applyToPoint(this.center);
            // Stop the current iteration of the method and return to
            // the calling method.
            return;
        }
        // If no collision has been detected, update the ball's position
        // according to its current velocity.
//        setVelocity(dx, dy);
        this.center = this.getVelocity().applyToPoint(this.center);
    }
    /**
     * Removes the sprite from the specified game.
     *
     * @param game the game from which the sprite will be removed
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
    }
}
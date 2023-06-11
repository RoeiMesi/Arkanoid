/**
 * @author Roei Mesilaty
 * @version 6.0 (current version number of program)
 * @since 2023-06-09
 * @StuID 315253336
 */
package Base;
/**
 * The Velocity class represents the velocity of an object in a
 * two-dimensional space.
 * It is defined by its x and y components, which are measured in
 * pixels per second.
 */
public class Velocity {
    private static final double EPSILON = Math.pow(10, -7);
    private final double dx;
    private final double dy;

    /**
     * Constructs a new Velocity object with the specified x and y components.
     *
     * @param dx the x-component of the velocity in pixels per second
     * @param dy the y-component of the velocity in pixels per second
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Constructs a new Velocity object from a given angle and speed.
     * The angle is measured in degrees and represents the direction of
     * the velocity vector while the speed is measured in pixels per second
     * and represents the magnitude of the velocity vector.
     *
     * @param angle the angle of the velocity vector in degrees
     * @param speed the magnitude of the velocity vector in pixels per second
     * @return a new Velocity object with the specified angle and speed
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        angle = Math.toRadians(angle);
        double dx = speed * Math.sin(angle);
        double dy = (-1) * speed * Math.cos(angle);
        if (Math.abs(dx - Math.round(dx)) < EPSILON) {
            dx = Math.round(dx);
        }
        if (Math.abs(dy - Math.round(dy)) < EPSILON) {
            dy = Math.round(dy);
        }
        return new Velocity(dx, dy);
    }

    /**
     * Returns the x-component of this Velocity.
     *
     * @return the x-component of this Velocity
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * Returns the y-component of this Velocity.
     *
     * @return the y-component of this Velocity
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * Applies this Velocity to a given Point.
     * Returns a new Point that represents the result of the operation.
     *
     * @param p the Point to apply this Velocity to
     * @return a new Point that represents the result of applying this
     * Velocity to the given Point
     */
    public Point applyToPoint(Point p) {
        if (p == null) {
            System.err.println("applyToPoint got a null point to apply");
            return null;
        }
        return new Point(p.getX() + this.dx, p.getY() + this.dy);
    }

    /**
     * Gets the current speed of the ball.
     *
     * @return the speed of the ball.
     */
    public double getSpeed() {
        return Math.sqrt(Math.pow(getDx(), 2) + Math.pow(getDy(), 2));
    }

    /**
     * Gets angle.
     *
     * @return the angle that the velocity's angle.
     */
    public double getAngle() {

        //  arc tan calc the angle from the x-axis.
        double angle = (90 + (Math.toDegrees(Math.atan2(this.dy,
                this.dx)))) % 360;

        // I want the angle to be positive.
        while (0 > angle) {
            angle += 360;
        }
        return angle;
    }
}
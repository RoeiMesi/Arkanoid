/**
 * @author Roei Mesilaty
 * @version 6.0 (current version number of program)
 * @since 2023-06-09
 * @StuID 315253336
 */
package Base;
/**
 * The Point class represents a point in a two-dimensional coordinate system.
 * It is defined by its x and y coordinates.
 */
public class Point {
    private static final double EPSILON = Math.pow(10, -7);
    private final double x;
    private final double y;
    /**
     * Constructs a point with the specified x and y coordinates.
     *
     * @param x the x-coordinate of the point
     * @param y the y-coordinate of the point
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Returns the point with the smaller x-coordinate, or this point if both
     * have the same x-coordinate.
     * If the other point is null, returns this point.
     *
     * @param other the other point to compare to.
     * @return the point with the smaller x-coordinate, or this point if
     * both have the same x-coordinate
     */
    public Point min(Point other) {
        if (other == null) {
            return this;
        }
        if (this.getX() < other.getX()) {
            return this;
        } else if (thresholdComparison(this.getX(), other.getX())) {
            if (this.getY() < other.getY()) {
                return this;
            } else {
                return other;
            }
        } else {
            return other;
        }
    }

    /**
     * Returns the point with the larger x-coordinate, or this point if
     * both have the same x-coordinate.
     * If the other point is null, returns this point.
     *
     * @param other the other point to compare to
     * @return the point with the larger x-coordinate, or this point if
     * both have the same x-coordinate
     */
    public Point max(Point other) {
        if (other == null) {
            return this;
        }
        if (this.getX() > other.getX()) {
            return this;
        } else if (thresholdComparison(this.getX(), other.getX())) {
            if (this.getY() > other.getY()) {
                return this;
            } else {
                return other;
            }
        } else {
            return other;
        }
    }
    /**
     * Computes the Euclidean distance between this point and the given point.
     *
     * @param other the point to compute the distance to
     * @return the Euclidean distance between this point and the given point
     */
    public double distance(Point other) {
        return Math.sqrt(((this.x - other.x) * (this.x - other.x)
                + (this.y - other.y) * (this.y - other.y)));
    }

    /**
     * Checks whether this point is equal to the given point, within a
     * certain threshold.
     * Returns true if both x and y coordinates of this point and the given
     * point are equal within a certain threshold value.
     *
     * @param other the point to compare to
     * @return true if this point is equal to the given point within a
     * certain threshold value, false otherwise
     */
    public boolean equals(Point other) {
        return (thresholdComparison(this.x, other.x)
                && thresholdComparison(this.y, other.y));
    }

    /**
     * Checks whether two double values are equal within a certain threshold.
     *
     * @param a the first double value
     * @param b the second double value
     * @return true if the two values are equal within a certain threshold
     * value, false otherwise
     */
    public boolean thresholdComparison(double a, double b) {
        return (Math.abs(a - b) <= EPSILON);
    }

    /**
     * Returns the x-coordinate of this point.
     *
     * @return the x-coordinate of this point
     */
    public double getX() {
        return this.x;
    }

    /**
     * Returns the y-coordinate of this point.
     *
     * @return the y-coordinate of this point
     */
    public double getY() {
        return this.y;
    }
}
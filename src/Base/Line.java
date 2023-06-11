/**
 * @author Roei Mesilaty
 * @version 6.0 (current version number of program)
 * @since 2023-06-09
 * @StuID 315253336
 */
package Base;
/**
 The Line class represents a line segment in a two-dimensional space.
 The line is represented by its two points - start and end points.
 */
public class Line {
    private static final double EPSILON = Math.pow(10, -7);
    private final Point first;
    private final Point second;
    private final Point start;
    private final Point end;
    /**
     * Constructs a line segment between two given points.
     *
     * @param start the starting point of the line segment.
     * @param end the ending point of the line segment.
     * @throws RuntimeException if either the starting or ending point is null.
     */
    public Line(Point start, Point end) {
        // Check if either start or end points are null
        if (start == null || end == null) {
            // Throw a runtime exception if either start or end points are null
            throw new RuntimeException("Line got a null Point");
        }
        // Create a new point object with the same coordinates as
        // the first point
        this.start = new Point(start.getX(), start.getY());
        this.end = new Point(end.getX(), end.getY());
        // Create a new point object with the same coordinates as the second
        // pointץ
        this.first = this.start.min(this.end);
        this.second = this.start.max(this.end);
    }

    /**
     * Constructs a new line segment with the given coordinates of first
     * and second points.
     * @param x1 the x-coordinate of the start point
     * @param y1 the y-coordinate of the start point
     * @param x2 the x-coordinate of the end point
     * @param y2 the y-coordinate of the end point
     */
    public Line(double x1, double y1, double x2, double y2) {
        this(new Point(x1, y1), new Point(x2, y2));
    }
    /**
     * Returns the starting point of this line segment.
     *
     * @return the starting point of this line segment.
     */
    public Point start() {
        return this.start;
    }

    /**
     * Returns the ending point of this line segment.
     *
     * @return the ending point of this line segment.
     */
    public Point end() {
        return this.end;
    }

    /**
     * Returns the length of the line segment.
     * @return the length of the line segment
     */
    public double length() {
        return this.first.distance(this.second);
    }
    /**
     * Returns the middle point of the line segment.
     * @return the middle point of the line segment
     */
    public Point middle() {
        double middleX = (this.second.getX() + this.first.getX()) / 2;
        double middleY = (this.second.getY() + this.first.getY()) / 2;
        return new Point(middleX, middleY);
    }

    /**
     * Returns the first point of the line segment.
     * @return the first point of the line segment
     */
    public Point first() {
        return this.first;
    }

    /**
     * Returns the second point of the line segment.
     * @return the second point of the line segment
     */
    public Point second() {
        return this.second;
    }

    /**
     * Calculates and returns the slope of the line segment.
     * @return the slope of the line segment.
     */
    public double slope() {
        // This is special case of when it's from form: x = b
        if (thresholdComparison(this.second.getX()
                - this.first.getX(), 0)) {
            return Double.POSITIVE_INFINITY;
        }
        return (this.second.getY() - this.first.getY())
                / (this.second.getX() - this.first.getX());
    }
    /**
     * Returns the x-coordinate of the intersection point between this line
     * segment and the specified line segment.
     *
     * @param other the other line segment.
     * @return the x-coordinate of the intersection point, or NaN if the
     * lines are parallel.
     */
    public double getXIntersect(Line other) {
        // vertical line intersection
        if (this.slope() == Double.POSITIVE_INFINITY
                && other.slope() != Double.POSITIVE_INFINITY) {
            // Return the x-coordinate of the starting point of this
            // line segment.
            return first.getX();
        }
        // vertical line intersection
        if (other.slope() == Double.POSITIVE_INFINITY
                && this.slope() != Double.POSITIVE_INFINITY) {
            // Return the x-coordinate of the starting point of the other
            // line segment.
            return other.first.getX();
        }
        // parallel line intersection
        if (this.isParallel(other)) {
            if (thresholdComparison(this.first.getX(), other.first.getX())
                    || thresholdComparison(this.first.getX(),
                    other.second.getX())) {
                // Return the x-coordinate of the starting point of
                // this line segment.
                return this.first.getX();
            } else {
                // Return the x-coordinate of the ending point of
                // this line segment.
                return this.second.getX();
            }
        }
        // Calculate the x-coordinate of the intersection point using
        // the slope-intercept form of the line equation
        return (other.first.getY() - other.first.getX() * other.slope()
                + this.first.getX() * this.slope() - this.first.getY())
                / (this.slope() - other.slope());
    }
    /**
     * Returns the y-coordinate of the intersection point between this
     * line segment and the specified line segment.
     *
     * @param other the other line segment.
     * @return the y-coordinate of the intersection point, or NaN if
     * the lines are parallel.
     */
    public double getYIntersect(Line other) {
        if (this.slope() == Double.POSITIVE_INFINITY
                && other.slope() == Double.POSITIVE_INFINITY) {
            // Both lines are vertical, so return the y-coordinate of
            // the higher starting point
            if (this.first.getY() >= other.second.getY() - EPSILON) {
                return this.first.getY();
            }
            return other.first.getY();
        }
        if (this.slope() == Double.POSITIVE_INFINITY) { // This line is vertical
            // Return the y-coordinate of the intersection point using the
            // x-coordinate from the getXIntersect method and the equation
            // of the other line.
            return other.slope() * this.getXIntersect(other)
                    - other.first.getX() * other.slope() + other.first.getY();
        }
        // The other line is vertical
        if (other.slope() == Double.POSITIVE_INFINITY) {
            // Return the y-coordinate of the intersection point using the
            // x-coordinate from the getXIntersect method and the equation
            // of this line.
            return this.slope() * other.getXIntersect(this)
                    - this.first.getX() * this.slope() + this.first.getY();
        }
        // Return the y-coordinate of the intersection point using
        // the slope-intercept form of the line equation
        return this.slope() * this.getXIntersect(other)
                - this.slope() * this.first.getX() + this.first.getY();
    }

    /**
     * Calculates and returns the intersection point
     * between this line segment and the specified line.
     * @param other the other line segment to intersect with
     * @return the intersection point
     */
    public Point intersectionPoint(Line other) {
        return new Point(this.getXIntersect(other), this.getYIntersect(other));
    }
    /**
     * Checks whether a given point is on the line segment defined by this
     * Line object.
     *
     * @param intersection The point to check.
     * @return true if the point is on the line segment, false otherwise.
     */
    public boolean isOnTheLine(Point intersection) {
        // Calculate the distances between the intersection point and the start
        // and end points of the line segment.
        double ac = intersection.distance(this.start);
        double bc = intersection.distance(this.end);

        // Calculate the length of the line segment.
        double ab = this.length();

        // Check if the sum of the distances between the intersection point and
        // the start and end points of the line segment is equal
        // to the length of the line segment.
        // Use a tolerance value to account for floating point imprecision.
        return (Math.abs((ac + bc) - ab) < EPSILON);
    }
    /**
     * Determines whether this line is in front of another line, based
     * on the direction they are both facing.
     *
     * @param other the other line to compare to
     * @return true if this line is in front of the other line, false otherwise
     */
    public boolean isInFront(Line other) {
        if (this.isParallel(other)) { // if lines are parallel
            if ((Math.abs(other.second.getX() - this.second.getX()) <= EPSILON)
                    || (Math.abs(this.second.getX() - other.second.getX())
                    <= EPSILON)) {
                // lines overlap
                return (thresholdComparison(other.second.getX()
                        - this.first.getX(), 0));
            }
            // lines have same slope
            return thresholdComparison((other.second.getY()
                    - this.second.getY()) / (other.second.getX()
                    - this.second.getX()), this.slope());
        }
        // lines are not parallel
        return false;
    }
    /**
     Determines if this line intersects with the given line.
     @param other the line to check for intersection with this line
     @return true if the lines intersect, false otherwise
     */
    public boolean isIntersecting(Line other) {
        // Check for vertical lines
        if (this.slope() == Double.POSITIVE_INFINITY
                && other.slope() == Double.POSITIVE_INFINITY) {
            // case of both lines are from form x = b
            if (!(this.isInFront(other))) {
                return false;
            }
            if (this.first.getY() > other.second.getY()) {
                return false;
            }
            if (other.first.getY() > this.second.getY()) {
                return false;
            }
            if (this.first.equals(other.second())
                    || other.first.equals(this.second)) {
                // if they are at least touching.
                return true;
            }
            if (this.first().getY() < other.second.getY()
                    && this.first.getY() >= other.first.getY()) {
                // infinity points
                return true;
            }
            // infinity points
            return other.first().getY() < this.second.getY()
                    && other.first.getY() >= this.first.getY();
        }
        // Check for parallel lines
        if (this.isParallel(other)) {
            if (this.equals(other)) {
                return true;
            }
            if (this.isInFront(other)) {
                return (this.first.getX() <= other.second.getX() + EPSILON
                        && this.second.getX() >= other.first.getX() - EPSILON)
                        || (other.first.getX() <= this.second.getX() + EPSILON
                        && other.second.getX() >= this.first.getX() - EPSILON);
            }
        }
        // Calculate intersection point
        Point intersection = this.intersectionPoint(other);
        return this.isOnTheLine(intersection)
                && other.isOnTheLine(intersection);
    }
    /**
     * Returns the intersection point of this line with a given line.
     * If there is no intersection point, returns null.
     *
     * @param other the other line
     * @return the intersection point, or null if there is none
     */
    public Point intersectionWith(Line other) {
        if (this.isInfiniteIntersectionPoints(other)) {
            return null;
        }
        if (this.equals(other)) { // This returns true if the line is the
            // same line, false if not because they are parallel.
            return null;
        }
        if (this.isIntersecting(other)) {
            return this.intersectionPoint(other);
        }
        return null;
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
     * Checks if this line is parallel to the given line.
     *
     * @param other the line to check if it's parallel to this line
     * @return true if the lines are parallel, false otherwise
     */
    public boolean isParallel(Line other) {
        // If both lines are vertical, they are parallel
        if (this.slope() == Double.POSITIVE_INFINITY
                && other.slope() == Double.POSITIVE_INFINITY) {
            return true;
        }
        // If the difference between the slopes of the lines is
        // very small, they are parallel
        return Math.abs(this.slope() - other.slope()) <= EPSILON;
    }
    /**
     * Checks if this line is a sub-line of the given line.
     *
     * @param other the line to compare with
     * @return true if this line is a sub-line of the other
     * line, false otherwise
     */
    public boolean isSubLine(Line other) {
        if (!(this.isParallel(other))) {
            return false;
        }
        // case where both lines are vertical
        if (this.slope() == Double.POSITIVE_INFINITY) {
            if ((thresholdComparison(this.first.getY(), other.second.getY())
                    && this.second.getY() > other.second.getY())
                    || (thresholdComparison(other.first.getY(),
                    this.second.getY()) && other.second.getY()
                    > this.second.getY())) {
                return false;
            }
            return this.isInFront(other)
                    && ((this.first.getY() <= other.first.getY() + EPSILON
                    && this.second.getY() <= other.second.getY() + EPSILON)
                    || (other.first.getY() >= this.first.getY() - EPSILON
                    && other.second.getY() <= this.second.getY() + EPSILON));
        }
        return this.isInFront(other)
                && ((this.first.getX() >= other.first.getX() - EPSILON
                && this.second.getX() <= other.second.getX() + EPSILON)
                || (other.first.getX() >= this.first.getX() - EPSILON
                && other.second.getX() <= this.second.getX() + EPSILON));
    }
    /**
     * Determines if this line has infinite intersection points
     * with the specified line.
     *
     * @param other the other line to check for infinite intersection points
     * with.
     * @return true if this line has infinite intersection points with the
     * specified line; false otherwise
     */
    public boolean isInfiniteIntersectionPoints(Line other) {
        // if either line is a sub-line of the other, they have infinite
        // intersection points
        if (this.isSubLine(other) || other.isSubLine(this)) {
            return true;
        }
        // if the lines are not parallel, they don't have infinite
        // intersection points
        if (!(this.isParallel(other))) {
            return false;
        }
        // case of both lines are from form x = b
        if (this.slope() == Double.POSITIVE_INFINITY
                && other.slope() == Double.POSITIVE_INFINITY) {
            // if the lines do not overlap on the y-axis, they don't have
            // infinite intersection points.
            if ((thresholdComparison(this.first.getY(), other.second.getY())
                    && this.second.getY() > other.second.getY())
                    || (thresholdComparison(other.first.getY(),
                    this.second.getY()) && other.second.getY()
                    > this.second.getY())) {
                return false;
            }
        }
        // if the lines are parallel and their intersection points
        // are infinite, return true
        if (this.first().getX() < other.second.getY()
                && this.first.getY() >= other.first.getY()) {
            return true;
        }
        // if the lines are parallel and their intersection points are
        // infinite, return true
        return (other.first().getY() < this.second.getY()
                && other.first.getY() >= this.first.getY());
        // if none of the above conditions are met, the lines don't have
        // infinite intersection points
    }
    /**
     * Determines if the specified Line object is equal to this one.
     * @param other the other Line object to compare to this one
     * @return true if the two lines have the same first and second
     * points, false otherwise
     */
    public boolean equals(Line other) {
        return (this.first.equals(other.first)
                && this.second.equals(other.second));
    }
    /**
     * Determines if this Line object is actually a point.
     * @return true if the first and second points are equal, false otherwise
     */
    public boolean isPoint() {
        return (this.first.equals(this.second));
    }
    /**
     * Calculates the closest intersection point of a line to a given rectangle.
     *
     * @param rect The rectangle to check for intersection points.
     * @return The closest intersection point to the start of the line,
     * or null if there are no intersection points.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        // Get a list of intersection points between the line and the rectangle.
        java.util.List<Point> points = rect.intersectionPoints(this);
        // If there are no intersection points, return null.
        if (points.isEmpty()) {
            return null;
        }
        // Initialization of closest intersection point.
        Point closest = points.get(0);
        // Iterate through each intersection point and check if it is closer
        // to the start of the line than the current closest point.
        for (Point intersectionPoint : points) {
            if (intersectionPoint.distance(this.start())
                    < closest.distance(this.start())) {
                closest = intersectionPoint;
            }
        }
        // Return the closest intersection point to the start of the line.
        return closest;
        // We know there is at least one intersection point with the rectangle.
    }
}
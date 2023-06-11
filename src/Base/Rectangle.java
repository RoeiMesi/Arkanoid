/**
 * @author Roei Mesilaty
 * @version 6.0 (current version number of program)
 * @since 2023-06-09
 * @StuID 315253336
 */
package Base;

import java.awt.Color;
import java.util.List;
import java.util.ArrayList;
import biuoop.DrawSurface;
/**
 * A class representing a rectangle frame with a starting point and dimensions
 * to stretch right and down.
 */
public class Rectangle {
    private Point upperLeft;
    private final double recWidth;
    private final double recHeight;
    private Line left;
    private Line right;
    private Line top;
    private Line bottom;
    private final Line[] bounds = new Line[4];
    private Color color = Color.BLACK;

    /**
     * Constructor function for creating a new Rectangle object.
     *
     * @param upperLeft the point representing the upper left
     *                  corner of the rectangle
     * @param width the width of the rectangle
     * @param height the height of the rectangle
     */
    public Rectangle(Point upperLeft,
                     double width, double height) {
        this.upperLeft = upperLeft;
        this.recWidth = width;
        this.recHeight = height;
        Point bottomLeft = new Point(upperLeft.getX(),
                upperLeft.getY() + recHeight);
        Point upperRight = new Point(upperLeft.getX()
                + width, upperLeft.getY());
        Point bottomRight = new Point(upperLeft.getX()
                + width, upperLeft.getY() + height);

        this.left = new Line(upperLeft, bottomLeft);
        this.top = new Line(upperLeft, upperRight);
        this.right = new Line(upperRight, bottomRight);
        this.bottom = new Line(bottomLeft, bottomRight);
        bounds[0] = left;
        bounds[1] = top;
        bounds[2] = right;
        bounds[3] = bottom;
    }
    /**
     * Returns the upper-left point of the rectangle.
     *
     * @return the upper-left point of the rectangle
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * Returns the left line of the rectangle.
     *
     * @return the left line of the rectangle
     */
    public Line getLeft() {
        return this.left;
    }

    /**
     * Returns the right line of the rectangle.
     *
     * @return the right line of the rectangle
     */
    public Line getRight() {
        return this.right;
    }

    /**
     * Returns the top line of the rectangle.
     *
     * @return the top line of the rectangle
     */
    public Line getTop() {
        return this.top;
    }

    /**
     * Returns the bottom line of the rectangle.
     *
     * @return the bottom line of the rectangle
     */
    public Line getBottom() {
        return this.bottom;
    }

    /**
     * Returns a list of intersection points between this rectangle
     * and a given line.
     *
     * @param line the given line
     * @return a list of intersection points
     */
    public List<Point> intersectionPoints(Line line) {
        List<Point> points = new ArrayList<>();
        for (Line boundLine : bounds) {
            if (boundLine.isIntersecting(line)) {
                points.add(boundLine.intersectionWith(line));
            }
        }
        return points;
    }
    /**
     * Sets the rectangle dimensions based on the specified x and y coordinates.
     *
     * @param x the x coordinate of the upper-left corner of the rectangle
     * @param y the y coordinate of the upper-left corner of the rectangle
     */
    public void setRectangle(double x, double y) {
        // Set the upper-left corner of the rectangle
        this.upperLeft = new Point(x, y);

        // Calculate the remaining points of the rectangle
        Point bottomLeft = new Point(upperLeft.getX(),
                upperLeft.getY() + recHeight);
        Point upperRight = new Point(upperLeft.getX()
                + this.getWidth(), upperLeft.getY());
        Point bottomRight = new Point(upperLeft.getX()
                + this.getWidth(), upperLeft.getY() + this.getHeight());

        // Set the lines representing the edges of the rectangle
        this.left = new Line(this.upperLeft, bottomLeft);
        this.top = new Line(this.upperLeft, upperRight);
        this.right = new Line(upperRight, bottomRight);
        this.bottom = new Line(bottomLeft, bottomRight);

        // Store the lines in an array for later use
        bounds[0] = left;
        bounds[1] = top;
        bounds[2] = right;
        bounds[3] = bottom;
    }

    /**
     * @return the color of the block
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * Sets the color of the rectangle.
     *
     * @param color the color to set
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Draws the rectangle on the specified DrawSurface.
     *
     * @param d the DrawSurface to draw on
     */
    public void drawOn(DrawSurface d) {
        // Get the dimensions of the rectangle
        int x1 = (int) getUpperLeft().getX();
        int y1 = (int) getUpperLeft().getY();
        int x2 = (int) getWidth();
        int y2 = (int) getHeight();

        // Set the color and fill the rectangle
        d.setColor(this.color);
        d.fillRectangle(x1, y1, x2, y2);
        d.setColor(Color.BLACK);
        d.drawRectangle(x1, y1, x2, y2);
    }
    /**
     * Returns the width of this rectangle frame.
     *
     * @return the width of this rectangle frame.
     */
    public double getWidth() {
        return this.recWidth;
    }

    /**
     * Returns the height of this rectangle frame.
     *
     * @return the height of this rectangle frame.
     */
    public double getHeight() {
        return this.recHeight;
    }
}
/**
 * @author Roei Mesilaty
 * @version 6.0 (current version number of program)
 * @since 2023-06-09
 * @StuID 315253336
 */
package Base;
/**
 * The Counter class represents a simple counter that can be increased or decreased.
 */
public class Counter {
    private int counter = 0;

    /**
     * Increases the counter by the specified number.
     *
     * @param number the value by which to increase the counter
     */
    public void increase(int number) {
        counter += number;
    }

    /**
     * Decreases the counter by the specified number.
     *
     * @param number the value by which to decrease the counter
     */
    public void decrease(int number) {
        counter -= number;
    }

    /**
     * Returns the current value of the counter.
     *
     * @return the current value of the counter
     */
    public int getValue() {
        return counter;
    }
}
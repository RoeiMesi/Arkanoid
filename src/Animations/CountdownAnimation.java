/**
 * @author Roei Mesilaty
 * @version 6.0 (current version number of program)
 * @since 2023-06-09
 * @StuID 315253336
 */
package Animations;

import Sprites.SpriteCollection;
import biuoop.DrawSurface;
import biuoop.Sleeper;
import java.awt.Color;

/**
 * Represents a countdown animation that displays a countdown from a specified number of seconds.
 * It uses a SpriteCollection to draw the game screen and displays the level name.
 */
public class CountdownAnimation implements Animation {
    private final double numOfSeconds;
    private final int countFrom;
    private int countDown;
    private final SpriteCollection gameScreen;
    private boolean stop;
    private final String levelName;
    private static final int LVL_NAME_SIZE = 20;
    private static final int TEXT_SIZE = 80;

    /**
     * Constructs a CountdownAnimation.
     *
     * @param numOfSeconds The total number of seconds for the countdown.
     * @param countFrom    The starting count value.
     * @param gameScreen   The SpriteCollection for drawing the game screen.
     * @param levelName    The name of the level.
     */
    public CountdownAnimation(double numOfSeconds, int countFrom,
                              SpriteCollection gameScreen, String levelName) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.countDown = countFrom;
        this.gameScreen = gameScreen;
        this.levelName = levelName;
        this.stop = false;
    }

    /**
     * Performs one frame of the countdown animation.
     *
     * @param d The DrawSurface to draw the animation on.
     */
    public void doOneFrame(DrawSurface d) {
        // Check if the countdown reached 0
        if (this.countDown <= 0) {
            this.stop = true;
        }

        // Draw the game screen and level name
        gameScreen.drawAllOn(d);
        d.setColor(new Color(50, 7, 98));
        d.drawText(350, 22, "Level Name: " + levelName, LVL_NAME_SIZE);

        // Draw the countdown value
        d.setColor(new Color(84, 65, 23));
        d.drawText(400, d.getHeight() / 2, Integer.toString(countDown), TEXT_SIZE);

        if (countDown != countFrom) {
            // Sleep for numOfSeconds seconds
            new Sleeper().sleepFor((long) (1000 * (numOfSeconds / countFrom)));
        }

        // Decrease the count
        this.countDown--;
    }

    /**
     * Checks if the countdown animation should stop.
     *
     * @return true if the animation should stop, false otherwise.
     */
    public boolean shouldStop() {
        return this.stop;
    }
}
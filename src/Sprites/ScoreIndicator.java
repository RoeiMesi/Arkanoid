/**
 * @author Roei Mesilaty
 * @version 6.0 (current version number of program)
 * @since 2023-06-09
 * @StuID 315253336
 */
package Sprites;

import Base.Counter;
import Logic.GameLevel;
import biuoop.DrawSurface;
import java.awt.Color;
/**
 * The ScoreIndicator class implements the Sprite interface and represents a visual indicator of the game score.
 */
public class ScoreIndicator implements Sprite {
    private final Counter score;
    private static final int TEXT_SIZE = 20;

    /**
     * Constructs a ScoreIndicator with the specified counter for the game score.
     *
     * @param score the counter for the game score
     */
    public ScoreIndicator(Counter score) {
        this.score = score;
    }

    /**
     * Draws the score indicator on the given draw surface.
     *
     * @param d the draw surface to draw on
     */
    public void drawOn(DrawSurface d) {
        int currScore = score.getValue();
        String text = Integer.toString(currScore);
        d.setColor(new Color(50, 7, 98));
        d.drawText(150, 22, "Score: " + text, TEXT_SIZE);
    }

    /**
     * This method is empty and does not perform any actions for time passing.
     */
    public void timePassed() {
        // No action needed
    }

    /**
     * Adds the score indicator to the specified game.
     *
     * @param game the game to add the score indicator to
     */
    public void addToGame(GameLevel game) {
        game.addSprite(this);
    }
}

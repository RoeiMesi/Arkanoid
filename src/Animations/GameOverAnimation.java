/**
 * @author Roei Mesilaty
 * @version 6.0 (current version number of program)
 * @since 2023-06-09
 * @StuID 315253336
 */
package Animations;

import Base.Counter;
import biuoop.DrawSurface;
import java.awt.Color;

/**
 * Represents a game over animation that is displayed when the game ends.
 * It shows a "GAME OVER" message and the final score if the player loses,
 * or a "YOU WIN" message and the final score if the player wins.
 */
public class GameOverAnimation implements Animation {
    private final boolean stop;
    private final boolean isWon;
    private final Counter score;
    private static final int TEXT_SIZE = 60;

    /**
     * Constructs a GameOverAnimation.
     *
     * @param isWon  Flag to indicate if the player won or lost.
     * @param score  The final score of the game.
     */
    public GameOverAnimation(boolean isWon, Counter score) {
        this.stop = false;
        this.score = score;
        this.isWon = isWon;
    }

    /**
     * Performs one frame of the game over animation.
     *
     * @param d The DrawSurface to draw the animation on.
     */
    public void doOneFrame(DrawSurface d) {
        String result = Integer.toString(score.getValue());
        Color backgroundColor = new Color(16, 14, 72);
        d.setColor(backgroundColor);
        d.fillRectangle(0, 0, 800, 600);

        if (!isWon) {
            d.setColor(Color.RED);
            d.drawText((d.getWidth() / 2) - 200, d.getHeight() / 2 - 80, "GAME OVER :(", TEXT_SIZE);
            d.drawText((d.getWidth() / 2) - 160, d.getHeight() / 2 + 20, "SCORE: " + result, TEXT_SIZE);
            d.drawText((d.getWidth() / 2) - 220, d.getHeight() / 2 + 120, "PRESS SPACE TO EXIT", 40);
        } else {
            d.setColor(Color.GREEN.darker());
            d.drawText((d.getWidth() / 2) - 150, d.getHeight() / 2 - 80, "YOU WIN!", TEXT_SIZE);
            d.drawText((d.getWidth() / 2) - 170, d.getHeight() / 2 + 20, "SCORE: " + result, TEXT_SIZE);
            d.drawText((d.getWidth() / 2) - 220, d.getHeight() / 2 + 120, "PRESS SPACE TO EXIT", 40);
        }
    }

    /**
     * Checks if the game over animation should stop.
     *
     * @return true if the animation should stop, false otherwise.
     */
    public boolean shouldStop() {
        return this.stop;
    }
}
/**
 * @author Roei Mesilaty
 * @version 6.0 (current version number of program)
 * @since 2023-06-09
 * @StuID 315253336
 */
package Logic;

import Animations.Animation;
import Animations.AnimationRunner;
import Animations.GameOverAnimation;
import Animations.KeyPressStoppableAnimation;
import Base.Counter;
import biuoop.KeyboardSensor;

import java.util.List;

/**
 * The GameFlow class is responsible for running multiple levels of the game.
 * It manages the flow of the game and handles the transition between levels.
 */
public class GameFlow {
    private final AnimationRunner runner;
    private final KeyboardSensor keyboard;
    private final Counter score = new Counter();

    /**
     * Constructs a GameFlow object with the given AnimationRunner and KeyboardSensor.
     *
     * @param ar the AnimationRunner to run the animations
     * @param ks the KeyboardSensor to detect keyboard input
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks) {
        this.runner = ar;
        this.keyboard = ks;
    }

    /**
     * Runs the given list of LevelInformation objects.
     *
     * @param levels the list of levels to run
     */
    public void runLevels(List<LevelInformation> levels) {
        Animation endScreen = null;

        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, this.keyboard, this.runner, this.score);
            level.initialize();
            level.run();

            if (!level.gameResult()) {
                endScreen = new GameOverAnimation(level.gameResult(), score);
                break;
            }
            if (level.gameResult()) {
                endScreen = new GameOverAnimation(level.gameResult(), score);
            }
        }
        this.runner.run(new KeyPressStoppableAnimation(keyboard, KeyboardSensor.SPACE_KEY, endScreen));
    }
}
/**
 * @author Roei Mesilaty
 * @version 6.0 (current version number of program)
 * @since 2023-06-09
 */
import Animations.AnimationRunner;
import Levels.DirectHit;
import Levels.WideEasy;
import Levels.Azure3;
import Logic.GameFlow;
import Logic.LevelInformation;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

/**
 * This class is the main class for running the game.
 */
public class Ass6Game {
    /**
     * The main method for running the game.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        GUI gui = new GUI("Arkanoid", 800, 600);
        AnimationRunner ar = new AnimationRunner(gui, 60);
        KeyboardSensor ks = gui.getKeyboardSensor();
        GameFlow gameFlow = new GameFlow(ar, ks);

        TreeMap<String, LevelInformation> levels = new TreeMap<>();
        levels.put("1", new DirectHit());
        levels.put("2", new WideEasy());
        levels.put("3", new Azure3());

        List<LevelInformation> lvls = new LinkedList<>();
        for (String lvl : args) {
            if (levels.containsKey(lvl)) {
                lvls.add(levels.get(lvl));
            }
        }
        if (lvls.size() == 0) {
            gameFlow.runLevels(new ArrayList<>(levels.values()));
        } else {
            gameFlow.runLevels(lvls);
        }
        gui.close();

    }
}

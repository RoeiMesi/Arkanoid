/**
 * @author Roei Mesilaty
 * @version 6.0 (current version number of program)
 * @since 2023-06-09
 * @StuID 315253336
 */
package Animations;

import biuoop.Sleeper;
import biuoop.DrawSurface;
import biuoop.GUI;

/**
 * Responsible for running animations and controlling the frame rate.
 * It uses a GUI to display the animations and ensures a consistent frame rate.
 */
public class AnimationRunner {
    private final GUI gui;                 // The GUI used to display the animations
    private final int framesPerSecond;     // The desired frames per second
    private final Sleeper sleeper = new Sleeper(); // Sleeper for controlling the frame rate
    private static final int THOUSAND = 1000;

    /**
     * Constructs an AnimationRunner.
     *
     * @param gui             The GUI used to display the animations.
     * @param framesPerSecond The desired frames per second.
     */
    public AnimationRunner(GUI gui, int framesPerSecond) {
        this.gui = gui;
        this.framesPerSecond = framesPerSecond;
    }

    /**
     * Runs the given animation.
     * The animation is displayed on the GUI with the specified frame rate.
     *
     * @param animation The animation to run.
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = THOUSAND / framesPerSecond;

        // Run the animation until it should stop
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // Start time of the frame
            DrawSurface d = gui.getDrawSurface();

            // Perform one frame of the animation
            animation.doOneFrame(d);

            // Show the updated frame on the GUI
            gui.show(d);

            // Calculate the time used for this frame
            long usedTime = System.currentTimeMillis() - startTime;

            // Calculate the remaining time to sleep to achieve the desired frame rate
            long milliSecondsLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondsLeftToSleep > 0) {
                // Sleep for the remaining time if it's positive
                this.sleeper.sleepFor(milliSecondsLeftToSleep);
            }
        }
    }
}
/**
 * @author Roei Mesilaty
 * @version 6.0 (current version number of program)
 * @since 2023-06-09
 * @StuID 315253336
 */
package Sprites;

import biuoop.DrawSurface;
import java.awt.Color;

/**
 * The OldLevelBackground class represents the background sprite for the Old Level.
 * It draws the specific background elements on the DrawSurface.
 */
public class Azure3Background extends AbstractSprite {
    /**
     * Draws the sprite on the given surface.
     * @param d the DrawSurface on which to draw the sprite.
     */
    @Override
    public void drawOn(DrawSurface d) {
        // Draw the dark gray border
        d.setColor(Color.DARK_GRAY);
        d.fillRectangle(0, 0, 800, 600);
        // Draw the lighter gray inner rectangle
        d.setColor(Color.GRAY);
        d.fillRectangle(0, 25, 800, 575);
        // Draw the navy blue playing area
        Color backgroundColor = new Color(0, 64, 64);
        d.setColor(backgroundColor);
        d.fillRectangle(30, 25, 745, 550);
        /// Lighthouse
        d.setColor(Color.BLACK);
        d.fillRectangle(70, 400, 120, 200);
        d.setColor(Color.WHITE);
        d.fillRectangle(85, 415, 90, 185);
        d.setColor(Color.BLACK);
        int x = 95;
        for (int i = 0; i < 4; i++) {
            d.fillRectangle(x, 415, 11, 185);
            x += 20;
        }
        int y = 435;
        for (int i = 0; i < 4; i++) {
            d.fillRectangle(85, y, 90, 11);
            y += 35;
        }
        d.setColor(Color.DARK_GRAY);
        d.fillRectangle(115, 330, 30, 70);
        d.setColor(Color.GRAY);
        d.fillRectangle(125, 180, 10, 150);

        // Create a Color object using RGB values
        Color creamColor = new Color(255, 253, 208);
        d.setColor(creamColor);
        d.fillCircle(130, 180, 12);
        d.setColor(Color.RED);
        d.fillCircle(130, 180, 9);
        d.setColor(Color.WHITE);
        d.fillCircle(130, 180, 4);

    }
}

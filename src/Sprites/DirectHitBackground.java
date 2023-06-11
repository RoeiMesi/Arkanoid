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
 * The DirectHitBackground class represents the background sprite for the Direct Hit level.
 * It draws the specific background elements on the DrawSurface.
 */
public class DirectHitBackground extends AbstractSprite {

    /**
     * Draws the sprite on the given surface.
     * @param d the DrawSurface on which to draw the sprite.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.fillRectangle(0, 18, d.getWidth(), d.getHeight());
        d.setColor(Color.BLUE);
        d.drawCircle(400, 162, 60);
        d.drawCircle(400, 162, 90);
        d.drawCircle(400, 162, 120);
        d.drawLine(400, 182, 400, 302);
        d.drawLine(420, 162, 540, 162);
        d.drawLine(380, 162, 260, 162);
        d.drawLine(400, 142, 400, 22);
    }
}

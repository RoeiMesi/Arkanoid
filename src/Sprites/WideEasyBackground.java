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
 * The DirectHitBackground class represents the background sprite for the Wide Easy level.
 * It draws the specific background elements on the DrawSurface.
 */
public class WideEasyBackground extends AbstractSprite {

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.lightGray.brighter());
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(new Color(99, 27, 133));
        d.fillRectangle(400, 300, 370, 270);
        d.fillRectangle(30, 30, 370, 270);
        d.fillRectangle(400, 300, 370, 270);

        d.setColor(new Color(128, 0, 0));
        d.fillRectangle(30, 300, 370, 270);
        d.fillRectangle(400, 30, 370, 270);
        int rightLineX = 780;
        for (int i = 0; i < 100; ++i) {
            d.setColor(new Color(238, 237, 179));
            d.drawLine(710, 80, rightLineX, 200);
            rightLineX -= 8;
        }
        d.setColor(new Color(255, 226, 24));
        d.fillCircle(710, 80, 30);
        d.setColor(new Color(236, 212, 73));
        d.fillCircle(710, 80, 25);
        d.setColor(new Color(238, 237, 179));
        d.fillCircle(710, 80, 20);
        int width = 26;
        int height = 26;
        int startingPointX = 100;
        int startingPointY = 50;
        d.setColor(Color.BLACK);
        d.fillRectangle(startingPointX + width, startingPointY, width * 12, height);
        d.setColor(Color.YELLOW);
        d.fillRectangle(startingPointX + width, startingPointY + height, 312, 286);
        // EYES
        d.setColor(Color.WHITE);
        d.fillRectangle(152, 128, 104, 104);
        d.fillRectangle(282, 128, 104, 104);
        d.setColor(Color.YELLOW);
        d.fillRectangle(230, 193, 52, 39);
        d.setColor(Color.BLACK);
        d.fillRectangle(243, 232, 39, 13);
        d.fillRectangle(256, 206, width, 13);
        d.fillRectangle(230, 219, 13, 13);
        d.fillRectangle(243, 193, 13, 13);
        d.fillRectangle(217, 193, 13, 26);
        d.fillRectangle(230, 180, 13, 13);
        d.fillRectangle(178, 154, height, width);
        d.fillRectangle(178, 180, height, width);
        d.fillRectangle(308, 154, height, width);
        d.fillRectangle(308, 180, height, width);
        d.fillRectangle(100, 76, width, 8 * height);
        d.fillRectangle(438, 76, width, 8 * height);
        // EYEBROWS RIGHT
        d.fillRectangle(330, 89, 13, 39);
        d.fillRectangle(304, 115, 13, 13);
        d.fillRectangle(291, 89, 13, 26);
        d.fillRectangle(356, 115, 13, 13);
        d.fillRectangle(369, 89, 13, 26);
        // EYEBROWS LEFT
        d.fillRectangle(226, 89, 13, 39);
        d.fillRectangle(200, 101, 13, 26);
        d.fillRectangle(187, 89, 13, 13);
        d.fillRectangle(161, 89, 13, 26);
        d.fillRectangle(174, 115, 13, 13);
        // SMILE
        d.fillRectangle(204, 271, 130, height / 2);
        d.fillRectangle(191, 258, width, height / 2);
        d.fillRectangle(321, 258, 39, height / 2);
        // TEETH
        d.setColor(Color.WHITE);
        d.fillRectangle(217, 284, height, width);
        d.fillRectangle(269, 284, height, width);
        // BLACK SIDES
        d.setColor(Color.BLACK);
        d.fillRectangle(113, 284, width, 7 * height);
        // RIGHT HAND
        d.fillRectangle(464, 271, 13, 13);
        d.setColor(Color.WHITE);
        d.fillRectangle(451, 284, width, 52);
        d.setColor(Color.BLACK);
        d.fillRectangle(477, 284, 13, 52);
        d.fillRectangle(451, 336, 39, 13);
        d.fillRectangle(464, 349, 13, 52);
        d.fillRectangle(451, 349, 13, 52);
        d.fillRectangle(477, 388, width, 13);
        d.setColor(Color.YELLOW);
        d.fillRectangle(464, 401, 39, height);
        d.setColor(Color.BLACK);
        d.fillRectangle(451, 401, 13, height);
        d.fillRectangle(503, 401, 13, height);
        d.fillRectangle(464, 427, 39, 13);
        // LEFT HAND
        d.setColor(Color.WHITE);
        d.fillRectangle(100, 284, 13, 52);
        d.setColor(Color.BLACK);
        d.fillRectangle(100, 336, 13, 13);
        d.fillRectangle(87, 284, 13, 130);
        d.setColor(Color.YELLOW);
        d.fillRectangle(100, 349, 13, 65);
        d.setColor(Color.BLACK);
        d.fillRectangle(100, 414, 13, 13);
        d.fillRectangle(425, 284, width, 7 * height);
        d.setColor(Color.WHITE);
        d.fillRectangle(139, 362, 286, height);
        Color brownColor = new Color(55, 19, 139);
        d.setColor(brownColor);
        d.fillRectangle(139, 388, 286, 65);
        d.fillRectangle(152, 453, 39, 39);
        d.fillRectangle(373, 453, 39, 39);
        d.setColor(Color.BLACK);
        d.fillRectangle(191, 388, 171, height);
        d.fillRectangle(191, 453, 184, height);
        d.fillRectangle(191, 479, width / 2, height);
        d.fillRectangle(362, 479, width / 2, height);
        d.fillRectangle(139, 492, 52, height / 2);
        d.fillRectangle(373, 492, 52, height / 2);
        d.fillRectangle(139, 453, width / 2, 39);
        d.fillRectangle(412, 453, width / 2, 39);
        // TIE
        d.setColor(Color.RED);
        d.fillRectangle(256, 362, 39, 65);
        // SHOES
        d.setColor(Color.GRAY);
        d.fillRectangle(159, 505, 26, 13);
        d.fillRectangle(380, 505, 26, 13);
        d.fillRectangle(133, 518, 52, 26);
        d.fillRectangle(353, 518, 52, 26);
        // INSIDE SHOE
        d.setColor(Color.BLACK);
        d.fillRectangle(146, 525, 26, 13);
        d.fillRectangle(366, 525, 26, 13);
    }
}

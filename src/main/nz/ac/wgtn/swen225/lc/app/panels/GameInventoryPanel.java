package nz.ac.wgtn.swen225.lc.app.panels;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.file.Path;

/**
 * Class responsible for containing a special part of the Panel, which contains the list of items the player has in
 * the inventory.
 */
public class GameInventoryPanel extends GridPanel {
    private final Point topLeft;

    /**
     * Constructor of the Inventory Panel, where the content of the Panel is loaded in.
     *
     * @param offsetWidth The distance between the left of the screen and the left this panel.
     *                    For use by the Renderer for determining the top-left corner of the Inventory panel.
     * @param offsetHeight The distance between the top of the screen and the top of this panel.
     *                     Also for use by the Renderer for determining the top-left corner of the Inventory panel.
     * @param width The width of the Inventory Panel.
     * @param height The preferred height of the Inventory Panel.
     */
    public GameInventoryPanel(int offsetWidth, int offsetHeight, int width, int height){
        super(Color.LIGHT_GRAY, width, height, 2, 3);
        topLeft = new Point(offsetWidth, offsetHeight);
    }

    /** @return The top-left position of the Inventory panel. */
    public Point getTopLeft(){ return topLeft; }
}

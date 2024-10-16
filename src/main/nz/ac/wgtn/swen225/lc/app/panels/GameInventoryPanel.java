package nz.ac.wgtn.swen225.lc.app.panels;

import java.awt.*;

/**
 * Class responsible for containing a special part of the Panel, which contains the list of items the player has in
 * the inventory.
 */
public class GameInventoryPanel extends GridPanel {

    /**
     * Constructor of the Inventory Panel, where the content of the Panel is loaded in.
     * TODO: Investigate how the "Inventory icons" will be loaded.
     *
     * @param width The width of the Inventory Panel.
     * @param height The preferred height of the Inventory Panel.
     */
    public GameInventoryPanel(int width, int height){
        super(Color.LIGHT_GRAY, width, height, 2, 3);
    }
}

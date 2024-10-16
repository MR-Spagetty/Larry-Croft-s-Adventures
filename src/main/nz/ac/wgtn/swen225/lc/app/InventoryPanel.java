package nz.ac.wgtn.swen225.lc.app;

import nz.ac.wgtn.swen225.lc.app.panels.GridPanel;

import java.awt.*;

/**
 * Class responsible for containing a special part of the Panel, which contains the list of items the player has in
 * the inventory.
 */
public class InventoryPanel extends GridPanel {

    /**
     * Constructor of the Inventory Panel, where the content of the Panel is loaded in.
     * TODO: Investigate how the "Inventory icons" will be loaded.
     */
    public InventoryPanel(int width, int height){
        super(Color.LIGHT_GRAY, width, height, 2, 3);
    }
}

package nz.ac.wgtn.swen225.lc.app.panels;

import java.awt.*;
import java.util.List;

import nz.ac.wgtn.swen225.lc.app.buttons.DefaultButton;

/**
 * Responsible for constructing parts of the Graphical User Interface used during gameplay. This
 * class also performs the updating of statistics related to the game, including the level of the game,
 * the number of chips left to collect, and the time remaining.
 */
public class GamePanel extends DefaultPanel {
    /**
     * Constructor which creates the menu containing information about the current game, and the buttons in the game.
     *
     * @param backgroundColor The background colour of the JPanel. Can be "null" if no
     *                         background is to be set.
     * @param width The preferred width of the JPanel.
     * @param height The preferred height of the JPanel.
     * @param mainUIButtons The buttons that are to be added into the panel that stores the buttons.
     */
    public GamePanel(Color backgroundColor, int width, int height, List<DefaultButton> mainUIButtons){
        super(backgroundColor, width, height);

        add(BorderLayout.NORTH, new GameInfoPanel(backgroundColor, width, (height/3)));

        GameButtonsPanel buttons = new GameButtonsPanel(backgroundColor, width, (height/10), 15f, mainUIButtons);
        add(BorderLayout.SOUTH, buttons);
    }
}
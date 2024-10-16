package nz.ac.wgtn.swen225.lc.app.panels;

import nz.ac.wgtn.swen225.lc.app.GameInventoryPanel;

import javax.swing.*;
import java.awt.*;

/**
 * A JPanel where information like the current level and the number of chips remaining is displayed.
 */
public class GameInfoPanel extends DefaultPanel {
    JLabel levelDisplay;
    JLabel timeDisplay;
    JLabel chipsLeftDisplay;

    /**
     * Constructor used to initialise the Game Information panel.
     *
     * @param backgroundColor The background colour of the JPanel. Can be "null" if no
     *                         background is to be set.
     * @param width The preferred width of the JPanel.
     * @param height The preferred height of the JPanel.
     */
    public GameInfoPanel(Color backgroundColor, int width, int height){
        super(backgroundColor, width, height);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        //A JLabel which literally is just for spacing out this panel with the rest.
        JLabel nothing = new CustomJLabel(" ");

        levelDisplay = new CustomJLabel("LEVEL: ");
        timeDisplay = new CustomJLabel("TIME: ");
        chipsLeftDisplay = new CustomJLabel("CHIPS LEFT: ");

        this.add(levelDisplay);
        this.add(timeDisplay);
        this.add(chipsLeftDisplay);
        this.add(nothing);
        this.add(new GameInventoryPanel(width, 60));
        this.add(nothing);
    }

    /**
     * A variation of a "JLabel" class which also sets the colour of the text, the font, and
     * the size.
     */
    private class CustomJLabel extends JLabel{
        public CustomJLabel(String text){
            super(text, SwingUtilities.CENTER);
            this.setFont(new Font("Comic Sans", Font.BOLD, 18));
            this.setForeground(Color.WHITE);
        }
    }
}
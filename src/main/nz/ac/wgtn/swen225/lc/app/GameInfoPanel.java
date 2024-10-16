package nz.ac.wgtn.swen225.lc.app;

import nz.ac.wgtn.swen225.lc.app.panels.DefaultPanel;

import javax.swing.*;
import java.awt.*;

/**
 * A JPanel where information like the current level and the number of chips remaining is displayed.
 */
public class GameInfoPanel extends DefaultPanel {

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

        JLabel levelDisplay = new CustomJLabel("LEVEL: ");
        JLabel timeDisplay = new CustomJLabel("TIME: ");
        JLabel chipsLeftDisplay = new CustomJLabel("CHIPS LEFT: ");
        JLabel nothing = new CustomJLabel(" ");

        this.add(levelDisplay);
        this.add(timeDisplay);
        this.add(chipsLeftDisplay);
        this.add(nothing);
        this.add(new InventoryPanel(width, 60));
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
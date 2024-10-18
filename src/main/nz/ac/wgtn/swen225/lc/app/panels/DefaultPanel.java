package nz.ac.wgtn.swen225.lc.app.panels;

import javax.swing.*;
import java.awt.*;

/**
 * A custom "Default Panel" class which allows for a Panel to created with custom dimensions
 * and a set background colour.
 *
 * @author Developer 1 <dev1@example.internal> - 300652265
 */
public class DefaultPanel extends JPanel {

    /**
     * Constructor used when we are making the Panel.
     *
     * @param backgroundColor The background colour of the JPanel. Can be "null" if no
     *                         background is to be set.
     * @param width The preferred width of the JPanel.
     * @param height The preferred height of the JPanel.
     */
    public DefaultPanel(Color backgroundColor, int width, int height){
        this.setBackground(backgroundColor);
        this.setPreferredSize(new Dimension(width, height));
    }
}
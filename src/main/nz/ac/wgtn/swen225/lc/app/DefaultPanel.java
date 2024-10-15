package nz.ac.wgtn.swen225.lc.app;

import javax.swing.*;
import java.awt.*;

/**
 * A custom "Default Panel" class which allows for a Panel to created with custom dimensions
 * and a set background colour.
 */
public class DefaultPanel extends JPanel {
    public DefaultPanel(Color backgroundColor, int width, int height){
        this.setBackground(backgroundColor);
        this.setPreferredSize(new Dimension(width, height));
    }
}
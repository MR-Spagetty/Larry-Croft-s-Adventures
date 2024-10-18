package nz.ac.wgtn.swen225.lc.app.panels;

import javax.swing.*;
import java.awt.*;

/**
 * Similar to the "Default Panel" class, but you're creating a panel arranged in a Grid-like fashion, rather
 * than in a normal fashion.
 *
 * @author Developer 1 <dev1@example.internal> - 300652265
 */
public class GridPanel extends JPanel{

    /**
     * Constructor used when you want the resulting Grid Panel to be of a preferred size in width and height.
     *
     * @param backgroundColor The background colour of the Grid JPanel. Can be "null" if no background is to
     *                        be set.
     * @param width The preferred width of the Grid JPanel.
     * @param height The preferred height of the Grid JPanel.
     * @param rows The number of rows in the "Grid" part of the JPanel.
     * @param cols The number of columns in the "Grid" part of the JPanel.
     */
    public GridPanel(Color backgroundColor, int width, int height, int rows, int cols){
        this(backgroundColor, rows, cols);
        this.setPreferredSize(new Dimension(width, height));
    }

    /**
     * Constructor used when the size of the resulting Grid Panel doesn't matter.
     *
     * @param backgroundColor The background colour of the Grid JPanel. Can be "null" if no background is to
     *                        be set.
     * @param rows The number of rows in the "Grid" part of the JPanel.
     * @param cols The number of columns in the "Grid" part of the JPanel.
     */
    public GridPanel(Color backgroundColor, int rows, int cols){
        this.setBackground(backgroundColor);
        this.setLayout(new GridLayout(rows, cols, 4, 4));
    }
}

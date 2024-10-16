package nz.ac.wgtn.swen225.lc.app.panels;

import javax.swing.*;
import java.awt.*;

/**
 * Similar to the "Default Panel" class, but you're creating a panel arranged in a Grid-like fashion, rather
 * than in a normal fashion.
 */
public class GridPanel extends JPanel{
    public GridPanel(Color backgroundColor, int width, int height, int rows, int cols){
        this(backgroundColor, rows, cols);
        this.setPreferredSize(new Dimension(width, height));
    }

    public GridPanel(Color backgroundColor, int rows, int cols){
        this.setBackground(backgroundColor);
        this.setLayout(new GridLayout(rows, cols, 4, 4));
    }
}

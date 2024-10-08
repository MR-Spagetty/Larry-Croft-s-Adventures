package nz.ac.wgtn.swen225.lc.app;

import javax.swing.*;
import java.awt.*;

public class GridPanel extends JPanel{
    public GridPanel(Color backgroundColor, int width, int height, int rows, int cols){
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(backgroundColor);
        this.setLayout(new GridLayout(rows, cols, 4, 4));
    }

    public GridPanel(int rows, int cols){ this.setLayout(new GridLayout(rows, cols, 4, 4)); }
}

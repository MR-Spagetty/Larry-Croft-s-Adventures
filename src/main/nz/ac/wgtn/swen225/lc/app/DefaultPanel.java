package nz.ac.wgtn.swen225.lc.app;

import javax.swing.*;
import java.awt.*;

public class DefaultPanel extends JPanel {
    public DefaultPanel(Color backgroundColor, int width, int height){
        this.setBackground(backgroundColor);
        this.setPreferredSize(new Dimension(width, height));
    }

    public DefaultPanel(){}
}
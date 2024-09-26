package nz.ac.wgtn.swen225.lc.app;

import javax.swing.*;
import java.awt.*;

/**
 * TODO: add comments and check with Renderer that this is OK in terms of displaying the Rendered game on the UI.
 */
public class GameDisplay extends JPanel {
    Renderer r = new Renderer();

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        r.render();
    }
}
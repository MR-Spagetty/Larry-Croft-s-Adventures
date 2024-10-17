package nz.ac.wgtn.swen225.lc.app.panels;

import javax.swing.JPanel;
import java.awt.*;

import nz.ac.wgtn.swen225.lc.renderer.*;
import nz.ac.wgtn.swen225.lc.domain.Point;

/**
 * The part of the main screen which displays the game.
 */
public class GameGraphicsPane extends JPanel {
    Renderer r = new Renderer();

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        r.render();
    }

    /** @return The center of the Graphics pane, in the form of a Point */
    public Point getMiddle(){
        return new Point(0, 0);
    }
}
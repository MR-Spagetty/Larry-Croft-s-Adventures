package nz.ac.wgtn.swen225.lc.app.panels;

import javax.swing.JPanel;
import java.awt.*;

import nz.ac.wgtn.swen225.lc.renderer.*;
import nz.ac.wgtn.swen225.lc.domain.Point;

/**
 * The part of the main screen which displays the game.
 */
public class GameGraphicsPane extends JPanel {
    //The class that stores the content to "render" to the graphics pane.
    Renderer r = new Renderer();

    private final int W; //The width of the Graphics pane
    private final int H; //The height of the Graphics pane

    public GameGraphicsPane(int width, int height){
        setPreferredSize(new Dimension(width, height));
        this.W = width;
        this.H = height;
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        r.render(g);
    }

    /** @return The center of the Graphics pane, in the form of a Point */
    public Point getMiddle(){ return new Point(W/2, H/2); }
}
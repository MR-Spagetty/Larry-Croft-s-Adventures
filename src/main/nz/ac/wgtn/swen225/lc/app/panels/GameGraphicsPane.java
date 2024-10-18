package nz.ac.wgtn.swen225.lc.app.panels;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Dimension;
import java.io.Serial;

import nz.ac.wgtn.swen225.lc.renderer.*;
import nz.ac.wgtn.swen225.lc.domain.Point;

/**
 * The part of the main screen (i.e: The Graphics Pane) which displays the game.
 *
 * @author Developer 1 <dev1@example.internal> - 300652265
 */
public class GameGraphicsPane extends JPanel {
    @Serial private static final long serialVersionUID= 1L;

    private final int W; //The width of the Graphics pane
    private final int H; //The height of the Graphics pane

    /**
     * Constructor used for creating the Graphics Pane.
     *
     * @param width The preferred width of the Graphics Pane.
     * @param height The preferred width of the Graphics Pane.
     */
    public GameGraphicsPane(int width, int height){
        setPreferredSize(new Dimension(width, height));
        this.W = width;
        this.H = height;
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Renderer.render(g);
    }

    /** @return The center of the Graphics pane, in the form of a Point */
    public Point getMiddle(){ return new Point(W/2, H/2); }
}
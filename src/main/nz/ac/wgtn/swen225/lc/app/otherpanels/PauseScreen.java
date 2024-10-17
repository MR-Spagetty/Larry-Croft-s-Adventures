package nz.ac.wgtn.swen225.lc.app.otherpanels;

import javax.swing.*;
import java.awt.*;

/**
 * Class which controls the "Pause Screen" Panel, which is a simple pop-up that shows that the game is paused.
 * This will be stored in a "JOptionPanel".
 */
public class PauseScreen extends JPanel{
    private static final PauseScreen PAUSE_SCREEN = new PauseScreen();
    public static PauseScreen pause = PAUSE_SCREEN;

    private PauseScreen(){
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(BorderLayout.NORTH, new JLabel("Current Game is Paused!"));
        this.add(BorderLayout.CENTER, new JLabel("Close the window or press 'ESC' to resume game!"));
    }
}
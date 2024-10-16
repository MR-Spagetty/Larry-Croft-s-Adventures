package nz.ac.wgtn.swen225.lc.app;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import nz.ac.wgtn.swen225.lc.app.panels.DefaultPanel;
import nz.ac.wgtn.swen225.lc.domain.*;

/**
 * Responsible for constructing parts of the Graphical User Interface used during gameplay. This
 * class also performs the updating of statistics related to the game, including the level of the game,
 * the number of chips left to collect, and the time remaining.
 */
public class GameUI extends DefaultPanel {
    /**
     * Constructor which creates the menu containing information about the current game, and the buttons in the game.
     */
    public GameUI(Color backgroundColor, int width, int height, List<DefaultButton> mainUIButtons){
        super(backgroundColor, width, height);

        add(BorderLayout.NORTH, new GameInfo(backgroundColor, width, (height/3)));

        GameButtons buttons = new GameButtons(backgroundColor, width, (height/10), 15f, mainUIButtons);
        add(BorderLayout.SOUTH, buttons);
    }

    /**
     * Creates a timer which refreshes the Graphics pane every time a tick occurs.
     * TODO: test setup to make sure it works as expected.
     *
     * @param gameDisplay The graphics display that will need to be refreshed by the timer.
     * @return The timer that will refresh the graphics display every few seconds.
     */
    public Timer createTimer(GraphicsPane gameDisplay){
        return new Timer(GameState.DEFAULT_TICK_RATE, unused->{
            assert SwingUtilities.isEventDispatchThread();
            gameDisplay.repaint();
        });
    }
}
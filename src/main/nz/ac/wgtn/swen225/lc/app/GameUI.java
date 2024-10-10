package nz.ac.wgtn.swen225.lc.app;

import javax.swing.*;
import java.awt.*;

import nz.ac.wgtn.swen225.lc.domain.*;

/**
 * Responsible for constructing parts of the Graphical User Interface used during gameplay. This
 * class also performs the updating of statistics related to the game, including the level of the game,
 * the number of chips left to collect, and the time remaining.
 */
public class GameUI {
    Color backgroundColor = Color.DARK_GRAY; //The Background colour of the UI.

    int width, height;

    public GameUI(int width, int height){
        this.width = width;
        this.height = height;
    }

    /**
     * Creates the menu containing information about the current game, and the buttons in the game.
     * TODO: Make sure we're satisfied with aspects of the Border.
     */
    public JPanel createMenu(){
        JPanel menu = new DefaultPanel(backgroundColor, width, height);
        menu.add(BorderLayout.NORTH, new GameInfo(backgroundColor, width, (height/3)));

        GameButtons buttons = GameButtons.gameButtons;
        buttons.constructPanel(backgroundColor, width, (height/2), 15f);

        menu.add(BorderLayout.SOUTH, buttons);

        return menu;
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
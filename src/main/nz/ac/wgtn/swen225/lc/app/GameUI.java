package nz.ac.wgtn.swen225.lc.app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import nz.ac.wgtn.swen225.lc.domain.*;

/**
 * Responsible for constructing parts of the Graphical User Interface used during gameplay. This
 * class also performs the updating of statistics related to the game, including the level of the game,
 * the number of chips left to collect, and the time remaining.
 */
public class GameUI {
    //The Screen that will be displayed when the game is paused.
    static PauseScreen ps = new PauseScreen(200);

    /**
     * Creates the menu containing information about the current game, and the buttons in the game.
     * TODO: Make sure we're satisfied with aspects of the Border.
     */
    public static JPanel createMenu(){
        JPanel menu = new JPanel();
        menu.setPreferredSize(new Dimension(150, 400));
        menu.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        menu.setLayout(new BoxLayout(menu, BoxLayout.Y_AXIS)); /* ??? */

        //menu.add(createGameInfo());
        menu.add(createGameButtons());

        return menu;
    }

    /**
     * TODO add comments and take action on todos below.
     */
    public static JPanel createGameInfo(){
        JPanel GameInfo = new JPanel();
        /** TODO Display the number of levels here */
        /** TODO Display the number of chips left to collect */
        /** TODO Display the time remaining */

        return GameInfo;
    }

    /**
     * Creates a timer which refreshes the Graphics pane every time a tick occurs.
     * TODO: test setup to make sure it works as expected.
     *
     * @param gameDisplay The graphics display that will need to be refreshed by the timer.
     * @return The timer that will refresh the graphics display every few seconds.
     */
    public static Timer createTimer(GraphicsPane gameDisplay){
        return new Timer(GameState.DEFAULT_TICK_RATE, unused->{
            assert SwingUtilities.isEventDispatchThread();
            gameDisplay.repaint();
        });
    }

    /**
     * Creates and returns the JPanel that will hold buttons that perform specific actions in relation
     * to the game and the GUI.
     * TODO add actions for "exit" "save" and "help".
     */
    public static JPanel createGameButtons(){
        JButton pauseGame = createButtonWithAction(unused -> ps.showScreen(), "PAUSE");
        JButton exitGame = createButtonWithAction(unused -> {}, "EXIT");
        JButton saveGame = createButtonWithAction(unused -> {}, "SAVE");;
        JButton displayHelp = createButtonWithAction(unused -> {}, "HELP");;

        JPanel gameButtons = new JPanel();

        gameButtons.add(pauseGame);
        gameButtons.add(exitGame);
        gameButtons.add(saveGame);
        gameButtons.add(displayHelp);

        return gameButtons;
    }

    /**
     * Small helper method which creates a new button and adds an action to it.
     */
    private static JButton createButtonWithAction(ActionListener al, String text){
        JButton newButton = new JButton(text);
        newButton.addActionListener(al);

        return newButton;
    }
}
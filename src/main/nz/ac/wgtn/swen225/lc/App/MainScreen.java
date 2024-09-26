package nz.ac.wgtn.swen225.lc.app;

import javax.swing.*;
import java.awt.event.ActionListener;


/**
 * TODO add comments
 */
public class MainScreen {
    //The Screen that will be displayed when the game is paused.
    static PauseScreen ps = new PauseScreen(200);

    public static JPanel createGameInfo(){
        JPanel GameInfo = new JPanel();
        /** TODO Display the number of levels here */
        /** TODO Display the number of chips left to collect */
        /** TODO Display the time remaining */

        return GameInfo;
    }

    public static JPanel createGameButtons(){
        JButton pauseGame = createButtonWithAction(unused -> ps.showScreen());
        JButton exitGame = new JButton();
        JButton saveGame = new JButton();

        JButton displayHelp = new JButton();

        JPanel gameButtons = new JPanel();
        gameButtons.add(pauseGame);

        return gameButtons;
    }

    /**
     * Small helper method which creates a new button and adds an action to it.
     */
    private static JButton createButtonWithAction(ActionListener al){
        JButton newButton = new JButton();
        newButton.addActionListener(al);

        return newButton;
    }
}
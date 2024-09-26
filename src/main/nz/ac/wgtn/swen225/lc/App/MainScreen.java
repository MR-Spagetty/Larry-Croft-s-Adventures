package nz.ac.wgtn.swen225.lc.app;

import javax.swing.*;
import java.awt.event.ActionListener;

import nz.ac.wgtn.swen225.lc.domain.*;

/**
 * TODO add comments
 */
public class MainScreen {
    //The Screen that will be displayed when the game is paused.
    static PauseScreen ps = new PauseScreen(200);

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
     * TODO add comments and actions for "exit" "save" and "help".
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
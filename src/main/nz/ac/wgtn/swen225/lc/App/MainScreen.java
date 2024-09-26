package nz.ac.wgtn.swen225.lc.app;

import javax.swing.*;
import java.awt.event.ActionListener;


/**
 * TODO add comments
 */
class MainScreen extends GameGUI {
    //The Screen that will be displayed when the game is paused.
    static PauseScreen ps = new PauseScreen(200);

    /**
     * Constructor for the Game GUI.
     */
    public MainScreen(){
        assert SwingUtilities.isEventDispatchThread();

        createGameInfo();
        createGameButtons();

        new ControlKeys(); //Initialises the class specifically for controlling the keys.
    }

    public void createGameInfo(){
        JPanel GameInfo = new JPanel();
        /** TODO Display the number of levels here */
        /** TODO Display the number of chips left to collect */
        /** TODO Display the time remaining */
    }

    public void createGameButtons(){
        JButton pauseGame = createButtonWithAction(unused -> ps.showScreen());
        JButton exitGame = new JButton();
        JButton saveGame = new JButton();

        JButton displayHelp = new JButton();

        JPanel gameButtons = new JPanel();
        gameButtons.add(pauseGame);
    }

    /**
     * Small helper method which creates a new button and adds an action to it.
     */
    private JButton createButtonWithAction(ActionListener al){
        JButton newButton = new JButton();
        newButton.addActionListener(al);

        return newButton;
    }
}
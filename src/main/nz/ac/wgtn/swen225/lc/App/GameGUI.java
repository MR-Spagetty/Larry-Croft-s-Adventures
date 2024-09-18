package nz.ac.wgtn.swen225.lc.App;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class GameGUI extends JFrame{
    /*
    * Action to be executed when the user closes the Game GUI with the 'X' button.
    * This action will be mostly similar to quitting the current game playing, as you will also be
    * asked whether you want to save the game before quitting.
    *
    * TODO Finish work on "closeGame" action.
    */
    Runnable closeGame= ()->{};

    /**
     * The Screen that will be displayed when the game is paused.
     */
    static PauseScreen ps = new PauseScreen(200);

    /**
     * Constructor for the Game GUI.
     */
    public GameGUI(){
        assert SwingUtilities.isEventDispatchThread();

        setVisible(true);
        addWindowListener(new WindowAdapter(){
            public void windowClosed(WindowEvent e){ closeGame.run(); }
        });

        ControlKeys keys = new ControlKeys();
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
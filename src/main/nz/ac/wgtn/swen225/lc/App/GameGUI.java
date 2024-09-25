package nz.ac.wgtn.swen225.lc.App;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class GameGUI extends JFrame{
    /*
    * Action to be executed when the user closes the Game GUI with the 'X' button.
    * This action will be mostly similar to quitting the current game playing, as you will also be
    * asked whether you want to save the game before quitting.
    *
<<<<<<< HEAD
    * TODO Finish work on "closeGame" action.
=======
    * todo: Finish work on "closeGame" action.
>>>>>>> 10e265a33b0dba697421c1efb5e9365dfbaf9dca
    */
    Runnable closeGame= ()->{};

    /**
     * Constructor for the Game GUI.
     */
    public GameGUI(){
        assert SwingUtilities.isEventDispatchThread();

        setVisible(true);
        addWindowListener(new WindowAdapter(){
            public void windowClosed(WindowEvent e){ closeGame.run(); }
        });
    }

    public void createGameInfo(){
<<<<<<< HEAD
        JPanel GameInfo = new JPanel();
        /** TODO Display the number of levels here */
        /** TODO Display the number of chips left to collect */
        /** TODO Display the time remaining */
=======
        MyJPanel GameInfo = new MyJPanel();
        /** todo Display the number of levels here */
        /** todo Display the number of chips left to collect */
        /** todo Display the time remaining */
>>>>>>> 10e265a33b0dba697421c1efb5e9365dfbaf9dca
    }

    public void createGameButtons(){
        JButton exitGame = new JButton();
        JButton saveGame = new JButton();
        JButton displayHelp = new JButton();

        JPanel gameButtons = new JPanel();
        gameButtons.add(exitGame);
    }
}
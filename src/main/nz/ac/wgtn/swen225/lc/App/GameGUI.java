package nz.ac.wgtn.swen225.lc.app;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author Developer 1 <dev1@example.internal>
 */
public class GameGUI extends JFrame{
<<<<<<< HEAD
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
=======
    /**
     * Action to be executed when the user closes the Game GUI with the 'X' button.
     * This action will be mostly similar to quitting the current game playing, as you will also be
     * asked whether you want to save the game before quitting.
     *
     * TODO Finish work on "closeGame" action.
     */
>>>>>>> cec60e477dee09da3a17fc3144292e517c8ed323
    Runnable closeGame= ()->{};

    //The Screen that will be displayed when the game is paused.
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
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> cec60e477dee09da3a17fc3144292e517c8ed323
        JPanel GameInfo = new JPanel();
        /** TODO Display the number of levels here */
        /** TODO Display the number of chips left to collect */
        /** TODO Display the time remaining */
<<<<<<< HEAD
=======
        MyJPanel GameInfo = new MyJPanel();
        /** todo Display the number of levels here */
        /** todo Display the number of chips left to collect */
        /** todo Display the time remaining */
>>>>>>> 10e265a33b0dba697421c1efb5e9365dfbaf9dca
=======
>>>>>>> cec60e477dee09da3a17fc3144292e517c8ed323
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
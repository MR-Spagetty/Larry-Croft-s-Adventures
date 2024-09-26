package nz.ac.wgtn.swen225.lc.app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * TODO add comments
 *
 * @author Developer 1 <dev1@example.internal>
 */
public class GameGUI extends JFrame{
    /*
     * Action to be executed when the user closes the Game GUI with the 'X' button.
     * This action will be mostly similar to quitting the current game playing, as you will also be
     * asked whether you want to save the game before quitting.
     */
    Runnable closeGame= ()->{};

    /**
     * TODO add comments
     */
    public GameGUI(){
        assert SwingUtilities.isEventDispatchThread();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(1200, 600));

        addWindowListener(new WindowAdapter(){
            public void windowClosed(WindowEvent e){ closeGame.run(); }
        });

        new StartScreen(); //Calls constructor for the Start Screen.

        pack();
        setVisible(true);
    }

    /**
     * TODO add comments
     */
    protected void changeGUIStyles(){
        invalidate();
        removeAll(); //Will remove all the Start Screen components, so we can add the other components in!
        validate();
        System.out.println("Created the Game GUI."); //TODO delete this!

        new MainScreen();
    }
}
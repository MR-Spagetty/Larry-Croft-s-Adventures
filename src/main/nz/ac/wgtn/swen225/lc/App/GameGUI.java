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

        createStartMenu();

        pack();
        setVisible(true);
    }

    /**
     * Helper method that creates the "components" that will be in the Start Menu.
     */
    private void createStartMenu(){
        add(BorderLayout.NORTH, StartScreen.instructions);

        add(
                BorderLayout.CENTER,
                StartScreen.createButtonsSection((unused -> changeGUIStyles()), (unused -> {}))
        );
    }

    /**
     * TODO add comments
     */
    private void createMainMenu(){
        add(BorderLayout.EAST, MainScreen.createGameButtons());

        new ControlKeys(); //Initialises the class specifically for controlling the keys.
    }

    /**
     * TODO add comments
     */
    private void changeGUIStyles(){
        removeAll(); //Will remove all the Start Screen components, so we can add the other components in!
        invalidate();
        validate();

        createMainMenu();
    }
}
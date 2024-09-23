package nz.ac.wgtn.swen225.lc.app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Class which controls the "Start Menu" GUI, including the Buttons and their corresponding actions.
 */
public class StartScreen extends JFrame{
    Runnable closeGame= ()->{}; //Action to be executed when the user closes the Game GUI with the 'X' button.

    /**
     * Constructor for the "Start Menu", which involves setting up the frame and the buttons that will be inside the
     * frame. It also makes sure that the JFrame is disposed when the user closes the window, and that the game will
     * be running on the "Event Dispatch" thread.
     */
    public StartScreen(){
        assert SwingUtilities.isEventDispatchThread();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        createStartMenu();

        setPreferredSize(new Dimension(800, 400));
        pack();
        setVisible(true);
    }

    /**
     * Helper method that creates the "components" that will be in the Game GUI.
     */
    private void createStartMenu(){
        add(BorderLayout.NORTH, new JLabel("Instructions go here!"));
        add(BorderLayout.CENTER, createButtonsSection());

        addWindowListener(new WindowAdapter(){
            public void windowClosed(WindowEvent e){ closeGame.run(); }
        });
    }

    /**
     * Creates a "JPanel" that will hold the buttons of the Start Menu. One of the buttons will start the game
     * itself, and if a game has not been loaded (which is loaded by pressing the "Load" button), a new game will
     * be initiated.
     */
    private JPanel createButtonsSection(){
        JPanel buttons = new JPanel();
        JButton start = new JButton("Start the Game!");
        JButton load = new JButton("Load existing game!");

        buttons.add(start);
        buttons.add(load);

        start.addActionListener(unused -> runGame());
        start.addActionListener(unused -> loadGame());

        return buttons;
    }

    private void runGame(){
        /** TODO Create new game file and then run game, if specified game file is blank! */
        System.out.println("Test");
    }

    /**
     * Loads an existing game from a ".json" file.
     */
    private void loadGame(){
        /** TODO If neccesary, create a file chooser for selecting a game file. */
    }
}
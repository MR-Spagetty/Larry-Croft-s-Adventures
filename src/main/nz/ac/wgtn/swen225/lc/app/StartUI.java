package nz.ac.wgtn.swen225.lc.app;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * Class which controls the "Start Menu" GUI, including the Buttons and their corresponding actions.
 */
public class StartUI{
    static JLabel instructions = new JLabel("Instructions go here!");

    /**
     * Creates a "JPanel" that will hold the buttons of the Start Menu. One of the buttons will start the game
     * itself, and if a game has not been loaded (which is loaded by pressing the "Load" button), a new game will
     * be initiated.
     */
    public static JPanel createButtonsSection(ActionListener onStart){
        JPanel buttons = new JPanel();
        JButton start = new JButton("Start the Game!");
        JButton load = new JButton("Load existing game!");

        buttons.add(start);
        buttons.add(load);

        start.addActionListener(onStart);
        load.addActionListener(unused -> loadGame());

        return buttons;
    }

    /**
     * Loads an existing game from a ".json" file.
     */
    public static void loadGame(){
        /** TODO If neccesary, create a file chooser for selecting a game file. */
        System.out.println("Feature to load game has not been implemented yet.");
    }
}
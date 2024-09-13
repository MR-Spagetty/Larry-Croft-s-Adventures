package nz.ac.wgtn.swen225.lc.App;

import javax.swing.*;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class StartMenu
{
    /** The below code is dependent on whether a new game is being made from a .JSON file. */
    Runnable createNewGame; //Program to execute when a new game is created.
    Runnable loadExistingGame; //Program

    public StartMenu(){
        assert SwingUtilities.isEventDispatchThread();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        createStartMenu();

        addWindowListener(new WindowAdapter(){
            public void windowClosed(WindowEvent e){ closePhase.run(); }
        });

        //setPreferredSize(new Dimension(800, 400));
        //pack();
    }

    public void createStartMenu(){
        JTextArea instructions = new JTextArea("Instructions go here!");
        JButton start = new JButton("Start the Game!");
        JButton load = new JButton("Load existing game!");
    }

    public void runGame(){
        /** todo */
    }
}
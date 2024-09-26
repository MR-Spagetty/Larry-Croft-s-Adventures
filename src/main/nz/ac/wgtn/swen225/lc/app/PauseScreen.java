package nz.ac.wgtn.swen225.lc.app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Class which controls the "Pause Screen" GUI, which is a simple menu that shows that the game is paused.
 */
public class PauseScreen extends JFrame{
    /**
     * Constructor for the "Pause Screen", which is similar to the constructor for making the "Start Screen".
     */
    public PauseScreen(int sizeOfScreen){
        assert SwingUtilities.isEventDispatchThread();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        add(BorderLayout.NORTH, new JLabel("Current Game is Paused!"));
        add(BorderLayout.CENTER, new JLabel("Close the window or press 'ESC' to resume game!"));

        addWindowListener(new WindowAdapter(){
            public void windowClosed(WindowEvent e){ hideScreen(); }
        });

        setPreferredSize(new Dimension(sizeOfScreen, sizeOfScreen));
        pack();
    }

    public void showScreen(){
        setVisible(true);
        GameGUI.timer.stop();
    }

    public void hideScreen(){
        setVisible(false);
        GameGUI.timer.start();
    }
}
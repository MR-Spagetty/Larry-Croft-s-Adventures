package nz.ac.wgtn.swen225.lc.App;

import javax.swing.*;

public class GameGUI{
    public GameGUI(){
        assert SwingUtilities.isEventDispatchThread();
    }

    public void createGameInfo(){
        MyJPanel GameInfo = new MyJPanel();
        /** todo Display the number of levels here */
        /** todo Display the number of chips left to collect */
    }

    public void createGameButtons(){
        JPanel gameButtons = new JPanel();

        JButton pauseAndExit = new JButton();
        JButton saveGame = new JButton();
        JButton displayHelp = new JButton();
    }
}
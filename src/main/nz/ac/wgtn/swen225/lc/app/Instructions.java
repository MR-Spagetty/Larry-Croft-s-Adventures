package nz.ac.wgtn.swen225.lc.app;

import javax.swing.*;

public class Instructions extends DefaultPanel{
    public Instructions(){

        this.add(new JLabel("HOW TO PLAY THE GAME:"));
        this.add(new JLabel(" - Use the Arrow keys to move Larry Croft around!"));
        this.add(new JLabel(" - YOUR AIM: Collect all of the \"Chips,\" and find the Exit! Dot it all before time runs out!"));
        this.add(new JLabel(" - Press \"SPACE\" to pause the game currently playing!"));
        this.add(new JLabel(" - Press \"CTRL + X\" to quit the current game without saving."));
        this.add(new JLabel(" - Press \"CTRL + S\" to save and exit the current game."));
        this.add(new JLabel(" - Press \"CTRL + 1\" to start a new game at Level 1."));
        this.add(new JLabel(" - Press \"CTRL + 2\" to start a new game at Level 2."));
    }
}
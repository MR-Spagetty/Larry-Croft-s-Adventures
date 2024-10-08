package nz.ac.wgtn.swen225.lc.app;

import javax.swing.*;
import java.awt.*;

public class Instructions extends DefaultPanel{
    public Instructions(){
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(createLabel("HOW TO PLAY THE GAME:"));
        this.add(createLabel(" - Use the Arrow keys to move Larry Croft around!"));
        this.add(createLabel(" - YOUR AIM: Collect all of the \"Chips,\" and find the Exit! Dot it all before time runs out!"));
        this.add(createLabel(" - Press \"SPACE\" to pause the game currently playing!"));
        this.add(createLabel(" - Press \"CTRL + X\" to quit the current game without saving."));
        this.add(createLabel(" - Press \"CTRL + S\" to save and exit the current game."));
        this.add(createLabel(" - Press \"CTRL + 1\" to start a new game at Level 1."));
        this.add(createLabel(" - Press \"CTRL + 2\" to start a new game at Level 2."));
    }

    private JLabel createLabel(String text){
        JLabel returnLabel = new JLabel(text);
        returnLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        return returnLabel;
    }
}
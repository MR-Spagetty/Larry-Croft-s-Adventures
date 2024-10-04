package nz.ac.wgtn.swen225.lc.app;

import javax.swing.*;
import java.awt.*;

/**
 * A JPanel where information like the current level and the number of chips remaining is displayed.
 */
public class GameInfo extends DefaultPanel{
    public GameInfo(Color backgroundColor, int width, int height){
        super(backgroundColor, width, height);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel levelDisplay = new CustomJLabel("LEVEL: ");
        JLabel timeDisplay = new CustomJLabel("TIME: ");
        JLabel chipsLeftDisplay = new CustomJLabel("CHIPS LEFT: ");

        this.add(levelDisplay);
        this.add(timeDisplay);
        this.add(chipsLeftDisplay);
    }

    /**
     * A variation of a "JLabel" class which also sets the colour of the text, the font, and
     * the size.
     * TODO: Get opinions on fonts and try and get them working.
     */
    private class CustomJLabel extends JLabel{
        public CustomJLabel(String text){
            super(text);
            this.setFont(new Font("Comic Sans", Font.BOLD, 18));
            this.setForeground(Color.WHITE);
        }
    }
}
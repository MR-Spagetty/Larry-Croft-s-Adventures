package nz.ac.wgtn.swen225.lc.app;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Holds the buttons that perform specific actions in relation to the game and the GUI.
 */
public class GameButtons extends GridPanel{
    public GameButtons(Color backgroundColor, int width, int height, float fontSize, List<DefaultButton> buttonsToAdd){
        super(backgroundColor, buttonsToAdd.size(), 1);

        buttonsToAdd.forEach(b -> {
            b.setPreferredSize(new Dimension(width, height));
            if (b.getText() != null) b.setFont(b.getFont().deriveFont(fontSize));
            this.add(b);
        });
    }
}
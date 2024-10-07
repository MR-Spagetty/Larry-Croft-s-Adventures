package nz.ac.wgtn.swen225.lc.app;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

/**
 * Class which holds the buttons that perform specific actions in relation to the game and the GUI.
 */
public class GameButtons extends GridPanel{
    public GameButtons(Color backgroundColor, int width, int height, Map<String, DefaultButton> buttonsToAdd){
        super(backgroundColor, width, height, 5, 1);

        this.add(buttonsToAdd.get("PAUSE"));
        this.add(buttonsToAdd.get("EXIT"));
        this.add(buttonsToAdd.get("HELP"));
    }
}
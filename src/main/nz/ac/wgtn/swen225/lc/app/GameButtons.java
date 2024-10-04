package nz.ac.wgtn.swen225.lc.app;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

/**
 * Class which holds the buttons that perform specific actions in relation to the game and the GUI.
 */
public class GameButtons extends DefaultPanel{
    public GameButtons(Color backgroundColor, int width, int height, int gridHeight, Map<String, DefaultButton> buttonsToAdd){
        super(backgroundColor, width, height);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        GridPanel controlGameButtons = new GridPanel(backgroundColor, width, gridHeight, 1, 3);
        controlGameButtons.add(buttonsToAdd.get("PAUSE"));
        controlGameButtons.add(buttonsToAdd.get("EXIT"));
        controlGameButtons.add(buttonsToAdd.get("HELP"));

        this.add(new RecordAndSavePanel());
        this.add(controlGameButtons);
    }
}
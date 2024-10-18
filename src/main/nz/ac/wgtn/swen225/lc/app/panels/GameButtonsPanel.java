package nz.ac.wgtn.swen225.lc.app.panels;

import nz.ac.wgtn.swen225.lc.app.buttons.DefaultButton;

import java.awt.*;
import java.util.List;

/**
 * Holds the buttons that perform specific actions in relation to the game and the GUI.
 *
 * @author Developer 1 <dev1@example.internal> - 300652265
 */
public class GameButtonsPanel extends GridPanel {

    /**
     * Constructor used when creating the Game Buttons JPanel.
     *
     * @param backgroundColor The background colour of the JPanel. Can be "null" if no
     *                        background is to be set.
     * @param buttonWidth The preferred width of the Buttons in the JPanel.
     * @param buttonHeight The preferred height of the Buttons in the JPanel.
     * @param buttonFontSize The size of the text in a Button in the JPanel, if text is available.
     * @param buttonsToAdd The buttons that are to be added into the JPanel.
     */
    public GameButtonsPanel(Color backgroundColor, int buttonWidth, int buttonHeight, float buttonFontSize, List<DefaultButton> buttonsToAdd){
        super(backgroundColor, buttonsToAdd.size(), 1);

        buttonsToAdd.forEach(b -> {
            b.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
            if (b.getText() != null) b.setFont(b.getFont().deriveFont(buttonFontSize));
            this.add(b);
        });
    }
}
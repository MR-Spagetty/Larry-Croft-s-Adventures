package nz.ac.wgtn.swen225.lc.app;

import nz.ac.wgtn.swen225.lc.app.panels.GridPanel;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Holds the buttons that perform specific actions in relation to the game and the GUI.
 */
public class GameButtons extends GridPanel {

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
    public GameButtons(Color backgroundColor, int buttonWidth, int buttonHeight, float buttonFontSize, List<DefaultButton> buttonsToAdd){
        super(backgroundColor, buttonsToAdd.size(), 1);

        buttonsToAdd.forEach(b -> {
            b.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
            if (b.getText() != null) b.setFont(b.getFont().deriveFont(buttonFontSize));
            this.add(b);
        });
    }
}

/**
 * A separate class that is also related to the "Game Buttons" class. Here, it contains static methods that create the buttons
 * that are to be used in the Main User Interface!
 */
class UIButtons {

    /**
     * Returns a list (rather than a panel) of the main UI buttons in the game. The actual buttons
     * themselves are made in two methods, with one requiring the supplement of the actions each button
     * will execute. (As they perform actions needed in different parts of the code.)
     *
     * @param save The action that will be executed when the user hits the "Save and Exit" button.
     * @param exit The action that will be executed when the user hits the "Exit" button.
     * @return The list of the main UI buttons in the game.
     */
    public static List<DefaultButton> mainUIButtons(Runnable save, Runnable exit){
        List<DefaultButton> pauseAndHelp = pauseAndHelpButtons();
        List<DefaultButton> saveAndRec = saveAndExitButtons(save, exit);

        return new ArrayList<>(){{
            addAll(pauseAndHelp);
            addAll(saveAndRec);
        }};
    }

    /**
     * Creates the "Save", "Exit" and "Record" buttons, which map to actions defined in another class, and are taken in as
     * parameters.
     *
     * @param save The action that will be executed when the user hits the "Save and Exit" button.
     * @param exit The action that will be executed when the user hits the "Exit" button.
     * @return A list consisting of the "Pause" and "Help" buttons, each wired up to their appropriate action.
     */
    private static List<DefaultButton> saveAndExitButtons(Runnable save, Runnable exit){
        DefaultButton saveGame = new DefaultButton(unused -> save.run(), "SAVE & EXIT");
        DefaultButton exitGame = new DefaultButton(unused -> exit.run(), "EXIT");

        return List.of(saveGame, exitGame);
    }

    /**
     * Creates the "Pause" and "Help" buttons. These do NOT require any action listeners to be taken in as they
     * do not have any effect on the layout of the GUI or (for the most part) the functioning of the game.
     *
     * @return A list consisting of the "Pause" and "Help" buttons, each wired up to their appropriate action.
     */
    private static List<DefaultButton> pauseAndHelpButtons(){
        DefaultButton pauseGame = new DefaultButton(unused -> IOController.ic.pauseGame(), "PAUSE");
        DefaultButton displayHelp = new DefaultButton(unused -> IOController.ic.createHelpDialog(), "HELP");

        return List.of(pauseGame, displayHelp);
    }
}
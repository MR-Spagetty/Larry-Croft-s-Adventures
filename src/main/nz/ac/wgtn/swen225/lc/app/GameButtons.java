package nz.ac.wgtn.swen225.lc.app;

import javax.swing.*;
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

/**
 * A separate class that is also related to the "Game Buttons" class. Here, it contains static methods that create the buttons
 * that are to be used in the Main User Interface!
 */
class UIButtons {
    private static final String IMG_URL = "src/main/nz/ac/wgtn/swen225/lc/app/assets/";

    /**
     * Returns a list (rather than a panel) of the main UI buttons in the game. The actual buttons
     * themselves are made in two methods, with one requiring the supplement of the actions each button
     * will execute. (As they perform actions needed in different parts of the code.)
     */
    public static List<DefaultButton> mainUIButtons(Runnable save, Runnable exit, Runnable record){
        List<DefaultButton> pauseAndHelp = pauseAndHelpButtons();
        List<DefaultButton> saveAndRec = saveAndRecButtons(save, exit, record);

        return new ArrayList<>(){{
            addAll(pauseAndHelp);
            addAll(saveAndRec);
        }};
    }

    /**
     * Creates the "Save", "Exit" and "Record" buttons, which map to actions defined in another class, and are taken in as
     * parameters.
     *
     * @return A list consisting of the "Pause" and "Help" buttons, each wired up to their appropriate action.
     */
    private static List<DefaultButton> saveAndRecButtons(Runnable save, Runnable exit, Runnable record){
        DefaultButton saveGame = new DefaultButton(unused -> save.run(), "SAVE");
        DefaultButton exitGame = new DefaultButton(unused -> exit.run(), "EXIT");
        DefaultButton recordButton = new DefaultButton(unused -> record.run(), new ImageIcon(IMG_URL + "record.png"));

        return List.of(recordButton, saveGame, exitGame);
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

    /**
     * Creates the "JPanel" that will hold the buttons of the Start Menu. One of the buttons will start a new
     * game for the player, and the other will allow the player to select an existing game to resume.
     */
    public static JPanel startUIButtonPanel(Runnable startGame, Runnable resumeGame){
        JPanel buttons = new JPanel();

        buttons.add(new DefaultButton(unused -> startGame.run(), "Start new game!"));
        buttons.add(new DefaultButton(unused -> resumeGame.run(), "Resume existing game!"));

        return buttons;
    }
}
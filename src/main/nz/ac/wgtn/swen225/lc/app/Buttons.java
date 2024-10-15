package nz.ac.wgtn.swen225.lc.app;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Buttons{
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
}
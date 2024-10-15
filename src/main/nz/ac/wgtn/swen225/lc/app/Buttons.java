package nz.ac.wgtn.swen225.lc.app;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Buttons{
    private final static String IMG_URL = "src/main/nz/ac/wgtn/swen225/lc/app/assets/";

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
        DefaultButton pauseGame = new DefaultButton(unused -> pauseGame(), "PAUSE");
        DefaultButton displayHelp = new DefaultButton(unused -> createHelpDialog(), "HELP");

        return List.of(pauseGame, displayHelp);
    }

    /**
     * Creates the pop-up window that re-iterates the instructions that apply to the game.
     * The method is stated here as it links up to the action of a button!
     */
    private static void createHelpDialog(){
        JOptionPane.showMessageDialog(null, Instructions.instructionsPanel, "Help", JOptionPane.PLAIN_MESSAGE);
    }

    /**
     * Pauses the game currently in progress and creates a pop-up window which indicates that the game is paused.
     * When closed (either by hitting "ESC" or the "Return to Game" button), the game resumes.
     * ===
     * The method is stated here as it links up to the action of a button! (It's also static so it can map up to
     * the "SPACE" bar.)
     */
    public static void pauseGame(){
        String[] option = {"Return to Game"};
        ImageIcon icon = new ImageIcon(IMG_URL + "pause.png");

        //The timer is stopped when the game is paused, if the timer has been initialised.
        if (UserInterface.timer != null) UserInterface.timer.stop();

        /*
         * The program will not continue running as long as this Dialog box is on the screen.
         * Take note it can also be closed by hitting the "ESC" key or the "X" button on the window!
         */
        JOptionPane.showOptionDialog(null, PauseScreen.pause, "PAUSED",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, icon, option, option[0]);

        if (UserInterface.timer != null) UserInterface.timer.start();
    }
}
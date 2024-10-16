package nz.ac.wgtn.swen225.lc.app;

import nz.ac.wgtn.swen225.lc.app.keybinders.ControlKeys;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Controls the "Input" and "Output" from pressing a button or a key during main gameplay.
 * It also includes some basic commands that are executed from buttons or keys, such as the pausing of the
 * game and the displaying of the Game Instructions during gameplay.
 */
public class IOController {
    private final String IMG_URL = "src/main/nz/ac/wgtn/swen225/lc/app/assets/";

    private final List<DefaultButton> mainUIButtons;
    private final ControlKeys keyController;

    //To prevent more than one IO Controller from being created.
    private static final IOController IC = new IOController();
    public static IOController ic = IC;

    /**
     * In the constructor, the buttons and the keystrokes are initialised to their actions.
     */
    private IOController(){
        mainUIButtons = UIButtons.mainUIButtons(() -> endGame(true), () -> endGame(false));

        keyController= new ControlKeys(Map.of(
                "EXIT", () -> endGame(false),
                "SAVE", () -> endGame(true),
                "RESUME", this::resumeExistingGameFromCurrentGame,
                "PAUSE", this::pauseGame,
                "S_REPLAY", App::callStepReplay
        ));
    }

    /**
     * Loads and automatically resumes an existing game from a ".json" file. This process is cancelled if the user
     * terminates the loading of a file.
     */
    protected void resumeExistingGame(){
        File fileToLoad = loadExistingGame();
        if (fileToLoad != null) UserInterface.ui.startGame(fileToLoad);
    }

    /**
     * Similar to the above method "resumeExistingGame", except it checks to make sure you're OK with exiting
     * the current game before asking you to select a game file.
     */
    private void resumeExistingGameFromCurrentGame(){
        int result = JOptionPane.showConfirmDialog(
                null, "Are you sure want to exit without saving?",
                "Confirm", JOptionPane.YES_NO_OPTION
        );

        if (result == JOptionPane.NO_OPTION) return;

        resumeExistingGame();
    }

    /**
     * Finishes up an already-started game and returns the user back to the main menu. If selected, the current game will also
     * be saved.
     *
     * @param save Whether the current game will be saved to a file or not!
     */
    public void endGame(boolean save){
        if (save){
            UserInterface.ui.saveGame();
        } else {
            int result = JOptionPane.showConfirmDialog(
                    null, "Are you sure want to exit without saving?",
                    "Confirm", JOptionPane.YES_NO_OPTION
            );

            if (result == JOptionPane.NO_OPTION) return;
        }

        UserInterface.ui.endGame();
    }

    /**
     * Creates the pop-up window that re-iterates the instructions that apply to the game.
     * The method is stated here as it links up to the action of a button!
     */
    protected void createHelpDialog(){
        JOptionPane.showMessageDialog(null, Instructions.instructionsPanel, "Help", JOptionPane.PLAIN_MESSAGE);
    }

    /**
     * Pauses the game currently in progress and creates a pop-up window which indicates that the game is paused.
     * When closed (either by hitting "ESC" or the "Return to Game" button), the game resumes.
     * ===
     * The method is stated here as it links up to the action of a button! (It's also static so it can map up to
     * the "SPACE" bar.)
     */
    public void pauseGame(){
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

    /**
     * Loads an existing game from a ".json" file using the JFileChooser mechanism. You are repeatedly asked for a
     * file until you either select a valid file, or if you decide to abandon selecting a valid file.
     * ===
     * References:
     * https://www.tutorialspoint.com/get-the-path-of-the-file-selected-in-the-jfilechooser-component-with-java
     * https://www.geeksforgeeks.org/java-swing-jfilechooser/
     */
    private File loadExistingGame(){
        boolean validFileSelected = false;
        File chosenFile = null;

        while (!validFileSelected){
            JFileChooser chooseFile = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
            int result = chooseFile.showOpenDialog(null);

            if (result != JFileChooser.APPROVE_OPTION) return null; //If you decide to cancel the operation.

            chosenFile = chooseFile.getSelectedFile();

            if (chosenFile.getName().contains(".json")){ validFileSelected = true; }
            else {
                JOptionPane.showMessageDialog(null, "Invalid File Selected! Only \".json\" files can be selected!",
                        "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }

        return chosenFile;
    }

    /** Getters for retrieving the UI Buttons and the Key Controller. */
    public List<DefaultButton> getMainUIButtons(){ return Collections.unmodifiableList(mainUIButtons); }
    public ControlKeys getKeyController(){ return keyController; }
}
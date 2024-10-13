package nz.ac.wgtn.swen225.lc.app;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Class which controls the "Start Menu" GUI, including the Buttons and their corresponding actions.
 */
public class StartUI extends UserInterface{
    public StartUI(){
        JPanel instructions = Instructions.instructionsPanel;
        JPanel buttons = createButtonsSection();

        /*
         * "startGame" will be changed so when executed, the contents on the Start Menu are removed.
         * This is because this action will be run when a game is started.
         */
        startGame = () -> {
            remove(instructions); remove(buttons);
            SwingUtilities.updateComponentTreeUI(this); //Refreshes the JFrame after the objects are removed!
            createMainMenu();
        };

        add(BorderLayout.NORTH, instructions);
        add(BorderLayout.CENTER, buttons);
    }

    /**
     * Creates a "JPanel" that will hold the buttons of the Start Menu. One of the buttons will start a new
     * game for the player, and the other will allow the player to select an existing game to resume.
     */
    public JPanel createButtonsSection(){
        JPanel buttons = new JPanel();
        buttons.add(new DefaultButton((unused -> startGame.run()), "Start new game!"));
        buttons.add(new DefaultButton(unused -> {}, "Resume existing game!"));

        return buttons;
    }

    /**
     * Loads and automatically resumes an existing game from a ".json" file. This process is cancelled if the user
     * terminates the loading of a file.
     */
    public void resumeExistingGame(){
        File fileToLoad = loadExistingGame();

        if (fileToLoad != null){
            startGame.run();
        }
    }

    /**
     * Loads an existing game from a ".json" file using the JFileChooser mechanism. You are repeatedly asked for a
     * file until you either select a valid file, or if you decide to abandon selecting a valid file.
     * ===
     * References:
     * https://www.tutorialspoint.com/get-the-path-of-the-file-selected-in-the-jfilechooser-component-with-java
     * https://www.geeksforgeeks.org/java-swing-jfilechooser/
     */
    public static File loadExistingGame(){
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
}
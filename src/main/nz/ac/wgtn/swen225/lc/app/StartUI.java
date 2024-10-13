package nz.ac.wgtn.swen225.lc.app;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Class which controls the "Start Menu" GUI, including the Buttons and their corresponding actions.
 */
public class StartUI{
    /**
     * Creates a "JPanel" that will hold the buttons of the Start Menu. One of the buttons will start the game
     * itself, and if a game has not been loaded (which is loaded by pressing the "Load" button), a new game will
     * be initiated.
     */
    public static JPanel createButtonsSection(ActionListener onStart){
        JPanel buttons = new JPanel();
        buttons.add(new DefaultButton(onStart, "Start new game!"));
        buttons.add(new DefaultButton(unused -> {}, "Resume existing game!"));

        return buttons;
    }

    /**
     * Loads an existing game from a ".json" file using the JFileChooser mechanism. You are repeatedly asked for a
     * file until you either select a valid file, or if you decide to abandon selecting a valid file.
     *
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
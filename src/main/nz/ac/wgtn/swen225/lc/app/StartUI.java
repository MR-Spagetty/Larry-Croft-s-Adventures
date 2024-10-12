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
        JButton start = new JButton("Start the Game!");
        JButton load = new JButton("Load existing game!");

        buttons.add(start);
        buttons.add(load);

        start.addActionListener(onStart);
        load.addActionListener(unused -> loadExistingGame());

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
    public static void loadExistingGame(){
        boolean validFileSelected = false;
        File chosenFile;

        while (!validFileSelected){
            JFileChooser chooseFile = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
            int result = chooseFile.showOpenDialog(null);

            if (result != JFileChooser.APPROVE_OPTION) return;

            chosenFile = chooseFile.getSelectedFile();

            if (chosenFile.getName().contains(".json")){ validFileSelected = true; }
            else {
                JOptionPane.showMessageDialog(null, "Invalid File Selected! Only \".json\" files can be selected!",
                        "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
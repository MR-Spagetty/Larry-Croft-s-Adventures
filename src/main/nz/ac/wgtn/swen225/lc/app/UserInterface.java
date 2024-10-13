package nz.ac.wgtn.swen225.lc.app;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

/**
 * Class which is responsible for handling the "Graphical User Interface" of the game.
 *
 * @author Developer 1 <dev1@example.internal>
 */
public class UserInterface extends JFrame{
    /*
     * Action to be executed when the user closes the Game GUI with the 'X' button.
     * This action will be mostly similar to quitting the current game playing, as you will also be
     * asked whether you want to save the game before quitting.
     */
    Runnable closeGame = ()->{};

    //Executed when the player wants to start a game. It basically removes all of the Start UI components from the frame.
    Runnable removeStartUI = () -> {};

    /*
     * Timer mainly for determining when to trigger the "draw" mechanism in the Renderer. This timer is static, so
     * the Pause Screen can stop and start it to "technically" pause the game.
     */
    static Timer timer;

    GameUI gameControls; //The wider "Game UI" that the user will be interacting with!

    private final int WIDTH = 1200;
    private final int HEIGHT = 600;

    /**
     * Constructor of the Graphical User Interface, which is where the GUI is set up when you start up the game.
     * This involves defining the size of the GUI window, and putting the Start Menu components inside.
     */
    public UserInterface(){
        assert SwingUtilities.isEventDispatchThread();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));

        addWindowListener(new WindowAdapter(){
            public void windowClosed(WindowEvent e){ closeGame.run(); }
        });

        createStartMenu();

        pack();
        setVisible(true);

        gameControls = new GameUI(WIDTH/4, HEIGHT);
    }

    /**
     * Helper method which creates the components present in the Main menu. This also sets up the keys to be used
     * in the game.
     */
    private void createMainMenu(){
        this.add(BorderLayout.EAST, gameControls.createMenu());
        this.addKeyListener(ControlKeys.keyController);

        /*
        GraphicsPane pane = new GraphicsPane();
        add(BorderLayout.CENTER, pane);
        timer = gameControls.createTimer(pane);

        this.setFocusable(true);
        pack();
        this.requestFocus();

        timer.start();
         */
    }

    /**
     * Helper method which creates the components present in the Start Menu, including the Buttons and their
     * corresponding actions.
     */
    private void createStartMenu(){
        JPanel instructions = Instructions.instructionsPanel;
        JPanel buttons = createButtonsSection();

        //When we start or resume a game, we will need to remove all the Start UI elements, so we can add the Game UI in.
        removeStartUI = () -> {
            remove(instructions); remove(buttons);
            SwingUtilities.updateComponentTreeUI(this); //Refreshes the JFrame after the objects are removed!
        };

        add(BorderLayout.NORTH, instructions);
        add(BorderLayout.CENTER, buttons);
    }

    /**
     * Creates a "JPanel" that will hold the buttons of the Start Menu. One of the buttons will start a new
     * game for the player, and the other will allow the player to select an existing game to resume.
     */
    private JPanel createButtonsSection(){
        JPanel buttons = new JPanel();
        buttons.add(new DefaultButton((unused -> startGame(null)), "Start new game!"));
        buttons.add(new DefaultButton(unused -> resumeExistingGame(), "Resume existing game!"));

        return buttons;
    }

    /**
     * Loads and automatically resumes an existing game from a ".json" file. This process is cancelled if the user
     * terminates the loading of a file.
     */
    private void resumeExistingGame(){
        File fileToLoad = loadExistingGame();
        if (fileToLoad != null) startGame(fileToLoad);
    }

    /** Creates a new game and runs it. */
    private void startGame(File gameFile){
        if (gameFile == null)
            gameFile = new File("src/main/nz/ac/wgtn/swen225/lc/persistency/examplelvl1.json");

        removeStartUI.run();
        /** TODO Call a method that takes in a game file and effectively "starts the game". Recorder will need it. */
        createMainMenu();
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
}
package nz.ac.wgtn.swen225.lc.app;

import nz.ac.wgtn.swen225.lc.app.otherpanels.Instructions;
import nz.ac.wgtn.swen225.lc.app.panels.GameGraphicsPane;
import nz.ac.wgtn.swen225.lc.app.panels.GamePanel;
import nz.ac.wgtn.swen225.lc.app.panels.StartButtonsPanel;
import nz.ac.wgtn.swen225.lc.domain.PlayerAction;
import nz.ac.wgtn.swen225.lc.recorder.Recorder;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.io.File;
import java.nio.file.Path;

/**
 * Class which is responsible for handling the "Graphical User Interface" of the game.
 *
 * @author Developer 1 <dev1@example.internal>
 */
public class UserInterface extends JFrame{
    //Executed when the player ends a game and goes back to the start menu.
    Runnable removeGameUI = () -> {};

    //Executed when the player wants to start a game. It basically removes all the Start UI components from the frame.
    Runnable removeStartUI = () -> {};

    /*
     * Timer mainly for determining when to trigger the "draw" mechanism in the Renderer. This timer is static, so
     * the Pause Screen can stop and start it to "technically" pause the game.
     */
    static Timer timer;

    private final int WIDTH = 1200, HEIGHT = 600;

    //To prevent more than one User Interface instance from being created.
    private static final UserInterface USER_INTERFACE = new UserInterface();
    public static UserInterface ui = USER_INTERFACE;

    /**
     * An empty constructor. This was placed here on purpose to prevent the initialisation of a new "UserInterface"
     * class, and encourage the use of the static instance "ui". Singleton pattern at work, as usual!
     */
    private UserInterface(){}

    /**
     * Method which creates the physical menu of the "User Interface" class.
     * This involves defining the size of the GUI window, and putting the Start Menu components inside.
     */
    public void createMenu(){
        assert SwingUtilities.isEventDispatchThread();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));

        createStartMenu();

        pack();
        setVisible(true);
    }

    /**
     * Helper method which creates the components present in the Start Menu, including the Buttons and their
     * corresponding actions.
     */
    private void createStartMenu(){
        JPanel instructions = Instructions.instructionsPanel;
        StartButtonsPanel buttons = new StartButtonsPanel(
                () -> startGame(null), () -> IOController.ic.resumeExistingGame(), () -> {}
        );

        removeStartUI = () -> {
            remove(instructions); remove(buttons);
            SwingUtilities.updateComponentTreeUI(this);
        };

        add(BorderLayout.NORTH, instructions);
        add(BorderLayout.CENTER, buttons);
    }

    /**
     * Helper method which creates the components present in the Main menu. This also sets up the keys to be used
     * in the game.
     */
    private void createMainMenu(){
        //The wider "Game UI" that the user will be interacting with!
        GamePanel gameControls = new GamePanel(Color.DARK_GRAY, WIDTH/4, HEIGHT, IOController.ic.getMainUIButtons());

        GameGraphicsPane pane = new GameGraphicsPane();

        removeGameUI = () -> {
            remove(gameControls); remove(pane);
            SwingUtilities.updateComponentTreeUI(this); //Refreshes the JFrame after the objects are removed!
        };

        this.add(BorderLayout.EAST, gameControls);
        this.addKeyListener(IOController.ic.getKeyController());

        this.setFocusable(true);
        this.requestFocus();
        pack();

        //add(BorderLayout.CENTER, pane);
        //timer = gameControls.createTimer(pane);
        //timer.start();
    }

    /**
     * Creates a new game and runs it. This can be done from an existing game file, if necessary.
     *
     * @param gameFile The file containing the game to be resumed, if the player is resuDeveloper 4 <dev4@example.internal> a game.
     *                 In other cases, such as when the player wants to start a new game, this file is "NULL".
     */
    public void startGame(File gameFile){
        askToRecordGame();

        //If a new game is being started, we will set the game file to be the first level.
        if (gameFile == null){
            gameFile = new File("src/main/nz/ac/wgtn/swen225/lc/persistency/examplelvl1.json");
        }

        //Might use: GameState.getGameState().loadState(....);
        //GameState.getGameState().setLevel(gameFile.toPath());
        removeStartUI.run();
        createMainMenu();
        Recorders.recs.startRecordingLevel(gameFile.toPath());
    }

    /**
     * When a user finishes one level, they will be taken to the next level. This involves the recorder being
     * signalled to stop one level and begin the next.
     */
    public void goBetweenLevels(){

        /** TODO, figure out the path needed for the second level! */
        /*
        if (rec != null){
            rec.endLevel();
            rec.startLevel(...);
        }
        */
    }

    protected void saveGame(){
        /**
         * TODO Possibly call a method from Domain that will SAVE the game state! (i.e: saveState(...)"
         */
        //GameState.getGameState().saveState(....).
    }

    /**
     * Removes the content on the current JFrame that allows for playing the game, and puts back the content on the
     * Start Menu. This is executed when the user exits a current game.
     */
    protected void endGame(){
        Recorders.recs.stopRecordingGame();
        removeGameUI.run();
        createStartMenu();
    }

    /**
     * Asks the user whether they want the game to be recorded or not.
     * If they ask for the game to be recorded, then they need to select where to store the files!
     */
    private void askToRecordGame(){
        int recordGame = JOptionPane.showConfirmDialog(
                null, "Do you want to record the game?",
                "Record Game?", JOptionPane.YES_NO_OPTION
        );

        if (recordGame == JOptionPane.YES_OPTION){
            recorderPath = selectRecorderFolder();
            if (recorderPath == null) return;

            rec = new Recorder(recorderPath);
        }
    }

    /**
     * Selects the folder that will store the recorded files.
     *
     * @return The path to the folder. "null" is returned when no folder is selected, such as when the user aborts
     *         selecting a file.
     */
    private Path selectRecorderFolder(){
        JFileChooser chooseFolder = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        chooseFolder.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); //So we do not select a file by accident!
        int result = chooseFolder.showOpenDialog(null);

        //If you decide to cancel the operation, you will be told that the game will NOT be recorded!
        if (result != JFileChooser.APPROVE_OPTION){
            JOptionPane.showMessageDialog(
                    null, "No folder path has been selected! Recorder will not be initiated.",
                    "Info", JOptionPane.PLAIN_MESSAGE);
            return null;
        }

        return chooseFolder.getSelectedFile().toPath();
    }
}
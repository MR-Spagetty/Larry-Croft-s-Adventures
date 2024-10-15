package nz.ac.wgtn.swen225.lc.app;

import nz.ac.wgtn.swen225.lc.domain.GameState;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.util.Collections;
import java.util.List;
import java.io.File;
import java.util.Map;

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

    //To prevent more than one "Lite" User Interface instance from being created.
    private static final UserInterface USER_INTERFACE = new UserInterface();
    public static UserInterface ui = USER_INTERFACE;

    /**
     * Constructor of the Graphical User Interface, which is where the GUI is set up when you start up the game.
     * Here, a "lite" version is created for use by the "Fuzz" class, which doesn't do any of the actual "GUI"
     * setup. The setup needs to be done in a method called "createMenu".
     */
    private UserInterface(){
        createMenu();
    }

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
        JPanel buttons = startUIButtonPanel(() -> startGame(null), () -> Controller.ic.resumeExistingGame());

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
        GameUI gameControls = new GameUI(Color.DARK_GRAY, WIDTH/4, HEIGHT, Controller.ic.getMainUIButtons());

        GraphicsPane pane = new GraphicsPane();

        removeGameUI = () -> {
            remove(gameControls); remove(pane);
            SwingUtilities.updateComponentTreeUI(this); //Refreshes the JFrame after the objects are removed!
        };

        this.add(BorderLayout.EAST, gameControls);
        this.addKeyListener(Controller.ic.getKeyController());

        this.setFocusable(true);
        this.requestFocus();
        pack();

        //add(BorderLayout.CENTER, pane);
        //timer = gameControls.createTimer(pane);
        //timer.start();
    }

    /** Creates a new game and runs it. This can be done from an existing game file, if neccesary. */
    protected void startGame(File gameFile){
        if (gameFile == null) gameFile = new File("src/main/nz/ac/wgtn/swen225/lc/persistency/examplelvl1.json");

        removeStartUI.run();
        GameState.getGameState().setLevel(gameFile.toPath());
        createMainMenu();
    }

    protected void saveGame(){
        /**
         * TODO Possibly call a method from Domain that will SAVE the game state! (i.e: saveState(...)"
         * TODO Call the "saveGame()" method in the recorder to stop recording!
         */
    }

    /**
     * Removes the content on the current JFrame that allows for playing the game, and puts back the content on the
     * Start Menu. This is executed when the user exits a current game.
     */
    protected void endGame(){
        removeGameUI.run();
        createStartMenu();
    }

    /**
     * Creates the "JPanel" that will hold the buttons of the Start Menu. One of the buttons will start a new
     * game for the player, and the other will allow the player to select an existing game to resume.
     */
    private JPanel startUIButtonPanel(Runnable startGame, Runnable resumeGame){
        JPanel buttons = new JPanel();

        buttons.add(new DefaultButton(unused -> startGame.run(), "Start new game!"));
        buttons.add(new DefaultButton(unused -> resumeGame.run(), "Resume existing game!"));

        return buttons;
    }
}

class Controller {
    private final List<DefaultButton> mainUIButtons;
    private final ControlKeys keyController;

    //To prevent more than one "Lite" User Interface instance from being created.
    private static final Controller IC = new Controller();
    public static Controller ic = IC;

    private Controller(){
        mainUIButtons = Buttons.mainUIButtons(() -> endGame(true), () -> endGame(false), () -> {});

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
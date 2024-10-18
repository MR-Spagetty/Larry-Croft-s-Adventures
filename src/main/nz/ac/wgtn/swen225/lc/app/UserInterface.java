package nz.ac.wgtn.swen225.lc.app;

import nz.ac.wgtn.swen225.lc.app.otherpanels.Instructions;
import nz.ac.wgtn.swen225.lc.app.panels.GameGraphicsPane;
import nz.ac.wgtn.swen225.lc.app.panels.GamePanel;
import nz.ac.wgtn.swen225.lc.app.panels.StartButtonsPanel;
import nz.ac.wgtn.swen225.lc.domain.GameState;
import nz.ac.wgtn.swen225.lc.renderer.Sound;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.Serial;
import java.nio.file.Path;

/**
 * Class which is responsible for handling the "Graphical User Interface" of the game.
 *
 * @author Developer 1 <dev1@example.internal>
 */
public class UserInterface extends JFrame{
    @Serial private static final long serialVersionUID= 1L;

    //Executed when the player switches User Interfaces, notably when the player starts or ends a game.
    Runnable switchUIs = () -> {};

    /*
     * The graphics pane that displays the game is stored globally, so Renderer can access it.
     * However, it is not fully initialised until a game is in progress!
     */
    private GameGraphicsPane pane = null;

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
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));

        createStartMenu();
        setVisible(true);
    }

    /**
     * Helper method which creates the components present in the Start Menu, including the Buttons and their
     * corresponding actions.
     */
    private void createStartMenu(){
        JPanel instructions = Instructions.instructionsPanel;
        StartButtonsPanel buttons = StartButtonsPanel.sbp;

        switchUIs.run();
        switchUIs = () -> {
            remove(instructions); remove(buttons);
            SwingUtilities.updateComponentTreeUI(this);
        };

        pane = null; //The graphics pane is not needed for the Start Menu, so this will be set to being "null".

        this.add(BorderLayout.NORTH, instructions);
        this.add(BorderLayout.CENTER, buttons);
        this.pack();
    }

    /**
     * Helper method which creates the components present in the Main menu. This also sets up the keys to be used
     * in the game.
     */
    private void createMainMenu(){
        int offsetWidth = (WIDTH * 3/4);

        //This value was guessed, as the height difference is determined by the height of the text!
        int offsetHeight = 40;

        //The wider "Game UI" that the user will be interacting with!
        GamePanel gameControls = new GamePanel(
                Color.DARK_GRAY, offsetWidth, offsetHeight, WIDTH/4, HEIGHT, IOController.ic.getMainUIButtons()
        );

        pane = new GameGraphicsPane(offsetWidth, HEIGHT);

        switchUIs.run();
        switchUIs = () -> {
            remove(gameControls); remove(pane);
            SwingUtilities.updateComponentTreeUI(this); //Refreshes the JFrame after the objects are removed!
        };

        this.add(BorderLayout.EAST, gameControls);
        this.add(BorderLayout.CENTER, pane);
        this.addKeyListener(IOController.ic.getKeyController());
        this.setFocusable(true);

        //The graphics pane will be refreshed every time a tick occurs.
        GameState.getGameState().tickTimer.addActionListener((unused) -> {
            assert SwingUtilities.isEventDispatchThread();
            pane.repaint();
        });

        this.pack();
        this.requestFocus();
    }

    /**
     * Starts a new game from a specific level.
     *
     * @param levelFile The file containing the level to start the game from.
     */
    public void startNewGame(File levelFile){
        Recorders.recs.askToRecordGame();
        initLevel(levelFile);
        startGame();
    }

    /**
     * Initialises a level in the game.
     *
     * @param levelFile The file containing the level to be initialized.
     */
    public void initLevel(File levelFile){
        GameState.getGameState().setLevel(levelFile.toPath());
        Recorders.recs.startRecordingLevel(levelFile.toPath());
    }

    /**
     * Initialises the level in the game by retrieving all key information from the Game State, and then writing it to
     * the Information board.
     *
     * TODO: Test the written code once all other issues in the game (none of those are related to "App") are fixed.
     */
    public void initLevelInfo(){
        GameState gs = GameState.getGameState();
        long levelID = GameState.getGameState().getMaze().longID();
        long timeRemaining = gs.getMaze().maxTicks * GameState.DEFAULT_TICK_RATE;
        int remainingTreasures = Math.max(0, gs.requiredTreasures() - gs.collectedTreasures());

        GameInfo.info.initialiseInformation(levelID, (int)timeRemaining, remainingTreasures);
    }

    /**
     * Starts a game (new or existing), after loading in a level from a file. This involves setting up the
     * main UI that the player will interact with, and load in information about the level into the display.
     */
    public void startGame(){
        GameState.getGameState().tickTimer.start();
        createMainMenu();
        initLevelInfo();

        new Sound().playSound("gameStart");
    }

    /**
     * When a user finishes one level, they will be taken to the next level. This involves the recorder being
     * signalled to stop one level and begin the next.
     * A "next" level will not begin recording if the path to the next level is "null".
     */
    public void goBetweenLevels(File nextLevel){
        Recorders.recs.stopRecordingCurrentLevel();
        if (nextLevel != null) Recorders.recs.startRecordingLevel(nextLevel.toPath());
    }

    /** Saves the current game to a file. (NB: This is not the recorded game file). */
    protected void saveGame(){
        GameState.getGameState().saveState(Path.of("savedGames/currentGame.json"));
    }

    /**
     * Removes the content on the current JFrame that allows for playing the game, and puts back the content on the
     * Start Menu. This is executed when the user exits a current game.
     */
    protected void endGame(){
        goBetweenLevels(null); //No file path is provided, as we are ending the game.
        Recorders.recs.stopRecordingGame();
        createStartMenu();
    }

    /**
     * Starts the playback of a recorded game.
     * TODO if time allows: Finish it
     */
    public void startGamePlayback(){}

    /** @return The Graphics Pane where the content is being rendered. This can be "Null" if not in use. */
    public GameGraphicsPane getGraphicsPane(){ return pane; }
}
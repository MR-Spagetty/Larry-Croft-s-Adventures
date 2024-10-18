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
     * Starts a new game or an existing game from the game file. If a new game is started, it should ask you whether the
     * game is to be recorded.
     *
     * @param gameFile The file containing the game to be resumed, if the player is resuDeveloper 4 <dev4@example.internal> a game.
     *                 In other cases, such as when the player wants to start a new game, this file is "NULL".
     */
    public void startGame(File gameFile){
        if (gameFile == null) Recorders.recs.askToRecordGame();

        //If a new game is being started, we will set the game file to be the first level.
        gameFile = new File("src/resources/levels/level0.json");

        GameState.getGameState().setLevel(gameFile.toPath());
        GameState.getGameState().tickTimer.start();
        Recorders.recs.startRecordingLevel(gameFile.toPath());
        createMainMenu();
        initLevelInfo();

        new Sound().playSound("gameStart");
    }

    /**
     * Initialises a level in the game.
     *
     * @param levelFile The file containing the level to be initialized.
     */
    public void initLevel(File levelFile){

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
        GameState.getGameState().tickTimer.stop();
        GameInfo.info.countdownTimer.stop();

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
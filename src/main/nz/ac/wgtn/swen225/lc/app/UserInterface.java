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
        pane = null; //The graphics pane is not needed for the Start Menu, so this will be set to being "null".

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

        pane = new GameGraphicsPane((WIDTH * 3/4), HEIGHT);

        removeGameUI = () -> {
            remove(gameControls); remove(pane);
            SwingUtilities.updateComponentTreeUI(this); //Refreshes the JFrame after the objects are removed!
        };

        this.add(BorderLayout.EAST, gameControls);
        this.addKeyListener(IOController.ic.getKeyController());

        this.setFocusable(true);
        this.requestFocus();
        pack();

        add(BorderLayout.CENTER, pane);

        //The graphics pane will be refreshed every time a tick occurs.
        GameState.getGameState().tickTimer.addActionListener((unused) -> {
            assert SwingUtilities.isEventDispatchThread();
            pane.repaint();
        });
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

        removeStartUI.run();
        createMainMenu();

        Recorders.recs.startRecordingLevel(gameFile.toPath());

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

    /** Saves the current game to a file. */
    protected void saveGame(){
        GameState.getGameState().saveState(Path.of("savedGames/currentGame.json"));
    }

    /**
     * Removes the content on the current JFrame that allows for playing the game, and puts back the content on the
     * Start Menu. This is executed when the user exits a current game.
     *
     * TODO: Recording file needs to be saved to the chosen directory. This is not happening ATM. Do ASAP!
     */
    protected void endGame(){
        goBetweenLevels(null); //No file path is provided, as we are ending the game.
        Recorders.recs.stopRecordingGame();
        removeGameUI.run();
        createStartMenu();

        GameState.getGameState().tickTimer.stop();
        GameInfo.info.countdownTimer.stop();
    }

    /**
     * Returns the graphics pane for use by the renderer.
     *
     * @return The Graphics Pane where the content is being rendered. This can be "Null" if not in use.
     */
    public GameGraphicsPane getGraphicsPane(){ return pane; }
}
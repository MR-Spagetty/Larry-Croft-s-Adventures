package nz.ac.wgtn.swen225.lc.app;

import nz.ac.wgtn.swen225.lc.domain.GameState;

import javax.swing.*;
import java.awt.*;
import java.io.File;

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
        JPanel buttons = startUIButtonPanel(() -> startGame(null), () -> IOController.ic.resumeExistingGame());

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
        GameUI gameControls = new GameUI(Color.DARK_GRAY, WIDTH/4, HEIGHT, IOController.ic.getMainUIButtons());

        GraphicsPane pane = new GraphicsPane();

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

    /** Creates a new game and runs it. This can be done from an existing game file, if neccesary. */
    protected void startGame(File gameFile){
        if (gameFile == null) gameFile = new File("src/main/nz/ac/wgtn/swen225/lc/persistency/examplelvl1.json");

        GameState.getGameState().setLevel(gameFile.toPath());
        removeStartUI.run();
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

package nz.ac.wgtn.swen225.lc.app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Set;

/**
 * Class which is responsible for handling the "Graphical User Interface" of the game.
 * TODO: ADD IN BUTTONS TO DO RECORDING! And add an area that will display the items picked up!
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

    /*
     * Action to be executed when the user begins playing a game. (i.e.: When we switch from the Start Menu to
     * the Game Menu itself.) Currently, the Runnable action does not do anything, as what is executed is dependent
     * on the components that are in the start menu (as these are removed).
     */
    Runnable startGame = ()->{};

    /*
     * Timer mainly for determining when to trigger the "draw" mechanism in the Renderer. This timer is static, so
     * the Pause Screen can stop and start it to "technically" pause the game.
     */
    static Timer timer;

    //An instance of the wider "Game UI" that the user will be interacting with when they are playing the game!
    GameUI gameControls;

    ControlKeys keyController; //An instance of the "Key Controller" they will be interacting with.

    private final int WIDTH = 1200;
    private final int HEIGHT = 600;

    /**
     * Constructor of the Graphical User Interface, which is where the GUI is set up when you start up the game.
     * This involves defining the size of the GUI window, and putting the Start Menu components inside.
     * TODO: make a more professional version of the Start Menu GUI.
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
        keyController = new ControlKeys();
    }

    /**
     * Helper method that creates the "components" that will be in the Start Menu.
     */
    private void createStartMenu(){
        JPanel instructions = new Instructions();
        JPanel buttons = StartUI.createButtonsSection((unused -> startGame.run()));

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
     * Helper method which creates the components present in the Main menu. This also sets up the keys to be used
     * in the game.
     * TODO: make a more professional version of the Main Menu GUI and add in the pane for displaying the graphics.
     */
    private void createMainMenu(){
        this.add(BorderLayout.EAST, gameControls.createMenu());
        this.addKeyListener(keyController);

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
     * Recursion at work here: This method returns the list of buttons that have been created in the game,
     * which is done in a method in "GameUI".
     */
    public Set<DefaultButton> getButtons(){ return gameControls.getButtons(); }

    /**
     * Recursion at work here: This method returns the list of keystrokes associated with an action,
     * which is done in a method in "KeyStrokes". (Which extends "ControlKeys".)
     */
    public Set<Integer> getKeyStrokes(){ return keyController.getKeyStrokes(); }
}
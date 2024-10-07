package nz.ac.wgtn.swen225.lc.app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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
    }

    /**
     * Helper method that creates the "components" that will be in the Start Menu.
     */
    private void createStartMenu(){
        JLabel instructions = StartUI.instructions;
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
        add(BorderLayout.EAST, GameUI.createMenu(WIDTH/4, HEIGHT));

        /*
        GraphicsPane pane = new GraphicsPane();
        add(BorderLayout.CENTER, pane);
        timer = GameUI.createTimer(pane);

        this.addKeyListener(new ControlKeys());
        this.setFocusable(true);
        pack();
        this.requestFocus();

        timer.start();
         */
    }
}
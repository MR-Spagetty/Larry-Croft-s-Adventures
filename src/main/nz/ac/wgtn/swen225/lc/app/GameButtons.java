package nz.ac.wgtn.swen225.lc.app;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Holds the buttons that perform specific actions in relation to the game and the GUI.
 */
public class GameButtons extends GridPanel{
    List<DefaultButton> buttonsToAdd = new ArrayList<>();
    private final static String IMG_URL = "src/main/nz/ac/wgtn/swen225/lc/app/assets/";

    /*
     * To prevent two instances of a "Game Buttons" class from being created, we create a single
     *  instance here, and make it accessible!
     */
    private static final GameButtons GAMEBUTTONS = new GameButtons();
    static GameButtons gameButtons = GAMEBUTTONS;

    public GameButtons(){
        super(5, 1);
        this.buttonsToAdd = createGameButtons();
    }

    /**
     * Separate method which does the actual "construction" of the panel. This method is to
     * allow for the option of creating the Game Buttons without needing to create the full panel.
     * (Largely for Fuzz testing purposes.)
     */
    public void constructPanel(Color backgroundColor, int width, int height, float fontSize){
        super.setBackground(backgroundColor);

        buttonsToAdd.forEach(b -> {
            b.setPreferredSize(new Dimension(width, height));
            if (b.getText() != null) b.setFont(b.getFont().deriveFont(fontSize));
            this.add(b);
        });
    }

    /**
     * Creates all the Game Buttons that will interact with the game itself. This is done before initialising the
     * panel that contains all of these buttons.
     */
    public List<DefaultButton> createGameButtons(){
        DefaultButton saveGame = new DefaultButton(unused -> {}, "SAVE");
        DefaultButton pauseGame = new DefaultButton(unused -> pauseGame(), "PAUSE");
        DefaultButton exitGame = new DefaultButton(unused -> {}, "EXIT");
        DefaultButton displayHelp = new DefaultButton(unused -> createHelpDialog(), "HELP");
        DefaultButton recordButton = new DefaultButton(unused -> {}, new ImageIcon(IMG_URL + "record.png"));

        return List.of(recordButton, saveGame, pauseGame, exitGame, displayHelp);
    }

    private void createHelpDialog(){
        JOptionPane.showMessageDialog(null, Instructions.instructionsPanel, "Help", JOptionPane.PLAIN_MESSAGE);
    }

    /**
     * Pauses the game currently in progress and creates a pop-up window which indicates that the game is paused.
     * When closed (either by hitting "ESC" or the "Return to Game" button), the game resumes.
     */
    public static void pauseGame(){
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

    /** Returns the list of buttons that have been created in the game. */
    public List<DefaultButton> getButtons(){ return Collections.unmodifiableList(buttonsToAdd); }
}
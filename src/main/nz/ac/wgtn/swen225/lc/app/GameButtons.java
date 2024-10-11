package nz.ac.wgtn.swen225.lc.app;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Class which holds the buttons that perform specific actions in relation to the game and the GUI.
 */
public class GameButtons extends GridPanel{
    //The Screen that will be displayed when the game is paused.
    static PauseScreen ps = new PauseScreen(200);

    List<DefaultButton> buttonsToAdd = new ArrayList<>();

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
        DefaultButton pauseGame = new DefaultButton(unused -> ps.showScreen(), "PAUSE");
        DefaultButton exitGame = new DefaultButton(unused -> {}, "EXIT");
        DefaultButton displayHelp = new DefaultButton(unused -> createHelpDialog(), "HELP");
        DefaultButton recordButton = createRecordButton();

        return List.of(recordButton, saveGame, pauseGame, exitGame, displayHelp);
    }

    private DefaultButton createRecordButton(){
        String url = "src/main/nz/ac/wgtn/swen225/lc/app/assets/record.png";
        ImageIcon icon = new ImageIcon(url);

        return new DefaultButton(unused -> {}, icon);
    }

    private void createHelpDialog(){
        JOptionPane.showMessageDialog(null, Instructions.instructionsPanel, "Help", JOptionPane.PLAIN_MESSAGE);
    }

    /** Returns the list of buttons that have been created in the game. */
    public List<DefaultButton> getButtons(){ return Collections.unmodifiableList(buttonsToAdd); }
}